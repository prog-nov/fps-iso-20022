package com.forms.ffp.msg.iclfps.bussiness;

import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidatePattern;
import com.forms.ffp.msg.iclfps.validation.FFPValidateRequired;

public class FFPAddressingAmendmentBean extends FFPBaseBussinessDataBean
{
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 35)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,35}" })
	@FFPValidateMessage(field = "Addressing Request ID")
	private String addressingRequestId;
	@FFPValidateRequired
	@FFPValidatePattern(pattern = { "[A-Z0-9]{3}" })
	@FFPValidateMessage(field = "Member ID")
	private String memberId;
	@FFPValidatePattern(pattern = { "[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}" })
	@FFPValidateMessage(field = "Member BIC")
	private String memberBic;
	@FFPValidateRequired
	@FFPValidateLength(minLength = 1, maxLength = 34)
	@FFPValidatePattern(pattern = { "[0-9a-zA-Z !@#$%^&amp;\\*\\(\\)_+~\\{\\}|:&quot;&lt;&gt;?`\\-=\\[\\]\\\\;',\\./]{1,34}" })
	@FFPValidateMessage(field = "Proxy ID")
	private String proxyId;
	@FFPValidateRequired
	@FFPValidateMessage(field = "Proxy ID Type")
	private String proxyIdType;
	@FFPValidateMessage(field = "Customer Name Language #1")
	private String customerNameLang1;
	@FFPValidateMessage(field = "Customer Full Name #1")
	private String customerFullName1;
	@FFPValidateMessage(field = "Customer Display Name #1")
	private String customerDisplayName1;
	@FFPValidateMessage(field = "Customer Name Language #2")
	private String customerNameLang2;
	@FFPValidateMessage(field = "Customer Full Name #2")
	private String customerFullName2;
	@FFPValidateMessage(field = "Customer Display Name #2")
	private String customerDisplayName2;
	@FFPValidateMessage(field = "Customer Type")
	private String customerType;
	@FFPValidateMessage(field = "Supported Option Code 1")
	private String supportedOptionCode1;
	@FFPValidateMessage(field = "Supported Option Code 2")
	private String supportedOptionCode2;
	@FFPValidateMessage(field = "Default Indicator")
	private String defaultIndc;
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

	public String getCustomerNameLang1()
	{
		return this.customerNameLang1;
	}

	public void setCustomerNameLang1(String customerNameLang1)
	{
		this.customerNameLang1 = customerNameLang1;
	}

	public String getCustomerFullName1()
	{
		return this.customerFullName1;
	}

	public void setCustomerFullName1(String customerFullName1)
	{
		this.customerFullName1 = customerFullName1;
	}

	public String getCustomerDisplayName1()
	{
		return this.customerDisplayName1;
	}

	public void setCustomerDisplayName1(String customerDisplayName1)
	{
		this.customerDisplayName1 = customerDisplayName1;
	}

	public String getCustomerNameLang2()
	{
		return this.customerNameLang2;
	}

	public void setCustomerNameLang2(String customerNameLang2)
	{
		this.customerNameLang2 = customerNameLang2;
	}

	public String getCustomerFullName2()
	{
		return this.customerFullName2;
	}

	public void setCustomerFullName2(String customerFullName2)
	{
		this.customerFullName2 = customerFullName2;
	}

	public String getCustomerDisplayName2()
	{
		return this.customerDisplayName2;
	}

	public void setCustomerDisplayName2(String customerDisplayName2)
	{
		this.customerDisplayName2 = customerDisplayName2;
	}

	public String getCustomerType()
	{
		return this.customerType;
	}

	public void setCustomerType(String customerType)
	{
		this.customerType = customerType;
	}

	public String getSupportedOptionCode1()
	{
		return this.supportedOptionCode1;
	}

	public void setSupportedOptionCode1(String supportedOptionCode1)
	{
		this.supportedOptionCode1 = supportedOptionCode1;
	}

	public String getSupportedOptionCode2()
	{
		return this.supportedOptionCode2;
	}

	public void setSupportedOptionCode2(String supportedOptionCode2)
	{
		this.supportedOptionCode2 = supportedOptionCode2;
	}

	public String getDefaultIndc()
	{
		return this.defaultIndc;
	}

	public void setDefaultIndc(String defaultIndc)
	{
		this.defaultIndc = defaultIndc;
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
