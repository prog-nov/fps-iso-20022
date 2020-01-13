package com.forms.ffp.bussiness.iclfps.pacs003;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpddi02.FFPDDI02;
import com.forms.ffp.adaptor.jaxb.participant.response.ROOT;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.msg.participant.FFPParticipantMessageConverter;

public class FFPMsgPacs003_DDI02 extends FFPMsgBaseParticipantMessage
{
	private FFPVO_Pacs003 pacs003 = null;

	public FFPMsgPacs003_DDI02(FFPVO_Pacs003 ip_pacs003)
	{
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDI02;
		this.pacs003 = ip_pacs003;
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public BODY marshalMsgReqBody()
	{
		BODY body = null;
		FFPDDI02 ddi02 = new FFPDDI02();
		ddi02.setSrcRefNm(pacs003.getP200Jb().getSrcRefNm());
		ddi02.setDbtrNm(pacs003.getDrctDbtTxInf().getDbtrNm());
		ddi02.setDbtrAcTp(pacs003.getDrctDbtTxInf().getDbtrAcctIdOthSchPrtry());
		ddi02.setDbtrAcNo(pacs003.getDrctDbtTxInf().getDbtrAcctIdOthId());
		ddi02.setSettlAmt(pacs003.getDrctDbtTxInf().getIntrBkSttlmAmt());
		ddi02.setSettlCcy(pacs003.getDrctDbtTxInf().getIntrBkSttlmCurrency());
		ddi02.setDrctDbtTxRltId(pacs003.getDrctDbtTxInf().getDrctDbtTxRltId());
		ddi02.setDbtrContPhone(pacs003.getDrctDbtTxInf().getDbtrContPhone());
		ddi02.setDbtrContEmailAddr(pacs003.getDrctDbtTxInf().getDbtrContEmailAddr());
		body = ddi02;
		return body;
	}

	public void unmarshalResponseMsg(String ip_responseMsg)
	{
		try
		{
			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
			com.forms.ffp.adaptor.jaxb.participant.response.ffpddi02.FFPDDI02 cddi02 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpddi02.FFPDDI02) body;

			if (pacs003.getP200Jb().getSrcRefNm().equals(cddi02.getSrcRefNm()))
			{
				FFPVO_Pacs003_DDI02REPLY reply = new FFPVO_Pacs003_DDI02REPLY();
				reply.setSrcRefNm(cddi02.getSrcRefNm());
				reply.setRsltCd(cddi02.getRsltCd());
				reply.setRejCd(cddi02.getRejCd());
				reply.setRejMsg(cddi02.getRejMsg());
				pacs003.setDdi02Reply(reply);
			} 
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
