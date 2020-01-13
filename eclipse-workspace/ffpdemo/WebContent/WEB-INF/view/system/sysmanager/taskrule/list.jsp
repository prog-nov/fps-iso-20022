<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>任务规则管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<!-- 查询表单-->
				<jsp:include page="./search.jsp"></jsp:include>
				<div class="bf4j-group">
					<div class="bf4j-line">
						<div class="bf4j-cell bf4j-c12 bf4j-right">
							<a class="bf4j-btn-2 gotoAdd"  title="添加">
								<i class="bf4j-icon-btn-add"></i>添加</a>
							<a class="bf4j-btn-2 gotoUpdate" title="修改">
								<i class="bf4j-icon-btn-edit"></i>修改</a>
							<a class="bf4j-btn-2 doDelete"  title="删除">
								<i class="bf4j-icon-btn-delete"></i>删除
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
				<div id="userDialog">
				</div>
				<div id="popupMenuEdit" class="easyui-menu" style="width: 120px; display: none;">
					<div class="gotoAdd" data-options="iconCls:'bf4j-icon-right-add'">选择该机构</div>
					<div class="gotoDel" data-options="iconCls:'bf4j-icon-right-delete'">取消选择</div>
				</div>
				<div id="popupMenu" class="easyui-menu" style="width: 50px; display: none;">
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'">修 改</div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'">删 除</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="taskrule{min}.js"/>'></script>
</body>
</html>
