package com.forms.ffp.bussiness.participant.addressing.ffpadr03;


import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.AddressingScheme;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_adrs_006_001_01.ProxyIDType;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.BusinessApplicationHeaderV01;
import com.forms.ffp.adaptor.jaxb.iclfps.head_001_001_01.FPSBusinessServiceCode;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.addressing.FFPJbA110;

public class FFPMsgAdr03_fpsadrs006 extends FFPMsgBaseHkiclMessage{
	private ObjectFactory _factory = new ObjectFactory();
	private FFPJbA110 a110=null;
	FFPMsgAdr03_fpsadrs006(FFPJbA110 a110){
		super();
		this.a110=a110;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADRS_006;
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
		/**
		 * CREATE HEADER
		 */
		GroupHeader gh = new GroupHeader();
		gh.setCreDtTm(FFPXMLUtils.toGregorianDt(new Date()));
		gh.setMsgId(this.msgID);
		root.setGrpHdr(gh);
		/**
		 *  CREATE BODY
		 */
		List<AddressingScheme> list = root.getAdrSchme();
		AddressingScheme sche = new AddressingScheme();
		sche.setAdrReqId(a110.getAdrReqId());
		sche.setId(a110.getProxyId());
		sche.setTp(ProxyIDType.fromValue(a110.getProxyIdTp()));
		list.add(sche);
		doc.setAdrSmryReq(root);
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
