package com.forms.datapipe.headfoot;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.forms.framework.util.StringUtil;

/**
 * headfoot parse class
 * @author ahnan
 * createDate:2011-04-10
 * updateDate:2011-04-28
 */
public class HeadFootInit 
{
	private static final String fileName = "head-foot-config.xml";
	
	public Map<String, List<HeadFootBean>> map = new HashMap<String, List<HeadFootBean>>();
	
	private static HeadFootInit instance;
	
	private static String synObj = "1";
	
	private HeadFootInit()
	{
	}
	
	public static HeadFootInit getInstance()
	{
		if (instance == null )
		{
			synchronized (synObj)
			{
				if (instance == null)
				{
					instance = new HeadFootInit();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * parse init
	 * @param fileName
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void init(String ip_jobPath) throws Exception 
	{
		SAXReader loc_sr = null;
		
		try
		{
			File loc_file = new File(ip_jobPath + fileName);
			if (!loc_file.exists())
				throw new Exception("config file can not be found");
			
			loc_sr = new SAXReader();
			Document loc_doc = loc_sr.read(loc_file);
			Element loc_root = loc_doc.getRootElement();
			
			List<Element> loc_headFootList = loc_root.elements("head-foot");
			for (Element loc_e : loc_headFootList)
			{
				List<HeadFootBean> list = readDetail(loc_e);
				map.put(loc_e.attributeValue("name"), list);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * read headfoot 
	 * @param ip_element
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static List<HeadFootBean> readDetail(Element ip_element) throws Exception
	{
		Class loc_clazz = HeadFootBean.class;
		List<HeadFootBean> loc_list = new ArrayList<HeadFootBean>();
		List<Element> loc_objList = ip_element.elements("field");
		
		for (Element loc_e : loc_objList)
		{
			Object loc_obj = loc_clazz.newInstance();
			Method[] loc_method = loc_obj.getClass().getMethods();
			List<Node> loc_attList = loc_e.attributes();
			for (Node loc_ss : loc_attList)
			{
				String loc_methodName = "set" + StringUtil.xmlToJavaUp(loc_ss.getName());
				for (Method loc_m : loc_method)
				{
					if (loc_methodName.equals(loc_m.getName()))
					{
						Object[] loc_array = {loc_ss.getStringValue()};
						loc_m.invoke(loc_obj, loc_array);
						break;
					}
				}
			}
			loc_list.add((HeadFootBean)loc_obj);
		}
		return loc_list;
	}
	
	public List<HeadFootBean> getList(String ip_jobPath, String ip_fileName) throws Exception
	{
		if (!map.containsKey(ip_fileName))
		{
			synchronized (synObj)
			{
				if (!map.containsKey(ip_fileName))
				{
					init(ip_jobPath);
				}
			}
		}
		return map.get(ip_fileName);
	}

}
