package com.forms.ffp.persistents.service.ss;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.ss.FFPSsCutoff;
import com.forms.ffp.persistents.dao.ss.FFPIDao_Cutoff;

@Service("FFPDaoService_Cutoff")
public class FFPDaoService_Cutoff implements FFPIDaoService_Cutoff
{
	@Resource(name = "FFPIDao_Cutoff")
	private FFPIDao_Cutoff dao;

	@Override
	public FFPSsCutoff inquiry(String cutoffType) throws Exception
	{
		return dao.inquiry(cutoffType);
	}
}
