<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<form id="searchForm" method="post">
	<div class="bf4j-group">
		<div class="bf4j-group-content">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.ntfctnId" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> <input type="text" name="NtfctnId" class="easyui-textbox" style="width: 90%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<select class="easyui-combobox" id="PaymentCag" name="PaymentCag">
							<option value="">All</option>
							<option value="CXSALA"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CXSALA" /></option>
							<option value="CXMRCH"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CXMRCH" /></option>
							<option value="CXBSNS"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CXBSNS" /></option>
							<option value="CXPSNL"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CXPSNL" /></option>
							<option value="DDBILL"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.DDBILL" /></option>
							<option value="DDTOPU"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.DDTOPU" /></option>
							<option value="DDECOM"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.DDECOM" /></option>
							<option value="DDOTHR"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.DDOTHR" /></option>
							<option value="RPRTRN"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.RPRTRN" /></option>
							<option value="RPRFND"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.RPRFND" /></option>
							<option value="SWAUTO"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.SWAUTO" /></option>
							<option value="SWMANU"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.SWMANU" /></option>
							<option value="SWOPCL"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.SWOPCL" /></option>
							<option value="SWRVSL"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.SWRVSL" /></option>
							<option value="CPBADJ"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CPBADJ" /></option>
							<option value="CPMERG"><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCag.CPMERG" /></option>
					    </select>
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentDbtr" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<select class="easyui-combobox" id="dbtrMmbIdSelect" name="dbtrMmbIdSelect">
							<option value="">All</option>
							<option value="ICL"><spring:message code="fps.clearingCode.ICL" /></option>
							<option value="888"><spring:message code="fps.clearingCode.888" /></option>
							<option value="838"><spring:message code="fps.clearingCode.838" /></option>
							<option value="828"><spring:message code="fps.clearingCode.828" /></option>
							<option value="818"><spring:message code="fps.clearingCode.818" /></option>
							<option value="Other">Other</option>
					    </select>
                 		<input type="text" id="dbtrMmbOther" name="dbtrMmbOther" class="easyui-textbox" style="width: 30%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.ntfctnCreateTs" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<input type="text" class="easyui-datebox"  id="ntfctnCreateBeginDate" name="ntfctnCreateBeginDate" editable="false" /> -
						<input type="text" class="easyui-datebox"  name="ntfctnCreateEndDate" editable="false" />
					</span>
				</div>
			</div>
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.paymentCdtr" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input">
						<select class="easyui-combobox" id="cdtrMmbIdSelect" name="cdtrMmbIdSelect">
							<option value="">All</option>
							<option value="ICL"><spring:message code="fps.clearingCode.ICL" /></option>
							<option value="888"><spring:message code="fps.clearingCode.888" /></option>
							<option value="838"><spring:message code="fps.clearingCode.838" /></option>
							<option value="828"><spring:message code="fps.clearingCode.828" /></option>
							<option value="818"><spring:message code="fps.clearingCode.818" /></option>
							<option value="Other">Other</option>
					    </select>
                 		<input type="text" id="cdtrMmbIdOther" name="cdtrMmbIdOther" class="easyui-textbox" style="width: 30%" />
					</span>
				</div>
				<div class="bf4j-cell bf4j-c2">
					<label><spring:message code="ffp.cashmanagement.fpspaymentnotification.settlementTs" /></label>
				</div>
				<div class="bf4j-cell bf4j-sc4">
					<span class="bf4j-input"> 
						 <input type="text" class="easyui-datebox"  name="settlementBeginDate" editable="false" /> -
						 <input type="text" class="easyui-datebox"  name="settlementEndDate" editable="false" />
					</span>
				</div>
			</div>
		</div>
		<div class="bf4j-group">
			<div class="bf4j-line">
				<div class="bf4j-cell bf4j-c12 bf4j-center">
					<a class="bf4j-btn doQuery" title="<spring:message code="button.search"/>">
						<i class="bf4j-icon-btn-search"></i><spring:message code="button.search" />
					</a>
				</div>
			</div>
		</div>
	</div>
</form>