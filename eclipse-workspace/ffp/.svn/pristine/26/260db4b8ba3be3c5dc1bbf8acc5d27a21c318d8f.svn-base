package com.forms.ffp.core.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
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

	// ISO Normalised Date Time Date and time in UTC time format
	// YYYY-MM-DDThh:mm:ss.sssZ.
	public static XMLGregorianCalendar toGregorianDt(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
			gregorianCalendar.setTime(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static XMLGregorianCalendar toGregorianDtNoTs(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			SimpleDateFormat _dtType2 = new SimpleDateFormat("yyyy-MM-dd");
			String fmtDate = _dtType2.format(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(fmtDate);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	// ISO Date Time Type 1 YYYY-MM-DDThh:mm:ss.sss
	public static XMLGregorianCalendar toGregorianDtType1(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	// ISO Date Time Type 2 YYYY-MM-DDThh:mm:ss
	public static XMLGregorianCalendar toGregorianDtType2(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			SimpleDateFormat _dtType2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String fmtDate = _dtType2.format(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(fmtDate);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static String getStringValue(String value)
	{
		if ((value == null) || (value.length() == 0))
		{
			return null;
		}
		return value;
	}

	public static Boolean isNullObject(Object... input)
	{
		Boolean isNullObject = Boolean.valueOf(true);
		Object[] arrayOfObject;
		int j = (arrayOfObject = input).length;
		for (int i = 0; i < j; i++)
		{
			Object o = arrayOfObject[i];

			isNullObject = Boolean.valueOf(isNullObject.booleanValue() & o == null);
		}
		return isNullObject;
	}

	public static String appendSequence(String prefix, int index, int length)
	{
		StringBuffer sb = new StringBuffer(prefix);
		sb.append(FFPStringUtils.padZero(index + 1, length - prefix.length()));
		return sb.toString();
	}
}
