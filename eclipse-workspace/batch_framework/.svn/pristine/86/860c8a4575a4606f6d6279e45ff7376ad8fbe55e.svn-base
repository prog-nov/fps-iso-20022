package com.forms.datapipe.config.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * fixedfile-input-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "fixedfile-input-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class FixedFileInputConfig
{
    @XmlElement(name = "file-name")
    private String fileName;

    @XmlElement
    private String encoding;

    @XmlElement(name = "header-bytes")
    private int headerBytes;

    @XmlElement(name = "header-handle-class")
    private String headerHandleClass;

    @XmlElement(name = "pre-read-rows")
    private int prereadRows;

    @XmlElement(name = "line-feed")
    private String lineFeed;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;
    
    @XmlElement(name = "seq-pattern")
    private String seqPattern;
    
    @XmlElement(name = "footer-bytes")
    private int footerBytes;
    
    @XmlElement(name = "footer-handle-class")
    private String footerHandleClass;

    // set method and get method begin
    public String getFooterHandleClass()
    {
        return footerHandleClass;
    }

    public void setFooterHandleClass(String footerHandleClass)
    {
        this.footerHandleClass = footerHandleClass;
    }

    public int getFooterBytes()
    {
        return footerBytes;
    }

    public void setFooterBytes(int footerBytes)
    {
        this.footerBytes = footerBytes;
    }

    public String getSeqPattern()
    {
        return seqPattern;
    }

    public void setSeqPattern(String seqPattern)
    {
        this.seqPattern = seqPattern;
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

    public int getPrereadRows()
    {
        return prereadRows;
    }

    public void setPrereadRows(int prereadRows)
    {
        this.prereadRows = prereadRows;
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

	//set method and get method end
	
	public int getFieldsLength()
	{
		int lineBytes = 0;
		for (Field field : fieldDefinition.getFields())
        {
            lineBytes += field.getBytes();
        }
		return lineBytes;
	}
}
