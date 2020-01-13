package com.forms.datapipe.compare;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Comparefileparse
 * @author ouser
 * @version 1.0
 * createDate:2011-04-11
 * updateDate:2011-04-28
 */
public class CmpXMLInit 
{
	/**
	 * initfile
	 * @param ip_filePath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<CmpBean> initXML(String ip_filePath) throws Exception
	{
		List<CmpBean> loc_cmpList = new ArrayList<CmpBean>();
		File loc_file = new File(ip_filePath);
		SAXReader loc_sr = null;
		Document loc_doc = null;
		CmpBean loc_bean = new CmpBean();
		
		try
		{
			if (!loc_file.exists())
				throw new Exception("this is not config file exists");
			
			loc_sr = new SAXReader();
			loc_doc = loc_sr.read(loc_file);
			Element loc_root = loc_doc.getRootElement();
			Element loc_configE = loc_root.element("configs");
			List<Element> loc_configList = loc_configE.elements();
			
			for (Element loc_config : loc_configList)
			{
				Element loc_ruleE = loc_config.element("use-mapping");
				if (loc_ruleE != null && !"Y".equals(loc_ruleE.getStringValue()))           
				{
					// this function is unusable
					
					// loc_bean.setUseMapping(loc_ruleE.getStringValue());
					// loc_bean.setConfigName(loc_config.attributeValue("name"));
					// loc_cmpList.add(loc_bean);
					// return loc_cmpList;
					throw new Exception("when compare data,use-mapping must be Y");
				}
				
				// using mapping compare field
				List<Element> loc_list = loc_config.element("mappings").elements();
				List<Map<String, String>> loc_resultList = new ArrayList<Map<String,String>>();
				for (Element loc_e : loc_list)
				{
					Map<String, String> loc_map = new HashMap<String, String>();
					loc_map.put("inputName", loc_e.attributeValue("input-name"));
					loc_map.put("outputName", loc_e.attributeValue("output-name"));
					loc_resultList.add(loc_map);
				}
				loc_bean.setMappings(loc_resultList);
				loc_cmpList.add(loc_bean);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		return loc_cmpList;
	}
}
