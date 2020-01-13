package com.forms.ffp.bussiness.iclfps.fpsadrs005;


import java.util.Date;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpaun01.ADDRESSINGSCHEMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpaun01.DefaultInd;
import com.forms.ffp.adaptor.jaxb.participant.request.ffpaun01.FFPAUN01;
import com.forms.ffp.bussiness.iclfps.fpsadrs005.FFPVo_Fpsadrs005.AdrSchme;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseParticipantMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;

public class FFPMsgFpsadrs005_AUN01 extends FFPMsgBaseParticipantMessage {
	
	private AdrSchme adrschme = null;
	
//	private FFPVo_Fpsadrs005 adrs005 = null;
	
	public FFPMsgFpsadrs005_AUN01(FFPVo_Fpsadrs005 adrs005, int ip_i){
		this.msgType = FFPJaxbConstants.JAXB_MSG_TYPE_FFPCTI01;
		this.adrschme = adrs005.getAdrSchmes().get(ip_i);
//		this.adrs005 = adrs005;
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
	}

	@Override
	public BODY marshalMsgReqBody() {
		
		FFPAUN01 aun = new FFPAUN01();
		ADDRESSINGSCHEMETYPE as = new ADDRESSINGSCHEMETYPE();
//		as.setSrcRefNm(FFPIDUtils.getSrcRefNm());
		as.setCusId(adrschme.getCusId());
		as.setDflt(DefaultInd.fromValue(adrschme.getDefaultIndicator()));
		as.setProxyId(adrschme.getProxyId());
		as.setProxyIdTp(adrschme.getProxyIdType());
		as.setSts(adrschme.getStatus());
		as.setStsUpdTm(FFPXMLUtils.toGregorianDt(new Date()));
		aun.getAdrSchme().add(as);
		return aun;
	}

	@Override
	public void unmarshalResponseMsg(String ip_responseMsg) throws Exception {
//		try
//		{
//			ROOT root = FFPParticipantMessageConverter.parseXml2ReponseObject(ip_responseMsg);
//			com.forms.ffp.adaptor.jaxb.participant.response.BODY body = root.getBODY();
//			com.forms.ffp.adaptor.jaxb.participant.response.ffpaun01.FFPAUN01 aun01 = (com.forms.ffp.adaptor.jaxb.participant.response.ffpaun01.FFPAUN01)body;
//			
//			if(adrs005.getJbA100().getSrcRefNm().equals(aun01.getSrcRefNm())){
//				FFPVo_Fpsadrs005_AUN01REPLY reply = new FFPVo_Fpsadrs005_AUN01REPLY();
//				reply.setSrcRefNm(aun01.getSrcRefNm());
//				reply.setRsltCd(aun01.getRsltCd());
//				reply.setRejCd(aun01.getRejCd());
//				reply.setRejMsg(aun01.getRejMsg());
//				adrs005.setReply(reply);
//			}else{
//				//TODO
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		
		return;
	}
}
