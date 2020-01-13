package com.forms.framework.job.common.husservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.job.ExportJob;
import com.forms.framework.job.husjob.HusJob;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.CommonAPI;
import com.forms.framework.util.ExecCmdUtil;

public class BaseHusJob extends BatchBaseJob
{
	protected Connection conn = null;

	public String schema;

	protected String configFile;

	public List<HousekeepRul> allList=null;

	public String run;

	protected String runTx;

	protected List<String> runTxCode;

	public String message;

	protected HusService husService;

	public final String RUN_STR = "run";

	public final String DEBUG_STR = "debug";
	
	public final String HUSTYPE_TRA = "TRANS";

	public final String HUSTYPE_DEL = "DELETE";

	public final String HUSTYPE_PAU = "PAUTH";

	public final String HUSTYPE_APP = "APPST";
	
	public String husType;
	
	protected boolean bakup_flag=false;
	
	private String cmd="";
	
	private String shStr="exportBatchSqlData.sh";
	
	private String addFlag="N";
	
	private final String fileHz=".property";
	
	private final String resultFileName="_RESULT"+fileHz;
	
	private final String execFileName="_EXEC"+fileHz;
	
	private final String noexecFileName="_NOEXEC"+fileHz;
	
	private final String tablesFileName="_TABLES"+fileHz;
	
	@Override
	public void init() throws BatchJobException
	{
		// TODO Auto-generated method stub
		super.init();
		cmd = this.batchRoot + File.separator + "bin" + File.separator
		+ shStr;
		try
		{
			
			configFile = this.batchRoot
					+ "/"
					+ this.jobConfiger.getElementsByPath(
							"job-config.private-settings.config-file").get(0)
							.getText();
			schema = ConnectionManager.getInstance().getDefaultSchema();
			List<Element> statsRuls=this.jobConfiger.getElementsByPath("job-config.private-settings.housekeep-stats.def-stats-rul");
			husService = HusService.getInstance(configFile,
					this.batchAcDate,statsRuls);
			allList = husService.allList;
			run = husService.runSetting.get("housekeep-run");
			runTx = husService.runSetting.get("housekeep-run-tx");
			if (runTx != null && !runTx.equalsIgnoreCase("all"))
			{
				runTxCode = Arrays.asList(runTx.split(","));
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

	public void deleteTempTable(Connection ip_conn) throws Exception
	{
		String loc_sql = "DELETE FROM " + schema + ".TB_BH_HOUSEKEEP_JNL WHERE HOUSE_TYPE=?";
		if (this.run.equalsIgnoreCase(DEBUG_STR))
		{
			this.batchLogger.info(loc_sql);
		} else if (this.run.equalsIgnoreCase(RUN_STR))
		{
			EntityManager.update(loc_sql,new Object[]{this.husType});
		} else
		{
			throw new Exception(
					"housekeep-run-setting/housekeep-run  config error!");
		}

	}
	
	public String getDeleteStr(String ip_tableName,
			String ip_tx_code, String jnlColumn, String condition){
		String loc_sql = "DELETE FROM  " + schema + "." + ip_tableName + " A ";
		if (condition != null)
		{
			loc_sql += " WHERE " + condition;
		} else
		{
			loc_sql += "WHERE EXISTS(SELECT 1 FROM " + schema
					+ ".TB_BH_HOUSEKEEP_JNL B WHERE A." + jnlColumn
					+ "=B.JNL_NO AND B.TX_CODE='"+ip_tx_code+"')";
		}
		return loc_sql;
	}

	public int deleteTable(Connection ip_conn, String ip_tableName,
			String ip_tx_code, String jnlColumn, String condition)
			throws Exception
	{
		String loc_sql =this.getDeleteStr(ip_tableName, ip_tx_code, jnlColumn, condition);

		if (this.run.equalsIgnoreCase(DEBUG_STR))
		{
			this.batchLogger.info(loc_sql + "  Parameter:"
					+ ip_tx_code);
		} else if (this.run.equalsIgnoreCase(RUN_STR))
		{				
			return EntityManager.update(ip_conn, loc_sql);
		} else
		{
			throw new Exception(
					"housekeep-run-setting/housekeep-run  config error!");
		}
		return 0;

	}

	@Override
	public boolean execute() throws BatchJobException
	{
		OutputStream execTxCodeFileos=null;
		OutputStream notexecTxCodeFileos=null;
		OutputStream execTableNamesos=null;
		try
		{
			conn = ConnectionManager.getInstance().getConnection();
			conn.setAutoCommit(false);
			HusJob husJob=null;
			List<String> execTxCodes=null;
			String backfilePath=ExportJob.getBackupFilePath();
			String propertyFilePath = backfilePath
					+ File.separator + this.batchAcDate.replaceAll("-", "")+File.separator;
			String configFilePath = backfilePath + File.separator+ CommonAPI.PROPERTY_PATH+File.separator;
			String exportFailFilePath=null;
			for(HousekeepRul housekeepRul : allList){
				execTxCodes=new ArrayList<String>();
				husJob=(HusJob)Class.forName(housekeepRul.getClassName()).newInstance();				
				husJob.init(this, batchLogger, batchAcDate);
				this.husType=housekeepRul.getId();
				this.message=housekeepRul.getId();
				exportFailFilePath=configFilePath+this.husType+resultFileName;
				if (housekeepRul.getIsBackup() != null
						&& "Y".equalsIgnoreCase(housekeepRul.getIsBackup()))
				{
					bakup_flag = true;
				}else{
					bakup_flag=false;
				}
//				 delete temp table
				deleteTempTable(conn);
				conn.commit();
				try{
					if(bakup_flag){
						File execTxCodeFile=new File(propertyFilePath+this.husType+this.execFileName);		
						File directory=execTxCodeFile.getParentFile();
						if(!directory.exists()){
							directory.mkdirs();
						}
						execTxCodeFileos=new FileOutputStream(execTxCodeFile);
						File notexecTxCodeFile=new File(propertyFilePath+this.husType+""+this.noexecFileName);
						notexecTxCodeFileos=new FileOutputStream(notexecTxCodeFile);
						File execTableNamesFile=new File(configFilePath+this.husType+this.tablesFileName);
						if(!execTableNamesFile.getParentFile().exists()){
							execTableNamesFile.getParentFile().mkdirs();
						}
						execTableNamesFile.createNewFile();
						execTableNamesos=new FileOutputStream(execTableNamesFile);
					}			
					this.writeBackupFile(housekeepRul, husJob, execTxCodes, execTxCodeFileos, notexecTxCodeFileos, execTableNamesos);
					if(bakup_flag){						
						this.backupFile(configFilePath);
					}
				}catch(Exception e){
					throw e;
				}finally{
					this.closeOS(execTxCodeFileos, notexecTxCodeFileos, execTableNamesos);
				}
				this.execData(housekeepRul, husJob, execTxCodes,exportFailFilePath);
				this.deleteTempTable(conn);
				conn.commit();
			}
		} catch (Exception ip_e)
		{
			throw new BatchJobException(ip_e);
		} finally
		{
			if (conn != null)
			{
				try
				{
					conn.close();
				} catch (Exception e)
				{
					throw new BatchJobException(e);
				}
			}
			this.closeOS(execTxCodeFileos, notexecTxCodeFileos, execTableNamesos);
		}
		return true;
	}
	
	private void closeOS(OutputStream execTxCodeFileos,	OutputStream notexecTxCodeFileos,OutputStream execTableNamesos) throws BatchJobException{
		if(execTxCodeFileos!=null){
			try {
				execTxCodeFileos.close();
			} catch (IOException e) {
				throw new BatchJobException(e);
			}
		}
		if(notexecTxCodeFileos!=null){
			try {
				execTxCodeFileos.close();
			} catch (IOException e) {
				throw new BatchJobException(e);
			}
		}
		if(execTableNamesos!=null){
			try {
				execTableNamesos.close();
			} catch (IOException e) {
				throw new BatchJobException(e);
			}
		}
	}
	
	private void backupFile(String configFilePath) throws BatchJobException{
		ExecCmdUtil.execCmd(new String[] { cmd, this.batchAcDate.replaceAll("-", ""), addFlag,
				configFilePath+this.husType+this.tablesFileName,configFilePath+this.husType+this.resultFileName});
	}
	
	private void writeBackupFile(HousekeepRul housekeepRul,HusJob husJob,List<String> execTxCodes,OutputStream execTxCodeFileos,OutputStream notexecTxCodeFileos,OutputStream execTableNamesos){
		for (Housekeep loc_houseKeep : housekeepRul.getHuslist())
		{
			try
			{
				// insert temp table
				if (runTx.equalsIgnoreCase("all")
						|| runTxCode.contains(loc_houseKeep.getTxCode()))
				{
					int count=husJob.insertTempTable(conn, loc_houseKeep);							
					if(count>0){
						execTxCodes.add(loc_houseKeep.getTxCode());
						if(bakup_flag){
							if (this.run.equalsIgnoreCase("debug"))
							{
								this.batchLogger.info("todo " + message
										+ ",backup begin,txcode="
										+ loc_houseKeep.getTxCode());
							}
							execTxCodeFileos.write((loc_houseKeep.getTxCode()+"\n").getBytes());
							String filename="";
							File f=null;
							for (HousekeepTable loc_housekeepTable : loc_houseKeep
									.getDefTables())
							{
								
								f=this.writeExportFileSub(loc_housekeepTable, loc_houseKeep,housekeepRul,husJob);
								if(f!=null){
									filename=f.getName().split("\\.")[0];
									execTableNamesos.write((filename+"\n").getBytes());
								}
							}
							// todo tx table
							for (HousekeepTable loc_housekeepTable : loc_houseKeep
									.getTables())
							{
								f=this.writeExportFileSub(loc_housekeepTable, loc_houseKeep,housekeepRul,husJob);	
								if(f!=null){
									filename=f.getName().split("\\.")[0];
									execTableNamesos.write((filename+"\n").getBytes());
								}
							}
						}
					}else{
						if(bakup_flag){
							notexecTxCodeFileos.write((loc_houseKeep.getTxCode()+"\n").getBytes());
						}
					}
					conn.commit();
					if(bakup_flag){
						if (this.run.equalsIgnoreCase("debug"))
						{
							this.batchLogger.info("todo " + message
									+ ",backup end,txcode="
									+ loc_houseKeep.getTxCode());
						}
					}					
				}
			} catch (Exception ip_e)
			{
				try
				{
					conn.rollback();
					this.batchLogger.warn(loc_houseKeep.getTxCode()
							+ " " + message + " error!" ,ip_e);
				} catch (SQLException e1)
				{
					this.batchLogger.warn(loc_houseKeep.getTxCode()
							+ " " + message ,ip_e);
				}
			}
		}		
	}
	
	private void execData(HousekeepRul housekeepRul, HusJob husJob, List<String> execTxCodes,String exportFailFilePath) throws Exception{		
		Set<String> exportFailTx=this.getExportFailTx(exportFailFilePath);		
		for (Housekeep loc_houseKeep : housekeepRul.getHuslist())
		{
			try
			{
				// insert temp table
				if (runTx.equalsIgnoreCase("all")
						|| runTxCode.contains(loc_houseKeep.getTxCode()))
				{
					if (this.run.equalsIgnoreCase("debug"))
					{
						this.batchLogger.info("todo " + message
								+ ",exec begin,txcode="
								+ loc_houseKeep.getTxCode());
					}
					if(execTxCodes.contains(loc_houseKeep.getTxCode())&&!exportFailTx.contains(loc_houseKeep.getTxCode())){
						for (HousekeepTable loc_housekeepTable : loc_houseKeep
								.getDefTables())
						{
							this.execSub(loc_housekeepTable, housekeepRul, husJob, loc_houseKeep);
						}
						// todo tx table
						for (HousekeepTable loc_housekeepTable : loc_houseKeep
								.getTables())
						{
							this.execSub(loc_housekeepTable, housekeepRul, husJob, loc_houseKeep);								
						}
					}
					
					if (this.run.equalsIgnoreCase("debug"))
					{
						this.batchLogger.info("todo " + message
								+ ",exec end,txcode="
								+ loc_houseKeep.getTxCode());
					}
				} 
			}catch (Exception ip_e)
			{
				try
				{
					conn.rollback();
					this.batchLogger.warn(loc_houseKeep.getTxCode()
							+ " " + message + " error!" ,ip_e);
				} catch (SQLException e1)
				{
					this.batchLogger.warn(loc_houseKeep.getTxCode()
							+ " " + message ,ip_e);
				}
			}
		}
	}
	
	private Set<String> getExportFailTx(String exportFailFilePath) throws Exception{		
		Set<String> exportFailTx=new HashSet<String>();
		File file=new File(exportFailFilePath);
		if(file.exists()){
			InputStream is=null;
			InputStreamReader isr=null;
			BufferedReader br=null;
			try{
				is=new FileInputStream(file);
				isr=new InputStreamReader(is);
				br=new BufferedReader(isr);
				String msg=null;
				while((msg=br.readLine())!=null){
					exportFailTx.add(msg.split("_")[2]);
				}
			}catch(Exception e){
				throw e;
			}finally{
				if(br!=null){
					br.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(is!=null){
					is.close();
				}
			}
		}		
		return exportFailTx;
		
	}
	
	private void execSub(HousekeepTable loc_housekeepTable,HousekeepRul housekeepRul,HusJob husJob,Housekeep loc_houseKeep) throws Exception{
		if(this.isexec(loc_housekeepTable, loc_houseKeep, housekeepRul, husJob)){
			husJob.subToDo(conn, loc_houseKeep,
					loc_housekeepTable);
		}
	}
	
	private File writeExportFileSub(HousekeepTable loc_housekeepTable,Housekeep loc_houseKeep,HousekeepRul housekeepRul,HusJob husJob) throws Exception{
		if(this.isexec(loc_housekeepTable, loc_houseKeep, housekeepRul, husJob)){
			return this.writeExportFile(loc_housekeepTable, loc_houseKeep,husJob);
		}		
		return null;		
	}
	
	private boolean isexec(HousekeepTable loc_housekeepTable,Housekeep loc_houseKeep,HousekeepRul housekeepRul,HusJob husJob) throws Exception{
		if(HousekeepRul.TYPE_PUBLIC.equalsIgnoreCase(housekeepRul.getType())){		
			if (loc_housekeepTable.getId() == null
					|| loc_housekeepTable.getId().indexOf(housekeepRul.getId())>=0)
			{
				return true;
			}
		}else if(HousekeepRul.TYPE_PRIVATE.equalsIgnoreCase(housekeepRul.getType())){
			if (loc_housekeepTable.getId() != null&&loc_housekeepTable.getId().indexOf(housekeepRul.getId())>=0)
			{
				return true;
			}
		}else{
			throw new Exception("config type :"+housekeepRul.getType()+" error");
		}
		return false;	
	}
	
	private File writeExportFile(HousekeepTable loc_housekeepTable,Housekeep loc_houseKeep,HusJob husJob) throws BatchJobException, Exception{
		String sql_str=this.getDeleteStr(husJob.getExecTableName(loc_housekeepTable),loc_houseKeep.getTxCode() , loc_housekeepTable.getJnlColumn(), loc_housekeepTable.getCondition());
		ExportJob exportJob=new ExportJob(this.batchAcDate,this.batchRoot,this.batchBackup,this.jobName,sql_str);
		Map<String, String> loc_map=new HashMap<String, String> ();
		loc_map.put("schema", schema);
		String filename=this.husType+"_"+loc_houseKeep.getTxCode()+"_"+husJob.getExecTableName(loc_housekeepTable);
		return exportJob.writeFile(filename, exportJob.getSqlStr(null, loc_map, sql_str));
	}
	
}
