package com.forms.datapipe.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应 /pipe-config/valves/valve 属性
 * 
 * @author cxl
 * 
 */
public class ValveConfig
{
    private String name;

    private String clazz;

    private String configFile;

    private Map<String, ValveForward> forwards = new HashMap<String, ValveForward>();

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getClazz()
    {
        return clazz;
    }

    public void setClazz(String clazz)
    {
        this.clazz = clazz;
    }

    public String getConfigFile()
    {
        return configFile;
    }

    public void setConfigFile(String configFile)
    {
        this.configFile = configFile;
    }

    public Map<String, ValveForward> getForwards()
    {
        return forwards;
    }

    public void setForwards(Map<String, ValveForward> forwards)
    {
        this.forwards = forwards;
    }

    public void addValveForward(ValveForward forward)
    {
        forwards.put(forward.getName(), forward);
    }

}
