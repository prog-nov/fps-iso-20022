package com.forms.framework.job.common.backupservice;

import java.util.Map;

import com.forms.framework.exception.BatchJobException;
/**
 * backup interface
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public interface BackupService
{
	/**
	 * backup
	 * @param ip_backupParameter
	 * @return
	 * @throws BatchJobException
	 */
	public boolean backup(Map<String, String> ip_backupParameter)
			throws BatchJobException;
}
