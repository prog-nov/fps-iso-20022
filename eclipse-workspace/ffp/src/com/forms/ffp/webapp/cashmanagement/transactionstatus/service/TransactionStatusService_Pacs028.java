package com.forms.ffp.webapp.cashmanagement.transactionstatus.service;

import java.util.Date;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.FIToFIPaymentStatusRequestV01;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.GroupHeader531;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.OriginalGroupInformation31;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.OriginalTransactionReference241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_028_001_01.PaymentTransaction731;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;

public class TransactionStatusService_Pacs028 extends FFPMsgBaseHkiclMessage
{
	private FFPVO_Pacs028 pacs028;

	public TransactionStatusService_Pacs028(FFPVO_Pacs028 pacs028)
	{
		super();
		this.pacs028 = pacs028;
		this.sendType = FFPConstants.SEND_TYPE_REQ;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_PACS_028;
		this.priority = FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYENQ;
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
		FIToFIPaymentStatusRequestV01 request = new FIToFIPaymentStatusRequestV01();
		GroupHeader531 groupHeader531 = new GroupHeader531();
		groupHeader531.setMsgId(pacs028.getMsgId());
		groupHeader531.setCreDtTm(FFPXMLUtils.toGregorianDt(new Date()));

		PaymentTransaction731 paymentTransaction731 = new PaymentTransaction731();
		paymentTransaction731.setStsReqId(null);
		paymentTransaction731.setOrgnlTxId(pacs028.getTransactionId());
		paymentTransaction731.setClrSysRef(pacs028.getClrSysRef());
		OriginalGroupInformation31 orgnlGrpInf = new OriginalGroupInformation31();
		orgnlGrpInf.setOrgnlMsgId("NOTPROVIDED");
		orgnlGrpInf.setOrgnlMsgNmId("NOTPROVIDED");
		OriginalTransactionReference241 orgnlTxRef = new OriginalTransactionReference241();
		BranchAndFinancialInstitutionIdentification51 dbtrAgt = new BranchAndFinancialInstitutionIdentification51();
		FinancialInstitutionIdentification81 dbfinInstnId = new FinancialInstitutionIdentification81();
		ClearingSystemMemberIdentification21 dbclrSysMmbId = new ClearingSystemMemberIdentification21();
		dbclrSysMmbId.setMmbId(pacs028.getDbtrAgtMmbId());
		dbfinInstnId.setBICFI(null);
		dbfinInstnId.setClrSysMmbId(dbclrSysMmbId);
		dbtrAgt.setFinInstnId(dbfinInstnId);
		orgnlTxRef.setDbtrAgt(dbtrAgt);
		BranchAndFinancialInstitutionIdentification51 cdtrAgt = new BranchAndFinancialInstitutionIdentification51();
		FinancialInstitutionIdentification81 cdfinInstnId = new FinancialInstitutionIdentification81();
		ClearingSystemMemberIdentification21 cdclrSysMmbId = new ClearingSystemMemberIdentification21();
		cdclrSysMmbId.setMmbId(pacs028.getCdtrAgtMmbId());
		cdfinInstnId.setBICFI(null);
		cdfinInstnId.setClrSysMmbId(cdclrSysMmbId);
		cdtrAgt.setFinInstnId(cdfinInstnId);
		orgnlTxRef.setCdtrAgt(cdtrAgt);
		paymentTransaction731.setOrgnlGrpInf(orgnlGrpInf);
		paymentTransaction731.setOrgnlTxRef(orgnlTxRef);
		request.setGrpHdr(groupHeader531);
		request.setTxInf(paymentTransaction731);
		doc.setFIToFIPmtStsReq(request);
		return doc;
	}

}
