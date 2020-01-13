<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="guide.list.head.title"/></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit : true,border : false" id="guid-layout">
		<div data-options="region:'north',border:false" style="height: 50px; overflow: hidden;">
			<div class="bf4j-warp" >
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			</div>
		</div>
		<div data-options="region:'west',border:true" style="width: 235px; overflow: hidden;padding-top: 10px;margin-left:10px;">
			<div class="bf4j-warp" >
				<div class="bf4j-group" >
					<form id="searchForm">
						<span><spring:message code="guide.list.menuName"/></span>
						<input name="menuName" placeholder="查询菜单名称" class="span2" style="width:100px"  />
						<a class="bf4j-btn-3 Action" href="javascript:void(0)" title='<spring:message code="button.search"/>' >
						<i class="bf4j-icon-btn-search" ></i></a>
					</form>
				</div>
				<div class="bf4j-group" >
					<ul id="forms_tree"></ul>
				</div>
			</div>
		</div>
		<div data-options="region:'center',border:false,href:'${pageContext.request.contextPath}/guide/toEdit?menuId'" style="border-left:1px solid #E9E9E9;" >
		</div>
	</div>
	<script type="text/javascript" src='<c:url value="/"/>system/sysmanager/guide/list.js'></script>
</body>
</html>