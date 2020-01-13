package com.forms.datapipe.context;

import java.util.HashMap;
import java.util.Map;

import com.forms.datapipe.config.PipeConfig;

/**
 * 数据转换管道context
 * 
 * @author cxl
 * 
 */
public class PipeContext
{
    /*
     * 数据转换配置中的参数
     */
    private Map<String, String> parameters;

    /*
     * pipeConfig
     */
    private PipeConfig pipeConfig;

    /*
     * 属性集合
     */
    private Map<String, Object> attributes = new HashMap<String, Object>();

    public PipeContext(PipeConfig pipeConfig, Map<String, String> parameters)
    {
        this.pipeConfig = pipeConfig;
        this.parameters = parameters;
    }

    /**
     * 获取参数值
     * 
     * @param paraName
     * @return 参数值
     */
    public String getParameter(String paraName)
    {
        return parameters.get(paraName);
    }

    /**
     * Request scope variable
     * 
     * @param key --
     *            key
     * @param value --
     *            value
     */
    public synchronized void setAttribute(String key, Object value)
    {
        attributes.put(key, value);
    }

    /**
     * Request scope variable
     * 
     * @param key --
     *            key
     * @return value
     */
    public synchronized Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    /**
     * Request scope variable
     * 
     * @param key --
     *            key
     */
    public synchronized void removeAttribute(String key)
    {
        if (attributes.containsKey(key)) attributes.remove(key);
    }

    public synchronized Map<String, Object> getAttributes()
    {
        return attributes;
    }

    public synchronized Map<String, String> getParameters()
    {
        return parameters;
    }

    public synchronized PipeConfig getPipeConfig()
    {
        return pipeConfig;
    }
}
