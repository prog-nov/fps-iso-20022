package com.forms.ffp.core.validate;

import java.io.ByteArrayInputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;

public abstract class FFPBaseXmlValidate 
{
	public abstract SAXValidator createValidate();
	
	
	public void xmlVlidate(String message)
	{
		try
		{
			//InputStream is = new InputStream();
			//xml error handler
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			
			SAXReader xmlReader = new SAXReader();
			
			Document xmlDocument = (Document) xmlReader.read(new ByteArrayInputStream(message.getBytes()));
			
			SAXValidator validate = createValidate();
			validate.setErrorHandler(errorHandler);
			validate.validate(xmlDocument);
			
			XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint()); 
			
			if(errorHandler.getErrors().hasContent())
			{
				writer.write(errorHandler.getErrors());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
