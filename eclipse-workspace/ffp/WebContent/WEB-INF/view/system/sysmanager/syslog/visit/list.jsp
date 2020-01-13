<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.syslog.visit.title.visitLogSearch" /></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!-- 查询页面 -->
				<jsp:include page="./search.jsp"></jsp:include>
				<!-- 列表展示 -->
				<div class="bf4j-group" >
					<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="logvisit{min}.js"/>'></script>
</body>
</html>
