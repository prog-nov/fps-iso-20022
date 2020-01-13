package com.forms.ffp.core.msg.iclfps.creator;

import java.util.Map;
import javax.jms.Destination;

public class FFPMsgProperty
{
	private String _correlationId;
	private Map<String, Object> _propertyMap;
	private Destination _replyToDest;
	private long _timeToLive = 0L;
	private int _priority = 4;

	public String getCorrelationId()
	{
		return this._correlationId;
	}

	public void setCorrelationId(String correlationId)
	{
		this._correlationId = correlationId;
	}

	public Map<String, Object> getPropertyMap()
	{
		return this._propertyMap;
	}

	public void setPropertyMap(Map<String, Object> propertyMap)
	{
		this._propertyMap = propertyMap;
	}

	public Destination getReplyToDest()
	{
		return this._replyToDest;
	}

	public void setReplyToDest(Destination replyToDest)
	{
		this._replyToDest = replyToDest;
	}

	public long getTimeToLive()
	{
		return this._timeToLive;
	}

	public void setTimeToLive(long timeToLive)
	{
		this._timeToLive = timeToLive;
	}

	public int getPriority()
	{
		return this._priority;
	}

	public void setPriority(int priority)
	{
		this._priority = priority;
	}
}