package com.forms.batch.job.unit.participant.message.ffpcto01;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpcto01.FFPCTO01;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.core.utils.FFPIDUtils;

public class FFPBatch_RES_CTO01 extends FFPMsgBaseResponseParticipantMessage{

	private FFPCTO01 ffpcto01;
	public FFPBatch_RES_CTO01(FFPCTO01 ffpcto01) {
		super();
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTO01;
		this.requestID = FFPConstants.MSG_CODE_AGENT;
		this.responseID = FFPConstants.MSG_CODE_FFP;
		this.resRefNo = FFPIDUtils.getRefno();
		this.ffpcto01 = ffpcto01;
	}

	@Override
	public BODY marshalMsgResBody() {
		// TODO Auto-generated method stub
		BODY body = null;
		body = this.ffpcto01;
		return body;
	}

	@Override
	public void unmarshalResponseMsg(String ip_responseMsg) {
		// TODO Auto-generated method stub
		
		super.unmarshalResponseMsg(ip_responseMsg);
	}
	
	
}
