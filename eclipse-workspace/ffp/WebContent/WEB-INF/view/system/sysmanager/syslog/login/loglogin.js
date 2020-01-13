/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 查询登录日志<br>
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
			logoutFlagRender = function(v){
				return v === '2' ? locale.system.sysmanager.syslog.login.logout : v === '3' ? locale.system.sysmanager.syslog.login.TimeoutLogout : '';
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
			            {field : 'sessionId', title : locale.system.sysmanager.syslog.login.SessionID, align:'left'},
			            {field : 'userId', title : locale.system.sysmanager.syslog.login.userId, align:'left'}, 
			            {field : 'userName', title : locale.system.sysmanager.syslog.login.username, align:'left', width : 100, sortable : true},
						{field : 'orgId', title : locale.system.sysmanager.syslog.login.agencyNumber, align:'left'},
						{field : 'orgName', title : locale.system.sysmanager.syslog.login.OrganizationNames, align:'left'},
						{field : 'serverIp', title : locale.system.sysmanager.syslog.login.serverIP, align:'left'},
						{field : 'clientIp', title : locale.system.sysmanager.syslog.login.clientIP, align:'left'},
						{field : 'browser', title : locale.system.sysmanager.syslog.login.browser, align:'center'},
						{field : 'os', title : locale.system.sysmanager.syslog.login.operatingSystem, align:'center'},
						{field : 'loginDate', title : locale.system.sysmanager.syslog.login.entryDate, align:'left'},
						{field : 'loginTime', title : locale.system.sysmanager.syslog.login.LogonTime, align:'left'},
						{field : 'logoutDate', title : locale.system.sysmanager.syslog.login.LogOutDate, align:'left'},
						{field : 'logoutTime', title : locale.system.sysmanager.syslog.login.LogOutTime, align:'left'},
						{field : 'logoutFlag', title : locale.system.sysmanager.syslog.login.AppropriateType, align:'center',formatter: logoutFlagRender }
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