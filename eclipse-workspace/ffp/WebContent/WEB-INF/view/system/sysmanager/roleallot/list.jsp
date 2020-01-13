<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.roleallot.title.roleallotManager" /></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!-- 查询表单-->
				<form id="searchForm" method="post">
					<div class="bf4j-group">
						<div class="bf4j-group-content">
							<div class="bf4j-line">
								<div class="bf4j-cell bf4j-c4">
									<label><spring:message code="system.sysmanager.roleallot.roleallotName" /></label>
								</div>
								<div class="bf4j-cell bf4j-c8">
									<span class="bf4j-input">
										<input type="text" name="roleAllotName" class="easyui-textbox" style="width: 90%" />
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="bf4j-group">
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-c12 bf4j-center">
								<a class="bf4j-btn doQuery"  title='<spring:message code="button.search" />'><i class="bf4j-icon-btn-search"></i><spring:message code="button.submitSearch" /></a>
							</div>
						</div>
					</div>
				</form>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-right">
							<a class="bf4j-btn-2 gotoAdd"  title='<spring:message code="system.sysmanager.roleallot.addroleallot" />'>
								<i class="bf4j-icon-btn-add"></i><spring:message code="system.sysmanager.roleallot.addroleallot" /></a>
							<a class="bf4j-btn-2 doDelete"  title='<spring:message code="button.mulitDelete" />'>
								<i class="bf4j-icon-btn-delete"></i><spring:message code="button.mulitDelete" />
							</a>
						</div>
					</div>
				</div>
				<!-- 列表展示 -->
				<div class="bf4j-group">
					<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
				<div id="dialog">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="role-allot{min}.js"/>'></script>
</body>
</html>
