package com.forms.datapipe;

import java.util.List;

import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

/**
 * 阀门接口
 * 
 * @author cxl
 * 
 */
public interface Valve
{
    /**
     * init
     * 
     * @param config --
     *            ValveConfig
     * @param pipeContext --
     *            pipeContext
     * @throws DataPipeException
     */
    public void init(ValveConfig config, PipeContext pipeContext)
        throws DataPipeException;

    /**
     * 
     * @param valveMapping --
     *            valve
     * @param pipeData --
     *            flow data
     * @param in --
     *            input context
     * @param out --
     *            Output Context
     * @return List<ValveForward>(next Valves)
     * @throws DataPipeException
     */
    public List<ValveForward> process(ValveMapping valveMapping,
        PipeData pipeData, InputContext in, OutputContext out)
        throws DataPipeException;

    /**
     * release resource
     * 
     * @throws DataPipeException
     */
    public void close() throws DataPipeException;

    /**
     * get Config
     * 
     * @return Config
     */
    public ValveConfig getConfig();
}
