///**
// * Copy Right Information : Forms Syntron <br>
// * Project : 四方精创 Java EE 开发平台 <br>
// * Description : 单值参数设置<br>
// * Author : Kingdom <br>
// * Version : 1.0.0 <br>
// * Since : 1.0.0 <br>
// * Date : 2016-5-5<br>
// */
require(['beneform4j-page'],function(Page) {
	
	Page.create(function($){
		
		var me = this,
			urls={
				save : $b.context.root + '/systemmanage/param/single/updateParamValue',
				combo : $b.context.root + '/systemmanage/param/single/getSingleParamComboData'
		};
		
		return {
			doUpdate : function(){
				var form = $('#settingForm'),
					params = {url : urls.save},
					codes = $("[name=paramCode]"),
					values = $("[name=paramValue]"),
					paramCodes = [],
					paramValues = [];
				$.each(codes, function(i, n){
					paramCodes.push($(n).val());
				});
				$.each(values, function(i, n){
					paramValues.push($(n).val());
				});
				if(form.form('validate')){
					$b.Submit.ajaxSubmit(urls.save,{'paramCode' : paramCodes,'paramValue' : paramValues},me.updateCallback);
				}
			},
			updateCallback : function (){
				$b.Msg.alert($b.Base.i18n('operate.doOk'));
			},
			init : function(){
				$(".newsBtn").find("a.bf4j-btn-save").on("click", this.doUpdate);
				parent.$.messager.progress('close');
			}
		}
	})
	
	});