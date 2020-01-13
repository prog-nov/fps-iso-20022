<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>用户管理</title>
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
						<a class="bf4j-btn-2 gotoDownload" title="下载">
							<i class="bf4j-icon-btn-download"></i>下载</a>
						<a class="bf4j-btn-2 gotoAdd" title="添加用户">
							<i class="bf4j-icon-btn-add"></i>添加用户</a>
						<a class="bf4j-btn-2 gotoUpdate" title="修改用户">
							<i class="bf4j-icon-btn-edit"></i>修改用户</a>
						<a class="bf4j-btn-2 doDelete" title="删除">
							<i class="bf4j-icon-btn-delete"></i>删除</a>
						<a class="bf4j-btn-2 gotoResetPassword" title="重置密码">
							<i class="bf4j-icon-btn-reset"></i>重置密码</a>
						<a class="bf4j-btn-2 gotoStart" title="启用">
							<i class="bf4j-icon-btn-start"></i>启用</a> 
						<a class="bf4j-btn-2 gotoStop" title="停用">
							<i class="bf4j-icon-btn-stop"></i>停用</a>
<!-- 						<a class="bf4j-btn-2 gotoLock" title="锁定"> -->
<!-- 							<i class="bf4j-icon-btn-lock"></i>锁定</a>  -->
						<a class="bf4j-btn-2 gotoUnlock" title="解锁">
							<i class="bf4j-icon-btn-delock"></i>解锁</a>   
						
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
	</div>
	<script type="text/javascript" src='<b:path url="user{min}.js"/>'></script>
</body>
</html>
