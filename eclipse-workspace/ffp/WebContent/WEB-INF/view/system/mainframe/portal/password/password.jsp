<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="portal.password.title"/></title>
</head>
<body>
<form id="form-update-password" class="easyui-form" method="post">
	<div class="bf4j-warp">
		<div class="bf4j-group">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c5">
					<label><spring:message code="portal.password.oldPassword"/></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="input"> 
						<input value="" class="easyui-validatebox" type="password" id="oldPassword" name="oldPassword" data-options="required:true,validType:'length[6,16]'"/>
					</span>
				</div> 
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c5">
					<label><spring:message code="portal.password.newPassword"/></label>
				</div>
				<div class="bf4j-cell bf4j-c6">
					<span class="input"> 
						<input class="easyui-validatebox" type="password" id="password" name="password" data-options="required:true,validType:['length[6,16]','same[\'rePassword\']']"/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c5">
					<label><spring:message code="portal.password.confirmPassword"/></label>
				</div>
				<div class="bf4j-cell bf4j-c7">
					<span class="input"> 
						<input class="easyui-validatebox" type="password" id="rePassword" name="rePassword" data-options="required:true,validType:['length[6,16]','same[\'password\']']"/>
					</span>
				</div>
			</div>
		</div>
		<div class="bf4j-group newsBtn">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c12 bf4j-center">
					<a class="bf4j-btn btn-search"  title="<spring:message code="button.submit"/>"><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit"/></a>
				</div>
			</div>
		</div>
		<div id="passwordDialog" class="esc" ></div>
	</div>
</form> 
<script type="text/javascript" src="<b:path url='{root}/system/mainframe/portal/password/password{min}.js'/>"></script>
</body>
</html>