package com.forms.ffp.core.msg.participant;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.core.utils.FFPStringUtils;

public class FFPParticipantMessageConverter
{
	private static Logger _logger = LoggerFactory.getLogger(FFPParticipantMessageConverter.class);

	public static String packageRequestObject2Xml(com.forms.ffp.adaptor.jaxb.participant.request.ROOT root) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_REQUEST);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(false));
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		JAXBElement<com.forms.ffp.adaptor.jaxb.participant.request.ROOT> rootEle = (new com.forms.ffp.adaptor.jaxb.participant.request.ObjectFactory()).createROOT(root);
		StringWriter sw = new StringWriter();
		marshaller.marshal(rootEle, sw);
		String xml = sw.toString();
		
		return packageDivideXml(xml);
	}
	
	public static String packageReponseObject2Xml(com.forms.ffp.adaptor.jaxb.participant.response.ROOT root) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_RESPONSE);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(false));
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		JAXBElement<com.forms.ffp.adaptor.jaxb.participant.response.ROOT> rootEle = (new com.forms.ffp.adaptor.jaxb.participant.response.ObjectFactory()).createROOT(root);
		StringWriter sw = new StringWriter();
		marshaller.marshal(rootEle, sw);
		String xml = sw.toString();
		
		return packageDivideXml(xml);
	}

	public static com.forms.ffp.adaptor.jaxb.participant.request.ROOT parseXml2RequestObject(String message) throws Exception
	{
		Unmarshaller unmarshaller = null;
		try
		{

			int len = Integer.valueOf(message.substring(0, 8));
			String str1 = message.substring(8, len + 8);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(str1));
			Document doc = db.parse(is);
			
			Node headDoc = doc.getElementsByTagName("HEAD").item(0);
			JAXBContext con = JAXBContext.newInstance(com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD.class);
			unmarshaller = con.createUnmarshaller();
			JAXBElement<com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD> headElement = unmarshaller.unmarshal(headDoc, com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD.class);
			com.forms.ffp.adaptor.jaxb.participant.request.head.HEAD head = headElement.getValue();
			
			Node bodyDoc = doc.getElementsByTagName("BODY").item(0);
			String packageName = FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_REQUEST + "." + head.getMessageType().toLowerCase();
			
			con = JAXBContext.newInstance(packageName);
			unmarshaller = con.createUnmarshaller();
			JAXBElement<?> bodyElement = (JAXBElement<?>)unmarshaller.unmarshal(bodyDoc);
			Object object = bodyElement.getValue();

			com.forms.ffp.adaptor.jaxb.participant.request.ROOT root = new com.forms.ffp.adaptor.jaxb.participant.request.ROOT();
			root.setHEAD(head);
			root.setBODY((com.forms.ffp.adaptor.jaxb.participant.request.BODY)object);
			return root;
		}catch (JAXBException e)
		{
			Object displayExpt = e;
			if (e.getLinkedException() != null)
			{
				displayExpt = e.getLinkedException();
			}

			_logger.error(String.format("unmarshal %s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
			e.printStackTrace();
			throw new RuntimeException(String.format("%s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
		} catch (Exception arg25)
		{
			_logger.error("unmarshal Exception: " + arg25.getMessage());
			arg25.printStackTrace();
			throw new RuntimeException(String.format("Exception: %s", new Object[] { arg25.getMessage() }));
		}
	}
	
	public static com.forms.ffp.adaptor.jaxb.participant.response.ROOT parseXml2ReponseObject(String message) throws Exception
	{
		Unmarshaller unmarshaller = null;
		try
		{

			int len = Integer.valueOf(message.substring(0, 8));
			String str1 = message.substring(8, len + 8);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(str1));
			Document doc = db.parse(is);
			
			Node headDoc = doc.getElementsByTagName("HEAD").item(0);
			JAXBContext con = JAXBContext.newInstance(com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD.class);
			unmarshaller = con.createUnmarshaller();
			JAXBElement<com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD> headElement = unmarshaller.unmarshal(headDoc, com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD.class);
			com.forms.ffp.adaptor.jaxb.participant.response.head.HEAD head = headElement.getValue();
			
			Node bodyDoc = doc.getElementsByTagName("BODY").item(0);
			String packageName = FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_RESPONSE + "." + head.getMessageType().toLowerCase();
			
			con = JAXBContext.newInstance(packageName);
			unmarshaller = con.createUnmarshaller();
			JAXBElement<?> bodyElement = (JAXBElement<?>)unmarshaller.unmarshal(bodyDoc);
			Object object = bodyElement.getValue();

			com.forms.ffp.adaptor.jaxb.participant.response.ROOT root = new com.forms.ffp.adaptor.jaxb.participant.response.ROOT();
			root.setHEAD(head);
			root.setBODY((com.forms.ffp.adaptor.jaxb.participant.response.BODY)object);
			return root;
		}catch (JAXBException e)
		{
			Object displayExpt = e;
			if (e.getLinkedException() != null)
			{
				displayExpt = e.getLinkedException();
			}

			_logger.error(String.format("unmarshal %s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
			e.printStackTrace();
			throw new RuntimeException(String.format("%s: %s", new Object[] { displayExpt.getClass().getSimpleName(), ((Throwable) displayExpt).getMessage() }));
		} catch (Exception arg25)
		{
			_logger.error("unmarshal Exception: " + arg25.getMessage());
			arg25.printStackTrace();
			throw new RuntimeException(String.format("Exception: %s", new Object[] { arg25.getMessage() }));
		}
	}
	
	private static String packageDivideXml(String xml)
	{
		//Replace BODY TAG
		String loc_xml = xml;
		String bodytag = "<BODY";
		int offset = loc_xml.indexOf(bodytag);
		String loc_tmp1 = loc_xml.substring(0, offset + bodytag.length());
		String loc_xml2 = loc_xml.substring(offset + bodytag.length());
		int offset2 = loc_xml2.indexOf(">");
		String loc_tmp2 = loc_xml2.substring(offset2);
		
		loc_xml = loc_tmp1 + loc_tmp2;
		
		// pending zero
		String padZero = FFPStringUtils.padZero(loc_xml.length(), 8);
		return padZero + loc_xml;
	}
}
