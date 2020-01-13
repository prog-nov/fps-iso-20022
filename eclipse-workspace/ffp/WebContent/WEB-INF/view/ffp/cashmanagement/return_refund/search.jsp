<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.rerurn_refund.cusName" />:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text" name="cusName"
						class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message
							code="ffp.cashmanagement.rerurn_refund.accountNm" />:</label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text"
						name="accountNm" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				</div>
			  
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.rerurn_refund.settleDate" />:</label>
				</div>
				 <div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
					 <input type="text" class="easyui-datebox"  name="settleDate"  />
					</span>
				</div>
			</div>
			
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.rerurn_refund.reType" />:</label>
				</div>
				 <div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input">
						<select name="reType">
							<option value=""><spring:message code="common.title.all" /></option>
							<option value="RETURN"><spring:message code="ffp.cashmanagement.rerurn_refund.RETURN" /></option>
							<option value="REFUND"><spring:message code="ffp.cashmanagement.rerurn_refund.REFUND" /></option>
						</select> 
					</span>
				</div>
			</div>
			

		</div>
	</div>
	<div class="bf4j-group">
		<div class="bf4j-line">
			<div class="bf4j-cell bf4j-c12 bf4j-center">
				<a class="bf4j-btn doQuery"
					title="<spring:message code="button.search"/>"><i
					class="bf4j-icon-btn-search"></i> <spring:message
						code="button.search" /></a>
			</div>
		</div>
	</div>
</form>