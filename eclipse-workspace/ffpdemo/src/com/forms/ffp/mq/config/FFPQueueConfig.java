package com.forms.ffp.mq.config;

public class FFPQueueConfig
{
	private String queueName;
	private String listener;
	private Integer threadPoolSize;

	public String getQueueName()
	{
		return queueName;
	}

	public void setQueueName(String queueName)
	{
		this.queueName = queueName;
	}

	public String getListener()
	{
		return listener;
	}

	public void setListener(String listener)
	{
		this.listener = listener;
	}

	public Integer getThreadPoolSize()
	{
		return threadPoolSize;
	}

	public void setThreadPoolSize(Integer threadPoolSize)
	{
		this.threadPoolSize = threadPoolSize;
	}

	
}