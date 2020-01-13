package com.forms.ffp.bussiness.common;

import javax.xml.bind.JAXBElement;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.Document;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.GroupHeader;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.MessageRoot;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.ObjectFactory;
import com.forms.ffp.adaptor.jaxb.iclfps.fps_admi_001_001_01.ReceiptMode;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.define.FFPConstantsServiceCode;
import com.forms.ffp.core.msg.iclfps.FFPMsgBaseHkiclMessage;
import com.forms.ffp.core.utils.FFPXMLUtils;
import com.forms.ffp.persistents.bean.tx.m100.FFPJbM100;

public class FFPMsgSwitchMode_FpsAdmi001 extends FFPMsgBaseHkiclMessage
{
	private FFPJbM100 m100;

	public FFPMsgSwitchMode_FpsAdmi001(FFPJbM100 m100)
	{
		super();
		this.m100 = m100;
		this.msgTypeName = FFPJaxbConstants.JAXB_MSG_TYPE_FPS_ADMI_001;
		this.msgBizSvc = FFPConstantsServiceCode.ICLFPS_SERVICECODE_ADMISV;
	}

	public JAXBElement<?> marshalMsgBizDataDocument()
	{
		Document loc_doc = createDocument();
		return (new ObjectFactory()).createDocument(loc_doc);
	}

	private Document createDocument()
	{
		ObjectFactory _objectfactory = new ObjectFactory();

		Document doc = _objectfactory.createDocument();
		MessageRoot root = new MessageRoot();
		GroupHeader head = new GroupHeader();
		head.setCreDtTm(FFPXMLUtils.toGregorianDt(m100.getSwtchgTs()));
		head.setMsgId(m100.getMsgId());
		root.setGrpHdr(head);
		root.setRcptMd(ReceiptMode.fromValue(m100.getRcptMd()));
		doc.setRcptMdSwtchgReq(root);
		return doc;
	}

	@Override
	public String getSendType()
	{
		return FFPConstants.SEND_TYPE_REQ;
	}

	@Override
	public String getPriority()
	{
		return FFPConstants.MQ_LEVEL_PRIORITY_HIGH;
	}

}
