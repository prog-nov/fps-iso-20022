package com.forms.ffp.core.msg.participant.busi;

public class FFP_CTI01_OVO extends FFP_HEAD_VO
{
	private String SrcRefNm;
	
	private String transactionId;
	
	private String RsltCd;
	
	private String RejCd;
	
	private String RejMsg;

	public String getSrcRefNm() {
		return SrcRefNm;
	}

	public void setSrcRefNm(String srcRefNm) {
		SrcRefNm = srcRefNm;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getRsltCd() {
		return RsltCd;
	}

	public void setRsltCd(String rsltCd) {
		RsltCd = rsltCd;
	}

	public String getRejCd() {
		return RejCd;
	}

	public void setRejCd(String rejCd) {
		RejCd = rejCd;
	}

	public String getRejMsg() {
		return RejMsg;
	}

	public void setRejMsg(String rejMsg) {
		RejMsg = rejMsg;
	}
}
