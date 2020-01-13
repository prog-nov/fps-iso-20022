/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 查询访问日志<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list'
			},
			today = $b.Date.format(new Date(),'yyyyMMdd'),
			optFlagRender = function(v){
				return v === '1' ? locale.system.sysmanager.syslog.visit.through : locale.system.sysmanager.syslog.visit.Refused;
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
			            {field : 'sessionId', title : locale.system.sysmanager.syslog.visit.SessionID, align:'center'},
			            {field : 'userId', title : locale.system.sysmanager.syslog.visit.userId, align:'left'}, 
			            {field : 'userName', title : locale.system.sysmanager.syslog.visit.username, align:'left', width : 100, sortable : true},
						{field : 'orgId', title : locale.system.sysmanager.syslog.visit.agencyNumber, align:'left'},
						{field : 'orgName', title : locale.system.sysmanager.syslog.visit.OrganizationNames, align:'left'},
						{field : 'permId', title : locale.system.sysmanager.syslog.visit.AuthorizationID, align:'left'},
						{field : 'serverIp', title : locale.system.sysmanager.syslog.visit.serverIP, align:'left'},
						{field : 'clientIp', title : locale.system.sysmanager.syslog.visit.clientIP, align:'left'},
						{field : 'browser', title : locale.system.sysmanager.syslog.visit.browser, align:'center'},
						{field : 'os', title : locale.system.sysmanager.syslog.visit.operatingSystem, align:'center'},
						{field : 'optFlag', title : locale.system.sysmanager.syslog.visit. authorizationResult, align:'center', formatter: optFlagRender},
						{field : 'optPath', title : locale.system.sysmanager.syslog.visit.menuPath, align:'left'},
						{field : 'optUrl', title : locale.system.sysmanager.syslog.visit.accessURL, align:'left'},
						{field : 'optDate', title : locale.system.sysmanager.syslog.visit.operationDate, align:'left'},
						{field : 'optTime', title : locale.system.sysmanager.syslog.visit.operatingTime, align:'left'},
						{field : 'costTime', title : locale.system.sysmanager.syslog.visit.operatingTime2, align:'left'}
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