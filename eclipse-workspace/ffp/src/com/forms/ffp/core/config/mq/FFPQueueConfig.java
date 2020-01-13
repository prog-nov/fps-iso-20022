package com.forms.ffp.core.config.mq;

public class FFPQueueConfig
{
	private String queueName;
	private String listener;
	private Integer threadPoolSize;
	private String priority;

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

	public String getPriority()
	{
		return priority;
	}

	public void setPriority(String priority)
	{
		this.priority = priority;
	}

	
}