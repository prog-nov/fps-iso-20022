/*-----------------------------------------------------
 Property of BOCHK - Confidential
 
 
 Source File Name:		CibDataProcessValve.java 
 ----------------------------------------------------
 Date of Creation:		26 04 2011
 
 Last Modified:
 27 04 2011			    update the information of class

 -----------------------------------------------------
 Version 				    1.0.0.0
 ----------------------------------------------------- */

package com.boc.cib.batch.batchjob.unit.cibtfi01.tfiusrac;

import java.util.List;

import com.forms.framework.job.common.valve.BaseOutputDataProcessValve;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;

/*
 * deal with every data of the output file ifdirac.
 * 
 * @author Author name @author TXA
 */

public class CibTfiusracDataProcessValve extends BaseOutputDataProcessValve
{

	public List<ValveForward> process(ValveMapping ip_valveMapping,
			PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
			throws DataPipeException
	{
		ip_pipeData.setPipeFieldValue("ACDATE", ip_in.getPipeContext().getParameter("acDate"));
		return super.process(ip_valveMapping, ip_pipeData, ip_in, ip_out);
	}

}
