<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="queryForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc6">
					<label><spring:message code="system.sysmanager.maintenance.roleName" /></label> 
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="roleName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>			
				<div class="bf4j-cell bf4j-sc6">
					<label><spring:message code="system.sysmanager.maintenance.roleallotName" /></label> 
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="roleAllotName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doQuery" title='<spring:message code="button.search" />'><i class="bf4j-icon-btn-search"></i><spring:message code="button.submitSearch" /></a>
			</div>
		</div>
	</div>
</form>