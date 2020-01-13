package com.forms.ffp.mq.listener.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.forms.ffp.mq.config.FFPMqConfig;
@Component("mqListenerAgentInterface")
public interface FFPMqListenerAgentInterface
{
	// @Autowired
	// private BankSimMessageConverter bankSimMessageConverter;

	public void init();
	
	public void startListeners();
	
	public void onDestory();
	
	public FFPMqConfig getConfig();

	public void setConfig(FFPMqConfig config);

	public JmsTemplate getJmsTemplate();

	public void setJmsTemplate(JmsTemplate jmsTemplate);
}
