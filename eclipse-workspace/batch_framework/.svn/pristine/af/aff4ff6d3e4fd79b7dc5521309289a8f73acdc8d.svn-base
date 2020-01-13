package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FileInfo
{
	
	@XmlAttribute(name = "file-id")
	private String fileId;
	
	@XmlElement(name = "file-name")
    private String fileName;

    @XmlElement
    private String encoding;
    
    @XmlElement
    private String delimiter;

    @XmlElement
    private String enclosure;
    
    @XmlElement(name = "flush-rows")
    private int flushRows;

    @XmlElement(name = "line-feed")
    private String lineFeed;

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getDelimiter()
	{
		return delimiter;
	}

	public void setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
	}

	public String getEnclosure()
	{
		return enclosure;
	}

	public void setEnclosure(String enclosure)
	{
		this.enclosure = enclosure;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public int getFlushRows()
	{
		return flushRows;
	}

	public void setFlushRows(int flushRows)
	{
		this.flushRows = flushRows;
	}

	public String getLineFeed()
	{
		return lineFeed;
	}

	public void setLineFeed(String lineFeed)
	{
		this.lineFeed = lineFeed;
	}
}
