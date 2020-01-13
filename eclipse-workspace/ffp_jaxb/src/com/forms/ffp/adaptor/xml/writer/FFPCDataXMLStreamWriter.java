package com.forms.ffp.adaptor.xml.writer;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class FFPCDataXMLStreamWriter extends FFPDelegatingXMLStreamWriter
{
	private static final String CDATA_BEGIN = "<![CDATA[";
	private static final String CDATA_END = "]]>";

	public FFPCDataXMLStreamWriter(XMLStreamWriter delegate)
	{
		super(delegate);
	}

	public void writeCharacters(String text) throws XMLStreamException
	{
		boolean useCData = false;
		if ((text.startsWith(CDATA_BEGIN)) && (text.endsWith(CDATA_END)))
		{
			text = text.substring(CDATA_BEGIN.length(), text.length() - CDATA_END.length());
			text = text.replaceAll(CDATA_END, "]]]]><![CDATA[>");
			useCData = true;
		}
		if (useCData)
		{
			super.writeCData(text);
		} else
		{
			super.writeCharacters(text);
		}
	}
}
