package com.forms.datapipe.valve;

import java.util.List;

import com.forms.datapipe.Valve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

public class DataProcess implements Valve {

	public void close() throws DataPipeException 
	{
		// TODO Auto-generated method stub

	}

	public ValveConfig getConfig() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void init(ValveConfig config, PipeContext pipeContext)
			throws DataPipeException 
	{
		// TODO Auto-generated method stub

	}

	public List<ValveForward> process(ValveMapping valveMapping,
			PipeData pipeData, InputContext in, OutputContext out)
			throws DataPipeException 
	{
		// TODO Auto-generated method stub
		return valveMapping.findForwards("success");
	}

}
