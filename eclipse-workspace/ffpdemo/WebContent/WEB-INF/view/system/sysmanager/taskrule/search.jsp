<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="systemmanager.taskrule.taskname"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="taskName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="systemmanager.taskrule.limitusers"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="operFlag" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="systemmanager.taskrule.limitorgs"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="orgFlag" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="systemmanager.taskrule.limitroles"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="roleFlag" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="systemmanager.taskrule.linkdetail"/>：</label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-combo" combo-options="extra:'all',dataKey:'BOOLEAN'" data-options="width:'90%'" name="detailFlag" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label></label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<label></label>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doQuery" id="doQuery" title="查询"><i class="bf4j-icon-btn-search"></i>提交查询</a>
			</div>
		</div>
	</div>
</form>