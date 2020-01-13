package com.forms.ffp.webapp.cashmanagement.return_refund.form;

import com.forms.beneform4j.webapp.common.form.LogableForm;

public class ReturnRefundSearchForm extends  LogableForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jnlNo;
	
	private String cusName;

	private String accountNm;

	private String settleDate;
	
	private String reType;
	
	
	
	

	public String getReType() {
		return reType;
	}

	public void setReType(String reType) {
		this.reType = reType;
	}

	public String getJnlNo() {
		return jnlNo;
	}

	public String getCusName() {
		return cusName;
	}

	public String getSettleDate() {
		return settleDate;
	}

	public void setJnlNo(String jnlNo) {
		this.jnlNo = jnlNo;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public String getAccountNm() {
		return accountNm;
	}

	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}

	
}
