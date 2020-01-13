package com.forms.ffp.bussiness.iclfps.pacs008;

import java.math.BigDecimal;
import java.util.Date;

import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP110;

public class FFPVO_Pacs008_CdtTrfTxInf
{
	private FFPJbP110 p110Jb;

	private FFPVO_Pacs008_CTI01REPLY cti01Reply;

	private FFPVO_Pacs008_CTI02REPLY cti02Reply;

	// Payment Identification
	private String InstrId; // Instruction Id
	private String EndToEndId; // End-to-end Id
	private String TxId; // Transaction Id
	private String ClrSysRef; // FPS Reference Number

	// Payment Type Information
	private String LclInstrm; // Account verification
	private String CtgyPurp; // Payment Category Purpose of FPS

	private String IntrBkSttlmAmt; // Interbank Settlement Amount
	private String IntrBkSttlmAmtCcy; // Interbank Settlement Amount Currency

	private Date IntrBkSttlmDt; // Interbank Settlement Date

	private Date SttlmTmIndctn; // Settlement time indication->Credit date time:for
							// Sc.C only

	private String InstdAmt; // Instructed Amount
	private String InstdAmtCcy; // Instructed Amount Currency

	private String ChrgBr; // Charge Bearer
	private String ChrgAgentId;
	private String ChrgAgentBic;
	private String ChrgCcy;
	private BigDecimal ChrgAmount;

	// Debtor
	private String DbtrNm; // Debtor name
	// Debtor->Identification->Organisation
	private String DbtrOrgIdAnyBIC; // BIC
	private String DbtrOrgIdOthrId;
	private String DbtrOrgIdOthrIdSchmeNm;
	private String DbtrOrgIdOthrIssr;
	private String DbtrPrvtIdOthrId;
	private String DbtrPrvtIdOthrIdSchmeNm;
	private String DbtrPrvtIdOthrIssr;
	// Debtor->Contact Details
	private String DbtrMobNb; // Phone number
	private String DbtrEmailAdr; // Email address

	// Debtor Account
	private String DbtrAcctId; // Debtor Account Identification
	private String DbtrAccSchmeNm; // Debtor Account Scheme Name

	// Debtor Agent
	private String DbtrAgBICFI; // Debtor Agent BICFI
	private String DbtrAgClrSysMmbId; // Debtor Agent Clearing System Member
										// Identification

	// Creditor Agent
	private String CdtrAgtBICFI; // Creditor Agent BICFI
	private String CdtrAgtClrSysMmbId; // Creditor Agent Clearing System Member
										// Identification

	// Creditor
	private String CdtrNm; // Creditor name
	private String CdtrOrgIdAnyBIC;
	private String CdtrOrgIdOthrId;
	private String CdtrOrgIdOthrIdSchmeNm;
	private String CdtrOrgIdOthrIssr;
	private String CdtrPrvtIdOthrId;
	private String CdtrPrvtIdOthrIdSchmeNm;
	private String CdtrPrvtIdOthrIssr;

	// Creditor->Contact Details
	private String CdtrMobNb;
	private String CdtrEmailAdr;

	// Creditor Account
	private String CdtrAcctId;
	private String CdtrAcctSchmeNm;

	// Purpose
	private String PurpCd;
	private String PurpPrtry;

	// Remittance Information
	private String RmtInf;

	public class ChargeInfo
	{
		private String ChrgsInfAmt;
		private String ChrgsInfCcy;
		private String ChrgsInfAgtBICFI;
		private String ChrgsInfAgtClrSysId;

		public String getChrgsInfAmt()
		{
			return ChrgsInfAmt;
		}

		public void setChrgsInfAmt(String chrgsInfAmt)
		{
			ChrgsInfAmt = chrgsInfAmt;
		}

		public String getChrgsInfCcy()
		{
			return ChrgsInfCcy;
		}

		public void setChrgsInfCcy(String chrgsInfCcy)
		{
			ChrgsInfCcy = chrgsInfCcy;
		}

		public String getChrgsInfAgtBICFI()
		{
			return ChrgsInfAgtBICFI;
		}

		public void setChrgsInfAgtBICFI(String chrgsInfAgtBICFI)
		{
			ChrgsInfAgtBICFI = chrgsInfAgtBICFI;
		}

		public String getChrgsInfAgtClrSysId()
		{
			return ChrgsInfAgtClrSysId;
		}

		public void setChrgsInfAgtClrSysId(String chrgsInfAgtClrSysId)
		{
			ChrgsInfAgtClrSysId = chrgsInfAgtClrSysId;
		}
	}

	public class CdtrOrgIdOth
	{
		private String CdtrOrgIdId;
		private String CdtrOrgIdSchmeNm;
		private String CdtrOrgIdIssr;

		public String getCdtrOrgIdId()
		{
			return CdtrOrgIdId;
		}

		public void setCdtrOrgIdId(String cdtrOrgIdId)
		{
			CdtrOrgIdId = cdtrOrgIdId;
		}

		public String getCdtrOrgIdSchmeNm()
		{
			return CdtrOrgIdSchmeNm;
		}

		public void setCdtrOrgIdSchmeNm(String cdtrOrgIdSchmeNm)
		{
			CdtrOrgIdSchmeNm = cdtrOrgIdSchmeNm;
		}

		public String getCdtrOrgIdIssr()
		{
			return CdtrOrgIdIssr;
		}

		public void setCdtrOrgIdIssr(String cdtrOrgIdIssr)
		{
			CdtrOrgIdIssr = cdtrOrgIdIssr;
		}
	}

	public class CdtrPrvtIdOth
	{
		private String CdtrPrvtIdId;
		private String CdtrPrvtIdSchmeNm;
		private String CdtrPrvtIdIssr;

		public String getCdtrPrvtIdId()
		{
			return CdtrPrvtIdId;
		}

		public void setCdtrPrvtIdId(String cdtrPrvtIdId)
		{
			CdtrPrvtIdId = cdtrPrvtIdId;
		}

		public String getCdtrPrvtIdSchmeNm()
		{
			return CdtrPrvtIdSchmeNm;
		}

		public void setCdtrPrvtIdSchmeNm(String cdtrPrvtIdSchmeNm)
		{
			CdtrPrvtIdSchmeNm = cdtrPrvtIdSchmeNm;
		}

		public String getCdtrPrvtIdIssr()
		{
			return CdtrPrvtIdIssr;
		}

		public void setCdtrPrvtIdIssr(String cdtrPrvtIdIssr)
		{
			CdtrPrvtIdIssr = cdtrPrvtIdIssr;
		}
	}

	public class DbtrOrgIdOth
	{
		private String DbtrOrgIdId;
		private String DbtrOrgIdSchmeNm;
		private String DbtrOrgIdIssr;

		public String getDbtrOrgIdId()
		{
			return DbtrOrgIdId;
		}

		public void setDbtrOrgIdId(String dbtrOrgIdId)
		{
			DbtrOrgIdId = dbtrOrgIdId;
		}

		public String getDbtrOrgIdSchmeNm()
		{
			return DbtrOrgIdSchmeNm;
		}

		public void setDbtrOrgIdSchmeNm(String dbtrOrgIdSchmeNm)
		{
			DbtrOrgIdSchmeNm = dbtrOrgIdSchmeNm;
		}

		public String getDbtrOrgIdIssr()
		{
			return DbtrOrgIdIssr;
		}

		public void setDbtrOrgIdIssr(String dbtrOrgIdIssr)
		{
			DbtrOrgIdIssr = dbtrOrgIdIssr;
		}
	}

	public class DbtrPrvtIdOth
	{
		private String DbtrPrvtIdId;
		private String DbtrPrvtIdSchmeNm;
		private String DbtrPrvtIdIssr;

		public String getDbtrPrvtIdId()
		{
			return DbtrPrvtIdId;
		}

		public void setDbtrPrvtIdId(String dbtrPrvtIdId)
		{
			DbtrPrvtIdId = dbtrPrvtIdId;
		}

		public String getDbtrPrvtIdSchmeNm()
		{
			return DbtrPrvtIdSchmeNm;
		}

		public void setDbtrPrvtIdSchmeNm(String dbtrPrvtIdSchmeNm)
		{
			DbtrPrvtIdSchmeNm = dbtrPrvtIdSchmeNm;
		}

		public String getDbtrPrvtIdIssr()
		{
			return DbtrPrvtIdIssr;
		}

		public void setDbtrPrvtIdIssr(String dbtrPrvtIdIssr)
		{
			DbtrPrvtIdIssr = dbtrPrvtIdIssr;
		}
	}

	public String getInstrId()
	{
		return InstrId;
	}

	public void setInstrId(String instrId)
	{
		InstrId = instrId;
	}

	public String getEndToEndId()
	{
		return EndToEndId;
	}

	public void setEndToEndId(String endToEndId)
	{
		EndToEndId = endToEndId;
	}

	public String getTxId()
	{
		return TxId;
	}

	public void setTxId(String txId)
	{
		TxId = txId;
	}

	public String getClrSysRef()
	{
		return ClrSysRef;
	}

	public void setClrSysRef(String clrSysRef)
	{
		ClrSysRef = clrSysRef;
	}

	public String getLclInstrm()
	{
		return LclInstrm;
	}

	public void setLclInstrm(String lclInstrm)
	{
		LclInstrm = lclInstrm;
	}

	public String getCtgyPurp()
	{
		return CtgyPurp;
	}

	public void setCtgyPurp(String ctgyPurp)
	{
		CtgyPurp = ctgyPurp;
	}

	public String getIntrBkSttlmAmt()
	{
		return IntrBkSttlmAmt;
	}

	public void setIntrBkSttlmAmt(String intrBkSttlmAmt)
	{
		IntrBkSttlmAmt = intrBkSttlmAmt;
	}

	public String getIntrBkSttlmAmtCcy()
	{
		return IntrBkSttlmAmtCcy;
	}

	public void setIntrBkSttlmAmtCcy(String intrBkSttlmAmtCcy)
	{
		IntrBkSttlmAmtCcy = intrBkSttlmAmtCcy;
	}

	public Date getIntrBkSttlmDt()
	{
		return IntrBkSttlmDt;
	}

	public void setIntrBkSttlmDt(Date intrBkSttlmDt)
	{
		IntrBkSttlmDt = intrBkSttlmDt;
	}

	public Date getSttlmTmIndctn()
	{
		return SttlmTmIndctn;
	}

	public void setSttlmTmIndctn(Date cdtDtTm)
	{
		SttlmTmIndctn = cdtDtTm;
	}

	public String getInstdAmt()
	{
		return InstdAmt;
	}

	public void setInstdAmt(String instdAmt)
	{
		InstdAmt = instdAmt;
	}

	public String getInstdAmtCcy()
	{
		return InstdAmtCcy;
	}

	public void setInstdAmtCcy(String instdAmtCcy)
	{
		InstdAmtCcy = instdAmtCcy;
	}

	public String getChrgBr()
	{
		return ChrgBr;
	}

	public void setChrgBr(String chrgBr)
	{
		ChrgBr = chrgBr;
	}

	public String getDbtrNm()
	{
		return DbtrNm;
	}

	public void setDbtrNm(String dbtrNm)
	{
		DbtrNm = dbtrNm;
	}

	public String getDbtrOrgIdAnyBIC()
	{
		return DbtrOrgIdAnyBIC;
	}

	public void setDbtrOrgIdAnyBIC(String dbtrOrgIdAnyBIC)
	{
		DbtrOrgIdAnyBIC = dbtrOrgIdAnyBIC;
	}

	public String getDbtrMobNb()
	{
		return DbtrMobNb;
	}

	public void setDbtrMobNb(String dbtrMobNb)
	{
		DbtrMobNb = dbtrMobNb;
	}

	public String getDbtrEmailAdr()
	{
		return DbtrEmailAdr;
	}

	public void setDbtrEmailAdr(String dbtrEmailAdr)
	{
		DbtrEmailAdr = dbtrEmailAdr;
	}

	public String getDbtrAcctId()
	{
		return DbtrAcctId;
	}

	public void setDbtrAcctId(String dbtrAcctId)
	{
		DbtrAcctId = dbtrAcctId;
	}

	public String getDbtrAccSchmeNm()
	{
		return DbtrAccSchmeNm;
	}

	public void setDbtrAccSchmeNm(String dbtrAccSchmeNm)
	{
		DbtrAccSchmeNm = dbtrAccSchmeNm;
	}

	public String getDbtrAgBICFI()
	{
		return DbtrAgBICFI;
	}

	public void setDbtrAgBICFI(String dbtrAgBICFI)
	{
		DbtrAgBICFI = dbtrAgBICFI;
	}

	public String getDbtrAgClrSysMmbId()
	{
		return DbtrAgClrSysMmbId;
	}

	public void setDbtrAgClrSysMmbId(String dbtrAgClrSysMmbId)
	{
		DbtrAgClrSysMmbId = dbtrAgClrSysMmbId;
	}

	public String getCdtrAgtBICFI()
	{
		return CdtrAgtBICFI;
	}

	public void setCdtrAgtBICFI(String cdtrAgtBICFI)
	{
		CdtrAgtBICFI = cdtrAgtBICFI;
	}

	public String getCdtrAgtClrSysMmbId()
	{
		return CdtrAgtClrSysMmbId;
	}

	public void setCdtrAgtClrSysMmbId(String cdtrAgtClrSysMmbId)
	{
		CdtrAgtClrSysMmbId = cdtrAgtClrSysMmbId;
	}

	public String getCdtrNm()
	{
		return CdtrNm;
	}

	public void setCdtrNm(String cdtrNm)
	{
		CdtrNm = cdtrNm;
	}

	public String getCdtrMobNb()
	{
		return CdtrMobNb;
	}

	public void setCdtrMobNb(String cdtrMobNb)
	{
		CdtrMobNb = cdtrMobNb;
	}

	public String getCdtrEmailAdr()
	{
		return CdtrEmailAdr;
	}

	public void setCdtrEmailAdr(String cdtrEmailAdr)
	{
		CdtrEmailAdr = cdtrEmailAdr;
	}

	public String getCdtrAcctId()
	{
		return CdtrAcctId;
	}

	public void setCdtrAcctId(String cdtrAcctId)
	{
		CdtrAcctId = cdtrAcctId;
	}

	public String getCdtrAcctSchmeNm()
	{
		return CdtrAcctSchmeNm;
	}

	public void setCdtrAcctSchmeNm(String cdtrAcctSchmeNm)
	{
		CdtrAcctSchmeNm = cdtrAcctSchmeNm;
	}

	public String getPurpCd()
	{
		return PurpCd;
	}

	public void setPurpCd(String purpCd)
	{
		PurpCd = purpCd;
	}

	public String getPurpPrtry()
	{
		return PurpPrtry;
	}

	public void setPurpPrtry(String purpPrtry)
	{
		PurpPrtry = purpPrtry;
	}

	public String getRmtInf()
	{
		return RmtInf;
	}

	public void setRmtInf(String rmtInf)
	{
		RmtInf = rmtInf;
	}

	public FFPJbP110 getP110Jb()
	{
		return p110Jb;
	}

	public void setP110Jb(FFPJbP110 p110Jb)
	{
		this.p110Jb = p110Jb;
	}

	public FFPVO_Pacs008_CTI01REPLY getCti01Reply()
	{
		return cti01Reply;
	}

	public void setCti01Reply(FFPVO_Pacs008_CTI01REPLY cti01Reply)
	{
		this.cti01Reply = cti01Reply;
	}

	public FFPVO_Pacs008_CTI02REPLY getCti02Reply()
	{
		return cti02Reply;
	}

	public void setCti02Reply(FFPVO_Pacs008_CTI02REPLY cti02Reply)
	{
		this.cti02Reply = cti02Reply;
	}

	public String getDbtrOrgIdOthrId()
	{
		return DbtrOrgIdOthrId;
	}

	public void setDbtrOrgIdOthrId(String dbtrOrgIdOthrId)
	{
		DbtrOrgIdOthrId = dbtrOrgIdOthrId;
	}

	public String getDbtrOrgIdOthrIdSchmeNm()
	{
		return DbtrOrgIdOthrIdSchmeNm;
	}

	public void setDbtrOrgIdOthrIdSchmeNm(String dbtrOrgIdOthrIdSchmeNm)
	{
		DbtrOrgIdOthrIdSchmeNm = dbtrOrgIdOthrIdSchmeNm;
	}

	public String getDbtrOrgIdOthrIssr()
	{
		return DbtrOrgIdOthrIssr;
	}

	public void setDbtrOrgIdOthrIssr(String dbtrOrgIdOthrIssr)
	{
		DbtrOrgIdOthrIssr = dbtrOrgIdOthrIssr;
	}

	public String getDbtrPrvtIdOthrId()
	{
		return DbtrPrvtIdOthrId;
	}

	public void setDbtrPrvtIdOthrId(String dbtrPrvtIdOthrId)
	{
		DbtrPrvtIdOthrId = dbtrPrvtIdOthrId;
	}

	public String getDbtrPrvtIdOthrIdSchmeNm()
	{
		return DbtrPrvtIdOthrIdSchmeNm;
	}

	public void setDbtrPrvtIdOthrIdSchmeNm(String dbtrPrvtIdOthrIdSchmeNm)
	{
		DbtrPrvtIdOthrIdSchmeNm = dbtrPrvtIdOthrIdSchmeNm;
	}

	public String getDbtrPrvtIdOthrIssr()
	{
		return DbtrPrvtIdOthrIssr;
	}

	public void setDbtrPrvtIdOthrIssr(String dbtrPrvtIdOthrIssr)
	{
		DbtrPrvtIdOthrIssr = dbtrPrvtIdOthrIssr;
	}

	public String getCdtrOrgIdAnyBIC()
	{
		return CdtrOrgIdAnyBIC;
	}

	public void setCdtrOrgIdAnyBIC(String cdtrOrgIdAnyBIC)
	{
		CdtrOrgIdAnyBIC = cdtrOrgIdAnyBIC;
	}

	public String getCdtrOrgIdOthrId()
	{
		return CdtrOrgIdOthrId;
	}

	public void setCdtrOrgIdOthrId(String cdtrOrgIdOthrId)
	{
		CdtrOrgIdOthrId = cdtrOrgIdOthrId;
	}

	public String getCdtrOrgIdOthrIdSchmeNm()
	{
		return CdtrOrgIdOthrIdSchmeNm;
	}

	public void setCdtrOrgIdOthrIdSchmeNm(String cdtrOrgIdOthrIdSchmeNm)
	{
		CdtrOrgIdOthrIdSchmeNm = cdtrOrgIdOthrIdSchmeNm;
	}

	public String getCdtrOrgIdOthrIssr()
	{
		return CdtrOrgIdOthrIssr;
	}

	public void setCdtrOrgIdOthrIssr(String cdtrOrgIdOthrIssr)
	{
		CdtrOrgIdOthrIssr = cdtrOrgIdOthrIssr;
	}

	public String getCdtrPrvtIdOthrId()
	{
		return CdtrPrvtIdOthrId;
	}

	public void setCdtrPrvtIdOthrId(String cdtrPrvtIdOthrId)
	{
		CdtrPrvtIdOthrId = cdtrPrvtIdOthrId;
	}

	public String getCdtrPrvtIdOthrIdSchmeNm()
	{
		return CdtrPrvtIdOthrIdSchmeNm;
	}

	public void setCdtrPrvtIdOthrIdSchmeNm(String cdtrPrvtIdOthrIdSchmeNm)
	{
		CdtrPrvtIdOthrIdSchmeNm = cdtrPrvtIdOthrIdSchmeNm;
	}

	public String getCdtrPrvtIdOthrIssr()
	{
		return CdtrPrvtIdOthrIssr;
	}

	public void setCdtrPrvtIdOthrIssr(String cdtrPrvtIdOthrIssr)
	{
		CdtrPrvtIdOthrIssr = cdtrPrvtIdOthrIssr;
	}

	public String getChrgAgentId()
	{
		return ChrgAgentId;
	}

	public void setChrgAgentId(String chrgAgentId)
	{
		ChrgAgentId = chrgAgentId;
	}

	public String getChrgAgentBic()
	{
		return ChrgAgentBic;
	}

	public void setChrgAgentBic(String chrgAgentBic)
	{
		ChrgAgentBic = chrgAgentBic;
	}

	public String getChrgCcy()
	{
		return ChrgCcy;
	}

	public void setChrgCcy(String chrgCcy)
	{
		ChrgCcy = chrgCcy;
	}

	public BigDecimal getChrgAmount()
	{
		return ChrgAmount;
	}

	public void setChrgAmount(BigDecimal chrgAmount)
	{
		ChrgAmount = chrgAmount;
	}

}
