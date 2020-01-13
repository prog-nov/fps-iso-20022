package com.forms.datapipe.input.header;

import java.util.Map;

import com.forms.datapipe.Input;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;

public class FixedFileInputFooterHandler implements FileInputFooterHandler {

	public Map<String, String> handle(Input input, PipeContext pipeContext,
			String string) throws DataPipeException 
	{
		return null;
	}

}
