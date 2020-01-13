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
				cursetting : 'cursetting',
				save  : 'update'
			};
		
		return {
			init :  function(){			    
				grid.datagrid({
					url : apis.cursetting,
					idField : 'cutoffType',
					pagination : true,
					rowTool: false,
					
					frozenColumns : [ [ 
					    {field : 'cutoffSeq', checkbox : true}, 
	    			   	{field : 'cutoffType', title : locale.ffp.systemmaintenance.cutoff.title.cutofftype, align:'center', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
						{field : 'workdayStart', title : locale.ffp.systemmaintenance.cutoff.title.workdayStart, align:'center'}, 
						{field : 'workdayEnd', title : locale.ffp.systemmaintenance.cutoff.title.workdayEnd, align:'center'},
						{field : 'satStart', title : locale.ffp.systemmaintenance.cutoff.title.satStart, align:'center'},
						{field : 'satEnd', title : locale.ffp.systemmaintenance.cutoff.title.satEnd, align:'center'},
						{field : 'holidayStart', title : locale.ffp.systemmaintenance.cutoff.title.holidayStart, align:'center'},
						{field : 'holidayEnd', title : locale.ffp.systemmaintenance.cutoff.title.holidayEnd, align:'center'}
			        ] ]
				});
				
				//绑定事件
				$("a.bf4j-btn-2").filter(".gotoUpdate").on("click", function(){
				   me.selectOne(grid, me.gotoUpdate);
			    }).end();
			    $b.Msg.closeProgress();
			},
			
			closeDialog : function(){
				dialog.dialog('close');
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.ffp.systemmaintenance.cutoff.title.amendtitle,    
				    height:400,
				    width:800,
				    href : 'edit.page',
				    onLoad : function(){
				    	$('#updateForm').form('load', data);
				    },
				    buttons : [ {
						text : locale.button.edit, size : 'large', handler : me.doUpdate
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
				
				var params = {url : apis.save};
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				me.closeDialog();
				grid.datagrid('reload');
			},
		};
	});
});