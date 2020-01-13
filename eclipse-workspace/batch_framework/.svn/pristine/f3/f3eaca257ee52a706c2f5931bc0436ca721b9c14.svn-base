package com.forms.datapipe.errorhandler;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Pipe;
import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

public class ErrorHandlerSkipValve implements Valve
{
	private static Log log = LogFactory.getLog(ErrorHandlerValve.class);

    private ValveConfig config;
    
    private boolean iserror=false;
    
    private String errMessage="";

    public void init(ValveConfig ip_config, PipeContext ip_pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = ip_config;

        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public List<ValveForward> process(ValveMapping ip_valveMapping,
        PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'process' called. ] ");

        //Exception e = (Exception) in.getAttribute(Pipe.EXCEPTION_KEY);
        ip_pipeData.setPipeFieldValue("errRowValue", ip_in.getPipeContext().getAttribute("rowValue"+ip_in.getInput().getRowIndex()));
        ip_pipeData.setPipeFieldValue("rowIndex", ip_in.getInput().getRowIndex());
        iserror=true;
        errMessage=ip_in.getAttribute(Pipe.EXCEPTION_KEY)==null?"":ip_in.getAttribute(Pipe.EXCEPTION_KEY).toString();
        throw new DataPipeException("Datapipe error:"+errMessage);
       
    }

    public void close() throws DataPipeException
    {    	
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        //do nothing
        if(iserror){
        	throw new DataPipeException("Datapipe error:"+errMessage);
        }
    }

    public ValveConfig getConfig()
    {
        return config;
    }
}
