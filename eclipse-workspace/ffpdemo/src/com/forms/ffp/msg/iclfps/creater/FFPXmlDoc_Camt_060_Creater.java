package com.forms.ffp.msg.iclfps.creater;

import java.util.Date;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.AccountIdentification4Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.AccountReportingRequestV03;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.FPSCurrencyCode;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.GenericAccountIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.GroupHeader591;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.Party12Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.xsd.camt_060_001.ReportingRequest31;
import com.forms.ffp.msg.iclfps.FFPMessageXmlValueUtils;
import com.forms.ffp.msg.iclfps.bussiness.FFPAccountBalanceEnquiryBean;
import com.forms.ffp.msg.iclfps.bussiness.FFPBaseBussinessBean;
import com.forms.ffp.msg.iclfps.bussiness.FFPPaymentFormBean;

public class FFPXmlDoc_Camt_060_Creater implements FFPXmlDocCreater
{
	private ObjectFactory _objFactory = new ObjectFactory();

	public Object createDocument(FFPBaseBussinessBean bean)
	{
		Document doc = this._objFactory.createDocument();

		AccountReportingRequestV03 acctRptReq = this._objFactory.createAccountReportingRequestV03();

		FFPPaymentFormBean paymentBean = (FFPPaymentFormBean) bean;
		if (paymentBean.getAccountBalanceEnquiryRequest() != null)
		{
			acctRptReq.setRptgReq(createReportingRequest(paymentBean.getAccountBalanceEnquiryRequest()));
		}

		GroupHeader591 grpHdr = this._objFactory.createGroupHeader591();
		grpHdr.setCreDtTm(FFPMessageXmlValueUtils.toGregorianDtType1(new Date()));
		grpHdr.setMsgId(paymentBean.getMessageId());

		acctRptReq.setGrpHdr(grpHdr);
		doc.setAcctRptgReq(acctRptReq);

		return doc;
	}

	private ReportingRequest31 createReportingRequest(FFPAccountBalanceEnquiryBean tx)
	{
		ReportingRequest31 rptgReq = this._objFactory.createReportingRequest31();

		rptgReq.setAcct(createCashAccount1(tx.getAccountId(), tx.getAccountCurrency()));
		rptgReq.setAcctOwnr(createPartyChoice1(tx.getAccountOwnerId(), tx.getAccountOwnerBic()));
		rptgReq.setReqdMsgNmId(tx.getRequiredMessageNameId());

		return rptgReq;
	}

	private CashAccount241 createCashAccount1(String accNum, String accCurrency)
	{
		accNum = FFPMessageXmlValueUtils.getStringValue(accNum);
		accCurrency = FFPMessageXmlValueUtils.getStringValue(accCurrency);
		if (FFPMessageXmlValueUtils.isNullObject(new Object[] { accNum, accCurrency }).booleanValue())
		{
			return null;
		}
		CashAccount241 cashAcct = this._objFactory.createCashAccount241();

		cashAcct.setCcy(FPSCurrencyCode.fromValue(accCurrency));

		GenericAccountIdentification11 othr = this._objFactory.createGenericAccountIdentification11();
		othr.setId(accNum);

		AccountIdentification4Choice1 id = this._objFactory.createAccountIdentification4Choice1();
		id.setOthr(othr);

		cashAcct.setId(id);

		return cashAcct;
	}

	private Party12Choice1 createPartyChoice1(String mmbId, String mmbBic)
	{
		Party12Choice1 id = this._objFactory.createParty12Choice1();

		id.setAgt(createBranchAndFinancialInstitutionIdentification1(mmbId, mmbBic));

		return id;
	}

	private BranchAndFinancialInstitutionIdentification51 createBranchAndFinancialInstitutionIdentification1(String mmbId, String mmbBic)
	{
		mmbId = FFPMessageXmlValueUtils.getStringValue(mmbId);
		mmbBic = FFPMessageXmlValueUtils.getStringValue(mmbBic);
		if (FFPMessageXmlValueUtils.isNullObject(new Object[] { mmbId, mmbBic }).booleanValue())
		{
			return null;
		}
		BranchAndFinancialInstitutionIdentification51 id = this._objFactory.createBranchAndFinancialInstitutionIdentification51();

		ClearingSystemMemberIdentification21 clrSysMmbId = this._objFactory.createClearingSystemMemberIdentification21();
		clrSysMmbId.setMmbId(mmbId);

		FinancialInstitutionIdentification81 finInstnId = this._objFactory.createFinancialInstitutionIdentification81();
		finInstnId.setBICFI(mmbBic);
		finInstnId.setClrSysMmbId(clrSysMmbId);

		id.setFinInstnId(finInstnId);

		return id;
	}
}
