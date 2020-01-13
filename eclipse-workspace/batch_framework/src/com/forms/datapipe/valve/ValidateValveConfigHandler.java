package com.forms.datapipe.valve;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.valve.JavaValidator;
import com.forms.datapipe.config.valve.ValidateRule;
import com.forms.datapipe.config.valve.ValidateValveConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.datapipe.validator.ValveValidator;
import com.forms.datapipe.validator.impl.JavaScriptValveValidator;

/**
 * @author lindeshu
 *
 */
public class ValidateValveConfigHandler {

	private final static Log log = LogFactory.getLog(ValidateValveConfigHandler.class);
	
	private Map<String, ValveValidator> valveValidators;

	public ValidateValveConfigHandler(ValidateValveConfig config) throws DataPipeException {
		this.valveValidators = new HashMap<String, ValveValidator>();
		for(Map.Entry<String, ValidateRule> entry : config.getValidateRules().entrySet()) {
			valveValidators.put(entry.getKey(), 
					new JavaScriptValveValidator());
		}
		for(Map.Entry<String, JavaValidator> entry : config.getJavaValidators().entrySet()) {
			ValveValidator validator = 
						(ValveValidator)DataPipeUtils.newInstance(entry.getValue().getClassName());
			valveValidators.put(entry.getKey(), validator);
		}
	}
	
	public Map<String, ValveValidator> getValveValidators() {
		return Collections.unmodifiableMap(valveValidators);
	}
	
	public void initValveValidators(ValveConfig config, PipeContext pipeContext) throws DataPipeException {
		for(Map.Entry<String, ValveValidator> entry : valveValidators.entrySet()) {
			String name = entry.getKey();
			ValveValidator validator = entry.getValue();
			debug("Initialize the valve validator: " + name);
			validator.init(config, pipeContext);
		}
	}
	
	public boolean validate(ValveConfig config, PipeData pipeData) throws DataPipeException {
		for(Map.Entry<String, ValveValidator> entry : valveValidators.entrySet()) {
			String name = entry.getKey();
			ValveValidator validator = entry.getValue();
			debug("Execute the valve validator: " + name);
			if(!validator.validate(config, pipeData)) {
        		return false;
        	}
		}
		return true;
	}
	
	public void closeValveValidators() {
		for(Map.Entry<String, ValveValidator> entry : valveValidators.entrySet()) {
			String name = entry.getKey();
			ValveValidator validator = entry.getValue();
			debug("Close the valve validator: " + name);
			validator.close();
		}
	}
	
	private static void debug(String message) {
		if(log.isDebugEnabled()) {
			log.debug(message);
		}
	}
}
