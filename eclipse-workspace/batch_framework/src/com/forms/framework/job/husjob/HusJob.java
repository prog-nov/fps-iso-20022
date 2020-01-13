package com.forms.framework.job.husjob;

import java.sql.Connection;

import com.forms.framework.job.common.husservice.BaseHusJob;
import com.forms.framework.job.common.husservice.Housekeep;
import com.forms.framework.job.common.husservice.HousekeepTable;
import com.forms.framework.log.BatchLogger;

public abstract class HusJob {

	protected BaseHusJob baseHusJob;
	
	protected BatchLogger batchLogger = null;
	
	protected String batchAcDate=null;
	
	public HusJob(){}
	
	public void init(BaseHusJob baseHusJob,BatchLogger batchLogger,String batchAcDate){
		this.baseHusJob=baseHusJob;
		this.batchLogger=batchLogger;
		this.batchAcDate=batchAcDate;
	}
		
	public abstract int insertTempTable(Connection ip_conn,
			Housekeep ip_houseKeep) throws Exception;
	
	public abstract void subToDo(Connection ip_conn, Housekeep ip_houseKeep,
			HousekeepTable ip_housekeepTable) throws Exception;
	
	public abstract String getExecTableName(HousekeepTable loc_housekeepTable) throws Exception;
}
