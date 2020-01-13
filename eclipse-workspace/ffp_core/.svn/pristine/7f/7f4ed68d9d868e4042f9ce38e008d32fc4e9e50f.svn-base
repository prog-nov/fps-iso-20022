package com.forms.ffp.core.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.config.FFPConnectorConfig;
import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.mq.FFPQueueConfig;
import com.forms.ffp.core.config.mq.FFPQueueManagerConfig;
import com.forms.ffp.core.config.runtime.PropertiesFile;
import com.forms.ffp.core.config.tcp.FFPTcpConfig;
import com.forms.ffp.core.define.FFPConstants;

public class FFPConnectorConfigSvc
{
	private static Logger _logger = LoggerFactory.getLogger(FFPConnectorConfigSvc.class);

	private static FFPConnectorConfigSvc instance = null;

	private Map<String, FFPConnectorConfig> connectConfigMap;

	public static FFPConnectorConfigSvc getInstance() throws Exception
	{
		if (instance == null)
		{
			instance = new FFPConnectorConfigSvc();
		}
		return instance;
	}

	public FFPConnectorConfigSvc() throws Exception
	{
		try
		{
			initConnectorConfig();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void initConnectorConfig() throws Exception
	{
		if (this.connectConfigMap == null)
		{
			this.connectConfigMap = new HashMap<String, FFPConnectorConfig>();
		}

		this.connectConfigMap.clear();

		PropertiesFile loc_rootPro = FFPRuntimeConfigSvc.getInstance().getRootConfig();
		PropertiesFile loc_connectorPro = FFPRuntimeConfigSvc.getInstance().getPropertiesFile(loc_rootPro.get("config.connector.filename"));
		;
		String connectorKey = "connector.key.{index}".replace(".", "\\.").replace("{index}", "([a-zA-Z0-9]+)");
		for (String key : loc_rootPro.keySet())
		{
			if (key.matches(connectorKey))
			{
				try
				{
					String connectorRefName = loc_rootPro.get(key);
					String value = loc_connectorPro.get(connectorRefName);
					String connectorType = loc_connectorPro.get(value + ".connector.type");
					if (FFPConstants.CONNECTTOR_TYPE_MQ_APACHEMQ.equals(connectorType) || FFPConstants.CONNECTTOR_TYPE_MQ_WEBPHEREMQ.equals(connectorType))
					{
						FFPMqConfig mqConfig = new FFPMqConfig();
						mqConfig.setConnectorName(value);
						mqConfig.setConnectorType(connectorType);

						FFPQueueManagerConfig queuemgrConfig = new FFPQueueManagerConfig();
						queuemgrConfig.setHostName(loc_connectorPro.get(value + ".mq.hostname"));
						queuemgrConfig.setPort(loc_connectorPro.getInteger(value + ".mq.port"));
						queuemgrConfig.setQueueManagerName(loc_connectorPro.get(value + ".mq.queueManagerName"));
						queuemgrConfig.setCcsid(loc_connectorPro.getInteger(value + ".mq.ccsid"));
						queuemgrConfig.setSslEnable(loc_connectorPro.getBoolean(value + ".mq.ssl.enable"));
						queuemgrConfig.setSslCipherSuite(loc_connectorPro.get(value + ".mq.ssl.cipherSuite"));
						queuemgrConfig.setSslPeerName(loc_connectorPro.get(value + ".mq.ssl.peerName"));
						queuemgrConfig.setSslTrustStoreFilename(loc_connectorPro.get(value + ".mq.ssl.trustStore"));
						queuemgrConfig.setSslTrustStorePassword(loc_connectorPro.get(value + ".mq.ssl.trustStorePassword"));
						queuemgrConfig.setSslKeyStoreFilename(loc_connectorPro.get(value + ".mq.ssl.keyStore"));
						queuemgrConfig.setSslKeyStorePassword(loc_connectorPro.get(value + ".mq.ssl.keyStorePassword"));
						queuemgrConfig.setSubscribeInward(loc_connectorPro.getBoolean(value + ".mq.in.subscribe"));
						queuemgrConfig.setClientChannel(loc_connectorPro.get(value + ".mq.clientChannel"));
						queuemgrConfig.setUser(loc_connectorPro.get(value + ".mq.user"));
						queuemgrConfig.setPassword(loc_connectorPro.get(value + ".mq.password"));
						mqConfig.setSessionCacheSize(loc_connectorPro.getInteger(value + ".mq.sessionCacheSize"));

						if (_logger.isInfoEnabled())
						{
							_logger.info("Queue Manager configuration loaded. Reference: [{}] QM: [{}]", value, queuemgrConfig.getQueueManagerName());
						}

						mqConfig.setQmCfg(queuemgrConfig);

						String sendqueueRegex = ("(" + value + ")").replace(".", "\\.") + "(\\.send\\.)(req|ack)(\\.)([0-9a-zA-Z]+)";

						Map<String, FFPQueueConfig> sendQueueNameList = new HashMap<String, FFPQueueConfig>();
						for (String queuekey : loc_connectorPro.keySet())
						{
							if (queuekey.matches(sendqueueRegex))
							{
								FFPQueueConfig queueConfig = new FFPQueueConfig();
								queueConfig.setQueueName(loc_connectorPro.get(queuekey));
								sendQueueNameList.put(queuekey, queueConfig);
							}
						}
						mqConfig.setSendQueueNameMap(sendQueueNameList);
						
						mqConfig.setDefaultDestinationQueueName(loc_connectorPro.get(value + ".send.default.queue"));

						if(queuemgrConfig.getSubscribeInward())
						{
							String receivequeueRegex = ("(" + value + ")").replace(".", "\\.") + "(\\.receive\\.)(req|ack)(\\.)([0-9a-zA-Z]+)";

							Map<String, FFPQueueConfig> receiveQueueNameList = new HashMap<String, FFPQueueConfig>();
							for (String queuekey : loc_connectorPro.keySet())
							{
								if (queuekey.matches(receivequeueRegex))
								{
									FFPQueueConfig queueConfig = new FFPQueueConfig();
									String[] arrry = loc_connectorPro.get(queuekey).split(",");
									queueConfig.setQueueName(arrry[0].trim());
									queueConfig.setListener(arrry[1].trim());
									queueConfig.setThreadPoolSize(Integer.valueOf(arrry[2].trim()));
									receiveQueueNameList.put(queuekey, queueConfig);
								}
							}
							mqConfig.setReceiveQueueNameMap(receiveQueueNameList);
						}
						this.connectConfigMap.put(connectorRefName, mqConfig);
					}
					else if(FFPConstants.CONNECTTOR_TYPE_TCP.equals(connectorType))
					{
						FFPTcpConfig tcpConfig = new FFPTcpConfig();
						tcpConfig.setConnectorName(value);
						tcpConfig.setConnectorType(connectorType);
						tcpConfig.setReceivehost(loc_connectorPro.get(value + ".tcp.receive.host"));
						tcpConfig.setReceiveport(loc_connectorPro.getInteger(value + ".tcp.receive.port"));
						tcpConfig.setReceiveSessionCount(loc_connectorPro.getInteger(value + ".tcp.receive.sessionCacheSize"));
						tcpConfig.setReceiveTimeOut(loc_connectorPro.getInteger(value + ".tcp.receive.timeout"));
						tcpConfig.setReceiveListenerClass(loc_connectorPro.get(value + ".tcp.receive.listener.class"));
						tcpConfig.setSendhost(loc_connectorPro.get(value + ".tcp.send.host"));
						tcpConfig.setSendport(loc_connectorPro.getInteger(value + ".tcp.send.port"));
						this.connectConfigMap.put(connectorRefName, tcpConfig);
					}
				} catch (Exception e)
				{
					if (_logger.isErrorEnabled())
					{
						_logger.error(String.format("Failed to map QM properties '%s'", new Object[] { key }), e);
					}
				}
			}
		}
	}

	public Map<String, FFPConnectorConfig> getConnectorConfigMap()
	{
		return this.connectConfigMap;
	}
}
