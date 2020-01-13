package com.forms.datapipe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.PipeConfig;
import com.forms.datapipe.context.InputContext;
import com.forms.datapipe.context.OutputContext;
import com.forms.datapipe.context.OutputContextObserver;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.MultiFileInput;
import com.forms.datapipe.input.MultiFileInputSupport;
import com.forms.datapipe.output.FileSplitOutput;
import com.forms.datapipe.output.FileSplitOutputSupport;
import com.forms.datapipe.performance.JobExecuteMonitor;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.threadpool.ThreadPoolConfig;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * 管道，用于把数据从一个（或多个）存储介质，经过中间一系列的数据加工，过滤分流等处理， 重新写入另外一个（或多个）介质
 * 
 * @author cxl
 * 
 */
public class DataPipe
{
    private static Log log = LogFactory.getLog(DataPipe.class);

    private String pipeConfigFile;

    private PipeConfig pipeConfig;

    private Pipe pipe;

    private PipeContext pipeContext;

    private Map<String, Input> inputs;

    private Map<String, Output> outputs;

    private boolean initFlag = false;

    private boolean closeFlag = false;

    private boolean errFlag = false;

    private int status;

    private boolean mainThreadFinish = false;

    private OutputThread outputThread;

    private Map<String, Exception> errors = new HashMap<String, Exception>();

    /*
     * 用于存放记录的顺序号
     */
    public static final String INDEX_KEY = DataPipe.class.getName() + "_INDEX";

    /**
     * 线程池, 全局变量
     */
    private static ThreadPoolExecutor pool;

    private void debugLog(String msg)
    {
        if (log.isDebugEnabled()) log.debug(msg);
        JobExecuteMonitor.log(this, msg);
    }

    static
    {
        String corePoolSizeStr = ThreadPoolConfig.get("corePoolSize");
        String maximumPoolSizeStr = ThreadPoolConfig.get("maximumPoolSize");
        String keepAliveTimeStr = ThreadPoolConfig.get("keepAliveTime");
        String taskPoolSizeStr = ThreadPoolConfig.get("taskPoolSize");
        if (log.isInfoEnabled())
            log.info(" Get TreadPool config: {corePoolSize=" + corePoolSizeStr
                + ";maximumPoolSize=" + maximumPoolSizeStr + ";keepAliveTime="
                + keepAliveTimeStr + ";taskPoolSize=" + taskPoolSizeStr + "}. ");

        int corePoolSize = Integer.parseInt(corePoolSizeStr);
        int maximumPoolSize = Integer.parseInt(maximumPoolSizeStr);
        int keepAliveTime = Integer.parseInt(keepAliveTimeStr);
        int taskPoolSize = Integer.parseInt(taskPoolSizeStr);
        pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
            keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(
                taskPoolSize), new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * init
     * 
     * @param pipeConfigFile
     * @param parameters
     * @throws DataPipeException
     */
    public void init(String pipeConfigFile, Map<String, String> parameters)
        throws DataPipeException
    {
        debugLog(" [ method 'init' called. ] ");
        if (initFlag)
            throw new DataPipeException(" [ DataPipe has been inited!  ] ");
        this.pipeConfigFile = pipeConfigFile;
        pipeConfig = DataPipeConfigFactory.getPipeConfig(pipeConfigFile);
        pipeContext = new PipeContext(pipeConfig, parameters);
        debugLog(" [ init pipe. ] ");
        pipe = new Pipe();
        pipe.init(pipeContext, this);
        debugLog(" [ init inputs. ] ");
        inputs = new HashMap<String, Input>();
        for (InputConfig inputConfig : pipeConfig.getInputs().values())
        {
            debugLog(" [ init input: " + inputConfig.getName() + ".. ] ");
            Input input = (Input)DataPipeUtils.newInstance(inputConfig.getClazz());
            inputs.put(inputConfig.getName(), input);
            input.init(inputConfig, pipeContext);
            if (input instanceof MultiFileInputSupport)
            {
                MultiFileInputSupport multiFileInputSupport = (MultiFileInputSupport) input;
                if (MultiFileInput.isConfigValid(multiFileInputSupport))
                {
                    input = new MultiFileInput(multiFileInputSupport);
                    inputs.put(inputConfig.getName(), input);
                    input.init(inputConfig, pipeContext);
                }
            }
        }

        debugLog(" [ init outputs. ] ");
        outputs = new HashMap<String, Output>();
        for (OutputConfig outputConfig : pipeConfig.getOutputs().values())
        {
            debugLog(" [ init output: " + outputConfig.getName() + ".. ] ");
            Output output = (Output)DataPipeUtils.newInstance(outputConfig.getClazz());
            outputs.put(outputConfig.getName(), output);
            output.init(outputConfig, pipeContext);
            if (output instanceof FileSplitOutputSupport)
            {
                FileSplitOutputSupport fileSplitOutputSupport = (FileSplitOutputSupport) output;
                if (FileSplitOutput.isConfigValid(fileSplitOutputSupport))
                {
                    output = new FileSplitOutput(fileSplitOutputSupport);
                    outputs.put(outputConfig.getName(), output);
                    output.init(outputConfig, pipeContext);
                }
            }
        }

        initFlag = true;
        debugLog(" [ success! ] ");
    }

    /**
     * transfer data
     * 
     * 
     * @throws DataPipeException
     */
    public void transfer() throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        debugLog(" [ method 'transfer' called. ] ");

        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        if (closeFlag)
            throw new DataPipeException(" [ DataPipe has been closed!  ] ");

        if (pipeConfig.getMultiThreadConfig() != null
            && pipeConfig.getMultiThreadConfig().isUse())
        {
            try
            {
                transfer4MultiThread();
            } catch (DataPipeException e)
            {
                clear();
                throw e;
            }
        } else
        {
            transfer4NonMultiThread();
        }

        debugLog(" [ success! ] ");
        PerformanceMonitor.endRecord(this);
    }

    private Object outputThreadLock = new Object();

    private Object mainThreadLock = new Object();

    private int readNum = 0;

    private int completeNum = 0;

    private boolean finish = false;

    private boolean outputThreadStarted = false;

    private void transfer4MultiThread() throws DataPipeException
    {
        debugLog(" [ method 'transfer4MultiThread' called. ] ");

        final boolean keepOrder = pipeConfig.getMultiThreadConfig().isKeepOrder();
        final int bufferSize = pipeConfig.getMultiThreadConfig().getBufferSize();
        debugLog(" [ MultiThreadConfig: keepOrder=" + keepOrder
            + ";bufferSize=" + bufferSize + " ] ");

        // define OutputContext and add Observer
        final OutputContext out = new OutputContext(outputs,
            new OutputContextObserver()
            {
                public void putOutputDataCalled(OutputContext out)
                    throws DataPipeException
                {
                    completeNum++;
                    if ((finish && readNum == completeNum)
                        || completeNum % bufferSize == 0)
                    {
                        wakeupOutputThread(out);
                    }
                }

                private void wakeupOutputThread(OutputContext out)
                    throws DataPipeException
                {
                    String thread = Thread.currentThread().getName();
                    if (keepOrder)
                    {
                        debugLog(" [ subThread '"
                            + thread
                            + "' is waiting for mainThread and outputThread into a wait state. ] ");
                    } else
                    {
                        debugLog(" [ subThread '"
                            + thread
                            + "' is waiting for outputThread into a wait state. ] ");
                    }

                    // 在notify outputThread之前outputThread必须先进入wait状态
                    synchronized (outputThreadLock)
                    {
                        debugLog(" [ subThread '" + thread
                            + "' get outputThreadLock. ] ");
                    }

                    // 如果是keepOrder和finish时, 在notify
                    // outputThread之前mainThread还必须先进入wait状态
                    if (keepOrder || finish)
                    {
                        synchronized (mainThreadLock)
                        {
                            debugLog(" [ subThread '" + thread
                                + "' get mainThreadLock. ] ");
                        }
                    }

                    while (!out.isOutputCachePoolEmpty())
                    {
                        debugLog(" [ subThread '"
                            + thread
                            + "' is waiting for outputThread to clear OutputCachePool. ] ");
                        try
                        {
                            synchronized (this)
                            {
                                this.wait(1);
                            }
                        } catch (Exception e)
                        {
                            throw new DataPipeException(e);
                        }
                    }

                    debugLog(" [ subThread '"
                        + thread
                        + "' switchCachePool and try to wake up outputThread. ] ");
                    out.switchCachePool();
                    synchronized (outputThreadLock)
                    {
                        debugLog(" [ subThread '" + thread
                            + "' get outputThreadLock. ] ");
                        outputThreadLock.notifyAll();
                    }
                }
            }, bufferSize);
        // start outputThread
        outputThread = new OutputThread(this, out, keepOrder);
        outputThread.setPriority(Thread.currentThread().getPriority() + 1);
        outputThread.start();

        // make sure that outputThread has been started.
        while (!outputThreadStarted)
        {
            debugLog(" [ mainThread is waiting for outputThread to start. ] ");
            try
            {
                synchronized (this)
                {
                    this.wait(1);
                }
            } catch (Exception e)
            {
                throw new DataPipeException(e);
            }
        }

        synchronized (mainThreadLock)
        {
            // handle input and transfer
            for (final Input input : inputs.values())
            {
                if (errFlag) break;
                debugLog(" [ handle input: " + input.getConfig().getName()
                    + ". ] ");
                Map<String, Object> inputData = null;
                while ((inputData = input.getRowData()) != null && !errFlag)
                {
                    readNum++;
                    inputData.put(INDEX_KEY, String.valueOf(readNum)); // record
                    // seq
                    final Map<String, Object> inputData0 = inputData;
                    pool.execute(new Runnable()
                    {
                        public void run()
                        {
                            if (errFlag) return;
                            try
                            {
                                pipe.transfer(new InputContext(pipeContext,
                                    input, inputData0), out);
                            } catch (Exception e)
                            {
                            	e.printStackTrace();
                                errors.put("Exception occurs in row "
                                    + String.valueOf(readNum), e);
                                clear();
                            }
                        }
                    });
                    if (keepOrder && readNum % bufferSize == 0)
                    {
                        try
                        {
                            debugLog(" [ mainThread release mainThreadLock and wait for outputThread notify. ] ");
                            status = 2; // 主线程进入等待状态
                            mainThreadLock.wait();
                            debugLog(" [ mainThread Waked. ] ");
                            status = 1; // 主线程被唤醒
                        } catch (Exception e)
                        {
                            throw new DataPipeException(e);
                        }
                    }
                }
            }
            // 收尾
            if (!errFlag) waitForAllTaskFinish(out, keepOrder, bufferSize);
            mainThreadFinish = true;
        }
    }

    private void waitForAllTaskFinish(OutputContext out, boolean keepOrder,
        int bufferSize) throws DataPipeException
    {
        debugLog(" [ method 'waitForAllTaskFinish' called. ] ");

        synchronized (out)
        {
            finish = true;
            if (readNum == completeNum)
            {
                // 这种情况下由于已经没有sub thread, outputThread需要手动唤醒
                closeThread0(out);
            }
        }

        try
        {
            debugLog(" [ mainThread release mainThreadLock and wait for outputThread notify. ] ");
            status = 2;
            mainThreadLock.wait();
            debugLog(" [ mainThread Waked. ] ");
            status = 1;
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
    }

    private void closeThread0(final OutputContext out)
    {
        debugLog(" [ mainThread start a closeThread to wake up outputThreadLock. ] ");
        Thread thread = new Thread(new Runnable()
        {
            public void run()
            {
                // 在notify outputThread之前outputThread必须先进入wait状态
                synchronized (outputThreadLock)
                {
                    debugLog(" [ closeThread get outputThreadLock. ] ");
                }

                while (!out.isOutputCachePoolEmpty())
                {
                    debugLog(" [ closeThread is waiting for outputThread to clear OutputCachePool. ] ");
                    try
                    {
                        synchronized (this)
                        {
                            this.wait(1);
                        }
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                debugLog(" [ closeThread switchCachePool and try to wake up outputThread. ] ");
                try
                {
                    out.switchCachePool();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                synchronized (outputThreadLock)
                {
                    debugLog(" [ closeThread get outputThreadLock. ] ");
                    outputThreadLock.notifyAll();
                }
                debugLog(" [ closeThread closed. ] ");
            }
        });
        thread.start();
    }

    private void transfer4NonMultiThread() throws DataPipeException
    {
        debugLog(" [ method 'transfer4NonMultiThread' called. ] ");

        OutputContext out = new OutputContext(outputs, null, 0);
        for (Input input : inputs.values())
        {
            debugLog(" [ handle input: " + input.getConfig().getName() + ". ] ");
            Map<String, Object> inputData = null;
            while ((inputData = input.getRowData()) != null)
            {
                pipe.transfer(new InputContext(pipeContext, input, inputData),
                    out);
            }
        }
    }

    /**
     * release resource
     * 
     * @throws DataPipeException
     */
    public void close() throws DataPipeException
    {
        debugLog(" [ method 'close' called. ] ");
        if (closeFlag)
            throw new DataPipeException(" [ DataPipe has been closed!  ] ");
        closeFlag = true;

        if (pipe != null)
        {
            debugLog(" [ try to close pipe... ] ");
            try
            {
                pipe.close();
            } catch (DataPipeException e)
            {
                errors.put("pipe", e);
            }
        }

        if (inputs != null)
        {
            for (String key : inputs.keySet())
            {
                debugLog(" [ try to close input: " + key + "... ] ");
                try
                {
                    inputs.get(key).close();
                } catch (DataPipeException e)
                {
                    errors.put("input:" + key, e);
                    continue;
                }
            }
        }

        if (outputs != null)
        {
            for (String key : outputs.keySet())
            {
                debugLog(" [ try to close output: " + key + "... ] ");
                try
                {
                    outputs.get(key).close();
                } catch (DataPipeException e)
                {
                    errors.put("output:" + key, e);
                    continue;
                }
            }
        }

        if (!errors.isEmpty())
        {
            debugLog(" [ handle exception... ] ");
            StringBuffer errMsg = new StringBuffer(
                "[ Error occurs in DataPipe.close, detail:");
            for (String key : errors.keySet())
            {
                errMsg.append("[");
                errMsg.append(key);
                errMsg.append(", exception:");
                errMsg.append(errors.get(key).getMessage());
                errMsg.append("]");
            }
            errMsg.append(" ]");

            throw new DataPipeException(errMsg.toString());
        }
        debugLog(" [ success! ] ");
    }

    public String getPipeConfigFile() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return pipeConfigFile;
    }

    public PipeConfig getPipeConfig() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return pipeConfig;
    }

    public PipeContext getPipeContext() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return pipeContext;
    }

    public Pipe getPipe() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return pipe;
    }

    public Map<String, Input> getInputs() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return inputs;
    }

    public Map<String, Output> getOutputs() throws DataPipeException
    {
        if (!initFlag)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");
        return outputs;
    }

    void clear()
    {
        if (stopFlag) return;
        stopFlag = true;
        outputThread.close();
        if (outputThread.isFinish())
        {
            errFlag = true;
            // 唤醒主线程
            if (status == 2)
            {
                while (!mainThreadFinish)
                {
                    synchronized (mainThreadLock)
                    {
                        mainThreadLock.notifyAll();
                    }
                    try 
                    {
						Thread.sleep(10);
					} catch (InterruptedException e) 
					{
						errors.put("Output thread occurs exception when close method called.", e);
					}
                }
            }
        }
    }

    private boolean stopFlag = false;

    public Object getOutputThreadLock()
    {
        return outputThreadLock;
    }

    public Object getMainThreadLock()
    {
        return mainThreadLock;
    }

    public void setOutputThreadStarted(boolean outputThreadStarted)
    {
        this.outputThreadStarted = outputThreadStarted;
    }

    public int getReadNum()
    {
        return readNum;
    }

    public boolean isFinish()
    {
        return finish;
    }

    public void setErrFlag(boolean errFlag)
    {
        this.errFlag = errFlag;
    }

    public Map<String, Exception> getErrors()
    {
        return errors;
    }

    public void setErrors(Map<String, Exception> errors)
    {
        this.errors = errors;
    }

	public static ThreadPoolExecutor getPool()
	{
		return pool;
	}

	public static void setPool(ThreadPoolExecutor pool)
	{
		DataPipe.pool = pool;
	}
}
