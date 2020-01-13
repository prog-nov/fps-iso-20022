<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="news.list.head.title"/></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 查询页面 -->
			<jsp:include page="./search.jsp"></jsp:include>
			<div class="bf4j-group Action">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c12 bf4j-right">
						<a class="bf4j-btn-2 AddBtn" 
							title='<spring:message code="news.list.button.add"/>'>
								<i class="bf4j-icon-btn-add"></i><spring:message code="news.list.button.add"/></a> 
						<a class="bf4j-btn-2 EditBtn" 
							title='<spring:message code="news.list.button.edit"/>'>
								<i class="bf4j-icon-btn-edit"></i><spring:message code="news.list.button.edit"/></a> 
						<a class="bf4j-btn-2 DeleteBtn" 
							title='<spring:message code="news.list.button.del"/>'>
								<i class="bf4j-icon-btn-delete"></i><spring:message code="news.list.button.del"/></a>
					</div>
				</div>
			</div>
			<!-- 列表展示 -->
			<div class="bf4j-group" >
				<table id="newsList" class="bf4j-grid-auto" ></table>
			</div>
			<div id="dialog"></div>
			</div>
		</div>
	</div>
	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div class="viewDetail" data-options="iconCls:'find'"><spring:message code="news.list.button.detail"/></div>
		<div class="updateDetail" data-options="iconCls:'pencil'"><spring:message code="news.list.button.edit"/></div>
	</div>
	<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/news/news.js'></script>
</body>
</html>
