package com.forms.datapipe.output;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.DatabaseOutputConfig;
import com.forms.datapipe.config.output.SqlParameter;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.framework.persistence.DataSourceForDataPipe;
import com.forms.framework.util.CommonAPI;

/**
 * DB output
 * 
 * @author cxl
 *
 */
public class DatabaseOutput
    implements Output
{

    private static Log log = LogFactory.getLog(DatabaseOutput.class);

    private DatabaseOutputConfig databaseOutputConfig;

    private DataSource dataSource;

    private String updateSql;

    private OutputConfig config;

    private List<Map<String, Object>> buffer;

    private int flushRows;

    private int rowIndex;
    
    private int count=0;
    
    private PipeContext pipeContext; 

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");
        this.config = config;
        this.pipeContext=pipeContext;
        databaseOutputConfig = DataPipeConfigFactory.getDatabaseOutputConfig(config.getConfigFile());
        
        Map<String, String> loc_map = null;
        try
        {
            String databaseName = databaseOutputConfig.getDatabaseName();
            dataSource = DataSourceForDataPipe.getDataSource(databaseName);
            loc_map = pipeContext.getParameters();
            loc_map.put("schema", DataSourceForDataPipe.getSchema(databaseName));
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        
        updateSql = DataPipeUtils.replaceParameters(
                databaseOutputConfig.getUpdateSql(), loc_map);
        log.info(updateSql);
        flushRows = (databaseOutputConfig.getFlushRows() > 0
                ? databaseOutputConfig.getFlushRows() : 1);
        buffer = new ArrayList<Map<String, Object>>(
            databaseOutputConfig.getFlushRows());
        rowIndex = 0;
        String breakpointCommit=CommonAPI.breakPointMap.get(CommonAPI.BREAKPOINT_COMMIT);
        if (breakpointCommit != null)
		{
			count += Integer.parseInt(breakpointCommit.split(":")[1]);			
		}
    }

    public synchronized void putRowData(Map<String, Object> rowData)
        throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'putRowData' called. ] ");
        rowIndex++;

        buffer.add(rowData);
        rowData.put("docCnt", pipeContext.getAttribute("docCnt")==null?"0":pipeContext.getAttribute("docCnt").toString());
        if (buffer.size() >= flushRows)
        {        	
            writeData();  
            count+=flushRows;            
            CommonAPI.breakPointMap.put(CommonAPI.BREAKPOINT_COMMIT, CommonAPI.BREAKPOINT_COMMIT+":"+String.valueOf(count));     
        }
    }

    public void writeData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'writeData' called. ] ");
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement(updateSql);
            for (Map<String, Object> rowData : buffer)
            {
            	if(databaseOutputConfig.getSqlParameters().getSqlParameters()!=null)
            	{
	                for (SqlParameter sqlParameter : databaseOutputConfig.getSqlParameters().getSqlParameters())
	                {
		                    pstmt.setString(sqlParameter.getIndex(),
		                       (String) rowData.get(sqlParameter.getField()));
	                }
            	}
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e)
        {
        	log.error(e.getNextException());
            throw new DataPipeException(e);
        } finally
        {
            DataPipeUtils.commit(conn);
            DataPipeUtils.close(conn, pstmt, null);
            buffer.clear();
        }

    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        if (!buffer.isEmpty()) {
        	count+=buffer.size();
        	writeData();
        	CommonAPI.breakPointMap.put(CommonAPI.BREAKPOINT_COMMIT, CommonAPI.BREAKPOINT_COMMIT+":"+String.valueOf(count));  
        }
    }

    public OutputConfig getConfig()
    {
        return config;
    }

    public FieldDefinition getFieldDefinition()
    {
        return databaseOutputConfig.getFieldDefinition();
    }

    public DatabaseOutputConfig getDatabaseOutputConfig()
    {
        return databaseOutputConfig;
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public String getUpdateSql()
    {
        return updateSql;
    }

    public int getFlushRows()
    {
        return flushRows;
    }
}
