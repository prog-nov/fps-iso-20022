package com.forms.batch.job.unit.participant.message.ffprro01;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffprro01.FFPRRO01;
import com.forms.ffp.bussiness.iclfps.pacs002.FFPVO_Pacs002_TxInfAndSts;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;

public class FFPBatchMsg_RRO01 extends FFPMsgBaseResponseParticipantMessage
{
	private FFPVO_Pacs002_TxInfAndSts msgJb_txInf;
	
	public FFPBatchMsg_RRO01(FFPVO_Pacs002_TxInfAndSts msgJb_txInf, String orgReqRefNo, String resRefNo)
	{
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
		this.reqRefNo = orgReqRefNo;
		this.resRefNo = resRefNo;
		this.responseSts = "N";
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTO01;
		this.msgJb_txInf = msgJb_txInf;
	}
	
	public BODY marshalMsgResBody()
	{
		FFPRRO01 rro01 = new FFPRRO01();
		rro01.setSrcRefNm(msgJb_txInf.getP300Jb().getSrcRefNm());
		rro01.setTransactionId(msgJb_txInf.getOrgnlTxId());
		//ACSC=>S
		if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_ACSC.getStatus().equals(msgJb_txInf.getTxSts()))
		{
			rro01.setRsltCd("S");
		}
		else if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus().equals(msgJb_txInf.getTxSts()))
		{
			rro01.setRsltCd("R");
			rro01.setRejCd(msgJb_txInf.getTxStsRsnCode());
			if (msgJb_txInf.getTxStsAddtlInf() != null)
			{
				StringBuffer loc_sb = new StringBuffer();
				for (String str : msgJb_txInf.getTxStsAddtlInf())
					loc_sb.append(str);
				rro01.setRejMsg(loc_sb.toString());
			}
		}
		else
		{
			
		}
		return rro01;
	}
	
	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		
	}
}
