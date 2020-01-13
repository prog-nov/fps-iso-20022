package com.forms.ffp.webapp.cashmanagement.accountinquery.service;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.AccountIdentification4Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.AccountReportingRequestV03;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.FPSCurrencyCode;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.GenericAccountIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.GroupHeader591;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.Party12Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.camt_060_001_03.ReportingRequest31;
import com.forms.ffp.core.config.runtime.FFPRuntimeConstants;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.webapp.cashmanagement.accountinquery.form.AccountBalanceInquiryForm;

public class AccountBalanceInquiryService_Camt060 extends FFPMsgBaseHkiclMessage
{
	private AccountBalanceInquiryForm form;
	
	public AccountBalanceInquiryService_Camt060(AccountBalanceInquiryForm form)
	{
		super();
		this.form = form;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_CAMT_060;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYENQ;
		this.sendType = FFPConstants.SEND_TYPE_REQ;
		this.priority = FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM;
	}

	public JAXBElement<?> marshalMsgBizDataDocument()
	{
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument()
	{
		ObjectFactory _objectfactory = new ObjectFactory();
		Document doc = _objectfactory.createDocument();
		AccountReportingRequestV03 v03 = _objectfactory.createAccountReportingRequestV03();
		GroupHeader591 gHeader = _objectfactory.createGroupHeader591();
		v03.setGrpHdr(gHeader);
		
		gHeader.setMsgId(this.msgID);
		gHeader.setCreDtTm(FFPXMLUtils.toGregorianDtType1(new Date()));
		
		ReportingRequest31 request = _objectfactory.createReportingRequest31();
		v03.setRptgReq(request);
		
		request.setReqdMsgNmId(FFPJaxbConstants.JAXB_MSG_TYPE_CAMT_052);
		
		CashAccount241 acct = _objectfactory.createCashAccount241();
		AccountIdentification4Choice1 acctId = _objectfactory.createAccountIdentification4Choice1();
		GenericAccountIdentification11 gai11 = _objectfactory.createGenericAccountIdentification11();
		gai11.setId(FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID);
		acctId.setOthr(gai11);
		acct.setId(acctId);
		acct.setCcy(FPSCurrencyCode.fromValue(form.getCurrency()));
		request.setAcct(acct);
		
		Party12Choice1 party = _objectfactory.createParty12Choice1();
		BranchAndFinancialInstitutionIdentification51 b51 = _objectfactory.createBranchAndFinancialInstitutionIdentification51();
		FinancialInstitutionIdentification81 f81 = _objectfactory.createFinancialInstitutionIdentification81();
		ClearingSystemMemberIdentification21 c21 = _objectfactory.createClearingSystemMemberIdentification21();
		c21.setMmbId(FFPRuntimeConstants.LOCAL_FPS_PARTICIPANT_ID);
		f81.setClrSysMmbId(c21);
		b51.setFinInstnId(f81);
		party.setAgt(b51);
		request.setAcctOwnr(party);
		
		doc.setAcctRptgReq(v03);
		
		return doc;
	}
}
