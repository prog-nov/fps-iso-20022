package com.forms.framework.job.common.backupservice;

import com.forms.framework.exception.BatchJobException;

/**
 * backup factory
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class BackupFactory
{

	public final static String table = "table";

	public final static String file = "file";

	/**
	 * get backup class
	 * 
	 * @param ip_sourceType
	 * @param ip_targetType
	 * @return
	 * @throws BatchJobException
	 */
	public static BackupService getBackupService(String ip_sourceType,
			String ip_targetType) throws BatchJobException
	{
		try
		{
			if (null == ip_sourceType || null == ip_targetType)
			{
				throw new BatchJobException(
						"sourceType is null or targetType is null");
			} else
			{
				if (table.equalsIgnoreCase(ip_sourceType)
						&& table.equalsIgnoreCase(ip_targetType))
				{
					// table to table
					return new T2TBackupService();
				} else if (table.equalsIgnoreCase(ip_sourceType)
						&& file.equalsIgnoreCase(ip_targetType))
				{
					// table to file
					return new T2FBackupService();
				} else if (file.equalsIgnoreCase(ip_sourceType)
						&& file.equalsIgnoreCase(ip_targetType))
				{
					// file to file
					return new F2FBackupService();
				} else
				{
					throw new BatchJobException(
							"no matched backupService found source:"
									+ ip_sourceType + "--target:" + ip_targetType);
				}
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

}
