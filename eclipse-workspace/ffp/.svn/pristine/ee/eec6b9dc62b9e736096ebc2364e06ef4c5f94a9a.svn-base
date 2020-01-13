/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 消息管理<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#newsList'),
			dialog = $('#dialog'),
			apis = {
				list : $b.context.root+'/news/listDatas',
				add  : $b.context.root+'/news/add',
				save   : $b.context.root+'/news/edit',
				remove : $b.context.root+'/news/delete',
				forAdd : $b.context.root+'/news/toAdd',
				forEdit: $b.context.root+'/news/toEdit'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					idField : 'msgId',
					pagination : true,
					rownumbers:false,
					rowTool:true,
					rowToolButtons:[
						{text : locale.button.look, iconCls : 'bf4j-icon-row-look', handler : function(index,data){me.gotoDetail(data)}},
						{text : locale.button.edit, iconCls : 'bf4j-icon-row-edit', handler : function(index,data){me.gotoUpdate(data)}},
						{text : locale.button.remove, iconCls : 'bf4j-icon-row-delete', handler : function(index,data){me.doDelete([data])}}
					],
					frozenColumns : [ [ 
						{field : 'msgId',checkbox : true}, 
						{field : 'msgTitle',title : locale.system.news.title.msgTitle,width : 300,sortable : true},
						{field : 'msgTypeName',title : locale.system.news.title.msgTypeName,align:'center',width : 80}, 
						{field : 'msgLevelName',title : locale.system.news.title.msgLevelName,width : 80,align:'center'},
						{field : 'msgType',hidden : true}, 
						{field : 'msgLevel',hidden : true},
						{field : 'effectiveFlag',hidden : true},
						{field : 'effectiveDayCnt',hidden : true},
						{field : 'effectiveStartDate',hidden : true},
						{field : 'effectiveStartTime',hidden : true},
						{field : 'effectiveEndDate',hidden : true},
						{field : 'effectiveEndTime',hidden : true},
	    			] ],
					columns : [ [ 
					    {field : 'msgContent',title : locale.system.news.title.msgContent,width : 500,sortable : true}, 
					    {field : 'sendOper',title : locale.system.news.title.sendOper,width : 100,sortable : true}, 
					    {field : 'sendDate',title : locale.system.news.title.sendDate,width : 100,sortable : true}, 
					    {field : 'sendTime',title : locale.system.news.title.sendTime,width : 120,sortable : true}
			        ] ],
					onDblClickRow : function(rowIndex, rowData){
	    				me.gotoDetail(rowData);
	    			}
				});
				me.setComboFields($('#searchForm'));
				
				$('.datagrid-header div').css('textAlign', 'center');
				$b.Msg.closeProgress();
				
				//绑定事件
				$(".Action").find("a.SearchBtn").on("click", me.doQuery)
					   .end().find("a.AddBtn").on("click", me.gotoAdd)
					   .end().find("a.EditBtn").on("click", function(){
						   me.selectOne(grid, me.gotoUpdate);
					    }).end().find("a.DeleteBtn").on("click", function(){
						   me.selectRows(grid, me.doDelete);
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
				    title:  locale.system.news.content.addTitle,    
				    height:500,
				    width:600,
				    href : apis.forAdd,
				    onLoad : function(){
				    	var f = $('#addForm');
				    	me.setComboFields(f);
				    	me.setOrganField(f);
				    	me.iniFormElement(f);
				    },
				    buttons : [ {
						text : locale.button.submit, size : 'large', handler : me.doAdd
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				}); 
			},
			
			doAdd : function (){
				var form = $("#addForm");
				if(!form.form('validate')){
					return false;
				}
				var params = {url : apis.add};
				$b.Submit.ajaxSubmitForm(form, params, me.addCallback);
			},
			
			addCallback : function (rs, params){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			gotoUpdate : function (data){
				dialog.dialog({    
				    title: locale.system.news.content.editTitle,    
				    height:500,
				    width:600,
				    href : apis.forEdit+'?msgId=' + data.msgId,
				    onLoad : function(){
				    	var f = $('#editForm');
				    	me.setComboFields(f);
				    	me.setOrganField(f);
				    	me.iniFormElement(f);
				    	me.iniMsgCss(f,data.msgType);
				    	me.iniEffectCss(f,data.effectiveFlag);
				    	var startDate = data.effectiveStartDate.substr(0,4) + "-" 
				    			+ data.effectiveStartDate.substr(4,2) +"-"
				    			+ data.effectiveStartDate.substr(6,2) + " "
				    			+ data.effectiveStartTime.substr(0,2) +":"
				    			+ data.effectiveStartTime.substr(2,2) + ":"
				    			+ data.effectiveStartTime.substr(4,2) ;
				    	var endDate = data.effectiveEndDate.substr(0,4) + "-" 
				    			+ data.effectiveEndDate.substr(4,2) +"-"
				    			+ data.effectiveEndDate.substr(6,2) + " "
				    			+ data.effectiveEndTime.substr(0,2) +":"
				    			+ data.effectiveEndTime.substr(2,2) + ":"
				    			+ data.effectiveEndTime.substr(4,2) ;
				    	f.find("#effectiveStartDate").datetimebox('setValue', startDate);
				    	f.find("#effectiveEndDate").datetimebox('setValue', endDate);
				    },
				    buttons : [ {
						text : locale.button.edit, size : 'large', handler : me.doUpdate
					}, {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
			},
			
			doUpdate : function (){
				var form = $('#editForm');
				if(!form.form('validate')){
					return false;
				}
				var params = {url : apis.save};
				$b.Submit.ajaxSubmitForm(form, params, me.updateCallback);
			},
			
			updateCallback : function (){
				$b.Msg.alert(locale.operate.doOk);
				grid.datagrid('reload');
				me.closeDialog();
			},
			
			doDelete : function (rows){
				$b.Msg.confirm($b.Base.i18n('operate.remove',rows.length), function(){
					var msgIds = [];
					$.each(rows, function(i, n){
						msgIds.push(n.msgId);
					});
					$b.Submit.ajaxSubmit(apis.remove, {'msgIds' : msgIds}, me.deleteCallback);
				});
			},
			
			deleteCallback : function (rs){
				$b.Msg.alert($b.Base.i18n('operate.removeOk',rs));
				me.doQuery();
			},
			gotoDetail :function(data){
				dialog.dialog({    
				    title: locale.system.news.content.detailTitle,    
				    height:500,
				    width:600,
				    href : $b.context.root+"/news/toDetail?msgId=" + data.msgId,
				    buttons : [ {
						text : locale.button.cancel, size : 'large', handler : me.closeDialog
					} ]
				});
				dialog.dialog("maximize");
			},
			iniFormElement:function(iniForm){
				iniForm.find(".recvNet").css("display","none")
				.end().find(".recvOper").css("display","none")
				.end().find(".effectiveDayCnt").css("display","none")
				.end().find(".effectiveStartDate").css("display","none")
				.end().find(".effectiveEndDate").css("display","none")
				.end().find(".effectiveFlag").css("display","none")
				.end().find("#msgType").combobox({
					onChange : function(newValue, oldValue){
						me.iniMsgCss(iniForm,newValue);
					}
				})
				.end().find("#effectiveFlag").combobox({
					onChange : function(newValue, oldValue){
						me.iniEffectCss(iniForm,newValue);
					}
				});
			},
			iniMsgCss:function(iniForm , newValue){
				if('3' == newValue){
					iniForm.find(".recvNet").css("display","block")
					.end().find(".recvOper").css("display","block")
					.end().find(".effectiveFlag").css("display","none");
				}else if('2' == newValue){
					iniForm.find(".recvNet").css("display","none")
					.end().find(".recvOper").css("display","none")
					.end().find(".effectiveFlag").css("display","block");
				}else{
					iniForm.find(".recvNet").css("display","none")
					.end().find(".recvOper").css("display","none")
					.end().find(".effectiveFlag").css("display","none");
				}
			},
			iniEffectCss:function(iniForm, newValue){
				if('2' == newValue){
					iniForm.find(".effectiveDayCnt").css("display","block")
					.end().find(".effectiveStartDate").css("display","none")
					.end().find(".effectiveEndDate").css("display","none");
				}else if('3' == newValue){
					iniForm.find(".effectiveDayCnt").css("display","none")
					.end().find(".effectiveStartDate").css("display","block")
					.end().find(".effectiveEndDate").css("display","block");
				}else{
					iniForm.find(".effectiveDayCnt").css("display","none")
					.end().find(".effectiveStartDate").css("display","none")
					.end().find(".effectiveEndDate").css("display","none");
				}
			}
		};
	});
});