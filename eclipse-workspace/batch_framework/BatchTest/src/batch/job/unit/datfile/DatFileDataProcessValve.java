package batch.job.unit.datfile;

import java.util.ArrayList;
import java.util.List;

import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.compare.CmpBean;
import com.forms.datapipe.compare.CmpXMLInit;
import com.forms.datapipe.compare.CmpXMLUtil;
import com.forms.datapipe.config.ValveConfig;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.framework.job.common.valve.BaseOutputDataProcessValve;
import com.forms.framework.util.CommonAPI;

public class DatFileDataProcessValve extends BaseOutputDataProcessValve
{
	private List<CmpBean> list = new ArrayList<CmpBean>();

	public void init(ValveConfig ip_config, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		super.init(ip_config, ip_pipeContext);
		try
		{
			list = CmpXMLInit.initXML(ip_pipeContext.getParameter("compareConfigFile"));
		} catch (Exception ip_e)
		{
			throw new DataPipeException(ip_e);
		}
	}
	
	public List<ValveForward> process(ValveMapping ip_valveMapping,
			PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
			throws DataPipeException
	{
		if (ip_pipeData.getPipeFieldStringValue("deltaFlag").toString()
				.trim().equals("U"))
		{
			if (!CmpXMLUtil.dataCompare(list, ip_out, ip_pipeData))
			{
				return ip_valveMapping.findForwards(CommonAPI.DATAPIPE_FAILUE);
			}
		}
		return super.process(ip_valveMapping, ip_pipeData, ip_in, ip_out);
	}

}
