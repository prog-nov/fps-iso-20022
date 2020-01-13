package com.forms.framework.job.common.validate;

import java.lang.reflect.Method;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.validator.ValveValidator;

public class BaseValidate implements ValveValidator
{
	private int index = 0;

	private PipeContext pipeContext;

	public void close()
	{
	}

	public void init(ValveConfig ip_config, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		this.pipeContext = ip_pipeContext;
	}

	public boolean validate(ValveConfig ip_config, PipeData ip_pipeData)
			throws DataPipeException
	{
		if (pipeContext.getAttribute("indocCnt") == null)
		{
			index = 0;
		}
		pipeContext.setAttribute("indocCnt", ++index);

		if (pipeContext.getAttribute("inRecLen") != null)
		{
			InputConfig loc_inputConfig = pipeContext.getPipeConfig().getInputs()
					.get(pipeContext.getAttribute("inName"));
			String loc_className = loc_inputConfig.getClazz().substring(
					loc_inputConfig.getClazz().lastIndexOf(".") + 1);
			try
			{
				int loc_len = 0;
				Method loc_m = DataPipeConfigFactory.class.getMethod("get"
						+ loc_className + "Config", String.class);
				Object loc_inCfg = loc_m.invoke(null, loc_inputConfig.getConfigFile());
				FieldDefinition loc_f = (FieldDefinition) loc_inCfg.getClass()
						.getMethod("getFieldDefinition").invoke(loc_inCfg);
				for (Field loc_field : loc_f.getFields())
				{
					loc_len += loc_field.getBytes();
				}
				if (loc_len != Integer.parseInt((String) pipeContext
						.getAttribute("inRecLen")))
				{
					throw new DataPipeException("head length:"+ pipeContext
							.getAttribute("inRecLen")+",data length:"+loc_len);
				}
			} catch (Exception ip_e)
			{
				throw new DataPipeException(ip_e);
			}
		}
		return true;
	}
}
