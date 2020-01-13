package com.forms.datapipe.config.datatype;

public class DataType
{
    private String name;

    private String validateMethod;

    private String parseMethod;

    private String printMethod;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValidateMethod()
    {
        return validateMethod;
    }

    public void setValidateMethod(String validateMethod)
    {
        this.validateMethod = validateMethod;
    }

    public String getParseMethod()
    {
        return parseMethod;
    }

    public void setParseMethod(String parseMethod)
    {
        this.parseMethod = parseMethod;
    }

    public String getPrintMethod()
    {
        return printMethod;
    }

    public void setPrintMethod(String printMethod)
    {
        this.printMethod = printMethod;
    }

}
