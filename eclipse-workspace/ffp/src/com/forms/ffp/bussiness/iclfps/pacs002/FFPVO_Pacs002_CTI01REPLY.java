package com.forms.ffp.bussiness.iclfps.pacs002;

public class FFPVO_Pacs002_CTI01REPLY
{
	private String msgId;
	
	private String systemId;
	
	private String SrcRefNm;

	private String RsltCd;

	private String RejCd;

	private String RejMsg;
	
	private String msgTp;
	

	public String getMsgTp() {
		return msgTp;
	}

	public void setMsgTp(String msgTp) {
		this.msgTp = msgTp;
	}

	public String getMsgId() {
		return msgId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

}
