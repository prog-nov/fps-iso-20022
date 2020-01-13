<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="system.sysmanager.syslog.visit.orgId" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input class="bf4j-organ" name="orgId" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="system.sysmanager.syslog.visit.userId" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
						<input type="text" name="userId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc3">
					<label><spring:message code="system.sysmanager.syslog.visit.userName" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<input type="text" name="userName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
					<div class="bf4j-cell bf4j-sc3">
						<label><spring:message code="system.sysmanager.syslog.visit.loginDate" /></label>
					</div>
					<div class="bf4j-cell bf4j-sc5">
						<span class="bf4j-input"> 
							<input type="text" name="beginDate"  style="width: 86px" /> - 
							<input type="text" name="endDate" style="width: 86px" />
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