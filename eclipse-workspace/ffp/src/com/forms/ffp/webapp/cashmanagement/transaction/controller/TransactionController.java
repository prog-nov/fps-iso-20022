package com.forms.ffp.webapp.cashmanagement.transaction.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.ffp.webapp.cashmanagement.transaction.bean.TransactionBean;
import com.forms.ffp.webapp.cashmanagement.transaction.form.TransactionSearchForm;
import com.forms.ffp.webapp.cashmanagement.transaction.service.ITransactionService;

@Scope("request")
@Controller
@RequestMapping("ffp/cashmanagement/transaction/")
public class TransactionController
{
	@Resource(name = "TransactionService")
	private ITransactionService service;
	
	@RequestMapping("list")
	@PageJsonBody
	public List<TransactionBean> list(TransactionSearchForm form, IPage page)
	{
		return service.sList(form, page);
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(@RequestParam(name = "jnlNo") String jnlNo)
	{
		ModelAndView mv = new ModelAndView("ffp/cashmanagement/transaction/detail");
        mv.addObject("data", service.sDetail(jnlNo));
        return mv;
	}
}
