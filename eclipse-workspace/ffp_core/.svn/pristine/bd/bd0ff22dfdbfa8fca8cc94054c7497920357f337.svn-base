package com.forms.ffp.core.connecor.sender.mq;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.connecor.sender.FFPSenderAgentInterface;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.msg.iclfps.creator.FFPMsgCreator;
import com.forms.ffp.core.msg.iclfps.creator.FFPMsgProperty;

public class FFPMqSenderAgent implements FFPSenderAgentInterface
{
	private static Logger logger = LoggerFactory.getLogger(FFPApacheMqSenderAgent.class);

	protected FFPMqConfig config;

	protected JmsTemplate jmsTemplate;

	public FFPMqSenderAgent(FFPMqConfig config)
	{
		this.config = config;
		init();
	}

	public void init()
	{
		
	}

	public FFPSendMessageResp sendAcknowledge(String message, Map<String, Object> propertyMap, String priority)
	{
		String destinationName = this.jmsTemplate.getDefaultDestinationName();
		return send(message, propertyMap, destinationName);
	}

	public FFPSendMessageResp sendRequest(String message, Map<String, Object> propertyMap, String priority)
	{
		String destinationName = this.jmsTemplate.getDefaultDestinationName();
		return send(message, propertyMap, destinationName);
	}

	public FFPSendMessageResp send(String message, Map<String, Object> propertyMap, String destinationName)
	{
		FFPSendMessageResp resp = new FFPSendMessageResp();
		resp.setDestination(destinationName);

		if (propertyMap == null)
		{
			propertyMap = new HashMap<String, Object>();
		}

		FFPMsgCreator msgCreator = new FFPMsgCreator();
		msgCreator.setMsgObj(message);

		FFPMsgProperty msgProperty = new FFPMsgProperty();
		msgProperty.setPropertyMap(propertyMap);

		msgCreator.setMsgProperty(msgProperty);
		msgCreator.setMsgConverter(this.jmsTemplate.getMessageConverter());

		if (logger.isDebugEnabled())
		{
			logger.debug(String.format("Send Message to MQ [%s]. Content Length: %s, Property: %s", new Object[] { destinationName, Integer.valueOf(message.length()), propertyMap }));
		}

		Long startTime = Long.valueOf(System.currentTimeMillis());
		this.jmsTemplate.send(destinationName, msgCreator);

		Long diff = Long.valueOf(System.currentTimeMillis() - startTime.longValue());

		if (logger.isInfoEnabled())
		{
			logger.info(String.format("Send Message to MQ [%s] Completed. Total time [%s]ms", new Object[] { destinationName, diff }));
		}
		try
		{
			resp.setJmsMessageId(msgCreator.getMsg().getJMSMessageID());
			resp.setSentSysTime(Long.valueOf(msgCreator.getMsg().getJMSTimestamp()));
		} catch (Exception e)
		{
			if (logger.isErrorEnabled())
			{
				logger.error("Fail to retrieve JMS Message Information", e);
			}
		}

		return resp;
	}

	public FFPMqConfig getConfig()
	{
		return this.config;
	}

	public void setConfig(FFPMqConfig config)
	{
		this.config = config;
	}

	public JmsTemplate getJmsTemplate()
	{
		return this.jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate)
	{
		this.jmsTemplate = jmsTemplate;
	}
}
