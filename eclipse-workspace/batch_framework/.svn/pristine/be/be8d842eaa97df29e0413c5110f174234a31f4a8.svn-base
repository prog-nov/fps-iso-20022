package com.forms.framework.conf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.util.CommonAPI;

public class BaseConfiger
{

	protected String fileName = null;

	// default reader.
	private static SAXReader saxReader = null;

	private Element root = null;

	public BaseConfiger(String ip_fileName)
			throws BatchFrameworkException
	{
		if (ip_fileName == null)
		{
			throw new BatchFrameworkException("Required config file name.");
		}
		this.fileName = ip_fileName;
		root = loadRootElement();
	}

	private static synchronized SAXReader getDefaultSAXReader()
	{
		if (saxReader == null)
		{
			saxReader = new SAXReader();
		}
		return saxReader;
	}

	public Element loadRootElement() throws BatchFrameworkException
	{
		File loc_file = null;
		FileInputStream loc_fis = null;
		InputStreamReader loc_isr = null;
		BufferedReader loc_br = null;
		Document loc_doc = null;
		Element loc_eleRoot = null;

		try
		{
			getDefaultSAXReader();
			loc_file = new File(fileName);
			loc_fis = new FileInputStream(loc_file);
			loc_isr = new InputStreamReader(loc_fis, CommonAPI.ENCODING_UTF_8);
			loc_br = new BufferedReader(loc_isr);
			// retrieve document instance.
			loc_doc = saxReader.read(loc_br);
			loc_eleRoot = loc_doc.getRootElement();

			return loc_eleRoot;
		} catch (Exception ip_e)
		{
			ip_e.printStackTrace();
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
				ip_e.printStackTrace();
			}
		}
	}

	/**
	 * return elements by path. if elemenet path is disabled return list size 0.
	 * 
	 * 
	 * @param ip_path
	 *            element path.
	 * @return .
	 * @throws BatchFrameworkException
	 */
	public List<Element> getElementsByPath(String ip_path)
			throws BatchFrameworkException
	{
		List<Element> loc_list = null;
		String[] loc_arrElePath = null;
		String loc_strRootName = null;
		Element loc_element = null;
		int loc_iCount = 0;

		if (ip_path == null || ip_path.length() < 1)
		{
			throw new BatchFrameworkException("Required element path.");
		}

		loc_list = new ArrayList<Element>();
		// splite element path.
		loc_arrElePath = ip_path.split(CommonAPI.SPLIT_IND_POINT);
		loc_iCount = loc_arrElePath.length;
		if (loc_iCount < 1)
		{
			throw new BatchFrameworkException("Invalid element path.");
		}
		for (String loc_str : loc_arrElePath)
		{
			if (loc_str == null || loc_str.length() < 1)
			{
				throw new BatchFrameworkException("Invalid element path.");
			}
		}

		loc_strRootName = root.getName();
		if (loc_strRootName.equals(loc_arrElePath[0]))
		{
			if (loc_iCount < 2)
			{
				loc_list.add(root);
			} else
			{
				loc_element = root;
				for (int i = 1; i < loc_iCount; i++)
				{
					loc_list = new ArrayList<Element>();
					for (Object obj : loc_element.elements(loc_arrElePath[i]))
					{
						loc_list.add((Element) obj);
					}
					if (loc_list.isEmpty())
					{
						break;
					}
					loc_element = loc_element.element(loc_arrElePath[i]);
				}
			}
		}

		return loc_list;
	}

	/**
	 * return sub elements by element path.
	 * 
	 * @param ip_path
	 *            element path.
	 * @return element sub elements.
	 * @throws BatchFrameworkException
	 */
	public List<Element> getSubElementsByPath(String ip_path)
			throws BatchFrameworkException
	{
		List<Element> loc_list = null;
		Element loc_element = null;

		loc_list = getElementsByPath(ip_path);
		if (loc_list != null && !loc_list.isEmpty())
		{
			loc_element = loc_list.get(0);
			loc_list = new ArrayList<Element>();
			for (Object obj : loc_element.elements())
			{
				loc_list.add((Element) obj);
			}
		}

		return loc_list;
	}

	public synchronized void writeDocument(String ip_fileName, Document ip_doc)
			throws BatchFrameworkException
	{
		File loc_file = null;
		FileOutputStream loc_fos = null;
		OutputStreamWriter loc_osw = null;
		BufferedWriter loc_bw = null;
		XMLWriter loc_writer = null;

		try
		{
			loc_file = new File(ip_fileName);
			loc_fos = new FileOutputStream(loc_file);
			loc_osw = new OutputStreamWriter(loc_fos, CommonAPI.ENCODING_UTF_8);
			loc_bw = new BufferedWriter(loc_osw);
			loc_writer = new XMLWriter(loc_bw);
			loc_writer.write(ip_doc);
			loc_writer.flush();
		} catch (Exception ip_e)
		{
			ip_e.printStackTrace();
			throw new BatchFrameworkException(ip_e);
		} finally
		{
			try
			{
				if (loc_writer != null)
				{
					loc_writer.close();
				}
				if (loc_bw != null)
				{
					loc_bw.close();
				}
				if (loc_osw != null)
				{
					loc_osw.close();
				}
				if (loc_fos != null)
				{
					loc_fos.close();
				}
			} catch (IOException ip_e)
			{
				ip_e.printStackTrace();
			}
		}
	}

}
