package com.forms.datapipe;

import java.util.ArrayList;
import java.util.List;

import com.forms.datapipe.config.PipeConfig;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.exception.DataPipeException;

/**
 * ValveMapping类似于struts的ActionMapping
 * 
 * @author cxl
 * 
 */
public class ValveMapping
{
    private PipeConfig pipeConfig;

    private ValveConfig valveConfig;

    public ValveMapping(PipeConfig pipeConfig, ValveConfig valveConfig)
    {
        this.pipeConfig = pipeConfig;
        this.valveConfig = valveConfig;
    }

    public ValveForward findForward(String name) throws DataPipeException
    {
        ValveForward forward = valveConfig.getForwards().get(name);
        if (forward != null) return forward;

        //Ȼ������ȫ��forward
        forward = pipeConfig.getGlobalForwards().get(name);

        if (forward == null)
            throw new DataPipeException("[ Invalid forward name: " + name
                + ", Valve: " + valveConfig.getName() + " ]");

        return forward;
    }

    public List<ValveForward> findForwards(String... names)
        throws DataPipeException
    {
        List<ValveForward> forwards = new ArrayList<ValveForward>();
        for (String name : names)
        {
            forwards.add(findForward(name));
        }
        return forwards;
    }
}
