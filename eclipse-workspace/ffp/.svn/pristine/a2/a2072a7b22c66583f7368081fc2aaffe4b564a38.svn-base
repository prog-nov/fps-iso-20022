<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/WEB-INF/view/common/includer/page-includer.jsp"></jsp:include>
<title><spring:message code="system.sysmanager.org.title.orgManager" /></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;">
		<div data-options="region:'center',border:false">
			<div class="bf4j-warp" >
				<!-- 导航页面 -->
				<jsp:include page="/WEB-INF/view/common/leader/leaderView.jsp"></jsp:include>
				<div class="easyui-layout" data-options="fit:true" style="padding-top:10px;height: 400px; ">
					<div data-options="region:'west',title:'<spring:message code="system.sysmanager.org.title.orgTree" /><font color=red><spring:message code="system.sysmanager.org.title.remark" /></font>'" 
						style="width:350px;">
						<ul id="tree"></ul>
					</div>
					<div data-options="region:'center',title:'<spring:message code="system.sysmanager.org.title.orgMain" />'">
						<div class="bf4j-warp" >
							<form id="searchForm" method="post">
								<input type="hidden" name="supOrgId">
								<div class="bf4j-group">
									<div class="bf4j-group-content">
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.supOrg" /></label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="supOrg" readonly="readonly" class="easyui-textbox bf4j-readonly" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.orgId" /></label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgId" class="easyui-validatebox" style="width: 90%" 
													 data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.org.orgIdMiss" />'"/>
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.orgName" /></label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgName" class="easyui-validatebox" style="width: 90%" 
													 data-options="required:true,  missingMessage:'<spring:message code="system.sysmanager.org.orgNameMiss" />'"/>
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.orgLevel" /></label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgLevel" readonly="readonly" class="easyui-textbox bf4j-readonly" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.orgType" /></label>
											</div>
											<div class="bf4j-cell bf4j-c8">
												<span class="bf4j-input">
													<input type="text" name="orgType" class="easyui-textbox" style="width: 90%" />
												</span>
											</div>
										</div>
										<div class="bf4j-line">
											<div class="bf4j-cell bf4j-c4">
												<label><spring:message code="system.sysmanager.org.des" /></label>
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
											<a class="bf4j-btn btn-save" title='<spring:message code="button.submit" />'><i class="bf4j-icon-btn-save"></i><spring:message code="button.submit" /></a>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div id="popupMenu" class="easyui-menu" style="width: 120px; display: none;">
					<div class="gotoAdd" data-options="iconCls:'bf4j-icon-right-add'"><spring:message code="system.sysmanager.org.title.addSonOrg" /></div>
					<div class="gotoUpdate" data-options="iconCls:'bf4j-icon-right-edit'"><spring:message code="system.sysmanager.org.title.editOrg" /></div>
					<div class="doDelete" data-options="iconCls:'bf4j-icon-right-delete'"><spring:message code="system.sysmanager.org.title.delOrgAndSonOrg" /></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src='<b:path url="org{min}.js"/>'></script>
</body>
</html>
