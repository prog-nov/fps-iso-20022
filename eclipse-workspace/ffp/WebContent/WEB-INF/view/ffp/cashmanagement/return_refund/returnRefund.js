
require([ 'beneform4j-page', 'locale' ], function(Page, locale) {
	Page.create(function($) {
		var me = this, grid = $('#dataList'), dialog = $('#dialog'), apis = {
			list : 'list',
			detail : 'detail'
		};
		
		var goReturn =function(){
			var form = $('#detailForm');
			$.post($b.context.root
					+ "/ffp/cashmanagement/return_refund/doReturn",
					form.serialize(),function(data){
					if(data==='OK'){
						$b.Msg.alert(locale.operate.doOk);
					}else{
						$b.Msg.alert(locale.operate.doFail);
					}	
			})
			me.closeDialog();
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
						title : locale.ffp.cashmanagement.return_refund.jnlNo,
						width : 100,
						sortable : true,
						formatter : function(value, row, index) {
							var href = me.goToDetail(row.jnlNo, value);
							return href;
						}
					}, ] ],
					columns : [ [ {
						field : locale.ffp.cashmanagement.return_refund.debtorName,
						title : locale.ffp.cashmanagement.return_refund.debtorName,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : locale.ffp.cashmanagement.return_refund.debtorAcctno,
						title : locale.ffp.cashmanagement.return_refund.debtorAcctno,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : locale.ffp.cashmanagement.return_refund.creditorName,
						title : locale.ffp.cashmanagement.return_refund.creditorName,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : locale.ffp.cashmanagement.return_refund.creditorAcctno,
						title : locale.ffp.cashmanagement.return_refund.creditorAcctno,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : locale.ffp.cashmanagement.return_refund.settlementAmt,
						title : locale.ffp.cashmanagement.return_refund.settlementAmt,
						width : 100,
						align : 'center',
						sortable : true
					}, {
						field : locale.ffp.cashmanagement.return_refund.settlementDate,
						title : locale.ffp.cashmanagement.return_refund.settlementDate,
						width : 100,
						align : 'center',
						sortable : true
					} ] ]
				});

				$(".doQuery").on("click", me.doQuery);
				$b.Msg.closeProgress();
				// 绑定事件
				/*
				 * $(".Action").find("a.DetailBtn").on("click", function(){
				 * me.selectOne(grid, me.showDetail);
				 * }).end().find("a.DeleteBtn").on("click", function(){
				 * me.selectRows(grid, me.doDelete); });
				 */
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

			goToDetail : function(jnlNo, value) {
				return '<a style="color:blue" href="#">' + value + '</a>';
			},
			/**
			 * 发起退款
			 */
			doReturn : function() {
				$b.Msg.confirm(locale.ffp.cashmanagement.return_refund.Arefund,goReturn);
				
			},
			
			

			showDetail : function(data) {
				dialog.dialog({
					title : locale.ffp.title.test,
					height : 500,
					width : 600,
					href : $b.context.root
							+ "/ffp/cashmanagement/return_refund/detail?jnlNo="
							+ data.jnlNo + "&txCode=" + data.txCode,
							 
				    buttons : [ {
									text : locale.ffp.cashmanagement.return_refund.refund, size : 'large', handler : me.doReturn
								}, {
									text : locale.button.cancel, size : 'large', handler : me.closeDialog
							} ]
				});
				dialog.dialog("maximize");
			},
		};
	});
});