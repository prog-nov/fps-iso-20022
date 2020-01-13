package com.forms.framework.job.common.sqlservice;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * sqlJob config parse
 * @author ahnan
 * createDate:2011-04-28
 * updateDate:2011-04-28
 */
public class SqlJobConfig
{
	private static final String TRANSACTION = "transaction";
	
	@SuppressWarnings("unchecked")
	public static List<List<String>> initXML(String ip_fileName) throws Exception
	{
		List<List<String>> loc_list = new ArrayList<List<String>>();
		SAXReader loc_sr = null;
		Document loc_doc = null;
		
		try
		{
			loc_sr = new SAXReader();
			File loc_file = new File(ip_fileName);
			if (!loc_file.exists())
				throw new Exception("we can not found any config file" + ip_fileName);
			
			loc_doc = loc_sr.read(loc_file);
			Element loc_root = loc_doc.getRootElement();
			List<Element> loc_transList = loc_root.elements(TRANSACTION);
			for (Element loc_trans : loc_transList)
			{
				List<String> loc_sqlList = new ArrayList<String>();
				
				List<Element> loc_sqlE = loc_trans.elements();
				for (Element loc_sql : loc_sqlE)
				{
					loc_sqlList.add(loc_sql.getStringValue());
				}
				
				loc_list.add(loc_sqlList);
			}
		}
		catch (Exception ip_e)
		{
			throw new Exception(ip_e);
		}
		
		return loc_list;
	}
}