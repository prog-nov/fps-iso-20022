package com.forms.ffp.bussiness.iclfps.camt054;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsPaymentNotification;

public class FFPVOCamt054 extends FFPVOBase
{

	private String msgId;
	private Date MsgCreateTs;

	private List<FFPDtFpsPaymentNotification> notifList;

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

	public Date getMsgCreateTs()
	{
		return MsgCreateTs;
	}

	public void setMsgCreateTs(Date creDtTm)
	{
		MsgCreateTs = creDtTm;
	}

	public List<FFPDtFpsPaymentNotification> getNotifList()
	{
		if(notifList == null)
			notifList = new ArrayList<FFPDtFpsPaymentNotification>();
		return notifList;
	}

	public void setNotifList(List<FFPDtFpsPaymentNotification> notifList)
	{
		this.notifList = notifList;
	}

}
