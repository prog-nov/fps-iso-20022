package com.forms.ffp.bussiness.participant.addressing.ffpadr04;

import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.Agent;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.ClearingSystemMemberIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.FinancialInstitutionIdentification;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.ProxyIDType;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.Purpose;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_008_001_01.PurposeCode;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.FPSBusinessServiceCode;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.addressing.FFPJbA120;

public class FFPMsgAdr04_fpsadrs008 extends FFPMsgBaseHkiclMessage{
	private ObjectFactory _factory = new ObjectFactory();
	private FFPVOFfpadr04 adr04;
	public FFPMsgAdr04_fpsadrs008(FFPVOFfpadr04 adr04){
		super();
		this.adr04=adr04;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADRS_008;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC01;
		this.msgID=this.getMsgID();
	}
	
	
	public JAXBElement<BusinessApplicationHeaderV01> marshalMsgBizDataHead()
	{
		BusinessApplicationHeaderV01 appHdr = new BusinessApplicationHeaderV01();
		appHdr.setFr(createParty(this.msgFromID));
		appHdr.setTo(createParty(this.msgToID));
		appHdr.setBizMsgIdr(msgID);
		appHdr.setMsgDefIdr(this.msgTypeName);
		appHdr.setBizSvc(FPSBusinessServiceCode.fromValue(this.msgBizSvc));
		appHdr.setCreDt(FFPXMLUtils.toGregorianDt(this.creDt));

		return (new com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.ObjectFactory()).createAppHdr(appHdr);
	}
	
	public JAXBElement<?> marshalMsgBizDataDocument()
	{
		Document doc = createDocument();
		return new ObjectFactory().createDocument(doc);
	}


	private Document createDocument() {
		
		Document doc = _factory.createDocument();
		MessageRoot root = new MessageRoot();
		
		GroupHeader gh = new GroupHeader();
		gh.setMsgId(msgID);
		gh.setCreDtTm(FFPXMLUtils.toGregorianDt(new Date()));
		root.setGrpHdr(gh);
		
		
		List<AddressingScheme> adrSchme = root.getAdrSchme();
		List<FFPJbA120> list = adr04.getList();
		AddressingScheme scheme = null;
		for(FFPJbA120 c: list){
			scheme = new AddressingScheme();
			scheme.setAdrReqId(c.getAdrReqId());
			scheme.setId(c.getProxyId());
			scheme.setTp(ProxyIDType.fromValue(c.getProxyIdTp()));
			Purpose pose = new Purpose();
			pose.setCd(PurposeCode.fromValue(c.getPurpCd()));
			scheme.setPurp(pose);
			if(null != c.getMmbId()){
				Agent agt = new Agent();
				FinancialInstitutionIdentification fii = new FinancialInstitutionIdentification();
				ClearingSystemMemberIdentification csi = new ClearingSystemMemberIdentification();
				csi.setMmbId(c.getMmbId());
				fii.setClrSysMmbId(csi);
				agt.setFinInstnId(fii);
				scheme.setAgt(agt);
			}
			adrSchme.add(scheme);
		}
		doc.setAdrEnqReq(root);
		return doc;
	}


	@Override
	public String getSendType() {
		// TODO Auto-generated method stub
		return FFPConstants.SEND_TYPE_REQ;
	}


	@Override
	public String getPriority() {
		// TODO Auto-generated method stub
		return FFPConstantsServiceCode.ICLFPS_SERVICECODE_PAYC01.equals(msgBizSvc)?
				FFPConstants.MQ_LEVEL_PRIORITY_HIGH:FFPConstants.MQ_LEVEL_PRIORITY_MEDIUM;
	}
}
