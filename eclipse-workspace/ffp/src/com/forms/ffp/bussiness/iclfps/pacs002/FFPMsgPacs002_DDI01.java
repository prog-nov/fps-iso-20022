package com.forms.ffp.bussiness.iclfps.pacs002;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpddi01.FFPDDI01;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;

public class FFPMsgPacs002_DDI01 extends FFPMsgBaseParticipantMessage
{
	private FFPVO_Pacs002_TxInfAndSts msgJb_txInf = null;

	public FFPMsgPacs002_DDI01(FFPVO_Pacs002 ip_pacs002, int ip_i)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDI01;
		this.msgJb_txInf = ip_pacs002.getTxInfList().get(ip_i);
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public BODY marshalMsgReqBody()
	{
		FFPDDI01 body = new FFPDDI01();
		body.setSrcRefNm(msgJb_txInf.getP200Jb().getTxJnl().getSrcRefNm());
		body.setPymtCatPrps(msgJb_txInf.getP200Jb().getPymtCatPrps());
		body.setSettlDate(msgJb_txInf.getP200Jb().getSettlementDate().toString());
		body.setSettlAmt(msgJb_txInf.getP200Jb().getSettlementAmount());
		body.setSettlCcy(msgJb_txInf.getP200Jb().getSettlementCurrency());
		body.setDbtrNm(msgJb_txInf.getP200Jb().getDebtorName());
		body.setDbtrAcTp(msgJb_txInf.getP200Jb().getDebtorAccountNumberType());
		body.setDbtrAcNo(msgJb_txInf.getP200Jb().getDebtorAccountNumber());
		body.setDbtrContPhone(msgJb_txInf.getP200Jb().getDebtorContPhone());
		body.setDbtrContEmailAddr(msgJb_txInf.getP200Jb().getDebtorContEmailAddr());
		body.setPytPurp(msgJb_txInf.getP200Jb().getPymtCatPrps());
		body.setRemInfo(msgJb_txInf.getP200Jb().getRemittanceInformation());
		return body;
	}

	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		try
		{
			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
			com.forms.ffp.adaptor.jaxb.participant.response.ffpddi01.FFPDDI01 ddi01 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpddi01.FFPDDI01) body;
			
			FFPVO_Pacs002_DDI01REPLY reply = new FFPVO_Pacs002_DDI01REPLY();
			reply.setMsgTp(root.getHEAD().getMessageType());
			reply.setMsgId(root.getHEAD().getResponseRefno());
			reply.setSystemId(root.getHEAD().getResponseID());
			reply.setTransactionId(ddi01.getTransactionId());
			reply.setRejCd(ddi01.getRejCd());
			reply.setRejMsg(ddi01.getRejMsg());
			reply.setSrcRefNm(ddi01.getSrcRefNm());
			reply.setRsltCd(ddi01.getRsltCd());
			msgJb_txInf.setDdi01Reply(reply);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}
