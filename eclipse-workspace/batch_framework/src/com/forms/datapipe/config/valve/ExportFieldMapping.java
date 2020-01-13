package com.forms.datapipe.config.valve;

public class ExportFieldMapping
{
    private String pipeField;

    private String outputField;

    public String getPipeField()
    {
        return pipeField;
    }

    public void setPipeField(String pipeField)
    {
        this.pipeField = pipeField;
    }

    public String getOutputField()
    {
        return outputField;
    }

    public void setOutputField(String outputField)
    {
        this.outputField = outputField;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[pipeField=");
        sb.append(pipeField);
        sb.append(";outputField=");
        sb.append(outputField);
        sb.append("]");
        return sb.toString();
    }
}
