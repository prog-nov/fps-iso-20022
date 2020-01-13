package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class FFPAddressingRegistrationSummaryBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Addressing Request ID")
	private String addressingRequestId;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Proxy ID")
	private String proxyId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Proxy ID Type")
	private String proxyIdType;

	public String getAddressingRequestId()
	{
		return this.addressingRequestId;
	}

	public void setAddressingRequestId(String addressingRequestId)
	{
		this.addressingRequestId = addressingRequestId;
	}

	public String getProxyId()
	{
		return this.proxyId;
	}

	public void setProxyId(String proxyId)
	{
		this.proxyId = proxyId;
	}

	public String getProxyIdType()
	{
		return this.proxyIdType;
	}

	public void setProxyIdType(String proxyIdType)
	{
		this.proxyIdType = proxyIdType;
	}
}
