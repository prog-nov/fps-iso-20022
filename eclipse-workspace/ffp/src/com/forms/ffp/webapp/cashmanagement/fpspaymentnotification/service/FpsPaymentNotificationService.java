package com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.persistents.bean.dt.FFPDtFpsPaymentNotification;
import com.forms.ffp.persistents.dao.dt.FFPIDao_FpsPaymentNotification;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.bean.FpsPaymentNotificationBean;
import com.forms.ffp.webapp.cashmanagement.fpspaymentnotification.form.FpsPaymentNotificationForm;

@Service("FpsPaymentNotificationService")
@Scope("prototype")
public class FpsPaymentNotificationService implements IFpsPaymentNotificationService
{
	@Resource(name = "FFPIDao_FpsPaymentNotification")
	private FFPIDao_FpsPaymentNotification dao;

	@Override
	public List<FpsPaymentNotificationBean> sList(FpsPaymentNotificationForm form, IPage page)
	{
		List<FpsPaymentNotificationBean> notifBean = new ArrayList<FpsPaymentNotificationBean>();
		List<FFPDtFpsPaymentNotification> notifList = dao.dList(form, page);
		if(notifList != null)
		{
			for(FFPDtFpsPaymentNotification notif : notifList)
			{
				FpsPaymentNotificationBean bean = new FpsPaymentNotificationBean();
				bean.setMsgId(notif.getMsgId());
				bean.setNtfctnId(notif.getNtfctnId());
				bean.setNtfctnCreateTs(FFPDateUtils.convertDateToString(notif.getNtfctnCreateTs(), FFPDateUtils.WEB_TIMESTAMP_FORMAT));
				bean.setNtfctnAcctId(notif.getNtfctnAcctId());
				bean.setNtfctnAcctType(getShowMessage("fps.NotiAccType.", notif.getNtfctnAcctType()));
				
				DecimalFormat myformat = new DecimalFormat();
				myformat.applyPattern("##,###.00");
				String ntfctnAmt = notif.getNtryAmtCcy() + myformat.format(notif.getNtryAmt());
				bean.setNtfctnAmt(ntfctnAmt);
				
				bean.setPaymentCag(getShowMessage("fps.PaymentCategoryPurposeCode.", notif.getNtryBankTransCode()));
				bean.setPaymentEndToEndId(notif.getNtryDetailEndToEndId());
				bean.setPaymentTxId(notif.getNtryDetailTxId());
				bean.setPaymentClrSysRef(notif.getNtryDetailClrSysRef());
				bean.setPaymentDbtr(getShowMessage("fps.clearingCode.", notif.getRelatedAgentsDbtrMmbId()));
				bean.setPaymentCdtr(getShowMessage("fps.clearingCode.", notif.getRelatedAgentsCdtrMmbId()));
				bean.setSettlementTs(FFPDateUtils.convertDateToString(notif.getRelatedDatesTransTs(), FFPDateUtils.WEB_TIMESTAMP_FORMAT));
				notifBean.add(bean);
			}
			
		}
		return notifBean;
	}
	
	private String getShowMessage(String key, String value)
	{
		if(value == null) return "";
		Locale local = Tool.LOCALE.getCurrentLocale();
		String msg = Tool.LOCALE.getMessage(key + value, local);
		if(msg != null && !msg.equals(key + value))
		{
			return msg;
		}
		else
		{
			return value;
		}
	}
}
