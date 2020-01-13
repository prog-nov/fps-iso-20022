package com.forms.ffp.persistents.bean;

import java.util.Date;

public class FFPTxJnl
{
	private String jnlNo;
	private String jnlRef;
	private String srcRefNm;
	private String txStat;
	private String txCode;
	private String txSrc;
	private String txMode;
	private String transactionId;
	private String endToEndId;
	private String txFileName;
	private String fpsRefNm;
	private int resendCount;
	private String txRejCode;
	private String txRejReason;
	private Date createTs;
	private Date lastUpdateTs;

	public String getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(String jnlNo)
	{
		this.jnlNo = jnlNo;
	}
	

	public String getJnlRef() {
		return jnlRef;
	}

	public void setJnlRef(String jnlRef) {
		this.jnlRef = jnlRef;
	}

	public String getTxCode()
	{
		return txCode;
	}

	public void setTxCode(String txCode)
	{
		this.txCode = txCode;
	}

	public String getTxMode()
	{
		return txMode;
	}

	public void setTxMode(String txMode)
	{
		this.txMode = txMode;
	}

	public String getTxFileName()
	{
		return txFileName;
	}

	public void setTxFileName(String txFileName)
	{
		this.txFileName = txFileName;
	}

	public String getTxStat()
	{
		return txStat;
	}

	public void setTxStat(String txStat)
	{
		this.txStat = txStat;
	}

	public String getSrcRefNm()
	{
		return srcRefNm;
	}

	public void setSrcRefNm(String srcRefNm)
	{
		this.srcRefNm = srcRefNm;
	}

	public String getTxSrc()
	{
		return txSrc;
	}

	public void setTxSrc(String txSrc)
	{
		this.txSrc = txSrc;
	}

	public String getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

	public String getEndToEndId()
	{
		return endToEndId;
	}

	public void setEndToEndId(String endToEndId)
	{
		this.endToEndId = endToEndId;
	}

	public Date getCreateTs()
	{
		return createTs;
	}

	public void setCreateTs(Date createTs)
	{
		this.createTs = createTs;
	}

	public Date getLastUpdateTs()
	{
		return lastUpdateTs;
	}

	public void setLastUpdateTs(Date lastUpdateTs)
	{
		this.lastUpdateTs = lastUpdateTs;
	}

	public String getFpsRefNm()
	{
		return fpsRefNm;
	}

	public void setFpsRefNm(String fpsRefNm)
	{
		this.fpsRefNm = fpsRefNm;
	}

	public int getResendCount()
	{
		return resendCount;
	}

	public void setResendCount(int resendCount)
	{
		this.resendCount = resendCount;
	}

	public String getTxRejCode()
	{
		return txRejCode;
	}

	public void setTxRejCode(String txRejCode)
	{
		this.txRejCode = txRejCode;
	}

	public String getTxRejReason()
	{
		return txRejReason;
	}

	public void setTxRejReason(String txRejReason)
	{
		this.txRejReason = txRejReason;
	}

}
