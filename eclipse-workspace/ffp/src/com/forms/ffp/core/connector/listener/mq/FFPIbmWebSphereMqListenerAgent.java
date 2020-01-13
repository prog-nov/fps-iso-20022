package com.forms.ffp.core.connector.listener.mq;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.forms.ffp.core.config.mq.FFPMqConfig;
import com.forms.ffp.core.config.mq.FFPQueueConfig;
import com.forms.ffp.core.config.mq.FFPQueueManagerConfig;
import com.forms.ffp.core.connector.listener.FFPMqListenerAgentInterface;
import com.forms.ffp.core.connector.listener.msg.FFPBaseMsgListener;
import com.forms.ffp.core.utils.FFPSecurityUtils;
import com.ibm.mq.jms.MQXAQueueConnectionFactory;

public class FFPIbmWebSphereMqListenerAgent implements FFPMqListenerAgentInterface
{
	private static Logger logger = LoggerFactory.getLogger(FFPIbmWebSphereMqListenerAgent.class);

	protected FFPMqConfig config;

	private ConnectionFactory connFac;

	private List<DefaultMessageListenerContainer> listenerContainer;

	@Autowired
	private BeanFactory beanFactory;

	@Resource(name = "messageConverter")
	private FFPMqMessageConverter msgConverter;
	
	private boolean startWithException = false;

	public FFPIbmWebSphereMqListenerAgent()
	{
	}

	public FFPIbmWebSphereMqListenerAgent(FFPMqConfig config)
	{
		this.config = config;
	}

	public void init()
	{
		logger.info("[{}] MQ Agent Initialization started.");
		try
		{
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
					SSLContext sslContext = FFPSecurityUtils.createSSLContext(qmCfg.getSslTrustStoreFilename(), qmCfg.getSslTrustStorePassword(), "JKS",
							qmCfg.getSslKeyStoreFilename(), qmCfg.getSslKeyStorePassword(), "JKS");

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
			connFac = ccf;

			logger.info("[{}] Connection Factory initialized [{}] Host: [{}:{}] QM: [{}], Client Channel: [{}], CCSID: [{}], SSL Enabled: [{}]", new Object[] { this.config.getConnectorName(),
					ccf.toString(), qmCfg.getHostName(), qmCfg.getPort(), qmCfg.getQueueManagerName(), qmCfg.getClientChannel(), qmCfg.getCcsid(), qmCfg.getSslEnable() });
		
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	};

	public void onDestory()
	{
		destoryListeners();
	}

	private void destoryListeners()
	{
		if (this.listenerContainer != null)
		{
			for (DefaultMessageListenerContainer container : this.listenerContainer)
			{
				container.destroy();
				if (logger.isInfoEnabled())
				{
					logger.info("[{}] MQ Listener destory. Destination: [{}]", this.config.getConnectorName(), container.getDestinationName());
				}
			}

			this.listenerContainer.clear();
		}
	}

	private void initListeners(ConnectionFactory subscribeInwardConnectionFactory)
	{
		if (this.listenerContainer == null)
		{
			this.listenerContainer = new ArrayList<DefaultMessageListenerContainer>();
		} else
		{
			destoryListeners();
		}

		if (logger.isInfoEnabled())
		{
			logger.info("[{}] Initializing MQ Listeners...", this.config.getConnectorName());
		}

		Iterator<String> iter = this.config.getReceiveQueueNameMap().keySet().iterator();
		while (iter.hasNext())
		{
			String key = iter.next();
			FFPQueueConfig queueConfig = this.config.getReceiveQueueNameMap().get(key);
			Class<?> clazz = null;
			try
			{
				clazz = Class.forName(queueConfig.getListener());
			} catch (ClassNotFoundException e)
			{
				logger.error(e.getMessage());
			}
			FFPBaseMsgListener listener = (FFPBaseMsgListener) this.beanFactory.getBean(clazz, new Object[] {});
			this.listenerContainer.add(initListener(subscribeInwardConnectionFactory, queueConfig, this.config, listener));
		}
	}

	private DefaultMessageListenerContainer initListener(final ConnectionFactory connectionFactory, final FFPQueueConfig queueConfig, final FFPMqConfig mqConfig,
			final FFPBaseMsgListener listener)
	{
		final DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageConverter(this.msgConverter);
		container.setRecoveryInterval(6000L);
		container.setDestinationName(queueConfig.getQueueName());
		container.setMessageSelector(mqConfig.getInwardSelector());
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(queueConfig.getThreadPoolSize());
		container.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
//		container.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
		container.setExceptionListener(new ExceptionListener()
		{
			public void onException(JMSException ex)
			{
				if (FFPIbmWebSphereMqListenerAgent.logger.isErrorEnabled())
				{
					StringBuilder sb = new StringBuilder();
					Throwable e = ex;
					int idx = 0;
					while (e != null)
					{
						idx++;
						if (sb.length() != 0)
						{
							sb.append(" --> ");
						}
						sb.append(String.format("[%s][%s][%s]", new Object[] { Integer.valueOf(idx), e.getClass().getSimpleName(), e.getMessage() }));
						e = e.getCause();
					}
					FFPIbmWebSphereMqListenerAgent.logger.error(
							"[{}] Exception catched for JMS Message Container. Connection Factory: [{}] Destination: [{}] Concurrency: [{}] Selector: [{}] Listener: [{}] Cause: {}",
							new Object[] { mqConfig.getConnectorName(), connectionFactory.toString(), queueConfig.getQueueName(), queueConfig.getThreadPoolSize(), mqConfig.getInwardSelector(),
									listener.getClass().getSimpleName(), sb.toString(), ex });
				}
				
				if(ex.getCause() instanceof ConnectException)
				{
					startWithException = true;
					container.destroy();
				}
			}
		});

		MessageListener msgListener = new MessageListener()
		{
			@Override
			public void onMessage(Message msg)
			{
				try
				{
					listener.onMessage(msg, mqConfig.getConnectorName(), queueConfig.getPriority());
				} catch (Exception e)
				{
					if (FFPIbmWebSphereMqListenerAgent.logger.isErrorEnabled())
					{
						FFPIbmWebSphereMqListenerAgent.logger.error("Unexpected Error.", e);
					}
				}

			}
		};

		container.setMessageListener(msgListener);

		logger.info("[{}] MQ Listener initialized. Connection Factory: [{}] Destination: [{}] Concurrency: [{}] Selector: [{}] Listener: [{}]",
				new Object[] { this.config.getConnectorName(), connectionFactory.toString(), queueConfig.getQueueName(), queueConfig.getThreadPoolSize(), mqConfig.getInwardSelector(), listener.getClass().getSimpleName() });

		return container;
	}

	public void startListeners() throws Exception
	{
		if ((this.config.getQmCfg().getSubscribeInward() == null) || (Boolean.TRUE.equals(this.config.getQmCfg().getSubscribeInward())))
		{
			initListeners(connFac);
		}
		
		if (logger.isInfoEnabled())
		{
			logger.info("[{}] Starting Listeners in new Thread...", this.config.getConnectorName());
		}
		startWithException = false;
		
		Runnable run = new Runnable()
		{
			@Override
			public void run()
			{
				for (DefaultMessageListenerContainer container : FFPIbmWebSphereMqListenerAgent.this.listenerContainer)
				{
					if (FFPIbmWebSphereMqListenerAgent.logger.isInfoEnabled())
					{
						FFPIbmWebSphereMqListenerAgent.logger.info("[{}] Starting listener... (Connection Factory Index = {}) Destination: [{}]",
								new Object[] { FFPIbmWebSphereMqListenerAgent.this.config.getConnectorName(), container.getDestinationName() });
					}
					container.initialize();
					container.start();
				}
				if (FFPIbmWebSphereMqListenerAgent.logger.isInfoEnabled())
				{
					FFPIbmWebSphereMqListenerAgent.logger.info("[{}] All Listeners started.", FFPIbmWebSphereMqListenerAgent.this.config.getConnectorName());
				}
			}
		};
		new Thread(run).start();
	}

	public FFPMqConfig getConfig()
	{
		return this.config;
	}

	public void setConfig(FFPMqConfig config)
	{
		this.config = config;
	}

	@Override
	public boolean getStartWithException()
	{
		return this.startWithException;
	}
}
