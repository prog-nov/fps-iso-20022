package com.forms.ffp.mq.listener.msg;

import java.sql.Timestamp;

import javax.jms.Message;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.utils.FFPDateUtils;

public abstract class FFPBaseMsgListener
{
	private static Logger _logger = LoggerFactory.getLogger(FFPBaseMsgListener.class);

	public abstract void onMessageParse(TextMessage message, String mqName) throws Exception;
	
	public abstract boolean onValidateSignature(TextMessage message, boolean ignoreException) throws Exception;
	
	public abstract void handleMessage() throws Exception;
	
	public void onMessage(Message message, String mqName) throws Exception
	{
		Long sysTime = Long.valueOf(System.currentTimeMillis());

		if (!(message instanceof TextMessage))
		{
			if (_logger.isErrorEnabled())
			{
				_logger.error(String.format("[BANK %s]>> Skip handling message due to message is not TextMessage: %s", new Object[] { mqName, message.getJMSMessageID() }));
			}
			return;
		}
		
		TextMessage textMessage = (TextMessage) message;
		onValidateSignature(textMessage, true);
		
		onMessageParse(textMessage, mqName);

		if (_logger.isInfoEnabled())
		{
			_logger.info(String.format("[MQNAME %s]>> Message received on [%s].",
					new Object[] { mqName, sysTime != null ? FFPDateUtils.getTimeStr(new Timestamp(sysTime.longValue()), "yyyy-MM-dd-HH.mm.ss.SSS") : "null" }));
		}
		
		handleMessage();
	}
}
