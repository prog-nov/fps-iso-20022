/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 个人密码修改<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-4<br>
 */
require(['rsa','beneform4j-page'],function($rsa,Page) {
	
	
	var me = Page.create({
		doUpdate : function(){
			var form = $('#form-update-password');
				if(form.form('validate')){
					var options = {"closeProgress":false,"data":{'oldPassword' : $.trim($('#oldPassword').val()),'newPassword' : $.trim($('#password').val())}};
					$rsa.getEncrypted($b.context.root + "/getKey",options,
					function(retData){
						 $b.Submit.ajaxSubmit($b.context.root + '/portal/password/updatePersonalPassword',retData,function(data){
							 if(data == 'ok'){
								$b.Msg.alert($b.Base.i18n('operate.doOk')); 
							}else{
								$b.Msg.error(data);
							}
						 });
					}		
					);
				}
		},
		init : function(){
			$(".newsBtn").find("a.btn-search").on("click", this.doUpdate);
		}
		
	});
});