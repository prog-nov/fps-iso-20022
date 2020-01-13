package com.forms.datapipe.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.exception.DataPipeFatalException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.valve.FieldConverter;

/**
 * output上下文
 * 
 * @author cxl
 * 
 */
public class OutputContext
{
    private static Log log = LogFactory.getLog(OutputContext.class);

    /*
     * 輸出介質集合
     */
    private Map<String, Output> outputs;

    /*
     * 監聽器for多綫程
     */
    private OutputContextObserver observer;

    public OutputContext(Map<String, Output> outputs,
        OutputContextObserver observer, int bufferSize)
    {
        this.outputs = outputs;
        this.observer = observer;

        //for多綫程
        cachePool0 = new ArrayList<RowData>(bufferSize);
        cachePool1 = new ArrayList<RowData>(bufferSize);
        inputCachePool = cachePool0;
        outputCachePool = cachePool1;
    }

    private Map<String, List<Output>> cache0 = new HashMap<String, List<Output>>();

    public List<Output> getOutputs(String exportValve)
        throws DataPipeException
    {
        List<Output> matched = cache0.get(exportValve);
        if (matched == null)
        {
            matched = new ArrayList<Output>();
            for (Output output : outputs.values())
            {
                if (output.getConfig().getFromValve().equals(exportValve))
                    matched.add(output);
            }
            if (matched.isEmpty())
                throw new DataPipeException("[ Invalid ExportValve: "
                    + exportValve + ", there is no output point to it. ]");

            cache0.put(exportValve, matched);
        }
        return matched;
    }

    public Map<String, Output> getOutputs()
    {
        return outputs;
    }

    public void processOutput(String exportValveId,
        Map<String, Object> fieldData) throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'processOutput' called. ] ");

        //validate and parse input data
        if (log.isDebugEnabled())
            log.debug(" [ validate and print output field data.. ] ");
        List<Output> outputs = getOutputs(exportValveId);
        for (Output output : outputs)
        {
            if (log.isDebugEnabled())
                log.debug(" [ handle output '" + output.getConfig().getName()
                    + "' .. ] ");
            processOneOutput(output, fieldData);
        }

        if (log.isDebugEnabled()) log.debug(" [ success! ] ");
    }

    @SuppressWarnings("unchecked")
	private void processOneOutput(Output output, Map<String, Object> outputData)
        throws DataPipeException
    {
    	if (log.isDebugEnabled())
            log.debug(" [ method 'processOneOutput' called. ] ");
        
        List<FieldData> fieldDatas = (List<FieldData>) outputData.get("loopFileData");
		Map<String, Object> validData = new HashMap<String, Object>();
		if (fieldDatas == null)
		{
			if (null != output.getFieldDefinition())
			{
				for (Field field : output.getFieldDefinition().getFields())
				{					
					String value = (field.getConstValue()==null?(String) outputData.get(field.getName()):field.getConstValue());
					value = FieldConverter.print(field, value);
					validData.put(field.getName(), value);
				}
			}
		} else
		{
			printData(fieldDatas, output.getFieldDefinition().getFields()); 
			validData.put("loopFileData", fieldDatas);
		}
		output.putRowData(validData);
    }

    //---------------------------for multi-thread start--------------------

    private List<RowData> cachePool0;

    private List<RowData> cachePool1;

    private List<RowData> inputCachePool;

    private List<RowData> outputCachePool;

    private int writeNum = 0;

    public List<RowData> getOutputCachePool()
	{
		return outputCachePool;
	}

	/**
     * for多綫程
     * 
     * @param exportValveId
     * @param fieldData
     * @param index
     * @throws DataPipeException
     */
    public synchronized void putOutputData(String exportValveId,
        Map<String, Object> fieldData, int index) throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'putOutputData' called. ] ");

        inputCachePool.add(new RowData(exportValveId, fieldData, index));
        observer.putOutputDataCalled(this);
    }

    /**
     * for多綫程
     * 
     * @throws DataPipeException
     */
    public synchronized void switchCachePool() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'switchCachePool' called. ] ");

        if (!outputCachePool.isEmpty())
            throw new DataPipeFatalException(
                " [ outputCachePool is not Empty! ] ");

        if (inputCachePool == cachePool0)
        {
            inputCachePool = cachePool1;
            outputCachePool = cachePool0;
        } else
        {
            inputCachePool = cachePool0;
            outputCachePool = cachePool1;
        }
    }

    /**
     * for多綫程
     * 
     * @param keepOrder
     * @throws DataPipeException
     */
    @SuppressWarnings("unchecked")
    public void flush(boolean keepOrder) throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'flush' called. ] ");

    	if (keepOrder) Collections.sort(outputCachePool);
    	for (RowData rowData : outputCachePool)
        {
    		processOutput(rowData.getExportValveId(), rowData.getFieldData());
        }
    	writeNum += outputCachePool.size();
        outputCachePool.clear();
    }

    /**
     * for多綫程
     * 
     * @return
     */
    public boolean isOutputCachePoolEmpty()
    {
        return outputCachePool.isEmpty();
    }

    /**
     * for多綫程
     * 
     * @return
     */
    public int getWriteNum()
    {
        return writeNum;
    }
    
    private void printData(List<FieldData> fieldDatas, List<Field> fields) throws DataPipeException
    {
    	for (FieldData data : fieldDatas)
		{
    		Field field = null;
    		for (Field f : fields)
    		{
    			if (f.getName().equals(data.getName()))
    				field = f;
    		}
    		if (field == null) continue;
			if (data.getFieldDatas() == null)
			{
				// 非循環數據
				data.setValue(FieldConverter.print(field, data.getValue()));
			} else
			{
				// 循環數據
				printData(data.getFieldDatas(), field.getFields());
			}
		}
    }

    @SuppressWarnings("unchecked")
    private class RowData
        implements Comparable
    {
        public RowData(String exportValveId, Map<String, Object> fieldData,
            int index)
        {
            this.exportValveId = exportValveId;
            this.fieldData = fieldData;
            this.index = index;
        }

        private String exportValveId;

        private Map<String, Object> fieldData;

        private int index;

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            this.index = index;
        }

        public String getExportValveId()
        {
            return exportValveId;
        }

        public void setExportValveId(String exportValveId)
        {
            this.exportValveId = exportValveId;
        }

        public Map<String, Object> getFieldData()
		{
			return fieldData;
		}

		public void setFieldData(Map<String, Object> fieldData)
		{
			this.fieldData = fieldData;
		}

		public int compareTo(Object o)
        {
            if (o instanceof RowData)
            {
                RowData another = (RowData) o;
                return (this.index - another.index);
            }

            throw new IllegalArgumentException("Argument must be a RowData");
        }

    }
}
