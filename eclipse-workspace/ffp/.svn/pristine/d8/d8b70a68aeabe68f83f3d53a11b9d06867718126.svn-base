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
				detail : 'detail'
			};
		
		return {
			init :  function(){
				me.setComboFields($('#searchForm'));
			    me.setOrganField($('#searchForm'));
			    
//			    $('#ntfctnCreateBeginDateValue').datebox().datebox('calendar').calendar({
//					validator: function(date){
//						var now = new Date();
//						var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate()-180);
//						var d2 = now;
//						return d1<=date && date<=d2;
//					}
//				});

			  
				grid.datagrid({
					url : apis.list,
					idField : 'ntfctnId',
					pagination : true,
					rownumbers:true,
					rowTool:false,
					frozenColumns : [ [ 
	    			   	{field : 'msgId', title : locale.ffp.cashmanagement.fpspaymentnotification.msgId ,fitColumn:true, sortable : true}, 
	    			] ],
					columns : [ [ 
						{field : 'ntfctnId', title : locale.ffp.cashmanagement.fpspaymentnotification.ntfctnId,fitColumn:true, align:'center',sortable : true},
						{field : 'ntfctnCreateTs', title : locale.ffp.cashmanagement.fpspaymentnotification.ntfctnCreateTs,fitColumn:true, align:'center',sortable : true},
						{field : 'ntfctnAcctId', title : locale.ffp.cashmanagement.fpspaymentnotification.ntfctnAcctId,fitColumn:true, align:'center',sortable : true},
						{field : 'ntfctnAcctType', title : locale.ffp.cashmanagement.fpspaymentnotification.ntfctnAcctType,fitColumn:true, align:'center',sortable : true},
						{field : 'ntfctnAmt', title : locale.ffp.cashmanagement.fpspaymentnotification.ntfctnAmt,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentCag', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentCag,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentEndToEndId', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentEndToEndId,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentTxId', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentTxId,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentClrSysRef', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentClrSysRef,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentDbtr', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentDbtr,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentCdtr', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentCdtr,fitColumn:true, align:'center',sortable : true},
						{field : 'paymentTs', title : locale.ffp.cashmanagement.fpspaymentnotification.paymentTs,fitColumn:true, align:'center',sortable : true},
			        ] ]
				});
				
				$(".doQuery").on("click", me.doQuery);
				
				$('#dbtrMmbId').on('change', function(){
					var val = $(this).children('option:selected').val();
					alert(val);
					if('Other' == val)
					{
						$('#DbtrMmbIdOther').attr("readonly","readonly").attr("disabled","disabled");
					}
					else
					{
						$('#DbtrMmbIdOther').removeAttr("readonly").removeAttr("disabled");
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

			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			
//			goToDetail : function(jnlNo, value){
//				return '<a style="color:blue" href="#">'+value+'</a>';
//			},
			
//			showDetail :function(data){
//				dialog.dialog({    
//				    title: locale.ffp.title.test,    
//				    height:500,
//				    width:600,
//				    href : $b.context.root+"/ffp/cashmanagement/transaction/detail?jnlNo=" + data.jnlNo,
//				    buttons : [ {
//						text : locale.button.cancel, size : 'large', handler : me.closeDialog
//					} ]
//				});
//				dialog.dialog("maximize");
//			},
		};
	});
});