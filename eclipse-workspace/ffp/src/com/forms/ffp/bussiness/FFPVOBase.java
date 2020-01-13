package com.forms.ffp.bussiness;

import java.util.Date;

import com.forms.ffp.core.msg.iclfps.FFPISO20022MessageWrapper;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;

public class FFPVOBase
{
	private FFPISO20022MessageWrapper iclfpsWrapper;
	private FFPParticipantMessageWrapper participantWrapper;
	
	private String fromClrSysMmbId;
	private String fromBic;
	private String toClrSysMmbId;
	private String toBic;
	protected String bizMsgIdr;
	protected String msgDefIdr;
	protected String bizSvc;
	protected Date createDate;

	public FFPISO20022MessageWrapper getIclfpsWrapper()
	{
		return iclfpsWrapper;
	}

	public void setIclfpsWrapper(FFPISO20022MessageWrapper iclfpsWrapper)
	{
		this.iclfpsWrapper = iclfpsWrapper;
	}
	
	public FFPParticipantMessageWrapper getParticipantWrapper()
	{
		return participantWrapper;
	}

	public void setParticipantWrapper(FFPParticipantMessageWrapper participantWrapper)
	{
		this.participantWrapper = participantWrapper;
	}
	
	public String getFromClrSysMmbId()
	{
		return fromClrSysMmbId;
	}

	public void setFromClrSysMmbId(String fromClrSysMmbId)
	{
		this.fromClrSysMmbId = fromClrSysMmbId;
	}

	public String getFromBic()
	{
		return fromBic;
	}

	public void setFromBic(String fromBic)
	{
		this.fromBic = fromBic;
	}

	public String getToClrSysMmbId()
	{
		return toClrSysMmbId;
	}

	public void setToClrSysMmbId(String toClrSysMmbId)
	{
		this.toClrSysMmbId = toClrSysMmbId;
	}

	public String getToBic()
	{
		return toBic;
	}

	public void setToBic(String toBic)
	{
		this.toBic = toBic;
	}

	public String getBizMsgIdr()
	{
		return bizMsgIdr;
	}

	public void setBizMsgIdr(String bizMsgIdr)
	{
		this.bizMsgIdr = bizMsgIdr;
	}

	public String getMsgDefIdr()
	{
		return msgDefIdr;
	}

	public void setMsgDefIdr(String msgDefIdr)
	{
		this.msgDefIdr = msgDefIdr;
	}

	public String getBizSvc()
	{
		return bizSvc;
	}

	public void setBizSvc(String bizSvc)
	{
		this.bizSvc = bizSvc;
	}

	public Date getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

}
