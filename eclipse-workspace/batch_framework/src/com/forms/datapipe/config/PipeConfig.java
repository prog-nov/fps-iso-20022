package com.forms.datapipe.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 对应配置文件pipe-config.xml
 * 
 * @author cxl
 * 
 */
public class PipeConfig
{
    private PipeDataConfig pipeData;
    
    private MultiThreadConfig multiThreadConfig;
    
    private String stopAtErr;

    private Map<String, InputConfig> inputs = new HashMap<String, InputConfig>();

    private Map<String, OutputConfig> outputs = new HashMap<String, OutputConfig>();

    private Map<String, ValveForward> globalForwards = new HashMap<String, ValveForward>();

    private Map<String, ValveConfig> valves = new HashMap<String, ValveConfig>();

    public void addInput(InputConfig input)
    {
        inputs.put(input.getName(), input);
    }

    public void addOutput(OutputConfig output)
    {
        outputs.put(output.getName(), output);
    }

    public void addGlobalForward(ValveForward forward)
    {
        globalForwards.put(forward.getName(), forward);
    }

    public void addValve(ValveConfig valve)
    {
        valves.put(valve.getName(), valve);
    }

    public PipeDataConfig getPipeData()
    {
        return pipeData;
    }

    public void setPipeData(PipeDataConfig pipeData)
    {
        this.pipeData = pipeData;
    }

    public Map<String, InputConfig> getInputs()
    {
        return inputs;
    }

    public void setInputs(Map<String, InputConfig> inputs)
    {
        this.inputs = inputs;
    }

    public Map<String, OutputConfig> getOutputs()
    {
        return outputs;
    }

    public void setOutputs(Map<String, OutputConfig> outputs)
    {
        this.outputs = outputs;
    }

    public Map<String, ValveForward> getGlobalForwards()
    {
        return globalForwards;
    }

    public void setGlobalForwards(Map<String, ValveForward> globalForwards)
    {
        this.globalForwards = globalForwards;
    }

    public Map<String, ValveConfig> getValves()
    {
        return valves;
    }

    public void setValves(Map<String, ValveConfig> valves)
    {
        this.valves = valves;
    }

    public void setMultiThreadConfig(MultiThreadConfig multiThreadConfig)
    {
        this.multiThreadConfig = multiThreadConfig;
    }

    public MultiThreadConfig getMultiThreadConfig()
    {
        return multiThreadConfig;
    }

	public String getStopAtErr() {
		return stopAtErr;
	}

	public void setStopAtErr(String stopAtErr) {
		this.stopAtErr = stopAtErr;
	}
}
