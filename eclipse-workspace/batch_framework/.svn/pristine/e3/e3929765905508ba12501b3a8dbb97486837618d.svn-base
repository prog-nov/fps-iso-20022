package com.forms.framework.job.husjob;

import java.sql.Connection;
import java.util.Arrays;

import com.forms.framework.job.common.husservice.Housekeep;
import com.forms.framework.job.common.husservice.HousekeepTable;
import com.forms.framework.persistence.EntityManager;

public class HusDeleteJob extends HusJob
{
	public int insertTempTable(Connection ip_conn,
			Housekeep ip_houseKeep) throws Exception
	{
		String loc_sql = "INSERT INTO "
				+ baseHusJob.schema
				+ ".TB_BH_HOUSEKEEP_JNL(JNL_NO,TX_CODE,HOUSE_TYPE) SELECT JNL_NO,TX_CODE,'"+baseHusJob.husType+"' FROM "
				+ baseHusJob.schema
				+ ".TB_TX_JNL_HIST WHERE TX_CODE=? AND LAST_ACTION_ACDT<=?";
		Object[] loc_param = new Object[] {
				ip_houseKeep.getTxCode(),
				ip_houseKeep.getHousekeepDay()};
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
			int result = baseHusJob.deleteTable(ip_conn, ip_housekeepTable
					.getHisTable(), ip_houseKeep.getTxCode(),
					ip_housekeepTable.getJnlColumn(), ip_housekeepTable
							.getCondition());
			this.batchLogger.info("todo " + baseHusJob.message + ",txcode="
					+ ip_houseKeep.getTxCode() + ",tablename="
					+ ip_housekeepTable.getHisTable() + " result=" + result
					+ " count");
	}

	@Override
	public String getExecTableName(HousekeepTable loc_housekeepTable) throws Exception {
		return loc_housekeepTable.getHisTable();
	}

}
