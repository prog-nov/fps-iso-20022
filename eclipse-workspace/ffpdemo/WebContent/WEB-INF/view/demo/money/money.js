/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 附件管理<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
require(['beneform4j-page'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list'
			};
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'id',
					pagination : true,
					rowTool: false,
					rownumbers:false ,
					scrollbarSize:0,
					onLoadSuccess:function(){
						$('input[name=btn_test]').on('click',function(){
							$this = $(this);
							$b.Msg.alert($this.attr('param'));
						});
					},
					frozenColumns : [ [ 
	    			   	{field : 'id', checkbox : true}, 
	    			   	{field : 'money1', title : "金额一", align:'center',width:120,formatter:$b.App.formatter.toPercent()}, 
	    			] ],
					columns : [ [ 
						{field : 'money2', title : "金额二", align:'center',width:120,formatter:$b.App.formatter.toPercent()}, 
						{field : 'money3', title : "金额三", align:'center',width:120,formatter:$b.App.formatter.toPercent()},
						{field : 'money4', title : "金额四", align:'center',width:120,formatter:$b.App.formatter.toPercent()},
						{field : 'money5', title : "金额五", align:'center',width:120,formatter:$b.App.formatter.toPercent()},
						{field : 'money6', title : "金额六", align:'center',width:120,formatter:$b.App.formatter.toPercent()},
						{field : 'money7', title : "操作" , align:'center',width:120,formatter:function(v,r){
							return '<input type="button" value="测试" name="btn_test" param="' + r.money2 + ',' + r.money3 + '"/>';
						}}
			        ] ]
				});
				//绑定事件
				$(".SearchBtn").on("click", me.doQuery);
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
			}
		};
	});
});