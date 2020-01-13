package com.forms.ffp.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FFPTeErrorMsg implements FFPMsgInterface
{
	public static final Class<FFPTeErrorMsg> CLASS_NAME = FFPTeErrorMsg.class;
	
	private static Logger log = LoggerFactory.getLogger(CLASS_NAME);
	
	public static String ERROR_CODE = null;
	
	private static FFPTeErrorMsg instance = null;
	
	static
	{
		instance = new FFPTeErrorMsg();
		ERROR_CODE = CLASS_NAME.toString();
	}
	
	public static FFPMsgInterface getInstance() throws FFPSystemException
	{
		if(instance == null)
		{
			instance = new FFPTeErrorMsg();
		}
		
		return instance;
	}
	
	public static String getErrorCode(Class<?> ip_class, int index)
	{
		return getErrorCode(ip_class.getName(), index);
	}
	
	public static String getErrorCode(String ip_className, int index)
	{
		return ip_className;
	}
	
	@Override
	public String getMsg(String errorCode) throws FFPSystemException {
		return null;
	}
}
