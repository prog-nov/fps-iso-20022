package com.forms.ffp.msg.iclfps.bussiness;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class NewCertificateBean
{
	@FFPValidateMessage(field = "Digital Certificate")
	private String digitalCertificate;
	@FFPValidateMessage(field = "Effective Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date effectiveDate;

	public String getDigitalCertificate()
	{
		return this.digitalCertificate;
	}

	public void setDigitalCertificate(String digitalCertificate)
	{
		this.digitalCertificate = digitalCertificate;
	}

	public Date getEffectiveDate()
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}
}
