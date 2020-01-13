/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 在线用户管理<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
require(['beneform4j-page','locale'], function(Page,locale) {
	Page.create(function($){
		var me = this,
			grid = $('#dataList'),
			dialog = $('#dialog'),
			apis = {
				list : 'list',
				offline : 'offline'
			};
		
		return {
			init :  function(){
			    me.setOrganField($('#searchForm'));
			    
				grid.datagrid({
					url : apis.list,
					idField : 'userId',
					pagination : true,
					rowTool: false,
					columns : [ [ 
			            {field : 'userId', checkbox : true}, 
			            {field : 'sessionId', title : locale.system.sysmanager.onlineuser.SessionID, align:'left'},
			            {field : 'userName', title : locale.system.sysmanager.onlineuser.username, align:'left', width : 100, sortable : true},
						{field : 'orgId', title : locale.system.sysmanager.onlineuser.agencyNumber, align:'left'},
						{field : 'serverIp', title : locale.system.sysmanager.onlineuser.serverIP, align:'left'},
						{field : 'clientIp', title : locale.system.sysmanager.onlineuser.clientIP, align:'left'},
						{field : 'browser', title : locale.system.sysmanager.onlineuser.browser, align:'center'},
						{field : 'os', title : locale.system.sysmanager.onlineuser.operatingSystem, align:'center'},
						{field : 'loginDate', title : locale.system.sysmanager.onlineuser.entryDate, align:'left'},
						{field : 'loginTime', title : locale.system.sysmanager.onlineuser.LogonTime, align:'left'}
			        ] ]
				});
				
				//绑定事件
				$(".doQuery").on("click", me.doQuery);
			    $("a.bf4j-btn-2").filter(".gotoOffline").on("click", function(){//强制下线
			    	var data = me.selectOne(grid);
				    if(data){
				    	me.doOffline(data);
				    }
			    });
			    $b.Msg.closeProgress();
			},
			/**
			 * 执行查询
			 */
			doQuery : function (){
				grid.datagrid('load', $.serializeObject($('#searchForm')));
			},
			
			/**
			 * 强制离线
			 */
			doOffline : function(data){
				var params = {userId : data.userId,sessionId : data.sessionId};
				$b.Submit.ajaxSubmit(apis.offline, params, function(rs){
					grid.datagrid('reload');
					$b.Msg.alert(locale.operate.doOk);
				});
			}
		};
	});
});