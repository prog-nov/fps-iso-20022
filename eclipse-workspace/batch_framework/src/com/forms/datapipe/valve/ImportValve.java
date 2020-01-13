package com.forms.datapipe.valve;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.DataPipe;
import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.PipeField;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.config.input.Case;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.valve.ImportFieldMapping;
import com.forms.datapipe.config.valve.ImportValveConfig;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.util.ByteUtils;

/**
 * 导入阀门
 * 
 * ImportValve InputContext.setPipeData(PipeData pipeData)
 * 
 * final class
 * 
 * @author cxl
 * 
 */
public final class ImportValve implements Valve
{
	private static Log log = LogFactory.getLog(ImportValve.class);

	private ValveConfig config;

	private ImportValveConfig importValveConfig;
	
	private Map<String, Integer> loopIndexes = new HashMap<String, Integer>();
	
	private Object lock = new Object();
	
	public void init(ValveConfig config, PipeContext pipeContext)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'init' called. ] ");

		this.config = config;
		if (config.getConfigFile() != null
				&& !"".equals(config.getConfigFile()))
			importValveConfig = DataPipeConfigFactory
					.getImportValveConfig(config.getConfigFile());
		// 將輸入配置中的字段加入到管道數據定義中
		initPipeField(pipeContext);
		if (log.isDebugEnabled())
			log.debug(" [ success. ] ");
	}

	@SuppressWarnings("unchecked")
	public List<ValveForward> process(ValveMapping valveMapping,
			PipeData pipeData, InputContext in, OutputContext out)
			throws DataPipeException
	{
		PerformanceMonitor.startRecord(this);
		if (log.isDebugEnabled())
			log.debug(" [ method 'process' called. ] ");

		//validate and parse input data
		if (log.isDebugEnabled())
			log.debug(" [ validate and parse all input field data.. ] ");

		//for多线程
		if (in.getPipeContext().getPipeConfig().getMultiThreadConfig() != null
				&& in.getPipeContext().getPipeConfig().getMultiThreadConfig()
						.isUse())
		{
			String index = (String) in.getInputData().get(DataPipe.INDEX_KEY);
			PipeField pipeField = new PipeField();
			pipeField.setName(DataPipe.INDEX_KEY);
			pipeField.setDataType("java.lang.String");
			pipeData.addPipeData(pipeField, index);
		}
		// 處理重定義數據
		reDefineFields(in.getInputData(), in.getInput().getFieldDefinition().getFields());
		// 普通數據保持原處理方式不變，如果是循環數據，則需要按新的格式進行處理
		List<FieldData> fieldDatas = (List<FieldData>) in.getInputData().get("loopFileData");
		Map<String, Object> validData = new HashMap<String, Object>();
		if (fieldDatas == null)
		{
			for (Field field : in.getInput().getFieldDefinition().getFields())
			{
				String value = (String) in.getInputData().get(field.getName());
				value = FieldConverter.parse(field, value);
				validData.put(field.getName(), value);
			}
		} else
		{
			parseData(fieldDatas); // 此時fieldDatas裏的數據都是被處理過的了
		}

		//mapping input value
		if (log.isDebugEnabled())
			log.debug(" [ mapping input value. ] ");
		// 輸入配置中定義的字段默認都導入到管道中，且字段名稱相同
		// 默認先導入輸入配置中定義的字段，再導入導入閥門Mapping的字段
		if (fieldDatas == null)
		{
			// 非循環處理數據處理，仍按照原來的模式導入輸入數據到管道，
			// 不同的是需要先映射輸入介質中定義的數據，默認此部分數據為java.lang.String類型
			Iterator it = validData.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry<String, String> me = (Map.Entry<String, String>) it.next();
				pipeData.setPipeFieldValue(me.getKey(), me.getValue());
			}
			// 如果importValveConfig不為null，則需要處理importValveConfig中定義的Mapping
			if (importValveConfig != null)
			{
				// 執行原有的Mapping邏輯
				for (ImportFieldMapping fieldMapping : importValveConfig
						.getFieldMappings())
				{
					if (log.isDebugEnabled())
						log.debug(" [ handle field mapping: " + fieldMapping + ". ] ");

					String value = (String) validData.get(fieldMapping.getInputField());
					if (value == null)
						throw new DataPipeException(" [ Undefined input field: "
								+ fieldMapping.getInputField() + "! ImportValve: "
								+ config.getName() + " ] ");

					pipeData.setPipeFieldValue(fieldMapping.getPipeField(), value);
				}
			}
		} else
		{
			// 輸入數據的字段名需要映射成管道定義的名稱
			if (importValveConfig != null)
			{
				for (ImportFieldMapping fieldMapping : importValveConfig
						.getFieldMappings())
				{
					// 遞歸查找名稱符合fieldMapping.getInputField()的值，將名稱替換為fieldMapping.getPipeField()
					mappingDatas(fieldDatas, fieldMapping);
				}
			}
			// 讓管道數據對象直接持有輸入的樹形數據引用
			pipeData.setInputDatas(fieldDatas);
		}

		if (log.isDebugEnabled())
			log.debug(" [ success! ] ");
		PerformanceMonitor.endRecord(this);
		return valveMapping.findForwards("success");
	}

	public void close() throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'close' called. ] ");
		//do nothing
	}

	public ValveConfig getConfig()
	{
		return config;
	}

	public ImportValveConfig getImportValveConfig()
	{
		return importValveConfig;
	}
	
	private void parseData(List<FieldData> fieldDatas) throws DataPipeException
	{
		for (FieldData data : fieldDatas)
		{
			if (data.getFieldDatas() == null)
			{
				// 非循環數據
				data.setValue(FieldConverter.parse(data));
			} else
			{
				// 循環數據
				parseData(data.getFieldDatas());
			}
		}
	}
	
	/**
	 * 映射字段
	 * 
	 * @param fieldDatas
	 * @param fieldMapping
	 */
	private void mappingDatas(List<FieldData> fieldDatas,
			ImportFieldMapping fieldMapping)
	{
		// 因爲字段名不允許相同，則只要有一個字段名符合要求即停止循環
		for (FieldData fieldData : fieldDatas)
		{
			List<FieldData> subFieldDatas = fieldData.getFieldDatas();
			if (subFieldDatas == null)
			{
				if (fieldData.getName().equals(fieldMapping.getInputField()))
				{
					fieldData.setName(fieldMapping.getPipeField());
					break;
				}
			} else
			{
				mappingDatas(subFieldDatas, fieldMapping);
			}
		}
	}
	
	private void initPipeField(PipeContext pipeContext) throws DataPipeException
	{
		Map<String, InputConfig> inputConfigs = pipeContext.getPipeConfig().getInputs();
		Iterator it = inputConfigs.keySet().iterator();
		try
		{
			while (it.hasNext())
			{
				InputConfig inputConfig = inputConfigs.get(it.next());
				List<Field> fields = getFields(inputConfig);
				addPipeField(fields, pipeContext);
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	private List<Field> getFields(InputConfig inputConfig) throws DataPipeException
	{
		try
		{
			String className = inputConfig.getClazz();
			className = className.substring(className.lastIndexOf(".") + 1);
			Method m = DataPipeConfigFactory.class.getMethod("get" + className + "Config", String.class);
			Object config = m.invoke(null, inputConfig.getConfigFile());
			Method getField = config.getClass().getMethod("getFieldDefinition");
			FieldDefinition f = (FieldDefinition) getField.invoke(config);
			return f.getFields();
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	private void addPipeField(List<Field> fields, PipeContext pipeContext)
	{
		if (fields == null || fields.size() == 0)
			return;
		for (Field field : fields)
		{
			if (field.getCases() != null)
			{
				for (Case c : field.getCases())
				{
					addPipeField(c.getFields(), pipeContext);
				}
			} else if (field.getFields() == null || field.getFields().size() == 0)
			{
				if (!pipeContext.getPipeConfig().getPipeData().getFields().containsKey(field.getName()))
				{
					PipeField pipeField = new PipeField();
					pipeField.setName(field.getName());
					pipeField.setDataType("java.lang.String");
					pipeContext.getPipeConfig().getPipeData().addPipeField(pipeField);
				}
			}
			else addPipeField(field.getFields(), pipeContext);
		}
	}
	
	/**
	 * 重定義字段
	 * 
	 * @param fieldDatas
	 * @param fields
	 * @throws DataPipeException 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	private void reDefineFields(Map<String, Object> inputData, List<Field> fields) throws DataPipeException
	{
		List<FieldData> fieldDatas = (List<FieldData>) inputData.get("loopFileData");
		int index = 0;
		if (fieldDatas == null)
		{
			// 非循環數據
			for (Field field : fields)
			{
				if (field.getCases() != null)
				{
					String value = (String) inputData.get(field.getName());
					index = 0;
					String caseValue = getCaseValue(field, inputData, null);
					Case defaultCase = null;
					boolean flag = false; // 判斷是否有case滿足條件
					// 具有重定義的邏輯
					for (Case c : field.getCases())
					{
						if (caseValue.equals(c.getValue()))
						{
							// 應選擇此种case
							for (Field f : c.getFields())
							{
								inputData.put(f.getName(), new String(value.getBytes(), index, f.getBytes()));
								index += f.getBytes();
							}
							flag = true;
							break;
						}
						if (field.getCaseDefault().equals(c.getValue()))
						{
							defaultCase = c;
						}
					}
					if (!flag)
					{
						// 沒有case滿足返回的caseValue，選擇case-default
						for (Field f : defaultCase.getFields())
						{
							inputData.put(f.getName(), new String(value.getBytes(), index, f.getBytes()));
							index += f.getBytes();
						}
					}
				}
			}
		} else
		{
			// 是循環數據
			for (Field field : fields)
			{
				if (field.getCases() != null)
				{
					Case c = getCase(field, inputData, fieldDatas);
					if (c == null) throw new DataPipeException("Invalid reDefine case!");
					inputData.put("loopFileData", reDefine4LoopData(fieldDatas, field, c));
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private String getCaseValue(Field field, Map<String, Object> inputData, List<FieldData> fieldDatas) throws DataPipeException
	{
		String caseValue = null;
		String depend = field.getCaseDepend();
		if (depend.startsWith("constant:"))
			caseValue = depend.substring(9);
		else if (depend.startsWith("field:"))
		{
			String fieldName = depend.substring(6);
			if (fieldDatas == null)
				caseValue = (String) inputData.get(fieldName);
			else
			{
				// 遞歸向上層尋找符合的字段
				caseValue = getFieldValue(fieldName, fieldDatas);
			}
		}
		else if (depend.startsWith("class:"))
		{
			String className = depend.substring(6);
			try
			{
				Class<?> clazz = Class.forName(className);
				Method m = clazz.getMethod("execute", Map.class);
				caseValue = (String) m.invoke(clazz.newInstance(), inputData);
			} catch (Exception e)
			{
				throw new DataPipeException(e);
			}
		} else
			throw new DataPipeException("Invalid case depend value!");
		return caseValue;
	}
	
	private List<FieldData> reDefine4LoopData(List<FieldData> fieldDatas, Field field, Case c) throws DataPipeException
	{
		List<FieldData> tmp = new ArrayList<FieldData>();
		
		int index = 0;
		// 首先遞歸找出需要重定義的字段值
		for (FieldData fieldData : fieldDatas)
		{
			if (fieldData.getFieldDatas() == null)
			{
				if (fieldData.getName().equals(field.getName()))
				{
					// 該字段數據對象對應重定義字段				
					String value = fieldData.getValue();
					index = 0;
					// 具有重定義的邏輯
					FieldData parent = new FieldData();
					parent.setFieldDatas(tmp);
					List<FieldData> reDefineFields = getReDefineLoopField(c.getFields(), parent, fieldDatas, index, value, 1, fieldData.getEncoding(), null);
					for (FieldData f : reDefineFields)
						tmp.add(f);
				} else
				{
					tmp.add(fieldData);
				}
			} else
			{
				fieldData.setFieldDatas(reDefine4LoopData(fieldData.getFieldDatas(), field, c));
				tmp.add(fieldData);
			}
		}
		fieldDatas = tmp;
		return fieldDatas;
	}
	
	private String getFieldValue(String fieldName, List<FieldData> fieldDatas)
	{
		for (FieldData fieldData : fieldDatas)
		{
			if (fieldData.getName().equals(fieldName))
			{
				return fieldData.getValue();
			}
		}
		FieldData parent = fieldDatas.get(0).getParent();
		if (parent != null) 
			return getFieldValue(fieldName, parent.getFieldDatas());
		else
			return "";
	}
	
	private Case getCase(Field field, Map<String, Object> inputData,
			List<FieldData> fieldDatas) throws DataPipeException
	{
		String caseValue = getCaseValue(field, inputData, fieldDatas);
		Case defaultCase = null;
		// 具有重定義的邏輯
		for (Case c : field.getCases())
		{
			if (caseValue.equals(c.getValue()))
			{
				// 應選擇此种case
				return c;
			}
			if (field.getCaseDefault().equals(c.getValue()))
			{
				defaultCase = c;
			}
		}
		return defaultCase;
	}
	
	private int getLoopCountValue(String loopCountString, Field field,
			FieldData parent) throws DataPipeException
	{
		int loopCount = 0;
		if (loopCountString == null || "".equals(loopCountString))
			throw new DataPipeException("Field " + field.getName()
					+ "'s loop count can not be null!");
		if (loopCountString.startsWith("constant:"))
			loopCount = Integer.parseInt(loopCountString.substring(9));
		else if (loopCountString.startsWith("field:"))
		{
			String fieldName = loopCountString.substring(6);
			boolean flag = false;
			// 需要遞歸向上尋找對應名稱的字段
			if (parent.getFieldDatas() == null)
				parent = parent.getParent();
			while (!flag && parent != null)
			{
				for (FieldData data : parent.getFieldDatas())
				{
					if (data.getName().equals(fieldName))
					{
						loopCount = Integer.parseInt(data.getValue());
						flag = true;
						break;
					}
				}
				parent = parent.getParent();
			}
			if (!flag)
				throw new DataPipeException("No satisfiy loop count field!");
		} else if (loopCountString.startsWith("class:"))
		{
			String className = loopCountString.substring(6);
			try
			{
				Class<?> clazz = Class.forName(className);
				Method m = clazz.getMethod("execute", Map.class);
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("loopFileData", parent.getFieldDatas());
				loopCount = Integer.parseInt((String) m.invoke(clazz.newInstance(), parameter));
			} catch (Exception e)
			{
				throw new DataPipeException(e);
			}
		} else
			throw new DataPipeException("Invalid loop count!");
		return loopCount;
	}
	
	private List<FieldData> getReDefineLoopField(List<Field> fields,
			FieldData parent, List<FieldData> fieldDatas, int index, String value, int count, String encoding, String loopName)
			throws DataPipeException
	{
		List<FieldData> datas = new ArrayList<FieldData>();
		for (int i = 0; i < count; i++)
		{
			FieldData current = new FieldData();
			current.setParent(parent);
			if (loopName != null)
			{
				FieldData loopIndex = new FieldData();
				loopIndex.setParent(parent);
				loopIndex.setName(loopName + "_loopIndex");
				loopIndex.setDataType("NumberType");
				synchronized (lock)
				{
					Integer lastIndex = loopIndexes.get(loopName + "_loopIndex");
					if (lastIndex == null) lastIndex = new Integer(0);
					Integer newIndex = new Integer(lastIndex.intValue() + 1);
					loopIndex.setValue(String.valueOf(newIndex.intValue()));
					loopIndexes.put(loopName + "_loopIndex", newIndex);
				}
				
				datas.add(loopIndex);
			}
			for (Field f : fields)
			{
				if (f.getCases() != null)
				{
					Case c = getCase(f, null, fieldDatas);
					List<FieldData> l = getReDefineLoopField(c.getFields(), parent, fieldDatas, index, value, count, encoding, loopName);
					for (FieldData fd : l) 
						datas.add(fd);
				}
				FieldData fieldRe = new FieldData();
				fieldRe.setName(f.getName());
				fieldRe.setParent(parent);
				fieldRe.setEncoding(encoding);
				if (f.getFields() == null || f.getFields().size() == 0)
				{
					fieldRe.setBytes(f.getBytes());
					fieldRe.setDataType(f.getDataType());
					try
					{
						String tmp = value.substring(index, index + f.getBytes() * 2);
						if (f.getDataType().indexOf("PackedDecimal") > -1
								|| f.getDataType().indexOf("ASM") > -1
								|| f.getDataType().indexOf("Binary") > -1)
						{
							fieldRe.setValue(tmp);
						} else
						{
							// 非PackedDecimal類型，直接new String
							fieldRe.setValue(new String(ByteUtils.fromHexString(tmp), encoding));
						}
					} catch (UnsupportedEncodingException e)
					{
						throw new DataPipeException(e);
					}
					current.addFieldData(fieldRe);
					datas.add(fieldRe);
					index += f.getBytes() * 2;
				} else
				{
					// 該重定義字段為循環數據
					int loopCount = getLoopCountValue(f.getLoopCount(), f, current);
					fieldRe.setLoopCount(loopCount);
					current.addFieldData(fieldRe);
					fieldRe.setFieldDatas(getReDefineLoopField(f.getFields(), current, fieldDatas, index, value, loopCount, encoding, f.getName()));
					datas.add(fieldRe);
					// 如果循環體有限制總長度，則取循環體總長度，否則取字段長度纍計
					if (f.getBytes() != 0)
						index += f.getBytes() * 2;
					else
					{
						int tmp = 0;
						for (Field f0 : f.getFields())
						{
							tmp += f0.getBytes() * 2;
						}
						index += tmp * loopCount;
					}
				}
			}
		}
		return datas;
	}
}
