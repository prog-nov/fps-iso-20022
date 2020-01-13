/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-2<br>
 */
require(['beneform4j-page','locale'], function(Page,locale) {
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
			    me.setComboDatas('ORGANIZATION_ID');
			    
				grid.datagrid({
					url : apis.list,
					idField : 'userId',
					pagination : true,
					rowTool: false,
					frozenColumns : [ [ 
	    			   	{field : 'userId', checkbox : true}, 
	    			   	{field : 'userName', title : locale.system.sysmanager.user.username, align:'left', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
						{field : 'nickName', title : locale.system.sysmanager.user.nickName, align:'center'}, 
						{field : 'userStatus', title : locale.system.sysmanager.user.status, align:'center'},
						{field : 'orgId', title : locale.system.sysmanager.user.agencyNumber, align:'center', formatter: me.comboFormatter('ORGANIZATION_ID')},
						{field : 'certType', title : locale.system.sysmanager.user.IDType, align:'left', formatter: me.comboFormatter('CERT_TYPE')},
						{field : 'certNo', title : locale.system.sysmanager.user.IDNumber, align:'center'},
						{field : 'mobilePhone', title : locale.system.sysmanager.user.mobileTelephone, align:'center'},
						{field : 'telephone', title : locale.system.sysmanager.user.phoneNumber, align:'center'},
						{field : 'email', title : locale.system.sysmanager.user.emailAddress, align:'left'},
						{field : 'limitIp', title : locale.system.sysmanager.user.limitationIP, align:'left'},
						{field : 'onlineSessinNum', title : locale.system.sysmanager.user.MaximumSessionNumber, align:'right'},
						{field : 'lockFlag', title : locale.system.sysmanager.user.theLockSymbol, align:'center', formatter: me.comboFormatter('BOOLEAN')},
						{field : 'lockDate', title : locale.system.sysmanager.user.LockTheDate, align:'center'},
						{field : 'lockTime', title : locale.system.sysmanager.user.lockingTime, align:'center'},
						{field : 'loginNum', title : locale.system.sysmanager.user.TryLoginFrequency, align:'right'},
						{field : 'lastLoginIp', title : locale.system.sysmanager.user.FinallyLoginIP, align:'left'},
						{field : 'lastLoginDate', title : locale.system.sysmanager.user.lastvisitDate, align:'center'},
						{field : 'lastLoginTime', title : locale.system.sysmanager.user.LastLoginTime, align:'center'},
						{field : 'modiPwdDate', title : locale.system.sysmanager.user.LastChangePassword, align:'center'},
						{field : 'modiPwdTime', title : locale.system.sysmanager.user.changeThePasswordTime, align:'center'}
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
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
			/**
			 * 进入新增
			 */
			gotoAdd : function (){
				dialog.dialog({    
				    title: locale.system.sysmanager.user.adduser,    
				    height:400,
				    width:800,
				    href : 'add.page',
				    onLoad : function(){
				    	me.setComboFields($('#addForm'));
					    me.setOrganField($('#addForm'));
					    me.setComboDatas('CERT_TYPE');
				    	me.loadSubGrid(apis.forAdd);
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text :locale.button.cancel , size : 'large', handler : me.closeDialog
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
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.system.sysmanager.user.usermod,    
				    height:400,
				    width:800,
				    href : 'edit.page',
				    onLoad : function(){
				    	me.setComboFields($('#updateForm'));
					    me.setOrganField($('#updateForm'));
					    me.setComboDatas('CERT_TYPE');
				    	$('#updateForm').form('load', data);
				    	me.loadSubGrid(apis.forEdit, data);
				    },
				    buttons : [ {
						text : locale.button.save, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
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
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			doDelete : function (rows){
				$b.Msg.confirm(locale.system.sysmanager.user.remove1+rows.length+locale.system.sysmanager.user.remove2, function(){
					var userIds = [];
					$.each(rows, function(i, n){
						userIds.push(n.userId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'userId' : userIds}, function(){me.deleteCallback(rows.length)});
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert(locale.system.sysmanager.user.removeS + rs + locale.system.sysmanager.user.removeS2);
				//grid.datagrid('reload');
				me.doQuery();
			},
			
			loadSubGrid : function (url, data){
				var subGrid = $('#userRoleList'),
					params = {};
				if(data){
					params.userId = data.userId;
				}
				me.subGrid = subGrid;
				subGrid.datagrid({
					url : url,
					queryParams: params,
					columns:[[
						{field:'checked', checkbox: true},
						{field:'roleName', title: locale.system.sysmanager.user.roleName, fit: true, width: 250}
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
				var params = {userId : data.userId}, 
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
					$b.Msg.alert(locale.operate.doOk);
				});
			}
		};
	});
});