package com.forms.ffp.persistents.bean.tx.inquiry.I110;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.forms.ffp.persistents.bean.FFPJbBaseFin;

public class FFPJbI110 extends FFPJbBaseFin implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jnlNo;
	private String txStat;
	private String txCode;
	private String clrSysRef;
	private String dbtrAgtMmbId;
	private String cdtrAgtMmbId;
	private String transactionId;
	private String endToEndId;
	private String txRejCode;
	private String txRejReason;
	private Date createTs;
	private Date lastUpdateTs;

	private String msgId;
	private String orgnlMsgId;
	private String orgnlMsgNmId;

	public String getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(String jnlNo)
	{
		this.jnlNo = jnlNo;
	}

	public String getTxStat()
	{
		return txStat;
	}

	public void setTxStat(String txStat)
	{
		this.txStat = txStat;
	}

	public String getTxCode()
	{
		return txCode;
	}

	public void setTxCode(String txCode)
	{
		this.txCode = txCode;
	}

	public String getClrSysRef()
	{
		return clrSysRef;
	}

	public void setClrSysRef(String clrSysRef)
	{
		this.clrSysRef = clrSysRef;
	}

	public String getDbtrAgtMmbId()
	{
		return dbtrAgtMmbId;
	}

	public void setDbtrAgtMmbId(String dbtrAgtMmbId)
	{
		this.dbtrAgtMmbId = dbtrAgtMmbId;
	}

	public String getCdtrAgtMmbId()
	{
		return cdtrAgtMmbId;
	}

	public void setCdtrAgtMmbId(String cdtrAgtMmbId)
	{
		this.cdtrAgtMmbId = cdtrAgtMmbId;
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

	public String getCreateTs()
	{
		String dateStr = null;
		if (createTs != null)
		{
			dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(createTs);
		}
		return dateStr;
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

	public String getMsgId()
	{
		return msgId;
	}

	public void setMsgId(String msgId)
	{
		this.msgId = msgId;
	}

	public String getOrgnlMsgId()
	{
		return orgnlMsgId;
	}

	public void setOrgnlMsgId(String orgnlMsgId)
	{
		this.orgnlMsgId = orgnlMsgId;
	}

	public String getOrgnlMsgNmId()
	{
		return orgnlMsgNmId;
	}

	public void setOrgnlMsgNmId(String orgnlMsgNmId)
	{
		this.orgnlMsgNmId = orgnlMsgNmId;
	}
}
