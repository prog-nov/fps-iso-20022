package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class MandateCancellationBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateMessage(field = "Cancellation Reason Code")
	private String cancellationReasonCode;
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,105}" })
	@FFPValidateMessage(field = "Cancellation Reason Additional Information")
	private String cancellationReasonAdditionalInformation;
	@FFPValidating
	private MandateBean originalMandate;

	public String getCancellationReasonCode()
	{
		return this.cancellationReasonCode;
	}

	public void setCancellationReasonCode(String cancellationReasonCode)
	{
		this.cancellationReasonCode = cancellationReasonCode;
	}

	public String getCancellationReasonAdditionalInformation()
	{
		return this.cancellationReasonAdditionalInformation;
	}

	public void setCancellationReasonAdditionalInformation(String cancellationReasonAdditionalInformation)
	{
		this.cancellationReasonAdditionalInformation = cancellationReasonAdditionalInformation;
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

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * MandateCancellationBean.class Java compiler version: 8 (52.0) JD-Core
 * Version: 0.7.1
 */