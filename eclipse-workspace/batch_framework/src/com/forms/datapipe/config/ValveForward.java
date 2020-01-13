package com.forms.datapipe.config;

/**
 * 对应 /pipe-config/valves/valve/forwards/forward和
 * /pipe-config/global-forwards/forwards/forward属性
 * 
 * @author cxl
 * 
 */
public class ValveForward
{
    /*
     * name
     */
    private String name;

    /*
     * 下个阀门
     */
    private String nextValve;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNextValve()
    {
        return nextValve;
    }

    public void setNextValve(String nextValve)
    {
        this.nextValve = nextValve;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof ValveForward)
        {
            ValveForward another = (ValveForward) obj;
            return this.name.equals(another.name);
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return "[name:" + name + ",nextValve:" + nextValve + " ]";
    }
}
