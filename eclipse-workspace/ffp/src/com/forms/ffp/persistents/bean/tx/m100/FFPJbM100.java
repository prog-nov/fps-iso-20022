 package com.forms.ffp.persistents.bean.tx.m100;

import java.util.Date;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbM100 extends FFPJbBaseFin
{
	private String jnlNo;
	private String msgId;
	private String rcptMd;
	private String accpStatus;
	private String RjctCd;
	private Date swtchgTs;

	
	
	public String getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(String jnlNo)
	{
		this.jnlNo = jnlNo;
	}

	public String getRjctCd()
	{
		return RjctCd;
	}

	public void setRjctCd(String rjctCd)
	{
		RjctCd = rjctCd;
	}

	public String getRcptMd()
	{
		return rcptMd;
	}

	public void setRcptMd(String rcptMd)
	{
		this.rcptMd = rcptMd;
	}

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

	public String getAccpStatus()
	{
		return accpStatus;
	}

	public void setAccpStatus(String accpStatus)
	{
		this.accpStatus = accpStatus;
	}

	public Date getSwtchgTs()
	{
		return swtchgTs;
	}

	public void setSwtchgTs(Date swtchgTs)
	{
		this.swtchgTs = swtchgTs;
	}

}
