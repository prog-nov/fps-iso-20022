package com.forms.ffp.bussiness.iclfps.fpscel001;

import java.util.Date;
import java.util.List;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_cel_001_001_01.RevokedCertificate;
import com.forms.ffp.bussiness.FFPVOBase;

public class FFPVOFpsCel001 extends FFPVOBase
{

	private String msgId;

	private Date creDtTm;

	private int totalRevokedCert;

	private List<RevokedCertificate> revokedEcertList;

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

	public Date getCreDtTm()
	{
		return creDtTm;
	}

	public void setCreDtTm(Date creDtTm)
	{
		this.creDtTm = creDtTm;
	}

	public int getTotalRevokedCert()
	{
		return totalRevokedCert;
	}

	public void setTotalRevokedCert(int totalRevokedCert)
	{
		this.totalRevokedCert = totalRevokedCert;
	}

	public List<RevokedCertificate> getRevokedEcertList()
	{
		return revokedEcertList;
	}

	public void setRevokedEcertList(List<RevokedCertificate> revokedEcertList)
	{
		this.revokedEcertList = revokedEcertList;
	}

}
