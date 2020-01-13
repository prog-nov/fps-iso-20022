package com.forms.ffp.bussiness.iclfps.pain009;

import java.math.BigDecimal;

import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.BranchAndFinancialInstitutionIdentification51;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.CashAccount241;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.CashAccount242;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.MandateOccurrences41;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.MandateSetupReason1Choice1;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.MandateTypeInformation21;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.PartyIdentification431;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.PartyIdentification432;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.PartyIdentification433;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.ReferredMandateDocument11;
import com.forms.ffp.adaptor.jaxb.iclfps.pain_009_001_05.Restricted15Digit2DecimalCurrencyAndAmount;
import com.forms.ffp.bussiness.FFPVOBase;

public class FFPVO_Pain9_Mndt extends FFPVOBase
{

	private String mndtId;
	private String mndtReqId;
	private MandateTypeInformation21 tp;
	private MandateOccurrences41 ocrncs;
	private boolean trckgInd;
	private BigDecimal colltnAmt;
	private BigDecimal colltnCcy;
	
	
	private BigDecimal maxAmt;
	private String maxAmtCcy;
	private String prtry;
	//daixie
	private String cdtrNm;
	private String cdtrOtherId;
	private String cdtrOtherCd;
	private String cdtrOtherPrvtId;
	private String cdtrOtherPrvtIdCd;
	private String cdtracctOtherId;
	private String cdtracctPrtry;
	private String cdtragtBicfi;
	private String cdtragtMnbid;
	
	private String dbtrNm;
	private String dbtrOtherId;
	private String dbtrOtherCd;
	private String dbtrOtherPrvtId;
	private String dbtrOtherPrvtIdCd;
	private String dbtracctOtherId;
	private String dbtracctPrtry;
	private String dbtragtBicfi;
	private String dbtragtMnbid;
	private String ultmtDbtrNm;
	private String cdtrRef;
	public String getMndtId()
	{
		return mndtId;
	}
	public void setMndtId(String mndtId)
	{
		this.mndtId = mndtId;
	}
	public String getMndtReqId()
	{
		return mndtReqId;
	}
	public void setMndtReqId(String mndtReqId)
	{
		this.mndtReqId = mndtReqId;
	}
	public MandateTypeInformation21 getTp()
	{
		return tp;
	}
	public void setTp(MandateTypeInformation21 tp)
	{
		this.tp = tp;
	}
	public MandateOccurrences41 getOcrncs()
	{
		return ocrncs;
	}
	public void setOcrncs(MandateOccurrences41 ocrncs)
	{
		this.ocrncs = ocrncs;
	}
	public boolean isTrckgInd()
	{
		return trckgInd;
	}
	public void setTrckgInd(boolean trckgInd)
	{
		this.trckgInd = trckgInd;
	}
	public BigDecimal getColltnAmt()
	{
		return colltnAmt;
	}
	public void setColltnAmt(BigDecimal colltnAmt)
	{
		this.colltnAmt = colltnAmt;
	}
	public BigDecimal getColltnCcy()
	{
		return colltnCcy;
	}
	public void setColltnCcy(BigDecimal colltnCcy)
	{
		this.colltnCcy = colltnCcy;
	}
	public BigDecimal getMaxAmt()
	{
		return maxAmt;
	}
	public void setMaxAmt(BigDecimal maxAmt)
	{
		this.maxAmt = maxAmt;
	}
	public String getMaxAmtCcy()
	{
		return maxAmtCcy;
	}
	public void setMaxAmtCcy(String maxAmtCcy)
	{
		this.maxAmtCcy = maxAmtCcy;
	}
	public String getPrtry()
	{
		return prtry;
	}
	public void setPrtry(String prtry)
	{
		this.prtry = prtry;
	}
	public String getCdtrNm()
	{
		return cdtrNm;
	}
	public void setCdtrNm(String cdtrNm)
	{
		this.cdtrNm = cdtrNm;
	}
	public String getCdtrOtherId()
	{
		return cdtrOtherId;
	}
	public void setCdtrOtherId(String cdtrOtherId)
	{
		this.cdtrOtherId = cdtrOtherId;
	}
	public String getCdtrOtherCd()
	{
		return cdtrOtherCd;
	}
	public void setCdtrOtherCd(String cdtrOtherCd)
	{
		this.cdtrOtherCd = cdtrOtherCd;
	}
	public String getCdtrOtherPrvtId()
	{
		return cdtrOtherPrvtId;
	}
	public void setCdtrOtherPrvtId(String cdtrOtherPrvtId)
	{
		this.cdtrOtherPrvtId = cdtrOtherPrvtId;
	}
	public String getCdtrOtherPrvtIdCd()
	{
		return cdtrOtherPrvtIdCd;
	}
	public void setCdtrOtherPrvtIdCd(String cdtrOtherPrvtIdCd)
	{
		this.cdtrOtherPrvtIdCd = cdtrOtherPrvtIdCd;
	}
	public String getCdtracctOtherId()
	{
		return cdtracctOtherId;
	}
	public void setCdtracctOtherId(String cdtracctOtherId)
	{
		this.cdtracctOtherId = cdtracctOtherId;
	}
	public String getCdtracctPrtry()
	{
		return cdtracctPrtry;
	}
	public void setCdtracctPrtry(String cdtracctPrtry)
	{
		this.cdtracctPrtry = cdtracctPrtry;
	}
	public String getCdtragtBicfi()
	{
		return cdtragtBicfi;
	}
	public void setCdtragtBicfi(String cdtragtBicfi)
	{
		this.cdtragtBicfi = cdtragtBicfi;
	}
	public String getCdtragtMnbid()
	{
		return cdtragtMnbid;
	}
	public void setCdtragtMnbid(String cdtragtMnbid)
	{
		this.cdtragtMnbid = cdtragtMnbid;
	}
	public String getDbtrNm()
	{
		return dbtrNm;
	}
	public void setDbtrNm(String dbtrNm)
	{
		this.dbtrNm = dbtrNm;
	}
	public String getDbtrOtherId()
	{
		return dbtrOtherId;
	}
	public void setDbtrOtherId(String dbtrOtherId)
	{
		this.dbtrOtherId = dbtrOtherId;
	}
	public String getDbtrOtherCd()
	{
		return dbtrOtherCd;
	}
	public void setDbtrOtherCd(String dbtrOtherCd)
	{
		this.dbtrOtherCd = dbtrOtherCd;
	}
	public String getDbtrOtherPrvtId()
	{
		return dbtrOtherPrvtId;
	}
	public void setDbtrOtherPrvtId(String dbtrOtherPrvtId)
	{
		this.dbtrOtherPrvtId = dbtrOtherPrvtId;
	}
	public String getDbtrOtherPrvtIdCd()
	{
		return dbtrOtherPrvtIdCd;
	}
	public void setDbtrOtherPrvtIdCd(String dbtrOtherPrvtIdCd)
	{
		this.dbtrOtherPrvtIdCd = dbtrOtherPrvtIdCd;
	}
	public String getDbtracctOtherId()
	{
		return dbtracctOtherId;
	}
	public void setDbtracctOtherId(String dbtracctOtherId)
	{
		this.dbtracctOtherId = dbtracctOtherId;
	}
	public String getDbtracctPrtry()
	{
		return dbtracctPrtry;
	}
	public void setDbtracctPrtry(String dbtracctPrtry)
	{
		this.dbtracctPrtry = dbtracctPrtry;
	}
	public String getDbtragtBicfi()
	{
		return dbtragtBicfi;
	}
	public void setDbtragtBicfi(String dbtragtBicfi)
	{
		this.dbtragtBicfi = dbtragtBicfi;
	}
	public String getDbtragtMnbid()
	{
		return dbtragtMnbid;
	}
	public void setDbtragtMnbid(String dbtragtMnbid)
	{
		this.dbtragtMnbid = dbtragtMnbid;
	}
	public String getUltmtDbtrNm()
	{
		return ultmtDbtrNm;
	}
	public void setUltmtDbtrNm(String ultmtDbtrNm)
	{
		this.ultmtDbtrNm = ultmtDbtrNm;
	}
	public String getCdtrRef()
	{
		return cdtrRef;
	}
	public void setCdtrRef(String cdtrRef)
	{
		this.cdtrRef = cdtrRef;
	}
	
}
