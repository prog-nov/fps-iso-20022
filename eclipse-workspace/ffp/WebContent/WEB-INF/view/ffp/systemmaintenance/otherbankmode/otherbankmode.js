require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			apis = {
				list : 'list'
			};
		
		return {
			init :  function(){
				grid.datagrid({
					url : apis.list,
					pagination : true,
					rowTool: false,
					
					columns : [ [ 
						{field : 'clearingCode', editor:'text', title : locale.ffp.cashmanagement.otherbankmode.clearingCode, align:'center'}, 
						{field : 'receiptMode', editor:'text', title : locale.ffp.cashmanagement.otherbankmode.receiptMode, align:'center'},
						{field : 'lastUpdateTs', editor:'text', title : locale.ffp.cashmanagement.otherbankmode.lastUpdateTs, align:'center',
							formatter: function (value, rowData, rowIndex) {
								if (value == undefined || value == '') {
							        return "";
							    }
							    var dt, month, day, hour, minute, second;
							    dt = (value instanceof Date ? value : new Date(value));
							    month=("00"+(dt.getMonth()+1)).substr(String(dt.getMonth()+1).length);
							    day=("00"+dt.getDate()).substr(String(dt.getDate()).length);
							    hour=("00"+dt.getHours()).substr(String(dt.getHours()).length);
							    minute=("00"+dt.getMinutes()).substr(String(dt.getMinutes()).length);
							    second=("00"+dt.getSeconds()).substr(String(dt.getSeconds()).length);
							    
							    return dt.getFullYear()+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
							}
						}
			        ] ]
				});
				
			    $b.Msg.closeProgress();
			},
		};
	});
});