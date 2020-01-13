package com.forms.ffp.webapp.systemmaintenance.systemstat.service;

import com.forms.ffp.webapp.systemmaintenance.systemstat.form.SystemStatForm;

public interface ISystemStatService
{

	public SystemStatForm sList();

	SystemStatForm switchReceiveMode(SystemStatForm form) throws Exception;

	SystemStatForm switchRealTimeListenerStat(SystemStatForm form) throws Exception;

	SystemStatForm switchRealTimeControlStat(SystemStatForm form) throws Exception;
	
	SystemStatForm switchBatchListenerStat(SystemStatForm form);
}
