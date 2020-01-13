/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-2<br>
 */
require([ 'beneform4j-page', 'locale' ], function(Page, locale) {
	Page.create(function($) {
		var me = this, grid = $('#dataList'), dialog = $('#dialog'), apis = {
			list : 'list'
		};

		return {
			init : function() {
				me.setComboFields($('#searchForm'));
				me.setOrganField($('#searchForm'));

				grid.datagrid({
					url : apis.list,
					idField : 'jnlNo',
					pagination : false,
					rownumbers : false,
					rowTool : false,
					fitColumns : true,
					frozenColumns : [ [ {
						field : 'jnlNo',
						title : locale.ffp.cashmanagement.transactionstatus.jnlNo,
						width : 250,
						sortable : true,
					}, ] ],
					columns : [ [ {
						field : 'txStat',
						title : locale.ffp.cashmanagement.transactionstatus.txStat,
						width : 150,
						align : 'center',
						sortable : true,
						formatter : function(value, row, index) {
							if (value == 'CREAT') {
								return locale.ffp.common.constant.CREAT;
							} else if (value == 'APPST') {

								return locale.ffp.common.constant.APPST;

							} else if (value == 'TMOUT') {
								return locale.ffp.common.constant.TMOUT;

							} else if (value == 'COMPL'||value == 'ACCP'||value == 'ACSC') {

								return locale.ffp.common.constant.COMPL;

							}else if (value == 'RJCT') {

								return locale.ffp.common.constant.RJCT;

							} else if (value == 'PROCE'||value == 'ACSP') {

								return locale.ffp.common.constant.PROCE;

							} else if (value == 'ERROR') {

								return locale.ffp.common.constant.ERROR;

							}else{
								return true;
							}
						}
					}, {
						field : 'transactionId',
						title : locale.ffp.cashmanagement.transactionstatus.transactionId,
						width : 250,
						align : 'center',
						sortable : true
					}, {
						field : 'clrSysRef',
						title : locale.ffp.cashmanagement.transactionstatus.clrSysRef,
						width : 200,
						align : 'center',
						sortable : true
					}, {
						field : 'dbtrAgtMmbId',
						title : locale.ffp.cashmanagement.transactionstatus.dbtrAgtMmbId,
						width : 150,
						align : 'center',
						sortable : true
					}, {
						field : 'cdtrAgtMmbId',
						title : locale.ffp.cashmanagement.transactionstatus.cdtrAgtMmbId,
						width : 150,
						align : 'center',
						sortable : true
					}, {
						field : 'createTs',
						title : locale.ffp.cashmanagement.transactionstatus.createTs,
						width : 250,
						align : 'center',
						sortable : true
					} ] ]
				});

				$(".doQuery").on("click", me.doQuery);
				$(".doReset").on("click", me.doReset);
				$b.Msg.closeProgress();
			},
			/**
			 * 关闭对话框
			 */
			closeDialog : function() {
				dialog.dialog('close');
			},
			/**
			 * 执行查询
			 */
			doReset : function(){
				$('#searchForm').form('reset');
			},
			doQuery : function() {
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
		};
	});
});