<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>系统维护时间</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
			<!-- 导航页面 -->
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			<!-- 列表展示 -->
			<div class="bf4j-group" >
				<table id="dataList" class="bf4j-grid-auto"></table>
			</div>
			<div id="dialog" class="bf4j-esc"></div>
			<div class="bf4j-group">
				<div class="bf4j-line">
					<div class="bf4j-cell bf4j-c12 bf4j-right">
						<a class="bf4j-btn-2 gotoUpdate" title="<spring:message code='ffp.systemmaintenance.cutoffsetting.edit.title'/>">
							<i class="bf4j-icon-btn-edit"></i><spring:message code='ffp.systemmaintenance.cutoffsetting.edit.button'/></a>
					</div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="cutoff{min}.js"/>'></script>
</body>
</html>
