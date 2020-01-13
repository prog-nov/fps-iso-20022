package com.forms.ffp.persistents.dao.dt;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsPaymentNotification;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.form.FpsPaymentNotificationForm;

@Repository("FFPIDao_FpsPaymentNotification")
public interface FFPIDao_FpsPaymentNotification
{
	public List<FFPDtFpsPaymentNotification> dList(FpsPaymentNotificationForm form, IPage page);
	
	@Executes({
		@Execute(sqlRef = @SqlRef("INSERT_LIST_NOTIFICATION"), param = @BatchParam(item = "notif"))
	})
	public int[] dInsertList(List<FFPDtFpsPaymentNotification> nList);
}
