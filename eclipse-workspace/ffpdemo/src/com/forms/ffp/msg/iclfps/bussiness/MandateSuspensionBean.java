package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class MandateSuspensionBean extends FFPBaseBussinessDataBean
{
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Suspension Request ID")
	private String suspensionRequestId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Suspension Reason Code")
	private String suspensionReasonCode;
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,105}" })
	@FFPValidateMessage(field = "Suspension Reason Additional Information")
	private String suspensionReasonAdditionalInformation;
	@FFPValidating
	private MandateBean originalMandate;

	public String getSuspensionRequestId()
	{
		return this.suspensionRequestId;
	}

	public void setSuspensionRequestId(String suspensionRequestId)
	{
		this.suspensionRequestId = suspensionRequestId;
	}

	public String getSuspensionReasonCode()
	{
		return this.suspensionReasonCode;
	}

	public void setSuspensionReasonCode(String suspensionReasonCode)
	{
		this.suspensionReasonCode = suspensionReasonCode;
	}

	public String getSuspensionReasonAdditionalInformation()
	{
		return this.suspensionReasonAdditionalInformation;
	}

	public void setSuspensionReasonAdditionalInformation(String suspensionReasonAdditionalInformation)
	{
		this.suspensionReasonAdditionalInformation = suspensionReasonAdditionalInformation;
	}

	public MandateBean getOriginalMandate()
	{
		return this.originalMandate;
	}

	public void setOriginalMandate(MandateBean originalMandate)
	{
		this.originalMandate = originalMandate;
	}
}
