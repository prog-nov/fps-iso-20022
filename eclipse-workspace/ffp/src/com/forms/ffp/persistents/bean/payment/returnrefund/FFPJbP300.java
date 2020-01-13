package com.forms.ffp.persistents.bean.payment.returnrefund;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbP300 extends FFPJbBaseFin
{

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String SrcRefNm;
	private String SrvcMode;
	private String PymtCatPrps;
	private String jnlRef;

	private String returnId;
	private String OrgnlInstrId;
	private String OrgnlEndToEndId;
	private String OrgnlTxId;
	private String OrgnlClrSysRef;
	private String retIntSetCur;
	private BigDecimal retIntSetAmt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date settlementDate;
	private String retInsCur;
	private BigDecimal retInsAmt;
	private String ChrgBr;
	private String chargersCurrency;
	private BigDecimal chargersAmount;
	private String chgAgtID;
	private String chgAgtBIC;
	private String reasonCode;
	private String additionalInformation;
	private BigDecimal OrgnlInterbankSettAmt;
	private String OrgnlInterbankSettCcy;
	private Date OrgnlInterbankSettDate;
	private String OrgnlCatgyPurp;
	private String OrgnlMandateInfo;
	private String OrgnlRemtInfo;

	private String OrgnlDbtrNm;
	private String OrgnlDbtrOrgIdAnyBIC;
	private String OrgnlDbtrOrgIdOthrId;
	private String OrgnlDbtrOrgIdOthrIdSchmeNm;
	private String OrgnlDbtrOrgIdOthrIssr;
	private String OrgnlDbtrPrvtIdOthrId;
	private String OrgnlDbtrPrvtIdOthrIdSchmeNm;
	private String OrgnlDbtrPrvtIdOthrIssr;

	private String OrgnlDbtrPhNo;
	private String OrgnlDbtrEmAddr;

	private String OrgnlDbtrAcctNo;
	private String OrgnlDbtrAcctNoTp;

	private String OrgnlDbtrAgtId;
	private String OrgnlDbtrAgtBIC;

	private String OrgnlCdtrNm;
	private String OrgnlCdtrOrgIdAnyBIC;
	private String OrgnlCdtrOrgIdOthrId;
	private String OrgnlCdtrOrgIdOthrIdSchmeNm;
	private String OrgnlCdtrOrgIdOthrIssr;
	private String OrgnlCdtrPrvtIdOthrId;
	private String OrgnlCdtrPrvtIdOthrIdSchmeNm;
	private String OrgnlCdtrPrvtIdOthrIssr;

	private String OrgnlCdtrPhNo;
	private String OrgnlCdtrEmAddr;

	private String OrgnlCdtrAcctNo;
	private String OrgnlCdtrAcctNoTp;

	private String OrgnlCdtrAgtId;
	private String OrgnlCdtrAgtBIC;

	public String getJnlRef()
	{
		return jnlRef;
	}

	public void setJnlRef(String jnlRef)
	{
		this.jnlRef = jnlRef;
	}

	public String getSrcRefNm()
	{
		return SrcRefNm;
	}

	public void setSrcRefNm(String srcRefNm)
	{
		SrcRefNm = srcRefNm;
	}

	public String getSrvcMode()
	{
		return SrvcMode;
	}

	public void setSrvcMode(String srvcMode)
	{
		SrvcMode = srvcMode;
	}

	public String getPymtCatPrps()
	{
		return PymtCatPrps;
	}

	public void setPymtCatPrps(String pymtCatPrps)
	{
		PymtCatPrps = pymtCatPrps;
	}

	public String getReturnId()
	{
		return returnId;
	}

	public void setReturnId(String returnId)
	{
		this.returnId = returnId;
	}

	public String getOrgnlInstrId()
	{
		return OrgnlInstrId;
	}

	public void setOrgnlInstrId(String orgnlInstrId)
	{
		OrgnlInstrId = orgnlInstrId;
	}

	public String getOrgnlEndToEndId()
	{
		return OrgnlEndToEndId;
	}

	public void setOrgnlEndToEndId(String orgnlEndToEndId)
	{
		OrgnlEndToEndId = orgnlEndToEndId;
	}

	public String getOrgnlTxId()
	{
		return OrgnlTxId;
	}

	public void setOrgnlTxId(String orgnlTxId)
	{
		OrgnlTxId = orgnlTxId;
	}

	public String getOrgnlClrSysRef()
	{
		return OrgnlClrSysRef;
	}

	public void setOrgnlClrSysRef(String orgnlClrSysRef)
	{
		OrgnlClrSysRef = orgnlClrSysRef;
	}

	public String getRetIntSetCur()
	{
		return retIntSetCur;
	}

	public void setRetIntSetCur(String retIntSetCur)
	{
		this.retIntSetCur = retIntSetCur;
	}

	public BigDecimal getRetIntSetAmt()
	{
		return retIntSetAmt;
	}

	public void setRetIntSetAmt(BigDecimal retIntSetAmt)
	{
		this.retIntSetAmt = retIntSetAmt;
	}

	public Date getSettlementDate()
	{
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate)
	{
		this.settlementDate = settlementDate;
	}

	public String getRetInsCur()
	{
		return retInsCur;
	}

	public void setRetInsCur(String retInsCur)
	{
		this.retInsCur = retInsCur;
	}

	public BigDecimal getRetInsAmt()
	{
		return retInsAmt;
	}

	public void setRetInsAmt(BigDecimal retInsAmt)
	{
		this.retInsAmt = retInsAmt;
	}

	public String getChrgBr()
	{
		return ChrgBr;
	}

	public void setChrgBr(String chrgBr)
	{
		ChrgBr = chrgBr;
	}

	public String getChargersCurrency()
	{
		return chargersCurrency;
	}

	public void setChargersCurrency(String chargersCurrency)
	{
		this.chargersCurrency = chargersCurrency;
	}

	public BigDecimal getChargersAmount()
	{
		return chargersAmount;
	}

	public void setChargersAmount(BigDecimal chargersAmount)
	{
		this.chargersAmount = chargersAmount;
	}

	public String getChgAgtID()
	{
		return chgAgtID;
	}

	public void setChgAgtID(String chgAgtID)
	{
		this.chgAgtID = chgAgtID;
	}

	public String getChgAgtBIC()
	{
		return chgAgtBIC;
	}

	public void setChgAgtBIC(String chgAgtBIC)
	{
		this.chgAgtBIC = chgAgtBIC;
	}

	public String getReasonCode()
	{
		return reasonCode;
	}

	public void setReasonCode(String reasonCode)
	{
		this.reasonCode = reasonCode;
	}

	public String getAdditionalInformation()
	{
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	public BigDecimal getOrgnlInterbankSettAmt()
	{
		return OrgnlInterbankSettAmt;
	}

	public void setOrgnlInterbankSettAmt(BigDecimal orgnlInterbankSettAmt)
	{
		OrgnlInterbankSettAmt = orgnlInterbankSettAmt;
	}

	public String getOrgnlInterbankSettCcy()
	{
		return OrgnlInterbankSettCcy;
	}

	public void setOrgnlInterbankSettCcy(String orgnlInterbankSettCcy)
	{
		OrgnlInterbankSettCcy = orgnlInterbankSettCcy;
	}

	public Date getOrgnlInterbankSettDate()
	{
		return OrgnlInterbankSettDate;
	}

	public void setOrgnlInterbankSettDate(Date orgnlInterbankSettDate)
	{
		OrgnlInterbankSettDate = orgnlInterbankSettDate;
	}

	public String getOrgnlCatgyPurp()
	{
		return OrgnlCatgyPurp;
	}

	public void setOrgnlCatgyPurp(String orgnlCatgyPurp)
	{
		OrgnlCatgyPurp = orgnlCatgyPurp;
	}

	public String getOrgnlMandateInfo()
	{
		return OrgnlMandateInfo;
	}

	public void setOrgnlMandateInfo(String orgnlMandateInfo)
	{
		OrgnlMandateInfo = orgnlMandateInfo;
	}

	public String getOrgnlRemtInfo()
	{
		return OrgnlRemtInfo;
	}

	public void setOrgnlRemtInfo(String orgnlRemtInfo)
	{
		OrgnlRemtInfo = orgnlRemtInfo;
	}

	public String getOrgnlDbtrNm()
	{
		return OrgnlDbtrNm;
	}

	public void setOrgnlDbtrNm(String orgnlDbtrNm)
	{
		OrgnlDbtrNm = orgnlDbtrNm;
	}

	public String getOrgnlDbtrOrgIdAnyBIC()
	{
		return OrgnlDbtrOrgIdAnyBIC;
	}

	public void setOrgnlDbtrOrgIdAnyBIC(String orgnlDbtrOrgIdAnyBIC)
	{
		OrgnlDbtrOrgIdAnyBIC = orgnlDbtrOrgIdAnyBIC;
	}

	public String getOrgnlDbtrOrgIdOthrId()
	{
		return OrgnlDbtrOrgIdOthrId;
	}

	public void setOrgnlDbtrOrgIdOthrId(String orgnlDbtrOrgIdOthrId)
	{
		OrgnlDbtrOrgIdOthrId = orgnlDbtrOrgIdOthrId;
	}

	public String getOrgnlDbtrOrgIdOthrIdSchmeNm()
	{
		return OrgnlDbtrOrgIdOthrIdSchmeNm;
	}

	public void setOrgnlDbtrOrgIdOthrIdSchmeNm(String orgnlDbtrOrgIdOthrIdSchmeNm)
	{
		OrgnlDbtrOrgIdOthrIdSchmeNm = orgnlDbtrOrgIdOthrIdSchmeNm;
	}

	public String getOrgnlDbtrOrgIdOthrIssr()
	{
		return OrgnlDbtrOrgIdOthrIssr;
	}

	public void setOrgnlDbtrOrgIdOthrIssr(String orgnlDbtrOrgIdOthrIssr)
	{
		OrgnlDbtrOrgIdOthrIssr = orgnlDbtrOrgIdOthrIssr;
	}

	public String getOrgnlDbtrPrvtIdOthrId()
	{
		return OrgnlDbtrPrvtIdOthrId;
	}

	public void setOrgnlDbtrPrvtIdOthrId(String orgnlDbtrPrvtIdOthrId)
	{
		OrgnlDbtrPrvtIdOthrId = orgnlDbtrPrvtIdOthrId;
	}

	public String getOrgnlDbtrPrvtIdOthrIdSchmeNm()
	{
		return OrgnlDbtrPrvtIdOthrIdSchmeNm;
	}

	public void setOrgnlDbtrPrvtIdOthrIdSchmeNm(String orgnlDbtrPrvtIdOthrIdSchmeNm)
	{
		OrgnlDbtrPrvtIdOthrIdSchmeNm = orgnlDbtrPrvtIdOthrIdSchmeNm;
	}

	public String getOrgnlDbtrPrvtIdOthrIssr()
	{
		return OrgnlDbtrPrvtIdOthrIssr;
	}

	public void setOrgnlDbtrPrvtIdOthrIssr(String orgnlDbtrPrvtIdOthrIssr)
	{
		OrgnlDbtrPrvtIdOthrIssr = orgnlDbtrPrvtIdOthrIssr;
	}

	public String getOrgnlDbtrPhNo()
	{
		return OrgnlDbtrPhNo;
	}

	public void setOrgnlDbtrPhNo(String orgnlDbtrPhNo)
	{
		OrgnlDbtrPhNo = orgnlDbtrPhNo;
	}

	public String getOrgnlDbtrEmAddr()
	{
		return OrgnlDbtrEmAddr;
	}

	public void setOrgnlDbtrEmAddr(String orgnlDbtrEmAddr)
	{
		OrgnlDbtrEmAddr = orgnlDbtrEmAddr;
	}

	public String getOrgnlDbtrAcctNo()
	{
		return OrgnlDbtrAcctNo;
	}

	public void setOrgnlDbtrAcctNo(String orgnlDbtrAcctNo)
	{
		OrgnlDbtrAcctNo = orgnlDbtrAcctNo;
	}

	public String getOrgnlDbtrAcctNoTp()
	{
		return OrgnlDbtrAcctNoTp;
	}

	public void setOrgnlDbtrAcctNoTp(String orgnlDbtrAcctNoTp)
	{
		OrgnlDbtrAcctNoTp = orgnlDbtrAcctNoTp;
	}

	public String getOrgnlDbtrAgtId()
	{
		return OrgnlDbtrAgtId;
	}

	public void setOrgnlDbtrAgtId(String orgnlDbtrAgtId)
	{
		OrgnlDbtrAgtId = orgnlDbtrAgtId;
	}

	public String getOrgnlDbtrAgtBIC()
	{
		return OrgnlDbtrAgtBIC;
	}

	public void setOrgnlDbtrAgtBIC(String orgnlDbtrAgtBIC)
	{
		OrgnlDbtrAgtBIC = orgnlDbtrAgtBIC;
	}

	public String getOrgnlCdtrNm()
	{
		return OrgnlCdtrNm;
	}

	public void setOrgnlCdtrNm(String orgnlCdtrNm)
	{
		OrgnlCdtrNm = orgnlCdtrNm;
	}

	public String getOrgnlCdtrOrgIdAnyBIC()
	{
		return OrgnlCdtrOrgIdAnyBIC;
	}

	public void setOrgnlCdtrOrgIdAnyBIC(String orgnlCdtrOrgIdAnyBIC)
	{
		OrgnlCdtrOrgIdAnyBIC = orgnlCdtrOrgIdAnyBIC;
	}

	public String getOrgnlCdtrOrgIdOthrId()
	{
		return OrgnlCdtrOrgIdOthrId;
	}

	public void setOrgnlCdtrOrgIdOthrId(String orgnlCdtrOrgIdOthrId)
	{
		OrgnlCdtrOrgIdOthrId = orgnlCdtrOrgIdOthrId;
	}

	public String getOrgnlCdtrOrgIdOthrIdSchmeNm()
	{
		return OrgnlCdtrOrgIdOthrIdSchmeNm;
	}

	public void setOrgnlCdtrOrgIdOthrIdSchmeNm(String orgnlCdtrOrgIdOthrIdSchmeNm)
	{
		OrgnlCdtrOrgIdOthrIdSchmeNm = orgnlCdtrOrgIdOthrIdSchmeNm;
	}

	public String getOrgnlCdtrOrgIdOthrIssr()
	{
		return OrgnlCdtrOrgIdOthrIssr;
	}

	public void setOrgnlCdtrOrgIdOthrIssr(String orgnlCdtrOrgIdOthrIssr)
	{
		OrgnlCdtrOrgIdOthrIssr = orgnlCdtrOrgIdOthrIssr;
	}

	public String getOrgnlCdtrPrvtIdOthrId()
	{
		return OrgnlCdtrPrvtIdOthrId;
	}

	public void setOrgnlCdtrPrvtIdOthrId(String orgnlCdtrPrvtIdOthrId)
	{
		OrgnlCdtrPrvtIdOthrId = orgnlCdtrPrvtIdOthrId;
	}

	public String getOrgnlCdtrPrvtIdOthrIdSchmeNm()
	{
		return OrgnlCdtrPrvtIdOthrIdSchmeNm;
	}

	public void setOrgnlCdtrPrvtIdOthrIdSchmeNm(String orgnlCdtrPrvtIdOthrIdSchmeNm)
	{
		OrgnlCdtrPrvtIdOthrIdSchmeNm = orgnlCdtrPrvtIdOthrIdSchmeNm;
	}

	public String getOrgnlCdtrPrvtIdOthrIssr()
	{
		return OrgnlCdtrPrvtIdOthrIssr;
	}

	public void setOrgnlCdtrPrvtIdOthrIssr(String orgnlCdtrPrvtIdOthrIssr)
	{
		OrgnlCdtrPrvtIdOthrIssr = orgnlCdtrPrvtIdOthrIssr;
	}

	public String getOrgnlCdtrPhNo()
	{
		return OrgnlCdtrPhNo;
	}

	public void setOrgnlCdtrPhNo(String orgnlCdtrPhNo)
	{
		OrgnlCdtrPhNo = orgnlCdtrPhNo;
	}

	public String getOrgnlCdtrEmAddr()
	{
		return OrgnlCdtrEmAddr;
	}

	public void setOrgnlCdtrEmAddr(String orgnlCdtrEmAddr)
	{
		OrgnlCdtrEmAddr = orgnlCdtrEmAddr;
	}

	public String getOrgnlCdtrAcctNo()
	{
		return OrgnlCdtrAcctNo;
	}

	public void setOrgnlCdtrAcctNo(String orgnlCdtrAcctNo)
	{
		OrgnlCdtrAcctNo = orgnlCdtrAcctNo;
	}

	public String getOrgnlCdtrAcctNoTp()
	{
		return OrgnlCdtrAcctNoTp;
	}

	public void setOrgnlCdtrAcctNoTp(String orgnlCdtrAcctNoTp)
	{
		OrgnlCdtrAcctNoTp = orgnlCdtrAcctNoTp;
	}

	public String getOrgnlCdtrAgtId()
	{
		return OrgnlCdtrAgtId;
	}

	public void setOrgnlCdtrAgtId(String orgnlCdtrAgtId)
	{
		OrgnlCdtrAgtId = orgnlCdtrAgtId;
	}

	public String getOrgnlCdtrAgtBIC()
	{
		return OrgnlCdtrAgtBIC;
	}

	public void setOrgnlCdtrAgtBIC(String orgnlCdtrAgtBIC)
	{
		OrgnlCdtrAgtBIC = orgnlCdtrAgtBIC;
	}

}
