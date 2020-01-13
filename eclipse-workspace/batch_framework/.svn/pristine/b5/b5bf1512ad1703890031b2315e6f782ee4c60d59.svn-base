package com.forms.datapipe;

import java.util.Map;

import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;

/**
 * 输出介质
 * 
 * @author cxl
 * 
 */
public interface Output
{
    /**
     * init
     * 
     * @param config --
     *            OutputConfig
     * @param pipeContext --
     *            pipeContext
     * @throws DataPipeException
     */
    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException;

    /**
     * 输出行数据
     * 
     * @param rowData
     * @throws DataPipeException
     */
    public void putRowData(Map<String, Object> rowData)
        throws DataPipeException;
    
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
    public OutputConfig getConfig();
    
    /**
     * 获取字段描述
     * 
     * @return FieldDefinition
     */
    public FieldDefinition getFieldDefinition();
}
