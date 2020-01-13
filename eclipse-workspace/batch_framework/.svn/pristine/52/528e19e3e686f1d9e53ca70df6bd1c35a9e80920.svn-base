package com.forms.datapipe.performance;

import com.forms.datapipe.exception.DataPipeException;

public class Performance
{
    public Performance(Object target)
    {
        this.target = target;
    }

    /*
     * ����صĶ���
     */
    private Object target;

    /*
     * ������(ִ��)�Ĵ���
     */
    private long executeTimes;

    /*
     * ִ�л��ѵ���ʱ��(����)
     */
    private long cost;

    /*
     * ��ʼ��¼ʱ��
     */
    private long startTime;

    /**
     * ��ʼ��¼����endRecord���ʹ��
     */
    public void startRecord() throws DataPipeException
    {
//        if (startTime != 0)
//            throw new DataPipeException(" Record is running! ");

        startTime = System.nanoTime();
    }

    /**
     * �����¼����startRecord���ʹ��
     */
    public void endRecord() throws DataPipeException
    {
//        if (startTime == 0)
//            throw new DataPipeException(
//                " [ Call method 'startRecord' first! ] ");

        record(System.nanoTime() - startTime);
        startTime = 0;
    }

    public void record(long cost)
    {
        this.cost += cost;
        this.executeTimes++;
    }

    public Object getTarget()
    {
        return target;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    public long getExecuteTimes()
    {
        return executeTimes;
    }

    public void setExecuteTimes(long executeTimes)
    {
        this.executeTimes = executeTimes;
    }

    public long getCost()
    {
        return cost;
    }

    public void setCost(long cost)
    {
        this.cost = cost;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("[target:");
        sb.append(target);
        sb.append(";executeTimes:");
        sb.append(executeTimes);
        sb.append(";cost:");
        sb.append(cost/1000000);
        sb.append("ms]");
        return sb.toString();
    }

}
