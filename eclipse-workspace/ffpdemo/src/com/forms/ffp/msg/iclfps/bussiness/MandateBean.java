package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class MandateBean extends MandateBaseBean
{
	private String creditorIdDetailsType;
	@FFPValidating
	@FFPValidateMessage(field = "Creditor")
	private EndUserDetailBean creditorIdDetail;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Creditor Account Number")
	private String creditorAccountNumber;
	@FFPValidateMessage(field = "Creditor Account Numbe Type")
	private String creditorAccountNumberType;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Creditor Agent ID")
	private String creditorAgentId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Creditor Agent BIC")
	private String creditorAgentBic;
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
}

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * MandateBean.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */