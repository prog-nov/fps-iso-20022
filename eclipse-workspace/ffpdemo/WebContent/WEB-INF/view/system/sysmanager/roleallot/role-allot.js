/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色(分配)管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-15<br>
 */
require(['beneform4j-treepage'], function(Page) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				gotoAdd  : 'gotoAdd',
				add  : 'insert',
				gotoEdit  : 'gotoEdit',
				save   : 'update',
				remove : 'delete',
				forAdd : 'listRolePermissionForAdd',
				forEdit: 'listRolePermissionForEdit'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'roleAllotId',
					pagination : true,
					fitColumns : true,
					rowToolButtons:[ {
						text : '编 辑', iconCls : 'bf4j-icon-row-edit', handler : function(index,data){
							me.gotoUpdate(data);
						}
					}, {
						text : '删 除', iconCls : 'bf4j-icon-row-delete', handler : function(index,data){
							me.doDelete([data]);
						}
					}],
					columns : [ [ 
						{field : 'roleAllotId', title : '角色(分配)代码', checkbox : true},   
						{field : 'roleAllotName', title : '角色(分配)名称', align:'left', width: 200},
						{field : 'des', title : '描述', align:'left', width: 400}
			        ] ]
				});
				
				$b.Msg.closeProgress();
				
				//绑定事件
				$("a.doQuery").on("click", me.doQuery);
				$("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
					   .end().filter(".doDelete").on("click", function(){
						   me.selectRows(grid, me.doDelete);
					    });
			},
			/**
			 * 关闭对话框
			 */
			closeDialog : function(){
				dialog.dialog('close');
			},
			/**
			 * 执行查询
			 */
			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: '新增角色(分配)',    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoAdd,
				    onLoad : function(){
				    	me.loadTab();
				    	//me.loadTree(apis.forAdd);
				    },
				    buttons : [ {
						text : '提交', size : 'large', handler : me.doAdd
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				var form = $('#addForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.add};
				me.collectParams(params);
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: '修改角色(分配)',    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoEdit,
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
				    	me.loadTab(data);
				    },
				    buttons : [ {
						text : '保存', size : 'large', handler : me.doUpdate
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			doUpdate : function (){
				var form = $('#updateForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.save};
				me.collectParams(params);
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			collectParams : function(params){
				var tabs = me.tabs.tabs('tabs');
				$.each(tabs, function(j, t){
					var tab = $(t);
					if(!tab.attr('loaded')){
						return true;
					}
					var permType = tab.attr('permType'),
						tree = tab.find('#menuPermissionTree'+permType),
						pdiv = me.tabs.find('div[permType='+permType+']'),
						prefix = 'permTypes['+j+'].',
						nodes = pdiv.find('span.tree-checkbox1[tree-node-id],span.tree-checkbox2[tree-node-id]');
					params[prefix+'permType'] = permType;
					$.each(nodes, function(i, n){
						var me = $(n), 
							code = me.attr('tree-node-id'),
							node = tree.treegrid('find', code);
						params[prefix+'permissions[' + i + '].permId'] = node.permId;
					});
				});
				return params;
			},
			
			doDelete : function (rows){
				$b.Msg.confirm('您确定要删除选择的'+rows.length+'个角色(分配)吗？', function(){
					var roleAllotIds = [];
					$.each(rows, function(i, n){
						roleAllotIds.push(n.roleAllotId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'roleAllotId' : roleAllotIds}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert('操作成功，成功删除 ' + rs + '个角色(分配)');
				//grid.datagrid('reload');
				me.doQuery();
			},
			
			loadTab  : function (data){
				var tabs = $('#permissionTabpanel');
				me.tabs = tabs;
				tabs.tabs({    
				    onSelect:function(title, index){    
				    	var container = tabs.tabs('getSelected'),   
				    		permType = container.attr('permType');
				    	me.tab = tabs.find('div[permType='+permType+']');
				    	me.tree = container.find('#menuPermissionTree'+permType);
				    	if(!container.attr('loaded')){
				    		container.attr('loaded', true);
				    		me.loadTree(container, data, permType);
				    	}
				    }    
				}); 
			},
			
			loadTree : function (container, data, permType){
				var tree = me.tree,
					params = {permType : permType},
					url;
				if(data){
					params.roleAllotId = data.roleAllotId;
					url = apis.forEdit;
				}else{
					url = apis.forAdd;
				}
				tree.treegrid({
		            url : url,
					animate: true,
					idField: 'code',
					treeField:'text',
					fitColumns:true,
					queryParams: params,
					columns:[[
					  {title:'权限ID',field:'permId',hidden:true},
					  {title:'菜单ID',field:'code',hidden:true},
					  {title:'菜单 <span class="treeCheckAll tree-checkbox tree-checkbox0"> </span>', field:'text', width:200,
						  formatter: function(v,row){
							  if (!row.leaf) {
								  v = v + '<font color="red">(' + row.childrenNum + ')</font>';
							  }
							  return '<span tree-node-id="'+row.code+'" depth="'+row.depth+'" class="tree-checkbox '+row.cls +'"> </span> '+v;
						  }  
					  }    
			      	]],
			      	onLoadSuccess: function(){
						me.setAllChecked();
						me.tab.find('span[tree-node-id]').on('click', function(){
							me.onCheckbox(this);
						});
						me.tab.find('span.treeCheckAll').on('click', function(){
							var checked = $(this).hasClass('tree-checkbox1');
							me.changeNodeCls({cls: (checked?'tree-checkbox0':'tree-checkbox1')});
							$.each(me.tab.find('span[tree-node-id][depth=1]'), function(){
								me.onCheckbox(this,checked);
							});
						});
					},
					/**
					 * 初始化数据处理
					 * 1.所有非叶子节点设置为关闭状态
					 * 2.计算选择的子节点和未选择的子节点数量
					 */
					loadFilter: function(data){
						var r = function(i,n){
								var children = n.children;
								if(!children || children.length === 0){
									$.extend(n, {
										childrenNum : 1,
										thirdNum : 0, 
										checkedNum : (n.checkStatus === 1 ? 1 : 0), 
										cls : (n.checkStatus === 1 ? 'tree-checkbox1' : 'tree-checkbox0')
									});
								}else{
									n.state = 'closed';//非叶子节点置为关闭状态
									$.each(children, r);
									$.extend(n, me.resolverCheckedStatus(children));
								}
							};
						$.each(data, r);
						return data;
					}
				});
			},
			
			/**
			 * 设置全选框
			 */
			setAllChecked : function() {
			    var me = this, tree= me.tree, roots = me.tab.find('span[tree-node-id][depth=1]'), children = [];
			    $.each(roots, function(i, cc) {
			    	children.push(tree.treegrid('find', $(cc).attr('tree-node-id')));
			    });
			    me.changeNodeCls(me.resolverCheckedStatus(children));
			},
			/**
			 * 复选框事件
			 */
			onCheckbox : function(obj, checked){
				var me = this,
					tree = me.tree,
					nodeObj = $(obj),
					treeNodeId = nodeObj.attr('tree-node-id'),
					node = tree.treegrid('find', treeNodeId),
					setSelfAndChildren = function(id, checked){//设置自身和子节点的选择状态
						var toggle = function(id,checked){
								var nd = tree.treegrid('find', id);
								$.extend(nd,{
									thirdNum: 0, 
									checkedNum : (checked ? 0 : nd.childrenNum),
									cls : (checked ? 'tree-checkbox0' : 'tree-checkbox1')
								});
								me.changeNodeCls(nd);
							};
						toggle(id, checked);
						$.each(tree.treegrid('getChildren', id), function(i, child){
							toggle(child.code, checked);
						});
					},
					setParent = function(id){//设置父节点的状态
						var nd = tree.treegrid('find', id);
						if(nd){
							$.extend(nd, me.resolverCheckedStatus(nd.children));
							me.changeNodeCls(nd);
							setParent(nd.parentCode);
						}
					};
				if(checked === undefined){
					checked = !nodeObj.hasClass('tree-checkbox0');//当前是否已选择
				}
				setSelfAndChildren(treeNodeId, checked);
				setParent(node.parentCode);
				me.setAllChecked();
			},
			/**
			 * 改变节点样式
			 */
			changeNodeCls : function(node) {
			    var me = this, id = node.code, $n;
			    if (!id) {
			    	$n = me.tab.find('span.treeCheckAll');
			    } else {
			    	$n = me.tab.find('[tree-node-id=' + id + ']');
			    }
			    $n.removeClass('tree-checkbox0 tree-checkbox1 tree-checkbox2').addClass(node.cls);
			},
			/**
			 * 解析子节点个数、已勾选节点个数、部分勾选节点个数、样式等
			 */
			resolverCheckedStatus : function(children) {
			    var tcn = children.length, cn = 0, tn = 0, cls = 'tree-checkbox2';
			    $.each(children, function(i, c) {
					if (c.childrenNum === c.checkedNum) {
					    cn++;
					} else if (c.checkedNum > 0 || c.thirdNum > 0) {
					    tn++;
					}
			    });
			    if (cn === tcn) {
			    	cls = 'tree-checkbox1';
			    } else if (cn === 0 && tn === 0) {
			    	cls = 'tree-checkbox0';
			    }
			    return {
					childrenNum : tcn, // 子节点个数
					checkedNum : cn,   // 已勾选子节点个数
					thirdNum : tn,     // 部分勾选（第三种状态）子节点个数
					cls : cls          // 样式
			    };
			}
		};
	});
});