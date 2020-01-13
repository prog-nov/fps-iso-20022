package com.forms.datapipe;

import java.util.Map;

import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;

/**
 * 输入介质
 * 
 * @author cxl
 * 
 */
public interface Input
{
    /**
     * init
     * 
     * @param config --
     *            ValveConfig
     * @param pipeContext --
     *            pipeContext
     * @throws DataPipeException
     */
    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException;

    /**
     * 获取头信息
     * 
     * @return
     * @throws DataPipeException
     */
    public Map<String, String> getHeaderData() throws DataPipeException;
    
    /**
     * 获取尾信息
     * 
     * @return
     * @throws DataPipeException
     */
    public Map<String, String> getFooterData() throws DataPipeException;

    /**
     * 获取当前行数据
     * 
     * @return
     * @throws DataPipeException
     */
    public Map<String, Object> getRowData() throws DataPipeException;
    
    /**
     * 获取当前行号
     * 
     * @return
     */
    public int getRowIndex();

    /**
     * release resource
     * 
     * @throws DataPipeException
     */
    public void close() throws DataPipeException;

    /**
     * get Config
     * 
     * @return Config
     */
    public InputConfig getConfig();

    /**
     * 获取字段描述
     * 
     * @return FieldDefinition
     */
    public FieldDefinition getFieldDefinition();
}
