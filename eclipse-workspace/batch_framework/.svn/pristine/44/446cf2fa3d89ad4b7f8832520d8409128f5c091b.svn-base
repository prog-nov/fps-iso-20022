package com.forms.datapipe.input;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.FieldDefintionHandler;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.input.Handler;
import com.forms.datapipe.config.input.Handlers;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.datapipe.util.LogUtils;

/**
 * @author lindeshu
 *
 */
public class FieldDefinitionHandler {

	private final static Log log = LogFactory.getLog(FieldDefinitionHandler.class);
	
	private Map<String, FieldDefintionHandler> fieldDefintionHandlers;

	public FieldDefinitionHandler(Handlers handlers) throws DataPipeException {
		this.fieldDefintionHandlers = new HashMap<String, FieldDefintionHandler>();
		if(handlers != null && handlers.getHandlers() != null) {
			for(Handler handler : handlers.getHandlers()) {
				FieldDefintionHandler fieldDefintionHandler = (FieldDefintionHandler)DataPipeUtils.newInstance(handler.getClassName());
				fieldDefintionHandlers.put(handler.getName(), fieldDefintionHandler);
			}
		}
	}
	
	public Map<String, FieldDefintionHandler> getConfigHandlers() {
		return Collections.unmodifiableMap(fieldDefintionHandlers);
	}
	
	public boolean handle(FieldDefinition config, PipeContext pipeContext) throws DataPipeException {
		for(Map.Entry<String, FieldDefintionHandler> entry : fieldDefintionHandlers.entrySet()) {
			String name = entry.getKey();
			FieldDefintionHandler handler = entry.getValue();
			LogUtils.debug(log, "Execute the field definition handler: " + name);
			handler.handle(config, pipeContext);
		}
		return true;
	}
}
	
