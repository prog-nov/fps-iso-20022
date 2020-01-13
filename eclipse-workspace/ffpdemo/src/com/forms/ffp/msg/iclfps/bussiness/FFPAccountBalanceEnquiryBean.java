package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class FFPAccountBalanceEnquiryBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Required Message Name ID")
	private String requiredMessageNameId;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Account ID")
	private String accountId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Account Currency")
	private String accountCurrency;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Account Owner BIC")
	private String accountOwnerBic;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Account Owner ID")
	private String accountOwnerId;

	public String getRequiredMessageNameId()
	{
		return this.requiredMessageNameId;
	}

	public void setRequiredMessageNameId(String requiredMessageNameId)
	{
		this.requiredMessageNameId = requiredMessageNameId;
	}

	public String getAccountId()
	{
		return this.accountId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getAccountCurrency()
	{
		return this.accountCurrency;
	}

	public void setAccountCurrency(String accountCurrency)
	{
		this.accountCurrency = accountCurrency;
	}

	public String getAccountOwnerBic()
	{
		return this.accountOwnerBic;
	}

	public void setAccountOwnerBic(String accountOwnerBic)
	{
		this.accountOwnerBic = accountOwnerBic;
	}

	public String getAccountOwnerId()
	{
		return this.accountOwnerId;
	}

	public void setAccountOwnerId(String accountOwnerId)
	{
		this.accountOwnerId = accountOwnerId;
	}
}
