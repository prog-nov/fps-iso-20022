package com.forms.ffp.webapp.testing.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.ffp.webapp.testing.form.TestingForm;
import com.forms.ffp.webapp.testing.service.TestingService;

@Controller
@Scope("request")
@RequestMapping("ffp/testing/test1")
public class TesingController
{
	@Resource()
	private TestingService service;
    
    @RequestMapping("/mylist")
	@JsonBody
	public ModelAndView myList()
	{
		service.init();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ffp/testing/test1/mylist");
		return mv;
	}
	
	@RequestMapping("/mySend")
	@ResponseBody
	public TestingForm mySend(TestingForm form){
		form.setResponseMsg(service.send(form));
		return form;
	}
}
