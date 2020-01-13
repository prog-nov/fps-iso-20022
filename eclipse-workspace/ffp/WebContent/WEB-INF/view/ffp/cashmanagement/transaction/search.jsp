<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.transaction.jnlNo" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text" name="jnlNo"
						class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message
							code="ffp.cashmanagement.transaction.transactionId" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text"
						name="transactionId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.transaction.endToEndId" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text"
						name="endToEndId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				</div>
			   <div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.transaction.txStat" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
				<span class="bf4j-input"> 
                         <select class="easyui-combobox" id="txStat" name="txStat">
									<option value=""><spring:message code="ffp.cashmanagement.transaction.ALL" /></option>
									<option value="COMPL"><spring:message code="ffp.cashmanagement.transaction.COMPL" /></option>
									<option value="ERROR"><spring:message code="ffp.cashmanagement.transaction.ERROR" /></option>
									<option value="APPST"><spring:message code="ffp.cashmanagement.transaction.APPST" /></option>
									<option value="REJCT"><spring:message code="ffp.cashmanagement.transaction.REJCT" /></option>
									<option value="CREAT"><spring:message code="ffp.cashmanagement.transaction.CREAT" /></option>
									<option value="TMOUT"><spring:message code="ffp.cashmanagement.transaction.TMOUT" /></option>
					    </select>					
				</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.transaction.txCode" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
					<select class="easyui-combobox" id="txCode" name="txCode">
									<option value=""><spring:message code="ffp.cashmanagement.transaction.ALL" /></option>
									<option value="P100"><spring:message code="ffp.cashmanagement.transaction.P100" /></option>
									<option value="P110"><spring:message code="ffp.cashmanagement.transaction.P110" /></option>
									<option value="P200"><spring:message code="ffp.cashmanagement.transaction.P200" /></option>
									<option value="P210"><spring:message code="ffp.cashmanagement.transaction.P210" /></option>
									<option value="P300"><spring:message code="ffp.cashmanagement.transaction.P300" /></option>
									<option value="A100"><spring:message code="ffp.cashmanagement.transaction.A100" /></option>
					</select>
					</span>
				</div>

			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.transaction.transDate" /></label>
				</div>
				 <div class="bf4j-cell bf4j-sc5">
					<span class="bf4j-input"> 
					 <input type="text" class="easyui-datebox"  name="beginDate"  /> -
					 <input type="text" class="easyui-datebox"  name="endDate"  />
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