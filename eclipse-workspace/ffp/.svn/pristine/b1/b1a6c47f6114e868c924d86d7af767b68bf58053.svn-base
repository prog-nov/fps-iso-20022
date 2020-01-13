<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.maintenance.title.roleRelMain" /></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 --> 
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 查询页面 -->
			<jsp:include page="./search.jsp"></jsp:include>
			<!-- 添加  修改  删除按钮 -->
			<div class="bf4j-group">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c12 bf4j-right">	
						<a class="bf4j-btn-2 gotoAdd" title='<spring:message code="button.add" />'>
							<i class="bf4j-icon-btn-add"></i><spring:message code="button.add" /></a>
						<a class="bf4j-btn-2 gotoUpdate" title='<spring:message code="button.update" />'>
							<i class="bf4j-icon-btn-edit"></i><spring:message code="button.update" /></a>
						<a class="bf4j-btn-2 doDelete" title='<spring:message code="button.delete" />'>
							<i class="bf4j-icon-btn-delete"></i><spring:message code="button.delete" /></a>
					</div>
				</div>
			</div>
			<!-- 列表展示 --> 
			<div class="bf4j-group" >
				<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
				<div id="dialog" class="bf4j-esc">
				</div>
			<!--列表行修改 -->
				<div id="rowMenu" class="easyui-menu" style="width: 50px; display: none;">
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'"><spring:message code="button.update" /></div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'"><spring:message code="button.delete" /></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="maintenance.js"/>'></script>
</body>
</html>