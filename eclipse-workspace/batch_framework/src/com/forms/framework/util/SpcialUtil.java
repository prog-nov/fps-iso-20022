package com.forms.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Element;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.util.XmlUtil;
import com.forms.datapipe.Input;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;

public class SpcialUtil
{

	public static String ADDINPUT_Y = "Y";

	public static String ADDINPUT_N = "N";

	public String DIRECTORY;

	public static final String TIME_PATTERN = "HHmmss";

	private static String path = System.getenv(CommonAPI.ENV_BATCH_ROOT);

	public static final String suffix = ".ctl";

	private static final String removeInputALL = "ALL";
	
	private static final String CONSTANT_ADD="add";
	
	private static final String CONSTANT_ADDINPUT="addInput";
	
	private static final String CONSTANT_REPLACE="replace";
	
	private static final String CONSTANT_DELETE="delete";
	
	private Element root=null;
	
	public SpcialUtil(String jobId,String acDate) throws BatchFrameworkException{
		init(jobId,acDate);
	}
	
	public void init(String jobId,String acDate) throws BatchFrameworkException{
		try
		{
			DIRECTORY=System.getenv(CommonAPI.ENV_BATCH_SPCIALPATH);
			root=getActionRoot(jobId, acDate);
		} catch (BatchFrameworkException e)
		{
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public String isAddInput(String actionName) throws Exception
	{
		try
		{
			if (root != null)
			{
				List<Element> actions = root.elements("action");
				if (actions != null)
				{
					for (Element e : actions)
					{
						if (actionName.equals(e.attributeValue("actionName"))&&CONSTANT_ADDINPUT.equals(e.attributeValue("type")))
						{
							return ADDINPUT_Y;
						}
					}
				}
			}
		} catch (Exception e)
		{
			throw e;
		}
		return ADDINPUT_N;
	}

	@SuppressWarnings("unchecked")
	public void getDataPipeInput(String actionName,
			PipeContext pipeContext, Map<String, Input> inputMaps)
			throws DataPipeException
	{
		try
		{
			if (root != null)
			{
				List<Element> actions = root.elements("action");
				List<Element> inputs = null;
				String removeInput = "";
				if (actions != null)
				{
					for (Element e : actions)
					{
						if (actionName.equals(e.attributeValue("actionName"))&&CONSTANT_ADDINPUT.equals(e.attributeValue("type")))
						{
							inputs = e.elements("input");
							if (inputs != null)
							{
								removeInput = e.attributeValue("removeInput");
								if (removeInput != null)
								{
									if (removeInputALL.equalsIgnoreCase(e
											.attributeValue("removeInput")))
									{
										inputMaps.clear();
									} else
									{
										for (String inputStr : removeInput
												.split(","))
										{
											inputMaps.remove(inputStr);
										}
									}
								}
								for (Element ee : inputs)
								{
									Input input = DataPipeUtils.newInstance(ee
											.attributeValue("class"));
									InputConfig inputConfig = new InputConfig();
									inputConfig.setName(ee
											.attributeValue("name"));
									inputConfig.setClazz(ee
											.attributeValue("class"));
									inputConfig.setConfigFile(ee
											.elementText("config-file"));
									inputConfig.setToValve(ee
											.attributeValue("to-valve"));
									input.init(inputConfig, pipeContext);
									inputMaps.put(ee.attributeValue("name"),
											input);
								}

							}
						}
					}
				}
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}

	}

	private Element getActionRoot(String jobId, String batchAcDt)
			throws BatchFrameworkException
	{
		try
		{
			Properties pro = getConfigProperties(jobId, batchAcDt);
			if (pro != null)
			{
				String configFilePath = pro.getProperty(batchAcDt);
				if (configFilePath != null)
				{
					if (new File(path + configFilePath).exists())
					{
						return XmlUtil
								.loadRootElement(path + configFilePath);
					} else
					{
						throw new BatchFrameworkException(path
								+ configFilePath + " not found");
					}
				}
			}
		} catch (Exception e)
		{
			throw new BatchFrameworkException(e);
		}
		return null;
	}

	public void removeConfigFile(String jobId)
			throws Exception
	{
		try
		{
			File file = getCtlFile(jobId);
			file.delete();
		} catch (Exception e)
		{
			throw e;
		}
	}

	private Properties getConfigProperties(String jobId, String batchAcDt)
			throws BatchFrameworkException
	{
		Properties pro = null;
		File file = getCtlFile(jobId);
		if (file.exists())
		{
			InputStream is = null;
			try
			{
				is = new FileInputStream(file);
				pro = new Properties();
				pro.load(is);
			} catch (Exception e)
			{
				throw new BatchFrameworkException(e);
			} finally
			{
				if (is != null)
				{
					try
					{
						is.close();
					} catch (IOException e)
					{
						throw new BatchFrameworkException(e);
					}
				}
			}
		}
		return pro;
	}

	private File getCtlFile(String jobId)
			throws BatchFrameworkException
	{
		return new File(DIRECTORY +File.separator+ jobId.toLowerCase()
				+ suffix);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Element> addSpcialAction(ArrayList<Element> list) throws Exception
	{
		ArrayList<Element> resultList=null;
		try
		{
			if (root != null)
			{
				List<Element> actionList = root.elements("action");
				if (actionList != null)
				{
					resultList=new ArrayList<Element>();		
					String actionName="";
					String type="";
					boolean addFlag=true;
					Element element=null;
					for(int i=0;i<list.size();i++){
						element=list.get(i);
						addFlag=true;
						for (Element spcelelment : actionList)
						{
							type=spcelelment.attributeValue("type");
							if(CONSTANT_ADD.equalsIgnoreCase(type)){
								actionName=spcelelment.attributeValue("beforeAction");
								if((actionName==null||"".equals(actionName))){
									if(i==(list.size()-1)){
										if(addFlag){
											resultList.add(element);
										}
										addFlag=false;																				
										resultList.add(spcelelment);
									}
									continue;
								}else{
									if(actionName.equals(element.elementText("action-name"))){
										resultList.add(spcelelment);
									}
								}
							}else if(CONSTANT_ADDINPUT.equalsIgnoreCase(type)){
								continue;
							}else if(CONSTANT_REPLACE.equalsIgnoreCase(type)){
								actionName=spcelelment.attributeValue("replaceAction");
								if(actionName==null||"".equals(actionName)){
									throw new Exception("spcial job config replaceAction is null");
								}
								if(actionName.equals(element.elementText("action-name"))){
									resultList.add(spcelelment);
									addFlag=false;
								}								
							}else if(CONSTANT_DELETE.equalsIgnoreCase(type)){
								actionName=spcelelment.attributeValue("deleteAction");
								if(actionName==null||"".equals(actionName)){
									throw new Exception("spcial job config deleteAction is null");
								}
								if(actionName.equals(element.elementText("action-name"))){
									addFlag=false;
								}								
							}else{
								throw new Exception("spcial job config type error:"+spcelelment.attributeValue("type"));
							}
														
						}
						if(addFlag){
							resultList.add(element);
						}
					}	
				}					
			}
		} catch (Exception e)
		{
			throw e;
		}
		return resultList==null?list:resultList;
	}
	

}
