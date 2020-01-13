package com.forms.ffp.mq.listener.msg;

import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("participant2FfpAckMsgListener")
public class FFPParticipant2FfpAckMsgListener extends FFPBaseMsgListener
{
	private static Logger _logger = LoggerFactory.getLogger(FFPParticipant2FfpAckMsgListener.class);

	@Value("${received.message.folder}")
	private String receivedMessageFolder;
	
	@Override
	public void onMessageParse(TextMessage message, String mqName) throws Exception
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean onValidateSignature(TextMessage message, boolean ignoreException)
	{
		return true;
	}

	public void handleMessage()
	{
		// TODO
	}

}
