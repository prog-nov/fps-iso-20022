package com.forms.ffp.bussiness.participant.addressing.ffpadr01;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpamr01.FFPAMR01;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpamr01.UNDRLYGDTLSTYPE;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;

public class FFPMsgFfpadr01_Ffpamr01 extends FFPMsgBaseResponseParticipantMessage{

	private FFPVo_Ffpadr01_Ffpamr01REPLY reply = null;
	
	public FFPMsgFfpadr01_Ffpamr01(FFPVo_Ffpadr01_Ffpamr01REPLY reply){
		this.reply = reply;
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPAMR04;
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	@Override
	public BODY marshalMsgResBody() {
		
		FFPAMR01 amr = new FFPAMR01();
		UNDRLYGDTLSTYPE uls = new UNDRLYGDTLSTYPE();
		uls.setProxyId(this.reply.getProxyId());
		uls.setProxyIdTp(this.reply.getProxyIdTp());
		uls.setSrcRefNm(this.reply.getSrcRefNm());
		uls.setSts(this.reply.getSts());
		if("RJCT".equalsIgnoreCase(this.reply.getSts())){
			uls.setRsnInfCd(this.reply.getRsnInfCd());
		}
		amr.getUndrlygDtls().add(uls);
		
		return amr; 
	}
	
	
	
}
