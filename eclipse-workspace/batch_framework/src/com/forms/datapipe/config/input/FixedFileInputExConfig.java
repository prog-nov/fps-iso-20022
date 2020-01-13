package com.forms.datapipe.config.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 对应配置文件fixedfile-input-ex-config.xml
 * 
 * @author YY
 * 
 */
@XmlRootElement(name = "fixedfile-input-ex-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class FixedFileInputExConfig
{
	@XmlElement(name = "file-name")
	private String fileName;

	@XmlElement
	private String encoding;

	@XmlElement(name = "header-bytes")
	private int headerBytes;

	@XmlElement(name = "header-lines")
	private int headerLines;

	@XmlElement(name = "header-handle-class")
	private String headerHandleClass;

	@XmlElement(name = "footer-lines")
	private int footerLines;
	
	@XmlElement(name = "footer-bytes")
	private int footerBytes;

	@XmlElement(name = "footer-handle-class")
	private String footerHandleClass;

	@XmlElement(name = "buffer-size")
	private int bufferSize;
	
	@XmlElement(name = "pre-read-rows")
    private int prereadRows;

	@XmlElement(name = "line-feed")
	private String lineFeed;

	@XmlElement(name = "field-definition")
	private FieldDefinition fieldDefinition;

	@XmlElement(name = "field-definition-handlers")
	private Handlers handlers;

	@XmlElement(name = "seq-pattern")
	private String seqPattern;

	public String getSeqPattern()
	{
		return seqPattern;
	}

	public void setSeqPattern(String seqPattern)
	{
		this.seqPattern = seqPattern;
	}

	public FieldDefinition getFieldDefinition()
	{
		return fieldDefinition;
	}

	public void setFieldDefinition(FieldDefinition fieldDefinition)
	{
		this.fieldDefinition = fieldDefinition;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public int getHeaderBytes()
	{
		return headerBytes;
	}

	public void setHeaderBytes(int headerBytes)
	{
		this.headerBytes = headerBytes;
	}

	public int getBufferSize()
	{
		return bufferSize;
	}

	public void setBufferSize(int bufferSize)
	{
		this.bufferSize = bufferSize;
	}

	public String getHeaderHandleClass()
	{
		return headerHandleClass;
	}

	public void setHeaderHandleClass(String headerHandleClass)
	{
		this.headerHandleClass = headerHandleClass;
	}

	public String getLineFeed()
	{
		return lineFeed;
	}

	public void setLineFeed(String lineFeed)
	{
		this.lineFeed = lineFeed;
	}

	public int getHeaderLines()
	{
		return headerLines;
	}

	public void setHeaderLines(int headerLines)
	{
		this.headerLines = headerLines;
	}

	public int getFooterLines()
	{
		return footerLines;
	}

	public void setFooterLines(int footerLines)
	{
		this.footerLines = footerLines;
	}

	public String getFooterHandleClass()
	{
		return footerHandleClass;
	}

	public void setFooterHandleClass(String footerHandleClass)
	{
		this.footerHandleClass = footerHandleClass;
	}

	public Handlers getHandlers()
	{
		return handlers;
	}

	public void setHandlers(Handlers handlers)
	{
		this.handlers = handlers;
	}

	public int getFooterBytes()
	{
		return footerBytes;
	}

	public void setFooterBytes(int footerBytes)
	{
		this.footerBytes = footerBytes;
	}

	public int getPrereadRows()
	{
		return prereadRows;
	}

	public void setPrereadRows(int prereadRows)
	{
		this.prereadRows = prereadRows;
	}
}
