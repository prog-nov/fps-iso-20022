package com.forms.ffp.core.msg.iclfps;

import javax.jms.TextMessage;

import com.forms.ffp.adaptor.jaxb.iclfps.fps_envelope_01.FpsMessageEnvelope;
import com.forms.ffp.core.msg.FFPMessageWrapper;

public class FFPISO20022MessageWrapper extends FFPMessageWrapper
{
	protected FpsMessageEnvelope fpsMsg;
	protected TextMessage message;
	protected String priority;
	protected Long receivedTimestamp;
	protected String clearingCode;
	protected String queueName;

	public FpsMessageEnvelope getFpsMsg()
	{
		return this.fpsMsg;
	}

	public void setFpsMsg(FpsMessageEnvelope fpsMsg)
	{
		this.fpsMsg = fpsMsg;
	}

	public TextMessage getMessage()
	{
		return this.message;
	}

	public void setMessage(TextMessage message)
	{
		this.message = message;
	}

	public String getPriority()
	{
		return this.priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}

	public Long getReceivedTimestamp()
	{
		return this.receivedTimestamp;
	}

	public void setReceivedTimestamp(Long receivedTimestamp)
	{
		this.receivedTimestamp = receivedTimestamp;
	}

	public String getClearingCode()
	{
		return this.clearingCode;
	}

	public void setClearingCode(String clearingCode)
	{
		this.clearingCode = clearingCode;
	}
	
	public String getQueueName()
	{
		return this.queueName;
	}

	public void setQueueName(String mqName)
	{
		this.queueName = mqName;
	}
}
