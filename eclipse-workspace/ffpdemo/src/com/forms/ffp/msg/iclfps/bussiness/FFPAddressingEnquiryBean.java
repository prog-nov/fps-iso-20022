package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class FFPAddressingEnquiryBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Addressing Request ID")
	private String addressingRequestId;
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Member ID")
	private String memberId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Member BIC")
	private String memberBic;
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Proxy ID")
	private String proxyId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Proxy ID Type")
	private String proxyIdType;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Purpose Code")
	private String purposeCode;

	public String getAddressingRequestId()
	{
		return this.addressingRequestId;
	}

	public void setAddressingRequestId(String addressingRequestId)
	{
		this.addressingRequestId = addressingRequestId;
	}

	public String getMemberId()
	{
		return this.memberId;
	}

	public void setMemberId(String memberId)
	{
		this.memberId = memberId;
	}

	public String getMemberBic()
	{
		return this.memberBic;
	}

	public void setMemberBic(String memberBic)
	{
		this.memberBic = memberBic;
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

	public String getPurposeCode()
	{
		return this.purposeCode;
	}

	public void setPurposeCode(String purposeCode)
	{
		this.purposeCode = purposeCode;
	}
}
