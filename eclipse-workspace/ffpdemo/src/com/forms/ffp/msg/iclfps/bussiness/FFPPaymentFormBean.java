package com.forms.ffp.msg.iclfps.bussiness;

import java.util.List;

public class FFPPaymentFormBean extends FFPBaseBussinessBean
{
	private List<CreditTransferTransactionBean> creditTransferTransactionList;
	private List<DirectDebitTransactionBean> directDebitTransactionList;
	private List<ReturnRefundTransactionBean> returnRefundTransactionList;
	private FFPStatusEnquiryBean statusEnquiryRequest;
	private FFPAccountBalanceEnquiryBean accountBalanceEnquiryRequest;
	private ReceiptModeSwitchingRequestBean receiptModeSwitchingRequest;
	private FFPCertExchangeRequestBean certExchangeRequest;

	public List<CreditTransferTransactionBean> getCreditTransferTransactionList()
	{
		return this.creditTransferTransactionList;
	}

	public void setCreditTransferTransactionList(List<CreditTransferTransactionBean> creditTransferTransactionList)
	{
		this.creditTransferTransactionList = creditTransferTransactionList;
	}

	public List<DirectDebitTransactionBean> getDirectDebitTransactionList()
	{
		return this.directDebitTransactionList;
	}

	public void setDirectDebitTransactionList(List<DirectDebitTransactionBean> directDebitTransactionList)
	{
		this.directDebitTransactionList = directDebitTransactionList;
	}

	public List<ReturnRefundTransactionBean> getReturnRefundTransactionList()
	{
		return this.returnRefundTransactionList;
	}

	public void setReturnRefundTransactionList(List<ReturnRefundTransactionBean> returnRefundTransactionList)
	{
		this.returnRefundTransactionList = returnRefundTransactionList;
	}

	public FFPStatusEnquiryBean getStatusEnquiryRequest()
	{
		return this.statusEnquiryRequest;
	}

	public void setStatusEnquiryRequest(FFPStatusEnquiryBean statusEnquiryRequest)
	{
		this.statusEnquiryRequest = statusEnquiryRequest;
	}

	public FFPAccountBalanceEnquiryBean getAccountBalanceEnquiryRequest()
	{
		return this.accountBalanceEnquiryRequest;
	}

	public void setAccountBalanceEnquiryRequest(FFPAccountBalanceEnquiryBean accountBalanceEnquiryRequest)
	{
		this.accountBalanceEnquiryRequest = accountBalanceEnquiryRequest;
	}

	public ReceiptModeSwitchingRequestBean getReceiptModeSwitchingRequest()
	{
		return this.receiptModeSwitchingRequest;
	}

	public void setReceiptModeSwitchingRequest(ReceiptModeSwitchingRequestBean receiptModeSwitchingRequest)
	{
		this.receiptModeSwitchingRequest = receiptModeSwitchingRequest;
	}

	public FFPCertExchangeRequestBean getCertExchangeRequest()
	{
		return this.certExchangeRequest;
	}

	public void setCertExchangeRequest(FFPCertExchangeRequestBean certExchangeRequest)
	{
		this.certExchangeRequest = certExchangeRequest;
	}

	public int getCount()
	{
		int count = 0;
		count += countBean(this.creditTransferTransactionList);
		count += countBean(this.directDebitTransactionList);
		count += countBean(this.returnRefundTransactionList);
		count += countBean(this.statusEnquiryRequest);
		count += countBean(this.accountBalanceEnquiryRequest);
		count += countBean(this.receiptModeSwitchingRequest);
		count += countBean(this.certExchangeRequest);
		return count;
	}
}

/*
 * Location: C:\Users\qingzhizi\working\Tools\apache-tomcat-8.0.45\webapps\
 * ICLFPS_BANK_SIMULATOR.war!\WEB-INF\classes\com\hkicl\fps\form\bean\
 * PaymentFormBean.class Java compiler version: 8 (52.0) JD-Core Version: 0.7.1
 */