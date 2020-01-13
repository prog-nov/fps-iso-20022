<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="document.list.title"/></title>
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
						<a class="bf4j-btn-2 gotoAdd" title='<spring:message code="document.list.addtitle"/>'>
							<i class="bf4j-icon-btn-add"></i><spring:message code="document.list.addtitle"/></a>
						<a class="bf4j-btn-2 gotoUpdate" title='<spring:message code="document.list.updatetitle"/>'>
							<i class="bf4j-icon-btn-reset"></i><spring:message code="document.list.updatetitle"/></a>
						<a class="bf4j-btn-2 gotoDownload" title='<spring:message code="document.list.downloadtitle"/>'>
							<i class="bf4j-icon-btn-download"></i><spring:message code="document.list.downloadtitle"/></a>
						<a class="bf4j-btn-2 gotoStop" title='<spring:message code="document.list.lock"/>'>
							<i class="bf4j-icon-btn-lock"></i><spring:message code="document.list.lock"/></a> 
						<a class="bf4j-btn-2 gotoStart" title='<spring:message code="document.list.unlock"/>'>
							<i class="bf4j-icon-btn-delock"></i><spring:message code="document.list.unlock"/></a>
						<a class="bf4j-btn-2 doDelete" title='<spring:message code="document.list.deletfile"/>'>
							<i class="bf4j-icon-btn-offline"></i><spring:message code="document.list.deletfile"/></a>
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
	<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/document/document{min}.js'></script>
</body>
</html>
