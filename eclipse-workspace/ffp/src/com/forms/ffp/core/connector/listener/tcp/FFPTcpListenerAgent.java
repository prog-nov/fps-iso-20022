package com.forms.ffp.core.connector.listener.tcp;

import javax.annotation.Resource;

import com.forms.ffp.core.config.tcp.FFPTcpConfig;
import com.forms.ffp.core.connector.listener.FFPTcpListenerAgentInterface;
import com.forms.ffp.persistents.service.ss.FFPIDaoService_System;

public class FFPTcpListenerAgent implements FFPTcpListenerAgentInterface
{
	private FFPTcpListenerThread listener = null;
	
	private FFPTcpConfig config;
	
	@Resource(name = "FFPDaoService_System")
	private FFPIDaoService_System daoService;
	
	public FFPTcpListenerAgent(FFPTcpConfig config)
	{
		this.config = config;
	}

	public void init() throws Exception
	{
		listener = new FFPTcpListenerThread(config);
//		startListeners();
	}

	public void onDestory() throws Exception
	{
		// Exit If Real-time Port Close
		listener.onDestory();
	}

	@Override
	public void startListeners() throws Exception
	{
		Thread thread = new Thread(listener);
		thread.start();
	}

	@Override
	public boolean getStartWithException()
	{
		return listener.startWithException;
	}
}
