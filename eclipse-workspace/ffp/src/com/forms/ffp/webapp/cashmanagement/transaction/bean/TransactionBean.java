package com.forms.ffp.webapp.cashmanagement.transaction.bean;

import com.forms.beneform4j.webapp.common.bean.LogableBean;


public class TransactionBean extends LogableBean
{

	private static final long serialVersionUID = 1316260575232795510L;

	private String jnlNo;

	private String transactionId;

	private String endToEndId;

	private String txCode;

	private String txStat;

	private String txSrc;

	private String beginDate;
	private String endDate;

	public String getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(String beginDate)
	{
		this.beginDate = beginDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getTxCode()
	{
		return txCode;
	}

	public void setTxCode(String txCode)
	{
		this.txCode = txCode;
	}

	public String getTxStat()
	{
		return txStat;
	}

	public void setTxStat(String txStat)
	{
		this.txStat = txStat;
	}

	public String getTxSrc()
	{
		return txSrc;
	}

	public void setTxSrc(String txSrc)
	{
		this.txSrc = txSrc;
	}

	public String getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(String jnlNo)
	{
		this.jnlNo = jnlNo;
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

}
