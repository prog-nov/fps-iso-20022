<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>平台管理中心-四方精创</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp">
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!-- 搜索块 -->
				<jsp:include page="/WEB-INF/view/demo/demo2/demo2-search.jsp"></jsp:include>
				<div class="bf4j-group Action">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-center">
							<a class="bf4j-btn SearchBtn" 
						title='<spring:message code="button.submitSearch"/>' ><i class="bf4j-icon-btn-search" ></i><spring:message code="button.submitSearch"/></a>
						</div>
					</div>
				</div>
				<div class="bf4j-group Action">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c3 bf4j-left">
							<a class="bf4j-btn-2 MoreSearchBtn" title="<spring:message code="button.moreSearch"/>"><i class="bf4j-icon-btn-add"></i><spring:message code="button.moreSearch"/></a>
						</div>
						<div class="bf4j-cell bf4j-c9 bf4j-right">
							<a class="bf4j-btn-2 AddBtn" 
							title="<spring:message code="demo.button.addproduct"/>" ><i class="bf4j-icon-btn-add" ></i><spring:message code="demo.button.addproduct"/></a>
							<a class="bf4j-btn-2 DeleteBtn" 
							title="<spring:message code="button.mulitDelete"/>" ><i class="bf4j-icon-btn-delete" ></i><spring:message code="button.mulitDelete"/></a>
							<a class="bf4j-btn-2 EditBtn" 
							title="<spring:message code="button.update"/>" ><i class="bf4j-icon-btn-edit" ></i><spring:message code="button.update"/></a>
						</div>
					</div>
				</div>
				<div class="bf4j-group">
					<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
				<!-- 弹出层 -->
				<div id="dialog" class="bf4j-esc"></div>
				<!-- 搜索条件弹出层 -->
				<div id="searchDialog" class="bf4j-esc"></div>
				<!-- 右键菜单 -->
				<div id="popupMenu" class="easyui-menu"
					style="width: 50px; display: none;">
					<div class="gotoDetail" data-options="iconCls:'bf4j-icon-right-look'"><spring:message code="button.look"/></div>
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'"><spring:message code="button.update"/></div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'"><spring:message code="button.delete"/></div>
				</div>
			</div>
		</div>
	</div>
		<script type="text/javascript" src="<b:path url="demo2{min}.js"/>"></script>
</body>
</html>
