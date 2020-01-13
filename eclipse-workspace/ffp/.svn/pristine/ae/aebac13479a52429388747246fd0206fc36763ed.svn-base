package com.forms.ffp.webapp.systemmaintenance.cutoffsetting.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.bean.CutOffBean;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.form.CutOffForm;
import com.forms.ffp.webapp.systemmaintenance.cutoffsetting.service.CutoffService;

@Controller
@Scope("request")
@RequestMapping("ffp/systemmaintenance/cutoff")
public class CutOffController {
	
	@Resource(name="cutoffService")
	private CutoffService service;
	
	@RequestMapping("/cursetting")
	@PageJsonBody
	public List<CutOffBean> inqueryAllCutoffType(IPage page)
	{
		return service.inqueryAllCutoffType();
	}
	
    @RequestMapping("/update")
    @JsonBody
    public int update(CutOffForm form) {
    	return service.sUpdate(form);
    }
}
