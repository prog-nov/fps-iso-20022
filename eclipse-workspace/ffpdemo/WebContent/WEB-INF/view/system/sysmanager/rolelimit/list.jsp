<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>角色约束管理</title>
<style type="text/css">
.datagrid-group,.datagrid-group-title{
	height: 38px !important;
	line-height: 38px !important;
}
.datagrid-row-expander{
	height: 35px !important;
	line-height: 35px !important;
}
.datagrid-group i{
	padding: 13px;
	vertical-align:top;
	font-size:0px;
	margin:0 5px;
	cursor: pointer;
}
</style>
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
									<label>角色名称：</label>
								</div>
								<div class="bf4j-cell bf4j-c8">
									<span class="bf4j-input">
										<input type="text" name="roleName" class="easyui-textbox" style="width: 90%" />
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="bf4j-group">
						<div class="bf4j-line">
							<div class="bf4j-cell bf4j-c12 bf4j-center">
								<a class="bf4j-btn doQuery"  title="查询"><i class="bf4j-icon-btn-search"></i>提交查询</a>
							</div>
						</div>
					</div>
				</form>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-right">
							<a class="bf4j-btn-2 gotoAdd"  title="添加角色约束">
								<i class="bf4j-icon-btn-add"></i>添加角色约束</a>
							<a class="bf4j-btn-2 doDelete"  title="批量删除">
								<i class="bf4j-icon-btn-delete"></i>批量删除
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
	<script type="text/javascript" src='<b:path url="role-limit{min}.js"/>'></script>
</body>
</html>
