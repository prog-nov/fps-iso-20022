package com.forms.datapipe.config;

/**
 * /pipe-config/outputs/output
 * 
 * @author cxl
 * 
 */
public class OutputConfig 
{
    private String name;

    private String clazz;

    private String fromValve;

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

    public String getFromValve()
    {
        return fromValve;
    }

    public void setFromValve(String fromValve)
    {
        this.fromValve = fromValve;
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
