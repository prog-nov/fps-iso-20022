package com.forms.ffp.msg.participant;

import javax.jms.TextMessage;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.FpsMessageEnvelope;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;


public class FFPParticipantMessageWrapper
{
	protected ROOT root;
	protected TextMessage message;
	protected String mqName;
	
	protected String priority;
	protected Long receivedTimestamp;
	protected String clearingCode;
	
	
	public ROOT getRoot() {
		return root;
	}
	
	public void setRoot(ROOT root) {
		this.root = root;
	}
	
	public TextMessage getMessage()
	{
		return this.message;
	}


	public void setMessage(TextMessage message)
	{
		this.message = message;
	}

	public String getMqName()
	{
		return this.mqName;
	}

	public void setMqName(String mqName)
	{
		this.mqName = mqName;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Long getReceivedTimestamp() {
		return receivedTimestamp;
	}

	public void setReceivedTimestamp(Long receivedTimestamp) {
		this.receivedTimestamp = receivedTimestamp;
	}

	public String getClearingCode() {
		return clearingCode;
	}

	public void setClearingCode(String clearingCode) {
		this.clearingCode = clearingCode;
	}
	
	
}
