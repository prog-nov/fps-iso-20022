package com.forms.framework.job.common.ftpservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.forms.framework.exception.BatchJobException;

/**
 * FTP service
 * 
 * @author ahnan createDate:2011-04-28 updateDate:2011-04-28
 */
public class FtpService
{

	public FTPClient getFtpClient(Map<String, String> ip_para)
			throws BatchJobException
	{
		FTPClient loc_ftpClient = new FTPClient();
		try
		{
			loc_ftpClient.connect(ip_para.get("hostIp"), Integer.parseInt(ip_para
					.get("hostPort")));			
			if(!loc_ftpClient.login(ip_para.get("user"), ip_para.get("password"))){
				throw new BatchJobException("ftp login error!check the ftp config");
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
		return loc_ftpClient;
	}

	/**
	 * get file from ftp
	 * 
	 * @param ip_ftpClient
	 * @param ip_map
	 * @return
	 * @throws BatchJobException
	 */
	public boolean getFtpFile(FTPClient ip_ftpClient, Map<String, String> ip_map)
			throws BatchJobException
	{
		FileOutputStream loc_fos = null;

		try
		{
			if ("ascii".equalsIgnoreCase(ip_map.get("transferType"))) // set encoding
				ip_ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
			else
				ip_ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			File loc_file = new File(ip_map.get("localPath")); // 
			if (!loc_file.exists())
				loc_file.mkdirs();

			String loc_hostRemote = ip_ftpClient.printWorkingDirectory();
			String loc_remotePath = ip_map.get("remotePath");
			if (null == ip_map.get("remoteFileName"))
			{
				throw new Exception("remoteFileName is null");
			}
			if (!"/".equals(loc_remotePath))
			{
				if (!ip_ftpClient.changeWorkingDirectory(loc_hostRemote + loc_remotePath)) //turn work directory
					throw new Exception("ftp server remote path is wrong");
			}
			// FTP no file return 
			String[] loc_files = ip_ftpClient.listNames(ip_map.get("remoteFileName"));
			if (loc_files == null || loc_files.length == 0)
			{
				ip_ftpClient.changeWorkingDirectory(loc_hostRemote);
				return false;
			}
			loc_fos = new FileOutputStream(new File(ip_map.get("localPath"), ip_map
					.get("localFileName")));
			if (!ip_ftpClient.retrieveFile(ip_map.get("remoteFileName"), loc_fos))
			{
				ip_ftpClient.changeWorkingDirectory(loc_hostRemote);
				return false;
			}
			ip_ftpClient.changeWorkingDirectory(loc_hostRemote);
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				if (loc_fos != null)
					loc_fos.close();
			} catch (Exception ip_ee)
			{
				throw new BatchJobException(ip_ee);
			}
		}
		return true;
	}

	/**
	 * upload file
	 * 
	 * @param ip_ftpClient
	 * @param ip_map
	 * @return
	 * @throws BatchJobException
	 */
	public boolean uploadFtpFile(FTPClient ip_ftpClient, Map<String, String> ip_map,String ip_hostRemote)
			throws BatchJobException
	{
		FileInputStream loc_fis = null;
		try
		{
			if ("ascii".equalsIgnoreCase(ip_map.get("transferType"))) // set encoding
				ip_ftpClient.setFileType(FTPClient.ASCII_FILE_TYPE);
			else
				ip_ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			String loc_remotePath = ip_map.get("remotePath");
			if (!"/".equals(loc_remotePath))
			{
				if (!ip_ftpClient.changeWorkingDirectory(ip_hostRemote +"/"+ loc_remotePath)) // turn work directory
				{
					throw new Exception("ftp server remote path "+ip_hostRemote +"/"+ loc_remotePath+" is wrong");
				}					
			}
			if (ip_map.get("localFileName") == null
					|| "".equals(ip_map.get("localFileName")))
			{
				throw new Exception("local file fileName is null");
			}
			File loc_file = new File(ip_map.get("localPath"), ip_map.get("localFileName"));
			if (!loc_file.exists())
			{
				throw new Exception("local file " + loc_file.getAbsolutePath()
						+ " is not exists");
			}
			loc_fis = new FileInputStream(loc_file);
			if (!ip_ftpClient.storeFile(ip_map.get("remoteFileName"), loc_fis))
			{
				return false;
			}
			ip_ftpClient.changeWorkingDirectory(ip_hostRemote);
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				if (loc_fis != null)
					loc_fis.close();
			} catch (Exception ip_ee)
			{
				throw new BatchJobException(ip_ee);
			}
		}
		return true;
	}


}
