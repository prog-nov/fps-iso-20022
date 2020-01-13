package com.forms.datapipe.valve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.config.valve.FilterCase;
import com.forms.datapipe.config.valve.FilterValveConfig;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.pipedata.PipeFieldData;

/**
 * 分流阀门
 * 
 * @author cxl
 * 
 */
public class FilterValve
    implements Valve
{
    private static Log log = LogFactory.getLog(FilterValve.class);

    private ValveConfig config;

    private FilterValveConfig filterValveConfig;

    private Map<String, Script> compiledScripts = new HashMap<String, Script>(); // 预编译的JS脚本

    //private Context cx;
    
    public void init(ValveConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        filterValveConfig = DataPipeConfigFactory.getFilterValveConfig(config.getConfigFile());
        Context cx = Context.enter();
        // Ԥ预编译每个CASE里的JS脚本
        for (FilterCase filterCase : filterValveConfig.getFilterCases().values())
        {
            Script s = cx.compileString(filterCase.getLogicExpress(), null, 1,
                null);
            compiledScripts.put(filterCase.getName(), s);
        }
        Context.exit();
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public List<ValveForward> process(ValveMapping valveMapping,
        PipeData pipeData, InputContext in, OutputContext out)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled()) log.debug(" [ method 'process' called. ] ");
        List<ValveForward> forwards = new ArrayList<ValveForward>();
        Context cx = null;
        try
        {
            cx = Context.enter();
            ScriptableObject scope = cx.initStandardObjects();
            // 执行每个CASE里的JS脚本
            for (FilterCase filterCase : filterValveConfig.getFilterCases().values())
            {
                if (log.isDebugEnabled())
                    log.debug(" [ execute filter case: " + filterCase.getName()
                        + " ]");

                if (pipeData.getInputDatas() == null)
                {
                	// 將pipeData裏的變量都定義為JS腳本的變量，因爲腳本裏可能會改變變量的值，所以每次循環需要重新設置
                    for (PipeFieldData pipeFieldData : pipeData.getPipeFieldDatas().values())
                    {
                        scope.put(pipeFieldData.getDefinition().getName(), scope,
                            pipeFieldData.getValue());
                    }
                } else
                {
                	for (FieldData fieldData : pipeData.getInputDatas())
                	{
                		// 僅將非循環數據放置到腳本變量定義中
                		if (fieldData.getFieldDatas() == null)
                		{
                			scope.put(fieldData.getName(), scope, fieldData.getValue());
                		}
                	}
                }

                // 执行JS脚本
                Script compiledScript = compiledScripts.get(filterCase.getName());
                Object result = compiledScript.exec(cx, scope);
                if (!(result instanceof Boolean))
                    throw new DataPipeException(
                        " [ The type of return value must be boolean! FilterValve: "
                            + config.getName() + ", FilterCase: " + filterCase
                            + ". ] ");

                if (log.isDebugEnabled())
                    log.debug(" [ result: " + Context.toBoolean(result) + " ]");
                if (Context.toBoolean(result))
                {
                    ValveForward forward = valveMapping.findForward(filterCase.getForward());
                    if (!forwards.contains(forward)) forwards.add(forward);
                }
            }
            return forwards;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new DataPipeException(e);
        } finally
        {
            Context.exit();
            PerformanceMonitor.endRecord(this);
        }
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        //Context.exit();
    }

    public ValveConfig getConfig()
    {
        return config;
    }

    public FilterValveConfig getFilterValveConfig()
    {
        return filterValveConfig;
    }

}