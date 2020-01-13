package com.forms.ffp.bussiness.iclfps.pacs003;

import com.forms.ffp.bussiness.FFPVOBase;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP200;

public class FFPVO_Pacs003 extends FFPVOBase
{
	private String MsgId;
	private String CreDtTm;
	private String SttlmMtd;
	private String Prtry;
	private String ClrSys;

	private FFPJbP200 p200Jb;
	private FFPVO_Pacs003_DDI02REPLY ddi02Reply;

	private FFPVO_Pacs003_DrctDbtTxInf DrctDbtTxInf;

	public String getMsgId()
	{
		return MsgId;
	}

	public void setMsgId(String msgId)
	{
		MsgId = msgId;
	}

	public String getCreDtTm()
	{
		return CreDtTm;
	}

	public void setCreDtTm(String creDtTm)
	{
		CreDtTm = creDtTm;
	}

	public String getSttlmMtd()
	{
		return SttlmMtd;
	}

	public void setSttlmMtd(String sttlmMtd)
	{
		SttlmMtd = sttlmMtd;
	}

	public String getPrtry()
	{
		return Prtry;
	}

	public void setPrtry(String prtry)
	{
		Prtry = prtry;
	}

	public FFPVO_Pacs003_DrctDbtTxInf getDrctDbtTxInf()
	{
		return DrctDbtTxInf;
	}

	public void setDrctDbtTxInf(FFPVO_Pacs003_DrctDbtTxInf drctDbtTxInf)
	{
		DrctDbtTxInf = drctDbtTxInf;
	}

	public String getClrSys()
	{
		return ClrSys;
	}

	public void setClrSys(String clrSys)
	{
		ClrSys = clrSys;
	}

	public FFPJbP200 getP200Jb()
	{
		return p200Jb;
	}

	public void setP200Jb(FFPJbP200 p200Jb)
	{
		this.p200Jb = p200Jb;
	}

	public FFPVO_Pacs003_DDI02REPLY getDdi02Reply()
	{
		return ddi02Reply;
	}

	public void setDdi02Reply(FFPVO_Pacs003_DDI02REPLY ddi02Reply)
	{
		this.ddi02Reply = ddi02Reply;
	}

}
