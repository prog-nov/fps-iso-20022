package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class MandateAcceptanceBean extends FFPBaseBussinessDataBean
{
	@FFPValidateMessage(field = "Accepted")
	private String accepted;
	@FFPValidateMessage(field = "Reject Reason Code")
	private String rejectReasonCode;
	@FFPValidateMessage(field = "Reject Reason Additional Information")
	private String additionalRejectReasonInformation;
	@FFPValidating
	private MandateBean originalMandate;

	public String getAccepted()
	{
		return this.accepted;
	}

	public void setAccepted(String accepted)
	{
		this.accepted = accepted;
	}

	public String getRejectReasonCode()
	{
		return this.rejectReasonCode;
	}

	public void setRejectReasonCode(String rejectReasonCode)
	{
		this.rejectReasonCode = rejectReasonCode;
	}

	public String getAdditionalRejectReasonInformation()
	{
		return this.additionalRejectReasonInformation;
	}

	public void setAdditionalRejectReasonInformation(String additionalRejectReasonInformation)
	{
		this.additionalRejectReasonInformation = additionalRejectReasonInformation;
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
 * MandateAcceptanceBean.class Java compiler version: 8 (52.0) JD-Core Version:
 * 0.7.1
 */