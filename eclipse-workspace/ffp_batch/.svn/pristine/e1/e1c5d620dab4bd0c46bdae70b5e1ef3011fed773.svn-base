package com.forms.batch.job.unit.participant.message.ffpcto01;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.BatchInformation;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessagePayloads;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.FPSBusinessServiceCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.AccountIdentification4Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.AccountSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CategoryPurpose1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.ChargeBearerType1Code1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Charges21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.ClearingSystemIdentification3Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.ClearingSystemMemberIdentification21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.ContactDetails21;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.CreditTransferTransaction251;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FIToFICustomerCreditTransferV06;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSAccountTypeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSAccountVerificationOptionCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSCategoryPurposeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSClearingSystemCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSCurrencyCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FPSCustomerCode;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.FinancialInstitutionIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GenericAccountIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GenericOrganisationIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GenericPersonIdentification11;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.GroupHeader701;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.LocalInstrument2Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.OrganisationIdentification81;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.OrganisationIdentificationSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Party11Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.PartyIdentification431;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.PaymentIdentification31;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.PaymentTypeInformation211;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.PersonIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.PersonIdentificationSchemeName1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Purpose2Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.RemittanceInformation111;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.Restricted15Digit2DecimalCurrencyAndAmount;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.SettlementInstruction41;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_008_001_06.SettlementMethod1Code1;
import com.forms.ffp.core.define.FFPConstantsPurposeCode;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPHkiclMessageConverter;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;

public class FFPMsgCTO01_MutiPacs008 extends FFPMsgBaseHkiclMessage
{
	private List<FFPJbP100> txJb = null;
	
	private Map<String,String> btchMap = null;
	
	private ObjectFactory _objFactory = new ObjectFactory();

	public FFPMsgCTO01_MutiPacs008(List<FFPJbP100> txJb)
	{
		super();
		this.txJb = txJb;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC03;
		//just for C3
		
		/*if (FFPConstantsServiceCode.FFPAGENT_SERVICECODE_C1.equals(txJb.getSrvcMode())){
			this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC01;
			this.priority = "H";
		}else{
			this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC02;
			this.priority = "M";
		}*/
	}

	public  FFPMsgCTO01_MutiPacs008(List<FFPJbP100> txJb,Map<String,String> btchMap) {
		super();
		this.txJb = txJb;
		this.btchMap = btchMap;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_PACS_008;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC03;
	}
	
	@Override
	public String parseHkiclMessage() throws Exception {
		String message = null;
		FpsMessageEnvelope envelope = new FpsMessageEnvelope();
		BatchInformation BtchInf = new BatchInformation();
		
		BtchInf.setBtchId(btchMap.get("BtchId"));
		BtchInf.setFlSeqNo(btchMap.get("FlSeqNo"));
		BtchInf.setNbOfFls(btchMap.get("NbOfFls"));
		envelope.setBtchInf(BtchInf);
		envelope.setNbOfMsgs(btchMap.get("NbOfMsgs"));
		
		List<ISO20022BusinessDataV01> bizData = this.marshalMsgBizDataList(); ///bizData
		FpsMessagePayloads payloads = new FpsMessagePayloads();
		payloads.getBizData().addAll(bizData);
		envelope.setFpsPylds(payloads);

		message = FFPHkiclMessageConverter.makeupRealTimeXml(FFPHkiclMessageConverter.packageXml(envelope));

		message = FFPHkiclMessageConverter.signXml(message);

		return message;
	}


	public JAXBElement<BusinessApplicationHeaderV01> marshalMsgBizDataHead()
	{
		BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
		appHdr.setFr(createParty(this.msgFromID));
		appHdr.setTo(createParty(this.msgToID));
		appHdr.setBizMsgIdr(this.getMsgID());
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
		FIToFICustomerCreditTransferV06 fIToFICstmrCdtTrf = this._objFactory.createFIToFICustomerCreditTransferV06();

		//Multi pacs008
		for(FFPJbP100 p100 : txJb)
		{
			CreditTransferTransaction251 sigleTxInf = createCreditTransferTranaction(p100);
			fIToFICstmrCdtTrf.getCdtTrfTxInf().add(sigleTxInf);
		}
		GroupHeader701 loc_gh = this._objFactory.createGroupHeader701();
		loc_gh.setCreDtTm(FFPXMLUtils.toGregorianDtType1(this.getCreDt()));
		loc_gh.setMsgId(this.getMsgID());
		loc_gh.setNbOfTxs(this.btchMap.get("NbOfTxs"));
		SettlementInstruction41 loc_si41 = new SettlementInstruction41();
		loc_si41.setSttlmMtd(SettlementMethod1Code1.CLRG);
		ClearingSystemIdentification3Choice1 loc_cyi3c1 = new ClearingSystemIdentification3Choice1();
		loc_cyi3c1.setPrtry(FPSClearingSystemCode.FPS);
		loc_si41.setClrSys(loc_cyi3c1);
		loc_gh.setSttlmInf(loc_si41);

		fIToFICstmrCdtTrf.setGrpHdr(loc_gh);
		doc.setFIToFICstmrCdtTrf(fIToFICstmrCdtTrf);
		return doc;
	}

	private CreditTransferTransaction251 createCreditTransferTranaction(FFPJbP100 txJb)
	{
		CreditTransferTransaction251 cdtTrfTx = this._objFactory.createCreditTransferTransaction251();

		PaymentIdentification31 loc_pi31 = new PaymentIdentification31();
		loc_pi31.setEndToEndId(txJb.getTxJnl().getEndToEndId());
		loc_pi31.setTxId(txJb.getTxJnl().getTransactionId());
		cdtTrfTx.setPmtId(loc_pi31);

		cdtTrfTx.setPmtTpInf(createPaymentTypeInformation(txJb.getAccountVerification(), txJb.getPymtCatPrps()));
		cdtTrfTx.setIntrBkSttlmAmt(createCurrencyAndAmount(txJb.getSettlementCurrency(), txJb.getSettlementAmount()));
		cdtTrfTx.setIntrBkSttlmDt(FFPXMLUtils.toGregorianDtNoTs(txJb.getSettlementDate()));

		cdtTrfTx.setInstdAmt(createCurrencyAndAmount(txJb.getInstructedCurrency(), txJb.getInstructedAmount()));
		cdtTrfTx.setChrgsInf(createCharges(txJb.getChargersAgentId(), txJb.getChargersAgentBic(), txJb.getChargersCurrency(), txJb.getChargersAmount()));
		cdtTrfTx.setChrgBr(ChargeBearerType1Code1.SLEV);

		PartyIdentification431 dbtr = createPartyIdentification1(txJb.getDebtorName(), txJb.getDebtorOrgIdAnyBIC(),
															txJb.getDebtorOrgIdOthrId(), txJb.getDebtorOrgIdOthrIdSchmeNm(), txJb.getDebtorOrgIdOthrIssr(),
															txJb.getDebtorPrvtIdOthrId(), txJb.getDebtorPrvtIdOthrIdSchmeNm(), txJb.getDebtorPrvtIdOthrIssr(),
															txJb.getDebtorContPhone(), txJb.getDebtorContEmailAddr());

		
		cdtTrfTx.setDbtr(dbtr);
		cdtTrfTx.setDbtrAcct(createCashAccount(txJb.getDebtorAccountNumber(), txJb.getDebtorAccountNumberType()));
		cdtTrfTx.setDbtrAgt(createBranchAndFinancialInstitutionIdentification1(txJb.getDebtorAgentId(), txJb.getDebtorAgentBic()));

		cdtTrfTx.setCdtrAgt(createBranchAndFinancialInstitutionIdentification1(txJb.getCreditorAgentId(), txJb.getCreditorAgentBic()));
		PartyIdentification431 cdtr = createPartyIdentification1(txJb.getCreditorName(), txJb.getCreditorOrgIdAnyBIC(),
															txJb.getCreditorOrgIdOthrId(), txJb.getCreditorOrgIdOthrIdSchmeNm(), txJb.getCreditorOrgIdOthrIssr(),
															txJb.getCreditorPrvtIdOthrId(), txJb.getCreditorPrvtIdOthrIdSchmeNm(), txJb.getCreditorPrvtIdOthrIssr(),
															txJb.getCdtrContPhone(), txJb.getCdtrContEmailAddr());
		cdtTrfTx.setCdtr(cdtr);
		cdtTrfTx.setCdtrAcct(createCashAccount(txJb.getCreditorAccountNumber(), txJb.getCreditorAccountNumberType()));

		cdtTrfTx.setPurp(createPurpose2Choice(txJb.getPaymentPurposeType(), txJb.getPaymentPurposeCd(), txJb.getPaymentPurposeProprietary()));
		cdtTrfTx.setRmtInf(createRemittanceInformation(txJb.getRemittanceInformation()));

		return cdtTrfTx;
	}

	private PaymentTypeInformation211 createPaymentTypeInformation(String accountVerification, String purposeCode)
	{
		accountVerification = FFPXMLUtils.getStringValue(accountVerification);
		purposeCode = FFPXMLUtils.getStringValue(purposeCode);

		if (FFPXMLUtils.isNullObject(new Object[] { accountVerification, purposeCode }).booleanValue())
		{
			return null;
		}
		PaymentTypeInformation211 pmtTpInf = this._objFactory.createPaymentTypeInformation211();

		if (accountVerification != null)
		{
			LocalInstrument2Choice1 lclInstrm = this._objFactory.createLocalInstrument2Choice1();
			lclInstrm.setPrtry(FPSAccountVerificationOptionCode.fromValue(accountVerification));
			pmtTpInf.setLclInstrm(lclInstrm);
		}

		if (purposeCode != null)
		{
			CategoryPurpose1Choice1 ctgyPurp = this._objFactory.createCategoryPurpose1Choice1();
			ctgyPurp.setPrtry(FPSCategoryPurposeCode.fromValue(purposeCode));
			pmtTpInf.setCtgyPurp(ctgyPurp);
		}

		return pmtTpInf;
	}

	private PartyIdentification431 createPartyIdentification1(String acctName, String OrgIdAnyBIC,
												String OrgIdOthrId, String OrgIdOthrIdSchmeNm, String OrgIdOthrIssr,
												String PrvtIdOthrId, String PrvtIdOthrIdSchmeNm, String PrvtIdOthrIssr,
												String phoneNo, String emailAddr)
	{
		PartyIdentification431 pi431 = this._objFactory.createPartyIdentification431();
		pi431.setNm(acctName);

		if(!FFPStringUtils.isEmptyOrNull(PrvtIdOthrId))
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
		}
		else if(!FFPStringUtils.isEmptyOrNull(OrgIdOthrId))
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
		
		pi431.setCtctDtls(createContactDetails(phoneNo, emailAddr));
		return pi431;
	}
	
	private ContactDetails21 createContactDetails(String phoneNo, String emailAddr)
	{
		String mobileNumber = FFPXMLUtils.getStringValue(phoneNo);
		String emailAddress = FFPXMLUtils.getStringValue(emailAddr);

		if (FFPXMLUtils.isNullObject(new Object[] { mobileNumber, emailAddress }).booleanValue())
		{
			return null;
		}
		ContactDetails21 ctctDtls = this._objFactory.createContactDetails21();
		ctctDtls.setMobNb(mobileNumber);
		ctctDtls.setEmailAdr(emailAddress);
		return ctctDtls;
	}
	
	private CashAccount241 createCashAccount(String accNum, String accNumType)
	{
		accNum = FFPXMLUtils.getStringValue(accNum);
		accNumType = FFPXMLUtils.getStringValue(accNumType);

		if (FFPXMLUtils.isNullObject(new Object[] { accNum, accNumType }).booleanValue())
		{
			return null;
		}
		
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
		mmbId = FFPXMLUtils.getStringValue(mmbId);
		mmbBic = FFPXMLUtils.getStringValue(mmbBic);

		if (FFPXMLUtils.isNullObject(new Object[] { mmbId, mmbBic }).booleanValue())
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

	private Restricted15Digit2DecimalCurrencyAndAmount createCurrencyAndAmount(String currency, BigDecimal amount)
	{
		String loc_currency = FFPStringUtils.getStringValue(currency);
		if (FFPXMLUtils.isNullObject(new Object[] { amount }).booleanValue())
		{
			return null;
		}

		Restricted15Digit2DecimalCurrencyAndAmount currencyAndAmount = this._objFactory.createRestricted15Digit2DecimalCurrencyAndAmount();
		currencyAndAmount.setCcy(FPSCurrencyCode.fromValue(loc_currency));
		currencyAndAmount.setValue(amount);

		return currencyAndAmount;
	}

	private Charges21 createCharges(String mmbId, String mmbBic, String currency, BigDecimal amount)
	{
		mmbId = FFPXMLUtils.getStringValue(mmbId);
		mmbBic = FFPXMLUtils.getStringValue(mmbBic);
		if (FFPXMLUtils.isNullObject(new Object[] { mmbId, mmbBic, amount }).booleanValue())
		{
			return null;
		}
		
		Charges21 charges = this._objFactory.createCharges21();
		charges.setAgt(createBranchAndFinancialInstitutionIdentification1(mmbId, mmbBic));
		charges.setAmt(createCurrencyAndAmount(currency, amount));

		return charges;
	}

	private Purpose2Choice1 createPurpose2Choice(String paymentPurposetype, String purposeCd, String purposePrtry)
	{
		paymentPurposetype = FFPXMLUtils.getStringValue(paymentPurposetype);
		purposeCd = FFPXMLUtils.getStringValue(purposeCd);
		purposePrtry = FFPXMLUtils.getStringValue(purposePrtry);
		
		if (FFPXMLUtils.isNullObject(new Object[] { paymentPurposetype, purposeCd, purposePrtry }).booleanValue())
		{
			return null;
		}
		
		if (FFPConstantsPurposeCode.PURPOSE_TYPE_CODE.equals(paymentPurposetype))
		{
			Purpose2Choice1 purp = this._objFactory.createPurpose2Choice1();
			purp.setCd(purposeCd);
			return purp;
		}
		if (FFPConstantsPurposeCode.PURPOSE_TYPE_OTHER.equals(paymentPurposetype))
		{
			Purpose2Choice1 purp = this._objFactory.createPurpose2Choice1();
			purp.setPrtry(purposePrtry);
			return purp;
		}

		return null;
	}

	private RemittanceInformation111 createRemittanceInformation(String ustrd)
	{
		ustrd = FFPXMLUtils.getStringValue(ustrd);
		if (FFPXMLUtils.isNullObject(new Object[] { ustrd }).booleanValue())
		{
			return null;
		}
		
		RemittanceInformation111 rmtInf = this._objFactory.createRemittanceInformation111();
		rmtInf.setUstrd(ustrd);
		return rmtInf;
	}

}
