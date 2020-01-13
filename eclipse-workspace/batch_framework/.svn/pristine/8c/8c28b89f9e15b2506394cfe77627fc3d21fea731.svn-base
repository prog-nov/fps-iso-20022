package com.forms.datapipe.performance;

import java.util.HashMap;
import java.util.Map;

import com.forms.datapipe.exception.DataPipeException;

/**
 * 监控器
 * 
 * @author cxl
 * 
 */
public class PerformanceMonitor
{
    private static Map<Object, Performance> monitorObjs = new HashMap<Object, Performance>();

    private static boolean on = false;

    public static void setOn()
    {
        on = true;
    }

    public static void setOff()
    {
        on = false;
    }

    /**
     * ��ʼ��¼����endRecord���ʹ��
     */
    public static void startRecord(Object target) throws DataPipeException
    {
        if (on) getPerformance(target).startRecord();
    }

    /**
     * �����¼����startRecord���ʹ��
     */
    public static void endRecord(Object target) throws DataPipeException
    {
        if (on) getPerformance(target).endRecord();
    }

    /**
     * ��¼�����ִ��ʱ��
     * 
     * @param target
     * @param cost
     */
    public static void record(Object target, long cost)
    {
        if (on) getPerformance(target).record(cost);
    }

    private static Performance getPerformance(Object target)
    {
        Performance performance = monitorObjs.get(target);
        if (performance == null)
        {
            performance = new Performance(target);
            monitorObjs.put(target, performance);
        }
        return performance;
    }

    public static Map<Object, Performance> getMonitorObjs()
    {
        return monitorObjs;
    }

    public static void print()
    {
//        for (Performance performance : monitorObjs.values())
//        {
//        }
    }
}
