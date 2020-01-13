///**
// * Copy Right Information : Forms Syntron <br>
// * Project : 四方精创 Java EE 开发平台 <br>
// * Description : 用户登录<br>
// * Author : Kingdom <br>
// * Version : 1.0.0 <br>
// * Since : 1.0.0 <br>
// * Date : 2016-12-15<br>
// */
require(['common','rsa','beneform4j-page','easyui-base'], function($b,$rsa,Page) {
	Page.create(function($){
		var me = this,
			form = '#loginform';
		    toVaild = function(){
			if ($(form).form('validate'))
			{
				var options = {"closeProgress":false,"data":{"userPwd" : $.trim($('#userPwdDisplay').val())}};
				$rsa.getEncrypted($b.context.root + "/getKey",options,
						function(data){
							$("#userPwd").val(data.userPwd);
							 $("#userPwdDisplay").val("******");
							 $(form).submit();
						}		
						);
			}
		};
		
		return {
			init :  function(){
				$('#userId').validatebox({ required: true });
				$('#userPwdDisplay').validatebox({ required: true });
				$(form).find('div.login-button input:only-child').on('click', toVaild);
				$("#userId").focus();
				$("#userId,#userPwdDisplay").on("keydown",function(event){
					//按下回车键
					if(event.keyCode==13)
					{
						toVaild();
					}
				});
				$b.Msg.closeProgress();
			}
		};
	});
});