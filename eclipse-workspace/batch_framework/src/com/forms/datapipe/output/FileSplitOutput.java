package com.forms.datapipe.output;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * 多文件输入
 * 
 * @author cxl
 * 
 */
public class FileSplitOutput
    implements Output
{

    private static Log log = LogFactory.getLog(FileSplitOutput.class);

    private OutputConfig config;

    private PipeContext pipeContext;

    private FileSplitOutputSupport output;

    private FieldDefinition fieldDefinition;

    private int rowIndex;

    private int fileIndex;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public FileSplitOutput(FileSplitOutputSupport output)
    {
        this.output = output;
        this.fieldDefinition = output.getFieldDefinition();
        this.fileIndex = output.getSeqStartAt();
    }

    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        this.pipeContext = pipeContext;

        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public void putRowData(Map<String, Object> rowData)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'putRowData' called. ] ");
        rowIndex++;

        if (output == null)
        {
            output = (FileSplitOutputSupport)DataPipeUtils.newInstance(config.getClazz());
            output.init(config, pipeContext);
        }

        output.putRowData(rowData);
        if (rowIndex % output.getSplitRows() == 0) closeCurrentFile();

        PerformanceMonitor.endRecord(this);
    }

    // 獲取分段文件名
    private String getSplitFileName() throws DataPipeException
    {
        String fileName = output.getFileName();
        if (fileName.indexOf(output.getSeqPattern()) == -1)
            throw new DataPipeException(" [ FileName '" + fileName
                + "' do not contain seq-pattern '" + output.getSeqPattern()
                + "'! ] ");

        String seqName = null;
        try
        {
            seqName = com.forms.datapipe.util.StringUtils.fill("Left",
                String.valueOf(fileIndex), "0", output.getSeqPattern().length());
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        String splitFileName = fileName.replaceAll(output.getSeqPattern(),
            seqName);
        splitFileName = DataPipeUtils.replaceParameters(splitFileName,
            pipeContext.getParameters());
        return splitFileName;
    }

    private void closeCurrentFile() throws DataPipeException
    {
        output.close();
        File file = new File(output.getFileName());
        if (file.exists())
        {
            file.renameTo(new File(getSplitFileName()));
        }
        output = null;
        fileIndex++;
    }

    public void close() throws DataPipeException
    {
        if (output != null) closeCurrentFile();
    }

    public OutputConfig getConfig()
    {
        return config;
    }

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public static boolean isConfigValid(FileSplitOutputSupport output)
    {
        return (output.getSplitRows() > 0 && output.getSeqStartAt() > -1 && !StringUtils.isEmpty(output.getSeqPattern()));
    }
}
