package com.forms.framework.job.common.valve;

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
import com.forms.framework.util.CommonAPI;

public class BaseOutputDataProcessValve implements Valve
{
	protected ValveConfig config;

	protected int docCnt = 0;

	/* (non-Javadoc)
	 * @see com.forms.datapipe.Valve#close()
	 */
	public void close() throws DataPipeException
	{
		docCnt = 0;
	}

	/* (non-Javadoc)
	 * @see com.forms.datapipe.Valve#getConfig()
	 */
	public ValveConfig getConfig()
	{
		return config;
	}

	/* (non-Javadoc)
	 * @see com.forms.datapipe.Valve#init(com.forms.datapipe.config.ValveConfig, com.forms.datapipe.context.PipeContext)
	 */
	public void init(ValveConfig ip_config, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		this.config = ip_config;
	}

	/* (non-Javadoc)
	 * @see com.forms.datapipe.Valve#process(com.forms.datapipe.ValveMapping, com.forms.datapipe.pipedata.PipeData, com.forms.datapipe.context.InputContext, com.forms.datapipe.context.OutputContext)
	 */
	public List<ValveForward> process(ValveMapping ip_valveMapping,
			PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
			throws DataPipeException
	{
		// data count for multiProcess
		synchronized (config)
		{
			docCnt++;
			ip_in.getPipeContext().setAttribute("docCnt", docCnt);
		}

		return ip_valveMapping.findForwards(CommonAPI.DATAPIPE_SUCCESS);
	}
}
