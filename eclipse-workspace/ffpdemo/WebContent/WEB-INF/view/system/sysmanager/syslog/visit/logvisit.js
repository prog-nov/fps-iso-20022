/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 查询访问日志<br>
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
			optFlagRender = function(v){
				return v === '1' ? '通过' : '拒绝';
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
			            {field : 'sessionId', title : '会话ID', align:'center'},
			            {field : 'userId', title : '用户ID', align:'left'}, 
			            {field : 'userName', title : '用户名称', align:'left', width : 100, sortable : true},
						{field : 'orgId', title : '机构号', align:'left'},
						{field : 'orgName', title : '机构名称', align:'left'},
						{field : 'permId', title : '权限ID', align:'left'},
						{field : 'serverIp', title : '服务端IP', align:'left'},
						{field : 'clientIp', title : '客户端IP', align:'left'},
						{field : 'browser', title : '浏览器', align:'center'},
						{field : 'os', title : '操作系统', align:'center'},
						{field : 'optFlag', title : '授权结果', align:'center', formatter: optFlagRender},
						{field : 'optPath', title : '菜单路径', align:'left'},
						{field : 'optUrl', title : '访问URL', align:'left'},
						{field : 'optDate', title : '操作日期', align:'left'},
						{field : 'optTime', title : '操作时间', align:'left'},
						{field : 'costTime', title : '操作耗时', align:'left'}
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
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