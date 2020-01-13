package com.forms.batch.job.unit.dispatcher;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.util.ExecCmdUtil;

public class FFPTimerTask implements Runnable
{
	private String name;

	private String command;

	private long delay;

	private String nextMethod = "E";

	private long period = 0;
	
	private String timeutil;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCommand()
	{
		return command;
	}

	public void setCommand(String command)
	{
		this.command = command;
	}

	public long getDelay()
	{
		return delay;
	}

	public void setDelay(long delay)
	{
		this.delay = delay;
	}

	public String getNextMethod()
	{
		return nextMethod;
	}

	public void setNextMethod(String nextMethod)
	{
		this.nextMethod = nextMethod;
	}

	public long getPeriod()
	{
		return period;
	}

	public void setPeriod(long period)
	{
		this.period = period;
	}
	
	public String getTimeutil()
	{
		return timeutil;
	}

	public void setTimeutil(String timeutil)
	{
		this.timeutil = timeutil;
	}

	@Override
	public void run()
	{
		BatchLogger logger = BatchLogger.getLogger("FFPTIMETASK", this.name, FFPTimerTask.class);
		try
		{
			ExecCmdUtil.execCmd(new String[]{command});
		} catch (BatchJobException e1)
		{
			logger.error(e1.getMessage());
		}
	}

}
