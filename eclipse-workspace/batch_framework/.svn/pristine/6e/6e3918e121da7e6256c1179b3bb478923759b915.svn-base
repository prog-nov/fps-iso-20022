package com.forms.datapipe.input;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Input;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.DatabaseInputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.persistence.DataSourceForDataPipe;

/**
 * Database Input
 * 
 * @author cxl
 * 
 */
public class DatabaseInput
    implements Input
{

    private static Log log = LogFactory.getLog(DatabaseInput.class);
    
    private  BatchLogger batchLog=null;

    private DatabaseInputConfig databaseInputConfig;

    private InputConfig config;

    private String querySql;

    private Connection conn = null;

    private PreparedStatement pstmt = null;

    private ResultSet rs = null;

    private int rowIndex;
    
    private DataSource dataSource;
    
    private String jobId;
    
    private String actionName;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        DataPipeUtils.close(conn, pstmt, rs);
    }

    public InputConfig getConfig()
    {
        return config;
    }

    public Map<String, Object> getRowData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");
        Map<String, Object> fields = new HashMap<String, Object>();
        try
        {
        	if (conn == null)
            {
            	conn = dataSource.getConnection();
                pstmt = conn.prepareStatement(querySql);
                batchLog.info(querySql);
                rs = pstmt.executeQuery();
            }
            if (rs.next())
            {
                String value = null;
                for (Field field : databaseInputConfig.getFieldDefinition().getFields())
                {
                    value = rs.getString(field.getName());
                    fields.put(field.getName(), value);
                }
            } else
            {
            	close();
                return null; // finish
            }
        } catch (SQLException e)
        {
            throw new DataPipeException(e);
        }
        rowIndex++;
        return fields;
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");
        this.config = config;
        databaseInputConfig = DataPipeConfigFactory.getDatabaseInputConfig(config.getConfigFile());
        
        Map<String, String> loc_map = null;
        try
        {
	        String databaseName = databaseInputConfig.getDatabaseName();
	        dataSource = DataSourceForDataPipe.getDataSource(databaseName);
	        loc_map = pipeContext.getParameters();
	        loc_map.put("schema", DataSourceForDataPipe.getSchema(databaseName));
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        
        
        querySql = DataPipeUtils.replaceParameters(
            databaseInputConfig.getQuerySql(), loc_map);
        jobId=pipeContext.getParameters().get("jobId");
        actionName=pipeContext.getParameters().get("actionName");
        batchLog=BatchLogger.getLogger(jobId,
        		actionName, this.getClass().toString());
        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        throw new DataPipeException(
            " [ DatabaseInput do not have HeaderData! ] ");
    }

    public FieldDefinition getFieldDefinition()
    {
        return databaseInputConfig.getFieldDefinition();
    }

    public DatabaseInputConfig getDatabaseInputConfig()
    {
        return databaseInputConfig;
    }

    public String getQuerySql()
    {
        return querySql;
    }

    public Map<String, String> getFooterData() throws DataPipeException
    {
        return null;
    }

}
