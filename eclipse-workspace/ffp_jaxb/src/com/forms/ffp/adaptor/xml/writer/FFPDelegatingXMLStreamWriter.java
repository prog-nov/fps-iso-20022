package com.forms.ffp.adaptor.xml.writer;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public abstract class FFPDelegatingXMLStreamWriter implements XMLStreamWriter
{
	private XMLStreamWriter _delegate;

	public FFPDelegatingXMLStreamWriter(XMLStreamWriter delegate)
	{
		this._delegate = delegate;
	}

	public void close() throws XMLStreamException
	{
		this._delegate.close();
	}

	public void flush() throws XMLStreamException
	{
		this._delegate.flush();
	}

	public NamespaceContext getNamespaceContext()
	{
		return this._delegate.getNamespaceContext();
	}

	public String getPrefix(String arg0) throws XMLStreamException
	{
		return this._delegate.getPrefix(arg0);
	}

	public Object getProperty(String arg0) throws IllegalArgumentException
	{
		return this._delegate.getProperty(arg0);
	}

	public void setDefaultNamespace(String arg0) throws XMLStreamException
	{
		this._delegate.setDefaultNamespace(arg0);
	}

	public void setNamespaceContext(NamespaceContext arg0) throws XMLStreamException
	{
		this._delegate.setNamespaceContext(arg0);
	}

	public void setPrefix(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.setPrefix(arg0, arg1);
	}

	public void writeAttribute(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeAttribute(arg0, arg1);
	}

	public void writeAttribute(String arg0, String arg1, String arg2) throws XMLStreamException
	{
		this._delegate.writeAttribute(arg0, arg1, arg2);
	}

	public void writeAttribute(String arg0, String arg1, String arg2, String arg3) throws XMLStreamException
	{
		this._delegate.writeAttribute(arg0, arg1, arg2, arg3);
	}

	public void writeCData(String arg0) throws XMLStreamException
	{
		this._delegate.writeCData(arg0);
	}

	public void writeCharacters(String arg0) throws XMLStreamException
	{
		this._delegate.writeCharacters(arg0);
	}

	public void writeCharacters(char[] arg0, int arg1, int arg2) throws XMLStreamException
	{
		this._delegate.writeCharacters(arg0, arg1, arg2);
	}

	public void writeComment(String arg0) throws XMLStreamException
	{
		this._delegate.writeComment(arg0);
	}

	public void writeDTD(String arg0) throws XMLStreamException
	{
		this._delegate.writeDTD(arg0);
	}

	public void writeDefaultNamespace(String arg0) throws XMLStreamException
	{
		this._delegate.writeDefaultNamespace(arg0);
	}

	public void writeEmptyElement(String arg0) throws XMLStreamException
	{
		this._delegate.writeEmptyElement(arg0);
	}

	public void writeEmptyElement(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeEmptyElement(arg0, arg1);
	}

	public void writeEmptyElement(String arg0, String arg1, String arg2) throws XMLStreamException
	{
		this._delegate.writeEmptyElement(arg0, arg1, arg2);
	}

	public void writeEndDocument() throws XMLStreamException
	{
		this._delegate.writeEndDocument();
	}

	public void writeEndElement() throws XMLStreamException
	{
		this._delegate.writeEndElement();
	}

	public void writeEntityRef(String arg0) throws XMLStreamException
	{
		this._delegate.writeEntityRef(arg0);
	}

	public void writeNamespace(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeNamespace(arg0, arg1);
	}

	public void writeProcessingInstruction(String arg0) throws XMLStreamException
	{
		this._delegate.writeProcessingInstruction(arg0);
	}

	public void writeProcessingInstruction(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeProcessingInstruction(arg0, arg1);
	}

	public void writeStartDocument() throws XMLStreamException
	{
		this._delegate.writeStartDocument();
	}

	public void writeStartDocument(String arg0) throws XMLStreamException
	{
		this._delegate.writeStartDocument(arg0);
	}

	public void writeStartDocument(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeStartDocument(arg0, arg1);
	}

	public void writeStartElement(String arg0) throws XMLStreamException
	{
		this._delegate.writeStartElement(arg0);
	}

	public void writeStartElement(String arg0, String arg1) throws XMLStreamException
	{
		this._delegate.writeStartElement(arg0, arg1);
	}

	public void writeStartElement(String arg0, String arg1, String arg2) throws XMLStreamException
	{
		this._delegate.writeStartElement(arg0, arg1, arg2);
	}
}
