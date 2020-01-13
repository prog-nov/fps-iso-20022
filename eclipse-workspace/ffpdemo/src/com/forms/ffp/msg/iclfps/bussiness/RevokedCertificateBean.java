package com.forms.ffp.msg.iclfps.bussiness;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class RevokedCertificateBean
{
	@FFPValidateMessage(field = "Digital Certificate")
	private String digitalCertificate;
	@FFPValidateMessage(field = "Revocation Date of Certificate")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date revocationDate;

	public String getDigitalCertificate()
	{
		return this.digitalCertificate;
	}

	public void setDigitalCertificate(String digitalCertificate)
	{
		this.digitalCertificate = digitalCertificate;
	}

	public Date getRevocationDate()
	{
		return this.revocationDate;
	}

	public void setRevocationDate(Date revocationDate)
	{
		this.revocationDate = revocationDate;
	}
}
