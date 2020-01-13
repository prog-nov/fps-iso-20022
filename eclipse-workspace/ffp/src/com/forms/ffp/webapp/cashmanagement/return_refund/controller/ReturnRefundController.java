package com.forms.ffp.webapp.cashmanagement.return_refund.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.ListJsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.ReturnRefundBean;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.DoReturnRefundForm;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.ReturnRefundSearchForm;
import com.forms.ffp.webapp.cashmanagement.return_refund.service.ReturnRefundService;

@Controller
@Scope("request")
@RequestMapping("ffp/cashmanagement/return_refund/")
public class ReturnRefundController {
	
	@Resource(name="ReturnRefundService")
	private ReturnRefundService service;
	
	
	
	
	@RequestMapping("list")
	@PageJsonBody
	public List<ReturnRefundBean> list(ReturnRefundSearchForm form,IPage page)
	{
		return service.inquerySomeRRMsg(form,page);
	}
	
	@RequestMapping("detail")
	public ModelAndView getSomeRRMsg(@RequestParam(name = "jnlNo") String jnlNo,
			@RequestParam(name = "txCode") String txCode){
		ModelAndView mv = new ModelAndView("ffp/cashmanagement/return_refund/detail");
		mv.addObject("data",service.inqueryDetail(txCode, jnlNo));
		return mv;
		
	}
	
	@RequestMapping("doReturn")
	@ResponseBody
	public Object doReturnRefund(DoReturnRefundForm form){
		String result=service.doReturnRefund(form);

		return result;
	}

}
