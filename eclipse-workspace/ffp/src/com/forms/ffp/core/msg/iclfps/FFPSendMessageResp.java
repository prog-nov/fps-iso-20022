package com.forms.ffp.core.msg.iclfps;

import com.forms.ffp.core.msg.FFPBaseResp;

public class FFPSendMessageResp extends FFPBaseResp
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String destination;

	protected String jmsMessageId;

	protected Long createSysTime;
	
	protected Long sentSysTime;

	public String getDestination()
	{
		return this.destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getJmsMessageId()
	{
		return this.jmsMessageId;
	}

	public void setJmsMessageId(String jmsMessageId)
	{
		this.jmsMessageId = jmsMessageId;
	}

	public Long getCreateSysTime()
	{
		return this.createSysTime;
	}

	public void setCreateSysTime(Long createSysTime)
	{
		this.createSysTime = createSysTime;
	}
	
	public Long getSentSysTime()
	{
		return this.sentSysTime;
	}

	public void setSentSysTime(Long sentSysTime)
	{
		this.sentSysTime = sentSysTime;
	}
}
