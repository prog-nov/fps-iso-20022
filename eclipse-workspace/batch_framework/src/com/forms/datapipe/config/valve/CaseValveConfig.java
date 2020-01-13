package com.forms.datapipe.config.valve;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应配置文件case-valve-config.xml
 * 
 * @author cxl
 * 
 */
public class CaseValveConfig
{
    private Map<String, FilterCase> filterCases = new HashMap<String, FilterCase>();

    public void addFilterCase(FilterCase filterCase)
    {
        filterCases.put(filterCase.getName(), filterCase);
    }

    public Map<String, FilterCase> getFilterCases()
    {
        return filterCases;
    }

    public void setFilterCases(Map<String, FilterCase> filterCases)
    {
        this.filterCases = filterCases;
    }
}
