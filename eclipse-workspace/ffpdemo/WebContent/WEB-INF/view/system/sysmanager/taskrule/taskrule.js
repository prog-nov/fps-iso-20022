/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 任务规则维护<br>
 * Author : lsc <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-8-22<br>
 */
require(['beneform4j-treepage','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			popupMenu = $('#popupMenu'),
			dialog = $('#dialog'),
			userDialog = $('#userDialog'),
			popupMenuEdit = $('#popupMenuEdit'),
			apis = {
				list : 'list',
				gotoAdd  : 'gotoAdd',
				add  : 'insert',
				gotoEdit  : 'gotoEdit',
				save   : 'update',
				remove : 'delete',
				roleList : 'listRole',
				orgListTree : 'listOrgTree',
				listTask : 'listTaskNode',
				listTaskUser : 'listTaskUser',
				gotoUserAdd : 'gotoUserAdd'
			};
		
		return {
			init :  function(){
				me.setComboFields($('#searchForm'));
				me.setComboDatas('BOOLEAN');
								
				grid.datagrid({
					url : apis.list,
					idField : 'ruleId',
					pagination : true,
					rowTool: false,
					fitColumns : true,
					columns : [ [ 
					    {field : 'ruleId', checkbox : true}, 
					    {field : 'taskId', hidden:true, title : '任务节点代码'},   
						{field : 'taskName', title : '任务节点名称', align:'left', width: 200},
						{field : 'roleFlag', title : '限定角色', align:'center', width: 200,formatter: me.comboFormatter('BOOLEAN')},
						{field : 'orgFlag', title : '限定机构', align:'center', width: 200,formatter: me.comboFormatter('BOOLEAN')},
						{field : 'operFlag', title : '限定人', align:'center', width: 200,formatter: me.comboFormatter('BOOLEAN')},
						{field : 'detailFlag', title : '进入明细', align:'center', width: 200,formatter: me.comboFormatter('BOOLEAN')}
			        ] ]
				});
				
				$b.Msg.closeProgress();
				
				//绑定事件
				$("a.doQuery").on("click", me.doQuery);
				$("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
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
			 * 关闭对话框
			 */
			closeUserDialog : function(){
				userDialog.dialog('close');
			},
			
			/**
			 * 根据选择的值展示对应的明细选项
			 */
			showDetailInfo : function (checkObj){
				var showValue = checkObj.val();
				var showDivId = checkObj.attr('id');
				if(showValue == 1){
					$('#'+showDivId+'Show').removeAttr('displayed');
				}
				else{
					$('#'+showDivId+'Show').attr('displayed','none');
				}
			},
			
			/**
			 * 执行查询
			 */
			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			
			/**
			 * 用户队列
			 */
			loadUserSubGrid : function(data){
				var opergrid = me.opergrid,
					params = {ruleId:''},
					url = apis.listTaskUser;
				if(data){
					params.ruleId = data.ruleId;
				}
				opergrid.datagrid({
					url : url,
					idField : 'userId',
					queryParams: params,
					fitColumns : true,
					checkbox : false,
					columns:[[
						{field:'userId', checkbox : true},
						{field:'userName', title: '用户名称', fit: true, width: 250}
					]]

				});
			},
			
			
			/**
			 * 角色列表
			 */
			loadRoleSubGrid : function (data){
				var roleListGrid = $('#roleFlagShow'),
					params = {dealType:'',ruleId:''},
					url = apis.roleList;
				if(data){
					params.dealType = 'edit';
					params.ruleId = data.ruleId;
				}
				else
				{
					params.dealType = 'add';
				}
				me.roleListGrid = roleListGrid;
				roleListGrid.datagrid({
					url : url,
					queryParams: params,
					fitColumns:true,
					columns:[[
						{field:'roleId', checkbox: true},
						{field:'roleName', title: '角色名称', fit: true, width: 250}
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
			
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: locale.system.taskrule.title.insert,    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoAdd,
				    onLoad : function(){
				    	me.initTabSelect();
				    	me.initTaskNode();
				    	me.loadTab();
				    	// 机构树右键菜单
						popupMenuEdit.find(".gotoAdd").on("click", function(){
							me.doOrgSel(popupMenuEdit.node);
						}).end().find(".gotoDel").on("click", function(){
							me.doOrgUnSel(popupMenuEdit.node);
						});
				    },
				    buttons : [ {
						text : locale.button.save, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			/**
			 * 进入用户新增查询页面
			 */
			gotoUserAdd : function (){
				userDialog.dialog({    
				    title: locale.system.taskrule.title.gotoadduser,    
				    height:400,
				    width:800,
				    maximized: false,
				    href : apis.gotoUserAdd,
				    onLoad : function(){
				    	me.usergrid = $('#userDataList');
				    },
				    buttons : [ {
						text : locale.button.insert, size : 'large', handler : me.doAddUser
					}, {
						text : locale.button.close, size : 'large', handler : me.closeUserDialog
					} ]
				}); 
			},
			
			/**
			 * 用户新增确认,添加到队列
			 */
			doAddUser : function(){
				var opergrid = me.opergrid,
					usergrid = me.usergrid;
				var checkeds = usergrid.datagrid('getChecked');
				var exists = opergrid.datagrid('getRows');
				var existsFlag = false;
				var alertStr = '';
				if(checkeds.length > 0){
					$.each(checkeds, function(i, n){
						existsFlag = false;
						$.each(exists, function(j, e){
							if(n.userId == e.userId){
								existsFlag = true;
								alertStr = alertStr + locale.system.taskrule.title.userId +
								n.userId+ locale.system.taskrule.title.userName +
								n.userName+'\r\n';
							}
						});
						if(!existsFlag){
							opergrid.datagrid('appendRow',{userId:n.userId,userName:n.userName});
						}
					});
					$('.userDelete').on('click',function(row){
						me.usergrid.datagrid('deleteRow',row.param);
					});
					if(alertStr != ''){
						$b.Msg.alert($b.Base.i18n('system.taskrule.content.userexists',alertStr));
					}
					else{
						$b.Msg.alert(locale.system.taskrule.content.doOk);
					}
				}
				else{
					$b.Msg.alert(locale.system.taskrule.content.notseletuser);
				}
			},
			
			/**
			 * 删除限定用户
			 */
			doUserDelete : function(rows){
				$b.Msg.confirm($b.Base.i18n('operate.remove',rows.length), function(){
					var grid = me.opergrid;
					var index = 0;
					var dataList = [];
					$.each(rows, function(i, n){
						dataList.push(n);
					});
					$.each(dataList, function(i, n){
						index = grid.datagrid('getRowIndex',n);
						grid.datagrid('deleteRow',index);
					});
				});
			},
			
			/**
			 * 删除所有用户
			 */
			doDeleteAll : function(){
				$b.Msg.confirm(locale.system.taskrule.content.deleteAllUser, function(){
					var opergrid = me.opergrid;
					var checkeds = opergrid.datagrid('getRows');
					$.each(checkeds, function(i, n){
						opergrid.datagrid('deleteRow',0);
					});
				});
			},
			
			
			/**
			 * 初始化任务节点
			 */
			initTaskNode : function (){
				$('#taskId').combobox({    
				    url: apis.listTask,    
				    valueField:'taskId', 
				    textField:'text',
				    onSelect: function(rec){
				    	$('#taskName').val(rec.text);
						$b.Submit.ajaxSubmit(apis.listTask, 
							{taskId:rec.taskId}, 
							function(rs){
								$.each(rs, function(i, n){
									$("#detailMenuId").val(n.detailMenuId);
									$("#detailMenuName").attr('value',n.detailMenuName);
								});
							}
						);
					}
				}); 
			},
			
			doAdd : function (){
				$b.Msg.confirm(locale.system.taskrule.content.doadd, function(){
					me.endEdit();
					var form = $('#addForm');
					var checkVal = $('#taskId').combobox('getValue');
					if( checkVal ==''){
						$b.Msg.alert(locale.system.taskrule.validate.tasknodenotnull);
						return false;
					}
					
					var params = {url : apis.add};
					me.collectParams(params);
					$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
				});
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.system.taskrule.title.modify,    
				    height:400,
				    width:800,
				    maximized: true,
				    href : apis.gotoEdit,
				    onLoad : function(){
				    	me.initTabSelect(data);
				    	$('#updateForm').form('load', data);
				    	$('#taskId').val(data.taskId);
						$('#taskName').val(data.taskName);
						$('#detailMenuId').val(data.detailMenuId);
						$('#detailMenuName').val(data.detailMenuName);
				    	me.loadTab(data);
				    	// 机构树右键菜单
						popupMenuEdit.find(".gotoAdd").on("click", function(){
							me.doOrgSel(popupMenuEdit.node);
						}).end().find(".gotoDel").on("click", function(){
							me.doOrgUnSel(popupMenuEdit.node);
						});
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			/**
			 * 初始化选择条件展示对应的值
			 */
			initTabSelect : function(data){
				 $('#permissionTabpanel').find('.control-show').combobox({
	    				valueField: 'value',
	    				textField: 'label',
	    				data: [{
	    					label: locale.system.taskrule.content.yes,
	    					value: '1'
	    				},{
	    					label: locale.system.taskrule.content.no,
	    					value: '0' ,
	    					selected:true
	    				}],
	    				onSelect: function(record){
	    					var showDivId = $(this).attr('id');
	    					if(record.value == 1){
	    						$('#'+showDivId+'Con').show();
	    						me.loadSubDetail(showDivId,data);
	    					}
	    					else{
	    						$('#'+showDivId+'Con').hide();
	    					}
	    				}
	    			});
			},
			
			doUpdate : function (){
				$b.Msg.confirm(locale.system.taskrule.content.doupdate, function(){
					me.endEdit();
					var form = $('#updateForm');
					var params = {url : apis.save};
					me.collectParams(params);
					$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
				});
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			collectParams : function(params){
				
				if(me.tree){
					var nodes = me.tab.find('span.tree-checkbox1[tree-node-id],span.tree-checkbox2[tree-node-id]');
					$.each(nodes, function(i, n){
						var mew = $(n), 
						code = mew.attr('tree-node-id'),
						node = me.tree.treegrid('find', code);
						params['orgsList[' + i + '].orgId'] = node.orgId;
					});
				}
				if(me.roleListGrid)
				{
					var checkeds = me.roleListGrid.datagrid('getChecked');
					$.each(checkeds, function(i, n){
						params['rolesList[' + i + '].roleId'] = n.roleId;
					});
				}
				if(me.opergrid)
				{
					var checkeds = me.opergrid.datagrid('getRows');
					$.each(checkeds, function(i, n){
						params['usersList[' + i + '].userId'] = n.userId;
					});
				}
				return params;
			},
			
			doDelete : function (rows){
				$b.Msg.confirm($b.Base.i18n('operate.remove',rows.length), function(){
					var ruleIds = [];
					$.each(rows, function(i, n){
						ruleIds.push(n.ruleId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'ruleIds' : ruleIds}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert($b.Base.i18n('operate.doOk'));
				grid.datagrid('reload');
				me.doQuery();
			},
			
			/**
			 * 根据选择条件展示对应的明细数据
			 */
			loadSubDetail : function (id,data){
				var showValue = $('#'+id).combobox('getValue');
				// 展示
				if(showValue == 1){
					$('#'+id+'Con').show();
					var container = $('#'+id+'Div');
					var showContainer = $('#'+id+'Show');
					if("orgFlag" == id){
						me.tab = container;
						me.tree = showContainer;
						if(!container.attr('loaded')){
							container.attr('loaded', true);
							me.loadTree(container, data);
						}
					}
					else if("roleFlag" == id){
						me.roletab = container;
						me.rolelist = showContainer;
						if(!container.attr('loaded')){
							container.attr('loaded', true);
							me.loadRoleSubGrid(data);
						}
					}
					else if("detailFlag" == id){
						me.nodetab =  container;
						me.nodetree = showContainer;
						if(!container.attr('loaded')){
							container.attr('loaded', true);
							
						}
					}
					else if("operFlag" == id){
						me.usertab =  container;
						me.opergrid = showContainer;
						if(!container.attr('loaded')){
							container.attr('loaded', true);
							
							$(".doDeleteAll").on("click", function(){
								me.doDeleteAll();
							});
							$(".doUserDelete").on("click", function(){
								me.selectRows(me.opergrid, me.doUserDelete);
							});
							$(".gotoUserAdd").on("click", function(){
								me.gotoUserAdd();
							})
							me.loadUserSubGrid(data);
						}
					}
				}
				else{
					$('#'+id+'Con').hide();
				}
				
			},
			
			loadTab  : function (data){
				var tabs = $('#permissionTabpanel');
				me.tabs = tabs;
				me.subGrid = undefined;
				me.tree = undefined;
				me.rolelist = undefined;
				me.nodetree = undefined;
				me.opergrid = undefined;
				tabs.tabs({
				    onSelect:function(title, index){    
				    	var container = tabs.tabs('getSelected');
				    	var showDivId = container.attr('id');
				    	showDivId = showDivId.replace('Div','');
				    	me.loadSubDetail(showDivId,data);
				    }    
				});
				var flagDivs = tabs.find('div[id$=Div]');
				$.each(flagDivs, function(i,n){
					var showDivId = n.id.replace('Div','');
			    	me.loadSubDetail(showDivId,data);
				});
			},
			
			/**
			 * 机构数展示
			 */
			loadTree : function (container, data){
				var tree = me.tree,
					params = {ruleId:''},
					url;
				url = apis.orgListTree;
				if(data){
					params.dealType = "edit";
					params.ruleId = data.ruleId;
				}else{
					params.dealType = "add";
				}
				tree.treegrid({
		            url : url,
					animate: true,
					idField: 'code',
					treeField:'text',
					fitColumns:true,
					queryParams: params,
					columns:[[
					  {title:'机构ID',field:'orgId',hidden:true},
					  {title:'机构ID',field:'code',hidden:true},
					  {title:'机构 <span class="treeCheckAll tree-checkbox tree-checkbox0"> </span>', field:'text', width:200,
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
					},
					onClickCell : function(field, record){
			      		me.endEdit();
			      		var id = record.code, 
			      			tree = me.tree,
			      			c = tree.treegrid('getChildren',id);
			      		if(field === 'orgAttr' && (!c || c.length === 0)){
			      			tree.treegrid('beginEdit', id);
			      			tree.editingId = id;	
			      		}else{
			      			return true;
			      		}
					},
					onContextMenu : function(e,record){
						me.endEdit();
						me.onContextMenuFn(e, record, popupMenuEdit);
					},
					onAfterEdit: function(row,changes){
						$.extend(row, changes);
						me.tab.find('span[tree-node-id]').on('click', function(){
							me.onCheckbox(this);
						});
					}
				});
			},
			
			doOrgSel : function(node){
				$(node).attr('cls','tree-checkbox1');
				me.changeNodeCls(node);
			},
			
			doOrgUnSel : function(node){
				$(node).attr('cls','tree-checkbox0');
				me.changeNodeCls(node);
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
					}
					;
				if(checked === undefined){
					checked = !nodeObj.hasClass('tree-checkbox0');//当前是否已选择
				}
				setSelfAndChildren(treeNodeId, checked);
//				setParent(node.parentCode);
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
			    var tcn = children.length, cn = 0, tn = 0, cls = 'tree-checkbox1'; // 父节点不变化，为勾选，若想变为方块请改成 tree-checkbox2
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