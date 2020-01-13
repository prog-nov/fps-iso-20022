package com.forms.ffp.core.msg.participant;

import java.net.Socket;

import javax.jms.TextMessage;

import com.forms.ffp.core.msg.FFPMessageWrapper;

public class FFPParticipantMessageWrapper extends FFPMessageWrapper
{
	protected com.forms.ffp.adaptor.jaxb.participant.request.ROOT requestRoot;
	protected com.forms.ffp.adaptor.jaxb.participant.response.ROOT responseRoot;
	protected TextMessage message;
	protected String priority;
	protected Long receivedTimestamp;
	protected String clearingCode;
	protected String queueName;
	protected Socket socket;

	public com.forms.ffp.adaptor.jaxb.participant.request.ROOT getRequestRoot()
	{
		return requestRoot;
	}

	public void setRequestRoot(com.forms.ffp.adaptor.jaxb.participant.request.ROOT requestRoot)
	{
		this.requestRoot = requestRoot;
	}

	public com.forms.ffp.adaptor.jaxb.participant.response.ROOT getResponseRoot()
	{
		return responseRoot;
	}

	public void setResponseRoot(com.forms.ffp.adaptor.jaxb.participant.response.ROOT responseRoot)
	{
		this.responseRoot = responseRoot;
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
	
	public Socket getSocket()
	{
		return this.socket;
	}

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}
}
