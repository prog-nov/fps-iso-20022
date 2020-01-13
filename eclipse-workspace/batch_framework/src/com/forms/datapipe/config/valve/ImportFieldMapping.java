package com.forms.datapipe.config.valve;

public class ImportFieldMapping
{
    private String inputField;

    private String pipeField;

    public String getInputField()
    {
        return inputField;
    }

    public void setInputField(String inputField)
    {
        this.inputField = inputField;
    }

    public String getPipeField()
    {
        return pipeField;
    }

    public void setPipeField(String pipeField)
    {
        this.pipeField = pipeField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[inputField=");
        sb.append(inputField);
        sb.append(";pipeField=");
        sb.append(pipeField);
        sb.append("]");
        return sb.toString();
    }
}
