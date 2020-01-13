package com.forms.datapipe.config.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 对应配置文件excel-input-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "excel-input-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExcelInputConfig
{
    @XmlElement(name = "file-name")
    private String fileName;

    @XmlElement(name = "sheet-name")
    private String sheetName;

    @XmlElement(name = "encoding")
    private String encoding;

    @XmlElement(name = "stop-at-null")
    private String stopAtNull;

    @XmlElement(name = "start-column")
    private int startColumn;

    @XmlElement(name = "start-row")
    private int startRow;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getSheetName()
    {
        return sheetName;
    }

    public void setSheetName(String sheetName)
    {
        this.sheetName = sheetName;
    }

    public String getEncoding()
    {
        return encoding;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    public String getStopAtNull()
    {
        return stopAtNull;
    }

    public void setStopAtNull(String stopAtNull)
    {
        this.stopAtNull = stopAtNull;
    }

    public int getStartColumn()
    {
        return startColumn;
    }

    public void setStartColumn(int startColumn)
    {
        this.startColumn = startColumn;
    }

    public int getStartRow()
    {
        return startRow;
    }

    public void setStartRow(int startRow)
    {
        this.startRow = startRow;
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
