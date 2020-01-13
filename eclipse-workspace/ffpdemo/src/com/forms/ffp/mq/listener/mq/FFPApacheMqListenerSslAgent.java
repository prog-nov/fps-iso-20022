package com.forms.ffp.mq.listener.mq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.ConnectionFactory;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.forms.ffp.mq.config.FFPMqConfig;
import com.forms.ffp.mq.config.FFPQueueConfig;
import com.forms.ffp.mq.config.FFPQueueManagerConfig;
import com.forms.ffp.mq.listener.FFPMessageConverter;
import com.forms.ffp.mq.listener.msg.FFPBaseMsgListener;
import com.forms.ffp.utils.SSLUtils;

public class FFPApacheMqListenerSslAgent implements FFPMqListenerAgentInterface {

	private static Logger logger = LoggerFactory.getLogger(FFPApacheMqListenerSslAgent.class);

	protected FFPMqConfig config;

	protected JmsTemplate jmsTemplate;

	private List<ConnectionFactory> connectionFactories;

	private List<DefaultMessageListenerContainer> listenerContainer;

	// @Autowired
	// private FFPRuntimeConfigSvc runtimeConfigSvc;
	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private FFPMessageConverter msgConverter;

	public FFPApacheMqListenerSslAgent() {
	}

	public FFPApacheMqListenerSslAgent(FFPMqConfig config) {
		this.config = config;
	}

	@PostConstruct
	public void init() {
		initJmsTemplate();
		startListeners();
	};

	private void initJmsTemplate() {
		
		//ssl 配置文件
		String keyStore = "D:/ssl/client1.ks";
		String trustStore = "D:/ssl/client1.ts";
		String keyStorePassword = "111111";
		String url = "ssl://127.0.0.1:61617";

		if (logger.isInfoEnabled()) {
			logger.info("[{}] MQ SSL Agent Initialization started.");
		}
		try {
			this.jmsTemplate = new JmsTemplate();
			this.connectionFactories = new ArrayList<ConnectionFactory>();
			List<ConnectionFactory> subscribeInward = new ArrayList<ConnectionFactory>();

			FFPQueueManagerConfig qmCfg = this.config.getQmCfg();

			// 创建SSL连接器工厂类
	        ActiveMQSslConnectionFactory sslConnectionFactory = new ActiveMQSslConnectionFactory();
	        // 设置参数，并加载SSL密钥和证书信息
	        sslConnectionFactory.setBrokerURL(url);
	        sslConnectionFactory.setKeyAndTrustManagers(SSLUtils.loadKeyManager(keyStore, keyStorePassword), SSLUtils.loadTrustManager(trustStore),
	                new java.security.SecureRandom());

			UserCredentialsConnectionFactoryAdapter adapter = new UserCredentialsConnectionFactoryAdapter();
			adapter.setTargetConnectionFactory(sslConnectionFactory);
			adapter.setUsername(qmCfg.getUser());
			adapter.setPassword(qmCfg.getPassword());

			CachingConnectionFactory ccf = new CachingConnectionFactory();
			ccf.setTargetConnectionFactory(adapter);
			ccf.setSessionCacheSize(this.config.getSessionCacheSize().intValue());

			if ((qmCfg.getSubscribeInward() == null) || (Boolean.TRUE.equals(qmCfg.getSubscribeInward()))) {
				subscribeInward.add(ccf);
			}

			if (logger.isInfoEnabled()) {
				logger.info(
						"[{}] Connection Factory initialized [{}] Host: [{}:{}] QM: [{}], Client Channel: [{}], CCSID: [{}], SSL Enabled: [{}]",
						new Object[] { this.config.getMqName(), ccf.toString(), qmCfg.getHostName(), qmCfg.getPort(),
								qmCfg.getQueueManagerName(), qmCfg.getClientChannel(), qmCfg.getCcsid(),
								qmCfg.getSslEnable() });
			}

			this.connectionFactories.add(ccf);

			this.jmsTemplate.setConnectionFactory((ConnectionFactory) this.connectionFactories.get(0));
			this.jmsTemplate.setSessionTransacted(true);
			this.jmsTemplate.setPubSubDomain(false);
			this.jmsTemplate.setDefaultDestinationName("queue:///" + this.config.getDefaultDestinationQueueName());

			initListeners(subscribeInward);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	public void onDestory() {
		destoryListeners();
	}

	private void destoryListeners() {
		if (this.listenerContainer != null) {
			for (DefaultMessageListenerContainer container : this.listenerContainer) {
				if (logger.isInfoEnabled()) {
					logger.info("[{}] MQ SSL Listener destory. Destination: [{}]", this.config.getMqName(),
							container.getDestinationName());
				}

				container.destroy();
			}

			this.listenerContainer.clear();
		}
	}

	private void initListeners(List<ConnectionFactory> subscribeInwardConnectionFactories) throws Exception {
		if (this.listenerContainer == null) {
			this.listenerContainer = new ArrayList<DefaultMessageListenerContainer>();
		} else {
			destoryListeners();
		}

		for (ConnectionFactory factory : subscribeInwardConnectionFactories) {
			if (logger.isInfoEnabled()) {
				logger.info("[{}] Initializing MQ Listeners (Connection Factory Index = {})", this.config.getMqName(),
						Integer.valueOf(this.connectionFactories.indexOf(factory)));
			}

			Iterator<String> iter = this.config.getReceiveQueueNameMap().keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				FFPQueueConfig queueConfig = this.config.getReceiveQueueNameMap().get(key);
				Class clazz = Class.forName(queueConfig.getListener());
				FFPBaseMsgListener listener = (FFPBaseMsgListener) this.beanFactory.getBean(clazz, new Object[] {});
				this.listenerContainer.add(initListener(factory, queueConfig.getQueueName(),
						queueConfig.getThreadPoolSize(), this.config.getInwardSelector(), listener));
			}
		}
	}

	private DefaultMessageListenerContainer initListener(final ConnectionFactory connectionFactory,
			final String destinationName, final int concurrentConsumers, final String selector,
			final FFPBaseMsgListener listener) throws Exception {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setMessageConverter(this.msgConverter);
		container.setRecoveryInterval(6000L);
		container.setDestinationName(destinationName);
		container.setMessageSelector(selector);
		container.setConcurrentConsumers(1);
		container.setMaxConcurrentConsumers(concurrentConsumers);
		container.setSessionAcknowledgeMode(0);
		container.setExceptionListener(new ExceptionListener() {
			public void onException(JMSException ex) {
				if (FFPApacheMqListenerSslAgent.logger.isErrorEnabled()) {
					StringBuilder sb = new StringBuilder();
					Throwable e = ex;
					int idx = 0;
					while (e != null) {
						idx++;
						if (sb.length() != 0) {
							sb.append(" --> ");
						}
						sb.append(String.format("[%s][%s][%s]",
								new Object[] { Integer.valueOf(idx), e.getClass().getSimpleName(), e.getMessage() }));
						e = e.getCause();
					}
					FFPApacheMqListenerSslAgent.logger.error(
							"[{}] Exception catched for JMS Message Container. Connection Factory: [{}] Destination: [{}] Concurrency: [{}] Selector: [{}] Listener: [{}] Cause: {}",
							new Object[] { FFPApacheMqListenerSslAgent.this.config.getMqName(),
									connectionFactory.toString(), destinationName, Integer.valueOf(concurrentConsumers),
									selector, listener.getClass().getSimpleName(), sb.toString(), ex });
				}
			}
		});

		MessageListener msgListener = new MessageListener() {
			@Override
			public void onMessage(Message msg) {
				try {
					listener.onMessage(msg, FFPApacheMqListenerSslAgent.this.config.getMqName());
				} catch (Exception e) {
					if (FFPApacheMqListenerSslAgent.logger.isErrorEnabled()) {
						FFPApacheMqListenerSslAgent.logger.error("Unexpected Error.", e);
					}
				}

			}
		};

		container.setMessageListener(msgListener);
		container.initialize();

		if (logger.isInfoEnabled()) {
			logger.info(
					"[{}] MQ SSL Listener initialized. Connection Factory: [{}] Destination: [{}] Concurrency: [{}] Selector: [{}] Listener: [{}]",
					new Object[] { this.config.getMqName(), connectionFactory.toString(), destinationName,
							Integer.valueOf(concurrentConsumers), selector, listener.getClass().getSimpleName() });
		}

		return container;
	}

	public void startListeners() {
		if (logger.isInfoEnabled()) {
			logger.info("[{}] Starting Listeners in new Thread...", this.config.getMqName());
		}

		Runnable run = new Runnable() {
			@Override
			public void run() {
				for (DefaultMessageListenerContainer container : FFPApacheMqListenerSslAgent.this.listenerContainer) {
					if (FFPApacheMqListenerSslAgent.logger.isInfoEnabled()) {
						FFPApacheMqListenerSslAgent.logger.info(
								"[{}] Starting listener... (Connection Factory Index = {}) Destination: [{}]",
								new Object[] { FFPApacheMqListenerSslAgent.this.config.getMqName(),
										Integer.valueOf(FFPApacheMqListenerSslAgent.this.connectionFactories
												.indexOf(container.getConnectionFactory())),
										container.getDestinationName() });
					}
					container.start();
				}
				if (FFPApacheMqListenerSslAgent.logger.isInfoEnabled()) {
					FFPApacheMqListenerSslAgent.logger.info("[{}] All Listeners started.",
							FFPApacheMqListenerSslAgent.this.config.getMqName());
				}
			}
		};

		new Thread(run).start();
	}

	public FFPMqConfig getConfig() {
		return this.config;
	}

	public void setConfig(FFPMqConfig config) {
		this.config = config;
	}

	public JmsTemplate getJmsTemplate() {
		return this.jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
