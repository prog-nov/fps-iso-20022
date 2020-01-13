package com.forms.framework.util;

import java.io.FileInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;

import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.exception.BatchFrameworkException;

public class ResourceUtil
{

	private static String packagePath = "batchResourceManagerSetting.xml";

	private static ResourceUtil resourceManager;

	private Map<String, String> param = null;
	
	public static String RESOURCE_PROPERTIES_TYPE = "PROPERTIES";
	
	public static String RESOURCE_XML_TYPE = "XML";
	
	public static String RESOURCE_PATH_TYPE = "PATH";

	public static synchronized ResourceUtil getInstance()
			throws BatchFrameworkException
	{
		if (resourceManager == null)
		{
			resourceManager = new ResourceUtil();
		}
		return resourceManager;
	}

	public Object getResource(String name, String type) throws Exception
	{
		if(param.containsKey(name))
		{
			if(RESOURCE_PROPERTIES_TYPE.equals(type))
			{
				return getPropertiesResource(name);
			}else if(RESOURCE_XML_TYPE.equals(type))
			{
				return getRootFromXmlResource(name);
			}else if(RESOURCE_PATH_TYPE.equals(type))
			{
				return this.getPath(name);
			}else
			{
				throw new Exception("TYPE MUST BE INPUT PROPERTIES,XML OR PATH");
			}
		}else
		{
			throw new Exception(name + " HASN'T SETTING IN batchResourceManagerSetting.xml");
		}
	}

	private ResourceUtil() throws BatchFrameworkException
	{
		param = new HashMap<String, String>();
		init();
	}

	@SuppressWarnings("unchecked")
	private void init() throws BatchFrameworkException
	{
		try
		{
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(packagePath);
			URL u = null;
			if (url == null)
			{
				throw new Exception(packagePath + " is not found");
			} else
			{
				Element root = XmlUtil
						.loadRootElement(url.toURI().getPath());
				List<Element> list = root.elements();
				for (Element element : list)
				{
						u = Thread.currentThread().getContextClassLoader()
							.getResource(element.getText());
					if (u == null)
					{
						throw new Exception(element.getText() + " is not found");
					}
					param.put(element.attributeValue("name"), u.toURI().getPath());
				}
			}
		} catch (Exception ip_e)
		{
			throw new BatchFrameworkException(ip_e);
		}
	}
	
	private Properties getPropertiesResource(String name) throws Exception
	{
		String path = param.get(name);
		Properties proper = new Properties();
		proper.load(new FileInputStream(path));
		return proper;
	}
	
	private Element getRootFromXmlResource(String name) throws Exception
	{
		return XmlUtil.loadRootElement(param.get(name));
	}
	
	private String getPath(String name)
	{
		return param.get(name);
	}

	public static void main(String[] args) throws Exception
	{
		//System.out.println(ResourceUtil.getInstance().getResource(
		//		"dbProperty",ResourceUtil.RESOURCE_PROPERTIES_TYPE));
//		System.out.println(ResourceUtil.getInstance().getResource(
//				"ftpProperty",ResourceUtil.RESOURCE_PROPERTIES_TYPE));
		String s = (String) ResourceUtil.getInstance().getResource(
				"isbProperty",ResourceUtil.RESOURCE_PATH_TYPE);
		System.out.println(s);
		BatchEnvBuilder b = BatchEnvBuilder.getInstance();
		System.out.println(b.getEnv(CommonAPI.ENV_BATCH_DATA));
	}
}