package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.input.Handlers;

/**
 * 对应配置文件fixedfile-output-ex-config.xml
 * 
 * @author YY
 * 
 */
@XmlRootElement(name = "fixedfile-output-ex-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class FixedFileOutputExConfig
{
	@XmlElement(name = "file-name")
    private String fileName;

    @XmlElement
    private String encoding;
    
    @XmlElement
    private String delimiter;

    @XmlElement
    private String enclosure;

    @XmlElement(name = "header-handle-class")
    private String headerHandleClass;
    
    @XmlElement(name = "footer-handle-class")
    private String footerHandleClass;

    @XmlElement(name = "flush-rows")
    private int flushRows;

    @XmlElement(name = "line-feed")
    private String lineFeed;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;
    
    @XmlElement(name = "field-definition-handlers")
    private Handlers handlers;

    @XmlElement(name = "seq-pattern")
    private String seqPattern;
    
    @XmlElement(name = "file-infos")
    private FileInfos fileInfos;

    @XmlElement(name = "seq-start-at")
    private int seqStartAt;

    @XmlElement(name = "split-rows")
    private int splitRows;

    public String getFooterHandleClass()
    {
        return footerHandleClass;
    }

    public void setFooterHandleClass(String footerHandleClass)
    {
        this.footerHandleClass = footerHandleClass;
    }

    public String getSeqPattern()
    {
        return seqPattern;
    }

    public void setSeqPattern(String seqPattern)
    {
        this.seqPattern = seqPattern;
    }

    public int getSeqStartAt()
    {
        return seqStartAt;
    }

    public void setSeqStartAt(int seqStartAt)
    {
        this.seqStartAt = seqStartAt;
    }

    public int getSplitRows()
    {
        return splitRows;
    }

    public void setSplitRows(int splitRows)
    {
        this.splitRows = splitRows;
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

    public String getHeaderHandleClass()
    {
        return headerHandleClass;
    }

    public void setHeaderHandleClass(String headerHandleClass)
    {
        this.headerHandleClass = headerHandleClass;
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

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition)
    {
        this.fieldDefinition = fieldDefinition;
    }

    public Handlers getHandlers()
    {
        return handlers;
    }

    public void setHandlers(Handlers handlers)
    {
        this.handlers = handlers;
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

	public FileInfos getFileInfos()
	{
		return fileInfos;
	}

	public void setFileInfos(FileInfos fileInfos)
	{
		this.fileInfos = fileInfos;
	}
}
