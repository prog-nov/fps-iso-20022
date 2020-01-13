<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/DTD/strict.dtd">
<html>
<head>
<meta http-equiv="Expires" content="0">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<title><spring:message code="system.login" /></title>
<jsp:include page="/WEB-INF/view/common/includer/login-includer.jsp"></jsp:include>
<script type="text/javascript">
require(['common','rsa','easyui-base'],function($b,$rsa){
	
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
                    <form name="loginform" id="loginform" action="login" method="POST" > 
                         <div class="textbox-user">
                            <input type="text" name="userId" id="userId" 
                            	placeholder="<spring:message code="login.userId.tips"/>" />
                        </div>
                        <div class="textbox-pwd">
                        	<input type="password" name="userPwdDisplay" id="userPwdDisplay" 
                            	placeholder="<spring:message code="login.password.tips"/>" />
                            	<input type="hidden"  name="userPwd" id="userPwd"/>
                        </div>
                        <div class="login-button">
                            <input type="button" value="<spring:message code="login.submit"/>"  />
                        </div>
                        <c:if test="${not empty login}">
                       	 <div class="message">[<c:out value="${login.code}"></c:out>]<c:out value="${login.message}"></c:out></div>
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
