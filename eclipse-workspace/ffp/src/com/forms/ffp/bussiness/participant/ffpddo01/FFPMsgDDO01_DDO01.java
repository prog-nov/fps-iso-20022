package com.forms.ffp.bussiness.participant.ffpddo01;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpddo01.FFPDDO01;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.persistents.bean.payment.directdebit.FFPJbP210;

public class FFPMsgDDO01_DDO01 extends FFPMsgBaseResponseParticipantMessage {

	private FFPJbP210 p210 = null;
	
	private String rejCd = null;
	
	private String rejMsg = null;

	public FFPMsgDDO01_DDO01(FFPJbP210 p210, String rejCd, String rejMsg)
	{
		super(p210.getJnlActionList().get(0));
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPDDO01;
		this.p210 = p210;
		this.rejCd = rejCd;
		this.rejMsg = rejMsg;
	}

	public BODY marshalMsgResBody() {
		FFPDDO01 ddo01 = new FFPDDO01();
		ddo01.setSrcRefNm(p210.getTxJnl().getSrcRefNm());
		ddo01.setFFPTransactionId(p210.getTxJnl().getJnlNo());
		ddo01.setRsltCd(rejCd == null ? "S" : "R");
		ddo01.setRejCd(rejCd);
		ddo01.setRejMsg(rejMsg);
		return ddo01;
	}

}
