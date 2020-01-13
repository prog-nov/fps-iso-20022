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
			grid = $('#userDataList'),
			dialog = $('#userDialog'),
			apis = {
				searchTaskUser : 'searchTaskUser'
			};
		
		return {
			init :  function(){
			    me.setOrganField($('#userSearchForm'));
			    
				grid.datagrid({
					url : apis.searchTaskUser,
					idField : 'userId',
					pagination : true,
					rowTool: false,
					frozenColumns : [ [ 
	    			   	{field : 'userId', checkbox : true}, 
	    			   	{field : 'userName', title : locale.system.sysmanager.taskrule.user.username, align:'left', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
						{field : 'nickName', title : locale.system.sysmanager.taskrule.user.nickName, align:'center'}, 
						{field : 'userStatus', title : locale.system.sysmanager.taskrule.user.status, align:'center',
							formatter: function(value){
								if (value == 1){
									return locale.system.sysmanager.taskrule.user.startUsing;
								} else {
									return locale.system.sysmanager.taskrule.user.stop;
								}
							}
						},
						{field : 'orgId', title : locale.system.sysmanager.taskrule.user.agencyNumber, align:'center'}
			        ] ]
				});
				$('a.doUserQuery').on("click", me.doUserQuery);
				
			    $b.Msg.closeProgress();
			},
			
			/**
			 * 执行查询
			 */
			doUserQuery : function (){
				grid.datagrid('load', $.serializeObject($('#userSearchForm')));
			}
			
		};
	});
});