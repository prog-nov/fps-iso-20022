<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/DTD/strict.dtd">
<%@ taglib prefix="b" uri="http://www.formssi.com/beneform4j/tags" %>
<html>
<head>
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link href='<b:path url="{root}/resources/beneform4j/css/theme/boc-red/easyui/easyui.css"/>' rel="stylesheet" >
<link href='<b:path url="{root}/resources/beneform4j/css/theme/boc-red/login/tmpl1/login.css"/>' rel="stylesheet" >
<!-- 引入requirejs -->
<jsp:include page="/WEB-INF/view/common/includer/requirejs-includer.jsp"></jsp:include>
<title>Login1</title>
<script type="text/javascript">
require(['beneform4j','rsa','easyui-base'],function($b,$rsa){
	$b.Msg.closeProgress();
	var form = '#loginform';
	var toVaild = function(){
		if ($(form).form('validate'))
		{

			var options = {"closeProgress":false,"data":{"userPwd" : $.trim($('#userPwdDisplay').val())}};
			$rsa.getEncrypted($b.context.root + "/getKey",options,
					function(data){
						$("#userPwd").val(data.userPwd);
						 $("#userPwdDisplay").val("******");
						 $("#loginform").submit();
					}		
					);
		}
	};
	
	jQuery(function($){
		$(form).find('div.login-button input:only-child').on('click', toVaild);
		$("#userId").focus();
		$("#userId,#userPwdDisplay").on("keydown",function(event){
			//按下回车键
			if(event.keyCode==13)
			{
				toVaild();
			}
		});
	});
});
	
</script>
</head>
<body>
	 <div class="max-div">
        <div class="top">
            <div class="top-content">
                <div class="logo"></div>
            </div>
        </div>
        <div class="center">
            <div class="center-content">
                <div class="left"></div>
                <!--登录面板区域-->
                <div class="right">
                    <div class="login-header">
                     	<spring:message code="login.title"/>
                    </div>
                    <form name="loginform" id="loginform" action="${pageContext.request.contextPath}/login" method="POST" > 
                         <div class="textbox-user">
                            <input type="text" type="text" name="userId" id="userId" 
                            	placeholder="<spring:message code="login.userId.tips"/>" class="easyui-validatebox" data-options="required:true" value="dev" />
                        </div>
                        <div class="textbox-pwd">
                        	<input type="password" name="userPwdDisplay" id="userPwdDisplay" value="111111"
                            	placeholder="<spring:message code="login.password.tips"/>" class="easyui-validatebox" data-options="required:true" />
                            	<input type="hidden"  name="userPwd" id="userPwd" />
                        </div>
                        <div class="login-button">
                            <input type="button" value="<spring:message code="login.submit"/>"  />
                        </div>
                        <c:if test="${not empty login}">
                       	 <div class="message">[${login.code}]${login.message}</div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer" >
             <spring:message code="login.copyright" htmlEscape="false"></spring:message>
        </div>
    </div>
</body>
</html>
