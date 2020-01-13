package com.forms.ffp.msg.iclfps.bussiness;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class ReturnRefundTransactionBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Return ID")
	private String returnId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original End to End ID")
	private String oEndToEndId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original Transaction ID")
	private String oTransactionId;
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original Instruction ID")
	private String oInstructionId;
	@FFPValidateMessage(field = "Original Clearing System Reference")
	private String oClearingSystemReference;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FFPValidateMessage(field = "Settlement Date")
	private Date settlementDate;
	@FFPValidateMessage(field = "Return Currency")
	private String returnCurrency;
	@FFPValidateMessage(field = "Return Amount")
	private BigDecimal returnAmount;
	@FFPValidateLength(minLength = 1, maxLength = 4)
	@FFPValidatePattern(pattern = { "[A-Z0-9]{4}" })
	@FFPValidateMessage(field = "Return Reason")
	private String returnReason;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,105}" })
	@FFPValidateMessage(field = "Additional Information")
	private List<String> additionalInformation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@FFPValidateMessage(field = "Settlement Date")
	private Date oSettlementDate;
	@FFPValidateMessage(field = "Settlement Currency")
	private String oSettlementCurrency;
	@FFPValidateMessage(field = "Settlement Amount")
	private BigDecimal oSettlementAmount;
	@FFPValidateMessage(field = "Payment Category Purpose Code")
	private String oPurposeCode;
	@FFPValidateMessage(field = "Remittance Information")
	private String remittanceInformation;
	@FFPValidateMessage(field = "Debtor Name")
	private String debtorName;
	private String debtorIdDetailsType;
	@FFPValidating
	@FFPValidateMessage(field = "Debtor")
	private EndUserDetailBean debtorIdDetail = new EndUserDetailBean();

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
	private EndUserDetailBean creditorIdDetail = new EndUserDetailBean();

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

	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Mandate Identification")
	private String mandateId;

	public String getReturnId()
	{
		return this.returnId;
	}

	public void setReturnId(String returnId)
	{
		this.returnId = returnId;
	}

	public String getoEndToEndId()
	{
		return this.oEndToEndId;
	}

	public void setoEndToEndId(String oEndToEndId)
	{
		this.oEndToEndId = oEndToEndId;
	}

	public String getoTransactionId()
	{
		return this.oTransactionId;
	}

	public void setoTransactionId(String oTransactionId)
	{
		this.oTransactionId = oTransactionId;
	}

	public String getoInstructionId()
	{
		return this.oInstructionId;
	}

	public void setoInstructionId(String oInstructionId)
	{
		this.oInstructionId = oInstructionId;
	}

	public String getoClearingSystemReference()
	{
		return this.oClearingSystemReference;
	}

	public void setoClearingSystemReference(String oClearingSystemReference)
	{
		this.oClearingSystemReference = oClearingSystemReference;
	}

	public Date getSettlementDate()
	{
		return this.settlementDate;
	}

	public void setSettlementDate(Date settlementDate)
	{
		this.settlementDate = settlementDate;
	}

	public String getReturnCurrency()
	{
		return this.returnCurrency;
	}

	public void setReturnCurrency(String returnCurrency)
	{
		this.returnCurrency = returnCurrency;
	}

	public BigDecimal getReturnAmount()
	{
		return this.returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount)
	{
		this.returnAmount = returnAmount;
	}

	public String getReturnReason()
	{
		return this.returnReason;
	}

	public void setReturnReason(String returnReason)
	{
		this.returnReason = returnReason;
	}

	public List<String> getAdditionalInformation()
	{
		return this.additionalInformation;
	}

	public void setAdditionalInformation(List<String> additionalInformation)
	{
		this.additionalInformation = additionalInformation;
	}

	public Date getoSettlementDate()
	{
		return this.oSettlementDate;
	}

	public void setoSettlementDate(Date oSettlementDate)
	{
		this.oSettlementDate = oSettlementDate;
	}

	public String getoSettlementCurrency()
	{
		return this.oSettlementCurrency;
	}

	public void setoSettlementCurrency(String oSettlementCurrency)
	{
		this.oSettlementCurrency = oSettlementCurrency;
	}

	public BigDecimal getoSettlementAmount()
	{
		return this.oSettlementAmount;
	}

	public void setoSettlementAmount(BigDecimal oSettlementAmount)
	{
		this.oSettlementAmount = oSettlementAmount;
	}

	public String getoPurposeCode()
	{
		return this.oPurposeCode;
	}

	public void setoPurposeCode(String oPurposeCode)
	{
		this.oPurposeCode = oPurposeCode;
	}

	public String getRemittanceInformation()
	{
		return this.remittanceInformation;
	}

	public void setRemittanceInformation(String remittanceInformation)
	{
		this.remittanceInformation = remittanceInformation;
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

	public String getMandateId()
	{
		return this.mandateId;
	}

	public void setMandateId(String mandateId)
	{
		this.mandateId = mandateId;
	}
}
