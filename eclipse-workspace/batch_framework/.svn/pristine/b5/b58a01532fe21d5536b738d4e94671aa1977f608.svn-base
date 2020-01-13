package com.forms.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.forms.framework.exception.BatchFrameworkException;

public class XmlUtil
{

	public static Element loadRootElement(String ip_fileName) throws BatchFrameworkException
	{
		return loadRootElement(ip_fileName,CommonAPI.ENCODING_UTF_8);		
	}
	
	public static Element loadRootElement(String ip_fileName,String encoding) throws BatchFrameworkException
	{
		File loc_file = null;
		FileInputStream loc_fis = null;
		InputStreamReader loc_isr = null;
		BufferedReader loc_br = null;
		Document loc_doc = null;
		Element loc_eleRoot = null;

		try
		{
			SAXReader loc_saxReader = new SAXReader();
			loc_file = new File(ip_fileName);
			loc_fis = new FileInputStream(loc_file);
			loc_isr = new InputStreamReader(loc_fis, encoding);
			loc_br = new BufferedReader(loc_isr);
			// retrieve document instance.
			loc_doc = loc_saxReader.read(loc_br);
			loc_eleRoot = loc_doc.getRootElement();
			return loc_eleRoot;
		} catch (Exception ip_e)
		{
			throw new BatchFrameworkException(ip_e);
		} finally
		{
			try
			{
				if (loc_br != null)
				{
					loc_br.close();
				}
				if (loc_isr != null)
				{
					loc_isr.close();
				}
				if (loc_fis != null)
				{
					loc_fis.close();
				}
			} catch (IOException ip_e)
			{
				throw new BatchFrameworkException(ip_e);
			}
		}
	}
	
}
