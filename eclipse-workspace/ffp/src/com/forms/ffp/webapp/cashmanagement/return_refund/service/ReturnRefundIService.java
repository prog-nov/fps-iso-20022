package com.forms.ffp.webapp.cashmanagement.return_refund.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.ReturnRefundBean;
import com.forms.ffp.webapp.cashmanagement.return_refund.bean.ReturnRefundDetailBean;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.DoReturnRefundForm;
import com.forms.ffp.webapp.cashmanagement.return_refund.form.ReturnRefundSearchForm;

public interface ReturnRefundIService {
	
	
	List<ReturnRefundBean> inquerySomeRRMsg(ReturnRefundSearchForm form,IPage page);
	
	String doReturnRefund(DoReturnRefundForm form);
	
	ReturnRefundDetailBean inqueryDetail(String txCode,String jnlNo);

}
