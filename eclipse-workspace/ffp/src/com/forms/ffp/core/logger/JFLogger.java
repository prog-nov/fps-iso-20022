package com.forms.ffp.core.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.ThrowableInformation;

public class JFLogger
{
	private Logger logger = null;

	private StringBuffer prefix = null;

	private JFLogger(String ip_a_loggerName)
	{
		logger = Logger.getRootLogger();
		prefix = new StringBuffer("");
		prefix.append("[");
		prefix.append(ip_a_loggerName);
		prefix.append(" ");
		// logger.
	}

	public static JFLogger getLogger(String ip_a_loggerName)
	{
		return new JFLogger(ip_a_loggerName);
	}

	public static <T> JFLogger getLogger(Class<T> ip_a_loggerName)
	{
		return new JFLogger(ip_a_loggerName.toString());
	}

	public void info(Object ip_message)
	{
		logger.info(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void info(Object ip_message, Throwable ip_t)
	{
		logger.info(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message + formatThrowable(ip_t)) + "]").toString());
	}

	public void debug(Object ip_message)
	{
		logger.debug(new StringBuffer(prefix.toString()).append(ip_message).toString());
	}

	public void debug(Object ip_message, Throwable ip_t)
	{
		logger.debug(new StringBuffer(prefix.toString()).append(ip_message).toString(),
				ip_t);
	}

	public void warn(Object ip_message)
	{
		logger.warn(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message)+ "]").toString());
	}

	public void warn(Object ip_message, Throwable ip_t)
	{
		logger.warn(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message+ formatThrowable(ip_t)) + "]").toString());
	}

	public void error(Object ip_message)
	{
		logger.error(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void error(Object ip_message, Throwable ip_t)
	{
		logger.error(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message + formatThrowable(ip_t)) + "]").toString());
	}

	public void fatal(Object ip_message)
	{
		logger.fatal(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message) + "]").toString());
	}

	public void fatal(Object ip_message, Throwable ip_t)
	{
		logger.fatal(new StringBuffer(prefix.toString()).append("" + formatMessage(ip_message+ formatThrowable(ip_t)) + "]").toString());
	}

	public String formatMessage(Object ip_message)
	{
		String loc_str = ip_message.toString();
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
		String loc_tmp = null;
		if(null != loc_strs)
		{
			for(int i=0;i<loc_strs.length;i++)
			{
				loc_tmp = loc_strs[i];
				if(null != loc_tmp)
				{
					loc_str += loc_tmp + "\n";
				}
			}
		}
		return loc_str;
	}
}
