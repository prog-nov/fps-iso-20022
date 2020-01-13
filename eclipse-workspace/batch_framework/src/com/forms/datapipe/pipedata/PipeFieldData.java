package com.forms.datapipe.pipedata;

import com.forms.datapipe.config.PipeField;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.BeanUtils;

/**
 * Field data
 * 
 * @author cxl
 * 
 */
public class PipeFieldData
{
    public PipeFieldData(PipeField definition, Object value)
        throws DataPipeException
    {
        this.definition = definition;
        this.setValue(value);
    }

    /*
     * ����
     */
    private PipeField definition;

    /*
     * ֵ
     */
    private Object value;

    public PipeField getDefinition()
    {
        return definition;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue()
    {
        return (T) value;
    }

    public String getStringValue()
    {
        return value == null ? "" : value.toString();
    }

    public void setValue(Object value) throws DataPipeException
    {
        if (value == null) return;

        try
        {
            this.value = BeanUtils.transform(value,
                Class.forName(definition.getDataType()));
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
    }
}
