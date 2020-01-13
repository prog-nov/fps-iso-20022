package com.forms.datapipe.validator;

import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

/**
 * @author lindeshu
 *
 */
public interface ValveValidator {
	
	void init(ValveConfig config, PipeContext pipeContext) throws DataPipeException;
	
	boolean validate(ValveConfig config, PipeData pipeData) throws DataPipeException;
	
	void close();
	
}
