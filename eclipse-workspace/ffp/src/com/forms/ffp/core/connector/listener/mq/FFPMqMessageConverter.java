package com.forms.ffp.core.connector.listener.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

public class FFPMqMessageConverter implements MessageConverter
{
	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException
	{
		return message;
	}

	@Override
	public Message toMessage(Object object, Session arg1) throws JMSException, MessageConversionException
	{
		return (Message)object;
	}
}
