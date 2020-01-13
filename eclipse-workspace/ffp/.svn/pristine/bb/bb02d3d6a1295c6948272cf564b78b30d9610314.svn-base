package com.forms.ffp.webapp.systemmaintenance.systemstat.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.ffp.webapp.systemmaintenance.systemstat.form.SystemStatForm;
import com.forms.ffp.webapp.systemmaintenance.systemstat.service.SystemStatService;

@Controller
@Scope("request")
@RequestMapping("ffp/systemmaintenance/systemstat")
public class SystemStatController {
	
	@Resource(name="SystemStatService")
	private SystemStatService service;
	
	@RequestMapping("/list")
	@JsonBody
	public SystemStatForm cList()
	{
		return service.sList();
	}
	
	@RequestMapping("/switchReceiveMode")
	@JsonBody
	public SystemStatForm switchReceiveMode(SystemStatForm form) throws Exception
	{
		if(form == null)
			return service.sList();
		return service.switchReceiveMode(form);
	}
	
	@RequestMapping("/switchRealTimeListenerStat")
	@JsonBody
	public SystemStatForm switchRealTimeListenerStat(SystemStatForm form) throws Exception
	{
		if(form == null)
			return service.sList();
		return service.switchRealTimeListenerStat(form);
	}
	
	@RequestMapping("/switchController")
	@JsonBody
	public SystemStatForm switchRealTimeControlStat(SystemStatForm form) throws Exception
	{
		if(form == null)
			return service.sList();
		
		return service.switchRealTimeControlStat(form);
	}
	
	@RequestMapping("/switchBatchListenerStat")
	@JsonBody
	public SystemStatForm switchBatchListenerStat(SystemStatForm form)
	{
		return form;
	}
}
