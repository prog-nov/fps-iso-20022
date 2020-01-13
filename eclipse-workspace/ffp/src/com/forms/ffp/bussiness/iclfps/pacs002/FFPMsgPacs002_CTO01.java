package com.forms.ffp.bussiness.iclfps.pacs002;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FPSTransactionStatusCode;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;

public class FFPMsgPacs002_CTO01 extends FFPMsgBaseResponseParticipantMessage
{
	private FFPVO_Pacs002_TxInfAndSts msgJb_txInf = null;

	public FFPMsgPacs002_CTO01(FFPVO_Pacs002 ip_pacs002, int ip_i)
	{
		super(ip_pacs002.getTxInfList().get(ip_i).getP100Jb().getJnlActionList().get(0));
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTO01;
		this.msgJb_txInf = ip_pacs002.getTxInfList().get(ip_i);
	}

	public BODY marshalMsgResBody()
	{
		FFPCTO01 cto01 = new FFPCTO01();
		cto01.setSrcRefNm(msgJb_txInf.getP100Jb().getTxJnl().getSrcRefNm());
		cto01.setFFPTransactionId(msgJb_txInf.getP100Jb().getTxJnl().getTransactionId());
		cto01.setRsltCd(msgJb_txInf.getTxSts());
		if(!FPSTransactionStatusCode.ACSC.equals(msgJb_txInf.getTxSts()))
		{
			cto01.setRejCd(msgJb_txInf.getTxStsRsnCode());
			if(msgJb_txInf.getTxStsAddtlInf() != null)
			{
				StringBuffer loc_sb = new StringBuffer();
				for(String str : msgJb_txInf.getTxStsAddtlInf())
					loc_sb.append(str);
				cto01.setRejMsg(loc_sb.toString());
			}
			
		}
		return cto01;
	}
 
	public FFPVO_Pacs002_TxInfAndSts getMsgJb_txInf()
	{
		return msgJb_txInf;
	}

	public void setMsgJb_txInf(FFPVO_Pacs002_TxInfAndSts msgJb_txInf)
	{
		this.msgJb_txInf = msgJb_txInf;
	}
	
}
