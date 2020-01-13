package com.forms.datapipe.input;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Input;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

public class MultiFileInput
    implements Input
{

    private static Log log = LogFactory.getLog(MultiFileInput.class);

    private InputConfig config;

    private PipeContext pipeContext;

    private MultiFileInputSupport input;

    private FieldDefinition fieldDefinition;

    private String fileName;

    private int rowIndex;

    private int fileIndex;
    
    private String seqPattern;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public MultiFileInput(MultiFileInputSupport input)
    {
        this.input = input;
        this.fieldDefinition = input.getFieldDefinition();
        this.fileName = input.getFileName();
        this.seqPattern = input.getSeqPattern();
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        this.pipeContext = pipeContext;
        this.seqPattern = DataPipeUtils.replaceParameters(this.seqPattern, pipeContext.getParameters());
        this.fileIndex = Integer.valueOf(this.seqPattern);
        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public Map<String, Object> getRowData() throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");

        if (input == null)
        {
            input = (MultiFileInputSupport)DataPipeUtils.newInstance(config.getClazz());
            input.setFileName(getNextFileName());
            input.init(config, pipeContext);
        }

        Map<String, Object> ret = input.getRowData();
        if (ret == null) return doNextFile();
        rowIndex++;

        PerformanceMonitor.endRecord(this);
        return ret;
    }

    private String getNextFileName() throws DataPipeException
    {
        String seqName = null;
        try
        {
            seqName = com.forms.datapipe.util.StringUtils.fill("Left",
                String.valueOf(fileIndex), "0", seqPattern.length());
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        String splitFileName = fileName.replaceAll(seqPattern,
            seqName);
        splitFileName = DataPipeUtils.replaceParameters(splitFileName,
            pipeContext.getParameters());
        return splitFileName;
    }

    private Map<String, Object> doNextFile() throws DataPipeException
    {
        fileIndex++;
        input.close();
        input = null;
        File file = new File(getNextFileName());
        if (file.exists())
        {
            return getRowData();
        } else
        {
            return null;
        }
    }

    public void close() throws DataPipeException
    {
        if (input != null)
        {
            input.close();
            input = null;
        }
    }

    public InputConfig getConfig()
    {
        return config;
    }

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public static boolean isConfigValid(MultiFileInputSupport input)
    {
        return (!StringUtils.isEmpty(input.getSeqPattern()));
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        return input.getHeaderData();
    }

    public Map<String, String> getFooterData() throws DataPipeException
    {
        return input.getFooterData();
    }

}
