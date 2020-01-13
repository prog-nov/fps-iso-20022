package com.forms.ffp.bussiness.iclfps.pacs002;

public class FFPVO_Pacs002_DDI01REPLY
{
	private String SrcRefNm;
	
	private String msgId;
	
	private String msgTp;
	
	private String systemId;

	private String RsltCd;

	private String RejCd;

	private String RejMsg;

	private String TransactionId;
	
	

	public String getMsgId() {
		return msgId;
	}

	public String getMsgTp() {
		return msgTp;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public void setMsgTp(String msgTp) {
		this.msgTp = msgTp;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSrcRefNm()
	{
		return SrcRefNm;
	}

	public void setSrcRefNm(String srcRefNm)
	{
		SrcRefNm = srcRefNm;
	}

	public String getRsltCd()
	{
		return RsltCd;
	}

	public void setRsltCd(String rsltCd)
	{
		RsltCd = rsltCd;
	}

	public String getRejCd()
	{
		return RejCd;
	}

	public void setRejCd(String rejCd)
	{
		RejCd = rejCd;
	}

	public String getRejMsg()
	{
		return RejMsg;
	}

	public void setRejMsg(String rejMsg)
	{
		RejMsg = rejMsg;
	}

	public String getTransactionId()
	{
		return TransactionId;
	}

	public void setTransactionId(String transactionId)
	{
		TransactionId = transactionId;
	}

}
