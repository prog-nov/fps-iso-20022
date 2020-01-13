package com.boc.cib.batch.batchjob.unit.cibtkn04;

import java.util.List;

import com.boc.cib.batch.util.SysUtil;
import com.forms.datapipe.ValveMapping;
import com.forms.datapipe.config.ValveForward;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.pipedata.PipeData;
import com.forms.framework.job.common.valve.BaseOutputDataProcessValve;
import com.forms.framework.util.StringUtil;

public class CibCheckTscFileDataProcessValve extends
		BaseOutputDataProcessValve 
	{
	
	public List<ValveForward> process(ValveMapping ip_valveMapping,
			PipeData ip_pipeData, InputContext ip_in, OutputContext ip_out)
			throws DataPipeException 
	{
		String loc_status = ip_pipeData.getPipeFieldStringValue("tokenStatus");
		String loc_tscBankCode = ip_pipeData
				.getPipeFieldStringValue("bankCode");
		String loc_cbsAc = ip_pipeData.getPipeFieldStringValue("cbsAc");
		String loc_userId = ip_pipeData.getPipeFieldStringValue("userId");
		String loc_cibBankCode = null;
		String loc_errorMsg = null;
		boolean loc_flag = true;
		if ("D".equals(loc_status)) 
		{
			//For damage case,if tsc response bank code is not in('012','039''043')
			if (!("012".equals(loc_tscBankCode)
					|| "039".equals(loc_tscBankCode) || "043"
					.equals(loc_tscBankCode)))
			{
				loc_errorMsg = "Error bank code from TSC response file :"
					+ "CBS_AC=" + loc_cbsAc + ",USER_ID=" + loc_userId
					+ ",TSC_BANK_CODE=" + loc_tscBankCode + ",TOKEN_STATUS="
					+ loc_status + ",DAMAGED_TSC_BANK_CODE not in ('012','039','043')";
				loc_flag = false;
			}
		} else 
		{
			//For Normal case,if tsc response bank code not equal cib bank code
			loc_cibBankCode = SysUtil.getOrgBranch(loc_cbsAc, SysUtil.TYPE_BK);
			if (!StringUtil.compareString(loc_cibBankCode, loc_tscBankCode))
			{
				loc_errorMsg = "Error bank code from TSC response file :"
					+ "CBS_AC=" + loc_cbsAc + ",USER_ID=" + loc_userId
					+ ",TSC_BANK_CODE=" + loc_tscBankCode + ",TOKEN_STATUS="
					+ loc_status + ",CIB_BANK_CODE=" + loc_cibBankCode;
				loc_flag = false;
			}
		}
		if (!loc_flag) 
		{
			throw new DataPipeException(loc_errorMsg);
		}
		return super.process(ip_valveMapping, ip_pipeData, ip_in, ip_out);
	}
}
