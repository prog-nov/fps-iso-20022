package com.forms.framework.job;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.ftpservice.FtpBaseJob;
import com.forms.framework.job.common.ftpservice.FtpService;

/**
 * FTP download file JOB
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class FtpDownloadJob extends FtpBaseJob
{

	private long sleepTimes = 1 * 6 * 1000;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.framework.BaseJob#execute()
	 */
	@Override
	public boolean execute() throws BatchJobException
	{
		boolean loc_flag = false;
		FTPClient loc_ftpClient = null;
		String loc_message = "";
		boolean loc_backupResult = false;
		try
		{
			String loc_transferProtocol = para.get("transferProtocol");

			if (sftp.equalsIgnoreCase(loc_transferProtocol))
			{
				FtpService loc_service = new FtpService();
				loc_ftpClient = loc_service.getFtpClient(para);
				for (Map<String, String> loc_fileParameter : filePara)
				{
					loc_message = loc_ftpClient.printWorkingDirectory()
							+ loc_fileParameter.get("remotePath") + "/"
							+ loc_fileParameter.get("remoteFileName") + " to "
							+ loc_fileParameter.get("localPath")
							+ loc_fileParameter.get("localFileName");
					this.batchLogger.info("[FTPDownloadJob " + " File "
							+ loc_message + "]");
					int loc_failRetryTimes = para.get("failRetryTimes") == null ? 1
							: Integer.parseInt(para.get("failRetryTimes")); // file
					// not
					// exists
					// download
					// times
					while (loc_failRetryTimes > 0)
					{
						if (!loc_service.getFtpFile(loc_ftpClient, loc_fileParameter)) // file
						// download
						// file not exists or download fail
						{
							loc_failRetryTimes--;
							Thread.sleep(sleepTimes);
							loc_flag = false;
						} else
						{
							loc_flag = true;
							break;
						}
					}
					if (!loc_flag)
					{
						throw new BatchJobException(loc_ftpClient
								.printWorkingDirectory()
								+ loc_fileParameter.get("remotePath")
								+ "/"
								+ loc_fileParameter.get("remoteFileName")
								+ " is not exists");
					}

					// default backup file

					if (!this.backupFile(loc_fileParameter))
					{
						this.batchLogger.info("[FTPDownloadJob "
								+ " backup File Failed "
								+ loc_fileParameter.get("localPath")
								+ loc_fileParameter.get("localFileName") + "]");
					}
				}
			} else if (isb.equals(loc_transferProtocol))
			{
				// ISB

				
				Map<String,String> loc_isbParameter=null;
				File loc_isbFile=null;
				for (Map<String, String> loc_fileParameter : filePara)
				{
					loc_message = this.batchIsb + this.separator
							+ loc_fileParameter.get("remotePath")
							+ loc_fileParameter.get("remoteFileName") + " to "
							+ loc_fileParameter.get("localPath")
							+ loc_fileParameter.get("localFileName");
					this.batchLogger.info("[ISBDownloadJob " + " File "
							+ loc_message + "]");
					// copy file from isb directory
					loc_isbParameter=this
					.getFileFromIsbParameter(loc_fileParameter);
					loc_isbFile = new File(loc_isbParameter.get("sourceName"));
					if (loc_isbFile.exists())
					{
						loc_backupResult = backupService
								.backup(loc_isbParameter);
						if (!loc_backupResult)
						{
							throw new BatchJobException("ISBDownload failed");
						}
						// default backup file
						if (!this.backupFile(loc_fileParameter))
						{
							this.batchLogger.info("[ISBDownloadJob "
									+ " backup File Failed "
									+ loc_fileParameter.get("localPath")
									+ loc_fileParameter.get("localFileName")
									+ "]");
						}
					}
				}
				for (Map<String, String> loc_fileParameter : filePara)
				{
//					delete ISB File
					File loc_file=new File(this.batchIsb + this.separator
							+ loc_fileParameter.get("remotePath")
							+ loc_fileParameter.get("remoteFileName"));
					loc_file.delete();
				}
			} else
			{
				throw new BatchJobException("transferProtocol "
						+ loc_transferProtocol + " error");
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				if (loc_ftpClient != null)
				{
					loc_ftpClient.disconnect();
				}
			} catch (Exception ip_e)
			{
				throw new BatchJobException(ip_e);
			}
		}

		return loc_flag;
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
		loc_localPath = ip_fileParameter.get("localPath");
		ip_fileParameter.put("localPath", this.batchData
				+ separator
				+ (loc_localPath == null ? para.get("defaultLocalInputPath")
						: loc_localPath));
		ip_fileParameter.put("remotePath", loc_remotePath == null ? para
				.get("defaultRemoteOutputPath") : loc_remotePath);
	}

	private Map<String, String> getFileFromIsbParameter(
			Map<String, String> ip_fileParameter) throws BatchJobException
	{
		Map<String, String> loc_backupParameter = new HashMap<String, String>();
		try
		{
			loc_backupParameter.put("sourceName", this.batchIsb + this.separator
					+ ip_fileParameter.get("remotePath")
					+ ip_fileParameter.get("remoteFileName"));
			loc_backupParameter.put("targetName", ip_fileParameter.get("localPath")
					+ ip_fileParameter.get("localFileName"));
			loc_backupParameter.put("acDate", this.batchAcDate);
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
		return loc_backupParameter;
	}

}
