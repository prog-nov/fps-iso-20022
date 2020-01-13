package com.forms.ffp.mq.config;

public class FFPQueueManagerConfig
{
	private String queueType;
	
	private String hostName;

	private Integer port;

	private String queueManagerName;

	private Integer ccsid;

	private Boolean subscribeInward;

	private String clientChannel;

	private String user;

	private String password;

	private Boolean sslEnable;

	private String sslCipherSuite;

	private String sslPeerName;

	private String sslTrustStoreFilename;

	private String sslTrustStorePassword;

	private String sslKeyStoreFilename;

	private String sslKeyStorePassword;

	public String getQueueType()
	{
		return this.queueType;
	}
	
	public void setQueueType(String queueType)
	{
		this.queueType = queueType;
	}
	
	public String getHostName()
	{
		return this.hostName;
	}

	public void setHostName(String hostName)
	{
		this.hostName = hostName;
	}

	public Integer getPort()
	{
		return this.port;
	}

	public void setPort(Integer port)
	{
		this.port = port;
	}

	public String getQueueManagerName()
	{
		return this.queueManagerName;
	}

	public void setQueueManagerName(String queueManagerName)
	{
		this.queueManagerName = queueManagerName;
	}

	public Integer getCcsid()
	{
		return this.ccsid;
	}

	public void setCcsid(Integer ccsid)
	{
		this.ccsid = ccsid;
	}

	public Boolean getSubscribeInward()
	{
		return subscribeInward;
	}

	public void setSubscribeInward(Boolean subscribeInward)
	{
		this.subscribeInward = subscribeInward;
	}

	public String getClientChannel()
	{
		return clientChannel;
	}

	public void setClientChannel(String clientChannel)
	{
		this.clientChannel = clientChannel;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Boolean getSslEnable()
	{
		return sslEnable;
	}

	public void setSslEnable(Boolean sslEnable)
	{
		this.sslEnable = sslEnable;
	}

	public String getSslCipherSuite()
	{
		return sslCipherSuite;
	}

	public void setSslCipherSuite(String sslCipherSuite)
	{
		this.sslCipherSuite = sslCipherSuite;
	}

	public String getSslPeerName()
	{
		return sslPeerName;
	}

	public void setSslPeerName(String sslPeerName)
	{
		this.sslPeerName = sslPeerName;
	}

	public String getSslTrustStoreFilename()
	{
		return sslTrustStoreFilename;
	}

	public void setSslTrustStoreFilename(String sslTrustStoreFilename)
	{
		this.sslTrustStoreFilename = sslTrustStoreFilename;
	}

	public String getSslTrustStorePassword()
	{
		return sslTrustStorePassword;
	}

	public void setSslTrustStorePassword(String sslTrustStorePassword)
	{
		this.sslTrustStorePassword = sslTrustStorePassword;
	}

	public String getSslKeyStoreFilename()
	{
		return sslKeyStoreFilename;
	}

	public void setSslKeyStoreFilename(String sslKeyStoreFilename)
	{
		this.sslKeyStoreFilename = sslKeyStoreFilename;
	}

	public String getSslKeyStorePassword()
	{
		return sslKeyStorePassword;
	}

	public void setSslKeyStorePassword(String sslKeyStorePassword)
	{
		this.sslKeyStorePassword = sslKeyStorePassword;
	}

}
