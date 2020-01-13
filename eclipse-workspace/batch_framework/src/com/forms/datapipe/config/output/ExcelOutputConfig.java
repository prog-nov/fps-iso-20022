package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.forms.datapipe.config.input.FieldDefinition;

/**
 * 对应配置文件excel-output-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "excel-output-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExcelOutputConfig
{
    @XmlElement(name = "file-name")
    private String fileName;

    @XmlElement(name = "sheet-name")
    private String sheetName;

    @XmlElement(name = "encoding")
    private String encoding;

    @XmlElement(name = "template-file")
    private String templateFile;

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

    public String getTemplateFile()
    {
        return templateFile;
    }

    public void setTemplateFile(String templateFile)
    {
        this.templateFile = templateFile;
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
