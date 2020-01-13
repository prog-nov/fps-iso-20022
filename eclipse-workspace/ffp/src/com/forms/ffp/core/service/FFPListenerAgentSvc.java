package com.forms.ffp.core.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.forms.ffp.core.connector.listener.mq.thread.FFPRealTimeListenerControllerThread;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.persistents.bean.ss.FFPSsSystem;
import com.forms.ffp.persistents.dao.ss.FFPIDao_System;

@Component("mqListenerAgentSvc")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FFPListenerAgentSvc extends Thread
{
	@Autowired
	private FFPRealTimeListenerControllerThread listenerAgentThread;

	@Resource(name = "FFPIDao_System")
	private FFPIDao_System daoSystem;
	
	@PostConstruct
	private void init() throws Exception
	{
		listenerAgentThread.init();
	}
	
//	@PostConstruct
	public void startSevices() throws Exception
	{
		FFPSsSystem system = daoSystem.inquiry();
		if(FFPConstants.LISTENER_STATUS_NOFUNCTION.equals(system.getRealtimeListenerStat()))
			return;
		
		init();
		startController();
	}
	
	@PreDestroy
	public void stopSevices() throws Exception
	{
		FFPSsSystem system = daoSystem.inquiry();
		if(FFPConstants.LISTENER_STATUS_NOFUNCTION.equals(system.getRealtimeListenerStat()))
			return;
		
		stopController();
	}
	
	public void startController() throws Exception
	{
		listenerAgentThread.setControllerStop(false);
		Thread thread = new Thread(listenerAgentThread);
		thread.start();
	}
	
	public void stopController() throws Exception
	{
		listenerAgentThread.setControllerStop(true);
	}
	
	public void startListener() throws Exception
	{
		listenerAgentThread.startListener();
	}
	
	public void stopListener() throws Exception
	{
		listenerAgentThread.stopListener();
	}
}
