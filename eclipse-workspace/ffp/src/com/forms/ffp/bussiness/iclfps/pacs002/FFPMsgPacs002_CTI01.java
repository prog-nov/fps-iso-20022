package com.forms.ffp.bussiness.iclfps.pacs002;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpcti01.FFPCTI01;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;
import com.forms.ffp.core.utils.FFPDateUtils;

public class FFPMsgPacs002_CTI01 extends FFPMsgBaseParticipantMessage
{
	private FFPVO_Pacs002_TxInfAndSts msgJb_txInf = null;

	public FFPMsgPacs002_CTI01(FFPVO_Pacs002 ip_pacs002, int ip_i)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01;
		this.msgJb_txInf = ip_pacs002.getTxInfList().get(ip_i);
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public BODY marshalMsgReqBody()
	{
		BODY body = null;
		FFPCTI01 cti01 = new FFPCTI01();
		cti01.setSrcRefNm(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm());
		cti01.setPymtCatPrps(msgJb_txInf.getP110Jb().getPymtCatPrps());
		cti01.setSettlDate(FFPDateUtils.convertDateToString(msgJb_txInf.getP110Jb().getSettlementDate()));
		cti01.setSettlAmt(msgJb_txInf.getP110Jb().getSettlementAmount());
		cti01.setSettlCcy(msgJb_txInf.getP110Jb().getSettlementCurrency());
		cti01.setCdtrNm(msgJb_txInf.getP110Jb().getCreditorName());
		cti01.setCdtrAcTp(msgJb_txInf.getP110Jb().getCreditorAccountNumberType());
		cti01.setCdtrAcNo(msgJb_txInf.getP110Jb().getCreditorAccountNumber());
		body = cti01;
		return body;
	}

	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		try
		{
			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
			com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01 cti01 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpcti01.FFPCTI01)body;
		
			if(msgJb_txInf.getP110Jb().getTxJnl().getSrcRefNm().equals(cti01.getSrcRefNm()))
			{
				FFPVO_Pacs002_CTI01REPLY reply = new FFPVO_Pacs002_CTI01REPLY();
				reply.setMsgTp(root.getHEAD().getMessageType());
				reply.setMsgId(root.getHEAD().getResponseRefno());
				reply.setSystemId(root.getHEAD().getResponseID());
				reply.setSrcRefNm(cti01.getSrcRefNm());
				reply.setRsltCd(cti01.getRsltCd());
				reply.setRejCd(cti01.getRejCd());
				reply.setRejMsg(cti01.getRejMsg());
				msgJb_txInf.setCti01Reply(reply);
			}
			else
			{
				//TODO
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public FFPVO_Pacs002_TxInfAndSts getMsgJb_txInf() {
		return msgJb_txInf;
	}

	public void setMsgJb_txInf(FFPVO_Pacs002_TxInfAndSts msgJb_txInf) {
		this.msgJb_txInf = msgJb_txInf;
	}
	
	
}
