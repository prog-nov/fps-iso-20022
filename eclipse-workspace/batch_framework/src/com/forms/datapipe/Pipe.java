package com.forms.datapipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.config.PipeConfig;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.exception.DataPipeFatalException;
import com.forms.datapipe.exception.NestedDataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.datapipe.valve.ExportValve;
import com.forms.datapipe.valve.ImportValve;

/**
 * 管道数据处理
 * 
 * @author cxl
 * 
 */
public class Pipe
{
    private static Log log = LogFactory.getLog(Pipe.class);

    private PipeConfig pipeConfig;

    private Map<String, Valve> valves;

    private DataPipe dataPipe;

    /*
     * global-forward
     */
    public static final String ERROR_FORWARD = "error";

    private Valve errorHandler;

    /*
     * 异常信息key,保存在输入介质上下文InputContext
     */
    public static final String EXCEPTION_KEY = Pipe.class.getName() + ".error";

    private int rowIndex;

    /**
     * 行号
     * 
     * @return
     */
    public int getRowIndex()
    {
        return rowIndex;
    }

    /**
     * init
     * 
     * @param pipeContext
     * @throws DataPipeException
     */
    public void init(PipeContext pipeContext, DataPipe dataPipe)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");
        pipeConfig = pipeContext.getPipeConfig();
        this.dataPipe = dataPipe;
        // 1. new instance and init
        if (log.isDebugEnabled()) log.debug(" [ init valves... ] ");
        valves = new HashMap<String, Valve>();
        for (ValveConfig valveConfig : pipeConfig.getValves().values())
        {
            if (log.isDebugEnabled())
                log.debug(" [ init valve: " + valveConfig.getName() + ".. ] ");

            Valve valve = (Valve)DataPipeUtils.newInstance(valveConfig.getClazz());
            valves.put(valveConfig.getName(), valve);
            valve.init(valveConfig, pipeContext);
        }

        // prepare error-handler
        if (log.isDebugEnabled()) log.debug(" [ prepare error-handler... ] ");
        ValveForward error = pipeConfig.getGlobalForwards().get(ERROR_FORWARD);
        if (error == null)
        {
            throw new DataPipeException(
                "[ global-forward 'error' must be defined! ]");
        } else
        {
            errorHandler = valves.get(error.getNextValve());
            if (errorHandler == null)
                throw new DataPipeException(
                    "[ Invalid nextValve of Global ValveForward '" + error
                        + "'! ]");
        }
        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success! ] ");
    }

    /**
     * data transfer
     * 
     * @param input --
     *            InputContext
     * @param output --
     *            OutputContext
     * @throws DataPipeException
     */
    public void transfer(InputContext in, OutputContext out)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled()) log.debug(" [ method 'transfer' called. ] ");
        rowIndex++;

        PipeData pipeData = new PipeData(pipeConfig.getPipeData());
        invokeValve(null, pipeData, in, out);

        if (log.isDebugEnabled()) log.debug(" [ success! ] ");
        PerformanceMonitor.endRecord(this);
    }

    /**
     * invokeValve
     * 
     * @param valve
     * @param pipeData
     * @param in
     * @param out
     * @throws DataPipeException
     */
    private void invokeValve(Valve valve, PipeData pipeData, InputContext in,
        OutputContext out) throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'invokeValve' called. ] ");

        try
        {
            invokeValve0(valve, pipeData, in, out);
        } catch (NestedDataPipeException e)
        {
            throw e;
        } catch (DataPipeFatalException e)
        {
            throw e;
        } catch (DataPipeException e)
        {
            handleError(e, pipeData, in, out);
            if ("true".equalsIgnoreCase(pipeConfig.getStopAtErr()))
            {
                // 遇错停止，不再继续处理后续数据
                dataPipe.setErrFlag(true);
                dataPipe.clear();
            }
        }
    }

    private void invokeValve0(Valve valve, PipeData pipeData, InputContext in,
        OutputContext out) throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'invokeValve0' called. ] ");

        if (valve == null)
        {
            _import(pipeData, in, out);
            return;
        }

        if (log.isDebugEnabled())
            log.debug(" [ call valve: " + valve.getConfig().getName()
                + "... ] ");
        List<ValveForward> forwards = valve.process(new ValveMapping(
            pipeConfig, valve.getConfig()), pipeData, in, out);

        // ExportValve is the end
        if (valve instanceof ExportValve) return;

        if (log.isDebugEnabled()) log.debug(" [ handle forwards... ] ");
        if (forwards == null || forwards.isEmpty())
            throw new DataPipeException("[ The return forward of valve '"
                + valve.getConfig().getName() + "' cannot be null and empty! ]");

        for (int i = 0; i < forwards.size(); i++)
        {
            ValveForward forward = forwards.get(i);
            Valve nextValve = valves.get(forward.getNextValve());
            if (nextValve == null)
                throw new DataPipeException(
                    "[ Invalid nextValve of ValveForward '" + forward
                        + "' at valve '" + valve.getConfig().getName() + "' ]");

            PipeData ownPipeData = pipeData;
            if (forwards.size() > 1 && i < forwards.size() - 1)
            {
                ownPipeData = PipeData.clone(pipeData); // each flow should use
                // its own data
            }
            invokeValve(nextValve, ownPipeData, in, out);
        }
    }

    /**
     * save exception to InputContext and invoke errorHandler valve
     * 
     * @param e
     * @param pipeData
     * @param in
     * @param out
     * @throws DataPipeException
     */
    private void handleError(DataPipeException e, PipeData pipeData,
        InputContext in, OutputContext out) throws DataPipeException
    {
        if (log.isWarnEnabled())
            log.warn(" [ method 'handleError' called. ] ");

        if (in.getAttribute(EXCEPTION_KEY) != null)
        {
            if (log.isFatalEnabled())
                log.fatal(" [ new exception was thrown when calling errorHandler!  ] ");

            if (log.isFatalEnabled())
                log.fatal(" [ print old exception stack trace...  ] ");
            ((Exception) in.getAttribute(EXCEPTION_KEY)).printStackTrace();

            if (log.isFatalEnabled())
                log.fatal(" [ print new exception stack trace...  ] ");
            e.printStackTrace();

            throw new NestedDataPipeException(e);
        }

        in.setAttribute(EXCEPTION_KEY, e); // save exception to InputContext

        // call errorHandler
        invokeValve(errorHandler, pipeData, in, out);
    }

    /**
     * find and invoke ImportValve
     * 
     * @param in
     * @param out
     * @throws DataPipeException
     */
    private void _import(PipeData pipeData, InputContext in, OutputContext out)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method '_import' called. ] ");

        String valveId = in.getInput().getConfig().getToValve();
        Valve valve = valves.get(valveId);
        if (valve instanceof ImportValve)
        {
            invokeValve(valve, pipeData, in, out);
        } else
        {
            throw new DataPipeException("[ Invalid to-valve '" + valveId
                + "' of Input '" + in.getInput().getConfig().getName()
                + "' , must be a ImportValve!  ]");
        }
    }

    /**
     * release resource
     * 
     * @throws DataPipeException
     */
    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");

        Map<String, DataPipeException> errors = new HashMap<String, DataPipeException>();
        if (valves != null)
        {
            for (String key : valves.keySet())
            {
                if (log.isDebugEnabled())
                    log.debug(" [ try to close valve: " + key + "... ] ");
                try
                {
                    valves.get(key).close();
                } catch (DataPipeException e)
                {
                    errors.put(key, e);
                    continue;
                }
            }
        }

        if (!errors.isEmpty())
        {
            if (log.isDebugEnabled()) log.debug(" [ handle exception... ] ");
            StringBuffer errMsg = new StringBuffer(
                "[ Error occurs in Pipe.close, detail:");
            for (String key : errors.keySet())
            {
                errMsg.append("[ valve:");
                errMsg.append(key);
                errMsg.append(", exception:");
                errMsg.append(errors.get(key).getMessage());
                errMsg.append("]");
            }
            errMsg.append(" ]");

            throw new DataPipeException(errMsg.toString());
        }

        if (log.isDebugEnabled()) log.debug(" [ success! ] ");
    }

    public Map<String, Valve> getValves()
    {
        return valves;
    }

    public Valve getErrorHandler()
    {
        return errorHandler;
    }
}
