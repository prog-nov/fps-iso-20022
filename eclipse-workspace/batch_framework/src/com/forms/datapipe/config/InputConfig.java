package com.forms.datapipe.config;


/**
 * 
 * @author cxl
 * 
 */
public class InputConfig
{
    private String name;

    private String clazz;

    private String toValve;

    private String configFile;
    
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

    public String getToValve()
    {
        return toValve;
    }

    public void setToValve(String toValve)
    {
        this.toValve = toValve;
    }

    public String getConfigFile()
    {
        return configFile;
    }

    public void setConfigFile(String configFile)
    {
        this.configFile = configFile;
    }
}
