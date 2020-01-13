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
			<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
			
			<jsp:include page="./search.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="systemstat{min}.js"/>'></script>
</body>
</html>
