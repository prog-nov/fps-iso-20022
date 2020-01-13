package com.forms.framework.job.common.backupservice;

import java.sql.Connection;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

/**
 * table to table backup
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class T2TBackupService extends BackupBaseService
{

	public T2TBackupService() throws BatchFrameworkException
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.framework.job.common.backupservice.BackupService#backup(java.util.Map)
	 */
	public boolean backup(Map<String, String> ip_backupParameter)
			throws BatchJobException
	{
		boolean loc_flag = true;
		Connection loc_conn = null;
		try
		{
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_conn.setAutoCommit(false);
			// check
			if (null == ip_backupParameter.get("sourceName"))
			{
				throw new Exception("sourceName is null");
			}
			// no targetName,default sourceName_his
			String loc_targetName = (ip_backupParameter.get("targetName") == null ? ip_backupParameter
					.get("sourceName")
					+ backupTable
					: ip_backupParameter.get("targetName"));
			String loc_sql = "INSERT INTO " + scheam + "." + loc_targetName
					+ " SELECT * FROM  " + scheam + "."
					+ ip_backupParameter.get("sourceName");

			String loc_conStr = this.getConditionStr(ip_backupParameter);

			// backup data begin........
			this.deleteBackupData(loc_conn, ip_backupParameter, loc_conStr);
			EntityManager.update(loc_conn, loc_sql + loc_conStr);
			this.deleteData(loc_conn, ip_backupParameter, loc_conStr);
			loc_conn.commit();
		} catch (Exception ip_e)
		{
			try
			{
				DbUtils.rollback(loc_conn);
			} catch (Exception ip_ee)
			{
				throw new BatchJobException(ip_ee);
			}
			throw new BatchJobException(ip_e);
		} finally
		{
			DbUtils.closeQuietly(loc_conn, null, null);
		}
		return loc_flag;
	}

	private void deleteBackupData(Connection ip_conn,
			Map<String, String> ip_backupParameter, String ip_conStr)
			throws BatchJobException
	{
		// delete data
		try
		{
			if (null != ip_backupParameter.get("befortBackup")
					&& deleteFlag.equals(ip_backupParameter.get("befortBackup")))
			{
				// delete data begin....
				String loc_deleteSql = "DELETE FROM " + scheam + "."
						+ ip_backupParameter.get("targetName");
				EntityManager.update(ip_conn, loc_deleteSql + ip_conStr);
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}
	}

}
