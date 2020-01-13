package com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.bean.FpsPaymentNotificationBean;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.form.FpsPaymentNotificationForm;

public interface IFpsPaymentNotificationService
{
	List<FpsPaymentNotificationBean> sList(FpsPaymentNotificationForm form, IPage page);
}
