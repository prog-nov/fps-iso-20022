<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="userSearchForm" method="post">
<div class="bf4j-group" id="taskUserDiv">
	<div class="bf4j-group-content">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-sc3">
				<label><spring:message code="systemmanager.taskrule.userid"/>：</label>
			</div>
			<div class="bf4j-cell bf4j-sc5">
				<span class="bf4j-input"> 
					<input type="text" name="userId" class="easyui-textbox" style="width: 90%" />
				</span>
			</div>
			<div class="bf4j-cell bf4j-sc3">
				<label><spring:message code="systemmanager.taskrule.username"/>：</label>
			</div>
			<div class="bf4j-cell bf4j-sc5">
				<span class="bf4j-input">
					<input type="text" name="userName" class="easyui-textbox" style="width: 90%" />
				</span>
			</div>
			<div class="bf4j-cell bf4j-sc3">
				<label><spring:message code="systemmanager.taskrule.nickname"/>：</label>
			</div>
			<div class="bf4j-cell bf4j-sc5">
				<span class="bf4j-input">
					<input type="text" name="nickName" class="easyui-textbox" style="width: 90%" />
				</span>
			</div>
		</div>
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-sc3">
				<label><spring:message code="systemmanager.taskrule.org"/>：</label>
			</div>
			<div class="bf4j-cell bf4j-sc5">
				<span class="bf4j-input"> 
					<input class="bf4j-organ" name="orgId" />
				</span>
			</div>
			<div class="bf4j-cell bf4j-sc3">
			</div>
			<div class="bf4j-cell bf4j-sc5">
			</div>
			<div class="bf4j-cell bf4j-sc3">
			</div>
			<div class="bf4j-cell bf4j-sc5">
			</div>
		</div>
	</div>
</div>
<div class="bf4j-group">
	<div class="bf4j-line">
		<div class="bf4j-cell bf4j-c12 bf4j-center">
			<a class="bf4j-btn doUserQuery" id="doUserQuery" title="查询"><i class="bf4j-icon-btn-search"></i>提交查询</a>
		</div>
	</div>
</div>

</form>