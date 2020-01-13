package com.forms.ffp.persistents.bean.payment.directdebit;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbP200 extends FFPJbBaseFin
{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String jnlNo;
	private String srcRefNm;
	private String srvcMode;
	private String pymtCatPrps;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date settlementDate;
	private String settlementCurrency;
	private BigDecimal settlementAmount;
	private String instructedCurrency;
	private BigDecimal instructedAmount;
	private String chargersAgentId;
	private String chargersAgentBic;
	private String chargersCurrency;
	private BigDecimal chargersAmount;

	private String drctDbtTxMndtId;

	private String debtorName;
	private String debtorOrgIdAnyBIC;
	private String debtorOrgIdOthrId;
	private String debtorOrgIdOthrIdSchmeNm;
	private String debtorOrgIdOthrIssr;
	private String debtorPrvtIdOthrId;
	private String debtorPrvtIdOthrIdSchmeNm;
	private String debtorPrvtIdOthrIssr;
	private String debtorContPhone;
	private String debtorContEmailAddr;
	private String debtorAccountNumber;
	private String debtorAccountNumberType;
	private String debtorAgentId;
	private String debtorAgentBic;

	private String creditorName;
	private String creditorOrgIdAnyBIC;
	private String creditorOrgIdOthrId;
	private String creditorOrgIdOthrIdSchmeNm;
	private String creditorOrgIdOthrIssr;
	private String creditorPrvtIdOthrId;
	private String creditorPrvtIdOthrIdSchmeNm;
	private String creditorPrvtIdOthrIssr;
	private String creditorContPhone;
	private String creditorContEmailAddr;
	private String creditorAccountNumber;
	private String creditorAccountNumberType;
	private String creditorAgentId;
	private String creditorAgentBic;

	private String paymentPurposeType;
	private String paymentPurposeCd;
	private String paymentPurposeProprietary;
	private String remittanceInformation;

	public String getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(String jnlNo)
	{
		this.jnlNo = jnlNo;
	}

	public String getSrcRefNm()
	{
		return srcRefNm;
	}

	public void setSrcRefNm(String srcRefNm)
	{
		this.srcRefNm = srcRefNm;
	}

	public String getSrvcMode()
	{
		return srvcMode;
	}

	public void setSrvcMode(String srvcMode)
	{
		this.srvcMode = srvcMode;
	}

	public String getPymtCatPrps()
	{
		return pymtCatPrps;
	}

	public void setPymtCatPrps(String pymtCatPrps)
	{
		this.pymtCatPrps = pymtCatPrps;
	}

	public Date getSettlementDate()
	{
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate)
	{
		this.settlementDate = settlementDate;
	}

	public String getSettlementCurrency()
	{
		return settlementCurrency;
	}

	public void setSettlementCurrency(String settlementCurrency)
	{
		this.settlementCurrency = settlementCurrency;
	}

	public BigDecimal getSettlementAmount()
	{
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount)
	{
		this.settlementAmount = settlementAmount;
	}

	public String getInstructedCurrency()
	{
		return instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency)
	{
		this.instructedCurrency = instructedCurrency;
	}

	public BigDecimal getInstructedAmount()
	{
		return instructedAmount;
	}

	public void setInstructedAmount(BigDecimal instructedAmount)
	{
		this.instructedAmount = instructedAmount;
	}

	public String getChargersAgentId()
	{
		return chargersAgentId;
	}

	public void setChargersAgentId(String chargersAgentId)
	{
		this.chargersAgentId = chargersAgentId;
	}

	public String getChargersAgentBic()
	{
		return chargersAgentBic;
	}

	public void setChargersAgentBic(String chargersAgentBic)
	{
		this.chargersAgentBic = chargersAgentBic;
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

	public String getDebtorName()
	{
		return debtorName;
	}

	public String getDrctDbtTxMndtId()
	{
		return drctDbtTxMndtId;
	}

	public void setDrctDbtTxMndtId(String drctDbtTxMndtId)
	{
		this.drctDbtTxMndtId = drctDbtTxMndtId;
	}

	public void setDebtorName(String debtorName)
	{
		this.debtorName = debtorName;
	}

	public String getDebtorAccountNumber()
	{
		return debtorAccountNumber;
	}

	public void setDebtorAccountNumber(String debtorAccountNumber)
	{
		this.debtorAccountNumber = debtorAccountNumber;
	}

	public String getDebtorAccountNumberType()
	{
		return debtorAccountNumberType;
	}

	public void setDebtorAccountNumberType(String debtorAccountNumberType)
	{
		this.debtorAccountNumberType = debtorAccountNumberType;
	}

	public String getDebtorAgentId()
	{
		return debtorAgentId;
	}

	public void setDebtorAgentId(String debtorAgentId)
	{
		this.debtorAgentId = debtorAgentId;
	}

	public String getDebtorAgentBic()
	{
		return debtorAgentBic;
	}

	public void setDebtorAgentBic(String debtorAgentBic)
	{
		this.debtorAgentBic = debtorAgentBic;
	}

	public String getDebtorContPhone()
	{
		return debtorContPhone;
	}

	public void setDebtorContPhone(String debtorContPhone)
	{
		this.debtorContPhone = debtorContPhone;
	}

	public String getDebtorContEmailAddr()
	{
		return debtorContEmailAddr;
	}

	public void setDebtorContEmailAddr(String debtorContEmailAddr)
	{
		this.debtorContEmailAddr = debtorContEmailAddr;
	}

	public String getCreditorName()
	{
		return creditorName;
	}

	public void setCreditorName(String creditorName)
	{
		this.creditorName = creditorName;
	}

	public String getCreditorAccountNumber()
	{
		return creditorAccountNumber;
	}

	public void setCreditorAccountNumber(String creditorAccountNumber)
	{
		this.creditorAccountNumber = creditorAccountNumber;
	}

	public String getCreditorAccountNumberType()
	{
		return creditorAccountNumberType;
	}

	public void setCreditorAccountNumberType(String creditorAccountNumberType)
	{
		this.creditorAccountNumberType = creditorAccountNumberType;
	}

	public String getCreditorAgentId()
	{
		return creditorAgentId;
	}

	public void setCreditorAgentId(String creditorAgentId)
	{
		this.creditorAgentId = creditorAgentId;
	}

	public String getCreditorAgentBic()
	{
		return creditorAgentBic;
	}

	public void setCreditorAgentBic(String creditorAgentBic)
	{
		this.creditorAgentBic = creditorAgentBic;
	}

	public String getCreditorContPhone()
	{
		return creditorContPhone;
	}

	public void setCreditorContPhone(String creditorContPhone)
	{
		this.creditorContPhone = creditorContPhone;
	}

	public String getCreditorContEmailAddr()
	{
		return creditorContEmailAddr;
	}

	public void setCreditorContEmailAddr(String creditorContEmailAddr)
	{
		this.creditorContEmailAddr = creditorContEmailAddr;
	}

	public String getPaymentPurposeType()
	{
		return paymentPurposeType;
	}

	public void setPaymentPurposeType(String paymentPurposeType)
	{
		this.paymentPurposeType = paymentPurposeType;
	}

	public String getPaymentPurposeCd()
	{
		return paymentPurposeCd;
	}

	public void setPaymentPurposeCd(String paymentPurposeCd)
	{
		this.paymentPurposeCd = paymentPurposeCd;
	}

	public String getPaymentPurposeProprietary()
	{
		return paymentPurposeProprietary;
	}

	public void setPaymentPurposeProprietary(String paymentPurposeProprietary)
	{
		this.paymentPurposeProprietary = paymentPurposeProprietary;
	}

	public String getRemittanceInformation()
	{
		return remittanceInformation;
	}

	public void setRemittanceInformation(String remittanceInformation)
	{
		this.remittanceInformation = remittanceInformation;
	}

	public String getDebtorOrgIdAnyBIC()
	{
		return debtorOrgIdAnyBIC;
	}

	public void setDebtorOrgIdAnyBIC(String debtorOrgIdAnyBIC)
	{
		this.debtorOrgIdAnyBIC = debtorOrgIdAnyBIC;
	}

	public String getDebtorOrgIdOthrId()
	{
		return debtorOrgIdOthrId;
	}

	public void setDebtorOrgIdOthrId(String debtorOrgIdOthrId)
	{
		this.debtorOrgIdOthrId = debtorOrgIdOthrId;
	}

	public String getDebtorOrgIdOthrIdSchmeNm()
	{
		return debtorOrgIdOthrIdSchmeNm;
	}

	public void setDebtorOrgIdOthrIdSchmeNm(String debtorOrgIdOthrIdSchmeNm)
	{
		this.debtorOrgIdOthrIdSchmeNm = debtorOrgIdOthrIdSchmeNm;
	}

	public String getDebtorOrgIdOthrIssr()
	{
		return debtorOrgIdOthrIssr;
	}

	public void setDebtorOrgIdOthrIssr(String debtorOrgIdOthrIssr)
	{
		this.debtorOrgIdOthrIssr = debtorOrgIdOthrIssr;
	}

	public String getDebtorPrvtIdOthrId()
	{
		return debtorPrvtIdOthrId;
	}

	public void setDebtorPrvtIdOthrId(String debtorPrvtIdOthrId)
	{
		this.debtorPrvtIdOthrId = debtorPrvtIdOthrId;
	}

	public String getDebtorPrvtIdOthrIdSchmeNm()
	{
		return debtorPrvtIdOthrIdSchmeNm;
	}

	public void setDebtorPrvtIdOthrIdSchmeNm(String debtorPrvtIdOthrIdSchmeNm)
	{
		this.debtorPrvtIdOthrIdSchmeNm = debtorPrvtIdOthrIdSchmeNm;
	}

	public String getDebtorPrvtIdOthrIssr()
	{
		return debtorPrvtIdOthrIssr;
	}

	public void setDebtorPrvtIdOthrIssr(String debtorPrvtIdOthrIssr)
	{
		this.debtorPrvtIdOthrIssr = debtorPrvtIdOthrIssr;
	}

	public String getCreditorOrgIdAnyBIC()
	{
		return creditorOrgIdAnyBIC;
	}

	public void setCreditorOrgIdAnyBIC(String creditorOrgIdAnyBIC)
	{
		this.creditorOrgIdAnyBIC = creditorOrgIdAnyBIC;
	}

	public String getCreditorOrgIdOthrId()
	{
		return creditorOrgIdOthrId;
	}

	public void setCreditorOrgIdOthrId(String creditorOrgIdOthrId)
	{
		this.creditorOrgIdOthrId = creditorOrgIdOthrId;
	}

	public String getCreditorOrgIdOthrIdSchmeNm()
	{
		return creditorOrgIdOthrIdSchmeNm;
	}

	public void setCreditorOrgIdOthrIdSchmeNm(String creditorOrgIdOthrIdSchmeNm)
	{
		this.creditorOrgIdOthrIdSchmeNm = creditorOrgIdOthrIdSchmeNm;
	}

	public String getCreditorOrgIdOthrIssr()
	{
		return creditorOrgIdOthrIssr;
	}

	public void setCreditorOrgIdOthrIssr(String creditorOrgIdOthrIssr)
	{
		this.creditorOrgIdOthrIssr = creditorOrgIdOthrIssr;
	}

	public String getCreditorPrvtIdOthrId()
	{
		return creditorPrvtIdOthrId;
	}

	public void setCreditorPrvtIdOthrId(String creditorPrvtIdOthrId)
	{
		this.creditorPrvtIdOthrId = creditorPrvtIdOthrId;
	}

	public String getCreditorPrvtIdOthrIdSchmeNm()
	{
		return creditorPrvtIdOthrIdSchmeNm;
	}

	public void setCreditorPrvtIdOthrIdSchmeNm(String creditorPrvtIdOthrIdSchmeNm)
	{
		this.creditorPrvtIdOthrIdSchmeNm = creditorPrvtIdOthrIdSchmeNm;
	}

	public String getCreditorPrvtIdOthrIssr()
	{
		return creditorPrvtIdOthrIssr;
	}

	public void setCreditorPrvtIdOthrIssr(String creditorPrvtIdOthrIssr)
	{
		this.creditorPrvtIdOthrIssr = creditorPrvtIdOthrIssr;
	}

}
