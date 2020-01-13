package com.forms.framework;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;

import com.forms.datapipe.DataPipeWrapper;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.datatype.DataTypeWrapper;
import com.forms.framework.conf.JobConfiger;
import com.forms.framework.conf.SysConfiger;
import com.forms.framework.ctrl.ActionCondition;
import com.forms.framework.ctrl.BatchJobJnl;
import com.forms.framework.ctrl.BatchJobJnlDao;
import com.forms.framework.ctrl.BreakPoint;
import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.CommonAPI;
import com.forms.framework.util.DateUtil;
import com.forms.framework.util.ResourceUtil;
import com.forms.framework.util.SpcialUtil;
import com.forms.framework.util.PatternUtil;
import com.forms.framework.util.XmlUtil;

public class RunBatchJob
{
	private static RunBatchJob runBatchJob = null;
	
	private BatchLogger logger = null;
	
	private java.util.Date _beginTime = null;
	
	private String batchRoot = null;

	private String batchData = null;

	private String batchBackup = null;
	
	private String batchIsb = null;

	private String batchConfInfo=null;
	
	private String batchBin=null;
	
	private String clearingCode = null;
	
	private String configFile = null;
	
	private Map<String, String> optionArgs = null;
	
	private SysConfiger sysConfiger = null;
	
	private JobConfiger jobConfiger = null;
	
	private String batchAcDate = null;
	
	private java.util.Date _endTime = null;
	
	private BatchJobJnl lastJnl = null;

	private BatchJobJnl jobJnl = null;
	
	private String errorMessage = null;
	
	private String rerunFlg = null;

	private String rerunType = null;

	private BreakPoint rerunBreakPoint = null;
	
	public SpcialUtil spcialUtil=null;
	
	private String actionName = "";
	
	private boolean jobResult = false;
	
	private String resultStr="FAIL";
	
	private Map<String, String> actionConditionMap = new HashMap<String, String>();
	/**
	 * single instance
	 * 
	 * @throws BatchFrameworkException
	 */
	public static synchronized RunBatchJob getInstance()
			throws BatchFrameworkException
	{
		if (runBatchJob == null)
			runBatchJob = new RunBatchJob();
		return runBatchJob;
	}
	
	
	public boolean execute(String[] args)
			throws BatchFrameworkException
	{
		try
		{
			this.parseArgs(args);
			this.init();
			this.check();
			this.processJob();
			this.spcialUtil.removeConfigFile(this.jobConfiger.getJobId());
			return true;
		}catch (Throwable ip_e)
		{
			BatchLogger
			.getLogger(this.jobConfiger.getJobId(), this.actionName, this.getClass().toString()).error(ip_e.getMessage(),ip_e);
			throw new BatchFrameworkException(ip_e);
		} finally
		{
			this.close();
		}
	}
	
	/**
	 * 
	 * @throws BatchFrameworkException
	 */
	private RunBatchJob() throws BatchFrameworkException
	{
		this.logger = BatchLogger.getLogger("RunBatchJob", "", this.getClass().toString());
		logger.info("init begin ...");
		_beginTime = DateUtil.getSysDatetime();
		logger.info("RunBatchJob inited at " + DateUtil.dateToString(_beginTime, DateUtil.BATCH_DATETIME_FORMAT));
		
		try
		{
			// 1.Init the SysConfiger object and encrypt sys configer file.
			sysConfiger = new SysConfiger((String) ResourceUtil.getInstance()
					.getResource(BatchJobConstants.DEFAULT_SYS_CONFIG_FILE,
							ResourceUtil.RESOURCE_PATH_TYPE));
			if (sysConfiger == null)
			{
				throw new BatchFrameworkException("Null Sys Configer Object");
			}
			logger.info("sysConfiger ok");
		}catch(Exception e)
		{
			throw new BatchFrameworkException(e);
		}
		
		// 2.get connection and test
		List<Element> loc_dbEleList = sysConfiger.getSubElementsByPath("system-config.database-list");
		if (loc_dbEleList != null)
		{
			for (int loc_i = 0; loc_i < loc_dbEleList.size(); loc_i++)
			{
				Element loc_dbEle = loc_dbEleList.get(loc_i);
				Properties loc_prop = null;
				if (loc_dbEle.elementText("property-resource") == null)
				{
					loc_prop = new Properties();
					loc_prop.put("databaseName", loc_dbEle
							.elementText("database-name"));
					loc_prop.put("url", loc_dbEle.elementText("url"));
					loc_prop.put("driverClassName", loc_dbEle
							.elementText("driver"));
					loc_prop.put("username", loc_dbEle.elementText("username"));
					loc_prop.put("password", sysConfiger.decrypt(loc_dbEle
							.elementText("password")));
					loc_prop.put("defaultSchema", loc_dbEle
							.elementText("default-schema"));
				} else
				{
					try
					{
						ResourceUtil loc_resourceManager = ResourceUtil
								.getInstance();
						loc_prop = (Properties) loc_resourceManager
								.getResource(loc_dbEle
										.elementText("property-resource"), ResourceUtil.RESOURCE_PROPERTIES_TYPE);
						loc_prop.put("databaseName", loc_prop
								.getProperty("databaseName"));
						loc_prop.put("password", sysConfiger.decrypt(loc_prop
								.getProperty("password")));
						loc_prop.put("defaultSchema", loc_prop
								.getProperty("defaultSchema"));
					} catch (Exception ip_ex)
					{
						throw new BatchFrameworkException(ip_ex);
					}
				}
				ConnectionManager.registerDatabase(loc_prop);
				logger.info("register database:"
						+ loc_prop.getProperty("databaseName"));
			}
		}
		Element loc_globleEle = sysConfiger.getGlobleElement();
		if (loc_globleEle != null)
		{
			ConnectionManager.setDefaultDatabase(loc_globleEle.elementText("default-database"));
		}
		Connection loc_testConn = null;
		try
		{
			loc_testConn = ConnectionManager.getInstance().getConnection();
			CommonAPI.schema = ConnectionManager.getInstance().getDefaultSchema();
		} catch (Exception ip_ex)
		{
			throw new BatchFrameworkException(ip_ex);
		} finally
		{
			if (loc_testConn != null)
			{
				try
				{
					loc_testConn.close();
				} catch (SQLException e)
				{
					throw new BatchFrameworkException(e);
				}
			}
		}
		logger.info("default-database:"
				+ loc_globleEle.elementText("default-database"));
		
		try
		{
			// 3. Init Parameters
			
			Map<String, String> env_list = BatchEnvBuilder.getInstance().getEnvList();
			batchRoot = env_list.get(CommonAPI.ENV_BATCH_ROOT);
			batchData = env_list.get(CommonAPI.ENV_BATCH_DATA);
			batchBackup = env_list.get(CommonAPI.ENV_BATCH_BACKUP);
			batchConfInfo=env_list.get(CommonAPI.ENV_BATCH_CONFINFO);
			batchBin = env_list.get(CommonAPI.ENV_BATCH_BIN);
			batchIsb = env_list.get(CommonAPI.ENV_BATCH_ISB);
			clearingCode = env_list.get(CommonAPI.ENV_CLEARINGCODE);
			optionArgs = BatchEnvBuilder.getInstance().getParamenterMap();
			
			// 1.Init the SysConfiger object and encrypt sys configer file.
			sysConfiger = new SysConfiger((String) ResourceUtil.getInstance()
					.getResource(BatchJobConstants.DEFAULT_SYS_CONFIG_FILE,
							ResourceUtil.RESOURCE_PATH_TYPE));
			if (sysConfiger == null)
			{
				throw new BatchFrameworkException("Null Sys Configer Object");
			}
			logger.info("sysConfiger ok");
		}catch(Exception e)
		{
			throw new BatchFrameworkException(e);
		}
		
		// 4. Init DataPip
		try
		{
			DataPipeConfigFactory.init(batchConfInfo);
			DataTypeWrapper.init((String) ResourceUtil.getInstance()
					.getResource(CommonAPI.DATA_PIPE_DATA_TYPE_PATH, 
							ResourceUtil.RESOURCE_PATH_TYPE));
		}catch(Exception e)
		{
			throw new BatchFrameworkException(e);
		}
	}
	
	/**
	 * 
	 * @param a_args
	 * @throws Exception 
	 */
	private void parseArgs(String[] a_args)
			throws Exception
	{
		logger.info("parseArgs begin ...");
		logger.info("a_args.length=" + a_args.length);
		
		if (a_args == null || a_args[0] == null)
		{
			throw new BatchFrameworkException("Parameter for config file is NULL");
		}
		configFile = batchConfInfo + File.separator + a_args[0];
		
		if (a_args.length > 1)
		{
			for (int loc_i = 0; loc_i < a_args.length; loc_i++)
			{
				// rerunFlg
				if (BatchJobConstants.JOB_PARAMETER_TYPE_RUNFLAG.equals(a_args[loc_i]))
				{
					optionArgs.put("rerunFlg", BatchJobConstants.JOB_RERUN_MANDATORY);
				}
				// busDate
				else if (BatchJobConstants.JOB_PARAMETER_TYPE_BATCHACDATE.equals(a_args[loc_i]))
				{
					if (loc_i >= a_args.length - 1)
					{
						throw new BatchFrameworkException("No date value for args -D");
					}
					try
					{
						if (DateUtil.stringToDate(a_args[loc_i + 1]) == null)
						{
							throw new BatchFrameworkException("Wrong date value for args -D:" + a_args[loc_i + 1]);
						}
					} catch (Exception ip_ex)
					{
						throw new BatchFrameworkException("Wrong date value for args -D:" + ip_ex.getMessage());
					}
					optionArgs.put("batchAcDate", a_args[loc_i + 1]);
				}else if (BatchJobConstants.JOB_PARAMETER_TYPE_SERVER.equals(a_args[loc_i]))
				{
					if (loc_i >= a_args.length - 1)
					{
						throw new BatchFrameworkException("No date value for args -S");
					}
					try
					{
						if (DateUtil.stringToDate(a_args[loc_i + 1]) == null)
						{
							throw new BatchFrameworkException("Wrong date value for args -S:" + a_args[loc_i + 1]);
						}
					} catch (Exception ip_ex)
					{
						throw new BatchFrameworkException("Wrong date value for args -S:" + ip_ex.getMessage());
					}
					optionArgs.put("serverDate", a_args[loc_i + 1]);
				}else if (BatchJobConstants.JOB_PARAMETER_TYPE_PARAMETER.equals(a_args[loc_i]))
				{
					if (loc_i >= a_args.length - 1)
					{
						throw new BatchFrameworkException("No parameter value for args -P");
					}
					String[] loc_str =  a_args[loc_i + 1].split(",");
					if(null != loc_str)
					{
						for(String loc_tmp : loc_str)
						{
							optionArgs.put(loc_tmp.substring(0, loc_tmp.indexOf("=")), 
									loc_tmp.substring(loc_tmp.indexOf("=") + 1));
						}
					}
				}
				// configFile
				else
				{
					continue;
				}
			}
			BatchEnvBuilder.getInstance().setParamenterMap(optionArgs);
		}
		logger.info("configFile=" + configFile);		
		logger.info("parseArgs end");
	}
	
	/**
	 * 1.Init the job runDate object. <br>
	 * 2.Init the SysConfiger object and encrypt sys configer file.<br>
	 * 3.Init the JobConfiger object.<br>
	 * 3.convert the direct password to encoding password and save back to
	 * system config file.<br>
	 * 4.test db connection if fail>
	 * 5.create Jnl info<br>
	 * 6.process rerunFlg<br>
	 * 7.process rerunType<br>
	 * 8.create rerunBreakPoint
	 */
	private void init() throws BatchFrameworkException
	{			
		// 5.Init the JobConfiger object.
		jobConfiger = new JobConfiger(configFile);
		if (jobConfiger == null)
		{
			throw new BatchFrameworkException("Null Job Configer file");
		}
		logger=BatchLogger
		.getLogger(jobConfiger.getJobId(), "", this.getClass().toString());
		logger.info("jobConfiger ok");
		
		// 6.Init the job runDate object.
		try
		{
			if (optionArgs.get("batchAcDate") == null)
			{
				batchAcDate = BatchEnvBuilder.getInstance().getParamenter(CommonAPI.BATCH_AC_DATE);
			} else
			{
				batchAcDate = optionArgs.get("batchAcDate");
			}
			logger.info("batchAcDate=" + batchAcDate);
		}catch(Exception e)
		{
			throw new BatchFrameworkException(e);
		}
		
		logger.info("rerunFlg=" + optionArgs.get("rerunFlg"));		
		// 7.Retrive last jnl and create a new jnl
		BatchJobJnlDao loc_jnlDao = new BatchJobJnlDao();
		lastJnl = new BatchJobJnl();
		jobJnl = new BatchJobJnl();
		jobJnl.setBatchAcDate(batchAcDate);
		jobJnl.setJobId(jobConfiger.getJobId());
		jobJnl.setJobStat(BatchJobConstants.JOB_STATUS_PROCESSING);
		jobJnl.setResult(BatchJobConstants.JOB_RESULT_FAIL);
		jobJnl.setStartTs(DateUtil.getSysDatetime());
		jobJnl.setEndTs(null);
		jobJnl.setBreakPoint(null);
		try
		{
			jobJnl.setActionJnl("["+InetAddress.getLocalHost().getHostAddress()+"]");
		} catch (UnknownHostException e)
		{
			throw new BatchFrameworkException(e);
		}
		lastJnl = loc_jnlDao.retrieveLastJnl(jobJnl.getBatchAcDate(), jobJnl.getJobId());
		if (lastJnl == null)
		{
			jobJnl.setJnlSeq(1);
		} else
		{
			jobJnl.setJnlSeq(lastJnl.getJnlSeq() + 1);
			errorMessage = lastJnl.getBreakPoint();
		}
		loc_jnlDao.insert(jobJnl);
		logger.info("jobJnl ok");
		logger.info("jobId=" + jobJnl.getJobId());
		// 8.process rerunFlg
		if (BatchJobConstants.JOB_RERUN_MANDATORY.equals(optionArgs
				.get("rerunFlg")))
		{
			rerunFlg = BatchJobConstants.JOB_RERUN_MANDATORY;
		} else
		{
			if (lastJnl == null
					|| BatchJobConstants.JOB_RESULT_SUCCESS
							.equalsIgnoreCase(lastJnl.getResult()))
			{
				rerunFlg = BatchJobConstants.JOB_RERUN_UNNEEDED;
			} else
			{
				rerunFlg = BatchJobConstants.JOB_RERUN_NEEDED;
			}
		}
		logger.info("rerunFlg=" + rerunFlg);
		// 9.process rerunType
		if (BatchJobConstants.JOB_RERUN_TYPE_BREAKPOINT
				.equalsIgnoreCase(jobConfiger.getRerunType()))
		{
			rerunType = BatchJobConstants.JOB_RERUN_TYPE_BREAKPOINT;
		} else if (BatchJobConstants.JOB_RERUN_TYPE_FROMBEGIN
				.equalsIgnoreCase(jobConfiger.getRerunType()))
		{
			rerunType = BatchJobConstants.JOB_RERUN_TYPE_FROMBEGIN;
		} else
		{
			throw new BatchFrameworkException("retun-type:"
					+ jobConfiger.getRerunType() + " is wrong");
		}
		logger.info("rerunType=" + rerunType);
		// 10.create rerunBreakPoint
		if (BatchJobConstants.JOB_RERUN_NEEDED.endsWith(rerunFlg))
		{
			if (lastJnl == null)
			{
				throw new BatchFrameworkException(
						"Rerun is needed and supported while last jnl is null");
			}
			rerunBreakPoint = new BreakPoint(lastJnl.getBatchAcDate(),
					lastJnl.getJobId(), lastJnl.getJnlSeq());
			rerunBreakPoint.parseBreakPoint(lastJnl.getBreakPoint());
			logger.info("rerunBreakPoint ok");
		}
		// 11. create Special job
		spcialUtil = new SpcialUtil(this.jobConfiger.getJobId(), this.batchAcDate);
		logger.info("init end");
	}
	
	private boolean check() throws BatchFrameworkException
	{
		logger.info("check begin ...");
		// 1.check execute time
		String loc_freqToday = DateUtil.parseFrequence(batchAcDate);
		if (loc_freqToday == null)
		{
			throw new BatchFrameworkException(
					"Can not get batch frequence for date:"
							+ batchAcDate.toString());
		}
		logger.info("freqToday=" + loc_freqToday);
		if (loc_freqToday.indexOf(jobConfiger.getRunFrequency()) < 0)
		{
			throw new BatchFrameworkException("run-frequency config error");
		}

		logger.info("run occasion check ok");
		// re executee same batchAcDate+jobId+success

		if (lastJnl != null) 
		{
			/*if (BatchJobConstants.JOB_RESULT_SUCCESS.equals(lastJnl
					.getResult()))
			{
				if (this.batchAcDate.equals(lastJnl.getBatchAcDate()))
				{
					if (!BatchJobConstants.JOB_RERUN_MANDATORY
							.equals(this.rerunFlg))
					{
						throw new BatchFrameworkException("duplicate run!");
					}
				}
			}*/
			logger.info("duplicate run check ok");
			if (BatchJobConstants.JOB_STATUS_PROCESSING.equals(lastJnl.getJobStat()))
			{
				throw new BatchFrameworkException("job "+jobConfiger.getJobId()+" is running!");
			}
		}
		logger.info("check end");
		return true;
	}
	
	/**
	 * process a job and it's all actions<br>
	 * 1. normal process<br>
	 * 2. rurun
	 */
	private void processJob() throws Exception
	{
		logger.info("processJob begin ...");
		try
		{
			// get actions from job config
			ArrayList<Element> loc_actionsEleList = (ArrayList<Element>) jobConfiger
					.getSubElementsByPath("job-config.action-flow");
			if (loc_actionsEleList == null)
			{
				throw new BatchJobException(
						"No action found in JobConfiger Object");
			}
			loc_actionsEleList=spcialUtil.addSpcialAction(loc_actionsEleList);
			// from begin process
			if (rerunType.equals(BatchJobConstants.JOB_RERUN_TYPE_FROMBEGIN)
					|| !BatchJobConstants.JOB_RERUN_NEEDED.equals(rerunFlg))
			{
				for (int i = 0; i < loc_actionsEleList.size(); i++)
				{
					Element loc_actionEle = loc_actionsEleList.get(i);
					processAction(loc_actionEle);
				}
			}
			// rerun process
			else
			{
				boolean loc_rerunEnd = false;
				String loc_lastActionName = rerunBreakPoint.getActionName();
				if (loc_lastActionName == null)
				{
					loc_lastActionName = loc_actionsEleList.get(0).elementText(
							"action-name");
				}
				for (int loc_i = 0; loc_i < loc_actionsEleList.size(); loc_i++)
				{
					Element loc_actionEle = loc_actionsEleList.get(loc_i);
					if (loc_lastActionName.equals(loc_actionEle
							.elementText("action-name")))
					{
						rerunActions(loc_actionsEleList, loc_actionEle);
						loc_rerunEnd = true;
					} else
					{
						if (loc_rerunEnd)
						{
							processAction(loc_actionEle);
						} else
						{
							continue;
						}
					}
				}
			}
			// true=all action true
			jobResult = true;
			resultStr="SUCCESS";
		} catch (Exception ip_e)
		{
			jobResult = false;
			resultStr="FAIL";
			logger.info("processJob failed");
			throw ip_e;
		} finally
		{

		}
		logger.info("processJob end");
	}
	
	/**
	 * normal process a action
	 */
	private void processAction(Element ip_a_actionEle) throws Exception
	{
		String loc_actionName = ip_a_actionEle.elementText("action-name");
		String loc_actionType = ip_a_actionEle.elementText("action-type");
		Element loc_actionCondition = ip_a_actionEle.element("action-condition");
		actionName = loc_actionName;
		if (loc_actionCondition != null)
		{
			executeActionCondition(loc_actionCondition);
			if ("false".equals(actionConditionMap.get(loc_actionCondition)))
			{
				return;
			}
		}
		logger.info("processAction:action-name=" + loc_actionName
				+ "action-type=" + loc_actionType);
		if (loc_actionName == null || loc_actionName.length() < 1)
		{
			throw new BatchJobException(
					"Action-name is null in JobConfiger Object");
		}
		if (loc_actionType == null || loc_actionType.length() < 1)
		{
			throw new BatchJobException(
					"Action-type is null in JobConfiger Object");
		}

		// save ActionJnl and BreakPoint
		BatchJobJnlDao loc_jnlDao = new BatchJobJnlDao();
		String loc_actionJnl = jobJnl.getActionJnl();
		if (loc_actionJnl == null)
		{
			loc_actionJnl = ip_a_actionEle.elementText("action-name");
		} else
		{
			loc_actionJnl = loc_actionJnl + "->"
					+ ip_a_actionEle.elementText("action-name");
		}
		loc_actionJnl = loc_actionJnl + "...";
		jobJnl.setActionJnl(loc_actionJnl);
		loc_jnlDao.updateActionJnl(jobJnl);
		BreakPoint loc_breakPoint = new BreakPoint(batchAcDate, jobJnl
				.getJobId(), jobJnl.getJnlSeq(), loc_actionName);
		loc_breakPoint.save("breakpoint", "begin...");

		// Run all kinds of action
		try
		{
			// For ActionType:JavaSysCall
			if (BatchJobConstants.JOB_ACTION_TYPE_JAVASYSCALL
					.equalsIgnoreCase(loc_actionType))
			{
				runJavaSysCall(ip_a_actionEle, loc_breakPoint);
			}
			// For ActionType:JavaJob
			else if (BatchJobConstants.JOB_ACTION_TYPE_JAVAACTION
					.equalsIgnoreCase(loc_actionType))
			{
				runJavaAction(ip_a_actionEle, loc_breakPoint);
			}
			// For ActionType:DataPip
			else if (BatchJobConstants.JOB_ACTION_TYPE_DATAPIP
					.equalsIgnoreCase(loc_actionType))
			{
				runDataPip(ip_a_actionEle, loc_breakPoint);
			}
			// For ActionType:SysCall
			else if (BatchJobConstants.JOB_ACTION_TYPE_SYSCALL
					.equalsIgnoreCase(loc_actionType))
			{
				runSysCall(ip_a_actionEle, loc_breakPoint);
			} 
			// For ActionType:CMDAction
			else if (BatchJobConstants.JOB_ACTION_TYPE_CMDACTION
					.equalsIgnoreCase(loc_actionType))
			{
				runJavaAction(ip_a_actionEle, loc_breakPoint);
			}
			else
			{
				throw new BatchJobException("action-type:" + loc_actionType
						+ " is wrong");
			}
		} catch (Exception ip_ex)
		{
			loc_actionJnl = loc_actionJnl + "failed";
			jobJnl.setActionJnl(loc_actionJnl);
			loc_jnlDao.updateActionJnl(jobJnl);
			String loc_errMsg = "";
			String loc_breakpointCommit = CommonAPI.breakPointMap
					.get(CommonAPI.BREAKPOINT_COMMIT);
			if (loc_breakpointCommit != null)
			{
				loc_errMsg += ";" + loc_breakpointCommit + ";";
			}
			loc_breakPoint.save("breakpoint", "failed" + loc_errMsg);
			throw new BatchJobException(ip_ex);
		} finally
		{
			if (CommonAPI.breakPointMap
					.containsKey(CommonAPI.BREAKPOINT_COMMIT))
				CommonAPI.breakPointMap
						.remove(CommonAPI.BREAKPOINT_COMMIT);
		}
		loc_actionJnl = loc_actionJnl + "success";
		jobJnl.setActionJnl(loc_actionJnl);
		loc_jnlDao.updateActionJnl(jobJnl);
		loc_breakPoint.save("breakpoint", "success");
	}

	private boolean executeActionCondition(Element ip_a_Condition) throws Exception
	{
		String conditionType = ip_a_Condition.attributeValue("type");
		if(BatchJobConstants.ACTION_CONDITION_TYPE_SQL.equals(conditionType))
		{
			String databaseName = ip_a_Condition.elementTextTrim("databaseName");
			String sql_str = ip_a_Condition.elementTextTrim("sql");
			String isFile = ip_a_Condition.element("sql").attributeValue("isFile");
			if(actionConditionMap.containsKey(conditionType + "-->" + databaseName + "-->" + sql_str))
			{
				return Boolean.valueOf(actionConditionMap.get(conditionType + "-->" + databaseName + "-->" + sql_str));
			}else
			{
				String sql = "";
				if("Y".equalsIgnoreCase(isFile))
				{
						Element root = XmlUtil.loadRootElement(this.batchConfInfo + File.separator + sql_str);
						sql = root.getTextTrim();
				}
				if(null == databaseName || "".equals(databaseName))
				{
					databaseName = ConnectionManager.getDefaultDatabase();
				}
				Connection conn = null;
				try
				{
					ConnectionManager cManager = ConnectionManager.getInstance(databaseName);
					optionArgs.put("schema", cManager.getDefaultSchema());
					conn = ConnectionManager.getInstance(databaseName).getConnection();
					PatternUtil replacesql = new PatternUtil(this.batchAcDate);
					sql = replacesql.patternReplace(optionArgs, sql);
					Object[] result = EntityManager.queryArray(conn, sql);
					actionConditionMap.put(conditionType + "-->" + databaseName + "-->" + sql_str, (String) result[0]);
					return Boolean.valueOf((String) result[0]);
				}catch(Exception e)
				{
					throw e;
				}finally
				{
					if(conn!=null)
						conn.close();
				}
			}
		}else
		{
			String clazz = ip_a_Condition.elementTextTrim("clazz");
			if(actionConditionMap.containsKey(clazz))
			{
				return Boolean.valueOf(actionConditionMap.get(clazz));
			}else
			{
				ActionCondition loc_condition = (ActionCondition) Class
						.forName(clazz).newInstance();
				Map<String, String> loc_argsMap = new HashMap<String, String>();
				loc_argsMap.put("batchAcDate", batchAcDate);
				String loc_conditionResult = String.valueOf(loc_condition
						.checkCondition(loc_argsMap));
				actionConditionMap.put(clazz, loc_conditionResult);
				return Boolean.valueOf(loc_conditionResult);
			}
		}
	}
	
	/**
	 * rerun one action and all front actions impacted by this action
	 */
	private void rerunActions(ArrayList<Element> ip_a_actionsEleList,
			Element ip_a_actionEle) throws Exception
	{
		logger.info("rerunAction:" + ip_a_actionEle.elementText("action-name"));
		String loc_rerunDependOn = ip_a_actionEle
				.elementText("rerun-depend-on");
		if (loc_rerunDependOn != null && loc_rerunDependOn.trim().length() > 0)
		{
			String[] loc_dependOns = loc_rerunDependOn.split(",");
			for (int loc_i = 0; loc_i < loc_dependOns.length; loc_i++)
			{
				if (loc_dependOns[loc_i] == null
						|| loc_dependOns[loc_i].trim().length() < 1)
					continue;
				for (int loc_j = 0; loc_j < ip_a_actionsEleList.size(); loc_j++)
				{
					Element loc_dependOnActionEle = ip_a_actionsEleList
							.get(loc_j);
					if (loc_dependOns[loc_i].equals(loc_dependOnActionEle
							.elementText("action-name")))
					{
						rerunActions(ip_a_actionsEleList, loc_dependOnActionEle);
					}
				}
			}
		}
		processAction(ip_a_actionEle);
	}
	
	/**
	 * processAction()->runJavaSysCall()
	 */
	private void runJavaSysCall(Element ip_a_action,
			BreakPoint ip_a_breakPoint) throws BatchFrameworkException
	{
		try
		{
			runJavaAction(ip_a_action, ip_a_breakPoint);
		} catch (Exception ip_ex)
		{
			throw new BatchFrameworkException(ip_ex);
		}
	}

	/**
	 * processAction()->runJavaJob()
	 */
	private void runJavaAction(Element ip_a_action,
			BreakPoint ip_a_breakPoint) throws Exception
	{
		BatchBaseJob loc_jobAction = null;
		try
		{
			String loc_processor = ip_a_action.elementText("processor");
			loc_jobAction = (BatchBaseJob) Class.forName(loc_processor)
					.newInstance();
			loc_jobAction.setJobName(jobConfiger.getJobId());
			loc_jobAction.setActionName(ip_a_action.elementText("action-name"));
			loc_jobAction.setBatchAcDate(batchAcDate);
			loc_jobAction.setSysConfiger(sysConfiger);
			loc_jobAction.setJobConfiger(jobConfiger);
			loc_jobAction.setJobJnl(jobJnl);
			loc_jobAction.setRerunFlg(rerunFlg);
			loc_jobAction.setRerunBreakPoint(rerunBreakPoint);
			loc_jobAction.setBreakPoint(ip_a_breakPoint);
			loc_jobAction.setBatchLogger(BatchLogger
					.getLogger(loc_jobAction.getJobName(), loc_jobAction
							.getActionName(), loc_processor));
			loc_jobAction.setBatchData(batchData);
			loc_jobAction.setBatchIsb(batchIsb);
			loc_jobAction.setBatchRoot(batchRoot);
			loc_jobAction.setBatchBackup(batchBackup);
			loc_jobAction.setBatchConfInfo(batchConfInfo);
			loc_jobAction.setBatchBin(batchBin);
			loc_jobAction.setActionElement(ip_a_action);
			loc_jobAction.setClearingCode(clearingCode);
			loc_jobAction.init();
			loc_jobAction.check();
			jobResult = loc_jobAction.execute();

		} catch (Exception ip_ex)
		{
			throw ip_ex;
		} finally
		{
			if (loc_jobAction != null)
			{
				loc_jobAction.close();
			}
		}
	}

	/**
	 * processAction()->runDataPip()
	 */
	private void runDataPip(Element ip_a_action, BreakPoint ip_a_breakPoint)
			throws Exception
	{
		try
		{
			String loc_configFile = ip_a_action.elementText("config-file");
			String loc_inputFilePath = ip_a_action
					.elementText("input-file-path");
			String loc_outputFilePath = ip_a_action
					.elementText("output-file-path");
			String loc_logLevel = ip_a_action.elementText("log-level");
			Map<String, String> loc_parameters = new HashMap<String, String>();
			loc_parameters = BatchEnvBuilder.getInstance().getParamenterMap();
			loc_parameters.put("log_level", loc_logLevel);
			loc_parameters.put("inputFilePath", batchData + File.separator
					+ loc_inputFilePath);
			loc_parameters.put("outputFilePath", batchData + File.separator
					+ loc_outputFilePath);
			loc_parameters.put("acDate", this.batchAcDate);
			this.parseValidateDate(ip_a_action, loc_parameters);
			
			loc_parameters.put("compareConfigFile", batchConfInfo + File.separator
					+ ip_a_action.elementText("compare-config-file"));
			
			loc_parameters.put("jobPath", configFile.substring(0, 
					configFile.replace("\\", "/").lastIndexOf("/") + 1));
			
			loc_parameters.put("dateFormat", DateUtil.BATCH_DATE_FORMAT);
			loc_parameters.put("jobId", this.jobConfiger.getJobId());
			loc_parameters.put("actionName", ip_a_action.elementText("action-name"));
			loc_parameters.put("isAddInput", spcialUtil.isAddInput(ip_a_action.elementText("action-name")));
			if (errorMessage != null)
			{
				String[] loc_messageValues = errorMessage.split(";");
				if (loc_messageValues.length > 1)
				{
					CommonAPI.breakPointMap.put(
							CommonAPI.BREAKPOINT_COMMIT,
							CommonAPI.BREAKPOINT_COMMIT
									+ ":"
									+ loc_messageValues[1]
											.substring(loc_messageValues[1]
													.indexOf(":") + 1));
				}
			}
			DataPipeWrapper.transfer(loc_configFile, loc_parameters);
		} catch (Exception ip_ex)
		{
			throw ip_ex;
		}
	}
	
	private void parseValidateDate(Element ip_a_action,
			Map<String, String> loc_parameters) throws Exception
	{
		String validate_date_type = ip_a_action
				.elementText("validate-acdate-type");
		String validate_date = ip_a_action.elementText("validate-acdate");
		if (validate_date_type == null || "".equals(validate_date_type))
		{
			loc_parameters.put("validateDate", this.batchAcDate);
		} else
		{
			if (BatchJobConstants.FILE_VALIDATE_TYPE_BATCH.equalsIgnoreCase(validate_date_type))
			{
				if (validate_date == null || "".equals(validate_date))
				{
					loc_parameters.put("validateDate", this.batchAcDate);
				} else
				{
					loc_parameters.put("validateDate", DateUtil.addAcDays(
							this.batchAcDate, DateUtil.BATCH_DATE_FORMAT,
							Integer.parseInt(validate_date)));
				}				
			} else if (BatchJobConstants.FILE_VALIDATE_TYPE_ONLINE
					.equalsIgnoreCase(validate_date_type))
			{
				String acdt;
				if (optionArgs.get("batchAcDate") == null)
				{
					acdt = DateUtil.getAcDate();
				} else
				{
					acdt = optionArgs.get("batchAcDate");
				}
				if (validate_date == null || "".equals(validate_date))
				{
					loc_parameters.put("validateDate", acdt);
				} else
				{
					loc_parameters.put("validateDate", DateUtil.addAcDays(
							acdt, DateUtil.BATCH_DATE_FORMAT, Integer
									.parseInt(validate_date)));
				}
			} else if (BatchJobConstants.FILE_VALIDATE_TYPE_SYSTEM
					.equalsIgnoreCase(validate_date_type))
			{
				String acdt;
				if (optionArgs.get("batchAcDate") == null)
				{
					acdt = DateUtil.getSysDate();
				} else
				{
					acdt = optionArgs.get("batchAcDate");
				}
				if (validate_date == null || "".equals(validate_date))
				{
					loc_parameters.put("validateDate", acdt);
				} else
				{
					loc_parameters.put("validateDate", DateUtil.addDays(
							acdt, DateUtil.BATCH_DATE_FORMAT, Integer
									.parseInt(validate_date)));
				}				
			} else if (BatchJobConstants.FILE_VALIDATE_TYPE_N.equalsIgnoreCase(validate_date_type))
			{

			} else
			{
				throw new Exception("validate-acdate-type:"
						+ validate_date_type + "config error");
			}
		}
	}
	
	/*
	 * processAction()->runSysCall()
	 */
	private void runSysCall(Element ip_a_action, BreakPoint ip_a_breakPoint)
			throws Exception
	{
		try
		{

		} catch (Exception ip_ex)
		{
			throw ip_ex;
		}
	}
	
	/**
	 * job execute end system clear update Job execute result
	 */
	private void close() throws BatchFrameworkException
	{
		logger.info("close begin ...");
		try
		{
			if(jobJnl != null)
			{
				BatchJobJnlDao loc_jnlDao = new BatchJobJnlDao();
				jobJnl.setJobStat(BatchJobConstants.JOB_STATUS_END);
				jobJnl
					.setResult(this.jobResult == true ? BatchJobConstants.JOB_RESULT_SUCCESS
							: BatchJobConstants.JOB_RESULT_FAIL);
				jobJnl.setEndTs(DateUtil.getSysDatetime());
				loc_jnlDao.close(jobJnl);
			}
		} catch (Exception ip_ex)
		{
			logger.error("", ip_ex);
		}
		_endTime = DateUtil.getSysDatetime();
		logger.info("RunBatchJob closed at "
				+ DateUtil.dateToString(_endTime,
						DateUtil.BATCH_DATETIME_FORMAT));
		logger.info(this.jobConfiger.getJobId() + " " +resultStr +", used "
				+ (_endTime.getTime() - _beginTime.getTime()) / 1000
				+ " second");
	}
}
