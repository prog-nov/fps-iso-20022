/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-2<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				add  : 'insert',
				save   : 'update',
				remove : 'delete',
				forAdd : 'listRoleForAdd',
				forEdit: 'listRoleForEdit',
				
				resetPassword : 'resetPassword',
				updateUserStatus : 'updateUserStatus',
				updateLockFlag : 'updateLockFlag',
				offline : 'offline'
			};
		
		return {
			init :  function(){
				me.setComboFields($('#searchForm'));
			    me.setOrganField($('#searchForm'));
			    me.setComboDatas('CERT_TYPE');
			    
				grid.datagrid({
					url : apis.list,
					idField : 'roleId',
					pagination : true,
					rowToolButtons:[ {
						text : '詳 情', iconCls : 'bf4j-icon-row-edit', handler : function(index,data){
							me.gotoUpdate(data);
						}
					}, {
						text : '删 除', iconCls : 'bf4j-icon-row-delete', handler : function(index,data){
							console.log(data);
							me.doDelete([data]);
						}
					}],
					frozenColumns : [ [ 
	    			   	//{field : 'jnlNo', checkbox : true}, 
	    			   	//{field : 'userName', title : '用户名称', align:'left', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
					    {field : 'roleId', title : '角色代码', checkbox : true},
					    {field : 'remarks', title : 'remarks',  formatter:function(value, row, index){  
					        var str = '<input list="companys" />'+
					        			'<datalist id="companys">'+
					        			'<option value="Apple">'+
					        			'<option value="Microsoft">'+
					        			'<option value="Github">'+
					        			'</datalist>';  
					        return str;  }},
						{field : 'txStat', title : 'txStat', align:'center'}, 
						{field : 'txSrc', title : 'txSrc', align:'center'},
						{field : 'transactionId', title : 'transactionId', align:'center',editor:{type:'numberbox',options:{precision:1}}},
						{field : 'endToEndId', title : 'endToEndId', align:'center'},
						
						{field : 'msgId', title : 'msgId', align:'center'},
						{field : 'msgFromType', title : 'msgFromType', align:'center'},
						{field : 'msgFrom', title : 'msgFrom', align:'center'},
						{field : 'msgToType', title : 'msgToType', align:'center'},
						{field : 'msgTo', title : 'msgTo', align:'center'},
						{field : 'msgType', title : 'msgType', align:'center'},
						{field : 'msgStatus', title : 'msgStatus', align:'center'},
						
						{field : 'debtorName', title : 'debtorName', align:'center'},
						{field : 'debtorAcctno', title : 'debtorAcctno', align:'center'},
						{field : 'debtorAcctnoType', title : 'debtorAcctnoType', align:'center'},
						{field : 'debtorAgtId', title : 'debtorAgtId', align:'center'},
						{field : 'debtorAgtBic', title : 'debtorAgtBic', align:'center'},
						
						{field : 'creditorName', title : 'creditorName', align:'center'},
						{field : 'creditorAcctno', title : 'creditorAcctno', align:'center'},
						{field : 'creditorAcctnoType', title : 'creditorAcctnoType', align:'center'},
						{field : 'creditorAgtId', title : 'creditorAgtId', align:'center'},
						{field : 'creditorAgtBic', title : 'creditorAgtBic', align:'center'},
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
				$(".doRefund").on("click",function(){

					  me.selectRows(grid, me.doRefund);
				});
				
			    $("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
			    .end().filter(".gotoDownload").on("click", function(){
			    	grid.datagrid('download', {downloadId : 'userList'});
			    }).end().filter(".gotoUpdate").on("click", function(){
				   me.selectOne(grid, me.gotoUpdate);
			    }).end().filter(".doDelete").on("click", function(){
				   me.selectRows(grid, me.doDelete);
			    }).end().filter(".gotoResetPassword").on("click", function(){//重置密码
				    var data = me.selectOne(grid);
				    if(data){
				    	me.doUpdateUserState(data, 4);
				    }
			    }).end().filter(".gotoStart").on("click", function(){//启用
			    	var data = me.selectOne(grid);
				    if(data){
				    	me.doUpdateUserState(data, 1, true);
				    }
			    }).end().filter(".gotoStop").on("click", function(){//停用
			    	var data = me.selectOne(grid);
				    if(data){
				    	me.doUpdateUserState(data, 1, false);
				    }
			    }).end().filter(".gotoLock").on("click", function(){//锁定
			    	var data = me.selectOne(grid);
				    if(data){
				    	me.doUpdateUserState(data, 3, true);
				    }
			    }).end().filter(".gotoUnlock").on("click", function(){//解锁
			    	var data = me.selectOne(grid);
				    if(data){
				    	me.doUpdateUserState(data, 3, false);
				    }
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
				
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			doRefund :function(rows){
				console.log(rows)
				$b.Msg.confirm('您确定要退款选择的'+rows.length+'个用户吗？', function(){
					var roleIds = [];
					$.each(rows, function(i, n){
						roleIds.push(n.roleId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'roleId' : roleIds}, function(){me.doRefundCallback(rows.length)});
				});
			},
			doRefundCallback:function(rs){
				$b.Msg.alert('操作成功，成功退款 ' + rs + '个用户');
				//grid.datagrid('reload');
				me.doQuery();
			},
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: '新增用户',    
				    height:400,
				    width:800,
				    href : 'add.page',
				    onLoad : function(){
				    	me.loadSubGrid(apis.forAdd);
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
				
				var params = {url : apis.add},
					checkeds = me.subGrid.datagrid('getChecked');
				$.each(checkeds, function(i, n){
					params['roles[' + i + '].roleId'] = n.roleId;
				});
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: '用户詳情',    
				    height:400,
				    width:800,
				    href : 'edit.page',
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
				    	me.loadSubGrid(apis.forEdit, data);
				    },
				    buttons : [ {
						text : '確定', size : 'large', handler : me.doUpdate
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
				
				var params = {url : apis.save},
					checkeds = me.subGrid.datagrid('getChecked');
				$.each(checkeds, function(i, n){
					params['roles[' + i + '].roleId'] = n.roleId;
				});
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			doDelete : function (rows){
				$b.Msg.confirm('您确定要删除选择的'+rows.length+'个用户吗？', function(){
					var roleIds = [];
					$.each(rows, function(i, n){
						roleIds.push(n.roleId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'roleId' : roleIds}, function(){me.deleteCallback(rows.length)});
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert('操作成功，成功删除 ' + rs + '个用户');
				//grid.datagrid('reload');
				me.doQuery();
			},
			
			loadSubGrid : function (url, data){
				var subGrid = $('#userRoleList'),
					params = {};
				if(data){
					params.roleId = data.roleId;
				}
				me.subGrid = subGrid;
				subGrid.datagrid({
					url : url,
					queryParams: params,
					columns:[[
						{field:'checked', checkbox: true},
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
			 * 启用/停用/离线/锁定/解锁用户
			 * type 1 启用/停用  (2 离线) 3 锁定/解锁 4 重置密码
			 */
			doUpdateUserState : function(data, type, isTrue){
				var params = {roleId : data.roleId}, 
					url;
				if(type === 1){
					url = apis.updateUserStatus;
					params.userStatus = isTrue;
				}else if(type === 3){
					url = apis.updateLockFlag;
					params.lockFlag = isTrue;
				}else{
					url = apis.resetPassword;
				}
				$b.Submit.ajaxSubmit(url, params, function(rs){
					grid.datagrid('reload');
					$b.Msg.alert('操作成功');
				});
			}
		};
	});
});