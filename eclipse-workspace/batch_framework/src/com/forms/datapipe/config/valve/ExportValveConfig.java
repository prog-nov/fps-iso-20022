package com.forms.datapipe.config.valve;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应配置文件export-valve-config.xml
 * 
 * @author cxl
 * 
 */
public class ExportValveConfig
{
    private List<ExportFieldMapping> fieldMappings = new ArrayList<ExportFieldMapping>();

    public void addFieldMapping(ExportFieldMapping fieldMapping)
    {
        fieldMappings.add(fieldMapping);
    }

    public List<ExportFieldMapping> getFieldMappings()
    {
        return fieldMappings;
    }

    public void setFieldMappings(List<ExportFieldMapping> fieldMappings)
    {
        this.fieldMappings = fieldMappings;
    }
}
