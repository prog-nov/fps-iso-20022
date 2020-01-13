<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.cache.title.cacheManager" /></title>
<style type="text/css">
.datagrid-header-expander{
	width: 48px !important;
}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-right">
							<a class="bf4j-btn-2 doQuery"  title='<spring:message code="button.search" />'>
								<i class="bf4j-icon-btn-delete"></i><spring:message code="button.delete" /></a>
							<a class="bf4j-btn-2 doClearAll"  title='<spring:message code="system.sysmanager.button.clearRefresh" />'>
								<i class="bf4j-icon-btn-delete"></i><spring:message code="system.sysmanager.button.clearRefresh" /></a>
						</div>
					</div>
				</div>
				<!-- 列表展示 -->
				<div class="bf4j-group" >
					<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="cache{min}.js"/>'></script>
</body>
</html>
