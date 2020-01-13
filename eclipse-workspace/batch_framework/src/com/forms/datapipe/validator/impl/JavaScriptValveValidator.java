package com.forms.datapipe.validator.impl;

import java.util.HashMap;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptableObject;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.valve.ValidateRule;
import com.forms.datapipe.config.valve.ValidateValveConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.pipedata.PipeFieldData;
import com.forms.datapipe.validator.ValveValidator;

/**
 * @author lindeshu
 * 
 */
public class JavaScriptValveValidator
    implements ValveValidator
{
    
    //private Context javascriptContext;

    private Map<String, Script> compiledScripts;

    private ValidateValveConfig validateValveConfig;

    public void init(ValveConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        validateValveConfig = DataPipeConfigFactory.getValidateValveConfig(config.getConfigFile());
        Context javascriptContext = Context.enter();
        compiledScripts = new HashMap<String, Script>();
        for (ValidateRule validateRule : validateValveConfig.getValidateRules().values())
        {
            Script compiledScript = javascriptContext.compileString(
                validateRule.getLogicExpress(), null, 1, null);
            compiledScripts.put(validateRule.getName(), compiledScript);
        }
        Context.exit();
    }

    public boolean validate(ValveConfig config, PipeData pipeData)
        throws DataPipeException
    {
        Context cx = null;
        try
        {
        	cx = Context.enter();
		    ScriptableObject scope = cx.initStandardObjects();
		    for (ValidateRule validateRule : validateValveConfig.getValidateRules().values())
		    {
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
		        
		        Object result = compiledScripts.get(validateRule.getName()).exec(cx, scope);
		        if (!(result instanceof Boolean))
		            throw new DataPipeException(
		                " [ The type of return value must be boolean! ValidateValve: "
		                    + config.getName() + ", ValidateRule: " + validateRule
		                    + " ] ");
		
		        if (!Context.toBoolean(result))
		        {
		            return false;
		        }
		    }
		    return true;
        } catch (Exception e)
        {
        	throw new DataPipeException(e);
        } finally
        {
        	Context.exit();
        }
    }

    public void close()
    {
        //Context.exit();
    }

}
