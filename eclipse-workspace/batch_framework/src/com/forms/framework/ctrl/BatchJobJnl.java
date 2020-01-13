package com.forms.framework.ctrl;

import java.util.Date;

public class BatchJobJnl
{

	private String batchAcDate = null;

	private String jobId = null;

	private int jnlSeq = 0;

	private Date startTs = null;

	private String jobStat = null;

	private String result = null;

	private String actionJnl = null;

	private Date endTs = null;

	private String breakPoint = null;

	public String getBatchAcDate()
	{
		return batchAcDate;
	}

	public void setBatchAcDate(String a_batchAcDate)
	{
		this.batchAcDate = a_batchAcDate;
	}

	public String getJobId()
	{
		return jobId;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public int getJnlSeq()
	{
		return jnlSeq;
	}

	public void setJnlSeq(int jnlSeq)
	{
		this.jnlSeq = jnlSeq;
	}

	public Date getStartTs()
	{
		return startTs;
	}

	public void setStartTs(Date startTs)
	{
		this.startTs = startTs;
	}

	public String getJobStat()
	{
		return jobStat;
	}

	public void setJobStat(String jobStat)
	{
		this.jobStat = jobStat;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public String getActionJnl()
	{
		return actionJnl;
	}

	public void setActionJnl(String a_actionJnl)
	{
		actionJnl = a_actionJnl;
	}

	public Date getEndTs()
	{
		return endTs;
	}

	public void setEndTs(Date endTs)
	{
		this.endTs = endTs;
	}

	public String getBreakPoint()
	{
		return breakPoint;
	}

	public void setBreakPoint(String breakPoint)
	{
		this.breakPoint = breakPoint;
	}

}
