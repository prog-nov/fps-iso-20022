package com.forms.ffp.persistents.service.ss;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.forms.ffp.persistents.bean.ss.FFPSsSystem;
import com.forms.ffp.persistents.dao.ss.FFPIDao_System;

@Service("FFPDaoService_System")
public class FFPDaoService_System implements FFPIDaoService_System
{
	@Resource(name = "FFPIDao_System")
	private FFPIDao_System dao;

	@Override
	public int update(FFPSsSystem form) throws Exception
	{
		return dao.update(form);
	}

	@Override
	public FFPSsSystem select() throws Exception
	{
		// TODO Auto-generated method stub
		return dao.inquiry();
	}

	@Override
	public int updateRealtimeListenerStat(String realtimeListenerStat)
	{
		return dao.updateRealtimeListenerStat(realtimeListenerStat);
	}
}
