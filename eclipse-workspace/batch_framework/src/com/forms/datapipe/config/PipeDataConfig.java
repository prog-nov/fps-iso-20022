package com.forms.datapipe.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应 /pipe-config/pipe-data 属性
 * 
 * @author cxl
 * 
 */
public class PipeDataConfig
{
    private Map<String, PipeField> fields = new HashMap<String, PipeField>();

    public Map<String, PipeField> getFields()
    {
        return fields;
    }

    public void setFields(Map<String, PipeField> fields)
    {
        this.fields = fields;
    }

    public void addPipeField(PipeField field)
    {
        fields.put(field.getName(), field);
    }

}
