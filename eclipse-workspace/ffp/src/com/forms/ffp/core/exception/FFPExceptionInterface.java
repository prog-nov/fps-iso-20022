package com.forms.ffp.core.exception;

public interface FFPExceptionInterface 
{
	public void printStackTrace();
	
	public void printStackTrace(java.io.PrintWriter s);
	
	public void printStackTrace(java.io.PrintStream s);
	
	/**
	 * pub_strfn_getTxCode : return the method name
	 * @return Transaction code Method where it throws exception:
	 */
	public String getTxPath();
	
	/**
	 * Return the exception error code
	 * @return Exception error code
	 */
	public String getErrorCode();
	
	/**
	 * Return the exception message
	 * @return Exception type
	 */
	public String getException();
	
	/**
	 * Return the exception error level displayed in logging 
	 * @return Exception error code ERROR,INFO,WARN,DEBUG
	 */
	public String getErrorLevel();
	
	/**
	 * Return the content of the Exception
	 * @return Sting Error details(all parameters)
	 */
	public String toString();
	
	public void setTxPath(String ip_txPath);
	
	public void setErrorCode(String ip_errCode) throws FFPSystemException;
	
	public void setException(String ip_exception);
	
	public void setErrorLevel(String ip_errorLevel) throws FFPSystemException;
	
	
}
