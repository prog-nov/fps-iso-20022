package com.forms.batch.job.unit.participant.message.ffpddo01;

import java.math.BigDecimal;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.FPSBusinessServiceCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.AccountIdentification4Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.AccountIdentification4Choice2;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.AccountSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.AccountSchemeName1Choice2;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.CashAccount242;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.CategoryPurpose1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.ChargeBearerType1Code1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Charges21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.ClearingSystemIdentification3Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.ContactDetails21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.DirectDebitTransaction91;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.DirectDebitTransactionInformation211;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FIToFICustomerDirectDebitV07;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSAccountTypeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSAccountTypeCode1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSCategoryPurposeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSClearingSystemCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSCurrencyCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FPSCustomerCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GenericAccountIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GenericAccountIdentification12;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GenericOrganisationIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GenericPersonIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.GroupHeader501;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.MandateRelatedInformation111;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.OrganisationIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.OrganisationIdentificationSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Party11Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.PartyIdentification431;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.PaymentIdentification31;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.PaymentTypeInformation251;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.PersonIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.PersonIdentificationSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Purpose2Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.RemittanceInformation111;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.Restricted15Digit2DecimalCurrencyAndAmount;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.SettlementInstruction21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_003_001_07.SettlementMethod2Code1;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.core.utils.FFPValidateUtils;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;

public class FFPMsgDDO01_Pacs003 extends FFPMsgBaseHkiclMessage
{

	private FFPJbP210 txJb = null;

	private ObjectFactory _objFactory = new ObjectFactory();

	public FFPMsgDDO01_Pacs003(FFPJbP210 txJb)
	{
		super();
		this.txJb = txJb;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_PACS_003;
		if (FFPConstantsServiceCode.FFPAGENT_SERVICECODE_D1.equals(txJb.getSrvcMode()))
		{
			this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYD01;
		}
	}

	public JAXBElement<BusinessApplicationHeaderV01> marshalMsgBizDataHead()
	{
		BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
		appHdr.setFr(createParty(this.msgFromID));
		appHdr.setTo(createParty(this.msgToID));
		appHdr.setBizMsgIdr(this.msgID);
		appHdr.setMsgDefIdr(this.msgTypeName);
		appHdr.setBizSvc(FPSBusinessServiceCode.fromValue(this.msgBizSvc));
		appHdr.setCreDt(FFPXMLUtils.toGregorianDt(this.creDt));
		return (new com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.ObjectFactory()).createAppHdr(appHdr);
	}

	public JAXBElement<?> marshalMsgBizDataDocument()
	{
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument()
	{
		Document doc = this._objFactory.createDocument();
		// doc root
		FIToFICustomerDirectDebitV07 fIToFICustomerDirectDebitV07 = this._objFactory.createFIToFICustomerDirectDebitV07();
		// doc head
		GroupHeader501 groupHeader501 = this._objFactory.createGroupHeader501();
		groupHeader501.setCreDtTm(FFPXMLUtils.toGregorianDtType1(this.getCreDt()));
		groupHeader501.setMsgId(this.getMsgID());
		groupHeader501.setNbOfTxs("1");

		SettlementInstruction21 settlementInstruction21 = this._objFactory.createSettlementInstruction21();
		ClearingSystemIdentification3Choice1 clearingSystemIdentification3Choice1 = this._objFactory.createClearingSystemIdentification3Choice1();
		clearingSystemIdentification3Choice1.setPrtry(FPSClearingSystemCode.FPS);
		settlementInstruction21.setClrSys(clearingSystemIdentification3Choice1);
		settlementInstruction21.setSttlmMtd(SettlementMethod2Code1.CLRG);
		groupHeader501.setSttlmInf(settlementInstruction21);
		fIToFICustomerDirectDebitV07.setGrpHdr(groupHeader501);

		// doc body
		fIToFICustomerDirectDebitV07.setDrctDbtTxInf(createDirectDebitTransactionInformation211());
		doc.setFIToFICstmrDrctDbt(fIToFICustomerDirectDebitV07);
		return doc;
	}

	private DirectDebitTransactionInformation211 createDirectDebitTransactionInformation211()
	{
		DirectDebitTransactionInformation211 txInfo = this._objFactory.createDirectDebitTransactionInformation211();

		PaymentIdentification31 loc_pi31 = new PaymentIdentification31();
		loc_pi31.setEndToEndId(txJb.getTxJnl().getEndToEndId());
		loc_pi31.setTxId(txJb.getTxJnl().getTransactionId());
		txInfo.setPmtId(loc_pi31);
		txInfo.setPmtTpInf(createPaymentTypeInformation());

		txInfo.setIntrBkSttlmAmt(createCurrencyAndAmount(txJb.getSettlementCurrency(), txJb.getSettlementAmount()));
		txInfo.setIntrBkSttlmDt(FFPXMLUtils.toGregorianDtNoTs(txJb.getSettlementDate()));
		txInfo.setInstdAmt(createCurrencyAndAmount(txJb.getInstructedCurrency(), txJb.getInstructedAmount()));

		txInfo.setChrgBr(ChargeBearerType1Code1.SLEV);
		txInfo.setChrgsInf(createCharges());

		txInfo.setDrctDbtTx(createDirectDebitTransaction91());

		PartyIdentification431 cdtr = createPartyIdentification1(txJb.getCreditorName(), txJb.getCreditorOrgIdAnyBIC(), txJb.getCreditorOrgIdOthrId(), txJb.getCreditorOrgIdOthrIdSchmeNm(),
				txJb.getCreditorOrgIdOthrIssr(), txJb.getCreditorPrvtIdOthrId(), txJb.getCreditorPrvtIdOthrIdSchmeNm(), txJb.getCreditorPrvtIdOthrIssr(), txJb.getCreditorContPhone(),
				txJb.getCreditorContEmailAddr());
		txInfo.setCdtr(cdtr);
		txInfo.setCdtrAcct(createCashAccount241(txJb.getCreditorAccountNumber(), txJb.getCreditorAccountNumberType()));
		txInfo.setCdtrAgt(createBranchAndFinancialInstitutionIdentification1(txJb.getCreditorAgentId(), txJb.getCreditorAgentBic()));

		PartyIdentification431 dbtr = createPartyIdentification1(txJb.getDebtorName(), txJb.getDebtorOrgIdAnyBIC(), txJb.getDebtorOrgIdOthrId(), txJb.getDebtorOrgIdOthrIdSchmeNm(),
				txJb.getDebtorOrgIdOthrIssr(), txJb.getDebtorPrvtIdOthrId(), txJb.getDebtorPrvtIdOthrIdSchmeNm(), txJb.getDebtorPrvtIdOthrIssr(), txJb.getDebtorContPhone(),
				txJb.getDebtorContEmailAddr());

		txInfo.setDbtr(dbtr);
		txInfo.setDbtrAcct(createCashAccount242(txJb.getDebtorAccountNumber(), txJb.getDebtorAccountNumberType()));
		txInfo.setDbtrAgt(createBranchAndFinancialInstitutionIdentification1(txJb.getDebtorAgentId(), txJb.getDebtorAgentBic()));

		txInfo.setPurp(createPurpose2Choice());

		txInfo.setRmtInf(createRemittanceInformation());

		return txInfo;
	}

	private DirectDebitTransaction91 createDirectDebitTransaction91()
	{
		DirectDebitTransaction91 directDebitTransaction91 = this._objFactory.createDirectDebitTransaction91();
		MandateRelatedInformation111 mandateRelatedInformation111 = this._objFactory.createMandateRelatedInformation111();
		mandateRelatedInformation111.setMndtId(txJb.getDrctDbtTxMndtId());
		directDebitTransaction91.setMndtRltdInf(mandateRelatedInformation111);

		return directDebitTransaction91;
	}

	private RemittanceInformation111 createRemittanceInformation()
	{
		RemittanceInformation111 rmtInf = this._objFactory.createRemittanceInformation111();
		rmtInf.setUstrd(txJb.getRemittanceInformation());
		return rmtInf;
	}

	private Purpose2Choice1 createPurpose2Choice()
	{
		if (FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(txJb.getPaymentPurposeType()))
		{
			Purpose2Choice1 purp = this._objFactory.createPurpose2Choice1();
			purp.setCd(txJb.getPaymentPurposeCd());
			return purp;
		}
		if (FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(txJb.getPaymentPurposeType()))
		{
			Purpose2Choice1 purp = this._objFactory.createPurpose2Choice1();
			purp.setPrtry(txJb.getPaymentPurposeProprietary());
			return purp;
		}
		return null;
	}

	private PaymentTypeInformation251 createPaymentTypeInformation()
	{
		PaymentTypeInformation251 pmtTpInf = this._objFactory.createPaymentTypeInformation251();
		CategoryPurpose1Choice1 ctgyPurp = this._objFactory.createCategoryPurpose1Choice1();
		ctgyPurp.setPrtry(FPSCategoryPurposeCode.fromValue(txJb.getPymtCatPrps()));
		pmtTpInf.setCtgyPurp(ctgyPurp);
		return pmtTpInf;
	}

	private Restricted15Digit2DecimalCurrencyAndAmount createCurrencyAndAmount(String currency, BigDecimal amount)
	{
		String loc_currency = FFPStringUtils.getStringValue(currency);
		if (FFPValidateUtils.isNullObject(new Object[] { amount }).booleanValue())
		{
			return null;
		}
		Restricted15Digit2DecimalCurrencyAndAmount currencyAndAmount = this._objFactory.createRestricted15Digit2DecimalCurrencyAndAmount();
		currencyAndAmount.setCcy(FPSCurrencyCode.fromValue(loc_currency));
		currencyAndAmount.setValue(amount);

		return currencyAndAmount;
	}

	private Charges21 createCharges()
	{
		Charges21 charges = this._objFactory.createCharges21();
		charges.setAgt(createBranchAndFinancialInstitutionIdentification1(txJb.getChargersAgentId(), txJb.getChargersAgentBic()));
		charges.setAmt(createCurrencyAndAmount(txJb.getChargersCurrency(), txJb.getChargersAmount()));

		return charges;
	}

	private PartyIdentification431 createPartyIdentification1(String acctName, String OrgIdAnyBIC, String OrgIdOthrId, String OrgIdOthrIdSchmeNm, String OrgIdOthrIssr, String PrvtIdOthrId,
			String PrvtIdOthrIdSchmeNm, String PrvtIdOthrIssr, String phoneNo, String emailAddr)
	{
		PartyIdentification431 pi431 = this._objFactory.createPartyIdentification431();
		pi431.setNm(acctName);

		if (!FFPStringUtils.isEmptyOrNull(PrvtIdOthrId))
		{
			Party11Choice1 id = this._objFactory.createParty11Choice1();
			PersonIdentification51 prvtId = this._objFactory.createPersonIdentification51();
			GenericPersonIdentification11 othr = this._objFactory.createGenericPersonIdentification11();
			PersonIdentificationSchemeName1Choice1 schmeNm = this._objFactory.createPersonIdentificationSchemeName1Choice1();
			schmeNm.setCd(FPSCustomerCode.fromValue(PrvtIdOthrIdSchmeNm));
			othr.setId(PrvtIdOthrId);
			othr.setSchmeNm(schmeNm);
			prvtId.setOthr(othr);
			id.setPrvtId(prvtId);
			pi431.setId(id);
		} else if (!FFPStringUtils.isEmptyOrNull(OrgIdOthrId))
		{
			Party11Choice1 id = this._objFactory.createParty11Choice1();
			OrganisationIdentification81 orgId = this._objFactory.createOrganisationIdentification81();
			GenericOrganisationIdentification11 othr = this._objFactory.createGenericOrganisationIdentification11();
			OrganisationIdentificationSchemeName1Choice1 schmeNm = this._objFactory.createOrganisationIdentificationSchemeName1Choice1();
			schmeNm.setCd(FPSCustomerCode.fromValue(OrgIdOthrIdSchmeNm));
			othr.setSchmeNm(schmeNm);
			othr.setId(OrgIdOthrId);
			othr.setIssr(OrgIdOthrIssr);
			orgId.setOthr(othr);
			orgId.setAnyBIC(OrgIdAnyBIC);
			id.setOrgId(orgId);
			pi431.setId(id);
		}

		ContactDetails21 details = this._objFactory.createContactDetails21();
		details.setMobNb(phoneNo);
		details.setEmailAdr(emailAddr);
		pi431.setCtctDtls(details);
		return pi431;
	}

	private CashAccount242 createCashAccount242(String accNum, String accNumType)
	{
		CashAccount242 cashAcct = this._objFactory.createCashAccount242();

		AccountSchemeName1Choice2 schmeNm = this._objFactory.createAccountSchemeName1Choice2();
		schmeNm.setPrtry(FPSAccountTypeCode1.fromValue(accNumType));

		GenericAccountIdentification12 othr = this._objFactory.createGenericAccountIdentification12();
		othr.setId(accNum);
		othr.setSchmeNm(schmeNm);

		AccountIdentification4Choice2 id = this._objFactory.createAccountIdentification4Choice2();
		id.setOthr(othr);
		cashAcct.setId(id);

		return cashAcct;
	}

	private CashAccount241 createCashAccount241(String accNum, String accNumType)
	{
		CashAccount241 cashAcct = this._objFactory.createCashAccount241();

		AccountSchemeName1Choice1 schmeNm = this._objFactory.createAccountSchemeName1Choice1();
		schmeNm.setPrtry(FPSAccountTypeCode.fromValue(accNumType));

		GenericAccountIdentification11 othr = this._objFactory.createGenericAccountIdentification11();
		othr.setId(accNum);
		othr.setSchmeNm(schmeNm);

		AccountIdentification4Choice1 id = this._objFactory.createAccountIdentification4Choice1();
		id.setOthr(othr);

		cashAcct.setId(id);

		return cashAcct;
	}

	private BranchAndFinancialInstitutionIdentification51 createBranchAndFinancialInstitutionIdentification1(String mmbId, String mmbBic)
	{
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
