/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 查询登录日志<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
require(['beneform4j-page'], function(Page) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list'
			},
			today = $b.Date.format(new Date(),'yyyyMMdd'),
			logoutFlagRender = function(v){
				return v === '2' ? '用户登出' : v === '3' ? '超时登出' : '';
			};
		
		return {
			init :  function(){
			    me.setOrganField($('#searchForm'));
			    $('input[name=beginDate]').datebox({
			    	formatter : function(date){
			    		return $b.Date.format(date,'yyyyMMdd');
			    	}
			    }).datebox('setValue', today.substring(0,6)+'01');
			    $('input[name=endDate]').datebox({
			    	formatter : function(date){
			    		return $b.Date.format(date,'yyyyMMdd');
			    	}
			    }).datebox('setValue', today);
			    
				grid.datagrid({
					url : apis.list,
					idField : 'userId',
					pagination : true,
					rowTool: false,
					queryParams: $.serializeObject($('#searchForm')),
					columns : [ [ 
			            {field : 'sessionId', title : '会话ID', align:'left'},
			            {field : 'userId', title : '用户ID', align:'left'}, 
			            {field : 'userName', title : '用户名称', align:'left', width : 100, sortable : true},
						{field : 'orgId', title : '机构号', align:'left'},
						{field : 'orgName', title : '机构名称', align:'left'},
						{field : 'serverIp', title : '服务端IP', align:'left'},
						{field : 'clientIp', title : '客户端IP', align:'left'},
						{field : 'browser', title : '浏览器', align:'center'},
						{field : 'os', title : '操作系统', align:'center'},
						{field : 'loginDate', title : '登录日期', align:'left'},
						{field : 'loginTime', title : '登录时间', align:'left'},
						{field : 'logoutDate', title : '登出日期', align:'left'},
						{field : 'logoutTime', title : '登出时间', align:'left'},
						{field : 'logoutFlag', title : '登出类型', align:'center',formatter: logoutFlagRender }
			        ] ]
				});
				
				//绑定事件
				$('.doQuery').on('click', me.doQuery);
			    $b.Msg.closeProgress();
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