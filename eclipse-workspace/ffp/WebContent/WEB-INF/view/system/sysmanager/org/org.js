/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 机构维护<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
require(['beneform4j-treepage','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			tree = $('#tree'),
			form = $('#searchForm'),
			popupMenu = $('#popupMenu'),
			apis = {
				getChildren   : 'getChildren',
				find   : 'find',
				insert : 'insert',
				update : 'update',
				remove : 'delete',
				move   : 'move'
			};
		
		return {
			init :  function(){
				// 构建异步加载树
				tree.tree({
					url : apis.getChildren,
					animate: true,
					lines : true,
					dnd : true,
					//初始化状态
					loadFilter: function(data){
						if($.isArray(data)){
							$.each(data, function(i, node){
								node.id = node.orgId;
								node.text = node.orgName;
								node.state = node.count >= 1? 'closed':'open';
								node.attributes = node;
							});
						}
						return data;
					},
					onBeforeLoad : function(node, param) {
						if(node && node.id){
							param.supOrgId = node.id;
						}
					},
					//单击事件
					onClick : me.gotoUpdate,
					//右键事件
					onContextMenu: function(e,node){
						me.onContextMenuFn(e, node, popupMenu);
					},
					//进入某个目标节点
					onDragEnter: function(target){
						me.onDragEnterFn(tree, target);
					},
					//拖动
					onBeforeDrop: function(target, source, point){
						if(point === 'top' || point=== 'buttom'){
							return false;	
						}else{
							//由于机构表中没有额外的排序字段，所以不能拖为某个机构的兄弟机构，同时也保证了只有一个根节点
							return me.doMove(target, source);
						}
					}
				});
				
				$b.Msg.closeProgress();
				
				//绑定事件
				$("a.btn-save").on("click", function(){
					if(form.form('validate')){
						if(me.optFlag === 'insert'){
							me.doAdd();
						}else if(me.optFlag === 'update'){
							me.doUpdate();
						}
					}
				});
				
				popupMenu.find(".gotoAdd").on("click", function(){
					me.gotoAdd(popupMenu.node);
				}) .end().find(".gotoUpdate").on("click", function(){
					me.gotoUpdate(popupMenu.node);
				}) .end().find(".doDelete").on("click", function(){
					me.doDelete(popupMenu.node);
				});
			},
			
			gotoAdd : function (node){
				form.form('clear');
				node = node.attributes;
				$('[name=supOrg]').val(node.text+'('+node.id+')');
				$('[name=supOrgId]').val(node.id);
				$('[name=orgLevel]').val(parseInt(node.orgLevel) + 1);
				$('[name=orgId]').removeAttr('readonly').removeClass('bf4j-readonly'); 
				me.optFlag = 'insert';
			},
			
			doAdd : function (){
				$b.Submit.ajaxSubmitForm(form, {url : apis.insert}, me.addCallback);
			},
			
			addCallback : function (rs, params){
				var id = $('[name=supOrgId]').val(),
					node = tree.tree('find', id);
				tree.tree('append', {//如果父节点原来是叶子节点，需先在页面添加，然后执行reload方法时方可重新加载
					parent : node.target,
					data:[{id: '', text: ''}]
				});
				tree.tree('reload', node.target);
				$b.Msg.alert(locale.operate.doOk);
			},
			
			gotoUpdate : function (node){
				form.form('clear').form('load', node.attributes);
				$('[name=orgId]').attr('readonly','readonly').addClass('bf4j-readonly');  
				var parent = tree.tree('getParent', node.target);
				if(parent){
					$('[name=supOrg]').val(parent.text+'('+parent.id+')');
				}
				me.optFlag = 'update';
			},
			
			doUpdate : function (){
				$b.Submit.ajaxSubmitForm(form, {url : apis.update}, me.updateCallback);
			},
			
			updateCallback : function (){
				var id = $('[name=orgId]').val(),
					node = tree.tree('find', id),
					orgName = $('[name=orgName]').val(),
					orgType = $('[name=orgType]').val(),
					des = $('[name=des]').val();
				$.extend(node.attributes, {text : orgName, orgName : orgName, orgType : orgType, des : des});
				tree.tree('update',{target : node.target, text : orgName});
				$b.Msg.alert(locale.operate.doOk);
			},
			
			doDelete : function (node){
				$b.Msg.confirm(locale.system.sysmanager.org.remove1+"<font color='red'> "+node.text+ ( node.attributes && node.attributes.count > 0 ? locale.system.sysmanager.org.remove2 : "") +" </font>吗？", function(){
	   				$b.Submit.ajaxSubmit(apis.remove, {orgId: node.id}, me.deleteCallback);
	   			});
			},
			
			deleteCallback : function (){
				tree.tree('remove', popupMenu.target);
				$b.Msg.alert(locale.system.sysmanager.org.succeedRemove+'<font color="red">' + popupMenu.node.text + '</font>');
			},
			
			doMove : function(target, source){
				var snode = source,//源节点
					onode = tree.tree('getParent', snode.target),//原父节点
					tnode = tree.tree('getNode', target);//目标节点，这里只能是父节点
				if(onode == tnode){
					return false;
				}
				$b.Msg.confirm(locale.system.sysmanager.org.orgQ1+"<font color='red'> "+snode.text+" </font> "+locale.system.sysmanager.org.orgQ2+"<font color='red'> "+tnode.text+" </font>"+locale.system.sysmanager.org.orgQ3, function(){
					var params = {orgId:snode.id, supOrgId:tnode.id, orgLevel:tnode.attributes.orgLevel+1};
					$b.Submit.ajaxSubmit(apis.move, params, function(){
						me.moveCallback(snode, onode, tnode, params);
					});
	   			});
				return false;
			},
			
			moveCallback : function(snode, onode, tnode, params){
				if(tnode.state === 'closed'){
					tree.tree('reload', tnode.target);
				}
				tree.tree('remove', snode.target);
				tree.tree('append', {
					parent : tnode.target,
					data   : [snode.attributes]
				});
				$.extend(snode.attributes, params);
				
				$b.Msg.alert(locale.operate.doOk);
			}
		};
	});
});