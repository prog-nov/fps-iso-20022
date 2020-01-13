<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" class="easyui-form" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.accountinquery.pleaseSelect" /></label>
				</div>
				<div class="bf4j-cell bf4j-c2">
				<span class="bf4j-input"> 
                         <select class="easyui-combobox" id="txStat" name="currency">
									<option value=""><spring:message code="common.title.all" /></option>
									<option value="CNY"><spring:message code="ffp.cashmanagement.accountinquery.CNY" /></option>
									<option value="HKD"><spring:message code="ffp.cashmanagement.accountinquery.HKD" /></option>
									<option value="USD"><spring:message code="ffp.cashmanagement.accountinquery.USD" /></option>
					    </select>					
				</span>
					<%-- <span class="bf4j-input"> 
						<input class="bf4j-combo" name="currency" combo-options="extra:'select',dataKey:'CURRENCY'" data-options="required:true" placeholder="<spring:message code="ffp.cashmanagement.accountinquery.pleaseSelect"/>" style="width: 100%"/>
					</span> --%>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.accountinquery.accountBalance" /></label>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<span class="bf4j-input">
						<input class="easyui-textbox" type="text" name="balance" style="width: 100%" readonly disabled/>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c5 bf4j-center">
					<a class="bf4j-btn doQuery" title='<spring:message code="button.search" />'><i class="bf4j-icon-btn-search"></i><spring:message code="button.submitSearch" /></a>
				</div>
			</div>
		</div>
	</div>
</form>