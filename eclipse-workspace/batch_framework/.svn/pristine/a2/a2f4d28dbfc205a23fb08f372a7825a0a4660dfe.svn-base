package com.forms.datapipe.input;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Input;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.ExcelInputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * Excel Input
 * 
 * @author cxl
 * 
 */
public class ExcelInput
    implements Input
{

    private static Log log = LogFactory.getLog(ExcelInput.class);

    private InputConfig config;

    private ExcelInputConfig excelInputConfig;

    private Map<String, String> headerData;

    private String fileName;

    private Workbook book;

    private Sheet sheet;
    
    private String stopAtNull;

    private int rowIndex;

    private int columnIndex;

    private int totalNum; // 总行数

    private Map<String, String> footerData; // 尾数据

    public void close() throws DataPipeException
    {
        if (book != null) book.close();
        book = null;
    }

    public InputConfig getConfig()
    {
        return config;
    }

    public Map<String, Object> getRowData() throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");

        if (rowIndex == totalNum)
        {
            // 已到达文件尾
            return null;
        }
        Cell[] rowValue = sheet.getRow(rowIndex);
        Map<String, Object> fields = new HashMap<String, Object>();
        for (Field field : excelInputConfig.getFieldDefinition().getFields())
        {
            if (rowValue[columnIndex] == null)
            {
                // 读到空单元格则当前行数据读取完毕
                break;
            } else if ("BlankCell".equals(rowValue[columnIndex].getClass().getSimpleName())
                && "N".equals(stopAtNull))
            {
                // 读到BlankCell且遇空停止的配置为N，则给该字段赋值为空字符串
                fields.put(field.getName(), "");
                columnIndex++;
            } else if ("BlankCell".equals(rowValue[columnIndex].getClass().getSimpleName())
                && "Y".equals(stopAtNull))
            {
            	// 读到BlankCell且遇空停止的配置为Y，则不再继续读取下面的数据
                return null;
            } else
            {
                fields.put(field.getName(), rowValue[columnIndex].getContents());
                columnIndex++;
            }
        }
        // 行号加1，列号重置到起始位置
        rowIndex++;
        columnIndex = excelInputConfig.getStartColumn();
        PerformanceMonitor.endRecord(this);

        return fields;
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        excelInputConfig = DataPipeConfigFactory.getExcelInputConfig(config.getConfigFile());

        //for MultiFileInput, cannot use filename from config.
        if (fileName == null)
        {
            fileName = DataPipeUtils.replaceParameters(
                excelInputConfig.getFileName(), pipeContext.getParameters());
        }
        rowIndex = excelInputConfig.getStartRow();
        columnIndex = excelInputConfig.getStartColumn();
        stopAtNull = excelInputConfig.getStopAtNull();
        try
        {
            File f = new File(fileName);
            book = Workbook.getWorkbook(f);
            sheet = book.getSheet(excelInputConfig.getSheetName());
            totalNum = sheet.getRows();
            /*
            // handle header
            if (excelInputConfig.getHeaderBytes() > 0)
            {
                byte[] headerBytes = new byte[excelInputConfig.getHeaderBytes()];
                fis.read(headerBytes);

                if (!StringUtils.isEmpty(excelInputConfig.getHeaderHandleClass()))
                {
                    FileInputHeaderHandler headerHandler = ClassUtils.newInstance(excelInputConfig.getHeaderHandleClass());
                    headerData = headerHandler.handle(this, pipeContext,
                        new String(headerBytes,
                            excelInputConfig.getEncoding()));
                }
            }
             */
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }

        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        return headerData;
    }

    public FieldDefinition getFieldDefinition()
    {
        return excelInputConfig.getFieldDefinition();
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public Map<String, String> getFooterData() throws DataPipeException
    {
        return footerData;
    }

}
