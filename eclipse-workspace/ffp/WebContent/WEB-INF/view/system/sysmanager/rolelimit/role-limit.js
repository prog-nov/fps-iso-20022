/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色约束管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
require(['beneform4j-treepage','locale','datagrid-groupview'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				add  : 'insert',
				find  : 'find',
				save   : 'update',
				remove : 'delete',
				
				forAdd : 'listRoleForAdd',
				forEdit: 'listRoleForEdit'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					pagination : true,
					fitColumns : true,
					rowTool: true,
					showGroup: true, 
					columns : [ [ 
						//{field : 'roleName', title : locale.system.sysmanager.rolelimit.role+' <span class="treeCheckAll tree-checkbox tree-checkbox0"> </span>', align:'left', width: 200},
						{field : 'roleDes', title : locale.system.sysmanager.rolelimit.describe, align:'left', width: 400}
			        ] ],
			        groupField:'limitNo',
			        view: groupview,
			        onLoadSuccess: function(){
			        	var panel = grid.datagrid('getPanel'),	
			        		all = panel.find('span.treeCheckAll'),
			        		rows = panel.find('span[limit-no]');
			        	me.panel = panel;
			        	me.addCheckboxCls(all, 'tree-checkbox0');
			        	rows.off('click').on('click', function() {
						    var checked = 0, cls;
						    me.changeCheckboxCls(this);
						    $.each(rows, function(){
								if($(this).hasClass('tree-checkbox1')){
									checked++;
								}
							});
						    cls = (checked === 0) ? '0' : (checked === rows.length ? '1' : '2');
						    me.addCheckboxCls(all, 'tree-checkbox'+cls);
						});
			        	all.off('click').on('click', function(){
							var cls;
							me.changeCheckboxCls(this);
							cls = all.hasClass('tree-checkbox1') ? '1' : '0';
							$.each(rows, function(){
								me.addCheckboxCls(this, 'tree-checkbox'+cls);
							});
						});
			        	panel.find('i[limit-no]').filter('.bf4j-icon-row-edit').off('click').on('click', function() {
						    me.gotoUpdate($(this).attr('limit-no'));
						}).end().filter('.bf4j-icon-row-look').off('click').on('click', function() {
						    me.gotoDetail($(this).attr('limit-no'));
						});
					},
					groupFormatter:function(value, rows){
						var row = rows[0], html;
						html = '<span limit-no="'+row.limitNo+'" class="tree-checkbox tree-checkbox0"> </span> ' + value
						     + locale.system.sysmanager.rolelimit.tot1+' <font color="red">' + row.roleNum + '</font> '+locale.system.sysmanager.rolelimit.tot2
						     + '<i limit-no="'+row.limitNo+'" class="bf4j-icon-row-look" title="'+locale.title.detail+'">　　</i>'
						     + '<i limit-no="'+row.limitNo+'" class="bf4j-icon-row-edit" title="'+locale.button.edit+'">　　</i>';
						return html;
					}
				});
				
				$b.Msg.closeProgress();
				
				//绑定事件
				$("a.doQuery").on("click", me.doQuery);
				$("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
					   .end().filter(".doDelete").on("click", me.gotoDelete);
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
				    title: locale.system.sysmanager.rolelimit.NewRole,    
				    height:400,
				    width:600,
				    //maximized: true,
				    //href : 'add.page',
				    content:'<table id="roleList" class="bf4j-grid-auto"></table>',
				    onOpen : function(){
				    	me.loadSubGrid(apis.forAdd);
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				var params = {};
				if(me.collectParams(params)){
					$b.Submit.ajaxSubmit(apis.add, params, me.addCallback);
				}
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoDetail : function (limitNo){
				dialog.dialog({    
				    title: locale.system.sysmanager.rolelimit.RoleConstraints,    
				    height:400,
				    width:600,
				    //maximized: true,
				    //href : 'detail.page',
				    content:'<table id="roleList" class="bf4j-grid-auto"></table>',
				    onOpen : function(){
				    	$('#roleList').datagrid({
							url : apis.find,
							queryParams: {limitNo : limitNo},
							pagination : false,
							fitColumns : true,
							columns:[[
								{field:'roleName', title: locale.system.sysmanager.rolelimit.CharacterName, fit: true, width: 250},
								{field:'des', title: locale.system.sysmanager.rolelimit.RoleDescription, fit: false, width: 250}
							]],
							loadFilter: function(data){
								return {rows : data.roles, total : data.roles.length};
							}
						});
				    },
				    buttons : [{
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					}]
				});
			},
			
			gotoUpdate : function (limitNo){
				dialog.dialog({    
				    title: locale.system.sysmanager.rolelimit.ModifyRoleConstraint,    
				    height:400,
				    width:600,
				    //maximized: true,
				    //href : 'edit.page',
				    content:'<table id="roleList" class="bf4j-grid-auto" limit-no="'+limitNo+'"></table>',
				    onOpen : function(){
				    	me.loadSubGrid(apis.forEdit, limitNo);
				    },
				    buttons : [ {
						text : locale.button.save, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			doUpdate : function (){
				var params = {};
				if(me.collectParams(params)){
					$b.Submit.ajaxSubmit(apis.save, params, me.updateCallback);
				}
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			collectParams : function(params){
				var limitNo = me.subGrid.attr('limit-no'),
					checkeds = me.subGrid.datagrid('getChecked');
				if(checkeds.length <= 1){
					$b.Msg.alert(locale.system.sysmanager.rolelimit.check1+"<font color='red'> 2 </font>"+locale.system.sysmanager.rolelimit.check2);
					return false;
				}else{
					$.each(checkeds, function(i, n){
						params['roleIds[' + i + ']'] = n.roleId;
					});
					if(limitNo){
						params.limitNo = limitNo;
					}
					return params;
				}
			},
			
			gotoDelete : function(){
				var checkeds = me.panel.find('span.tree-checkbox1[limit-no]');
			    if(checkeds.length === 0){
				   $b.Msg.warning($b.locale.operate.selectToRecord);
			    }else{
				   me.doDelete(checkeds);
			    }
			},
			
			doDelete : function (rows){
				$b.Msg.confirm(locale.system.sysmanager.rolelimit.remove1+rows.length+locale.system.sysmanager.rolelimit.remove2, function(){
					var limitNos = [];
					rows.each(function(){
						limitNos.push($(this).attr('limit-no'));
					});
					$b.Submit.ajaxSubmit(apis.remove, {'limitNo' : limitNos}, function(){me.deleteCallback(limitNos.length);});
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert(locale.system.sysmanager.rolelimit.removeS + rs + locale.system.sysmanager.rolelimit.removeS2);
				//grid.datagrid('reload');
				me.doQuery();
			},
			
			loadSubGrid : function (url, limitNo){
				var subGrid = $('#roleList'),
					params = {};
				if(limitNo){
					params.limitNo = limitNo;
				}
				me.subGrid = subGrid;
				subGrid.datagrid({
					url : url,
					queryParams: params,
					columns:[[
						{field:'checked', checkbox: true},
						{field:'roleName', title: locale.system.sysmanager.rolelimit.CharacterName, fit: true, width: 250},
						{field:'des', title: locale.system.sysmanager.rolelimit.RoleDescription, fit: false, width: 250}
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
			 * 改变复选框样式
			 */
			changeCheckboxCls : function(obj){
				me.addCheckboxCls(obj, $(obj).hasClass('tree-checkbox1') ? 'tree-checkbox0' : 'tree-checkbox1');
			},
			/**
			 * 添加复选框样式
			 */
			addCheckboxCls : function(obj, cls) {
			    $(obj).removeClass('tree-checkbox0 tree-checkbox1 tree-checkbox2').addClass(cls);
			}
		};
	});
});