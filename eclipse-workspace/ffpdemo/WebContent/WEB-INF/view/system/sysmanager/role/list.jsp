<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>角色管理</title>
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
					<div class="bf4j-group newsBtn">
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
							<a class="bf4j-btn-2 gotoDownload" title="下载">
								<i class="bf4j-icon-btn-download"></i>下载</a>
							<a class="bf4j-btn-2 gotoAdd"  title="添加角色">
								<i class="bf4j-icon-btn-add"></i>添加角色</a>
							<a class="bf4j-btn-2 gotoUpdate" title="修改角色">
								<i class="bf4j-icon-btn-edit"></i>修改角色</a>
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
				<div id="popupMenu" class="easyui-menu" style="width: 50px; display: none;">
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'">修 改</div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'">删 除</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="role{min}.js"/>'></script>
</body>
</html>
