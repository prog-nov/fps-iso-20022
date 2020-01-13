package com.forms.framework.job.husjob;

import java.sql.Connection;
import java.util.Arrays;

import com.forms.framework.job.common.husservice.Housekeep;
import com.forms.framework.job.common.husservice.HousekeepTable;
import com.forms.framework.persistence.EntityManager;

public class HusTransJob extends HusJob
{

	private int transTable(Connection ip_conn, String ip_sourceTableName,
			String ip_targetTableName, String ip_tx_code) throws Exception
	{
		String loc_sql = "INSERT INTO "
				+ baseHusJob.schema
				+ "."
				+ ip_targetTableName
				+ " SELECT A.* FROM "
				+ baseHusJob.schema
				+ "."
				+ ip_sourceTableName
				+ " A JOIN "
				+ baseHusJob.schema
				+ ".TB_BH_HOUSEKEEP_JNL B ON A.JNL_NO=B.JNL_NO WHERE B.TX_CODE=?";
		Object[] loc_param = new Object[] { ip_tx_code };
		if (baseHusJob.run.equalsIgnoreCase(baseHusJob.DEBUG_STR))
		{
			this.batchLogger.info(loc_sql + "  Parameter:"
					+ Arrays.deepToString(loc_param));
		} else if (baseHusJob.run.equalsIgnoreCase(baseHusJob.RUN_STR))
		{
			return EntityManager.update(ip_conn, loc_sql, loc_param);
		} else
		{
			throw new Exception(
					"housekeep-run-setting/housekeep-run  config error!");
		}
		return 0;

	}

	public int insertTempTable(Connection ip_conn,
			Housekeep ip_houseKeep) throws Exception
	{
		String loc_sql = "INSERT INTO "
				+ baseHusJob.schema
				+ ".TB_BH_HOUSEKEEP_JNL(JNL_NO,TX_CODE,HOUSE_TYPE) SELECT A.JNL_NO,A.TX_CODE,'"+baseHusJob.husType+"' FROM "
				+ baseHusJob.schema
				+ ".TB_TX_JNL A WHERE A.TX_CODE=? AND A.LAST_ACTION_ACDT<=? AND HOLD_TYPE IS NULL AND A.STAT IN(";
		for (int i = 0; i < ip_houseKeep.getHousekeep_stats().length; i++)
		{
			if (i == 0)
			{
				loc_sql += "?";
			} else
			{
				loc_sql += ",?";
			}
		}
		loc_sql += ")";
		loc_sql += (ip_houseKeep.getTransSqlCondition()==null?"":ip_houseKeep.getTransSqlCondition());
		Object[] loc_param = new Object[2 + ip_houseKeep.getHousekeep_stats().length];
		loc_param[0] = ip_houseKeep.getTxCode();
		loc_param[1] = this.batchAcDate;
		System.arraycopy(ip_houseKeep.getHousekeep_stats(), 0, loc_param, 2,
				ip_houseKeep.getHousekeep_stats().length);
		if (baseHusJob.run.equalsIgnoreCase(baseHusJob.DEBUG_STR))
		{
			this.batchLogger.info(loc_sql + "  Parameter:"
					+ Arrays.deepToString(loc_param));
		} else if (baseHusJob.run.equalsIgnoreCase(baseHusJob.RUN_STR))
		{
			return EntityManager.update(ip_conn, loc_sql, loc_param);
		} else
		{
			throw new Exception(
					"housekeep-run-setting/housekeep-run  config error!");
		}
		return 0;
	}

	public void subToDo(Connection ip_conn, Housekeep ip_houseKeep,
			HousekeepTable ip_housekeepTable) throws Exception
	{
			if (ip_housekeepTable.getHisTable() != null)
			{
				this.transTable(ip_conn, ip_housekeepTable.getSourceTable(),
						ip_housekeepTable.getHisTable(), ip_houseKeep
								.getTxCode());
			}
			int result=baseHusJob.deleteTable(ip_conn, ip_housekeepTable.getSourceTable(),
					ip_houseKeep.getTxCode(), ip_housekeepTable
							.getJnlColumn(), ip_housekeepTable
							.getCondition());
			this.batchLogger.info("todo " + baseHusJob.message + ",txcode="
					+ ip_houseKeep.getTxCode() + ",tablename="
					+ ip_housekeepTable.getSourceTable()+" result="+result+" count");
	}

	@Override
	public String getExecTableName(HousekeepTable loc_housekeepTable) throws Exception {
		return loc_housekeepTable.getSourceTable();
	}

}