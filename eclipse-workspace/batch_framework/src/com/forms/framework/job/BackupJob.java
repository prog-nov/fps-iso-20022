package com.forms.framework.job;

import java.util.Map;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.backupservice.BackupBaseJob;
import com.forms.framework.job.common.backupservice.BackupFactory;
import com.forms.framework.job.common.backupservice.BackupService;
/**
 * backup job 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class BackupJob extends BackupBaseJob
{

	@Override
	public boolean execute() throws BatchJobException
	{
		boolean loc_flag = true;
		String loc_message = "";
		try
		{
			BackupService loc_backupService = null;
			for (Map<String, String> loc_backupParameter : para)
			{
				loc_message = loc_backupParameter.get("sourceType") + ":"
						+ loc_backupParameter.get("sourceName") + " to "
						+ loc_backupParameter.get("targetType") + ":"
						+ loc_backupParameter.get("targetName");
				this.batchLogger.info("[backup " + loc_message+"]");
				loc_backupService = BackupFactory.getBackupService(
						loc_backupParameter.get("sourceType"), loc_backupParameter
								.get("targetType"));
				loc_flag=loc_backupService.backup(loc_backupParameter);
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
		return loc_flag;
	}
}
