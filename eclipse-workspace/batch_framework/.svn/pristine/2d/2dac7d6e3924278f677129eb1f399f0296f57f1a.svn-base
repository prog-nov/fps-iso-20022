package com.forms.framework.job;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.common.sqlservice.SqlJobConfig;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.util.DateUtil;
import com.forms.framework.util.PatternUtil;
import com.forms.framework.util.ValidateUtil;

/**
 * sqlJob
 * 
 * @author ahnan createDate:2011-04-28 updateDate:2011-04-28
 */
public abstract class SqlJob extends BatchBaseJob
{
	private String transferName = null;

	private boolean bakup_flag = false;
	
	private String databaseName = "";
	
	private List<Element> paraList = null;

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws BatchJobException
	{
		// TODO Auto-generated method stub
		super.init();
		// get transferName from job configer
		try
		{
			databaseName = this.actionElement.elementTextTrim("databaseName");
			if(null == databaseName || "".equals(databaseName))
			{
				databaseName = ConnectionManager.getDefaultDatabase();
			}
			this.batchLogger.info("THIS SQL JOB EXECUTE ON " + databaseName);
			
			if (this.actionElement.element("backup") != null
					&& this.actionElement.element("backup").getText()
							.equalsIgnoreCase("Y"))
			{
				bakup_flag = true;
			}
			Element loc_paras = this.actionElement.element("parameters");
			if (loc_paras == null)
			{
				throw new BatchJobException(
						"Null configer for Sql action parameters in job configer:"
								+ this.actionName);
			}

			paraList = loc_paras.elements("parameter");
			if (paraList == null)
			{
				throw new BatchJobException(
						"Null configer for Sql action parameters in job configer:"
								+ this.actionName);
			}
		} catch (Exception ip_ex)
		{
			throw new BatchJobException(ip_ex);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		try
		{
			Map<String, String> loc_map = dealSqlStr();
			ConnectionManager loc_cmanager = ConnectionManager.getInstance(databaseName);
			loc_map.put("schema", loc_cmanager.getDefaultSchema());
			loc_map.put("dateFormat", DateUtil.BATCH_DATE_FORMAT);
			loc_map.put("serverDate", DateUtil.getSysDate());
			
			String isSql = "";
			String value = "";
			for(Element tmp : paraList)
			{
				isSql = tmp.attributeValue("isSql");
				value = tmp.getTextTrim();
				if("Y".equalsIgnoreCase(isSql))
				{
					executeSql(value, loc_map, loc_cmanager);
				}else
				{
					executeSqlFile(this.batchConfInfo + File.separator + value,
							loc_map, loc_cmanager);
				}
			}
		}catch(Exception e)
		{
			throw new BatchJobException(e);
		}
			

		return false;
	}
	
	private void executeSql(String sql, Map<String, String> map, ConnectionManager cManager) throws Exception
	{
		PatternUtil loc_sqlJob = new PatternUtil(this.batchAcDate);
		if (this.bakup_flag)
		{
			this.batchLogger.info("BACKUP UP DB DATA BEGIN");
			new ExportJob(this.batchAcDate, this.batchRoot,
					this.batchBackup, this.jobConfiger.getJobId(), sql).execute(
							cManager.getDefaultSchema(), loc_sqlJob, map);
			this.batchLogger.info("BACKUP UP DB DATA END");
		}
		Connection loc_conn = null;
		PreparedStatement loc_pstmt = null;
		try
		{
			loc_conn = cManager.getConnection();
			loc_conn.setAutoCommit(false);
			sql = loc_sqlJob.patternReplace(map, sql);
			this.batchLogger.info(sql);
			loc_pstmt = loc_conn.prepareStatement(sql);						
			loc_pstmt.execute();
			loc_conn.commit();
		}catch (Exception ip_e)
		{
			DbUtils.rollback(loc_conn);
			this.batchLogger.error(sql);
			throw ip_e;
		}finally
		{
			DbUtils.closeQuietly(loc_conn, loc_pstmt, null);
		}
	}
	
	private void executeSqlFile(String sqlFileName, Map<String, String> map, ConnectionManager cManager) throws Exception
	{
		List<List<String>> loc_list = null;
		if (ValidateUtil.isEmpty(sqlFileName))
		{
			throw new Exception(
					"not fileName config message has bean found");
		}
		
		loc_list = SqlJobConfig.initXML(sqlFileName);
		if (loc_list == null)
		{
			throw new Exception(" HAVE NOT SQL IN THE FILE:" + sqlFileName);
		}
		
		PatternUtil loc_sqlJob = new PatternUtil(this.batchAcDate);
		this.batchLogger.info("EXECUTE SQL READ FROM " + sqlFileName);
		if (this.bakup_flag)
		{
			this.batchLogger.info("BACKUP UP DB DATA BEGIN");
			new ExportJob(this.batchAcDate, this.batchRoot,
					this.batchBackup, this.jobConfiger.getJobId(), loc_list).execute(
							cManager.getDefaultSchema(), loc_sqlJob, map);
			this.batchLogger.info("BACKUP UP DB DATA END");
		}
		Connection loc_conn = null;
		PreparedStatement loc_pstmt = null;
		try
		{
			loc_conn = cManager.getConnection();
			loc_conn.setAutoCommit(false);
			for (List<String> loc_l : loc_list)
			{
				String loc_sql = "";
				try
				{
					for (String loc_str : loc_l)
					{
						loc_sql = loc_sqlJob.patternReplace(map, loc_str);
						loc_pstmt = loc_conn.prepareStatement(loc_sql);						
						loc_pstmt.execute();
						this.batchLogger.info(loc_sql);
					}
					loc_conn.commit();
				} catch (Exception ip_e)
				{
					DbUtils.rollback(loc_conn);
					this.batchLogger.error(loc_sql);
					throw new BatchJobException(ip_e);
				} 
			}
		}catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		}finally
		{
			DbUtils.closeQuietly(loc_conn, loc_pstmt, null);
		}
	}

	public abstract Map<String, String> dealSqlStr()
			throws Exception;

	public String getTransferName()
	{
		return transferName;
	}

	public void setTransferName(String ip_transferName)
	{
		this.transferName = ip_transferName;
	}
}
