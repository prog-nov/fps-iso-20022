package com.forms.ffp.core.connector.listener.tcp;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.config.tcp.FFPTcpConfig;


public class FFPTcpListenerThread implements Runnable
{
	private Logger logger = LoggerFactory.getLogger(FFPTcpListenerThread.class);
	
	private ServerSocket serverSocket = null;
	
	private FFPTcpConfig config;
	
	private ThreadGroup threadGroup;
	
	private boolean normalStop = false;
	
	public boolean startWithException = false;

	public FFPTcpListenerThread(FFPTcpConfig config)
	{
		this.config = config;
	}

	public void onDestory() throws Exception
	{
		logger.info("***监听服务器"+ config.getConnectorName() +"停止接收新消息");
		if(serverSocket != null)
		{
			normalStop = true;
			serverSocket.close();
		}
		logger.info("***监听服务器"+ config.getConnectorName() +"正在检查处理中的消息");
		while(threadGroup.activeCount() > 0)
		{
			logger.info("***监听服务器"+ config.getConnectorName() +"还有" + threadGroup.activeCount() + "个消息正在处理中");
			Thread[] list = new Thread[threadGroup.activeCount()];
	        int count = threadGroup.enumerate(list);
	        for (int i = 0; i < count; i++)
	        {
	            System.out.println("Thread " + list[i].getName() + " found");
	         }
			Thread.sleep(3 * 1000);
		}
		logger.info("***监听服务器"+ config.getConnectorName() +"已关闭***");
	}

	@Override
	public void run()
	{
		threadGroup = new ThreadGroup("FFP AGENT LISTENER");
		startWithException = false;
		normalStop = false;
		try
		{
			serverSocket = new ServerSocket(config.getReceiveport(), config.getReceiveSessionCount(), InetAddress.getByName(config.getReceivehost()));
			logger.info("***监听服务器"+ config.getConnectorName() +"即将启动，等待客户端的连接***");
			while(true)
			{
				Socket socket = serverSocket.accept();
				if(socket != null)
				{
					Thread thread = new Thread(threadGroup, new FFPTcpSocketThread(socket));
					thread.setName("FFP AGENT LISTENER" + thread.getName());
					logger.info("***监听服务器"+ config.getConnectorName() + "Number of Threads:" + threadGroup.activeCount() + ",Processed threadname:" + thread.getName());
					thread.start();
				}
			}
		}
		catch(Exception e)
		{
			if(!normalStop)
			{
				startWithException = true;
				logger.error("FFP AGENT LISTENER", e);
			}
		}
	}
}
