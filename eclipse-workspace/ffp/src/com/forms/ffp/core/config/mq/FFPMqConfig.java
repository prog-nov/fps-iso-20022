package com.forms.ffp.core.config.mq;

import java.util.Map;

import com.forms.ffp.core.config.FFPConnectorConfig;

public class FFPMqConfig implements FFPConnectorConfig
{
	private String connectorName;
	private String connectorType;
	private FFPQueueManagerConfig qmCfg;
	private Map<String, FFPQueueConfig> receiveQueueNameMap;

	private FFPQueueConfig outwardRequestHighPriorityQueueName;
	private FFPQueueConfig outwardRequestMediumPriorityQueueName;
	private FFPQueueConfig outwardAcknowledgeHighPriorityQueueName;
	private FFPQueueConfig outwardAcknowledgeMediumPriorityQueueName;

	private String defaultDestinationQueueName;

	private String inwardSelector;
	private Integer sessionCacheSize;

	public String getConnectorName()
	{
		return this.connectorName;
	}

	public void setConnectorName(String connectorName)
	{
		this.connectorName = connectorName;
	}

	public String getConnectorType()
	{
		return this.connectorType;
	}

	public void setConnectorType(String connectorType)
	{
		this.connectorType = connectorType;
	}

	public FFPQueueManagerConfig getQmCfg()
	{
		return this.qmCfg;
	}

	public void setQmCfg(FFPQueueManagerConfig qmCfg)
	{
		this.qmCfg = qmCfg;
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

	public FFPQueueConfig getOutwardRequestHighPriorityQueueName()
	{
		return outwardRequestHighPriorityQueueName;
	}

	public void setOutwardRequestHighPriorityQueueName(FFPQueueConfig outwardRequestHighPriorityQueueName)
	{
		this.outwardRequestHighPriorityQueueName = outwardRequestHighPriorityQueueName;
	}

	public FFPQueueConfig getOutwardRequestMediumPriorityQueueName()
	{
		return outwardRequestMediumPriorityQueueName;
	}

	public void setOutwardRequestMediumPriorityQueueName(FFPQueueConfig outwardRequestMediumPriorityQueueName)
	{
		this.outwardRequestMediumPriorityQueueName = outwardRequestMediumPriorityQueueName;
	}

	public FFPQueueConfig getOutwardAcknowledgeHighPriorityQueueName()
	{
		return outwardAcknowledgeHighPriorityQueueName;
	}

	public void setOutwardAcknowledgeHighPriorityQueueName(FFPQueueConfig outwardAcknowledgeHighPriorityQueueName)
	{
		this.outwardAcknowledgeHighPriorityQueueName = outwardAcknowledgeHighPriorityQueueName;
	}

	public FFPQueueConfig getOutwardAcknowledgeMediumPriorityQueueName()
	{
		return outwardAcknowledgeMediumPriorityQueueName;
	}

	public void setOutwardAcknowledgeMediumPriorityQueueName(FFPQueueConfig outwardAcknowledgeMediumPriorityQueueName)
	{
		this.outwardAcknowledgeMediumPriorityQueueName = outwardAcknowledgeMediumPriorityQueueName;
	}

}
