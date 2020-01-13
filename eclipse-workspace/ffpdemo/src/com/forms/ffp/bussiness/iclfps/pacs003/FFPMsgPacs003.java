package com.forms.ffp.bussiness.iclfps.pacs003;

import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
/**
 * 组装pacs003消息
 * @author admin
 *
 */
public class FFPMsgPacs003 {
	
	private FFPJbPacs003 txJb = null;
	
//	private FFPPaymentFormBean bean = null;

	public FFPMsgPacs003(FFPJbPacs003 txJb) {
		this.txJb = txJb;
	}
	
	//重组消息
//	public FFPMsgFormContextBean marshalMsgBean(){
		
//		FFPMsgFormContextBean mfcb = new FFPMsgFormContextBean();
//		
//		bean = new FFPPaymentFormBean();
//		bean.setMessageDefinitionIdentifier(FFPMessageDefinitionIdentifier.ICL_MSG_MESSAGEDEFINITIONIDENTIFIER_CAMT_60);
//		bean.setBusinessService(FFPMessageServiceCode.ICL_MSG_SERVICE_CODE_ADMISV);
//
//		FFPAccountBalanceEnquiryBean accountBalanceEnquiryRequest = new FFPAccountBalanceEnquiryBean();
//		accountBalanceEnquiryRequest.setRequiredMessageNameId("camt.052.001.06");
//		accountBalanceEnquiryRequest.setAccountId(txJb.getClearCode());
//		accountBalanceEnquiryRequest.setAccountOwnerId(txJb.getClearCode());
//		accountBalanceEnquiryRequest.setAccountCurrency("HKD");
//
//		bean.setAccountBalanceEnquiryRequest(accountBalanceEnquiryRequest);
//		bean.setFrMmbId(txJb.getClearCode());
//		bean.setFrMmbBic(txJb.getBicCode());
//		bean.setToMmbId("ICL");
//		bean.setMessageId(FFPJnlUtils.getMessageId(txJb.getClearCode()));
//
//		mfcb.putFormBean("acctRptgReq", bean);
//		return mfcb;
		
//	}
	
	/**
	 * 将出入的JavaBean转成xml文件
	 */
	public String Jb2String(){
		String txId = txJb.getMsgTranId();
		//自定义一个报文response
		ROOT root = new ROOT();
		HEAD head = new HEAD();
		head.setResponseID(txId);
		root.setHEAD(head);
		
		BODY body = null;
		return null;
	}

}
