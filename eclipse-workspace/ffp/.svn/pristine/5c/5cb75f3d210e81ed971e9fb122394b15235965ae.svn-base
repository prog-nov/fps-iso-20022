/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 个人参数设置<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
require(['beneform4j-page'],function(Page) {
	
	
	Page.create(function($){
		
		var me = this,
			urls={
				save : $b.context.root + '/portal/setting/updateUserSettings',
				combo : $b.context.root + '/portal/setting/getUserComboData' 
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
				var theme = $("#USER_THEME").combobox("getValue");
				var local = $("#USER_LOCALE").combobox("getValue");
				var form = $('#settingForm');
				if(form.data("defaultLang") != local || form.data("defaultTheme") != theme){
					$b.Base.refresh();
				}
			},
			init : function(){
				
				
				$(".newsBtn").find("a.btn-save").on("click", this.doUpdate);
				
				$(".easyui-combobox").each(function(i,o){
					$b.Submit.ajaxSubmit(urls.combo,{"paramCode" : o.id},function(data){
							$("#" + o.id).combobox({
		   	   					data:data,
		   	   					required:true,
		   	   					valueField: 'dataCode',
		   	   					textField: 'dataText'
		   	   				});
					});
				})
				
				var form = $('#settingForm');
				form.data("defaultLang",$("#USER_LOCALE").combobox("getValue"));
				form.data("defaultTheme",$("#USER_THEME").combobox("getValue"));
			}
		}
	})
	
	});