package com.forms.ffp.core.msg.participant;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import com.forms.ffp.adaptor.define.FFPJaxbConstants;
import com.forms.ffp.core.define.FFPConstants;
import com.forms.ffp.core.utils.FFPStringUtils;
import com.forms.ffp.core.validate.FFPBaseXmlValidate;

public class FFPParticipantMessageConverter
{
	private static Logger _logger = LoggerFactory.getLogger(FFPParticipantMessageConverter.class);

	public static String packageRequestObject2Xml(com.forms.ffp.adaptor.jaxb.participant.request.ROOT root) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_REQUEST);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(false));
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
		//marshaller.setEventHandler("");
		//marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "C:\\Users\\user\\eclipse-workspace\\ffp_jaxb\\schema\\participant\\Request\\FFPCTI01.xsd");
		JAXBElement<com.forms.ffp.adaptor.jaxb.participant.request.ROOT> rootEle = (new com.forms.ffp.adaptor.jaxb.participant.request.ObjectFactory()).createROOT(root);
		StringWriter sw = new StringWriter();
		marshaller.marshal(rootEle, sw);
		String xml = sw.toString();
		
		/*String xsd = "C:\\Users\\user\\Desktop\\xsd\\request\\FFPCTI01.xsd";
		File file = new File(xsd);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = in.read(buffer)) != -1) 
		{
			// loop read
			bo.write(buffer, 0, length);
		}
		byte[] databytes = bo.toByteArray();
		bo.close();
		in.close();
		
		Source xmlSource = new StreamSource(new ByteArrayInputStream(xml.getBytes()));
        Source xsdSource = new StreamSource(new ByteArrayInputStream(databytes));

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(xsdSource);

        schema.newValidator().validate(xmlSource);*/

		
		return packageDivideXml(xml);
	}
	
	public static String packageReponseObject2Xml(com.forms.ffp.adaptor.jaxb.participant.response.ROOT root) throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(FFPJaxbConstants.JAXB_CONTEXT_PACKAGE_PARTICIPANT_RESPONSE);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(false));
		marshaller.setProperty("jaxb.encoding", "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
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

			int len = Integer.valueOf(message.substring(0, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN));
			byte[] bytes = message.getBytes(FFPConstants.ENCODING_UTF8);
			byte[] messageBytes = new byte[len];
			System.arraycopy(bytes, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN, messageBytes, 0, len);
			String str1 = new String(messageBytes, FFPConstants.ENCODING_UTF8);
			
			
			String xsd = "C:\\Users\\user\\Desktop\\xsd\\request\\FFPCTO01.xsd";
			File file = new File(xsd);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = in.read(buffer)) != -1) 
			{
				// loop read
				bo.write(buffer, 0, length);
			}
			byte[] databytes = bo.toByteArray();
			bo.close();
			in.close();
			
			Source xmlSource = new StreamSource(new ByteArrayInputStream(str1.getBytes()));
	        Source xsdSource = new StreamSource(new ByteArrayInputStream(databytes));

	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(xsdSource);

	        schema.newValidator().validate(xmlSource);
			
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

			int len = Integer.valueOf(message.substring(0, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN));
			byte[] bytes = message.getBytes(FFPConstants.ENCODING_UTF8);
			byte[] messageBytes = new byte[len];
			System.arraycopy(bytes, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN, messageBytes, 0, len);
			String str1 = new String(messageBytes, FFPConstants.ENCODING_UTF8);
			
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
		String padZero = FFPStringUtils.padZero(loc_xml.getBytes().length, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN);
		
		return padZero + loc_xml;
	}
	
	
	public static com.forms.ffp.adaptor.jaxb.participant.request.ROOT parseXml2RequestObject(String message, FFPBaseXmlValidate xmlValidate) throws Exception
	{
		Unmarshaller unmarshaller = null;
		try
		{

			int len = Integer.valueOf(message.substring(0, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN));
			byte[] bytes = message.getBytes(FFPConstants.ENCODING_UTF8);
			byte[] messageBytes = new byte[len];
			System.arraycopy(bytes, FFPJaxbConstants.JAXB_CONTEXT_PARTICIPANT_LENGTHFIELD_LEN, messageBytes, 0, len);
			String str1 = new String(messageBytes, FFPConstants.ENCODING_UTF8);
			
			xmlValidate.xmlVlidate(str1);
			/*String xsd = "C:\\Users\\user\\Desktop\\xsd\\request\\FFPCTO01.xsd";
			File file = new File(xsd);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = in.read(buffer)) != -1) 
			{
				// loop read
				bo.write(buffer, 0, length);
			}
			byte[] databytes = bo.toByteArray();
			bo.close();
			in.close();
			
			Source xmlSource = new StreamSource(new ByteArrayInputStream(str1.getBytes()));
	        Source xsdSource = new StreamSource(new ByteArrayInputStream(databytes));

	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = factory.newSchema(xsdSource);

	        schema.newValidator().validate(xmlSource);*/
			
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
}
