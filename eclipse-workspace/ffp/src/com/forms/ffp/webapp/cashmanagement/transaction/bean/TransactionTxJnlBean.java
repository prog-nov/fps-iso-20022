package com.forms.ffp.webapp.cashmanagement.transaction.bean;

import com.forms.beneform4j.webapp.common.bean.LogableBean;
import com.forms.ffp.webapp.common.bean.DataLayoutBean;

public class TransactionTxJnlBean extends LogableBean
{
	private static final long serialVersionUID = 1316260575232795510L;

	private DataLayoutBean jnlNo;

	private DataLayoutBean transactionId;

	private DataLayoutBean endToEndId;

	private DataLayoutBean txCode;

	private DataLayoutBean txStat;

	private DataLayoutBean txSrc;

	public DataLayoutBean getTxCode()
	{
		return txCode;
	}

	public void setTxCode(DataLayoutBean txCode)
	{
		this.txCode = txCode;
	}

	public DataLayoutBean getTxStat()
	{
		return txStat;
	}

	public void setTxStat(DataLayoutBean txStat)
	{
		this.txStat = txStat;
	}

	public DataLayoutBean getTxSrc()
	{
		return txSrc;
	}

	public void setTxSrc(DataLayoutBean txSrc)
	{
		this.txSrc = txSrc;
	}

	public DataLayoutBean getJnlNo()
	{
		return jnlNo;
	}

	public void setJnlNo(DataLayoutBean jnlNo)
	{
		this.jnlNo = jnlNo;
	}

	public DataLayoutBean getTransactionId()
	{
		return transactionId;
	}

	public void setTransactionId(DataLayoutBean transactionId)
	{
		this.transactionId = transactionId;
	}

	public DataLayoutBean getEndToEndId()
	{
		return endToEndId;
	}

	public void setEndToEndId(DataLayoutBean endToEndId)
	{
		this.endToEndId = endToEndId;
	}

}
