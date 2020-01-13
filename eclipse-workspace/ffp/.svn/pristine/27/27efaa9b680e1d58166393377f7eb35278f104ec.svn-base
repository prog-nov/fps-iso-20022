/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-30<br>
 */
require(['beneform4j-treepage','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			popupMenu = $('#popupMenu'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				gotoAdd  : 'gotoAdd',
				add  : 'insert',
				gotoEdit  : 'gotoEdit',
				save   : 'update',
				remove : 'delete',
				allotForAdd : 'listRoleAllotForAdd',
				forAdd : 'listRolePermissionForAdd',
				allotForEdit: 'listRoleAllotForEdit',
				forEdit: 'listRolePermissionForEdit'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'roleId',
					pagination : true,
					fitColumns : true,
					rowToolButtons:[ {
						text : locale.title.edit, iconCls : 'bf4j-icon-row-edit', handler : function(index,data){
							me.gotoUpdate(data);
						}
					}, {
						text : locale.title.remove, iconCls : 'bf4j-icon-row-delete', handler : function(index,data){
							me.doDelete([data]);
						}
					}],
					columns : [ [ 
						{field : 'roleId', title : locale.system.sysmanager.role.RoleCode, checkbox : true},   
						{field : 'roleName', title : locale.system.sysmanager.role.roleName, align:'left', width: 200},
						{field : 'des', title : locale.system.sysmanager.role.describe, align:'left', width: 400}
			        ] ],
					onRowContextMenu : function(e, rowIndex, rowData) {
						e.preventDefault();
						$(this).datagrid('unselectAll')
							   .datagrid('uncheckAll')
							   .datagrid('selectRow', rowIndex);
						popupMenu.dataRow = rowData;
						popupMenu.menu('show', {left : e.pageX, top : e.pageY});
					}
				});
				
				$b.Msg.closeProgress();
				
				//绑定事件
				$("a.doQuery").on("click", me.doQuery);
				$("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
						.end().filter(".gotoDownload").on("click", function(){
							grid.datagrid('download', {downloadId : 'file-roleList'});
						})
					   .end().filter(".gotoUpdate").on("click", function(){
						   me.selectOne(grid, me.gotoUpdate);
					    })
					   .end().filter(".doDelete").on("click", function(){
						   me.selectRows(grid, me.doDelete);
					    });
				
				popupMenu.find(".gotoUpdate").on("click", function(){
					me.gotoUpdate(popupMenu.dataRow);
				}) .end().find(".doDelete").on("click", function(){
					me.doDelete([popupMenu.dataRow]);
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
				    title: locale.system.sysmanager.role.AddRoles,    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoAdd,
				    onLoad : function(){
				    	me.loadTab();
				    	//me.loadTree(apis.forAdd);
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				me.endEdit();
				var form = $('#addForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.add};
				me.collectParams(params);
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.system.sysmanager.role.modifyRole,    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoEdit,
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
				    	me.loadTab(data);
				    },
				    buttons : [ {
						text : locale.button.save, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			doUpdate : function (){
				me.endEdit();
				var form = $('#updateForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.save};
				me.collectParams(params);
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			collectParams : function(params){
				var tabs = me.tabs.tabs('tabs');
				$.each(tabs, function(j, t){
					var tab = $(t);
					if(tab.is('.roleAllot')||!tab.attr('loaded')){
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
						params[prefix+'permissions[' + i + '].permAttr'] = node.permAttr;
					});
				});
				if(me.subGrid){
					params.roleAllotLoaded = true;
					var checkeds = me.subGrid.datagrid('getChecked');
					$.each(checkeds, function(i, n){
						params['roleAllots[' + i + '].roleAllotId'] = n.roleAllotId;
					});
				}
				return params;
			},
			
			doDelete : function (rows){
				$b.Msg.confirm(locale.system.sysmanager.role.remove1+rows.length+locale.system.sysmanager.role.remove2, function(){
					var roleIds = [];
					$.each(rows, function(i, n){
						roleIds.push(n.roleId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'roleId' : roleIds}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert(locale.system.sysmanager.role.removeS + rs + locale.system.sysmanager.role.removeS2);
				//grid.datagrid('reload');
				me.doQuery();
			},
			
			loadTab  : function (data){
				var tabs = $('#permissionTabpanel');
				me.tabs = tabs;
				me.subGrid = undefined;
				tabs.tabs({    
				    onSelect:function(title, index){    
				    	var container = tabs.tabs('getSelected'),   
				    		permType = container.attr('permType');
				    	me.tab = tabs.find('div[permType='+permType+']');
				    	me.tree = container.find('#menuPermissionTree'+permType);
				    	if(!container.attr('loaded')){
				    		container.attr('loaded', true);
				    		if($(container).is('.roleAllot')){
					    		me.loadRoleAllot(container, data);
					    	}else{
					    		me.loadTree(container, data, permType);
					    	}
				    	}
				    }    
				}); 
			},
			
			loadRoleAllot : function(container, data){
				var grid = $(container).find('#roleAllotList'),
					params = {},
					url;
				if(data){
					params.roleId = data.roleId;
					url = apis.allotForEdit;
				}else{
					url = apis.allotForAdd;
				}
				me.subGrid = grid;
				grid.datagrid({
					url : url,
					queryParams: params,
					columns:[[
						{field:'checked', checkbox: true},
						{field:'roleAllotName', title: locale.system.sysmanager.role.RoleAssignment, fit: true, width: 250}
					]],
					onLoadSuccess: function(data, grid){
						var $g = $(grid||this);
						if(data && data.rows){
							$.each(data.rows, function(i,c){
								if(c.checked){
									$g.datagrid('checkRow', i);
								}
							});
							$g.datagrid('clearSelections');
						}
					}
				});
			},
			
			loadTree : function (container, data, permType){
				var tree = me.tree,
					params = {permType : permType},
					url;
				if(data){
					params.roleId = data.roleId;
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
					  {title:locale.system.sysmanager.role.authorizationID,field:'permId',hidden:true},
					  {title:locale.system.sysmanager.role.menuID,field:'code',hidden:true},
					  {title:locale.system.sysmanager.role.menu+'<span class="treeCheckAll tree-checkbox tree-checkbox0"> </span>', field:'text', width:200,
						  formatter: function(v,row){
							  if (!row.leaf) {
								  v = v + '<font color="red">(' + row.childrenNum + ')</font>';
							  }
							  return '<span tree-node-id="'+row.code+'" depth="'+row.depth+'" class="tree-checkbox '+row.cls +'"> </span> '+v;
						  }  
					  },
			          {title:locale.system.sysmanager.role.menutype,field:'permAttr', width: 100,
						  formatter: function(v, row){
							  if(row.leaf){
								  return v === '1' ? locale.system.sysmanager.role.operability : (v === '2' ? locale.system.sysmanager.role.uoperability : '');
							  }else{
								  row.permAttr = '';
								  return '';
							  }
						  },
						  editor : {
				        	  type:'combobox',
				        	  options:{
				        		  valueField:'id',
				        		  textField:'text',
				        		  data : [{id : '' , text : '--'+locale.system.sysmanager.role.choose+'--'}, {id : '1', text : locale.system.sysmanager.role.operability},{id : '2', text : locale.system.sysmanager.role.uoperability}]
				        	  }
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
					},
					onClickCell : function(field, record){
			      		me.endEdit();
			      		var id = record.code, 
			      			tree = me.tree,
			      			c = tree.treegrid('getChildren',id);
			      		if(field === 'permAttr' && (!c || c.length === 0)){
			      			tree.treegrid('beginEdit', id);
			      			tree.editingId = id;	
			      		}else{
			      			return true;
			      		}
					},
					onAfterEdit: function(row,changes){
						$.extend(row, changes);
						me.tab.find('span[tree-node-id]').on('click', function(){
							me.onCheckbox(this);
						});
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
			},
			/**
			 * 结束编辑状态
			 */
			endEdit : function(){
				var tree = me.tree;
				if(tree && tree.editingId){
					tree.treegrid('endEdit', tree.editingId);
	      		}
			}
		};
	});
});