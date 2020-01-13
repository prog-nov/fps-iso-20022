/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 操作指引管理<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-3<br>
 */
require(['beneform4j-treepage'], function(Page) {
	Page.create(function($){
		var me = this,tree,
		listUrl = '/guide/list',
		listEdit = '/guide/toEdit';
		return {
			init : function(){
				//动态加载数据库树
				tree = $b.App.dynamicTree('#forms_tree',server_consts.root + listUrl,me.callback);
				//绑定事件
				$("#searchForm").find(".Action").unbind("click").bind("click",function() {
						var searUrl = encodeURI(server_consts.root + listUrl + '?menuName=' + $("input[name='menuName']").val());
						searUrl = encodeURI(searUrl);
						//刷新树
						$b.App.dynamicTree(tree , searUrl,me.callback);
				});
			},
		  	callback : function(node){
				//打开树节点
				if("1" == node.attributes){
					$b.Msg.openProgress();
					$('#guid-layout').layout('panel','center').panel('refresh',server_consts.root + listEdit + '?menuId=' + node.id);
				}else{
					$('#guid-layout').layout('panel','center').panel('refresh',server_consts.root + listEdit);
				}
			 }
		};
	});
});