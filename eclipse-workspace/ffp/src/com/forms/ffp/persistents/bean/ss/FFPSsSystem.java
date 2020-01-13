package com.forms.ffp.persistents.bean.ss;

import java.util.Date;

public class FFPSsSystem
{
	private String dataKey;

	private Date batchAcdt;

	private String runningMode;

	private String fpsReceiveMode;
	
	private String realtimeControlStat;

	private String realtimeListenerStat;

	private String batchListenerStat;

	public String getDataKey()
	{
		return dataKey;
	}

	public void setDataKey(String dataKey)
	{
		this.dataKey = dataKey;
	}

	public String getRunningMode()
	{
		return runningMode;
	}

	public void setRunningMode(String runningMode)
	{
		this.runningMode = runningMode;
	}

	public Date getBatchAcdt()
	{
		return batchAcdt;
	}

	public void setBatchAcdt(Date batchAcdt)
	{
		this.batchAcdt = batchAcdt;
	}

	public String getRealtimeListenerStat()
	{
		return realtimeListenerStat;
	}

	public void setRealtimeListenerStat(String realtimeListenerStat)
	{
		this.realtimeListenerStat = realtimeListenerStat;
	}
	
	public String getRealtimeControlStat()
	{
		return realtimeControlStat;
	}

	public void setRealtimeControlStat(String realtimeControlStat)
	{
		this.realtimeControlStat = realtimeControlStat;
	}

	public String getBatchListenerStat()
	{
		return batchListenerStat;
	}

	public void setBatchListenerStat(String batchListenerStat)
	{
		this.batchListenerStat = batchListenerStat;
	}

	public String getFpsReceiveMode()
	{
		return fpsReceiveMode;
	}

	public void setFpsReceiveMode(String fpsReceiveMode)
	{
		this.fpsReceiveMode = fpsReceiveMode;
	}

}
