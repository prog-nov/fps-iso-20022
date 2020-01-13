package com.forms.ffp.bussiness.iclfps.pacs002;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.pacs_002_001_08.FPSTransactionStatusCode;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpddo01.FFPDDO01;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;

public class FFPMsgPacs002_DDO01 extends FFPMsgBaseResponseParticipantMessage {

	private FFPVO_Pacs002_TxInfAndSts msgJb_txInf = null;

	FFPMsgPacs002_DDO01(FFPVO_Pacs002 ip_pacs002, int ip_i) {
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDO01;
		this.msgJb_txInf = ip_pacs002.getTxInfList().get(ip_i);
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	public BODY marshalMsgResBody() {
		FFPDDO01 ddo01 = new FFPDDO01();
		ddo01.setSrcRefNm(msgJb_txInf.getP200Jb().getTxJnl().getSrcRefNm());
		ddo01.setFFPTransactionId(msgJb_txInf.getP200Jb().getTxJnl().getTransactionId());
		ddo01.setRsltCd(msgJb_txInf.getTxSts());
		if (!FPSTransactionStatusCode.ACSC.equals(msgJb_txInf.getTxSts())) {
			ddo01.setRejCd(msgJb_txInf.getTxStsRsnCode());
			if (msgJb_txInf.getTxStsAddtlInf() != null) {
				StringBuffer loc_sb = new StringBuffer();
				for (String str : msgJb_txInf.getTxStsAddtlInf())
					loc_sb.append(str);
				ddo01.setRejMsg(loc_sb.toString());
			}
		}
		return ddo01;
	}

}
