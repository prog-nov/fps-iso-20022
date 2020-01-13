package com.forms.ffp.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class FFPXMLUtils
{
	protected static Logger _logger = LoggerFactory.getLogger(FFPXMLUtils.class);

	public static Document string2Documnet(String in)
	{
		try
		{
			DocumentBuilderFactory docbuilderfactory = DocumentBuilderFactory.newInstance();
			docbuilderfactory.setNamespaceAware(true);

			DocumentBuilder docbuilder = docbuilderfactory.newDocumentBuilder();
			docbuilder.setErrorHandler(new ErrorHandler()
			{
				public void warning(SAXParseException arg0) throws SAXException
				{
					FFPXMLUtils._logger.warn(arg0.getMessage());
				}

				public void fatalError(SAXParseException arg0) throws SAXException
				{
					FFPXMLUtils._logger.error(arg0.getMessage());
				}

				public void error(SAXParseException arg0) throws SAXException
				{
					FFPXMLUtils._logger.error(arg0.getMessage());
				}
			});
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(in));
			return docbuilder.parse(is);
		} catch (SAXParseException saxpe)
		{
			_logger.debug("Invalid XML doc. Exception=" + saxpe.toString());
			throw new RuntimeException("Invalid XML document.");
		} catch (Exception e)
		{
			_logger.error("Input String=" + in, e);
			throw new RuntimeException(String.format("Fail to parse XML document. %s: %s", new Object[] { e.getClass().getSimpleName(), e.getMessage() }));
		}
	}
	
	public static String domToString(Element el) throws Exception
	{
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		StringWriter buffer = new StringWriter();
		transformer.transform(new DOMSource(el), new StreamResult(buffer));
		String str = buffer.toString();
		return str;
	}
}
