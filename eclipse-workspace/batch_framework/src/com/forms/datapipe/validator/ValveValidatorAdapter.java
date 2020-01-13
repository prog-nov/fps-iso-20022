package com.forms.datapipe.validator;

import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

/**
 * {@link ValveValidator} adapter. Default do nothing;
 * @author lindeshu
 *
 */
public class ValveValidatorAdapter implements ValveValidator{

	public void init(ValveConfig config, PipeContext pipeContext)
			throws DataPipeException {
	}

	public boolean validate(ValveConfig config, PipeData pipeData)
			throws DataPipeException {
		return true;
	}

	public void close() {
	}

}
