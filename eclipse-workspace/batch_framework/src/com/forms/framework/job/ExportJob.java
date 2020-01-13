package com.forms.framework.job;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.util.CommonAPI;
import com.forms.framework.util.ExecCmdUtil;
import com.forms.framework.util.ResourceUtil;
import com.forms.framework.util.PatternUtil;

public class ExportJob
{

	private final String DELETE_STR = "DELETE";

	private final String SELECT_STR = "SELECT *";

	private String PATTERN_STRING;

	private Pattern PATTERN;

	private OutputStream os = null;

	private OutputStreamWriter osw = null;

	private BufferedWriter bw = null;

	private String addFlag = "N";

	public final static String fileHz = ".properties";

	private String propertyFilePath = "";

	private static String shStr = "exportSqlData";

	private String cmd;

	private String batchAcDate;

	@SuppressWarnings("unused")
	private String batchRoot;

	private String jobId;

	protected List<List<String>> loc_list = null;
	
	protected String loc_sql;
	
	protected String backfilePath="";
	
	private String dbHost;
	
	private String dbDatabaseName;
	
	private String dbUsername;
	
	private String dbPassword;
	
	private String dbSchema;
	
	public ExportJob(String batchAcDate, String batchRoot,
			String batchBackup, String jobId, List<List<String>> loc_list) throws Exception
	{
		this(batchAcDate, batchRoot, batchBackup, jobId,
				null, loc_list);		
	}

	public ExportJob(String batchAcDate, String batchRoot,
			String batchBackup, String jobId, String loc_sql) throws Exception
	{
		this(batchAcDate, batchRoot, batchBackup, jobId,
				loc_sql, null);
	}
	
	public static String getBackupFilePath() throws Exception{
		return BatchEnvBuilder.getInstance().getEnv(CommonAPI.ENV_BATCH_DBBACKUP_PATH);
	}

	private ExportJob(String batchAcDate, String batchRoot,
			String batchBackup, String jobId, String loc_sql,
			List<List<String>> ip_list) throws Exception
	{
		this.batchAcDate = batchAcDate;
		this.batchRoot = batchRoot;
		this.jobId = jobId;
		backfilePath=getBackupFilePath();
		cmd = (String) ResourceUtil.getInstance().getResource(shStr, ResourceUtil.RESOURCE_PATH_TYPE);
		this.loc_list= ip_list;
		this.loc_sql = loc_sql;
		init();
	}
	
	public ExportJob()
	{
		init();
	}

	public void init()
	{
		String backpath = backfilePath + File.separator + this.batchAcDate.replaceAll("-", "");
		File file1 = new File(backpath);
		file1.mkdirs();
		propertyFilePath = backpath + File.separator + CommonAPI.PROPERTY_PATH + File.separator;
		
		try
		{
			String url = ConnectionManager.getInstance().getUrl();
			String[] tmp = url.split(":");
			dbHost = tmp[2].replace("/", "");
			dbDatabaseName = ConnectionManager.getInstance().getDatabaseName();
			dbUsername = ConnectionManager.getInstance().getUserName();
			dbPassword = ConnectionManager.getInstance().getPassword();
			dbSchema = ConnectionManager.getInstance().getDefaultSchema();
		} catch (BatchFrameworkException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean execute(String schema) throws BatchJobException
	{
		Map<String, String> loc_map=new HashMap<String, String> ();
		loc_map.put("schema", schema);
		return this.execute(schema, null, loc_map);
	}

	public boolean execute(String schema,
			PatternUtil ip_sqlJob, Map<String, String> ip_map)
			throws BatchJobException
	{
		try
		{
			PATTERN_STRING = schema.toUpperCase() + ".\\w+";
			PATTERN = Pattern.compile(PATTERN_STRING, Pattern.DOTALL);
			if (loc_list != null)
			{
				this.backup(ip_sqlJob, ip_map);
			} else if (loc_sql != null)
			{
				this.backupOne(ip_sqlJob, ip_map, loc_sql);
			} else
			{
				throw new Exception("loc_list is null and loc_sql is null");
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		return true;
	}

	private void backup(PatternUtil in_sqlJob, Map<String, String> in_map)
			throws BatchJobException
	{
		for (List<String> loc_l : loc_list)
		{
			try
			{
				for (String loc_str : loc_l)
				{
					this.backupOne(in_sqlJob, in_map, loc_str);
				}
			} catch (Exception ip_e)
			{
				throw new BatchJobException(ip_e);
			}

		}
	}
	
	public String getSqlStr(PatternUtil in_sqlJob,Map<String, String> in_map,String loc_str) throws Exception{
		String loc_sql = "";
		if (in_sqlJob != null)
		{
			loc_sql = in_sqlJob.patternReplace(in_map, loc_str);
		} else
		{
			loc_sql = loc_str;
		}
		if (loc_sql.trim().startsWith(DELETE_STR))
		{
			loc_sql = loc_sql.replaceFirst(DELETE_STR, SELECT_STR);
			return loc_sql;
		}
		return loc_str;
	}
	
	private void backupOne(PatternUtil in_sqlJob, Map<String, String> in_map,String loc_str) throws Exception{
		String tableName = "";
		String loc_sql = "";
		File f = null;
		int result = 0;
		if (loc_str.trim().startsWith(DELETE_STR))
		{
			loc_sql = this.getSqlStr(in_sqlJob, in_map, loc_str);
			tableName = this.getTableName(loc_sql, in_map
					.get("schema"));
			f = this.writeFile(tableName, loc_sql);
			ExecCmdUtil.execCmd(new String[] { cmd, this.batchAcDate.replaceAll("-", ""), addFlag,
					jobId + "_" + tableName + "_" + System.currentTimeMillis(),
					f.getAbsolutePath(), dbHost, dbDatabaseName, dbUsername, dbPassword, dbSchema});
			if (result != 0)
			{
				throw new BatchJobException(tableName
						+ " backup fail");
			}
		}
	}

	private String getTableName(String in_sql, String schema)
	{
		Matcher loc_m = PATTERN.matcher(in_sql.trim().toUpperCase());
		if (loc_m.find())
		{
			loc_m.group();
			return loc_m.group().replaceAll(schema.toUpperCase() + ".", "");
		}
		return "";
	}

	public File writeFile(String tableName, String sql)
			throws BatchJobException
	{
		File file = new File(propertyFilePath + jobId + "_" + tableName
				+ fileHz);
		try
		{
			file.getParentFile().mkdirs();
			os = new FileOutputStream(file);
			osw = new OutputStreamWriter(os);
			bw = new BufferedWriter(osw);
			bw.write(sql);
			return file;
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		} finally
		{
			try
			{
				if (bw != null)
				{
					bw.close();
				}
				if (osw != null)
				{
					osw.close();
				}
				if (os != null)
				{
					os.close();
				}
			} catch (Exception e)
			{
				throw new BatchJobException(e);
			}
		}
	}
}
