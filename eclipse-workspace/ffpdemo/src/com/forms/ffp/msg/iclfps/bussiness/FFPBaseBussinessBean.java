package com.forms.ffp.msg.iclfps.bussiness;

import java.util.List;

import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.sun.istack.internal.NotNull;

public abstract class FFPBaseBussinessBean
{
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "From Member ID")
	private String frMmbId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "From Member BIC")
	private String frMmbBic;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "To Member ID")
	private String toMmbId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "To Member BIC")
	private String toMmbBic;
	private String businessService;
	@NotNull
	@FFPValidateRequired
	@FFPValidateMessage(field = "Message ID")
	private String messageId;
	@NotNull
	@FFPValidateRequired
	@FFPValidateMessage(field = "Message Definition Identifier")
	private String messageDefinitionIdentifier;

	public String getFrMmbId()
	{
		return this.frMmbId;
	}

	public void setFrMmbId(String frMmbId)
	{
		this.frMmbId = frMmbId;
	}

	public String getFrMmbBic()
	{
		return this.frMmbBic;
	}

	public void setFrMmbBic(String frMmbBic)
	{
		this.frMmbBic = frMmbBic;
	}

	public String getToMmbId()
	{
		return this.toMmbId;
	}

	public void setToMmbId(String toMmbId)
	{
		this.toMmbId = toMmbId;
	}

	public String getToMmbBic()
	{
		return this.toMmbBic;
	}

	public void setToMmbBic(String toMmbBic)
	{
		this.toMmbBic = toMmbBic;
	}

	public String getBusinessService()
	{
		return this.businessService;
	}

	public void setBusinessService(String businessService)
	{
		this.businessService = businessService;
	}

	public String getMessageId()
	{
		return this.messageId;
	}

	public void setMessageId(String messageId)
	{
		this.messageId = messageId;
	}

	public String getMessageDefinitionIdentifier()
	{
		return this.messageDefinitionIdentifier;
	}

	public void setMessageDefinitionIdentifier(String messageDefinitionIdentifier)
	{
		this.messageDefinitionIdentifier = messageDefinitionIdentifier;
	}

	public abstract int getCount();

	public int countBean(List<? extends FFPBaseBussinessDataBean> list)
	{
		if ((list != null) && (list.size() > 0))
		{
			int count = 0;
			for (int i = 0; i < list.size(); i++)
			{
				count += ((FFPBaseBussinessDataBean) list.get(i)).getNumOfCopy();
			}
			return count;
		}
		return 0;
	}

	public int countBean(FFPBaseBussinessDataBean bean)
	{
		if (bean != null)
		{
			return bean.getNumOfCopy();
		}
		return 0;
	}
}
