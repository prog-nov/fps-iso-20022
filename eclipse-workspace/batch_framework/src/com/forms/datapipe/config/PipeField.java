package com.forms.datapipe.config;

/**
 * 对应 /pipe-config/pipe-data/field 属性
 * 
 * @author cxl
 * 
 */
public class PipeField
{
    /*
     * 变量名
     */
    private String name;

    /*
     * 变量对应数据类型
     */
    private String dataType;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "[name:" + name + ",dataType:" + dataType + " ]";
    }

}
