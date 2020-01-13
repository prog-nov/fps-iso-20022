package com.forms.datapipe.valve;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.DataPipe;
import com.forms.datapipe.Output;
import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.PipeField;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.valve.ExportFieldMapping;
import com.forms.datapipe.config.valve.ExportValveConfig;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.pipedata.PipeData;

/**
 * 
 * ExportValve OutputContext.setOutputData(Map<String, String> outputData)
 * 
 * final class
 * 
 * @author cxl
 * 
 */
public final class ExportValve
    implements Valve
{
    private static Log log = LogFactory.getLog(ExportValve.class);

    private ValveConfig config;

    private ExportValveConfig exportValveConfig;

    public void init(ValveConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        if (config.getConfigFile() != null && !"".equals(config.getConfigFile()))
        	exportValveConfig = DataPipeConfigFactory.getExportValveConfig(config.getConfigFile());
        // mapping the field define in output to pipe
        initPipeField(pipeContext);
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public List<ValveForward> process(ValveMapping valveMapping,
        PipeData pipeData, InputContext in, OutputContext out)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled()) log.debug(" [ method 'process' called. ] ");

        // mapping output value
        if (log.isDebugEnabled()) log.debug(" [ mapping output value. ] ");
        Map<String, Object> fieldData = new HashMap<String, Object>();
        List<Output> outputs = out.getOutputs(config.getName());
        if (pipeData.getInputDatas() == null)
        {
        	// mapping the field from pipe to output
        	for (Output output : outputs)
            {
        		if (null != output.getFieldDefinition())	            	
	            	for (Field field : output.getFieldDefinition().getFields())
	            		fieldData.put(field.getName(),
	                            pipeData.getPipeFieldStringValue(field.getName()));
            		
            }
        	if (exportValveConfig != null)
        	{
        		for (ExportFieldMapping fieldMapping : exportValveConfig.getFieldMappings())
                {
                    if (log.isDebugEnabled())
                        log.debug(" [ handle field mapping: " + fieldMapping + ". ] ");

                    fieldData.put(fieldMapping.getOutputField(),
                        pipeData.getPipeFieldStringValue(fieldMapping.getPipeField()));
                }
        	}
        } else
        {
        	// mapping field name
			if (exportValveConfig != null)
			{
				for (ExportFieldMapping fieldMapping : exportValveConfig
						.getFieldMappings())
				{
					mappingDatas(pipeData.getInputDatas(), fieldMapping);
				}
			}
        	fieldData.put("loopFileData", pipeData.getInputDatas());
        }
       
        String rowIndex = (String)  in.getPipeContext().getAttribute(DataPipe.INDEX_KEY);
        in.getPipeContext().removeAttribute("rowValue" + rowIndex);
		if (in.getPipeContext().getPipeConfig().getMultiThreadConfig() != null
				&& in.getPipeContext().getPipeConfig().getMultiThreadConfig()
						.isUse()) {
            int index = Integer.parseInt(rowIndex);
            out.putOutputData(config.getName(), fieldData, index);
        } else
        {
            out.processOutput(config.getName(), fieldData);
        }

        PerformanceMonitor.endRecord(this);
        return null;
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        // do nothing
    }

    public ValveConfig getConfig()
    {
        return config;
    }

    public ExportValveConfig getExportValveConfig()
    {
        return exportValveConfig;
    }
    
    /**
	 * mapping field
	 * 
	 * @param fieldDatas
	 * @param fieldMapping
	 */
	private void mappingDatas(List<FieldData> fieldDatas,
			ExportFieldMapping fieldMapping)
	{
		for (FieldData fieldData : fieldDatas)
		{
			List<FieldData> subFieldDatas = fieldData.getFieldDatas();
			if (subFieldDatas == null)
			{
				if (fieldData.getName().equals(fieldMapping.getPipeField()))
				{
					fieldData.setName(fieldMapping.getOutputField());
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
		Map<String, OutputConfig> outputConfigs = pipeContext.getPipeConfig().getOutputs();
		Iterator it = outputConfigs.keySet().iterator();
		try
		{
			while (it.hasNext())
			{
				OutputConfig outputConfig = outputConfigs.get(it.next());
				List<Field> fields = getFields(outputConfig);
				addPipeField(fields, pipeContext);
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}
	
	private List<Field> getFields(OutputConfig outputConfig) throws DataPipeException
	{
		try
		{
			String className = outputConfig.getClazz();
			className = className.substring(className.lastIndexOf(".") + 1);
			Method m = DataPipeConfigFactory.class.getMethod("get" + className + "Config", String.class);
			Object config = m.invoke(null, outputConfig.getConfigFile());
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
			if (field.getFields() == null || field.getFields().size() == 0)
			{
				if (field.getKeyField() != null
						&& !"".equals(field.getKeyField()))
					continue; // key field not need to mapping
				if (!pipeContext.getPipeConfig().getPipeData().getFields()
						.containsKey(field.getName()))
				{
					PipeField pipeField = new PipeField();
					pipeField.setName(field.getName());
					pipeField.setDataType("java.lang.String");
					pipeContext.getPipeConfig().getPipeData().addPipeField(
							pipeField);
				}
			} else
				addPipeField(field.getFields(), pipeContext);
		}
	}

}
