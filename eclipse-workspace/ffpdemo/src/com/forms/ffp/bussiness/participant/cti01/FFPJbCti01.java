package com.forms.ffp.bussiness.participant.cti01;

import com.forms.ffp.bussiness.FFPJbBase;

public class FFPJbCti01 extends FFPJbBase {
	
	private String accountingDate ;
	private String requestRefno ;
	
	public String getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}
	
	public String getRequestRefno() {
		return requestRefno;
	}
	public void setRequestRefno(String requestRefno) {
		this.requestRefno = requestRefno;
	}
	
	@Override
	public String toString() {
		return "FFPJbCti01 [accountingDate=" + accountingDate + ", requestRefno=" + requestRefno + "]";
	}
	
	
}
