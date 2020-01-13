package com.forms.framework.job;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.ftpservice.FtpBaseJob;
import com.forms.framework.job.common.ftpservice.FtpService;

/**
 * FTP uplpad JOB
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class FtpUploadJob extends FtpBaseJob
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.framework.BaseJob#execute()
	 */
	@Override
	public boolean execute() throws BatchJobException
	{
		// TODO Auto-generated method stub
		boolean loc_flag = true;
		try
		{
			String loc_transferProtocol = para.get("transferProtocol");
			// upload file
			if (sftp.equalsIgnoreCase(loc_transferProtocol))
			{
				sftpUploadFile();
			} else if (isb.equals(loc_transferProtocol))
			{
				isbUploadFile();
			} else
			{
				throw new BatchJobException("transferProtocol "
						+ loc_transferProtocol + " error");
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
		return loc_flag;
	}

	private void sftpUploadFile() throws Exception
	{
		FTPClient loc_ftpClient = null;
		String loc_message = "";
		try
		{
			FtpService loc_service = new FtpService();
			loc_ftpClient = loc_service.getFtpClient(para);
			loc_ftpClient.changeWorkingDirectory(this.batchFtp);
			for (Map<String, String> loc_fileParameter : filePara)
			{
				loc_message = loc_fileParameter.get("localPath")
						+ loc_fileParameter.get("localFileName") + " to "
						+ this.batchFtp+"/"
						+ loc_fileParameter.get("remotePath") 
						+ loc_fileParameter.get("remoteFileName");
				this.batchLogger.info("[FTPUploadJob " + " File " + loc_message
						+ "]");
				if (!loc_service.uploadFtpFile(loc_ftpClient, loc_fileParameter,this.batchFtp))
				{
					throw new BatchJobException("[FTPUploadJob "
							+ " FAILED " + loc_message + "]");
				} else
				{
					// default backup file
					if (!this.backupFile(loc_fileParameter))
					{
						this.batchLogger.info("[FTPUploadJob "
								+ " backup File Failed "
								+ loc_fileParameter.get("localPath")
								+ loc_fileParameter.get("localFileName") + "]");
					}
				}
			}
		}catch(Exception e)
		{
			throw e;
		}finally
		{
			if(null != loc_ftpClient)
			{
				loc_ftpClient.disconnect();
			}
		}
	}
	
	public void isbUploadFile() throws Exception
	{
//			delete ISB file
		String loc_message = "";
		boolean loc_backupResult = false;
		try
		{
			for (Map<String, String> loc_fileParameter : filePara)
			{
				File loc_file=new File(this.batchIsb + this.separator
						+ loc_fileParameter.get("remotePath")
						+ loc_fileParameter.get("remoteFileName")+".SENT");
				loc_file.delete();
			}
			for (Map<String, String> loc_fileParameter : filePara)
			{
//				 default backup file
				if (!this.backupFile(loc_fileParameter))
				{
					this.batchLogger.info("[ISBUploadJob "
							+ " backup File Failed "
							+ loc_fileParameter.get("localPath")
							+ loc_fileParameter.get("localFileName") + "]");
				}
				loc_message = loc_fileParameter.get("localPath")
						+ loc_fileParameter.get("localFileName") + " to "
						+ this.batchIsb + this.separator
						+ loc_fileParameter.get("remotePath")
						+ loc_fileParameter.get("remoteFileName");
				if(loc_fileParameter.get("send")==null||loc_fileParameter.get("send").equals("Y")){
					this.batchLogger.info("[ISBUploadJob " + " File " + loc_message
							+ "]");
				}
				// copy file to isb directory
				loc_backupResult = backupService.backup(this
						.getFileToIsbParameter(loc_fileParameter));
				if (!loc_backupResult)
				{
					throw new BatchJobException("File backup failed");
				}
				
				callISBOtherSystem(loc_fileParameter);
			}
			notSendfile.delete();
		}catch(Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Nothing to do
	 * if user must send a message to other system when success to isb upload,
	 * can create a class extends this class and rewrite this function
	 * @param msgFlag
	 * @param value is Y,N,NULL
	 * @throws Exception
	 */
	protected void callISBOtherSystem(Map<String, String> ip_fileParameter) throws Exception
	{
		//doNothing
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.framework.job.common.ftpservice.FtpBaseJob#initFilePath(java.util.Map)
	 */
	@Override
	protected void initFilePath(Map<String, String> ip_fileParameter)
	{
		String loc_localPath = ip_fileParameter.get("localPath");
		String loc_remotePath = ip_fileParameter.get("remotePath");
		ip_fileParameter.put("localPath", this.batchData
				+ separator
				+ (loc_localPath == null ? para.get("defaultLocalOutputPath")
						: loc_localPath));
		ip_fileParameter.put("remotePath", loc_remotePath == null ? para
				.get("defaultRemoteInputPath") : loc_remotePath);
	}

	private Map<String, String> getFileToIsbParameter(
			Map<String, String> ip_fileParameter) throws BatchJobException
	{
		Map<String, String> loc_backupParameter = new HashMap<String, String>();
		try
		{
			loc_backupParameter.put("sourceName", ip_fileParameter.get("localPath")
					+ ip_fileParameter.get("localFileName"));
			loc_backupParameter.put("targetName", this.batchIsb + this.separator
					+ ip_fileParameter.get("remotePath")
					+ ip_fileParameter.get("remoteFileName"));
			loc_backupParameter.put("acDate", this.batchAcDate);
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
		return loc_backupParameter;
	}

}
