package com.forms.datapipe.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Input;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.input.LineFileInputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;
import com.forms.datapipe.input.header.FileInputHeaderHandler;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * fixed file input read by line
 * 
 * @author YY
 *
 */
public class LineFileInput implements Input
{

    private static Log log = LogFactory.getLog(LineFileInput.class);

    private InputConfig config;

    private LineFileInputConfig lineFileInputConfig;

    private Map<String, String> headerData;

    private Map<String, String> footerData;

    private String fileName;

    private BufferedReader br;
    
    private InputStreamReader isr;
    
    private FileInputStream fis;

    private int rowIndex;

    private int footerLines;
    
    private int lineBytes = 0;

    private PipeContext pipeContext;

    private List<String> footerBuffer;
    
    private FieldDefinition fieldDefinition;
    
    public int getRowIndex()
    {
        return rowIndex;
    }

    private void dealFooterData(String rowValue) throws DataPipeException
    {
        if (lineFileInputConfig.getFooterHandleClass() == null
				|| "".equals(lineFileInputConfig.getFooterHandleClass()))
            return;
        FileInputFooterHandler footerHandler = DataPipeUtils.newInstance(lineFileInputConfig.getFooterHandleClass());
        // handle footer
        StringBuffer data = new StringBuffer();
        data.append(rowValue + lineFileInputConfig.getLineFeed());
        for (String lineInfo : footerBuffer)
        {
            data.append(lineInfo + lineFileInputConfig.getLineFeed());
        }
        footerData = footerHandler.handle(this, pipeContext, data.toString());
    }

    public Map<String, Object> getRowData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");
        String rowValue;
        byte[] rowData = null;
        try
        {
            if (footerBuffer.isEmpty())
            {
                rowValue = br.readLine();
                //pre read the lines of footer
                for (int i = 0; i < footerLines; i++)
                {
                    String s = br.readLine();
                    if (s == null)
                    {
                        // handle footer
                        dealFooterData(rowValue);
                        return null;
                    }
                    footerBuffer.add(s);
                }
            } else
            {
                rowValue = footerBuffer.remove(0);
                String s = br.readLine();
                if (s == null)
                {
                    // handle footer
                    dealFooterData(rowValue);
                    return null;
                }
                footerBuffer.add(s);
            }
            if (rowValue == null)
            {
                return null; // finish
            }
            pipeContext.setAttribute("rowValue" + String.valueOf(rowIndex + 1), rowValue);
            rowData = rowValue.getBytes(lineFileInputConfig.getEncoding());
        } catch (IOException e)
        {
            throw new DataPipeException(e);
        }
        Map<String, Object> fields = new HashMap<String, Object>();
        if (rowData.length != lineBytes)
        {
            pipeContext.setAttribute("err", "The data length must be '" + lineBytes + "'!");
            rowIndex++;
            return fields;
        }
        int index = 0;
        for (Field field : fieldDefinition.getFields())
        {
            String value = null;
            try
            {
                value = new String(rowData, index, field.getBytes(), lineFileInputConfig.getEncoding());
            } catch (UnsupportedEncodingException e)
            {
                throw new DataPipeException(e);
            }
            fields.put(field.getName(), value);
            index += field.getBytes();
        }
        rowIndex++;

        return fields;
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        this.pipeContext = pipeContext;
        
        lineFileInputConfig = DataPipeConfigFactory.getLineFileInputConfig(config.getConfigFile());
        fieldDefinition = lineFileInputConfig.getFieldDefinition();
        
        footerLines = lineFileInputConfig.getFooterLines();
        footerBuffer = new ArrayList<String>();
        // for MultiFileInput, cannot use filename from config.
        if (fileName == null)
        {
            fileName = DataPipeUtils.replaceParameters(
                lineFileInputConfig.getFileName(), pipeContext.getParameters());
        }
        //line bytes
        for (Field field : fieldDefinition.getFields())
        {
            lineBytes += field.getBytes();
        }
        try
        {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis, lineFileInputConfig.getEncoding());
            br = new BufferedReader(isr);

            // handle header
            if (lineFileInputConfig.getHeaderLines() > 0)
            {
                if (lineFileInputConfig.getHeaderHandleClass() != null
						&& !"".equals(lineFileInputConfig
								.getHeaderHandleClass()))
                {
                    FileInputHeaderHandler headerHandler = DataPipeUtils.newInstance(lineFileInputConfig.getHeaderHandleClass());
                    StringBuffer header = new StringBuffer();
                    for (int i = 0; i < lineFileInputConfig.getHeaderLines(); i++)
                    {
                        header.append(br.readLine() + lineFileInputConfig.getLineFeed());
                    }
                    headerData = headerHandler.handle(this, pipeContext, header.toString());
                }
            }
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        DataPipeUtils.close(br);
        DataPipeUtils.close(isr);
        DataPipeUtils.close(fis);
    }

    public InputConfig getConfig()
    {
        return config;
    }

    public FieldDefinition getFieldDefinition()
    {
        return this.fieldDefinition;
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        return headerData;
    }

    public LineFileInputConfig getlineFileInputConfig()
    {
        return lineFileInputConfig;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getSeqPattern()
    {
        return lineFileInputConfig.getSeqPattern();
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Map<String, String> getFooterData() throws DataPipeException
    {
        return footerData;
    }
}
