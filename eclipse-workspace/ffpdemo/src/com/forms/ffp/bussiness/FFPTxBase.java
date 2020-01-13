package com.forms.ffp.bussiness;

import com.forms.ffp.adaptor.jaxb.iclfps.xsd.fps_envelope.ISO20022BusinessDataV01;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;

public abstract class FFPTxBase
{
	protected String serviceName;

	protected FFPJbBase txJb;

	public abstract void perform() throws Exception;

	public abstract void validate() throws Exception;

	public void parseISO20022BizData(ISO20022BusinessDataV01 bizData) throws Exception
	{
		
	}
	
	// TODO Amend
	public void parseParticipantFrmData(ROOT root) throws Exception
	{
		
	}
	
	/**
	 * 发送消息到指定消息一个或多个消息队列当中
	 * @param message
	 * @param destination
	 */
	public void sendMsg(String message, StringBuilder destination) {
		
	}
	

	public String getServiceName()
	{
		return serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public FFPJbBase getTxJb()
	{
		return txJb;
	}

	public void setTxJb(FFPJbBase txJb)
	{
		this.txJb = txJb;
	}

}
