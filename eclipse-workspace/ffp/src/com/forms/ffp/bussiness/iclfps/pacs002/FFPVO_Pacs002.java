package com.forms.ffp.bussiness.iclfps.pacs002;

import java.util.List;

import com.forms.ffp.bussiness.FFPVOBase;

public class FFPVO_Pacs002 extends FFPVOBase
{
	private String MsgId;
	private String CreDtTm;

	private String OrgnlMsgId;
	private String OrgnlMsgNmId;
	private String GrpSts;
	private String GrpStsRsnCode;
	private List<String> GrpStsAddtlInfList;
	private List<FFPVO_Pacs002_TxInfAndSts> txInfList;

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

	public String getOrgnlMsgId()
	{
		return OrgnlMsgId;
	}

	public void setOrgnlMsgId(String orgnlMsgId)
	{
		OrgnlMsgId = orgnlMsgId;
	}

	public String getOrgnlMsgNmId()
	{
		return OrgnlMsgNmId;
	}

	public void setOrgnlMsgNmId(String orgnlMsgNmId)
	{
		OrgnlMsgNmId = orgnlMsgNmId;
	}

	public String getGrpSts()
	{
		return GrpSts;
	}

	public void setGrpSts(String grpSts)
	{
		GrpSts = grpSts;
	}

	public String getGrpStsRsnCode()
	{
		return GrpStsRsnCode;
	}

	public void setGrpStsRsnCode(String grpStsRsnCode)
	{
		GrpStsRsnCode = grpStsRsnCode;
	}

	public List<String> getGrpStsAddtlInfList()
	{
		return GrpStsAddtlInfList;
	}

	public void setGrpStsAddtlInfList(List<String> grpStsAddtlInfList)
	{
		GrpStsAddtlInfList = grpStsAddtlInfList;
	}

	public List<FFPVO_Pacs002_TxInfAndSts> getTxInfList()
	{
		return txInfList;
	}

	public void setTxInfList(List<FFPVO_Pacs002_TxInfAndSts> txInfList)
	{
		this.txInfList = txInfList;
	}

}
