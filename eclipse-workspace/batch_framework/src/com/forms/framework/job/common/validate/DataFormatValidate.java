package com.forms.framework.job.common.validate;

import java.lang.reflect.Method;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.validator.ValveValidator;

public class DataFormatValidate implements ValveValidator
{
	private PipeContext pipeContext;

	private static String validateSuccess = "S";

	private static String validateFail = "F";

	private static int infoBytes = 0;

	public void close()
	{
	}

	public void init(ValveConfig ip_config, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		this.pipeContext = ip_pipeContext;
		Object[] outputs = pipeContext.getPipeConfig().getOutputs().values()
				.toArray();
		for (int i = 0; i < outputs.length; i++)
		{
			OutputConfig outputConfig = (OutputConfig) outputs[i];
			if ("export".equals(outputConfig.getFromValve()))
			{
				try
				{
					String loc_className = outputConfig.getClazz().substring(
							outputConfig.getClazz().lastIndexOf(".") + 1);
					Method loc_m = DataPipeConfigFactory.class.getMethod("get"
							+ loc_className + "Config", String.class);
					Object loc_inCfg = loc_m.invoke(null, outputConfig
							.getConfigFile());
					FieldDefinition loc_f = (FieldDefinition) loc_inCfg
							.getClass().getMethod("getFieldDefinition").invoke(
									loc_inCfg);
					for (Field loc_field : loc_f.getFields())
					{
						if ("validateInfo".equals(loc_field.getName()))
						{
							if (infoBytes != 0
									&& infoBytes != loc_field.getBytes())
							{
								throw new DataPipeException(
										"Different bytes length (" + infoBytes
												+ "," + loc_field.getBytes()
												+ ") in output config file!");
							}
							infoBytes = loc_field.getBytes();
						}
					}
				} catch (Exception ip_e)
				{
					throw new DataPipeException(ip_e);
				}
			}
		}
	}

	public boolean validate(ValveConfig ip_config, PipeData ip_pipeData)
			throws DataPipeException
	{
		try
		{
			// Get method name
			InputConfig loc_inputConfig = pipeContext.getPipeConfig()
					.getInputs().get(pipeContext.getAttribute("inName"));
			String loc_className = loc_inputConfig.getClazz().substring(
					loc_inputConfig.getClazz().lastIndexOf(".") + 1);
			Method loc_m = DataPipeConfigFactory.class.getMethod("get"
					+ loc_className + "Config", String.class);
			Object loc_inCfg = loc_m.invoke(null, loc_inputConfig
					.getConfigFile());

			// Get field definition
			FieldDefinition loc_f = (FieldDefinition) loc_inCfg.getClass()
					.getMethod("getFieldDefinition").invoke(loc_inCfg);

			// Validate field format
			String loc_validateMethod;
			String loc_result = validateSuccess;
			boolean loc_success = true;
			StringBuffer loc_errorInfo = new StringBuffer();
			// Actual number of columns which validate faild
			int loc_actualIndex = 0;
			// Write to validateInfo number of columns which validate faild
			int loc_writeIndex = 0;
			for (Field loc_field : loc_f.getFields())
			{
				loc_validateMethod = loc_field.getValidateMethod();
				if (loc_validateMethod != null
						&& !"".equals(loc_validateMethod))
				{
					String loc_fieldValue = ip_pipeData
							.getPipeFieldStringValue(loc_field.getName());
					Object flag = DataFormat.class.getMethod(
							loc_validateMethod, String.class).invoke(null,
							loc_fieldValue);
					String loc_error = loc_field.getName() + ":["
							+ loc_fieldValue + "] faild;";
					if (!(Boolean) flag)
					{
						loc_actualIndex++;
					}
					if (!(Boolean) flag
							&& (loc_errorInfo.length() + loc_error.length()) < infoBytes - 3)
					{
						loc_errorInfo.append(loc_error);
						loc_writeIndex++;
					}

					if (loc_success && !(Boolean) flag)
					{
						loc_result = validateFail;
						loc_success = false;
					}
				}
			}

			// Append ... means validateInfo length is not enough
			if (loc_writeIndex < loc_actualIndex)
			{
				loc_errorInfo.append("...");
			} else
			{
				//Delete last delimiter ;
				if (loc_errorInfo.lastIndexOf(";") > -1)
				{
					loc_errorInfo.replace(0, loc_errorInfo.length(),
							loc_errorInfo.substring(0, loc_errorInfo
									.lastIndexOf(";")));
				}
			}
			ip_pipeData.setPipeFieldValue("validateInfo", loc_errorInfo.toString().toUpperCase());
			ip_pipeData.setPipeFieldValue("validateResult", loc_result);
			return true;
		} catch (Exception ip_e)
		{
			throw new DataPipeException(ip_e);
		}
	}
}
