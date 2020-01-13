package com.forms.ffp.bussiness.iclfps.camt060;

import com.forms.ffp.bussiness.FFPJnlUtils;
import com.forms.ffp.msg.iclfps.FFPMessageDefinitionIdentifier;
import com.forms.ffp.msg.iclfps.FFPMessageServiceCode;
import com.forms.ffp.msg.iclfps.bussiness.FFPAccountBalanceEnquiryBean;
import com.forms.ffp.msg.iclfps.bussiness.FFPMsgFormContextBean;
import com.forms.ffp.msg.iclfps.bussiness.FFPPaymentFormBean;

public class FFPMsgCamt060
{
	private FFPJbCamt060 txJb = null;

	private FFPPaymentFormBean bean = null;

	public FFPMsgCamt060(FFPJbCamt060 txJb)
	{
		this.txJb = txJb;
	}

	public FFPMsgFormContextBean marshalMsgBean()
	{
		FFPMsgFormContextBean mfcb = new FFPMsgFormContextBean();
		
		bean = new FFPPaymentFormBean();
		bean.setMessageDefinitionIdentifier(FFPMessageDefinitionIdentifier.ICL_MSG_MESSAGEDEFINITIONIDENTIFIER_CAMT_60);
		bean.setBusinessService(FFPMessageServiceCode.ICL_MSG_SERVICE_CODE_ADMISV);

		FFPAccountBalanceEnquiryBean accountBalanceEnquiryRequest = new FFPAccountBalanceEnquiryBean();
		accountBalanceEnquiryRequest.setRequiredMessageNameId("camt.052.001.06");
		accountBalanceEnquiryRequest.setAccountId(txJb.getClearCode());
		accountBalanceEnquiryRequest.setAccountOwnerId(txJb.getClearCode());
		accountBalanceEnquiryRequest.setAccountCurrency("HKD");

		bean.setAccountBalanceEnquiryRequest(accountBalanceEnquiryRequest);
		bean.setFrMmbId(txJb.getClearCode());
		bean.setFrMmbBic(txJb.getBicCode());
		bean.setToMmbId("ICL");
		bean.setMessageId(FFPJnlUtils.getMessageId(txJb.getClearCode()));

		mfcb.putFormBean("acctRptgReq", bean);
		return mfcb;
	}

	public FFPJbCamt060 getTxJb() {
		return txJb;
	}

	public void setTxJb(FFPJbCamt060 txJb) {
		this.txJb = txJb;
	}

	
}
