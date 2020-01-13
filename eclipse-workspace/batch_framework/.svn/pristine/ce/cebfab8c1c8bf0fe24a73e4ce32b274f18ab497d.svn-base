package com.forms.datapipe.valve;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.config.valve.ValidateValveConfig;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.pipedata.PipeData;

/**
 * @author cxl
 * 
 */
public class ValidateValve
    implements Valve
{

    private static Log log = LogFactory.getLog(ValidateValve.class);

    private ValveConfig config;

    private ValidateValveConfig validateValveConfig;

    private ValidateValveConfigHandler validateValveConfigHandler;

    public void init(ValveConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");
        this.config = config;
        this.validateValveConfig = DataPipeConfigFactory.getValidateValveConfig(config.getConfigFile());
        this.validateValveConfigHandler = new ValidateValveConfigHandler(
            validateValveConfig);
        validateValveConfigHandler.initValveValidators(config, pipeContext);
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public List<ValveForward> process(ValveMapping valveMapping,
        PipeData pipeData, InputContext in, OutputContext out)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled()) log.debug(" [ method 'process' called. ] ");
        try
        {
            if (!validateValveConfigHandler.validate(config, pipeData))
            {
                return valveMapping.findForwards(validateValveConfig.getUnapproveForward());
            }
            return valveMapping.findForwards(validateValveConfig.getApproveForward());
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        finally
        {
            PerformanceMonitor.endRecord(this);
        }
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        validateValveConfigHandler.closeValveValidators();
    }

    public ValveConfig getConfig()
    {
        return config;
    }

    public ValidateValveConfig getValidateValveConfig()
    {
        return validateValveConfig;
    }

}
