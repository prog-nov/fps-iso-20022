package com.forms.ffp.msg.participant;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.forms.ffp.adaptor.jaxb.participant.request.BODY;
import com.forms.ffp.adaptor.jaxb.participant.request.FFPCTI01;
import com.forms.ffp.adaptor.jaxb.participant.request.HEAD;
import com.forms.ffp.adaptor.jaxb.participant.request.ROOT;
import com.forms.ffp.utils.FFPStringUtils;

public class FFPParticipantMessageAdaptor
{
	private static int PARTICIPANT_MSG_LENGTH_FIELD_LEN = 8;
	
	public static String packageXml(ROOT root) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(new Class[] { ROOT.class });
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
		marshaller.setProperty("jaxb.encoding", "UTF-8");

		StringWriter sw = new StringWriter();
		marshaller.marshal(root, sw);
		String xmlStr = sw.toString();
		return FFPStringUtils.padZero(xmlStr.length(), PARTICIPANT_MSG_LENGTH_FIELD_LEN) + xmlStr;
	}

	public static ROOT parseObject(String message) throws Exception
	{
		int len = Integer.valueOf(message.substring(0, PARTICIPANT_MSG_LENGTH_FIELD_LEN));
//		String xmlStr = message.substring(PARTICIPANT_MSG_LENGTH_FIELD_LEN, len + PARTICIPANT_MSG_LENGTH_FIELD_LEN);
		//xml文件
		String xmlStr = message.substring(message.indexOf("<?"));
		
		JAXBContext context = JAXBContext.newInstance(ROOT.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		//TODO 分布解析，生成head和body,有没有可以统一生成的地方？
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlStr.toString()));
		Document doc = db.parse(is);
		
		Node headDoc = doc.getElementsByTagName("HEAD").item(0);
		JAXBElement<HEAD> headElement = unmarshaller.unmarshal(headDoc, HEAD.class);
		HEAD head = headElement.getValue();
		System.out.println(head);
		
		BODY body = null;
		if("FFPCTI01".equals(head.getMessageType()))
		{
			Node bodyDoc = doc.getElementsByTagName("BODY").item(0);
			JAXBElement<FFPCTI01> bodyElement = unmarshaller.unmarshal(bodyDoc, FFPCTI01.class);
			body = bodyElement.getValue();
		}
		
		ROOT root = new ROOT();
		root.setHEAD(head);
		root.setBODY(body);
		
		return root;
	}
}
