package com.forms.ffp.msg.iclfps.bussiness;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class CreditTransferTransactionBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "End to End ID")
	private String endToEndId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Transaction ID")
	private String transactionId;
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Instruction ID")
	private String instructionId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Payment Category Purpose Code")
	private String purposeCode;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Account Verification")
	private String accountVerification;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Settlement Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date settlementDate;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Settlement Currency")
	private String settlementCurrency;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Settlement Amount")
	private BigDecimal settlementAmount;
	@FFPValidateMessage(field = "Instructed Currency")
	private String instructedCurrency;
	@FFPValidateMessage(field = "Instructed Amount")
	private BigDecimal instructedAmount;
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Chargers Agent ID")
	private String chargersAgentId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Chargers Agent BIC")
	private String chargersAgentBic;
	@FFPValidateMessage(field = "Chargers Currency")
	private String chargersCurrency;
	@FFPValidateMessage(field = "Chargers Amount")
	private BigDecimal chargersAmount;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Debtor Name")
	private String debtorName;
	private String debtorIdDetailsType;
	@FFPValidating
	@FFPValidateMessage(field = "Debtor")
	private EndUserDetailBean debtorIdDetail;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Debtor Account Number")
	private String debtorAccountNumber;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Debtor Account Number Type")
	private String debtorAccountNumberType;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Debtor Agent ID")
	private String debtorAgentId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Debtor Agent BIC")
	private String debtorAgentBic;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Creditor Name")
	private String creditorName;
	private String creditorIdDetailsType;
	@FFPValidating
	@FFPValidateMessage(field = "Creditor")
	private EndUserDetailBean creditorIdDetail;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Creditor Account Number")
	private String creditorAccountNumber;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Creditor Account Numbe Type")
	private String creditorAccountNumberType;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Creditor Agent ID")
	private String creditorAgentId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Creditor Agent BIC")
	private String creditorAgentBic;
	@FFPValidateMessage(field = "Payment Purpose Type")
	private String paymentPurposeType;
	@FFPValidateMessage(field = "Payment Purpose Code")
	private String paymentPurposeCd;
	@FFPValidateMessage(field = "Payment Purpose Proprietary")
	private String paymentPurposeProprietary;
	@FFPValidateMessage(field = "Remittance Information")
	private String remittanceInformation;

	public String getEndToEndId()
	{
		return this.endToEndId;
	}

	public void setEndToEndId(String endToEndId)
	{
		this.endToEndId = endToEndId;
	}

	public String getTransactionId()
	{
		return this.transactionId;
	}

	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	public String getInstructionId()
	{
		return this.instructionId;
	}

	public void setInstructionId(String instructionId)
	{
		this.instructionId = instructionId;
	}

	public String getPurposeCode()
	{
		return this.purposeCode;
	}

	public void setPurposeCode(String purposeCode)
	{
		this.purposeCode = purposeCode;
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

	public void setDebtorName(String debtorName)
	{
		this.debtorName = debtorName;
	}

	public String getDebtorIdDetailsType()
	{
		return this.debtorIdDetailsType;
	}

	public void setDebtorIdDetailsType(String debtorIdDetailsType)
	{
		this.debtorIdDetailsType = debtorIdDetailsType;
	}

	public EndUserDetailBean getDebtorIdDetail()
	{
		return this.debtorIdDetail;
	}

	public void setDebtorIdDetail(EndUserDetailBean debtorIdDetail)
	{
		this.debtorIdDetail = debtorIdDetail;
	}

	public String getDebtorAccountNumber()
	{
		return this.debtorAccountNumber;
	}

	public void setDebtorAccountNumber(String debtorAccountNumber)
	{
		this.debtorAccountNumber = debtorAccountNumber;
	}

	public String getDebtorAccountNumberType()
	{
		return this.debtorAccountNumberType;
	}

	public void setDebtorAccountNumberType(String debtorAccountNumberType)
	{
		this.debtorAccountNumberType = debtorAccountNumberType;
	}

	public String getDebtorAgentId()
	{
		return this.debtorAgentId;
	}

	public void setDebtorAgentId(String debtorAgentId)
	{
		this.debtorAgentId = debtorAgentId;
	}

	public String getDebtorAgentBic()
	{
		return this.debtorAgentBic;
	}

	public void setDebtorAgentBic(String debtorAgentBic)
	{
		this.debtorAgentBic = debtorAgentBic;
	}

	public String getCreditorName()
	{
		return this.creditorName;
	}

	public void setCreditorName(String creditorName)
	{
		this.creditorName = creditorName;
	}

	public String getCreditorIdDetailsType()
	{
		return this.creditorIdDetailsType;
	}

	public void setCreditorIdDetailsType(String creditorIdDetailsType)
	{
		this.creditorIdDetailsType = creditorIdDetailsType;
	}

	public EndUserDetailBean getCreditorIdDetail()
	{
		return this.creditorIdDetail;
	}

	public void setCreditorIdDetail(EndUserDetailBean creditorIdDetail)
	{
		this.creditorIdDetail = creditorIdDetail;
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
		return this.paymentPurposeCd;
	}

	public void setPaymentPurposeCd(String paymentPurposeCd)
	{
		this.paymentPurposeCd = paymentPurposeCd;
	}

	public String getPaymentPurposeProprietary()
	{
		return this.paymentPurposeProprietary;
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
}

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * CreditTransferTransactionBean.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */