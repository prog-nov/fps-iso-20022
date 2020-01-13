package com.forms.ffp.core.connector.listener.msg;

import java.net.Socket;

import javax.annotation.Resource;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.bussiness.helper.FFPParticipantHelper;
import com.forms.ffp.core.msg.FFPMessageWrapper;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageWrapper;

public class FFPParticipant2FfpReqMsgListener extends FFPBaseMsgListener
{
	private static Logger _logger = LoggerFactory.getLogger(FFPParticipant2FfpReqMsgListener.class);

	@Resource(name = "participantHelper")
	private FFPParticipantHelper helper;

	@Override
	public boolean onValidateSignature(TextMessage message, boolean ignoreException) throws Exception
	{
		return true;
	}

	@Override
	public FFPParticipantMessageWrapper onMessageParse(TextMessage message, String connectorName, String priority) throws Exception
	{
		return null;
	}

	public FFPParticipantMessageWrapper onTcpMessageParse(String message, Socket socket) throws Exception
	{
		FFPParticipantMessageWrapper msgWrapper = new FFPParticipantMessageWrapper();
		msgWrapper.setSocket(socket);
		msgWrapper.setReceivedTimestamp(Long.valueOf(System.currentTimeMillis()));

		ROOT root = null;

		try
		{
			root = FFPParticipantMessageConverter.parseXml2RequestObject(message);
			msgWrapper.setRequestRoot(root);
		} catch (Exception e)
		{
			_logger.error("[AGENT]>> Failed to parse message", e);
			throw e;
		}
		return msgWrapper;
	}
	
	@Override
	public void handleMessage(FFPMessageWrapper ip_warpper)
	{
		FFPParticipantMessageWrapper warpper = (FFPParticipantMessageWrapper)ip_warpper;
		try
		{
			this.helper.helperParticipant("inward", warpper);
		} catch (Exception e)
		{
			_logger.error(String.format("[%s]>> Failed to handler message.", new Object[] { warpper.getQueueName() }), e);
		}
	}
}
