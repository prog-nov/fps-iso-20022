package com.forms.ffp.bussiness.iclfps.pacs004;

import com.forms.ffp.persistents.bean.payment.returnrefund.FFPJbP300;

public class FFPVO_Pacs004_TxInf
{

	private FFPJbP300 p300;

	private FFPVO_Pacs004_RRI01REPLY reply;

	public FFPJbP300 getP300()
	{
		return p300;
	}

	public void setP300(FFPJbP300 p300)
	{
		this.p300 = p300;
	}

	public FFPVO_Pacs004_RRI01REPLY getReply()
	{
		return reply;
	}

	public void setReply(FFPVO_Pacs004_RRI01REPLY reply)
	{
		this.reply = reply;
	}

}
