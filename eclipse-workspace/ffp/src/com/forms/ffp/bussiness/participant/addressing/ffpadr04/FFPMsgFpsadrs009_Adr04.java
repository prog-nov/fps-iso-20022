package com.forms.ffp.bussiness.participant.addressing.ffpadr04;

import java.util.List;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.ADDRESSINGSCHEMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.CUSTOMERNAMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.FFPADR04;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr04.LangType;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120dtl;

public class FFPMsgFpsadrs009_Adr04 extends FFPMsgBaseResponseParticipantMessage{
	
	private List<FFPJbA120dtl> list;
	
	private List<FFPJbA120> listA120;
	
	public FFPMsgFpsadrs009_Adr04(List<FFPJbA120dtl> list,List<FFPJbA120> listA120){
		super();
		this.list=list;
		this.listA120=listA120;
		this.reqRefNo=this.getReqRefNo();
		this.requestID = FFPConstants.MSG_CODE_AGENT;
		this.responseID = FFPConstants.MSG_CODE_FFP;
		this.msgType=FFPJaxbConstants.JAXB_MSG_TYPE_FFPADR04;
	}
	
	
	
	public BODY marshalMsgResBody(){
		
		if(null != this.list){
			return getNormalResult();
			
		}else{//TIME OUT
			return getTimeOutResult();
		}
		
	}



	private BODY getTimeOutResult() {
		FFPADR04  adr04 = new FFPADR04();
		adr04.setSrcRefNm(this.listA120.get(0).getSrcRefNm());
		List<ADDRESSINGSCHEMETYPE> listSche = adr04.getAdrSchme();
		ADDRESSINGSCHEMETYPE adrsche = null;
		for(FFPJbA120 c: this.listA120){
			adrsche = new ADDRESSINGSCHEMETYPE();			
			adrsche.setProxyId(c.getProxyId());
			adrsche.setProxyIdTp(c.getProxyIdTp());
			adrsche.setSts("R");
			adrsche.setRsnInfCd("#Time Out");			
			adrsche.setPurpCd(c.getPurpCd());
			adrsche.setMmbId(c.getMmbId());
			adrsche.setRsltCusId(c.getProxyId());
			adrsche.setRsltCusTp(c.getProxyIdTp());
			listSche.add(adrsche);
	
		}

		return adr04;
	}



	private BODY getNormalResult() {
		FFPADR04  adr04 = new FFPADR04();
		adr04.setSrcRefNm(this.list.get(0).getSrcRefNm());
		List<ADDRESSINGSCHEMETYPE> listSche = adr04.getAdrSchme();
		ADDRESSINGSCHEMETYPE adrsche = null;
		for(FFPJbA120dtl c: this.list){
			adrsche = new ADDRESSINGSCHEMETYPE();
			
			adrsche.setProxyId(c.getProxyId());
			adrsche.setProxyIdTp(c.getProxyIdTp());
			adrsche.setSts(c.getSts().equals("ACCT")?"A":"R");
			adrsche.setRsnInfCd(c.getRjCd());
			
			adrsche.setPurpCd(c.getPurpCd());
			
			List<CUSTOMERNAMETYPE> listNm=adrsche.getRsltCusNm();
			CUSTOMERNAMETYPE nameTp=null;
			if(LangType.EN.value().equals(c.getLangEN())){
				nameTp=new CUSTOMERNAMETYPE();
				nameTp.setLang(LangType.fromValue(c.getLangEN()));
				nameTp.setFullNm(c.getFullNmEN());
				nameTp.setDispNm(c.getDispNmEN());
				listNm.add(nameTp);
			}
			if(LangType.ZH.value().equals(c.getLangZH())){
				nameTp=new CUSTOMERNAMETYPE();
				nameTp.setLang(LangType.fromValue(c.getLangZH()));
				nameTp.setFullNm(c.getFullNmZH());
				nameTp.setDispNm(c.getDispNmZH());
				listNm.add(nameTp);
			}
			adrsche.setMmbId(c.getMmbId());
			adrsche.setRsltCusId(c.getRsltCusId());
			adrsche.setRsltCusTp(c.getRsltCusTp());
			listSche.add(adrsche);
	
		}

		return adr04;
	}
}
