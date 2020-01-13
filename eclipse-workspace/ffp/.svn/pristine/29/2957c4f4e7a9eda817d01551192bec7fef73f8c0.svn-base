<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.user.title.userManager" /></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 查询页面 -->
			<jsp:include page="./search.jsp"></jsp:include>
			<div class="bf4j-group">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c12 bf4j-right">
						<a class="bf4j-btn-2 gotoDownload" title='<spring:message code="system.sysmanager.button.download" />'>
							<i class="bf4j-icon-btn-download"></i><spring:message code="system.sysmanager.button.download" /></a>
						<a class="bf4j-btn-2 gotoAdd" title='<spring:message code="system.sysmanager.user.addUser" />'>
							<i class="bf4j-icon-btn-add"></i><spring:message code="system.sysmanager.user.addUser" /></a>
						<a class="bf4j-btn-2 gotoUpdate" title='<spring:message code="system.sysmanager.user.updateUser" />'>
							<i class="bf4j-icon-btn-edit"></i><spring:message code="system.sysmanager.user.updateUser" /></a>
						<a class="bf4j-btn-2 doDelete" title='<spring:message code="system.sysmanager.user.delete" />'>
							<i class="bf4j-icon-btn-delete"></i><spring:message code="system.sysmanager.user.delete" /></a>
						<a class="bf4j-btn-2 gotoResetPassword" title=''>
							<i class="bf4j-icon-btn-reset"></i><spring:message code="system.sysmanager.user.resetPass" /></a>
						<a class="bf4j-btn-2 gotoStart" title='<spring:message code="system.sysmanager.user.start" />'>
							<i class="bf4j-icon-btn-start"></i><spring:message code="system.sysmanager.user.start" /></a> 
						<a class="bf4j-btn-2 gotoStop" title='<spring:message code="system.sysmanager.user.close" />'>
							<i class="bf4j-icon-btn-stop"></i><spring:message code="system.sysmanager.user.close" /></a>
<!-- 						<a class="bf4j-btn-2 gotoLock" title="锁定"> -->
<!-- 							<i class="bf4j-icon-btn-lock"></i>锁定</a>  -->
						<a class="bf4j-btn-2 gotoUnlock" title='<spring:message code="system.sysmanager.user.unlock" />'>
							<i class="bf4j-icon-btn-delock"></i><spring:message code="system.sysmanager.user.unlock" /></a>   
						
					</div>
				</div>
			</div>
			<!-- 列表展示 -->
			<div class="bf4j-group" >
				<table id="dataList" class="bf4j-grid-auto"></table>
			</div>
			<div id="dialog" class="bf4j-esc">
			</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="user{min}.js"/>'></script>
</body>
</html>
