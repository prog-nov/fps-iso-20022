package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class FFPStatusEnquiryBean extends FFPBaseBussinessDataBean
{
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Status Request ID")
	private String statusRequestId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original Message ID")
	private String originalMessageId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original Message Name ID")
	private String originalMessageNameId;
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Original Transaction ID")
	private String originalTransactionId;
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Clearing System Reference")
	private String clearingSystemReference;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Debtor Agent BIC")
	private String originalDebtorAgentBic;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Debtor Agent ID")
	private String originalDebtorAgentId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Creditor Agent BIC")
	private String originalCreditorAgentBic;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Creditor Agent ID")
	private String originalCreditorAgentId;

	public String getStatusRequestId()
	{
		return this.statusRequestId;
	}

	public void setStatusRequestId(String statusRequestId)
	{
		this.statusRequestId = statusRequestId;
	}

	public String getOriginalMessageId()
	{
		return this.originalMessageId;
	}

	public void setOriginalMessageId(String originalMessageId)
	{
		this.originalMessageId = originalMessageId;
	}

	public String getOriginalMessageNameId()
	{
		return this.originalMessageNameId;
	}

	public void setOriginalMessageNameId(String originalMessageNameId)
	{
		this.originalMessageNameId = originalMessageNameId;
	}

	public String getOriginalTransactionId()
	{
		return this.originalTransactionId;
	}

	public void setOriginalTransactionId(String originalTransactionId)
	{
		this.originalTransactionId = originalTransactionId;
	}

	public String getClearingSystemReference()
	{
		return this.clearingSystemReference;
	}

	public void setClearingSystemReference(String clearingSystemReference)
	{
		this.clearingSystemReference = clearingSystemReference;
	}

	public String getOriginalDebtorAgentBic()
	{
		return this.originalDebtorAgentBic;
	}

	public void setOriginalDebtorAgentBic(String originalDebtorAgentBic)
	{
		this.originalDebtorAgentBic = originalDebtorAgentBic;
	}

	public String getOriginalDebtorAgentId()
	{
		return this.originalDebtorAgentId;
	}

	public void setOriginalDebtorAgentId(String originalDebtorAgentId)
	{
		this.originalDebtorAgentId = originalDebtorAgentId;
	}

	public String getOriginalCreditorAgentBic()
	{
		return this.originalCreditorAgentBic;
	}

	public void setOriginalCreditorAgentBic(String originalCreditorAgentBic)
	{
		this.originalCreditorAgentBic = originalCreditorAgentBic;
	}

	public String getOriginalCreditorAgentId()
	{
		return this.originalCreditorAgentId;
	}

	public void setOriginalCreditorAgentId(String originalCreditorAgentId)
	{
		this.originalCreditorAgentId = originalCreditorAgentId;
	}
}
