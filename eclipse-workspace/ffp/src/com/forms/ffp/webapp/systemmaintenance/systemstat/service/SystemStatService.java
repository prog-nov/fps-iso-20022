package com.forms.ffp.webapp.systemmaintenance.systemstat.service;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.util.Tool;
import com.forms.ffp.bussiness.common.FFPTxSwitchModeSevice;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.service.FFPListenerAgentSvc;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.persistents.bean.ss.FFPSsSystem;
import com.forms.ffp.persistents.dao.ss.FFPIDao_System;
import com.forms.ffp.webapp.systemmaintenance.systemstat.form.SystemStatForm;

@Service("SystemStatService")
@Scope("prototype")
public class SystemStatService implements ISystemStatService
{

	@Resource(name = "FFPIDao_System")
	private FFPIDao_System dao;
	
	@Autowired
	private BeanFactory beanFactory;
	
	@Autowired
	private FFPListenerAgentSvc listenerSvc;

	@Override
	public SystemStatForm sList()
	{
		SystemStatForm form = new SystemStatForm();
		FFPSsSystem system = dao.inquiry();
		if (system != null)
		{
			form.setBatchAcdt(FFPDateUtils.convertDateToString(system.getBatchAcdt(), FFPDateUtils.ISO_DATE_FORMAT));
			form.setRunningMode(system.getRunningMode());
			form.setFpsReceiveMode(system.getFpsReceiveMode());
			form.setRealtimeListenerStat(system.getRealtimeListenerStat());
			form.setRealtimeControlStat(system.getRealtimeControlStat());
			form.setBatchListenerStat(system.getBatchListenerStat());
			
			Locale locale = Tool.LOCALE.getCurrentLocale();
			form.setRunningModeLabel(Tool.LOCALE.getMessage("ffp.systemmaintenance.systemstat.runningMode." + system.getRunningMode(), locale));
			form.setFpsReceiveModeLabel(Tool.LOCALE.getMessage("ffp.systemmaintenance.systemstat.fpsReceiveMode." + system.getFpsReceiveMode(), locale));
			form.setRealtimeListenerStatLabel(Tool.LOCALE.getMessage("ffp.systemmaintenance.systemstat.realtimeListenerStat." + system.getRealtimeListenerStat(), locale));
			form.setRealtimeControlStatLabel(Tool.LOCALE.getMessage("ffp.systemmaintenance.systemstat.realtimeControlStat." + system.getRealtimeControlStat(), locale));
			form.setBatchListenerStatLabel(Tool.LOCALE.getMessage("ffp.systemmaintenance.systemstat.batchListenerStat." + system.getBatchListenerStat(), locale));
		}
		return form;
	}

	@Override
	public SystemStatForm switchReceiveMode(SystemStatForm form) throws Exception
	{
		if(FFPConstants.FPS_RECEIVE_MODE_REALTIME.equals(form.getFpsReceiveMode()))
		{
			FFPTxSwitchModeSevice mode = beanFactory.getBean(FFPTxSwitchModeSevice.class);
			mode.init(FFPConstants.FPS_RECEIVE_MODE_BATCH, null);
			mode.perform();
		}
		else if(FFPConstants.FPS_RECEIVE_MODE_BATCH.equals(form.getFpsReceiveMode()))
		{
			FFPTxSwitchModeSevice mode = beanFactory.getBean(FFPTxSwitchModeSevice.class);
			mode.init(FFPConstants.FPS_RECEIVE_MODE_REALTIME, null);
			mode.perform();
		}
		return sList();
	}

	@Override
	public SystemStatForm switchRealTimeListenerStat(SystemStatForm form) throws Exception
	{
		if(FFPConstants.LISTENER_STATUS_RUNNING.equals(form.getRealtimeListenerStat())
				&& FFPConstants.FPS_RECEIVE_MODE_BATCH.equals(form.getFpsReceiveMode()))
		{
			listenerSvc.stopListener();
		}
		else if(FFPConstants.LISTENER_STATUS_CLOSE.equals(form.getRealtimeListenerStat()))
		{
			listenerSvc.startListener();
		}
		return sList();
	}
	
	@Override
	public SystemStatForm switchRealTimeControlStat(SystemStatForm form) throws Exception
	{
		FFPSsSystem system = dao.inquiry();
		if(FFPConstants.LISTENER_STATUS_CLOSE.equals(system.getRealtimeControlStat()))
		{
			if(FFPConstants.LISTENER_STATUS_CLOSE.equals(system.getRealtimeListenerStat())
					&& FFPConstants.FPS_RECEIVE_MODE_BATCH.equals(system.getFpsReceiveMode()))
			{
				dao.updateRealtimeControlStat(FFPConstants.LISTENER_STATUS_START_PROCESSING);
				listenerSvc.startController();
			}
		}
		else if(FFPConstants.LISTENER_STATUS_RUNNING.equals(system.getRealtimeControlStat()))
		{
			dao.updateRealtimeControlStat(FFPConstants.LISTENER_STATUS_STOP_PROCESSING);
			listenerSvc.stopController();
		}
		
		return sList();
	}

	@Override
	public SystemStatForm switchBatchListenerStat(SystemStatForm form)
	{
		if(form == null)
			return sList();
		return null;
	}
}
