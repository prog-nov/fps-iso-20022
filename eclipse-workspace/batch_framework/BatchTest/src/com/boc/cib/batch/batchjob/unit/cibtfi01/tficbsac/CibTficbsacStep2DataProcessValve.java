package com.boc.cib.batch.batchjob.unit.cibtfi01.tficbsac;

import java.util.List;

import com.boc.cib.batch.util.SysUtil;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.framework.job.common.valve.BaseOutputDataProcessValve;

public class CibTficbsacStep2DataProcessValve extends
		BaseOutputDataProcessValve
{
	public List<ValveForward> process(ValveMapping ip_valveMapping,
			PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
			throws DataPipeException
	{
		ip_pipeData.setPipeFieldValue("acDate", ip_in.getPipeContext()
				.getParameter("acDate"));
		String loc_cbsAc = ip_pipeData.getPipeFieldStringValue("cbsAc");
		ip_pipeData.setPipeFieldValue("bankNo", SysUtil.getOrgBranch(
				loc_cbsAc, SysUtil.TYPE_BK));
		return super.process(ip_valveMapping, ip_pipeData, ip_in, ip_out);
	}

}
