<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>参数定义</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true"
		style="padding-top: 10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp">
				<!--导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!--查找页面 -->
				<jsp:include page="./find.jsp"></jsp:include>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-right">
							<a class="bf4j-btn-2 gotoAdd" title='新增参数'> 
							 <i class="bf4j-icon-btn-add"></i>添加参数</a> 
							<a class="bf4j-btn-2 gotoUpdate" title='编辑参数'> 
							 <i class="bf4j-icon-btn-edit"></i>修改参数</a> 
							<a class="bf4j-btn-2 doDelete" title='删除参数'> 
							 <i class="bf4j-icon-btn-delete"></i>删除参数</a>
						</div>
					</div>
				</div>
				<!-- 显示参数表  -->
				<div class="bf4j-group">
					<table id="dataList" class="bf4j-grid-auto"></table>
				</div>
				<div id="dialog" class="bf4j-esc"></div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="define-param.js"/>'></script>
</body>
</html>