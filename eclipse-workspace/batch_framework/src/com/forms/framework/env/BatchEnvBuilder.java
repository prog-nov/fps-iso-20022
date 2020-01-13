package com.forms.framework.env;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.forms.framework.log.BatchLogger;
import com.forms.framework.util.CommonAPI;
import com.forms.framework.util.ResourceUtil;
import com.forms.framework.util.ValidateUtil;

public class BatchEnvBuilder
{
	private final String ENV_FILE_NAME = "SystemEnvParameters";
	
	private static BatchEnvBuilder envBuilder;
	
	private BatchLogger batchLogger;
	
	private String isPrint = "N";
	
	private Map<String, String> env_list = null;
	
	private Map<String, String> parameter_map = null;
	
	private static String CLASSPATH = "classpath";
	
	private static String PARAMETERS = "parameters";
	
	private static String ISPRINT = "isprint";
	
	public static synchronized BatchEnvBuilder getInstance() throws Exception
	{
		if(null == envBuilder)
		{
			envBuilder = new BatchEnvBuilder();
		}
		return envBuilder;
	}
	
	private BatchEnvBuilder() throws Exception
	{
		batchLogger = BatchLogger.getLogger("BatchEnvBuilder", "", BatchEnvBuilder.class);
		env_list = new HashMap<String, String>();
		parameter_map = new HashMap<String, String>();
		init();
		check();
		if("Y".equalsIgnoreCase(isPrint))
		{
			print();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void init() throws Exception
	{
		Element root = (Element) ResourceUtil.getInstance().getResource(ENV_FILE_NAME, ResourceUtil.RESOURCE_XML_TYPE);
		isPrint = root.elementTextTrim(ISPRINT);
		List<Element> envlist = root.element(CLASSPATH).elements();
		String env_name = "";
		String env_type = "";
		String env_value = "";
		if(null != envlist)
		{
			for (Element element : envlist)
			{
				env_name = element.attributeValue("name");
				env_type = element.attributeValue("isEnv");
				env_value = element.getTextTrim();
				if("Y".equalsIgnoreCase(env_type))
				{
					env_value = System.getenv(env_value);
				}
				env_list.put(env_name, env_value);
			}
		}
		
		List<Element> paralist = root.element(PARAMETERS).elements();
		String para_name = "";
		String para_type = "";
		String para_value = "";
		if(null != paralist)
		{
			for (Element element : paralist)
			{
				para_name = element.attributeValue("name");
				para_type = element.attributeValue("isFunction");
				para_value = element.getTextTrim();
				if("Y".equalsIgnoreCase(para_type))
				{
		            int methodIndex = para_value.lastIndexOf(".");
		            String className = para_value.substring(0, methodIndex);
		            String methodName = para_value.substring(methodIndex + 1);
		            Class<?> clazz = Class.forName(className);
		            Method method = clazz.getMethod(methodName);
		            para_value = (String) method.invoke(null);
				}
				parameter_map.put(para_name, para_value);
			}
		}
	}
	
	private void check() throws Exception
	{
		if (ValidateUtil.isEmpty(env_list.get(CommonAPI.ENV_BATCH_ROOT)))
		{
			throw new Exception(
					"System variable BATCH_ROOT is null");
		}
		if (ValidateUtil.isEmpty(env_list.get(CommonAPI.ENV_BATCH_DATA)))
		{
			throw new Exception(
					"System variable BATCH_DATA is null");
		}
		if (ValidateUtil.isEmpty(env_list.get(CommonAPI.ENV_BATCH_BACKUP)))
		{
			throw new Exception(
					"System variable BATCH_BACKUP is null");
		}
		if (ValidateUtil.isEmpty(env_list.get(CommonAPI.ENV_BATCH_CONFINFO)))
		{
			throw new Exception(
					"System variable BATCH_CONFINFO is null");
		}
	}
	
	private void print()
	{
		if(null != env_list)
		{
			Iterator<String> iter = env_list.keySet().iterator();
			String key = "";
			while(iter.hasNext())
			{
				key = iter.next();
				batchLogger.info(key + "=" + env_list.get(key));
			}
		}
		
		if(null != parameter_map)
		{
			Iterator<String> iter = parameter_map.keySet().iterator();
			String key = "";
			while(iter.hasNext())
			{
				key = iter.next();
				batchLogger.info(key + "=" + parameter_map.get(key));
			}
		}
	}
	
	public String getEnv(String key)
	{
		return env_list.get(key);
	}
	
	public Map<String, String> getEnvList()
	{
		return env_list;
	}
	
	public void setEnvList(Map<String, String> env_list)
	{
		this.env_list = env_list;
	}
	
	public String getParamenter(String key)
	{
		return parameter_map.get(key);
	}
	
	public Map<String, String> getParamenterMap()
	{
		return parameter_map;
	}
	
	public void setParamenterMap(Map<String, String> parameter_map)
	{
		this.parameter_map = parameter_map;
	}
}
