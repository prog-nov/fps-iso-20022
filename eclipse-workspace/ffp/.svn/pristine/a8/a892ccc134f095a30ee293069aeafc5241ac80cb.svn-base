package com.forms.ffp.bussiness.participant.addressing.ffpadr01;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.Agent;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.ClearingSystemMemberIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.CustomerType;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.FinancialInstitutionIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.OptionCode;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.ProxyIDType;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.Purpose;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.PurposeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.SupportedOption;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.UnderlyingAmendmentDetails;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_002_001_01.YesNoIndicator;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.define.FFPConstantsTxJnl;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.addressing.FFPJbA100;

public class FFPMsgFfpadr01_Fpsadrs002 extends FFPMsgBaseHkiclMessage {

	private FFPVo_Ffpadr01 adr01 = null;

	private ObjectFactory _objFactory = new ObjectFactory();

	public FFPMsgFfpadr01_Fpsadrs002(FFPVo_Ffpadr01 adr01) {
		super();
		this.adr01 = adr01;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADRS_002;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_ADDR01;
		this.sendType = FFPConstants.SEND_TYPE_REQ;
		// this.priority = this.adr01.getParticipantWrapper().getPriority();
		// TODO this.priority???
		this.priority = "H";
	}

	public JAXBElement<?> marshalMsgBizDataDocument() {
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument() {

		FFPJbA100 jbA100 = adr01.getJbA100();

		Document doc = _objFactory.createDocument();
		MessageRoot msgRoot = _objFactory.createMessageRoot();
		GroupHeader gh = _objFactory.createGroupHeader();
		gh.setMsgId(this.msgID);
		gh.setCreDtTm(FFPXMLUtils.toGregorianDt(this.creDt));
		msgRoot.setGrpHdr(gh);

		UnderlyingAmendmentDetails uad = _objFactory.createUnderlyingAmendmentDetails();
		uad.setAdrReqId(jbA100.getAdrReqId());

		Agent agent = new Agent();
		FinancialInstitutionIdentification fi = new FinancialInstitutionIdentification();
		agent.setFinInstnId(fi);
		ClearingSystemMemberIdentification csmi = new ClearingSystemMemberIdentification();
		fi.setClrSysMmbId(csmi);
		csmi.setMmbId(jbA100.getClrCd());
		uad.setAgt(agent);

		uad.setCusTp(CustomerType.fromValue(jbA100.getCusTp()));
		uad.setDflt(YesNoIndicator.fromValue(jbA100.getDflt()));

		uad.setId(jbA100.getProxyId());

		Purpose purp = new Purpose();
		purp.setCd(PurposeCode.fromValue(jbA100.getPurpCd()));
		uad.setPurp(purp);

		SupportedOption so = new SupportedOption();
		so.getCd().add(OptionCode.fromValue(jbA100.getSupOpCd()));
		uad.setSupOp(so);

		uad.setTp(ProxyIDType.fromValue(jbA100.getProxyIdTp()));

		msgRoot.getUndrlygAmdmntDtls().add(uad);
		doc.setAdrAmdmntReq(msgRoot);

		return doc;
	}

	public void getResponseDataFromDB(FFPJbA100 jbA100Db){

		FFPJbA100 jbA100 = adr01.getJbA100();
		
		FFPVo_Ffpadr01_Ffpamr01REPLY reply = new FFPVo_Ffpadr01_Ffpamr01REPLY();
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
		
		reply.setSrcRefNm(jbA100.getTxJnl().getSrcRefNm());
		reply.setProxyId(jbA100.getProxyId());
		reply.setProxyIdTp(jbA100.getProxyIdTp());
		adr01.setReply(reply);
	}
}
