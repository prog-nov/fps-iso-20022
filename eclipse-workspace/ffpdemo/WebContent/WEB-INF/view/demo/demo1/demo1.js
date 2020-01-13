/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 角色管理<br>
 * Author : leoYang <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){

		var me = this,
		 	fakedata=[],
			grid = $('#dataList'),
			dialog = $('#dialog'),
			popupMenu = $('#popupMenu'),
			apis = {
				list : 'list',
				add  : 'insert',
				save   : 'update',
				remove : 'delete',
				forAdd : 'listRoleForAdd',
				forEdit: 'listRoleForEdit'
			};
		
		return {
			init :  function(){
				var item;
				for(i=1;i<=10;i++){
					item=
					{
						"date" : "2015/12/5",
						"id" : i,
						"type" : "办公用品",
						"name" : "这里是产品名称"+i,
						"addr" : "Address 11",
						"count" : "10000",
						"oldPrice" : (999+i),
						"nowPrice" : (888+i),
						"remark" : "产品明细1测试策划ishi <b>asdasda</b>产品明细1测试策划ishi <b>asdasda</b>产品明细1测试策划ishi <b>asdasda</b>产品明细1测试策划ishi <b>asdasda</b>产品明细1测试策划ishi <b>asdasda</b>"
					}
					fakedata.push(item);	
				}
				
				grid.datagrid({
//					url : apis.list,  接口地址
					data:fakedata,
					idField : 'id',
					pagination : true,
					fitColumns : false,
					rowTool:true,
					rowToolButtons:[
	    			    {text : locale.button.look, iconCls : 'bf4j-icon-row-look', handler : function(index,data){me.gotoDetail(data)}},
	    			    {text : locale.button.edit, iconCls : 'bf4j-icon-row-edit', handler : function(index,data){me.gotoUpdate(data)}},
	    			    {text : locale.button.remove, iconCls : 'bf4j-icon-row-delete', handler : function(index,data){me.doDelete([data])}}
					],
					frozenColumns : [ [ 
	    			   	{field : 'id', checkbox : true}, 
	    			   	{field : 'name', title : locale.demo.product.name, align:'left', width : 200, sortable : true}
	    			] ],
	    			columns : [ [
					{
						field : 'type',
						title : locale.demo.product.type,
						width : 100,
						align :'center',
						sortable : true
					}, {
	    				field : 'count',
	    				title : locale.demo.product.count,
	    				width : 100,
	    				align:'right',
	    				sortable : true
	    			}, {
	    				field : 'oldPrice',
	    				title : locale.demo.product.oldprice,
	    				width : 100,
	    				sortable : true,
	    				align:'right',
	    				styler : function(value, row, index) {
							return 'color:red;';
						}
	    			}, {
	    				field : 'nowPrice',
	    				title : locale.demo.product.newprice,
	    				width : 100,
	    				sortable : true,
	    				align:'right',
	    				styler : function(value, row, index) {
							return 'color:red;';
						}
	    			}, {
	    				field : 'addr',
	    				title : locale.demo.product.address,
	    				align :'center',
	    				width : 200
	    			}, {
	    				field : 'remark',
	    				title : locale.demo.product.description,
	    				sortable : true
	    			}]],
	    			onDblClickRow : function(rowIndex, rowData){
	    				me.gotoDetail(rowData);
	    				dialog.dialog("maximize");
	    			},
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
				$(".Action").find("a.SearchBtn").on("click", me.doQuery)
					   .end().find("a.AddBtn").on("click", me.gotoAdd)
					   .end().find("a.EditBtn").on("click", function(){
						   me.selectOne(grid, me.gotoUpdate);
					    }).end().find("a.DeleteBtn").on("click", function(){
						   me.selectRows(grid, me.doDelete);
					    });
				popupMenu.find(".gotoDetail").on("click", function(){
					me.gotoDetail(popupMenu.dataRow);
				}) .end().find(".gotoUpdate").on("click", function(){
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
				    title: locale.demo.title.add,    
				    height:550,
				    width:550,
				    maximized:true,
				    href : 'addProduct.page',
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				var form = $('#addForm');
				if(!form.form('validate')){
					return false;
				}
				//DEMO模拟新增 ， 此处数据库提交请参照用户管理提交. 
				me.addCallback();
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoDetail:function(data)
			{
				//DEMO模拟详细展示效果 ,此处实现请参考-消息公告详细模板实现
				dialog.dialog({    
				    title: locale.demo.title.detail,    
				    height:580,
				    width:550,
				    maximized:true,
				    href : 'detailProduct.page',
				    onLoad : function(){
				    	for(field in data)
				    	{
				    		$("#"+field).html(data[field]);
				    	}
				    },
				    buttons : [{
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.demo.title.update,    
				    height:580,
				    width:550,
				    maximized:true,
				    href : 'eidtProduct.page',
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
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
				//DEMO模拟修改 ， 此处数据库提交请参照用户管理提交. 
				me.updateCallback();
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			doDelete : function (rows){
				$b.Msg.confirm($b.Base.i18n("operate.remove",rows.length), function(){
					//DEMO模拟删除 ， 此处数据库提交请参照用户管理提交. 
					me.deleteCallback(rows.length);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert($b.Base.i18n("operate.removeOk",rs));
				me.doQuery();
			}
		};
	});
});