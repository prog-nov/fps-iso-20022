package com.forms.ffp.persistents.bean.payment.credittransfer;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbP110 extends FFPJbBaseFin
{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String SrcRefNm;
	private String jnlNo;
	private String srvcMode;
	private String pymtCatPrps;
	private String accountVerification;
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
	private String DbtrContPhone;
	private String DbtrContEmailAddr;

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

	public String getPymtCatPrps()
	{
		return this.pymtCatPrps;
	}

	public void setPymtCatPrps(String PymtCatPrps)
	{
		this.pymtCatPrps = PymtCatPrps;
	}

	public String getAccountVerification()
	{
		return this.accountVerification;
	}

	public void setAccountVerification(String accountVerification)
	{
		this.accountVerification = accountVerification;
	}

	public Date getSettlementDate()
	{
		return this.settlementDate;
	}

	public void setSettlementDate(Date settlementDate)
	{
		this.settlementDate = settlementDate;
	}

	public String getSettlementCurrency()
	{
		return this.settlementCurrency;
	}

	public void setSettlementCurrency(String settlementCurrency)
	{
		this.settlementCurrency = settlementCurrency;
	}

	public BigDecimal getSettlementAmount()
	{
		return this.settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount)
	{
		this.settlementAmount = settlementAmount;
	}

	public String getInstructedCurrency()
	{
		return this.instructedCurrency;
	}

	public void setInstructedCurrency(String instructedCurrency)
	{
		this.instructedCurrency = instructedCurrency;
	}

	public BigDecimal getInstructedAmount()
	{
		return this.instructedAmount;
	}

	public void setInstructedAmount(BigDecimal instructedAmount)
	{
		this.instructedAmount = instructedAmount;
	}

	public String getChargersAgentId()
	{
		return this.chargersAgentId;
	}

	public void setChargersAgentId(String chargersAgentId)
	{
		this.chargersAgentId = chargersAgentId;
	}

	public String getChargersAgentBic()
	{
		return this.chargersAgentBic;
	}

	public void setChargersAgentBic(String chargersAgentBic)
	{
		this.chargersAgentBic = chargersAgentBic;
	}

	public String getChargersCurrency()
	{
		return this.chargersCurrency;
	}

	public void setChargersCurrency(String chargersCurrency)
	{
		this.chargersCurrency = chargersCurrency;
	}

	public BigDecimal getChargersAmount()
	{
		return this.chargersAmount;
	}

	public void setChargersAmount(BigDecimal chargersAmount)
	{
		this.chargersAmount = chargersAmount;
	}

	public String getDebtorName()
	{
		return this.debtorName;
	}

	public void setDebtorName(String DebtorName)
	{
		this.debtorName = DebtorName;
	}

	public String getDebtorAccountNumber()
	{
		return this.debtorAccountNumber;
	}

	public void setDebtorAccountNumber(String DebtorAccountNumber)
	{
		this.debtorAccountNumber = DebtorAccountNumber;
	}

	public String getDebtorAccountNumberType()
	{
		return this.debtorAccountNumberType;
	}

	public void setDebtorAccountNumberType(String DebtorAccountNumberType)
	{
		this.debtorAccountNumberType = DebtorAccountNumberType;
	}

	public String getDebtorAgentId()
	{
		return this.debtorAgentId;
	}

	public void setDebtorAgentId(String DebtorAgentId)
	{
		this.debtorAgentId = DebtorAgentId;
	}

	public String getDebtorAgentBic()
	{
		return this.debtorAgentBic;
	}

	public void setDebtorAgentBic(String DebtorAgentBic)
	{
		this.debtorAgentBic = DebtorAgentBic;
	}

	public String getCreditorName()
	{
		return this.creditorName;
	}

	public void setCreditorName(String creditorName)
	{
		this.creditorName = creditorName;
	}

	public String getCreditorAccountNumber()
	{
		return this.creditorAccountNumber;
	}

	public void setCreditorAccountNumber(String creditorAccountNumber)
	{
		this.creditorAccountNumber = creditorAccountNumber;
	}

	public String getCreditorAccountNumberType()
	{
		return this.creditorAccountNumberType;
	}

	public void setCreditorAccountNumberType(String creditorAccountNumberType)
	{
		this.creditorAccountNumberType = creditorAccountNumberType;
	}

	public String getCreditorAgentId()
	{
		return this.creditorAgentId;
	}

	public void setCreditorAgentId(String creditorAgentId)
	{
		this.creditorAgentId = creditorAgentId;
	}

	public String getCreditorAgentBic()
	{
		return this.creditorAgentBic;
	}

	public void setCreditorAgentBic(String creditorAgentBic)
	{
		this.creditorAgentBic = creditorAgentBic;
	}

	public String getPaymentPurposeType()
	{
		return this.paymentPurposeType;
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
		return this.remittanceInformation;
	}

	public void setRemittanceInformation(String remittanceInformation)
	{
		this.remittanceInformation = remittanceInformation;
	}

	public String getDebtorContPhone()
	{
		return debtorContPhone;
	}

	public void setDebtorContPhone(String DebtorContPhone)
	{
		this.debtorContPhone = DebtorContPhone;
	}

	public String getDebtorContEmailAddr()
	{
		return debtorContEmailAddr;
	}

	public void setDebtorContEmailAddr(String DebtorContEmailAddr)
	{
		this.debtorContEmailAddr = DebtorContEmailAddr;
	}

	public String getCreditorContPhone()
	{
		return creditorContPhone;
	}

	public void setCreditorContPhone(String CreditorContPhone)
	{
		this.creditorContPhone = CreditorContPhone;
	}

	public String getCreditorContEmailAddr()
	{
		return creditorContEmailAddr;
	}

	public void setCreditorContEmailAddr(String CreditorContEmailAddr)
	{
		this.creditorContEmailAddr = CreditorContEmailAddr;
	}

	public String getSrvcMode()
	{
		return srvcMode;
	}

	public void setSrvcMode(String srvcMode)
	{
		this.srvcMode = srvcMode;
	}

	public String getDebtorOrgIdAnyBIC()
	{
		return debtorOrgIdAnyBIC;
	}

	public void setDebtorOrgIdAnyBIC(String DebtorOrgIdAnyBIC)
	{
		this.debtorOrgIdAnyBIC = DebtorOrgIdAnyBIC;
	}

	public String getDebtorOrgIdOthrId()
	{
		return debtorOrgIdOthrId;
	}

	public void setDebtorOrgIdOthrId(String DebtorOrgIdOthrId)
	{
		this.debtorOrgIdOthrId = DebtorOrgIdOthrId;
	}

	public String getDebtorOrgIdOthrIdSchmeNm()
	{
		return debtorOrgIdOthrIdSchmeNm;
	}

	public void setDebtorOrgIdOthrIdSchmeNm(String DebtorOrgIdOthrIdSchmeNm)
	{
		this.debtorOrgIdOthrIdSchmeNm = DebtorOrgIdOthrIdSchmeNm;
	}

	public String getDebtorOrgIdOthrIssr()
	{
		return debtorOrgIdOthrIssr;
	}

	public void setDebtorOrgIdOthrIssr(String DebtorOrgIdOthrIssr)
	{
		this.debtorOrgIdOthrIssr = DebtorOrgIdOthrIssr;
	}

	public String getDebtorPrvtIdOthrId()
	{
		return debtorPrvtIdOthrId;
	}

	public void setDebtorPrvtIdOthrId(String DebtorPrvtIdOthrId)
	{
		this.debtorPrvtIdOthrId = DebtorPrvtIdOthrId;
	}

	public String getDebtorPrvtIdOthrIdSchmeNm()
	{
		return debtorPrvtIdOthrIdSchmeNm;
	}

	public void setDebtorPrvtIdOthrIdSchmeNm(String DebtorPrvtIdOthrIdSchmeNm)
	{
		this.debtorPrvtIdOthrIdSchmeNm = DebtorPrvtIdOthrIdSchmeNm;
	}

	public String getDebtorPrvtIdOthrIssr()
	{
		return debtorPrvtIdOthrIssr;
	}

	public void setDebtorPrvtIdOthrIssr(String DebtorPrvtIdOthrIssr)
	{
		this.debtorPrvtIdOthrIssr = DebtorPrvtIdOthrIssr;
	}

	public String getCreditorOrgIdAnyBIC()
	{
		return creditorOrgIdAnyBIC;
	}

	public void setCreditorOrgIdAnyBIC(String CreditorOrgIdAnyBIC)
	{
		this.creditorOrgIdAnyBIC = CreditorOrgIdAnyBIC;
	}

	public String getCreditorOrgIdOthrId()
	{
		return creditorOrgIdOthrId;
	}

	public void setCreditorOrgIdOthrId(String CreditorOrgIdOthrId)
	{
		this.creditorOrgIdOthrId = CreditorOrgIdOthrId;
	}

	public String getCreditorOrgIdOthrIdSchmeNm()
	{
		return creditorOrgIdOthrIdSchmeNm;
	}

	public void setCreditorOrgIdOthrIdSchmeNm(String CreditorOrgIdOthrIdSchmeNm)
	{
		this.creditorOrgIdOthrIdSchmeNm = CreditorOrgIdOthrIdSchmeNm;
	}

	public String getCreditorOrgIdOthrIssr()
	{
		return creditorOrgIdOthrIssr;
	}

	public void setCreditorOrgIdOthrIssr(String CreditorOrgIdOthrIssr)
	{
		this.creditorOrgIdOthrIssr = CreditorOrgIdOthrIssr;
	}

	public String getCreditorPrvtIdOthrId()
	{
		return creditorPrvtIdOthrId;
	}

	public void setCreditorPrvtIdOthrId(String CreditorPrvtIdOthrId)
	{
		this.creditorPrvtIdOthrId = CreditorPrvtIdOthrId;
	}

	public String getCreditorPrvtIdOthrIdSchmeNm()
	{
		return creditorPrvtIdOthrIdSchmeNm;
	}

	public void setCreditorPrvtIdOthrIdSchmeNm(String CreditorPrvtIdOthrIdSchmeNm)
	{
		this.creditorPrvtIdOthrIdSchmeNm = CreditorPrvtIdOthrIdSchmeNm;
	}

	public String getCreditorPrvtIdOthrIssr()
	{
		return creditorPrvtIdOthrIssr;
	}

	public void setCreditorPrvtIdOthrIssr(String CreditorPrvtIdOthrIssr)
	{
		this.creditorPrvtIdOthrIssr = CreditorPrvtIdOthrIssr;
	}

	public String getSrcRefNm() {
		return SrcRefNm;
	}

	public void setSrcRefNm(String srcRefNm) {
		SrcRefNm = srcRefNm;
	}

	public String getDbtrContPhone() {
		return DbtrContPhone;
	}

	public void setDbtrContPhone(String dbtrContPhone) {
		DbtrContPhone = dbtrContPhone;
	}

	public String getDbtrContEmailAddr() {
		return DbtrContEmailAddr;
	}

	public void setDbtrContEmailAddr(String dbtrContEmailAddr) {
		DbtrContEmailAddr = dbtrContEmailAddr;
	}
	
	

}
