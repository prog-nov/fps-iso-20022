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
			apis = {
				queryCcy : 'queryCcy'
			};
		
		return {
			init :  function(){
				me.setComboFields($('#searchForm'));
			    me.setOrganField($('#searchForm'));
			    
			    $(".doQuery").on("click", me.doQuery);
			    $b.Msg.closeProgress();
			},
			/**
			 * 执行查询
			 */
			doQuery : function (){
				var form = $('#searchForm');
				if(!form.form('validate')){
					return false;
				}
				
				var params = {url : apis.queryCcy};
				$b.Submit.ajaxSubmitForm(form, params, me.showBalance);
			},
			
			showBalance : function (data){
				$('#searchForm').form('load', data);
				$b.Msg.closeProgress();
			},
		};
	});
});