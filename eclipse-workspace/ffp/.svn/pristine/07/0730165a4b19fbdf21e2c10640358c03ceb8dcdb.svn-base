<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/view/common/taglib.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreateCreditTransferMsg</title>
<link href="/ffp/resources/ffp/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="/ffp/resources/ffp/bootstrap/css/bootstrap-theme.css" rel="stylesheet">
<link href="/ffp/resources/ffp/bankSim/css/bankSim.css" rel="stylesheet"> 
</head>
<body>
    <div class="container"><div style="width: 100%;"></div>

      <!-- Static navbar -->
      <!--<nav class="navbar navbar-default">
      	<div class="container-fluid"><div class="container-fluid">
    <div class="navbar-header">
       <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">FPS Bank Simulator</a> 
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li><a href="..">Home</a></li>
        <li><a href="../sendAndReceive/launch">Send Message</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">View Message <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="../viewReceivedMessage/launch">Received Message</a></li>
            <li><a href="../viewCreatedMessage/launch">Created Message</a></li>
            <li><a href="../viewSentMessage/launch">Sent Message</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Create Message <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="../createISO/launch">Payment Message</a></li>
            <li><a href="../createAddressing/launch">Addressing Message</a></li>
            <li><a href="../createEDDA/launch">eDDA Message</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Manage Auto Response <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="../viewTemplate/launch">View Response Template</a></li>
            <li><a href="../createRespTemp/launch">Create Response Template</a></li>
            <li><a href="../viewTemplate/config">Manage Auto Response Config</a></li>
          </ul>
        </li>
      </ul>
    </div> 
  </div></div>
      </nav>-->
		<h3>Confirm Credit Transfer Message</h3>
	<!-- /// -->	
	<form id="ctForm" method="POST" action="./sendmsg" class="form-horizontal" accept-charset="UTF-8">
        <!-- Main component for a primary marketing message or call to action -->
        <div class="panel panel-default">
		</div>
		
        <div class="panel panel-primary" id="tx[0]">
          <div class="panel-heading clearfix">
			<h3 class="panel-title pull-left">Transaction Details #1</h3>
			<div class="form-inline pull-right">
				<div class="input-group">
					<div class="input-group-addon">Number of Copies</div>
					<input type="number" class="form-control" min="1" max="10000" name="creditTransferTransactionList[0].numOfCopy" value="${model.creditTransferTransactionList[0].numOfCopy}">
				</div>
			</div>
          </div> 
          <div class="panel-body">			
			
				
			<div class="row">
				<div class="col-md-3">
					<label for="sttlmDt" class="control-label"><span style="color:red">*</span> Settlement Date</label>
					<input type="text" class="form-control" id="sttlmDt" placeholder="YYYY-MM-DD" name="creditTransferTransactionList[0].settlementDate" 
						value="2018-02-28" required="">					
				</div>				
				<div class="col-md-4">
					<label for="sttlmAmt" class="control-label"><span style="color:red">*</span> Settlement Amount</label>
					<div class="form-inline">
					    <div class="input-group">
							<select id="ccy" class="form-control" name="creditTransferTransactionList[0].settlementCurrency" value="${model.creditTransferTransactionList[0].settlementCurrency}">
								<option value="HKD" <c:if test="${'HKD' eq model.creditTransferTransactionList[0].settlementCurrency}">selected</c:if>>HKD</option>
								<option value="CNY" <c:if test="${'CNY' eq model.creditTransferTransactionList[0].settlementCurrency}">selected</c:if>>CNY</option>
								<option value="USD" <c:if test="${'USD' eq model.creditTransferTransactionList[0].settlementCurrency}">selected</c:if>>USD</option>
								<option value="EUR" <c:if test="${'EUR' eq model.creditTransferTransactionList[0].settlementCurrency}">selected</c:if>>EUR</option>
							</select>	    
					      <div class="input-group-addon">$</div>
					      <input type="number" class="form-control" id="sttlmAmt" step="0.01" placeholder="15n.2n" size="20" name="creditTransferTransactionList[0].settlementAmount" 
					      		value="${model.creditTransferTransactionList[0].settlementAmount}" required="">
					    </div>					
				    </div>		
				</div>
				
				<!-- <div class="col-md-4">
					<label for="instructedAmt" class="control-label">Instructed Amount</label>
					<div class="form-inline">
					    <div class="input-group">
							<select id="instructedCcy" class="form-control" name="creditTransferTransactionList[0].instructedCurrency" value="">
								<option value="HKD">HKD</option>
								<option value="CNY">CNY</option>
								<option value="USD">USD</option>
								<option value="EUR">EUR</option>
							</select>	    
					      <div class="input-group-addon">$</div>
					      <input type="number" class="form-control" id="instructedAmount" step="0.01" placeholder="15n.2n" size="20" name="creditTransferTransactionList[0].instructedAmount" value="">
					    </div>					
				    </div>		
				</div> -->
			</div>	
			
			<!-- <div class="row">
				<div class="col-md-4">
					<label for="cdtrMmbId" class="control-label">Chargers Agent</label>
					<div class="form-inline">
					    <div class="input-group">
					    	<div class="input-group-addon">ID</div>
							<input type="text" class="form-control" id="dbtrMmbId" placeholder="3!A" size="3" name="creditTransferTransactionList[0].chargersAgentId" value="">
							<div class="input-group-addon">BIC</div>
							<input type="text" class="form-control" id="dbtrMmbBic" placeholder="11A" name="creditTransferTransactionList[0].chargersAgentBic" value="">
					    </div>													
					</div>														
				</div>
				
				<div class="col-md-6">
					<label for="chargersAmount" class="control-label">Chargers Amount</label>
					<div class="form-inline">
					    <div class="input-group">
							<select id="chargersCcy" class="form-control" name="creditTransferTransactionList[0].chargersCurrency" value="">
								<option value="HKD">HKD</option>
								<option value="CNY">CNY</option>
								<option value="USD">USD</option>
								<option value="EUR">EUR</option>
							</select>	    
					      <div class="input-group-addon">$</div>
					      <input type="number" class="form-control" id="chargersAmount" step="0.01" placeholder="15n.2n" size="20" name="creditTransferTransactionList[0].chargersAmount" value="">
					    </div>					
				    </div>		
				</div>
			</div>	 -->														

			<div class="row">
				<div class="col-md-12">
					<label for="dbtrNm" class="control-label"><span style="color:red">*</span> Debtor Name</label>
					<input type="text" class="form-control" id="dbtrNm" placeholder="140u" name="creditTransferTransactionList[0].debtorName" 
						value="${model.creditTransferTransactionList[0].debtorName }" required="">
				</div>
			</div>				
			<div class="row">
				<div class="col-md-8">
					<label for="dbtrAccNum" class="control-label"><span style="color:red">*</span> Debtor Account Number</label>
					<div class="form-inline">
					    <div class="input-group">
							<input type="text" class="form-control" id="dbtrAccNum" placeholder="34c" size="40" name="creditTransferTransactionList[0].debtorAccountNumber" 
									value="${model.creditTransferTransactionList[0].debtorAccountNumber }" required="">
							<div class="input-group-addon">Type</div>
							<select id="dbtrAccNumTyp" class="form-control" name="creditTransferTransactionList[0].debtorAccountNumberType" value="${model.creditTransferTransactionList[0].debtorAccountNumberType }" required="">
								<option value="">-- Please select --</option>							
								<option value="BBAN" <c:if test="${'BBAN' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>BBAN - Customer / bank account number</option>							
								<option value="AIIN" <c:if test="${'AIIN' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>AIIN - Credit card number</option>							
								<option value="SVID" <c:if test="${'SVID' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>SVID - FPS identifier (registered in addressing overlay service)</option>							
								<option value="EMAL" <c:if test="${'EMAL' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>EMAL - Email address (registered in addressing overlay service)</option>							
								<option value="MOBN" <c:if test="${'MOBN' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>MOBN - Mobile number (registered in addressing overlay service)</option>							
								<option value="CUST" <c:if test="${'CUST' eq model.creditTransferTransactionList[0].debtorAccountNumberType}">selected</c:if>>CUST - Customer ID (retrieved from addressing overlay service)</option>							
							</select>	
					    </div>													
					</div>						
				</div>
				<div class="col-md-4">
					<label for="cdtrMmbId" class="control-label"><span style="color:red">*</span> Debtor Agent</label>
					<div class="form-inline">
					    <div class="input-group">
					    	<div class="input-group-addon">ID</div>
							<input type="text" class="form-control" id="dbtrMmbId" placeholder="3!A" size="3" name="creditTransferTransactionList[0].debtorAgentId" 
									value="${model.creditTransferTransactionList[0].debtorAgentId}" required="">
							<!-- <div class="input-group-addon">BIC</div>
							<input type="text" class="form-control" id="dbtrMmbBic" placeholder="11A" name="creditTransferTransactionList[0].debtorAgentBic" value=""> -->
					    </div>													
					</div>														
				</div>						
			</div>
			
			

	<!-- <div id="debtorIdDetailsTypeDiv[0]" class="row raidoShowDiv">
		<div class="col-md-12">
			<label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].debtorIdDetailsType" value="NIL">
				<span>No ID Details</span>
			</label><label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].debtorIdDetailsType" value="ORG">
				<span>Add Organisation Identification</span>
			</label><label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].debtorIdDetailsType" value="PRV">
				<span>Add Private Identification</span>
			</label>
		</div>
	</div> -->
	
	<!-- <input type="hidden" name="creditTransferTransactionList[0].debtorIdDetail.idSize" value="1"> -->
	
	<div id="debtorIdDetailsTypeORG[0]" class="alert alert-info" style="display: none;">
		<div class="row">
			<div class="col-md-2">
				<label for="debtorOrganisationBic" class="control-label">BIC</label>
				<input type="text" class="form-control" id="debtorOrganisationBic" placeholder="11A" size="12" name="creditTransferTransactionList[0].debtorIdDetail.organisationBic" value="" disabled="">
			</div>				
			<div class="col-md-10">	
				<label for="creditTransferTransactionList[0].debtorIdDetail.Orgid" class="control-label"><font>Debtor</font> Organisation ID</label>
				<div class="listInput-container" id="creditTransferTransactionList[0].debtorIdDetail.Orgid" data-seq="py7s1">
					
					<div class="listInput-element-container" data-index="0">
								<div class="form-inline">
									<div class="input-group">
										
										<div class="input-group-addon">ID</div>
										
										<input type="text" class="form-control" id="dbtrId" placeholder="35c" data-bind-name="'creditTransferTransactionList[0].debtorIdDetail.id[' + data.index + '].id'" data-bind-value="data.id" name="creditTransferTransactionList[0].debtorIdDetail.id[0].id" disabled="">
											
										<div class="input-group-addon">Type</div>
										
										<select id="dbtrIdType" class="form-control" data-bind-name="'creditTransferTransactionList[0].debtorIdDetail.id[' + data.index + '].idType'" data-bind-value="data.idType" name="creditTransferTransactionList[0].debtorIdDetail.id[0].idType" disabled="">
											<option value="">-- Please select --</option>							
											<option value="CUST">CUST</option>							
										</select>
										
										
										<span class="input-group-btn">
											
										</span>
									</div>
								</div>
								
							</div><div class="listInput-after">
						
					</div>
					
				</div>
			
			</div>			
		</div>
		<div class="row">
			<div class="col-md-2">
				<label for="mobileNumber" class="control-label">Mobile Number</label>
				<input type="text" class="form-control" id="mobileNumber" placeholder="+852-88888888" name="creditTransferTransactionList[0].debtorIdDetail.mobileNumber" value="" disabled="">
			</div>
			<div class="col-md-4">
				<label for="emailAddress" class="control-label">Email Address</label>
				<input type="text" class="form-control" id="emailAddress" placeholder="" name="creditTransferTransactionList[0].debtorIdDetail.emailAddress" value="" disabled="">
			</div>	
		</div>
	</div>
	<div id="debtorIdDetailsTypePRV[0]" class="alert alert-info" style="display: none;">
		<div class="row">
			<div class="col-md-10">		
				<label for="creditTransferTransactionList[0].debtorIdDetail.prvId" class="control-label"><font>Debtor</font> Private ID</label>
				<div class="listInput-container" id="creditTransferTransactionList[0].debtorIdDetail.prvId" data-seq="m24q8">
	
					
					
					<div class="listInput-element-container" data-index="0">
								<div class="form-inline">
									<div class="input-group">
										
										<div class="input-group-addon">ID</div>
										
										<input type="text" class="form-control" id="dbtrId" placeholder="35c" data-bind-name="'creditTransferTransactionList[0].debtorIdDetail.id[' + data.index + '].id'" data-bind-value="data.id" name="creditTransferTransactionList[0].debtorIdDetail.id[0].id" disabled="">
											
										<div class="input-group-addon">Type</div>
										
										<select id="dbtrIdType" class="form-control" data-bind-name="'creditTransferTransactionList[0].debtorIdDetail.id[' + data.index + '].idType'" data-bind-value="data.idType" name="creditTransferTransactionList[0].debtorIdDetail.id[0].idType" disabled="">
											<option value="">-- Please select --</option>							
											<option value="CUST">CUST</option>							
										</select>
										
										
										<span class="input-group-btn">
											
										</span>
									</div>
								</div>
								
							</div><div class="listInput-after">
						
					</div>
					
				</div>
													
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<label for="debtorMobileNumber" class="control-label">Mobile Number</label>
				<input type="text" class="form-control" id="${endUser}MobileNumber" placeholder="+852-88888888" name="creditTransferTransactionList[0].debtorIdDetail.mobileNumber" value="" disabled="">
			</div>
			<div class="col-md-4">
				<label for="debtorEmailAddress" class="control-label">Email Address</label>
				<input type="text" class="form-control" id="${endUser}EmailAddress" placeholder="" name="creditTransferTransactionList[0].debtorIdDetail.emailAddress" value="" disabled="">
			</div>		
		</div>			
	</div>		

			
			
			<div class="row">
				<div class="col-md-12">
					<label for="cdtrNm" class="control-label"><span style="color:red">*</span> Creditor Name</label>
					<input type="text" class="form-control" id="cdtrNm" placeholder="140u" name="creditTransferTransactionList[0].creditorName"
						 value="${model.creditTransferTransactionList[0].creditorName }" required="">
				</div>
			</div>				
			<div class="row">
				<div class="col-md-8">
					<label for="cdtrAccNum" class="control-label"><span style="color:red">*</span> Creditor Account Number</label>
					<div class="form-inline">
					    <div class="input-group">
							<input type="text" class="form-control" id="cdtrAccNum" placeholder="34c" size="40" name="creditTransferTransactionList[0].creditorAccountNumber" 
									value="${model.creditTransferTransactionList[0].creditorAccountNumber}" required="">
							<div class="input-group-addon">Type</div>
							<select id="cdtrAccNumTyp" class="form-control" name="creditTransferTransactionList[0].creditorAccountNumberType" value="${model.creditTransferTransactionList[0].creditorAccountNumberType}" required="">
								<option value="">-- Please select --</option>							
								<option value="BBAN" <c:if test="${'BBAN' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>BBAN - Customer / bank account number</option>							
								<option value="AIIN" <c:if test="${'AIIN' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>AIIN - Credit card number</option>							
								<option value="SVID" <c:if test="${'SVID' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>SVID - FPS identifier (registered in addressing overlay service)</option>							
								<option value="EMAL" <c:if test="${'EMAL' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>EMAL - Email address (registered in addressing overlay service)</option>							
								<option value="MOBN" <c:if test="${'MOBN' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>MOBN - Mobile number (registered in addressing overlay service)</option>							
								<option value="CUST" <c:if test="${'CUST' eq model.creditTransferTransactionList[0].creditorAccountNumberType}">selected</c:if>>CUST - Customer ID (retrieved from addressing overlay service)</option>							
							</select>	
					    </div>													
					</div>						
				</div>
				<div class="col-md-4">
					<label for="cdtrMmbId" class="control-label"><span style="color:red">*</span> Creditor Agent</label>
					<div class="form-inline">
					    <div class="input-group">
					    	<div class="input-group-addon">ID</div>
							<input type="text" class="form-control" id="cdtrMmbId" placeholder="3!A" size="3" name="creditTransferTransactionList[0].creditorAgentId" 
								value="${model.creditTransferTransactionList[0].creditorAgentId}" required="">
							<!-- <div class="input-group-addon">BIC</div>
							<input type="text" class="form-control" id="cdtrMmbBic" placeholder="11A" name="creditTransferTransactionList[0].creditorAgentBic" value=""> -->
					    </div>													
					</div>														
				</div>
			</div>
			
			

	<!-- <div id="creditorIdDetailsTypeDiv[0]" class="row raidoShowDiv">
		<div class="col-md-12">
			<label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].creditorIdDetailsType" value="NIL">
				<span>No ID Details</span>
			</label><label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].creditorIdDetailsType" value="ORG">
				<span>Add Organisation Identification</span>
			</label><label class="radio-inline">
				<input type="radio" name="creditTransferTransactionList[0].creditorIdDetailsType" value="PRV">
				<span>Add Private Identification</span>
			</label>
		</div>
	</div> -->
	
	<!-- <input type="hidden" name="creditTransferTransactionList[0].creditorIdDetail.idSize" value="1"> -->
	
	<div id="creditorIdDetailsTypeORG[0]" class="alert alert-info" style="display: none;">
		<div class="row">
			<div class="col-md-2">
				<label for="creditorOrganisationBic" class="control-label">BIC</label>
				<input type="text" class="form-control" id="creditorOrganisationBic" placeholder="11A" size="12" name="creditTransferTransactionList[0].creditorIdDetail.organisationBic" value="" disabled="">
			</div>				
			<div class="col-md-10">	
				<label for="creditTransferTransactionList[0].creditorIdDetail.Orgid" class="control-label"><font>Creditor</font> Organisation ID</label>
				<div class="listInput-container" id="creditTransferTransactionList[0].creditorIdDetail.Orgid" data-seq="q32wxn">
	
					
					<div class="listInput-element-container" data-index="0">
								<div class="form-inline">
									<div class="input-group">
										
										<div class="input-group-addon">ID</div>
										
										<input type="text" class="form-control" id="dbtrId" placeholder="35c" data-bind-name="'creditTransferTransactionList[0].creditorIdDetail.id[' + data.index + '].id'" data-bind-value="data.id" name="creditTransferTransactionList[0].creditorIdDetail.id[0].id" disabled="">
											
										<div class="input-group-addon">Type</div>
										
										<select id="dbtrIdType" class="form-control" data-bind-name="'creditTransferTransactionList[0].creditorIdDetail.id[' + data.index + '].idType'" data-bind-value="data.idType" name="creditTransferTransactionList[0].creditorIdDetail.id[0].idType" disabled="">
											<option value="">-- Please select --</option>							
											<option value="CUST">CUST</option>							
										</select>
										
										
										<span class="input-group-btn">
											
										</span>
									</div>
								</div>
								
							</div><div class="listInput-after">
						
					</div>
					
				</div>
			
			</div>			
		</div>
		<div class="row">
			<div class="col-md-2">
				<label for="mobileNumber" class="control-label">Mobile Number</label>
				<input type="text" class="form-control" id="mobileNumber" placeholder="+852-88888888" name="creditTransferTransactionList[0].creditorIdDetail.mobileNumber" value="" disabled="">
			</div>
			<div class="col-md-4">
				<label for="emailAddress" class="control-label">Email Address</label>
				<input type="text" class="form-control" id="emailAddress" placeholder="" name="creditTransferTransactionList[0].creditorIdDetail.emailAddress" value="" disabled="">
			</div>	
		</div>
	</div>
	<div id="creditorIdDetailsTypePRV[0]" class="alert alert-info" style="display: none;">
		<div class="row">
			<div class="col-md-10">		
				<label for="creditTransferTransactionList[0].creditorIdDetail.prvId" class="control-label"><font>Creditor</font> Private ID</label>
				<div class="listInput-container" id="creditTransferTransactionList[0].creditorIdDetail.prvId" data-seq="kfu3ks">
					
					<div class="listInput-element-container" data-index="0">
								<div class="form-inline">
									<div class="input-group">
										
										<div class="input-group-addon">ID</div>
										
										<input type="text" class="form-control" id="dbtrId" placeholder="35c" data-bind-name="'creditTransferTransactionList[0].creditorIdDetail.id[' + data.index + '].id'" data-bind-value="data.id" name="creditTransferTransactionList[0].creditorIdDetail.id[0].id" disabled="">
											
										<div class="input-group-addon">Type</div>
										
										<select id="dbtrIdType" class="form-control" data-bind-name="'creditTransferTransactionList[0].creditorIdDetail.id[' + data.index + '].idType'" data-bind-value="data.idType" name="creditTransferTransactionList[0].creditorIdDetail.id[0].idType" disabled="">
											<option value="">-- Please select --</option>							
											<option value="CUST">CUST</option>							
										</select>
										
										
										<span class="input-group-btn">
											
										</span>
									</div>
								</div>
								
							</div><div class="listInput-after">
						
					</div>
					
				</div>
													
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<label for="creditorMobileNumber" class="control-label">Mobile Number</label>
				<input type="text" class="form-control" id="${endUser}MobileNumber" placeholder="+852-88888888" name="creditTransferTransactionList[0].creditorIdDetail.mobileNumber" value="" disabled="">
			</div>
			<div class="col-md-4">
				<label for="creditorEmailAddress" class="control-label">Email Address</label>
				<input type="text" class="form-control" id="${endUser}EmailAddress" placeholder="" name="creditTransferTransactionList[0].creditorIdDetail.emailAddress" value="" disabled="">
			</div>		
		</div>			
	</div>		

			
			<!-- <div id="paymentPurposeTypeDiv[0]" class="row raidoShowDiv">
				<div class="col-md-12">
					<label for="paymentPurposeType" class="control-label">Payment Purpose</label>
					<label class="radio-inline">
						<input type="radio" name="creditTransferTransactionList[0].paymentPurposeType" value="NIL">
						<span>No Payment Purpose</span>
					</label><label class="radio-inline">
						<input type="radio" name="creditTransferTransactionList[0].paymentPurposeType" value="CD">
						<span>Code</span>
					</label><label class="radio-inline">
						<input type="radio" name="creditTransferTransactionList[0].paymentPurposeType" value="PRTRY">
						<span>Proprietary</span>
					</label>
				</div>
			</div>	 
			
			<div id="paymentPurposeTypeCD[0]" class="alert alert-info" style="display: none;">
				<div class="row">
					<div class="col-md-2">					
						<label for="paymentPurposeCd" class="control-label">Code</label>
						<input type="text" class="form-control" id="paymentPurposeCd" placeholder="4C" name="creditTransferTransactionList[0].paymentPurposeCd" value="" disabled="">
					</div>
				</div>
			</div>	
			
			<div id="paymentPurposeTypePRTRY[0]" class="alert alert-info" style="display: none;">
				<div class="row">
					<div class="col-md-3">					
						<label for="paymentPurposeProprietary" class="control-label">Proprietary</label>
						<input type="text" class="form-control" id="paymentPurposeProprietary" placeholder="35C" name="creditTransferTransactionList[0].paymentPurposeProprietary" value="" disabled="">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<label for="rmtInf" class="control-label">Remittance Information</label>
					<input type="text" class="form-control" id="rmtInf" placeholder="140u" name="creditTransferTransactionList[0].remittanceInformation" value="">
				</div>
			</div>
			-->

		</div>
    </div> 
    <!-- /container -->
	<div class="row">
		<!--<div class="col-md-3">
			<button type="submit" class="btn btn-default" onclick="toReadOnly()">Next »</button>
		</div>
		<div class="col-md-9" style="text-align: right;">
			<button type="button" class="btn btn-default">Export Form</button>
			<input type="file" style="display: none;">
			<button type="button" class="btn btn-default">Import Form</button>
		</div> -->
		<div class="col-md-9" style="text-align: right;">
			<!-- <button id="modifyBtn" type="button" class="btn btn-default">modify</button> -->
			<input type="button" class="btn btn-default" onclick="javascript:history.back(-1);" value="modify">
			<!-- <button  type="submit" class="btn btn-default">submit</button> -->
			<input id="submitBtn" type="button" class="btn btn-default" value="submit"/> 
		</div>
	</div>	    
	</form>		
    		
	</div>
	<script src="/ffp/resources/ffp/jquery/jquery-3.1.1.js"></script>
	<script src="/ffp/resources/ffp/bootstrap/js/bootstrap.js"></script>
	
	<script>	
		//Define List Name for bankSim.js
		var listName = "creditTransferTransactionList";	
	</script>
	
	<!-- 打开之后页面显示会乱 -->
	<!-- <script src="/ffp/resources/ffp/bankSim/js/bankSim.js"></script> -->

	<script>	
	
		$(document).ready(function(){
		/*<![CDATA[*/
			var supressFields = null;
		/*]]>*/
			if(!!supressFields){
				for(var i=0; i<supressFields.length; i++){
					$("*[name='" + supressFields[i]).parents("div[class*=col-md]").addClass("has-error");
				}
			}
			//关闭easyUI的对话框
			jQuery(function($){
				top.$.messager.progress('close');
			});
			
			///设为只读
			$("#ctForm").find("input").attr("readonly", "readonly");
			//防止select标签可编辑
			$("#ctForm").find("select").attr("disabled", "disabled");
		});
		
		/* $("#modifyBtn").click(function(){
			 //方案一、修改只读状态为可编辑
			 $("#ctForm").find("input,select").removeAttr("readOnly");
			 //方案二、返回上一个页面进行重复修改 见按钮修改
			 
		}); */
		
		$("#submitBtn").click(function(){
			 //让select选项可提交
			 $("#ctForm").find("select").removeAttr("disabled");
			 $("#ctForm").submit();
		});
		
	</script>
	
<div style="position: fixed; bottom: 10px; right: 10px; z-index: 9999; width: 300px;">
</div>
</body>
</html>