package com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.bean.FpsPaymentNotificationBean;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.form.FpsPaymentNotificationForm;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.service.FpsPaymentNotificationService;

@Scope("request")
@Controller
@RequestMapping("ffp/cashmanagement/fpspaymentnotification/")
public class FpsPaymentNotificationController
{
	private static Logger _logger = LoggerFactory.getLogger(FpsPaymentNotificationController.class);
	
	@Resource(name = "FpsPaymentNotificationService")
	private FpsPaymentNotificationService service;
	
	@RequestMapping("list")
	@PageJsonBody
	public List<FpsPaymentNotificationBean> list(FpsPaymentNotificationForm form, IPage page)
	{
		return service.sList(form, page);
	}
}
