package com.forms.ffp.webapp.cashmanagement.transaction.bean;

import com.forms.beneform4j.webapp.common.bean.LogableBean;
import com.forms.ffp.webapp.common.bean.DataLayoutBean;

public class TransactionTxJnlActionBean extends LogableBean
{
	private static final long serialVersionUID = 1316260575232795511L;

	private DataLayoutBean seqNo;
	private DataLayoutBean msgId;
	private DataLayoutBean msgFromType;
	private DataLayoutBean msgFrom;
	private DataLayoutBean msgToType;
	private DataLayoutBean msgTo;
	private DataLayoutBean msgType;
	private DataLayoutBean msgStatus;
	private DataLayoutBean rejCode;
	private DataLayoutBean rejRsn;
	private DataLayoutBean msgCreatDate;
	private DataLayoutBean msgProceDate;
	private DataLayoutBean msgComplDate;

	public DataLayoutBean getSeqNo()
	{
		return seqNo;
	}

	public void setSeqNo(DataLayoutBean seqNo)
	{
		this.seqNo = seqNo;
	}

	public DataLayoutBean getMsgId()
	{
		return msgId;
	}

	public void setMsgId(DataLayoutBean msgId)
	{
		this.msgId = msgId;
	}

	public DataLayoutBean getMsgFromType()
	{
		return msgFromType;
	}

	public void setMsgFromType(DataLayoutBean msgFromType)
	{
		this.msgFromType = msgFromType;
	}

	public DataLayoutBean getMsgFrom()
	{
		return msgFrom;
	}

	public void setMsgFrom(DataLayoutBean msgFrom)
	{
		this.msgFrom = msgFrom;
	}

	public DataLayoutBean getMsgToType()
	{
		return msgToType;
	}

	public void setMsgToType(DataLayoutBean msgToType)
	{
		this.msgToType = msgToType;
	}

	public DataLayoutBean getMsgTo()
	{
		return msgTo;
	}

	public void setMsgTo(DataLayoutBean msgTo)
	{
		this.msgTo = msgTo;
	}

	public DataLayoutBean getMsgType()
	{
		return msgType;
	}

	public void setMsgType(DataLayoutBean msgType)
	{
		this.msgType = msgType;
	}

	public DataLayoutBean getMsgStatus()
	{
		return msgStatus;
	}

	public void setMsgStatus(DataLayoutBean msgStatus)
	{
		this.msgStatus = msgStatus;
	}

	public DataLayoutBean getRejCode()
	{
		return rejCode;
	}

	public void setRejCode(DataLayoutBean rejCode)
	{
		this.rejCode = rejCode;
	}

	public DataLayoutBean getRejRsn()
	{
		return rejRsn;
	}

	public void setRejRsn(DataLayoutBean rejRsn)
	{
		this.rejRsn = rejRsn;
	}

	public DataLayoutBean getMsgCreatDate()
	{
		return msgCreatDate;
	}

	public void setMsgCreatDate(DataLayoutBean msgCreatDate)
	{
		this.msgCreatDate = msgCreatDate;
	}

	public DataLayoutBean getMsgProceDate()
	{
		return msgProceDate;
	}

	public void setMsgProceDate(DataLayoutBean msgProceDate)
	{
		this.msgProceDate = msgProceDate;
	}

	public DataLayoutBean getMsgComplDate()
	{
		return msgComplDate;
	}

	public void setMsgComplDate(DataLayoutBean msgComplDate)
	{
		this.msgComplDate = msgComplDate;
	}

}
