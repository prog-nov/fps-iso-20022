package com.forms.datapipe.context;

import java.util.HashMap;
import java.util.Map;

import com.forms.datapipe.Input;

/**
 * 输入介质上下文
 * 
 * @author cxl
 * 
 */
public class InputContext
{

    /*
     * attributes
     */
    private Map<String, Object> attributes = new HashMap<String, Object>();

    /*
     * 输入
     */
    private Input input;

    /*
     * PipeContext
     */
    private PipeContext pipeContext;

    /*
     * row data
     */
    private Map<String, Object> inputData;

    public InputContext(PipeContext pipeContext, Input input,
        Map<String, Object> inputData)
    {
        this.pipeContext = pipeContext;
        this.input = input;
        this.inputData = inputData;
    }

    public PipeContext getPipeContext()
    {
        return pipeContext;
    }

    /**
     * 获取输入
     * 
     * @return Input
     */
    public Input getInput()
    {
        return input;
    }

    /**
     * Request scope variable
     * 
     * @param key --
     *            key
     * @param value --
     *            value
     */
    public void setAttribute(String key, Object value)
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
    public Object getAttribute(String key)
    {
        return attributes.get(key);
    }

    /**
     * Request scope variable
     * 
     * @param key --
     *            key
     */
    public void removeAttribute(String key)
    {
        attributes.remove(key);
    }

    public Map<String, Object> getAttributes()
    {
        return attributes;
    }

    public Map<String, Object> getInputData()
    {
        return inputData;
    }
}
