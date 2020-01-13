package com.forms.ffp.core.validate;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.io.SAXValidator;

public class FFPInwardXmlValidate extends FFPBaseXmlValidate
{
	
	public void init()
	{
		
	}
	
	@Override
	public SAXValidator createValidate() 
	{
		SAXValidator validator = null;
		try
		{
			//xml parse factory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "C:\\Users\\user\\Desktop\\xsd\\request\\FFPCTO01.xsd"); 
			
			
			validator = new SAXValidator(parser.getXMLReader());
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return validator;
	}

}
