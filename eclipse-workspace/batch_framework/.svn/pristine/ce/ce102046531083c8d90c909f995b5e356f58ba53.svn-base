package com.forms.framework.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.ThrowableInformation;

public class BatchLogger
{
	private Logger logger = null;

	private StringBuffer prefix = null;

	private BatchLogger(String ip_a_jobName, String ip_a_actionName,
			String ip_a_loggerName)
	{
		logger = Logger.getRootLogger();
		prefix = new StringBuffer("");
		if (ip_a_jobName != null && ip_a_actionName != null)
		{
			prefix.append("[");
			prefix.append(ip_a_jobName);
			prefix.append(" ");
			prefix.append(ip_a_actionName);
			prefix.append(" ");
		}
		// logger.
	}

	public static BatchLogger getLogger(String ip_a_jobName,
			String ip_a_actionName, String ip_a_loggerName)
	{
		return new BatchLogger(ip_a_jobName, ip_a_actionName,
				ip_a_loggerName);
	}

	public static BatchLogger getLogger(String ip_a_jobName,
			String ip_a_actionName, Class<?> ip_a_loggerName)
	{
		return new BatchLogger(ip_a_jobName, ip_a_actionName,
				ip_a_loggerName.toString());
	}

	public void info(Object ip_message)
	{
		logger.info(new StringBuffer(prefix).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void info(Object ip_message, Throwable ip_t)
	{
		logger.info(new StringBuffer(prefix).append("" + formatMessage(ip_message + formatThrowable(ip_t)) + "]").toString());
	}

	public void debug(Object ip_message)
	{
		logger.debug(new StringBuffer(prefix).append(ip_message).toString());
	}

	public void debug(Object ip_message, Throwable ip_t)
	{
		logger.debug(new StringBuffer(prefix).append(ip_message).toString(),
				ip_t);
	}

	public void warn(Object ip_message)
	{
		logger.warn(new StringBuffer(prefix).append("" + formatMessage(ip_message)+ "]").toString());
	}

	public void warn(Object ip_message, Throwable ip_t)
	{
		logger.warn(new StringBuffer(prefix).append("" + formatMessage(ip_message+ formatThrowable(ip_t)) + "]").toString());
	}

	public void error(Object ip_message)
	{
		logger.error(new StringBuffer(prefix).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void error(Object ip_message, Throwable ip_t)
	{
		logger.error(new StringBuffer(prefix).append("" + formatMessage(ip_message + formatThrowable(ip_t)) + "]").toString());
	}

	public void fatal(Object ip_message)
	{
		logger.fatal(new StringBuffer(prefix).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void fatal(Object ip_message, Throwable ip_t)
	{
		logger.fatal(new StringBuffer(prefix).append("" + formatMessage(ip_message+ formatThrowable(ip_t)) + "]").toString());
	}

	public String formatMessage(Object ip_message)
	{
		if(ip_message instanceof Throwable)
		{
			return formatThrowable((Throwable)ip_message);
		}
		
		String loc_str = (String) ip_message;
		if(null != loc_str)
		{
			loc_str = loc_str.replaceAll("\\[", "\\(");
			loc_str = loc_str.replaceAll("\\]", "\\)");
			return loc_str;
		}else
		{
			return "";
		}
	}
	
	public String formatThrowable(Throwable ip_t)
	{
		ThrowableInformation throwableInfo = new ThrowableInformation(ip_t);
		String[] loc_strs = throwableInfo.getThrowableStrRep();
		String loc_str = "";
		if(null != loc_strs)
		{
			for(String loc_tmp : loc_strs)
			{
				if(null != loc_tmp)
				{
					loc_str += loc_tmp + "\n";
				}
			}
		}
		return loc_str;
	}
}
