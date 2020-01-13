package com.forms.framework.job.common.backupservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.util.DateUtil;
import com.forms.datapipe.exception.DataPipeException;

/**
 * table to file backup
 * 
 * @author lyz createDate:2011-04-28 updateDate:2011-04-28
 */
public class T2FBackupService extends BackupBaseService
{

	public T2FBackupService() throws BatchFrameworkException
	{
		super();
	}

	private OutputStream fos;

	private int currentIndex = 0;

	private int columnCount = 0;

	private int bufferSize = 0;

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
		PreparedStatement loc_stmt = null;
		ResultSet loc_rs = null;
		File loc_file = null;
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
			if (null == ip_backupParameter.get("primaryKey"))
			{
				throw new BatchJobException("primary-key is null");
			}
			// buffer
			bufferSize = (ip_backupParameter.get("bufferSize") == null ? fileBufferSize
					: Integer.parseInt(ip_backupParameter.get("bufferSize")));
			loc_file = this.getTargetFile(ip_backupParameter.get("targetName"),
					ip_backupParameter);
			fos = new FileOutputStream(loc_file);
			// split
			String loc_splitStr = (ip_backupParameter.get("splitStr") == null ? defaultSplit
					: ip_backupParameter.get("splitStr"));
			// get connection
			loc_conn = ConnectionManager.getInstance().getConnection();
			// SQL
			String loc_sql = " SELECT * FROM  " + scheam + "."
					+ ip_backupParameter.get("sourceName");
			String loc_conStr = this.getConditionStr(ip_backupParameter);

			loc_stmt = loc_conn.prepareStatement(loc_sql + loc_conStr);
			loc_rs = loc_stmt.executeQuery();
			// column count
			ResultSetMetaData loc_metaData = loc_stmt.getMetaData();
			columnCount = loc_metaData.getColumnCount();
			StringBuffer loc_tableHeadBuffer = new StringBuffer();
			StringBuffer loc_columnHeadBuffer = new StringBuffer(
					BackupConstants.lineFeed);
			StringBuffer loc_buffer = new StringBuffer();
			int loc_index = 0;
			// write tablehead schema,tablename,backuptimestamp
			loc_tableHeadBuffer.append(BackupConstants.COMM_SCHEMA).append("=")
					.append(scheam).append(loc_splitStr);
			loc_tableHeadBuffer.append(BackupConstants.COMM_TABLENAME).append(
					"=").append(ip_backupParameter.get("sourceName")).append(
					loc_splitStr);
			loc_tableHeadBuffer.append(BackupConstants.COMM_PRIMARYKEY).append(
					"=").append(ip_backupParameter.get("primaryKey")).append(
					loc_splitStr);
			loc_tableHeadBuffer.append(BackupConstants.COMM_BACKUPTIME).append(
					"=").append(
					DateUtil.dateToString(DateUtil.getSysDatetime(),
							DateUtil.BATCH_DATETIME_FORMAT));
			this.writeData(loc_tableHeadBuffer.toString());
			// write columnhead
			for (int loc_i = 1; loc_i <= columnCount; loc_i++)
			{
				loc_columnHeadBuffer.append(loc_metaData.getColumnName(loc_i)).append(
						loc_i == columnCount ? "" : loc_splitStr);

			}
			this.writeData(loc_columnHeadBuffer.toString());
			// write data
			while (loc_rs.next())
			{
				loc_index++;
				loc_buffer.append(BackupConstants.lineFeed);
				for (int loc_i = 1; loc_i <= columnCount; loc_i++)
				{
					loc_buffer.append(
							loc_rs.getObject(loc_i) == null ? "" : loc_rs.getObject(loc_i))
							.append(loc_splitStr);

				}
				if (++currentIndex == bufferSize)
				{
					// write data
					currentIndex = 0;
					this.writeData(loc_buffer.toString());
					loc_buffer = new StringBuffer();
				}

			}
			if (currentIndex != 0)
			{
				this.writeData(loc_buffer.toString());
				loc_buffer = new StringBuffer();
			}
			// write tablefoot
			this.writeData(BackupConstants.lineFeed
					+ BackupConstants.recordCount + "=" + loc_index);
			// deelte data
			this.deleteData(loc_conn, ip_backupParameter, loc_conStr);
			// delete backup data
			this.deleteBackupFile(ip_backupParameter.get("targetName"),
					ip_backupParameter.get("keepFileDays"), ip_backupParameter
							.get("acDate"));
		} catch (Exception loc_e)
		{
			throw new BatchJobException(loc_e);
		} finally
		{
			try
			{
				DbUtils.closeQuietly(loc_conn, loc_stmt, loc_rs);

				if (fos != null)
				{
					fos.close();
				}
				if (!loc_flag)
				{
					if (loc_file != null && loc_file.exists())
					{
						loc_file.delete();
					}
				}
			} catch (Exception e)
			{
				throw new BatchJobException(e);
			}
		}
		return loc_flag;
	}

	/**
	 * write data
	 * 
	 * @param ip_buffer
	 * @throws DataPipeException
	 */
	private void writeData(String ip_buffer) throws DataPipeException
	{
		try
		{
			fos.write(ip_buffer.getBytes());
		} catch (Exception loc_e)
		{
			throw new DataPipeException(loc_e);
		}
	}
}
