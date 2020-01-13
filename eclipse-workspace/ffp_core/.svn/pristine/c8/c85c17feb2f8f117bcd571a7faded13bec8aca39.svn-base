package com.forms.ffp.core.connector.sender.mq;

import java.util.Map;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.mq.FFPQueueManagerConfig;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.iclfps.FFPSendMessageResp;
import com.forms.ffp.core.service.FFPRuntimeConfigSvc;
import com.forms.ffp.core.utils.FFPSecurityUtils;
import com.ibm.mq.jms.MQXAQueueConnectionFactory;

public class FFPIbmWebSphereMqSenderAgent extends FFPMqSenderAgent
{
	private static Logger logger = LoggerFactory.getLogger(FFPIbmWebSphereMqSenderAgent.class);

	public FFPIbmWebSphereMqSenderAgent(FFPMqConfig config)
	{
		super(config);
	}

	public void init()
	{
		if (logger.isInfoEnabled())
		{
			logger.info("[{}] MQ Sender Agent Initialization started.");
		}
		try
		{
			this.jmsTemplate = new JmsTemplate();

			FFPQueueManagerConfig qmCfg = this.config.getQmCfg();

			MQXAQueueConnectionFactory factory = new MQXAQueueConnectionFactory();
			factory.setHostName(qmCfg.getHostName());
			factory.setPort(qmCfg.getPort().intValue());
			factory.setQueueManager(qmCfg.getQueueManagerName());
			factory.setChannel(qmCfg.getClientChannel());
			factory.setCCSID(qmCfg.getCcsid().intValue());
			factory.setTransportType(1);

			if ((qmCfg.getSslEnable() != null) && (qmCfg.getSslEnable().booleanValue()))
			{
				factory.setSSLPeerName(qmCfg.getSslPeerName());
				factory.setSSLCipherSuite(qmCfg.getSslCipherSuite());
				try
				{
					SSLContext sslContext = FFPSecurityUtils.createSSLContext(FFPRuntimeConfigSvc.getInstance().getConfigFilePath(qmCfg.getSslTrustStoreFilename()), qmCfg.getSslTrustStorePassword(),
							"JKS", FFPRuntimeConfigSvc.getInstance().getConfigFilePath(qmCfg.getSslKeyStoreFilename()), qmCfg.getSslKeyStorePassword(), "JKS");

					factory.setSSLSocketFactory(sslContext.getSocketFactory());
				} catch (Exception e)
				{
					throw new RuntimeException(e);
				}
			}

			UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
			adapter.setTargetConnectionFactory(factory);
			adapter.setUsername(qmCfg.getUser());
			adapter.setPassword(qmCfg.getPassword());

			CachingConnectionFactory ccf = new CachingConnectionFactory();
			ccf.setTargetConnectionFactory(adapter);
			ccf.setSessionCacheSize(this.config.getSessionCacheSize().intValue());

			if (logger.isInfoEnabled())
			{
				logger.info("Connection Factory initialized [{}] Host: [{}:{}] QM: [{}], Client Channel: [{}], CCSID: [{}], SSL Enabled: [{}]",
						new Object[] { ccf.toString(), qmCfg.getHostName(), qmCfg.getPort(), qmCfg.getQueueManagerName(), qmCfg.getClientChannel(), qmCfg.getCcsid(), qmCfg.getSslEnable() });
			}

			this.jmsTemplate.setConnectionFactory(ccf);
			this.jmsTemplate.setSessionTransacted(true);
			this.jmsTemplate.setPubSubDomain(false);
			this.jmsTemplate.setDefaultDestinationName("queue:///" + config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName());

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
			destinationName = "queue:///" + config.getSendQueueNameMap().get(config.getConnectorName() + ".send.ack." + FFPConstants.MQ_LEVEL_PRIORITY_HIGH).getQueueName();
		} else if (FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM.equals(priority))
		{
			destinationName = "queue:///" + config.getSendQueueNameMap().get(config.getConnectorName() + ".send.ack." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName();
		}

		return send(message, propertyMap, destinationName);
	}

	public FFPSendMessageResp sendRequest(String message, Map<String, Object> propertyMap, String priority)
	{
		String destinationName = this.jmsTemplate.getDefaultDestinationName();
		if (FFPConstants.MQ_LEVEL_PRIORITY_HIGH.equals(priority))
		{
			destinationName = "queue:///" + config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_HIGH).getQueueName();
		} else if (FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM.equals(priority))
		{
			destinationName = "queue:///" + config.getSendQueueNameMap().get(config.getConnectorName() + ".send.req." + FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM).getQueueName();
		}

		return send(message, propertyMap, destinationName);
	}
}
