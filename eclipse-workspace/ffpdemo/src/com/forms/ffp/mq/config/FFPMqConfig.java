package com.forms.ffp.mq.config;

import java.util.Map;

public class FFPMqConfig
{
	private String mqName;
	private FFPQueueManagerConfig qmCfg;
	private Map<String, FFPQueueConfig> sendQueueNameMap;
	private Map<String, FFPQueueConfig> receiveQueueNameMap;
	private String defaultDestinationQueueName;

	private String inwardSelector;
	private Integer sessionCacheSize;

	public String getMqName()
	{
		return this.mqName;
	}

	public void setMqName(String mqName)
	{
		this.mqName = mqName;
	}

	public FFPQueueManagerConfig getQmCfg()
	{
		return this.qmCfg;
	}

	public void setQmCfg(FFPQueueManagerConfig qmCfg)
	{
		this.qmCfg = qmCfg;
	}

	public Map<String, FFPQueueConfig> getSendQueueNameMap()
	{
		return sendQueueNameMap;
	}

	public void setSendQueueNameMap(Map<String, FFPQueueConfig> sendQueueNameList)
	{
		this.sendQueueNameMap = sendQueueNameList;
	}

	public Map<String, FFPQueueConfig> getReceiveQueueNameMap()
	{
		return receiveQueueNameMap;
	}

	public void setReceiveQueueNameMap(Map<String, FFPQueueConfig> receiveQueueNameList)
	{
		this.receiveQueueNameMap = receiveQueueNameList;
	}
	
	public String getInwardSelector()
	{
		return this.inwardSelector;
	}

	public void setInwardSelector(String inwardSelector)
	{
		this.inwardSelector = inwardSelector;
	}
	
	public Integer getSessionCacheSize()
	{
		return this.sessionCacheSize;
	}

	public void setSessionCacheSize(Integer sessionCacheSize)
	{
		this.sessionCacheSize = sessionCacheSize;
	}
	
	public String getDefaultDestinationQueueName()
	{
		return this.defaultDestinationQueueName;
	}

	public void setDefaultDestinationQueueName(String defaultDestinationQueueName)
	{
		this.defaultDestinationQueueName = defaultDestinationQueueName;
	}
}
