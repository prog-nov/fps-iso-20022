package com.forms.ffp.core.connecor.sender.mq;

import java.util.Map;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.mq.FFPQueueManagerConfig;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;

public class FFPApacheMqSenderAgent extends FFPMqSenderAgent
{
	private static Logger logger = LoggerFactory.getLogger(FFPApacheMqSenderAgent.class);

	public FFPApacheMqSenderAgent(FFPMqConfig config)
	{
		super(config);
	}

	public void init()
	{
		if (logger.isInfoEnabled())
		{
			logger.info("MQ Sender Agent Initialization started.");
		}
		try
		{
			this.jmsTemplate = new JmsTemplate();

				FFPQueueManagerConfig qmCfg = this.config.getQmCfg();

				ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
				factory.setBrokerURL("tcp://" + qmCfg.getHostName() + ":" + qmCfg.getPort());

				UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
				adapter.setTargetConnectionFactory(factory);
				adapter.setUsername(qmCfg.getUser());
				adapter.setPassword(qmCfg.getPassword());

				CachingConnectionFactory ccf = new CachingConnectionFactory();
				ccf.setTargetConnectionFactory(adapter);
				ccf.setSessionCacheSize(this.config.getSessionCacheSize().intValue());
				
				if (logger.isInfoEnabled())
				{
					logger.info("Connection Factory initialized [{}] Host: [{}:{}] QM: [{}], Client Channel: [{}], CCSID: [{}], SSL Enabled: [{}]", new Object[] {
							ccf.toString(), qmCfg.getHostName(), qmCfg.getPort(), qmCfg.getQueueManagerName(), qmCfg.getClientChannel(), qmCfg.getCcsid(), qmCfg.getSslEnable() });
				}


			this.jmsTemplate.setConnectionFactory(ccf);
			this.jmsTemplate.setSessionTransacted(true);
			this.jmsTemplate.setPubSubDomain(false);
			this.jmsTemplate.setDefaultDestinationName(config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName());

		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public FFPSendMessageResp sendAcknowledge(String message, Map<String, Object> propertyMap, String priority)
	{
		String destinationName = this.jmsTemplate.getDefaultDestinationName();
		if (FFPConstants.MQ_LEVEL_PRIORITY_HIGH.equals(priority))
		{
			destinationName = config.getSendQueueNameMap().get(config.getConnectorName() + ".send.ack." + FFPConstants.MQ_LEVEL_PRIORITY_HIGH).getQueueName();
		} else if (FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM.equals(priority))
		{
			destinationName = config.getSendQueueNameMap().get(config.getConnectorName() + ".send.ack." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName();
		}

		return send(message, propertyMap, destinationName);
	}

	public FFPSendMessageResp sendRequest(String message, Map<String, Object> propertyMap, String priority)
	{
		String destinationName = this.jmsTemplate.getDefaultDestinationName();
		if (FFPConstants.MQ_LEVEL_PRIORITY_HIGH.equals(priority))
		{
			destinationName = config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_HIGH).getQueueName();
		} else if (FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM.equals(priority))
		{
			destinationName = config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName();
		}

		return send(message, propertyMap, destinationName);
	}
}
