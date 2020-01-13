package com.forms.ffp.bussiness.participant.addressing.ffpadr02;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.Agent;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.ClearingSystemMemberIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.FinancialInstitutionIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_003_001_01.ProxyIDType;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;

public class FFPMsgFfpadr02_Fpsadrs003 extends FFPMsgBaseHkiclMessage {

	private ObjectFactory _objFactory = new ObjectFactory();

	private FFPVoFfpadr02 adr02 = null;

	public FFPMsgFfpadr02_Fpsadrs003(FFPVoFfpadr02 adr02) {
		super();
		this.adr02 = adr02;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADRS_003;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_ADDR01;
		this.sendType = FFPConstants.SEND_TYPE_REQ;
//		this.priority = this.adr01.getParticipantWrapper().getPriority();
		//TODO 
		this.priority = "H";
	}

	public JAXBElement<?> marshalMsgBizDataDocument() {
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument() {
		Document doc = _objFactory.createDocument();

		MessageRoot msgRoot = _objFactory.createMessageRoot();
		GroupHeader gh = _objFactory.createGroupHeader();
		gh.setMsgId(this.msgID);
		gh.setCreDtTm(FFPXMLUtils.toGregorianDt(this.creDt));
		msgRoot.setGrpHdr(gh);

		AddressingScheme as = _objFactory.createAddressingScheme();
		as.setAdrReqId(adr02.getJbA100().getAdrReqId());

		Agent agent = new Agent();
		FinancialInstitutionIdentification fi = new FinancialInstitutionIdentification();
		agent.setFinInstnId(fi);
		ClearingSystemMemberIdentification csmi = new ClearingSystemMemberIdentification();
		fi.setClrSysMmbId(csmi);
		csmi.setMmbId(adr02.getJbA100().getClrCd());
		as.setAgt(agent);

		as.setId(adr02.getJbA100().getProxyId());
		as.setTp(ProxyIDType.fromValue(adr02.getJbA100().getProxyIdTp()));

		msgRoot.getAdrSchme().add(as);
		doc.setAdrCxlReq(msgRoot);

		return doc;
	}

	public void getDataFromDB(FFPJbA100 jbA100Db) {

		FFPVo_Fpsadr02_Ffpamr01REPLY reply = new FFPVo_Fpsadr02_Ffpamr01REPLY();
		String txStatus = jbA100Db.getTxJnl().getTxStat();
		
		
		if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_TMOUT.getStatus().equalsIgnoreCase(txStatus)||FFPConstantsTxJnl.TX_STATUS.TX_STAT_FPS_REJCT.getStatus().equalsIgnoreCase(txStatus)){
			//time out or reject
			reply.setSts("RJCT");
			if(jbA100Db.getTxJnl().getTxRejCode() != null){
				reply.setRsnInfCd(jbA100Db.getTxJnl().getTxRejCode());
			}
		}else if(FFPConstantsTxJnl.TX_STATUS.TX_STAT_APPST.getStatus().equalsIgnoreCase(txStatus)){
			reply.setSts("ACCT");
		}
		
		reply.setSrcRefNm(adr02.getJbA100().getTxJnl().getSrcRefNm());
		reply.setProxyId(adr02.getJbA100().getProxyId());
		reply.setProxyIdTp(adr02.getJbA100().getProxyIdTp());
		adr02.setReply(reply);
	}

}
