package com.forms.ffp.bussiness.participant.ffpcto01;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.persistents.bean.payment.credittransfer.FFPJbP100;

public class FFPMsgCTO01_CTO01 extends FFPMsgBaseResponseParticipantMessage
{
	private FFPJbP100 p100 = null;
	
	private String rejCd = null;
	
	private String rejMsg = null;

	public FFPMsgCTO01_CTO01(FFPJbP100 p100, String rejCd, String rejMsg)
	{
		super(p100.getJnlActionList().get(0));
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTO01;
		this.p100 = p100;
		this.rejCd = rejCd;
		this.rejMsg = rejMsg;
	}

	public BODY marshalMsgResBody()
	{
		FFPCTO01 cto01 = new FFPCTO01();
		cto01.setSrcRefNm(p100.getTxJnl().getSrcRefNm());
		cto01.setFFPTransactionId(p100.getTxJnl().getJnlNo());
		cto01.setRsltCd("R");
		cto01.setRejCd(rejCd);
		cto01.setRejMsg(rejMsg);
		return cto01;
	}
}
