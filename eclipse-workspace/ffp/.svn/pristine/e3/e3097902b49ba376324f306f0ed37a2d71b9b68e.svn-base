package com.forms.ffp.core.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.core.define.FFPConstants;

public class FFPException extends Exception implements JFExceptionInterface
{
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(FFPException.class);
	
	private StringBuffer txPath;
	
	private String action;

	private List<FFPErrCode> errorCodeList;
	
	private String errorLevel;
	
	private String exception;
	
	private String retryPath;
	
	public FFPException()
	{
	}
	
	public FFPException(String ip_action, String ip_errorLevel, String ip_exception) throws Exception
	{
		super();
		
		try
		{
			txPath = new StringBuffer();
			setAction(ip_action);
			setErrorLevel(ip_errorLevel);
			setException(ip_exception);
			
			StackTraceElement[] loc_traces = this.getStackTrace();
			String loc_className = loc_traces[0].getClassName();
			String loc_methodName = loc_traces[0].getMethodName();
			
			setTxPath(loc_className + loc_methodName);
//			TODO
//			setErrorCode(new JFErrCode(JFSeErrMsg.getErrorCode(), ""));
		}catch(Exception ip_ex)
		{
			logger.warn("JFException.JFException()" +
					"Exception throw during handling an exception." +
					"Original Exception input:" +
					"ip_action=" + ip_action +
					":ip_errorLevel" + ip_errorLevel +
					":ip_exception=" + ip_exception);
			throw ip_ex;
		}
	}
	
	public String getTxPath()
	{
		return this.txPath.toString();
	}
	
	public List<FFPErrCode> getErrorCodeList()
	{
		return this.errorCodeList;
	}
	
	public String getException()
	{
		return this.exception;
	}
	
	public String getErrorLevel()
	{
		return this.errorLevel;
	}
	
	public String getAction()
	{
		return this.action;
	}
	
	public String getRetryPath()
	{
		return this.retryPath;
	}
	
	public void setTxPath(String ip_txPath)
	{
		this.txPath.insert(0, ip_txPath);
	}
	
	public void setErrorCode(FFPErrCode ip_errCode) throws Exception
	{
		if(ip_errCode == null)
		{
			throw new FFPSystemException(FFPConstants.ERR_LEVEL_WARN, "Null error code found!");
		}
		
		if(this.errorCodeList == null)
		{
			this.errorCodeList = new ArrayList<FFPErrCode>();
		}
		
		this.errorCodeList.add(ip_errCode);
	}
	
	public void setErrorCodeList(List<FFPErrCode> ip_errorCodeList) throws Exception
	{
		if(ip_errorCodeList == null)
		{
			throw new FFPSystemException(FFPConstants.ERR_LEVEL_WARN, "Null error code list found!");
		}
		
		this.errorCodeList = ip_errorCodeList;
	}
	
	public void setException(String ip_exception)
	{
		this.exception = ip_exception;
	}
	
	public void setErrorLevel(String ip_errorLevel) throws Exception
	{
		if(!FFPConstants.ERR_LEVEL_INFO.equals(ip_errorLevel) &&
				!FFPConstants.ERR_LEVEL_WARN.equals(ip_errorLevel) &&
				!FFPConstants.ERR_LEVEL_ERROR.equals(ip_errorLevel))
		{
			throw new FFPSystemException(FFPConstants.ERR_LEVEL_WARN, "Unknown err type(" + ip_errorLevel + ")");
		}
		
		this.errorLevel = ip_errorLevel;
	}
	
	public void setAction(String ip_action)
	{
		this.action = ip_action;
	}
	
	public void setRetryPath(String ip_retryPath)
	{
		this.retryPath = ip_retryPath;
	}
	
	public String toString()
	{
		return new StringBuffer().append(this.getErrorLevel())
				.append("/")
				.append(this.getErrorCode())
				.append("/")
				.append(this.getTxPath())
				.append("/")
				.append(this.getException())
				.append("/")
				.append(this.getAction())
				.toString();
	}
	
	private String getErrorCode()
	{
		StringBuffer loc_result = new StringBuffer();
		for(int loc_i = 0; loc_i < this.getErrorCodeList().size(); loc_i ++)
		{
			if(loc_i > 0)
				loc_result.append(",");
			loc_result.append(this.getErrorCodeList().get(loc_i));
		}
		return loc_result.toString();
	}
	
	public String[] getDisplayMsg(String ip_langType)
	{
		try
		{
//			TODO
			return new String[]{""};
		}catch(Exception ip_exception)
		{
			return new String[]{};
		}
	}
	
	public String[] getLogMsg()
	{
		return new String[]{this.getException() + "(" + this.getErrorCodeList().get(0) + ")"};
	}
	
	public boolean isToRejectPage()
	{
		return true;
	}
}
