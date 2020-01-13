package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class FFPMandateAmendmentBean extends MandateBaseBean
{
	@FFPValidateRequired
	@FFPValidateMessage(field = "Amendment Reason Code")
	private String amendmentReasonCode;
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,105}" })
	@FFPValidateMessage(field = "Amendment Reason Additional Information")
	private String amendmentReasonAdditionalInformation;
	@FFPValidating
	private MandateBean originalMandate;

	public String getAmendmentReasonCode()
	{
		return this.amendmentReasonCode;
	}

	public void setAmendmentReasonCode(String amendmentReasonCode)
	{
		this.amendmentReasonCode = amendmentReasonCode;
	}

	public String getAmendmentReasonAdditionalInformation()
	{
		return this.amendmentReasonAdditionalInformation;
	}

	public void setAmendmentReasonAdditionalInformation(String amendmentReasonAdditionalInformation)
	{
		this.amendmentReasonAdditionalInformation = amendmentReasonAdditionalInformation;
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
