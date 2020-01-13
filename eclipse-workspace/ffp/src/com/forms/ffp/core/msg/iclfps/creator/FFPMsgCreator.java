package com.forms.ffp.core.msg.iclfps.creator;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;

public class FFPMsgCreator implements MessageCreator
{
	protected MessageConverter _msgConverter;
	protected Object _msgObj;
	protected Message _msg;
	protected String _correlationId;
	protected Destination _replyTo;
	protected Map<String, Object> _propertyMap;
	protected Long _expiration = null;
	protected Integer _priority = null;

	public Message createMessage(Session session) throws JMSException
	{
		if (this._msg == null)
		{
			this._msg = this._msgConverter.toMessage(this._msgObj, session);
		}

		if (this._correlationId != null)
		{
			this._msg.setJMSCorrelationID(this._correlationId);
		}

		if (this._replyTo != null)
		{
			this._msg.setJMSReplyTo(this._replyTo);
		}

		if (this._expiration != null)
		{
			this._msg.setJMSExpiration(this._expiration.longValue());
		}

		if (this._priority != null)
		{
			this._msg.setJMSPriority(this._priority.intValue());
		}

		if (this._propertyMap != null)
		{
			Iterator<Entry<String, Object>> arg2 = this._propertyMap.entrySet().iterator();

			while (arg2.hasNext())
			{
				Entry<String, Object> entry = arg2.next();
				if (entry.getValue() instanceof String)
				{
					this._msg.setStringProperty((String) entry.getKey(), (String) entry.getValue());
				} else if (entry.getValue() instanceof Integer)
				{
					this._msg.setIntProperty((String) entry.getKey(), ((Integer) entry.getValue()).intValue());
				} else if (entry.getValue() instanceof Boolean)
				{
					this._msg.setBooleanProperty((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
				} else if (entry.getValue() instanceof Long)
				{
					this._msg.setLongProperty((String) entry.getKey(), ((Long) entry.getValue()).longValue());
				}
			}
		}

		return this._msg;
	}

	public void setMsgProperty(FFPMsgProperty msgProperty)
	{
		if (msgProperty.getCorrelationId() != null)
		{
			this.setCorrelationId(msgProperty.getCorrelationId());
		}

		if (msgProperty.getReplyToDest() != null)
		{
			this.setReplyTo(msgProperty.getReplyToDest());
		}

		if (msgProperty.getPropertyMap() != null)
		{
			this.setPropertyMap(msgProperty.getPropertyMap());
		}

		this.setExpiration(Long.valueOf(msgProperty.getTimeToLive()));
		this.setPriority(Integer.valueOf(msgProperty.getPriority()));
	}

	public void setMsg(Message msg)
	{
		this._msg = msg;
	}

	public Message getMsg()
	{
		return this._msg;
	}

	public Object getMsgObj()
	{
		return this._msgObj;
	}

	public void setMsgObj(Object msgObj)
	{
		this._msgObj = msgObj;
	}

	public MessageConverter getMsgConverter()
	{
		return this._msgConverter;
	}

	public void setMsgConverter(MessageConverter msgConverter)
	{
		this._msgConverter = msgConverter;
	}

	public String getCorrelationId()
	{
		return this._correlationId;
	}

	public void setCorrelationId(String correlationId)
	{
		this._correlationId = correlationId;
	}

	public Destination getReplyTo()
	{
		return this._replyTo;
	}

	public void setReplyTo(Destination replyTo)
	{
		this._replyTo = replyTo;
	}

	public Map<String, Object> getPropertyMap()
	{
		return this._propertyMap;
	}

	public void setPropertyMap(Map<String, Object> propertyMap)
	{
		this._propertyMap = propertyMap;
	}

	public String toString()
	{
		String ret = "";
		ret = "Correctionlation ID[" + this._correlationId + "]";
		if (this._replyTo != null)
		{
			ret = ret + "; Reply To [" + ToStringBuilder.reflectionToString(this._replyTo) + "]";
		}

		if (this._propertyMap != null)
		{
			ret = ret + "; Property Map [" + ToStringBuilder.reflectionToString(this._propertyMap) + "]";
		}

		return ret;
	}

	public Long getExpiration()
	{
		return this._expiration;
	}

	public void setExpiration(Long expiration)
	{
		this._expiration = expiration;
	}

	public Integer getPriority()
	{
		return this._priority;
	}

	public void setPriority(Integer priority)
	{
		this._priority = priority;
	}
}