package com.forms.ffp.webapp.cashmanagement.accountinquery.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.ffp.webapp.cashmanagement.accountinquery.form.AccountBalanceInquiryForm;
import com.forms.ffp.webapp.cashmanagement.accountinquery.service.AccountBalanceInquiryService;

@Scope("request")
@Controller
@RequestMapping("ffp/cashmanagement/accountbalanceinquery/")
public class AccountBalanceInquiryController
{
	@Resource(name = "AccountInquiryService")
	private AccountBalanceInquiryService service;
	
	@RequestMapping("list")
	public ModelAndView list()
	{
		ModelAndView mv = new ModelAndView("ffp/cashmanagement/accountbalanceinquery/list");
        return mv;
	}
	
//	@RequestMapping("queryCcy")
//	@JsonBody
//	public ModelAndView queryCcy(AccountBalanceInquiryForm form)
//	{
//		ModelAndView mv = new ModelAndView("ffp/cashmanagement/accountbalanceinquery/list");
//		AccountBalanceInquiryForm retForm = service.queryCcy(form);
//        mv.addObject("data", retForm);
//        return mv;
//	}
	
	@RequestMapping("queryCcy")
	@JsonBody
	public AccountBalanceInquiryForm queryCcy(AccountBalanceInquiryForm form)
	{
		AccountBalanceInquiryForm retForm = service.queryCcy(form);
		return retForm;
	}
}
