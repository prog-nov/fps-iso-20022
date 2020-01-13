package com.forms.datapipe;

import java.util.Map;

import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.JobExecuteMonitor;

/**
 * 简化DataPipe的调用
 * 
 * @author cxl
 * 
 */
public class DataPipeWrapper
{
    /**
     * DataPipe.transfer
     * 
     * @param pipeConfig --
     *            配置文件
     * @param parameters --
     *            外部传入参数
     * @return DataPipe 用来给caller从DataPipe获取参数值
     * @throws DataPipeException
     */
    public static DataPipe transfer(String pipeConfig,
        Map<String, String> parameters) throws DataPipeException
    {
        DataPipe dataPipe = new DataPipe();
        try
        {
            dataPipe.init(pipeConfig, parameters);
            dataPipe.transfer();
            JobExecuteMonitor.finish(dataPipe);
            return dataPipe;
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new DataPipeException(e);
        } finally
        {
            try
            {
                dataPipe.close();
            } catch (DataPipeException e)
            {
                e.printStackTrace();
                throw e;
            }
        }
    }
    
    public static void close()
    {
    	DataPipe.getPool().shutdown();
    }
}
