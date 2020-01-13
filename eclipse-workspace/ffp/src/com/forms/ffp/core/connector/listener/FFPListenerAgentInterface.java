package com.forms.ffp.core.connector.listener;

import org.springframework.stereotype.Component;
@Component("mqListenerAgentInterface")
public interface FFPListenerAgentInterface
{
	public boolean getStartWithException();
	
	public void init() throws Exception;
	
	public void startListeners() throws Exception;
	
	public void onDestory() throws Exception;
}
