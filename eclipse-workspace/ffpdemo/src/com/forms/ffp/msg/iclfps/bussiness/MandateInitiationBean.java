package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class MandateInitiationBean extends MandateBean
{
	@FFPValidateRequired
	@FFPValidateMessage(field = "Reason")
	private String reason;

	public String getReason()
	{
		return this.reason;
	}

	public void setReason(String reason)
	{
		this.reason = reason;
	}
}
