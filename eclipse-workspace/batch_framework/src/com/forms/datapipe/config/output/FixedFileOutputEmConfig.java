package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.forms.datapipe.config.input.FieldDefinition;



/**
 * fixedfile-output-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "fixedfile-em-output-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class FixedFileOutputEmConfig
{
	@XmlElement(name = "file-name")
    private String fileName;

    @XmlElement
    private String encoding;

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
    
    @XmlElement(name = "seq-pattern")
    private String seqPattern;

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
}
