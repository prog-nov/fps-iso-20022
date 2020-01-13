<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.onlineuser.title.onlineUserManager" /></title>
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
						<a class="bf4j-btn-2 gotoOffline" title='<spring:message code="system.sysmanager.onlineUser.offline" />'>
							<i class="bf4j-icon-btn-offline"></i><spring:message code="system.sysmanager.onlineUser.offline" /></a>
					</div>
				</div>
			</div>
			<!-- 列表展示 -->
			<div class="bf4j-group" >
				<table id="dataList" class="bf4j-grid-auto"></table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="useronline{min}.js"/>'></script>
	</div>
</body>
</html>
