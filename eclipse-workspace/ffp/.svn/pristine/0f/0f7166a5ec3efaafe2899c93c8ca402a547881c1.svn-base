package com.forms.ffp.bussiness.participant.addressing.ffpadr03;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import com.forms.ffp.adaptor.jaxb.participant.response.BODY;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.CUSTOMERNAMETYPE;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.DefaultInd;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.FFPADR03;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.LangType;
import com.forms.ffp.adaptor.jaxb.participant.response.ffpadr03.SUMMARYTYPE;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.participant.FFPMsgBaseResponseParticipantMessage;
import com.forms.ffp.core.utils.FFPDateUtils;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110dtl;

public class FFPMsgFpsadrs007_Adr03 extends FFPMsgBaseResponseParticipantMessage{
	private FFPJbA110 a110;
	private String sts;
	private String rejCode;
	FFPMsgFpsadrs007_Adr03(FFPJbA110 a110){
		super();
		this.a110=a110;
		sts = a110.getTxJnl().getTxStat();
		rejCode=a110.getTxJnl().getTxRejCode();
		this.reqRefNo=this.getReqRefNo();
		this.requestID = FFPConstants.MSG_CODE_FFP;
		this.responseID = FFPConstants.MSG_CODE_AGENT;
		this.msgType="FFPADR03";
	}
	
	public BODY marshalMsgResBody()
	{
		FFPADR03 adr03 = new FFPADR03();
		adr03.setSrcRefNm(a110.getSrcRefNm());
		adr03.setProxyId(a110.getProxyId());
		adr03.setProxyIdTp(a110.getProxyIdTp());
		//REJCT
		if(sts.equals(FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus())){
			adr03.setNoOfAdr("0");
			adr03.setRjCd(rejCode);
			adr03.setSts("R");
			return adr03;
		//TIME OUT
		}else if(sts.equals(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus())){
			adr03.setNoOfAdr("0");
			adr03.setSts("R");
			adr03.setRjCd("#TIME OUT");
			return adr03;
		}
		List<FFPJbA110dtl> list =a110.getAdrList();
		
		if(list == null){
			adr03.setNoOfAdr("0");
			adr03.setSts("R");
			adr03.setRjCd("#OTHERS");//TODO
			return adr03;
		}
		FFPJbA110dtl a110dtl=null;
		List<SUMMARYTYPE> listSum = adr03.getSmry();
		SUMMARYTYPE smry=null;
		for(int i=0,len = list.size();i<len;i++){
			a110dtl= list.get(i);
			if(i==0){				
				if(!(String.valueOf(len)).equals(a110dtl.getNoOfAdr())){
					adr03.setSts("R");
					adr03.setNoOfAdr(a110dtl.getNoOfAdr());
					adr03.setRjCd("#WRONG MSG!");//TODO
					break;
				}else{
					adr03.setSts("A");
					adr03.setNoOfAdr(a110dtl.getNoOfAdr());
				}	
			}
			
			smry= new SUMMARYTYPE();
			List<CUSTOMERNAMETYPE> cusNmList =smry.getCusNm();
			if(a110dtl.getLangEN() != null){
				CUSTOMERNAMETYPE cusNmEN = new CUSTOMERNAMETYPE();
				cusNmEN.setLang(LangType.EN);
				cusNmEN.setDispNm(a110dtl.getDispNmEN());
				cusNmList.add(cusNmEN);
			}
			if(a110dtl.getLangZH() != null){
				CUSTOMERNAMETYPE cusNmZH = new CUSTOMERNAMETYPE();
				cusNmZH.setLang(LangType.ZH);
				cusNmZH.setDispNm(a110dtl.getDispNmEN());
				cusNmList.add(cusNmZH);
			}
			smry.setMmbId(a110dtl.getMmbId());
			smry.setDefInd(DefaultInd.fromValue(a110dtl.getDefInd()));
			smry.setPurpCd(a110dtl.getPurpCd());
			/**
			 * TODO
			 */
			SimpleDateFormat smpd = new SimpleDateFormat(FFPDateUtils.WEB_TIMESTAMP_FORMAT);
			
			try {
				smry.setCreDtTm(FFPXMLUtils.toGregorianDt(smpd.parse(a110dtl.getCreDtTm())));
				smry.setLstUpdDtTm(FFPXMLUtils.toGregorianDt(smpd.parse(a110dtl.getLstUpdDtTm())));
			} catch (ParseException e) {
				e.printStackTrace();
				smry.setCreDtTm(FFPXMLUtils.toGregorianDt(new Date()));
				smry.setLstUpdDtTm(FFPXMLUtils.toGregorianDt(new Date()));
			}
//			
			listSum.add(smry);	
		}
		
	
		return adr03;
	}

	public String getSts() {
		return sts;
	}

	public String getRejCode() {
		return rejCode;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public void setRejCode(String rejCode) {
		this.rejCode = rejCode;
	}
	
	
	
	
}
