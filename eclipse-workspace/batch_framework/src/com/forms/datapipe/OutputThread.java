package com.forms.datapipe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.context.OutputContext;

public class OutputThread
    extends Thread
{

    private static Log log = LogFactory.getLog(OutputThread.class);

    private boolean keepOrder;

    private OutputContext out;

    private DataPipe dataPipe;

    private boolean finish = false;

    /*
     * 输出线程状态，1 - wake up, 2 - flush, 3 - wait
     */
    private int status;

    private boolean stopFlag = false;

    public OutputThread(DataPipe dataPipe, OutputContext out, boolean keepOrder)
    {
        this.dataPipe = dataPipe;
        this.keepOrder = keepOrder;
        this.out = out;
    }

    private void debugLog(String msg)
    {
        if (log.isDebugEnabled()) log.debug(msg);
    }

    public void run()
    {
        synchronized (dataPipe.getOutputThreadLock())
        {
            dataPipe.setOutputThreadStarted(true);
            while (true)
            {
                if (stopFlag) break;
                debugLog(" [ outputThread release outputThreadLock and wait for sub thread notify. ] ");
                try
                {
                    status = 3; // 输出线程进入等待
                    dataPipe.getOutputThreadLock().wait();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                debugLog(" [ outputThread Waked. ] ");
                status = 1; // 输出线程已被唤醒
                if (keepOrder && !dataPipe.isFinish() && !stopFlag)
                {
                    debugLog(" [ outputThread try to wake up mainThread to continue. ] ");
                    synchronized (dataPipe.getMainThreadLock())
                    {
                        debugLog(" [ outputThread get mainThreadLock. ] ");
                        dataPipe.getMainThreadLock().notifyAll();
                    }
                }
                try
                {
                    status = 2; // flush
                    out.flush(keepOrder);
                } catch (Exception e)
                {
                    e.printStackTrace();
                    out.getOutputCachePool().clear();
                    dataPipe.getErrors().put("Output thread occurs exception",
                        e);
                    dataPipe.clear();
                    break;
                }

                if (dataPipe.isFinish()
                    && dataPipe.getReadNum() == out.getWriteNum() && !stopFlag)
                {
                    debugLog(" [ outputThread finish all output and try to wake up mainThread. ] ");
                    synchronized (dataPipe.getMainThreadLock())
                    {
                        debugLog(" [ outputThread get mainThreadLock. ] ");
                        dataPipe.getMainThreadLock().notifyAll();
                    }
                    break;
                }
            }
        }
        finish = true;
        //DataPipe.getPool().shutdownNow();
        debugLog(" [ outputThread closed. ] ");
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public boolean isStopFlag()
    {
        return stopFlag;
    }

    public void setStopFlag(boolean stopFlag)
    {
        this.stopFlag = stopFlag;
    }

    public boolean isFinish()
    {
        return finish;
    }

    public void close()
    {
        // 关闭输出线程
        // 更新输出线程的停止标识
        stopFlag = true;
        if (status == 3)
        {
            try
            {
                //out.switchCachePool();
                while (!finish)
                {
                    synchronized (dataPipe.getOutputThreadLock())
                    {
                        dataPipe.getOutputThreadLock().notifyAll();
                    }
                    Thread.sleep(10);
                }
            } catch (Exception e)
            {
                dataPipe.getErrors().put(
                    "Output thread occurs exception when close method called.",
                    e);
            }
        } else
        {
        	finish = true;
        }
        //finish = true;
    }
}
