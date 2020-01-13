package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.forms.datapipe.config.input.FieldDefinition;


/**
 * 对应配置文件csvfile-output-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "csvfile-output-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvFileOutputConfig
{
    @XmlElement(name = "file-name")
    private String fileName;

    @XmlElement
    private String encoding;

    @XmlElement(name = "header-bytes")
    private int headerBytes;

    @XmlElement(name = "header-handle-class")
    private String headerHandleClass;
    
    @XmlElement(name = "footer-handle-class")
    private String footerHandleClass;

    @XmlElement(name = "buffer-size")
    private int bufferSize;

    @XmlElement(name = "line-feed")
    private String lineFeed;

    @XmlElement
    private String delimiter;

    @XmlElement
    private String enclosure;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;

    @XmlElement(name = "seq-pattern")
    private String seqPattern;

    @XmlElement(name = "seq-start-at")
    private int seqStartAt;

    @XmlElement(name = "split-rows")
    private int splitRows;

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

    public int getHeaderBytes()
    {
        return headerBytes;
    }

    public void setHeaderBytes(int headerBytes)
    {
        this.headerBytes = headerBytes;
    }

    public String getHeaderHandleClass()
    {
        return headerHandleClass;
    }

    public void setHeaderHandleClass(String headerHandleClass)
    {
        this.headerHandleClass = headerHandleClass;
    }

    public int getBufferSize()
    {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize)
    {
        this.bufferSize = bufferSize;
    }

    public String getLineFeed()
    {
        return lineFeed;
    }

    public void setLineFeed(String lineFeed)
    {
        this.lineFeed = lineFeed;
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

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition)
    {
        this.fieldDefinition = fieldDefinition;
    }

    public String getFooterHandleClass()
    {
        return footerHandleClass;
    }

    public void setFooterHandleClass(String footerHandleClass)
    {
        this.footerHandleClass = footerHandleClass;
    }

}
