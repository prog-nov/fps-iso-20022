package com.forms.datapipe.performance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.datapipe.DataPipe;

public class JobExecuteMonitor
{
    private static Map<DataPipe, List<String>> monitorJobs = new HashMap<DataPipe, List<String>>();

    private static List<DataPipe> finishJobs = new ArrayList<DataPipe>();

    private static boolean on = false;

    public static void setOn()
    {
        on = true;
    }

    public static void setOff()
    {
        on = false;
    }

    public static void log(DataPipe dataPipe, String action)
    {
        if (!on) return;

        List<String> actions = monitorJobs.get(dataPipe);
        if (actions == null)
        {
            actions = new ArrayList<String>();
            monitorJobs.put(dataPipe, actions);
        }
        actions.add(action);
    }

    public static void finish(DataPipe dataPipe)
    {
        if (!on) return;

        finishJobs.add(dataPipe);
    }

    public static Map<DataPipe, List<String>> getFinishJobActions()
    {
        Map<DataPipe, List<String>> temp = new HashMap<DataPipe, List<String>>();
        for (DataPipe job : monitorJobs.keySet())
        {
            if (finishJobs.contains(job)) temp.put(job, monitorJobs.get(job));
        }
        return temp;
    }

    public static Map<DataPipe, List<String>> getUnfinishJobActions()
    {
        Map<DataPipe, List<String>> temp = new HashMap<DataPipe, List<String>>();
        for (DataPipe job : monitorJobs.keySet())
        {
            if (!finishJobs.contains(job)) temp.put(job, monitorJobs.get(job));
        }
        return temp;
    }

    public static void printJobActions(Map<DataPipe, List<String>> actions)
    {
//        for (DataPipe job : actions.keySet())
//        {
//            for (String action : actions.get(job))
//            {
//            }
//        }
    }

}
