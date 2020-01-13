package com.forms.ffp.msg.iclfps.bussiness;

import java.util.List;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;
import com.forms.ffp.msg.iclfps.validation.FFPValidating;

public class FFPCertExchangeRequestBean extends FFPBaseBussinessDataBean
{
	@FFPValidating
	@FFPValidateMessage(field = "New Certificate")
	private List<NewCertificateBean> newCertificates;
	@FFPValidating
	@FFPValidateMessage(field = "Revoked Certificate")
	private List<RevokedCertificateBean> revokedCertificates;

	public List<NewCertificateBean> getNewCertificates()
	{
		return this.newCertificates;
	}

	public void setNewCertificates(List<NewCertificateBean> newCertificates)
	{
		this.newCertificates = newCertificates;
	}

	public List<RevokedCertificateBean> getRevokedCertificates()
	{
		return this.revokedCertificates;
	}

	public void setRevokedCertificates(List<RevokedCertificateBean> revokedCertificates)
	{
		this.revokedCertificates = revokedCertificates;
	}
}
