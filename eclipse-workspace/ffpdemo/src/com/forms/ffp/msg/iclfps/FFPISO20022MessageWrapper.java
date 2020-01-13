package com.forms.ffp.msg.iclfps;

import javax.jms.TextMessage;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;

public class FFPISO20022MessageWrapper
{
	protected FpsMessageEnvelope fpsMsg;
	protected TextMessage message;
	protected String priority;
	protected Long receivedTimestamp;
	protected String clearingCode;
	protected String mqName;

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
	
	public String getMqName()
	{
		return this.mqName;
	}

	public void setMqName(String mqName)
	{
		this.mqName = mqName;
	}
}
