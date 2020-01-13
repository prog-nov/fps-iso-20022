package com.forms.framework.job.common.backupservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;

/**
 * recovery database service
 * 
 * @author lyz createDate:2011-06-11 updateDate:2011-06-11
 */
public class RecoveryService
{
	/**
	 * recovery
	 * 
	 * @param ip_fileName
	 * @param ip_splitStr
	 * @throws BatchJobException
	 */
	public void recoveryDatabase(String ip_fileName, String ip_splitStr)
			throws BatchJobException
	{
		File loc_file = new File(ip_fileName);
		if (!loc_file.exists())
		{
			throw new BatchJobException("file " + ip_fileName + " not exists");
		}

		InputStream loc_is = null;
		InputStreamReader loc_isr = null;
		BufferedReader loc_br = null;
		Connection loc_conn = null;
		PreparedStatement loc_pstmt1 = null;
		PreparedStatement loc_pstmt2 = null;
		try
		{
			loc_conn = ConnectionManager.getInstance().getConnection();
			loc_conn.setAutoCommit(false);
			loc_is = new FileInputStream(loc_file);
			loc_isr = new InputStreamReader(loc_is);
			loc_br = new BufferedReader(loc_isr);
			String loc_values = null;
			int loc_rowIndex = 0;
			Map<String, String> loc_tableInfo = new HashMap<String, String>();
			String[] loc_columnInfos = null;
			String[] loc_valueArray = null;
			while ((loc_values = loc_br.readLine()) != null)
			{
				loc_rowIndex++;
				if (loc_values.startsWith(BackupConstants.recordCount + "="))
				{
					// foot data
				} else
				{
					loc_valueArray = loc_values.split(ip_splitStr);
					if (loc_rowIndex == 1)
					{
						// table info
						for (String loc_info : loc_valueArray)
						{
							loc_tableInfo.put(loc_info.split("=")[0],
									loc_info.split("=")[1]);
						}
					} else if (loc_rowIndex == 2)
					{
						// column info
						loc_columnInfos = loc_values.split(ip_splitStr);
						String loc_deleteSql = this.parseDeleteSql(loc_tableInfo,
								loc_valueArray);
						String loc_insertSql = this.parseInsertSql(loc_tableInfo,
								loc_valueArray);
						loc_pstmt1 = loc_conn.prepareStatement(loc_deleteSql);
						loc_pstmt2 = loc_conn.prepareStatement(loc_insertSql);
					} else
					{

						// data
						this.setDeleteParam(loc_pstmt1, loc_valueArray, loc_tableInfo,
								loc_columnInfos);
						this.setInsertParam(loc_pstmt2, loc_valueArray);
						loc_pstmt1.addBatch();
						loc_pstmt2.addBatch();

					}
				}
			}
			loc_pstmt1.executeBatch();
			loc_pstmt2.executeBatch();
			loc_conn.commit();
		} catch (Exception ip_e)
		{
			try
			{
				loc_conn.rollback();
			} catch (SQLException ip_e1)
			{
				throw new BatchJobException(ip_e1);
			}
			throw new BatchJobException(ip_e);
		} finally
		{
			try
			{
				if (loc_pstmt1 != null)
				{
					loc_pstmt1.close();
				}
				if (loc_pstmt2 != null)
				{
					loc_pstmt2.close();
				}
				if (loc_conn != null)
				{
					loc_conn.close();
				}
				if (loc_br != null)
				{
					loc_br.close();
				}
				if (loc_isr != null)
				{
					loc_isr.close();
				}
				if (loc_is != null)
				{
					loc_is.close();
				}
			} catch (Exception ip_e)
			{
				throw new BatchJobException(ip_e);
			}
		}
	}

	/**
	 * parse delete sql str
	 * 
	 * @param ip_tableInfo
	 * @param ip_tableColumns
	 * @return
	 */
	private String parseDeleteSql(Map<String, String> ip_tableInfo,
			String[] ip_tableColumns)
	{
		StringBuffer loc_deleteSql = new StringBuffer();
		loc_deleteSql.append("DELETE FROM ").append(
				ip_tableInfo.get(BackupConstants.COMM_SCHEMA)).append(".")
				.append(ip_tableInfo.get(BackupConstants.COMM_TABLENAME))
				.append(" WHERE 1=1 ");
		String[] loc_primaryKeys = ip_tableInfo
				.get(BackupConstants.COMM_PRIMARYKEY).split(",");
		for (String loc_primaryKey : loc_primaryKeys)
		{
			loc_deleteSql.append(" AND ").append(loc_primaryKey).append("=?");
		}
		return loc_deleteSql.toString();
	}

	/**
	 * parse insert sql str
	 * 
	 * @param ip_tableInfo
	 * @param ip_tableColumns
	 * @return
	 */
	private String parseInsertSql(Map<String, String> ip_tableInfo,
			String[] ip_tableColumns)
	{
		StringBuffer loc_insertSql = new StringBuffer();
		StringBuffer loc_columnStr = new StringBuffer();
		StringBuffer loc_argStr = new StringBuffer();
		loc_insertSql.append("INSERT INTO ").append(
				ip_tableInfo.get(BackupConstants.COMM_SCHEMA)).append(".")
				.append(ip_tableInfo.get(BackupConstants.COMM_TABLENAME))
				.append("(");
		for (String loc_tableColumn : ip_tableColumns)
		{
			loc_columnStr.append(loc_tableColumn).append(",");
			loc_argStr.append("?,");
		}
		loc_insertSql.append(loc_columnStr.substring(0, loc_columnStr.length() - 1));
		loc_insertSql.append(") VALUES(").append(
				loc_argStr.substring(0, loc_argStr.length() - 1)).append(")");
		return loc_insertSql.toString();
	}

	/**
	 * set insert parameter
	 * 
	 * @param ip_stmt
	 * @param ip_values
	 * @throws SQLException
	 */
	private void setInsertParam(PreparedStatement ip_stmt, String[] ip_values)
			throws SQLException
	{
		int loc_index = 0;
		for (String ip_value : ip_values)
		{
			ip_stmt.setString(++loc_index, ip_value.equals("") ? null : ip_value);
		}
	}

	/**
	 * set delete parameter
	 * 
	 * @param ip_stmt
	 * @param ip_valueArray
	 * @param ip_tableInfo
	 * @param ip_columnInfos
	 * @throws SQLException
	 */
	private void setDeleteParam(PreparedStatement ip_stmt, String[] ip_valueArray,
			Map<String, String> ip_tableInfo, String[] ip_columnInfos)
			throws SQLException
	{
		int loc_index = 0;
		String[] loc_primaryKeys = ip_tableInfo
				.get(BackupConstants.COMM_PRIMARYKEY).split(",");
		for (String loc_primaryKey : loc_primaryKeys)
		{
			for (int loc_i = 0; loc_i < ip_columnInfos.length; loc_i++)
			{
				if (ip_columnInfos[loc_i].equals(loc_primaryKey))
				{
					ip_stmt.setString(++loc_index, ip_valueArray[loc_i].equals("") ? null
							: ip_valueArray[loc_i]);
					break;
				}
			}
		}

	}

}
