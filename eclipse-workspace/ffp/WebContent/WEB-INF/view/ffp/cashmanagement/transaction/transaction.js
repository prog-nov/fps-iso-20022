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
			list : 'list',
			detail : 'detail'
		};

		return {
			init : function() {
				me.setComboFields($('#searchForm'));
				me.setOrganField($('#searchForm'));

				grid.datagrid({
					url : apis.list,
					idField : 'jnlNo',
					pagination : true,
					rownumbers : true,
					rowTool : true,
					fitColumns : true,
					rowToolButtons : [ {
						text : locale.button.look,
						iconCls : 'bf4j-icon-row-look',
						handler : function(index, data) {
							me.showDetail(data)
						}
					} ],
					onDblClickRow : function(rowIndex, rowData) {
						me.showDetail(rowData);
					},
					frozenColumns : [ [ {
						field : 'jnlNo',
						title : locale.ffp.transaction.jnlNo,
						width : 250,
						sortable : true,
						formatter : function(value, row, index) {
							/*var href = "<a style='color:blue' href='detail?jnlNo="+value+"'>" + value + "</a>";*/
							var href = "<a style='color:blue' href='#'>" + value + "</a>";
							return href;
						}
					}, ] ],
					columns : [ [ {
						field : 'txStat',
						title : locale.ffp.transaction.txStat,
						width : 100,
						align : 'center',
						sortable : true,
						formatter : function(value, row, index) {
							if (value == 'CREAT') {
								return locale.ffp.common.constant.CREAT;
							} else if (value == 'APPST') {

								return locale.ffp.common.constant.APPST;

							} else if (value == 'TMOUT') {
								return locale.ffp.common.constant.TMOUT;

							} else if (value == 'COMPL') {

								return locale.ffp.common.constant.COMPL;

							} else if (value == 'PROCE') {

								return locale.ffp.common.constant.PROCE;

							} else if (value == 'ERROR') {

								return locale.ffp.common.constant.ERROR;

							}else{
								return true;
							}
						}
					}, {
						field : 'txSrc',
						title : locale.ffp.transaction.txSrc,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : 'transactionId',
						title : locale.ffp.transaction.transactionId,
						width : 250,
						align : 'center',
						sortable : true
					}, {
						field : 'endToEndId',
						title : locale.ffp.transaction.endToEndId,
						width : 250,
						align : 'center',
						sortable : true
					} ] ]
				});

				$(".doQuery").on("click", me.doQuery);
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
			doQuery : function() {
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},

		/*	goToDetail : function(jnlNo, value) {
				return "<a style='color:blue' href='detail?jnlNo="+jnlNo+"'>" + jnlNo + "</a>";
			},*/

			showDetail : function(data) {
				dialog.dialog({
					title : locale.ffp.title.detail,
					height : 500,
					width : 600,
					href : $b.context.root
							+ "/ffp/cashmanagement/transaction/detail?jnlNo="
							+ data.jnlNo,
					buttons : [ {
						text : locale.button.cancel,
						size : 'large',
						handler : me.closeDialog
					} ]
				});
				dialog.dialog("maximize");
			},
		};
	});
});