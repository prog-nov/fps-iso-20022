package com.forms.ffp.mq.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.forms.ffp.config.FFPRuntimeConfigSvc;
import com.forms.ffp.config.PropertiesFile;
@Component("mqConfigSvc")
public class FFPMqConfigSvc
{
	private static Logger _logger = LoggerFactory.getLogger(FFPMqConfigSvc.class);

	@Resource(name = "runtimeConfigSvc")
	private FFPRuntimeConfigSvc runtimeConfigSvc;
	//@Value("${init.config.folder}")
	@Value("${config.mq.filename}")
	private String propertiesFile;
	private PropertiesFile props;
	
	private static Map<String, FFPMqConfig> qmConfigMap;

	@PostConstruct
	public void init() throws Exception
	{
		try
		{
			this.props = this.runtimeConfigSvc.getPropertiesFile(this.propertiesFile);
			initQueueManagerConfig();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void initQueueManagerConfig()
	{
		if (FFPMqConfigSvc.qmConfigMap == null)
		{
			FFPMqConfigSvc.qmConfigMap = new HashMap<String, FFPMqConfig>();
		}

		FFPMqConfigSvc.qmConfigMap.clear();

		String qmRefNameProp = "qm.refName.{index}".replace(".", "\\.").replace("{index}", "([0-9]+)");
		for (String key : this.props.keySet())
		{
			if (key.matches(qmRefNameProp))
			{
				try
				{
					String value = this.props.get(key);

					String refName = this.props.get(key);
					FFPMqConfig mqConfig = new FFPMqConfig();
					mqConfig.setMqName(refName);

					FFPQueueManagerConfig queuemgrConfig = new FFPQueueManagerConfig();
					queuemgrConfig.setQueueType(this.props.get(value + ".mq.type"));
					queuemgrConfig.setHostName(this.props.get(value + ".mq.hostname"));
					queuemgrConfig.setPort(this.props.getInteger(value + ".mq.port"));
					queuemgrConfig.setQueueManagerName(this.props.get(value + ".mq.queueManagerName"));
					queuemgrConfig.setCcsid(this.props.getInteger(value + ".mq.ccsid"));
					queuemgrConfig.setSslEnable(this.props.getBoolean(value + ".mq.ssl.enable"));
					queuemgrConfig.setSslCipherSuite(this.props.get(value + ".mq.ssl.cipherSuite"));
					queuemgrConfig.setSslPeerName(this.props.get(value + ".mq.ssl.peerName"));
					queuemgrConfig.setSslTrustStoreFilename(this.props.get(value + ".mq.ssl.trustStore"));
					queuemgrConfig.setSslTrustStorePassword(this.props.get(value + ".mq.ssl.trustStorePassword"));
					queuemgrConfig.setSslKeyStoreFilename(this.props.get(value + ".mq.ssl.keyStore"));
					queuemgrConfig.setSslKeyStorePassword(this.props.get(value + ".mq.ssl.keyStorePassword"));
					queuemgrConfig.setSubscribeInward(this.props.getBoolean(value + ".mq.in.subscribe"));
					queuemgrConfig.setClientChannel(this.props.get(value + ".mq.clientChannel"));
					queuemgrConfig.setUser(this.props.get(value + ".mq.user"));
					queuemgrConfig.setPassword(this.props.get(value + ".mq.password"));

					if (_logger.isInfoEnabled())
					{
						_logger.info("Queue Manager configuration loaded. Reference: [{}] QM: [{}]", refName, queuemgrConfig.getQueueManagerName());
					}

					mqConfig.setQmCfg(queuemgrConfig);

					String sendqueueRegex = ("(" + value + ")").replace(".", "\\.") + "(\\.send\\.)(req|ack)(\\.)([0-9a-zA-Z]+)";

					Map<String, FFPQueueConfig> sendQueueNameList = new HashMap<String, FFPQueueConfig>();
					for (String queuekey : this.props.keySet())
					{
						if (queuekey.matches(sendqueueRegex))
						{
							FFPQueueConfig queueConfig = new FFPQueueConfig();
							queueConfig.setQueueName(this.props.get(queuekey));
							sendQueueNameList.put(queuekey, queueConfig);
						}
					}
					mqConfig.setSendQueueNameMap(sendQueueNameList);

					String receivequeueRegex = ("(" + value + ")").replace(".", "\\.") + "(\\.receive\\.)(req|ack)(\\.)([0-9a-zA-Z]+)";

					Map<String, FFPQueueConfig> receiveQueueNameList = new HashMap<String, FFPQueueConfig>();
					for (String queuekey : this.props.keySet())
					{
						if (queuekey.matches(receivequeueRegex))
						{
							FFPQueueConfig queueConfig = new FFPQueueConfig();
							String[] arrry = this.props.get(queuekey).split(",");
							queueConfig.setQueueName(arrry[0].trim());
							queueConfig.setListener(arrry[1].trim());
							queueConfig.setThreadPoolSize(Integer.valueOf(arrry[2].trim()));
							receiveQueueNameList.put(queuekey, queueConfig);
						}
					}
					mqConfig.setReceiveQueueNameMap(receiveQueueNameList);
					mqConfig.setSessionCacheSize(this.props.getInteger(value + ".mq.sessionCacheSize"));

					FFPMqConfigSvc.qmConfigMap.put(refName, mqConfig);
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

	public String getPropertiesFile()
	{
		return this.propertiesFile;
	}

	public void setPropertiesFile(String propertiesFile)
	{
		this.propertiesFile = propertiesFile;
	}

	public Map<String, FFPMqConfig> getQmConfigMap()
	{
		return FFPMqConfigSvc.qmConfigMap;
	}
}
