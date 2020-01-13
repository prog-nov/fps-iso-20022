package com.forms.datapipe.output;

import java.io.File;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.ExcelOutputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * excel格式输出
 * 
 * @author cxl
 *
 */
public class ExcelOutput
    implements Output
{

    private static Log log = LogFactory.getLog(ExcelOutput.class);

    private OutputConfig config;

    private ExcelOutputConfig excelOutputConfig;

    private String fileName;

    private WritableWorkbook book;

    private WritableSheet sheet;
    
    private int currentRow = 0;

    private int rowIndex;

    private int columnIndex;
    
    public int getRowIndex()
    {
        return rowIndex;
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        if (book != null)
        {
            try
            {
                book.write();
                book.close();
            } catch (Exception e)
            {
                throw new DataPipeException(e);
            }
        }
        book = null;
    }

    public FieldDefinition getFieldDefinition()
    {
        return excelOutputConfig.getFieldDefinition();
    }

    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        excelOutputConfig = DataPipeConfigFactory.getExcelOutputConfig(config.getConfigFile());

        //for MultiFileInput, cannot use filename from config.
        if (fileName == null)
        {
            fileName = DataPipeUtils.replaceParameters(
                excelOutputConfig.getFileName(), pipeContext.getParameters());
        }
        currentRow = excelOutputConfig.getStartRow();
        columnIndex = excelOutputConfig.getStartColumn();
        try
        {
            File f = new File(fileName);
            book = Workbook.createWorkbook(f);
            sheet = book.createSheet(excelOutputConfig.getSheetName(), 1);
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }

        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public synchronized void putRowData(Map<String, Object> rowData)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'putRowData' called. ] ");
        rowIndex++;
        try
        {
            for (Field field : excelOutputConfig.getFieldDefinition().getFields())
            {
                if (!rowData.containsKey(field.getName()))
                    throw new DataPipeException(" [ Missing field '"
                        + field.getName() + "' in rowData! ] ");
                String value = (String) rowData.get(field.getName());
                sheet.addCell(new Label(columnIndex, currentRow, value));
                columnIndex++;
            }
        }
        catch (WriteException e)
        {
            throw new DataPipeException(e);
        }
        columnIndex = excelOutputConfig.getStartColumn();
        currentRow++;
        PerformanceMonitor.endRecord(this);

    }

    public OutputConfig getConfig()
    {
        return config;
    }

    public ExcelOutputConfig getExcelOutputConfig()
    {
        return excelOutputConfig;
    }

    public String getFileName()
    {
        return fileName;
    }

}
