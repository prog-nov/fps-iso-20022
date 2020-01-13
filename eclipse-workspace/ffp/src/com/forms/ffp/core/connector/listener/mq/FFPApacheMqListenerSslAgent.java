package com.forms.ffp.core.connector.listener.mq;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQSslConnectionFactory;
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

public class FFPApacheMqListenerSslAgent implements FFPMqListenerAgentInterface
{
	private static Logger logger = LoggerFactory.getLogger(FFPApacheMqListenerSslAgent.class);

	protected FFPMqConfig config;

	private ConnectionFactory connFac;

	private List<DefaultMessageListenerContainer> listenerContainer;

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private FFPMqMessageConverter msgConverter;
	
	private boolean startWithException = false;

	public FFPApacheMqListenerSslAgent()
	{
	}

	public FFPApacheMqListenerSslAgent(FFPMqConfig config)
	{
		this.config = config;
	}

	public void init()
	{
		logger.info("[{}] MQ SSL Agent Initialization started.");
		try
		{
			FFPQueueManagerConfig qmCfg = this.config.getQmCfg();

			ActiveMQSslConnectionFactory factory = new ActiveMQSslConnectionFactory();
			factory.setBrokerURL("ssl://" + qmCfg.getHostName() + ":" + qmCfg.getPort());
			factory.setKeyAndTrustManagers(FFPSecurityUtils.loadKeyManager(qmCfg.getSslKeyStoreFilename(), qmCfg.getSslKeyStorePassword(), "JKS"),
					FFPSecurityUtils.loadTrustManager(qmCfg.getSslTrustStoreFilename(), qmCfg.getSslTrustStorePassword(), "JKS"), new java.security.SecureRandom());

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
					logger.info("[{}] MQ SSL Listener destory. Destination: [{}]", this.config.getConnectorName(), container.getDestinationName());
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
				if (FFPApacheMqListenerSslAgent.logger.isErrorEnabled())
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
					FFPApacheMqListenerSslAgent.logger.error(
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
					if (FFPApacheMqListenerSslAgent.logger.isErrorEnabled())
					{
						FFPApacheMqListenerSslAgent.logger.error("Unexpected Error.", e);
					}
				}

			}
		};

		container.setMessageListener(msgListener);

		logger.info("[{}] MQ SSL Listener initialized. Connection Factory: [{}] Destination: [{}] Concurrency: [{}] Selector: [{}] Listener: [{}]",
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
				for (DefaultMessageListenerContainer container : FFPApacheMqListenerSslAgent.this.listenerContainer)
				{
					if (FFPApacheMqListenerSslAgent.logger.isInfoEnabled())
					{
						FFPApacheMqListenerSslAgent.logger.info("[{}] Starting listener... Destination: [{}]",
								new Object[] { FFPApacheMqListenerSslAgent.this.config.getConnectorName(), container.getDestinationName() });
					}
					container.initialize();
					container.start();
				}
				if (FFPApacheMqListenerSslAgent.logger.isInfoEnabled())
				{
					FFPApacheMqListenerSslAgent.logger.info("[{}] All Listeners started.", FFPApacheMqListenerSslAgent.this.config.getConnectorName());
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
