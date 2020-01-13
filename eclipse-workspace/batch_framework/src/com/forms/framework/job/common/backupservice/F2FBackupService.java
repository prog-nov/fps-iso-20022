package com.forms.framework.job.common.backupservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;

/**
 * file to file backup
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class F2FBackupService extends BackupBaseService
{

	public F2FBackupService() throws BatchFrameworkException
	{
		super();
	}

	private int buffer = 8192;

	byte[] b = new byte[buffer];

	int len = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.framework.job.common.backupservice.BackupService#backup(java.util.Map)
	 */
	public boolean backup(Map<String, String> ip_backupParameter)
			throws BatchJobException
	{
		boolean loc_flag = true;
		File loc_sourceFile = null;

		try
		{
			// check
			if (null == ip_backupParameter.get("sourceName"))
			{
				throw new BatchJobException("source-name is null");
			}
			if (null == ip_backupParameter.get("targetName"))
			{
				throw new BatchJobException("target-name is null");
			}
			loc_sourceFile = new File(ip_backupParameter.get("sourceName"));
			if (!loc_sourceFile.exists())
			{
				throw new BatchJobException("sourceFile "+ip_backupParameter.get("sourceName")+" is not exists");
			}
			if (loc_sourceFile.isDirectory())
			{
				loc_flag = this.backupFiles(loc_sourceFile, ip_backupParameter, "");
			} else
			{
				loc_flag = this.backupFile(ip_backupParameter.get("targetName"),
						ip_backupParameter, loc_sourceFile);
				this.deleteBackupFile(ip_backupParameter.get("targetName"),
						ip_backupParameter.get("keepFileDays"),ip_backupParameter.get("acDate"));
			}

		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		return loc_flag;
	}

	/**
	 * backup directory and sub file
	 * 
	 * @param ip_sourceFile
	 * @param targetFileName
	 * @param ip_backupParameter
	 * @param isDirectory
	 * @param ip_currentFilePath
	 * @return
	 * @throws BatchJobException
	 */
	private boolean backupFiles(File ip_sourceFile,
			Map<String, String> ip_backupParameter, String ip_currentFilePath)
			throws BatchJobException
	{
		boolean loc_flag = true;
		String loc_filePath = "";
		loc_filePath += ip_currentFilePath;
		try
		{
			if (ip_sourceFile.isDirectory())
			{
				loc_filePath += "/" + ip_sourceFile.getName();
				if (!ip_sourceFile.exists())
				{
					ip_sourceFile.mkdirs();
				}
				// directory
				File[] loc_files = ip_sourceFile.listFiles();
				if (loc_files != null)
				{
					for (File loc_f : loc_files)
					{
						if (!backupFiles(loc_f, ip_backupParameter, loc_filePath))
						{
							return false;
						}
					}
				}
				if (null != ip_backupParameter.get("afterBackup")
						&& deleteFlag
								.equals(ip_backupParameter.get("afterBackup")))
				{
					// delete directory
					ip_sourceFile.delete();
				}
				this.deleteBackupFile(ip_backupParameter.get("targetName")
						+ loc_filePath + "/", ip_backupParameter.get("keepFileDays"),ip_backupParameter.get("acDate"));
			} else
			{
				loc_flag = this.backupFile(ip_backupParameter.get("targetName")
						+ loc_filePath + "/" + ip_sourceFile.getName(),
						ip_backupParameter, ip_sourceFile);
				// delete backup data
				this.deleteBackupFile(ip_backupParameter.get("targetName")
						+ loc_filePath + "/" + ip_sourceFile.getName(),
						ip_backupParameter.get("keepFileDays"),ip_backupParameter.get("acDate"));
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		return loc_flag;
	}

	/**
	 * backup file
	 * 
	 * @param ip_targetFileName
	 * @param afterBackup
	 * @param ip_sourceFile
	 * @return
	 * @throws BatchJobException
	 */
	private boolean backupFile(String ip_targetFileName,
			Map<String, String> ip_backupParameter, File ip_sourceFile)
			throws BatchJobException
	{
		InputStream loc_is = null;
		OutputStream loc_os = null;
		boolean loc_flag = true;
		try
		{
			// backup file
			File loc_targetFile = this.getTargetFile(ip_targetFileName,
					ip_backupParameter);
			if (null != ip_backupParameter.get("afterBackup")
					&& deleteFlag.equals(ip_backupParameter.get("afterBackup")))
			{
				// delete source,rename
				if (loc_targetFile.exists())
				{
					loc_targetFile.delete();
				}
				loc_flag = ip_sourceFile.renameTo(loc_targetFile);
			} else
			{
				// copy backup
				loc_is = new FileInputStream(ip_sourceFile);
				loc_os = new FileOutputStream(loc_targetFile);
				this.writeData(loc_is, loc_os);
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		} finally
		{
			try
			{
				if (loc_is != null)
				{
					loc_is.close();
				}
				if (loc_os != null)
				{
					loc_os.close();
				}
			} catch (Exception e)
			{
				throw new BatchJobException(e);
			}
		}
		return loc_flag;
	}

	/**
	 * write file
	 * 
	 * @param ip_is
	 * @param ip_os
	 * @throws BatchJobException
	 */
	private void writeData(InputStream ip_is, OutputStream ip_os)
			throws BatchJobException
	{
		try
		{
			while ((len = ip_is.read(b)) != -1)
			{
				ip_os.write(b, 0, len);
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

}
