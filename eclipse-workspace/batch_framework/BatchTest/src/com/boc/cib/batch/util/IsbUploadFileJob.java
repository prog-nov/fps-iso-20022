package com.boc.cib.batch.util;

import java.util.Map;

import com.forms.framework.job.FtpUploadJob;

/**
 * FTP uplpad JOB
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class IsbUploadFileJob extends FtpUploadJob
{
	/**
	 * Nothing to do
	 * if user must send a message to other system when success to isb upload,
	 * can create a class extends this class and rewrite this function
	 * @param msgFlag
	 * @param value is Y,N,NULL
	 * @throws Exception
	 */
	@Override
	protected void callISBOtherSystem(Map<String, String> ip_fileParameter) throws Exception
	{
		String msgFlag = ip_fileParameter.get("send");
		if(msgFlag==null||msgFlag.equals("Y")){
//			int result=ISBMsgUtil.fileCreationAck(isbFlag,
//					isbStr, ip_fileParameter.get("localFileName"), ip_fileParameter.get("localFileId"),
//					DateUtil.dateToString(DateUtil.stringToDate(
//							this.batchAcDate,
//							DateUtil.CIB_DATE_FORMAT), "yyyyMMdd"));
			int result = 0;
			if(result!=0){
				this.batchLogger.error(ip_fileParameter.get("localFileId")+ip_fileParameter.get("localFileName")+" ISB SEND FILE FAIL! result:"+result);
			}else
			{
				this.batchLogger.error(ip_fileParameter.get("localFileId")+ip_fileParameter.get("localFileName")+" ISB SEND FILE SUCCESS! result:"+result);
			}
		}
	}
	
	/**
	 * Nothing to do
	 * if user must send a message to other system when success to sftp upload,
	 * can create a class extends this class and rewrite this function
	 * @param msgFlag
	 * @throws Exception
	 */
	protected void callSFTPOtherSystem(String msgFlag) throws Exception
	{
		//doNothing
	}
}
