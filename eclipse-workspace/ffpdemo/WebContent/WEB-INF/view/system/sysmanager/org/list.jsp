<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title>机构管理</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;height: 400px; ">
					<div data-options="region:'west',title:'机构树<font color=red>（可右键操作机构，可拖动机构）</font>'" 
						style="width:350px;">
						<ul id="tree"></ul>
					</div>
					<div data-options="region:'center',title:'机构维护'">
						<div class="bf4j-warp" >
							<form id="searchForm" method="post">
								<input type="hidden" name="supOrgId">
								<div class="bf4j-group">
									<div class="bf4j-group-content">
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>父机构：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="supOrg" readonly="readonly" class="easyui-textbox bf4j-readonly" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>机构ID：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgId" class="easyui-validatebox" style="width: 90%" 
													 data-options="required:true,  missingMessage:'机构ID不能为空.'"/>
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>机构名称：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgName" class="easyui-validatebox" style="width: 90%" 
													 data-options="required:true,  missingMessage:'机构名称不能为空.'"/>
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>机构级别：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgLevel" readonly="readonly" class="easyui-textbox bf4j-readonly" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>机构类型：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgType" class="easyui-textbox" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label>描述：</label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input"> 
													<textarea name="des" class="easyui-validatebox" style="width: 90%;height:100px">
									                </textarea>
												</span>
											</div>
										</div>
									</div>
								</div>
								<div class="bf4j-group">
									<div class="bf4j-line">
										<div class="bf4j-cell bf4j-c12 bf4j-center">
											<a class="bf4j-btn btn-save" title="提交"><i class="bf4j-icon-btn-save"></i>提交</a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div id="popupMenu" class="easyui-menu" style="width: 120px; display: none;">
					<div class="gotoAdd" data-options="iconCls:'bf4j-icon-right-add'">添加子机构</div>
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'">修改机构</div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'">删除机构及其子机构</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="org{min}.js"/>'></script>
</body>
</html>
