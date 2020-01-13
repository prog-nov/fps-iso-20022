/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户管理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-2<br>
 */
require(['beneform4j-page'], function(Page) {
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
	    			   	{field : 'userName', title : '用户名称', align:'left', width : 100, sortable : true}
	    			] ],
					columns : [ [ 
						{field : 'nickName', title : '用户昵称', align:'center'}, 
						{field : 'userStatus', title : '状态', align:'center',
							formatter: function(value){
								if (value == 1){
									return '启用';
								} else {
									return '停用';
								}
							}
						},
						{field : 'orgId', title : '机构号', align:'center'}
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