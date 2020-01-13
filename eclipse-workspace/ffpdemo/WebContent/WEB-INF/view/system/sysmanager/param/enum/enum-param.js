/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 枚举参数维护<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-8<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){
		var me = this,
			form = $('#searchForm'), //列表项查询表单
			grid = $('#dataList'),   //列表项表格
			listForm,  //枚举型参数明细查询表单
			listGrid,  //枚举型参数明细查
			treeForm,  //树形参数编辑表单
			tree,	   //树形参数展示树	
			popupMenu, //树形参数维护右键菜单
			dialog = $('#dialog'), 
			editIndex, //枚举型参数编辑索引
			updateParamCode, //当前编辑的参数代码
			apis = {
				list : 'listEnumDef',
				listData : 'listEnumData',
				listTree : 'listEnumTree',
				findTreeNode : 'findTreeNode',
				insertTreeNode : 'addTreeNode',
				updateTreeNode : 'editTreeNode',
				moveTreeNode : 'moveTreeNode',
				deleteTreeNode : 'deleteTreeNode',
				add : 'addEnumDef',
				save : 'editEnumDef',
				saveListData : 'editListData',
				remove : 'deleteEnumDef'
			},
			editableRender = function(v){
				return v === '1' ? '是' : '否';
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'paramCode',
					pagination : true,
					rowTool: false,
					queryParams: $.serializeObject(form),
					columns : [ [ 
					    {checkbox : true},
			            {field : 'paramCode', title : '参数代码', align:'center'},
			            {field : 'paramName', title : '参数名称', align:'left'}, 
			            {field : 'paramGroup', title : '所属组别', align:'left', width : 100, sortable : true},
						{field : 'paramAttr', title : '参数特性', align:'left'},
						{field : 'editable', title : '可否编辑', align:'left', formatter: editableRender},
						{field : 'seqno', title : '序号', align:'left'},
						{field : 'des', title : '描述', align:'left'}
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
				$("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
			    .end().filter(".gotoUpdate").on("click", function(){
				   me.selectOne(grid, me.gotoUpdate);
			    }).end().filter(".gotoUpdateData").on("click", function(){//重置密码
			    	me.selectOne(grid, me.gotoUpdateData);
			    }).end().filter(".doDelete").on("click", function(){
				   me.selectRows(grid, me.doDelete);
			    });
			    $b.Msg.closeProgress();
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
				grid.datagrid('load', $.serializeObject(form));
			},
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: '新增参数定义',    
				    height:400,
				    width:380,
				    href : 'add.page',
				    onLoad : function(){
				    	var f = $('#addForm');
				    	me.setComboFields(f);
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
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: '编辑参数定义',    
				    height:400,
				    width:380,
				    href : 'edit.page',
				    onLoad : function(){
				    	var f = $('#updateForm');
				    	f.form('load', data);
				    	me.setComboFields(f);
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
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			/**
			 * 进入明细编辑
			 * @param data
			 * @returns
			 */
			gotoUpdateData : function (data){
				if(data && data.paramAttr === 'LIST'){
					dialog.dialog({    
					    title: '编辑列表型参数明细',    
					    height:500,
					    width:640,
					    maximized:true,
					    href : 'editlistdetail.page',
					    onLoad : function(){
					    	listForm = $('#editListForm');
					    	listGrid = $('#dataListDetail');
					    	$('#editListForm').form('load', data);
					    	$("a.bf4j-btn-2").filter(".addRecord").on("click", me.append)
						    .end().filter(".delRecord").on("click", me.removeit);					    	
					    	listGrid.datagrid({
								url : apis.listData,
								fitColumns : true,
								rowTool : false,								
								queryParams : $.serializeObject(listForm),
								onClickCell : me.onClickCell,
								onDblClickCell : me.onDblClickCell,
								columns : [ [ 
						            {field : 'dataCode', title : '数据代码', align:'left', editor:'text'}, 
						            {field : 'dataText', title : '数据文本', align:'left', editor:'text'},
									{field : 'dataParam', title : '数据参数', align:'left', editor:'text'},
									{field : 'seqno', title : '序号', align:'left', editor:'text'},
									{field : 'des', title : '描述', align:'left', editor:'text'}
						        ] ]
							});
					    },
					    buttons : [ {
							text : '保存', size : 'large', handler : me.doUpdateListDetail
						}, {
							text : '取消', size : 'large', handler : me.closeDialog
						} ]
					});
				} else if (data && data.paramAttr === 'TREE'){
					updateParamCode = data.paramCode;
					dialog.dialog({    
					    title: '编辑树型参数明细',    
					    height:500,
					    width:640,
					    maximized:true,
					    href : 'edittreedetail.page',
					    onLoad : function(){
					    	treeForm = $('#editTreeForm');
					    	tree = $('#dataTreeDetail');
					    	popupMenu = $('#popupMenu');
					    	
						    var treeIdTextBox = treeForm.find('input[name=dataCode]');
					    	    
							// 构建异步加载树
							tree.tree({
								url : apis.listTree,
								animate: true,
								lines : true,
								dnd : true,
								//格式化，添加包含的子节点树
								formatter : function(node){
									if(node.count>=1){
										return node.text + '<font color="red">('+node.count+')</font>';
									}
									return node.text;
								},
								//查询参数设置
								onBeforeLoad : function(node, param) {
									param.paramCode = updateParamCode;
									if(node && node.id){
										param.parentDataCode = node.id;
									} else {
										param.parentDataCode = 'root';
									}
								},								
								//初始化状态
								loadFilter: function(data){
									if($.isArray(data)){
										$.each(data, function(i, node){
											node.attributes = {
												paramCode : node.paramCode,
												dataCode : node.dataCode,
												parentDataCode : node.parentDataCode,
												dataText : node.dataText,
												dataIcon : node.dataIcon,
												dataParam : node.dataParam,
												seqno : node.seqno,
												des : node.des	
											};
											node.id = node.dataCode;
											node.text = node.dataText;
											node.state = node.count >= 1? 'closed':'open';
										});
									}
									return data;
								},
								//单击事件
								onClick : function(node){
									var param = {
										needBlock : false,
										paramCode : updateParamCode, 
										dataCode : node.id	
									};
									$b.Submit.ajaxSubmit(apis.findTreeNode, param, function(data){
										treeForm.form('load',data);
										treeForm.find('input[name=dataCode]').attr('readonly','readonly').addClass('bf4j-readonly');
										me.optFlag = 'update';
									});
								},
								//右键事件
								onContextMenu: function(e,node){
									e.preventDefault();
									popupMenu.node = node;
									popupMenu.target = node.target;//保存当前对象
									popupMenu.menu('show',{left: e.pageX,top: e.pageY});
								},
								//进入某个目标节点
								onDragEnter: function(target){
									if(!tree.tree('isLeaf', target)){
										var children = tree.tree('getChildren', target);
										if(children && children.length >= 1){//如果未展开父菜单并且父节点已经加载过子节点，则先展开	
											tree.tree('expand', target);
										}
									}
								},
								//拖动
								onBeforeDrop: function(target, source, point){
									me.moveTreeNode({
										url : apis.moveTreeNode,
										params: source.attributes,
										rootId : 'root',
										tree  : tree,
										target : target,
										source : source,
										point  : point
									});
								}
							});	
							me._bindpopupMenuEvent();
					    },
					    buttons : [ {
							text : '保存', size : 'large', handler : me.doUpdateTreeDetail
						}, {
							text : '取消', size : 'large', handler : me.closeDialog
						} ]
					});
				}	
			},
			endEditing : function(){
				if (editIndex == undefined){return true}
				if (listGrid.datagrid('validateRow', editIndex)){
					listGrid.datagrid('endEdit', editIndex);
					editIndex = undefined;
					return true;
				} else {
					return false;
				}
			},
			
			onClickCell : function(index, field){
				if (editIndex != index){
					me.endEditing();
				}
			},
			onDblClickCell : function(index, field){
				if (editIndex != index){
					if (me.endEditing()){
						listGrid.datagrid('selectRow', index)
								.datagrid('beginEdit', index);
						var ed = listGrid.datagrid('getEditor', {index:index,field:field});
						($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
						editIndex = index;
					} else {
						listGrid.datagrid('selectRow', editIndex);
					}
				}
			},
			append : function (){
				if (me.endEditing()){
					listGrid.datagrid('appendRow',{});
					editIndex = listGrid.datagrid('getRows').length-1;
					listGrid.datagrid('selectRow', editIndex)
							.datagrid('beginEdit', editIndex);
				}
			},
			removeit : function(){
				if (editIndex == undefined){return}
				listGrid.datagrid('cancelEdit', editIndex)
						.datagrid('deleteRow', editIndex);
				editIndex = undefined;
			},
			/**
			 * 执行列表型明细项更新操作
			 */
			doUpdateListDetail : function (){
				if (me.endEditing()){
					var params = {},
						paramCode = [],
						dataCode = [],
						dataText = [],
						dataParam = [],
						seqno = [],
						des = [],
					    form = $.serializeObject(listForm),
						rows = listGrid.datagrid('getRows'),
						changes = listGrid.datagrid('getChanges'),
						l = rows.length;
					if(changes.length>0){
						$.each(rows, function(i, n){
							paramCode.push(form.paramCode);
							dataCode.push(n.dataCode);
							dataText.push(n.dataText);
							dataParam.push(n.dataParam === '' ? ' ' : n.dataParam);
							seqno.push(n.seqno === '' ? '0' : n.seqno );
							des.push(n.des === '' ? ' ' : n.des);
						});
						params = {	paramCode : paramCode,
									dataCode : dataCode,
									dataText : dataText,
									dataParam : dataParam,
									seqno : seqno,
									des : des
						};
						$b.Submit.ajaxSubmit(apis.saveListData, params, function(rs){
							$b.Msg.alert('保存成功！', function(){
								listGrid.datagrid('load');
							});
						});
					}else{
						$b.Msg.warning('没有要保存的内容！');
					}
				}
			},			
			doUpdateTreeDetail : function(){
				if(treeForm.form('validate')){
					if(me.optFlag === 'insert'){
						me.doInsertTreeNode();
					}else if(me.optFlag === 'update'){
						me.doUpdateTreeNode();
					}
				}
			},
			
			doDelete : function (rows){
				$b.Msg.confirm('您确定要删除选择的'+rows.length+'个参数项吗？', function(){
					var paramCodes = [];
					$.each(rows, function(i, n){
						paramCodes.push(n.paramCode);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'paramCode' : paramCodes}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert('操作成功，成功删除 ' + rs + '个参数项');
				me.doQuery();
			},
			/**
			 * 执行查询
			 */
			doQuery : function (){
				grid.datagrid('load', $.serializeObject(form));
			},
			/**
			 * 执行新增树节点
			 */
			doInsertTreeNode : function (){
				$b.Submit.ajaxSubmitForm(treeForm, {url : apis.insertTreeNode}, function(rs){
					var values = $.serializeObject(treeForm),
						node = tree.tree('find', values.parentDataCode);
					node.count++;
					tree.tree('append', {//如果父节点原来是叶子节点，需先在页面添加，然后执行reload方法时方可重新加载
						parent : node.target,
						data:[{id: '', text: ''}]
					});
					tree.tree('reload', node.target);
					$b.Msg.alert('已经添加成功');
				});
			},
			/**
			 * 执行更新树节点
			 */
			doUpdateTreeNode : function (){
				$b.Submit.ajaxSubmitForm(treeForm, {url : apis.updateTreeNode}, function(){
					var values = $.serializeObject(treeForm),
						node = tree.tree('find', values.dataCode),
						target = node.target;
					node.text = values.dataText;
					tree.tree('update',{target : target, text: values.dataText});
					$.extend(node.attributes, values);
					$b.Msg.alert('修改成功!');
				});
			},
			/**
			 * 移动树形结点
			 * @param options
			 * @returns
			 */
			moveTreeNode : function(options){
				var url = options.url,
					rootId = options.rootId,
					tree = $(options.tree),
					point = options.point,
					snode = options.source,//源节点
					target = options.target,
					tnode = tree.tree('getNode', target),//目标节点
					pnode,//父节点
	    			params,
	    			hasLoaded,
	    			rs = false;
				if(point === undefined){//父节点
					pnode = tnode;
					hasLoaded = pnode.state === 'open';
					if(hasLoaded){
						tree.tree('expand', target);
					}
				}else{
					hasLoaded = true;
					pnode = tree.tree('getParent', target);
				}
				params = $b.union({
					parentDataCode: (pnode ? pnode.id : rootId), 
					needBlock : false,
					async:false
				}, options.params);
	    		$b.Submit.ajaxSubmit(url, params, function(){
	    			rs = true;
	    			var oldPnode = tree.tree('getParent', snode.target);//原父节点
	    			if(oldPnode && (!pnode || oldPnode.id !== pnode.id)){//有原父节点，并且原父节点与新父节点不同
	    				oldPnode.count--;
	    				tree.tree('update',{target : oldPnode.target});
	    			}
	    			if(pnode && (!oldPnode || oldPnode.id !== pnode.id)){//有新父节点，并且原父节点与新父节点不同
	    				pnode.count++;
	    				tree.tree('update',{target : pnode.target});
	    				if(!hasLoaded){
	    					tree.tree('reload', pnode.target);
	    					tree.tree('remove', snode.target);
	    				}
	    			}
	    			tree.tree('reload', tnode.target);
	    			$b.Msg.alert("节点 <font color='red'>"+snode.text+"</font> 拖动成功");
	    			if(typeof options.callback === 'function'){
	    				options.callback();
	    			}
	    		}, function(){rs = false;});
	    		return rs;
			},
			/**
			 * 绑定右键菜单事件
			 */
			_bindpopupMenuEvent : function(){
				
				popupMenu.find(".menuAdd").on("click", function(){
					var node = tree.tree('getNode',popupMenu.target);
					treeForm.form('clear').form('load',{paramCode: node.attributes.paramCode, parentDataCode: node.attributes.dataCode});
					treeForm.find('input[name=dataCode]').removeAttr('readonly',false).removeClass('bf4j-readonly');
					me.optFlag = 'insert';
				}) .end().find(".menuEdit").on("click", function(){
					var node = tree.tree('getNode',popupMenu.target),
						param = {paramCode: node.attributes.paramCode, dataCode: node.id, mime : 'json', needBlock : false};
					$b.Submit.ajaxSubmit(apis.findTreeNode, param, function(data){
						treeForm.form('load',data);
						me.optFlag = 'update';
					});
				}).end().find(".menuDelete").on("click", function(){
		   			var node = tree.tree('getNode',popupMenu.target),
		   				count = node.attributes.count;
		   			$b.Msg.confirm("您确定要删除所选的<font color='red'> "+node.text+(count > 0 ? "及其所有子节点" : "")+" </font>吗？", function(){
		   				$b.Submit.ajaxSubmit(apis.deleteTreeNode, {dataCode : node.id, paramCode: node.attributes.paramCode}, function(){
		   					var parent = tree.tree('getParent', popupMenu.target);
		   					parent.count--;
		   					tree.tree('update',{target : parent.target});//更新父节点
		   					tree.tree('remove', popupMenu.target);//从树中移除
		   					$b.Msg.alert('成功删除<font color="red">' + node.text + '</font>');
		   				});
		   			});
				});
			}
		};
	});
});