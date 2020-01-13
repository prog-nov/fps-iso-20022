package com.forms.batch.job.commom;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.ftpservice.FtpBaseJob;

public class FFPDownloadJob extends FtpBaseJob
{

	@Override
	public boolean execute() throws BatchJobException
	{
		boolean loc_flag = false;
		String loc_message = "";
		boolean loc_backupResult = false;
		try
		{
			for (Map<String, String> loc_fileParameter : filePara)
			{
				loc_message = this.batchIsb + this.separator
						+ loc_fileParameter.get("remotePath")
						+ loc_fileParameter.get("remoteFileName") + " to "
						+ loc_fileParameter.get("localPath")
						+ loc_fileParameter.get("localFileName");
				this.batchLogger.info("[ISBDownloadJob " + " File "
						+ loc_message + "]");
				// copy file from remote directory
				File remoteFilePath = new File(this.batchIsb + this.separator + loc_fileParameter.get("remotePath"));
				final String remoteFilePattern = loc_fileParameter.get("remoteFileName");
				File[] remoteFileList = remoteFilePath.listFiles(new FilenameFilter()
				{
					@Override
					public boolean accept(File dir, String name)
					{
						if(name.matches(remoteFilePattern))
						{
							return true;
						}
						return false;
					}
				});
				
				if (remoteFileList != null)
				{
					for(File remoteFile : remoteFileList)
					{
						this.batchLogger.info("[ISBDownloadJob move " + remoteFile.getPath() + " to " +
								loc_fileParameter.get("localPath") + remoteFile.getName() + "]");
						Map<String, String> loc_isbParameter = new HashMap<String, String>();
						loc_isbParameter.put("sourceName", remoteFile.getPath());
						loc_isbParameter.put("targetName", loc_fileParameter.get("localPath") + remoteFile.getName());
						loc_isbParameter.put("acDate", this.batchAcDate);
						
						loc_backupResult = backupService.backup(loc_isbParameter);
						if (!loc_backupResult)
						{
							throw new BatchJobException("ISBDownload failed");
						}
						// default backup file
						if (null == loc_fileParameter.get("backup")
								|| "Y".equalsIgnoreCase(loc_fileParameter.get("backup")))
						{
							this.batchLogger.info("[ISBDownloadJob backup " + remoteFile.getPath() + " to " +
									loc_fileParameter.get("localPath") + remoteFile.getName() + "]");
							
							Map<String, String> loc_backupParameter = new HashMap<String, String>();
							String loc_fileName = remoteFile.getName();
							if (loc_fileParameter.get("backupFilePath") == null)
							{
								loc_fileName = batchBackup + this.separator + loc_fileName;
							} else
							{

								loc_fileName = batchBackup + this.separator + loc_fileParameter.get("backupFilePath") + loc_fileName;
							}
							loc_backupParameter.put("sourceName", remoteFile.getPath());
							loc_backupParameter.put("targetName", loc_fileName + 
													(loc_fileParameter.get("backupFilePattern") == null ? "#{acDate[yyyyMMdd"
															+ patternUtil.timePattern + "]}"
															: loc_fileParameter.get("backupFilePattern")));
							loc_backupParameter.put("acDate", this.batchAcDate);
							
							loc_backupResult = backupService.backup(loc_backupParameter);
							if (!loc_backupResult)
							{
								throw new BatchJobException("ISBDownload backup failed");
							}
						}
					}
					
					for(File remoteFile : remoteFileList)
					{
//						delete remote File
						remoteFile.delete();
					}
				}
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
		}
		return loc_flag;
	}

	@Override
	protected void initFilePath(Map<String, String> ip_fileParameter)
	{
		String loc_localPath = ip_fileParameter.get("localPath");
		String loc_remotePath = ip_fileParameter.get("remotePath");
		
		
		ip_fileParameter.put("localPath", this.batchData
				+ separator
				+ (loc_localPath == null ? para.get("defaultLocalInputPath")
						: loc_localPath));
		ip_fileParameter.put("remotePath", loc_remotePath == null ? para
				.get("defaultRemoteOutputPath") : loc_remotePath);
	}

}
