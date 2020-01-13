/**
* Copy Right Information : Forms Syntron <br>
* Project : 四方精创 Java EE 开发平台 <br>
* Description : 角色-角色(分配)关系维护<br>
* Author : WangXiaoYing <br>
* Version : 1.1.0 <br>
* Since : 1.1.0 <br>
* Date : 2017-3-13<br>
*/
require(['beneform4j-page'], function(Page){
	Page.create(function($){
		/**
		 * 生成主页面数据表格
		 */
		var me=this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			rowMenu = $('#rowMenu'),
			//修改页面加载的初始数据
			initUpdateData,
			/**
			 * 定义列表table,增加,修改,删除,增加页面下拉框的url
			 */
			apis = {
				getList : 'list',
				//插入数据
				add  : 'insert',
				gotoAdd  : 'gotoAdd',
				//修改数据
				save   : 'update',
				gotoEdit  : 'gotoEdit',
				//删除数据
				remove : 'delete',
				forAdd : 'getListRoleAllotForAdd',
				//下拉框填充
				forSelect : 'getListRoleAllotForSelect',
				forEdit: 'getListRoleAllotForEdit'
			};		
		/**
		 * 返回初始化表格数据，显示在主页面上
		 */
		return {
			init :  function(){
				grid.datagrid({
					url : apis.getList,
					idField : 'roleId',
					pagination : true,
					fitColumns : true,
					rowToolButtons:[ {
						text : '修改', iconCls : 'bf4j-icon-row-edit', handler : function(index,data){
							me.gotoUpdate(data);
						}
					}, {
						text : '删 除', iconCls : 'bf4j-icon-row-delete', handler : function(index,data){
							me.doDelete([data]);
						}
					}],
					/**
					 * 角色与角色(分配)列表项展示
					 */
					columns : [ [ 
						{field : 'roleId', title : '角色ID', checkbox : true},   
						{field : 'roleName', title : '角色名称', align:'center', width: 250},
						//{field : 'roleAllotId', title : '角色(分配)ID', align:'center', width: 400},
						{field : 'roleAllotName', title : '角色(分配)名称', align:'center', width: 300}
			        ] ]
				});
				$b.Msg.closeProgress();
				/**
				 * 绑定查询,增加,修改,删除事件
				 */
				$(".doQuery").on("click", me.doQuery);
			    $("a.bf4j-btn-2").filter(".gotoAdd").on("click", me.gotoAdd)
			    .end().filter(".gotoUpdate").on("click", function(){
				   me.selectOne(grid, me.gotoUpdate);
			    }).end().filter(".doDelete").on("click", function(){
				   me.selectRows(grid, me.doDelete);
			    });
			    rowMenu.find(".gotoUpdate").on("click", function(){
					me.gotoUpdate(rowMenu.dataRow);
				}).end().find(".doDelete").on("click", function(){
					me.doDelete([rowMenu.dataRow]);
				});
			    
			},
			/**
			 * 查询函数
			 */
			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#queryForm')));
			},			
			/**
			 * 新增角色和角色(分配)的关系
			 */
			gotoAdd : function(){
				dialog.dialog({    
				    title: '新增角色和角色(分配)的关系',    
				    height:400,
				    width:800,
				    maximized: false,
				    //转到增加页面
				    href : "add.page",
				    onLoad : function(){
				    	//加载下拉框
				    	me.loadSelect(apis.forSelect);
				    	//下拉框提示请选择
				    	me.numberInputPlaceholder();
				    	//加载角色
				    	me.loadSubGrid(apis.forAdd);
				    },
				    buttons : [ {
						text : '提交', size : 'large', handler : me.doAdd
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			/**
			 * 增加下拉框提示请选择
			 */
			numberInputPlaceholder : function (){
				$("#roleSelect").each(function(i){
					var span = $(this).siblings("span")[0];
					var targetInput = $(span).find("input:first");
					if(targetInput){
						$(targetInput).attr("placeholder", $(this).attr("placeholder"));
					}
				});
			},
			/**
			 * 增加角色与角色(分配)关系的函数
			 */
			doAdd : function (){
				var form = $('#addForm');
				if(!form.form('validate')){
					return false;
				}
				var params = {url : apis.add},
					//取出表格数据
					checkeds = me.subGrid.datagrid('getChecked');
				//判断是否添加新角色与角色(分配)关系,如果没选给予提示
				if(checkeds.length===0){
					$b.Msg.alert('请勾选至少一个角色(分配)!');
					return false;
				}
				//下拉框所选项对应id里的value拿出
				var selectRoleId = $("#roleSelect").combobox('getValue');
				//遍历数据
				$.each(checkeds, function(i, n){
					//将每一行数据放入参数中
					params['roles[' + i + '].roleId'] = n.roleAllotId;
				});
				params["roleId"] = selectRoleId;
				//用ajax发送至后台
				$b.Submit.ajaxSubmitForm(form, params,  me.addCallback);
			},
			/**
			 * 增加成功函数
			 */
			addCallback : function (rs, params){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			/**
			 * 修改角色和角色(分配)的关系
			 */
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: '修改角色和角色(分配)的关系',    
				    height:400,
				    width:800,
				    //小窗口显示
				    maximized: false,
				    href : 'edit.page',
				    onLoad : function(){
				    	//加载待修改数据
				    	$('#updateForm').form('load',data);
				    	//把加载的初始化修改数据序列化url编码的文本字符串
				    	initUpdateData= $('#updateForm').serialize();
				    	//加载右侧角色(分配)关系列表
				    	me.loadSubGrid(apis.forEdit,data);
				    },
				    buttons : [ {
						text : '保存', size : 'large', handler : me.doUpdate
					}, {
						text : '取消', size : 'large', handler : me.closeDialog
					} ]
				});
			},
			/**
			 * 修改角色和角色(分配)关系的函数
			 */
			doUpdate : function (){
				//全部未勾选时给予提示
				var checkeds = me.subGrid.datagrid('getChecked');
				if(checkeds.length===0){
					$b.Msg.alert('您将删除此关系!请勾选至少一个角色(分配)!');
					return false;
				}
				var form = $('#updateForm'),
					//把准备提交的修改的数据序列化url编码的文本字符串
				    updateFormData= $('#updateForm').serialize();
				//判断初始化修改数据是否修改
				if(initUpdateData===updateFormData){
					 $b.Msg.alert('您未做任何修改!');
				}else{
					if(!form.form('validate')){
					return false;
					}				
					var params = {url : apis.save};											
					$.each(checkeds, function(i, n){
						params['roles[' + i + '].roleId'] = n.roleAllotId;
					});
					$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
				}				
			},
			/**
			 * 修改成功函数
			 */
			updateCallback : function (){
				$b.Msg.alert('操作成功 ');
				grid.datagrid('reload');
				me.closeDialog();
			},
			/**
			 * 删除角色与角色(分配)的关系
			 */
			doDelete : function (rows){
				console.log(rows);
				$b.Msg.confirm('您确定要删除选择的所有与'+rows[0].roleName+'关联的角色(分配)关系吗？', function(){
					var roleIds = [];
					$.each(rows, function(i, n){//遍历数据
						roleIds.push(n.roleId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'roleId' : roleIds}, function(){me.deleteCallback(rows.length)});
					});
				},
			/**
			 * 删除成功函数
			 */
			deleteCallback : function (rs){
				$b.Msg.alert('操作成功，已删除 ' + rs + '组角色与角色(分配)关系');
				me.doQuery();
			},
			/**
			 * 下拉框填充
			 */
			loadSelect : function (url){
				$('#roleSelect').combobox({    
				    url:url,    
				    valueField:'roleId',    
				    textField:'roleName'   
				}); 
			},
			/**
			 * 角色分配列表展示
			 */
			loadSubGrid : function (url, data){
				var subGrid = $('#roleAllotList'),
					params = {};
				if(data){
					params.roleAllotId = data.roleAllotId;
					params.roleId = data.roleId;
				}
				me.subGrid = subGrid;				
				subGrid.datagrid({
					url : url,
					queryParams: params,
					columns:[[
						{field:'checked', checkbox:true},
						{field:'roleAllotId', title: '角色(分配)ID', fit: true, width: 150},
						{field:'roleAllotName', title: '角色(分配)名称', fit: true, width: 200}
					]],
					/**
					 * 加载成功函数
					 */
					onLoadSuccess: function(data, grid){
						var $g = $(grid||this);
						if(data && data.rows){
							$.each(data.rows, function(i,c){
								if(c.checked){
									$g.datagrid('checkRow', i);
								}
							});
							$g.datagrid('clearSelections');
						};
					}
				});
			},
			 /**
			  * 关闭对话框
			  */
			closeDialog : function(){
				dialog.dialog('close');
			}			
		};				
	});
});