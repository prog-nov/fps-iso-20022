package com.forms.ffp.webapp.systemmaintenance.otherbankmode.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsParticipantMode;
import com.forms.ffp.webapp.systemmaintenance.otherbankmode.service.OtherBankReceiveModeService;

@Controller
@Scope("request")
@RequestMapping("ffp/systemmaintenance/otherbankmode")
public class OtherBankReceiveModeController {
	
	@Resource(name="OtherBankReceiveModeService")
	private OtherBankReceiveModeService service;
	
	@RequestMapping("/list")
	@PageJsonBody
	public List<FFPDtFpsParticipantMode> inqueryAllCutoffType(IPage page)
	{
		return service.showOtherBankReceiveMode();
	}
}
