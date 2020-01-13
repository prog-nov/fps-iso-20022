<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-sc2">
					<label><spring:message code="document.list.docName"/>:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> 
						<input type="text" name="docName" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label><spring:message code="document.list.suffix"/>:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" name="suffix" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label><spring:message code="document.list.docType"/>:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input class="bf4j-combo" combo-options="dataKey:'BF_DOC_TYPE',extra:'select'" name="docType"/>
					</span>
				</div>
				<div class="bf4j-cell bf4j-sc2">
					<label><spring:message code="document.list.docState"/>:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input class="bf4j-combo" combo-options="dataKey:'BF_DOC_STATUS',extra:'select'" name="docState"/>
					</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn SearchBtn" title='<spring:message code="button.search"/>'>
					<i class="bf4j-icon-btn-search"></i>
					<spring:message code="button.search"/>
				</a>
			</div>
		</div>
	</div>
</form>