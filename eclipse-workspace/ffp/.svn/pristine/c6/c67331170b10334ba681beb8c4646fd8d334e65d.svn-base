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
		var me = this,
		//使用示例显示默认的图标
		editor = $b.App.iniKindEditor("content"),
		
		//去掉所有的图标
		//editor = $b.App.iniKindEditor("content",[]),
		
		//加自己需要的图标
		//editor = $b.App.iniKindEditor("content",['undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste']),
		form = $("#form"),
		savePath = $b.context.root+'/guide/edit';
		return {
			init : function(){
				$b.Msg.closeProgress();
				//绑定提交事件
				$("#submit").on("click", me.submitForm);
				if(form.find('#menuFlag').val() === '1'){
					$('.bf4j-btn').show();
				} else {
					$('.bf4j-btn').hide();
				}
			},
			submitForm:function() {
				$("#guideContent").val(editor.html());
				var params = {url : savePath};
				$b.Submit.ajaxSubmitForm(form, params);
			}
		};
	});
});