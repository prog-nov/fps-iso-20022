package com.forms.datapipe.pipedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.datapipe.config.PipeDataConfig;
import com.forms.datapipe.config.PipeField;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;

/**
 * pipe data(one row)
 * 
 * @author cxl
 * 
 */
public class PipeData
    implements Cloneable
{
    private PipeDataConfig config;

    private Map<String, PipeFieldData> fieldDatas;
    
    private List<FieldData> inputDatas;

    public PipeData(PipeDataConfig config) throws DataPipeException
    {
        this.config = config;

        fieldDatas = new HashMap<String, PipeFieldData>();
        for (PipeField pipeField : config.getFields().values())
        {
            PipeFieldData pipeFieldData = new PipeFieldData(pipeField, null);
            fieldDatas.put(pipeField.getName(), pipeFieldData);
        }
    }
    
    public void addPipeData(PipeField pipeField, String value) throws DataPipeException
    {
        fieldDatas.put(pipeField.getName(), new PipeFieldData(pipeField, value));
    }

    public Map<String, PipeFieldData> getPipeFieldDatas()
    {
        return fieldDatas;
    }

    public PipeFieldData getPipeFieldData(String fieldName)
        throws DataPipeException
    {
        PipeFieldData fieldData = fieldDatas.get(fieldName);
        if (fieldData == null)
            throw new DataPipeException("[ Undefined pipe field: " + fieldName
                + " ]");

        return fieldDatas.get(fieldName);
    }

    public Object getPipeFieldValue(String fieldName) throws DataPipeException
    {
        return getPipeFieldData(fieldName).getValue();
    }

    public String getPipeFieldStringValue(String fieldName)
        throws DataPipeException
    {
        return getPipeFieldData(fieldName).getStringValue();
    }

    public void setPipeFieldValue(String fieldName, Object newValue)
        throws DataPipeException
    {
        getPipeFieldData(fieldName).setValue(newValue);
    }

    public PipeDataConfig getConfig()
    {
        return config;
    }

    @Override
    public PipeData clone() throws CloneNotSupportedException
    {
        try
        {
            PipeData newOne = new PipeData(this.config);
            for (PipeFieldData pipeFieldData : fieldDatas.values())
            {
                PipeFieldData temp = newOne.fieldDatas.get(pipeFieldData.getDefinition().getName());
                temp.setValue(pipeFieldData.getValue());
            }
            return newOne;
        } catch (DataPipeException e)
        {
            e.printStackTrace();
            throw new CloneNotSupportedException(e.getMessage());
        }
    }

    public static PipeData clone(PipeData pipeData) throws DataPipeException
    {
        try
        {
            return pipeData.clone(); //each flow should use its own data
        } catch (CloneNotSupportedException e)
        {
            throw new DataPipeException(e);
        }
    }

	public List<FieldData> getInputDatas()
	{
		return inputDatas;
	}

	public void setInputDatas(List<FieldData> inputDatas)
	{
		this.inputDatas = inputDatas;
	}
}
