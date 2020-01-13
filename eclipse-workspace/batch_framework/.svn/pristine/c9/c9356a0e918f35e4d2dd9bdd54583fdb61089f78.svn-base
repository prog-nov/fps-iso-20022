package com.forms.datapipe.input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Input;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.CsvFileInputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;
import com.forms.datapipe.input.header.FileInputHeaderHandler;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * 分隔符格式文件输入
 * 
 * @author cxl
 * 
 */
public class CsvFileInput
    implements Input, MultiFileInputSupport
{
    private static Log log = LogFactory.getLog(CsvFileInput.class);

    private InputConfig config;

    private CsvFileInputConfig csvFileInputConfig;

    private Map<String, String> headerData;
    
    private Map<String, String> footerData;

    private String fileName;

    private BufferedReader br;
    
    private InputStreamReader isr;
    
    private FileInputStream fis;

    private String delimiter;

    private String enclosure;

    private int rowIndex;
    
    private int footerLines;
    
    private PipeContext pipeContext;
    
    private List<String> footerBuffer;

    public int getRowIndex()
    {
        return rowIndex;
    }

    private void dealFooterData(String rowValue) throws DataPipeException
    {
        if (StringUtils.isEmpty(csvFileInputConfig.getFooterHandleClass()))
            return;
        FileInputFooterHandler footerHandler = (FileInputFooterHandler)DataPipeUtils.newInstance(csvFileInputConfig.getFooterHandleClass());
        // 处理文件尾部数据，footerBuffer中的rowValue均为尾部数据
        StringBuffer data = new StringBuffer();
        data.append(rowValue + csvFileInputConfig.getLineFeed());
        for (String lineInfo : footerBuffer)
        {
            data.append(lineInfo + csvFileInputConfig.getLineFeed());
        }
        footerData = footerHandler.handle(this, pipeContext, data.toString());
    }
    
    public Map<String, Object> getRowData() throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");
        String rowValue;
        try
        {
            if (footerBuffer.isEmpty())
            {
                rowValue = br.readLine();
                // Ԥ预读配置中footlines行数的数据，如果没有尾部数据，那footlines为0，循环不会执行
                for (int i = 0; i < footerLines; i++)
                {
                    String s = br.readLine();
                    if (s == null)
                    {
                        // 处理尾部数据
                        dealFooterData(rowValue);
                        return null;
                    }
                    footerBuffer.add(s);
                }
            }
            else
            {
                rowValue = footerBuffer.remove(0);
                String s = br.readLine();
                if (s == null)
                {
                    // 处理尾部数据
                    dealFooterData(rowValue);
                    return null;
                }
                footerBuffer.add(s);
            }
            if (rowValue == null)
            {
                return null; //已到达文件尾
            }
            pipeContext.setAttribute("rowValue" + String.valueOf(rowIndex + 1), rowValue);
        } catch (IOException e)
        {
            throw new DataPipeException(e);
        }
        String[] rowData = splitString(rowValue, delimiter);
        if (rowData.length != csvFileInputConfig.getFieldDefinition().getFields().size())
        {
            throw new DataPipeException("Invalid data input!");
        }
        Map<String, Object> fields = new HashMap<String, Object>();
        for (int i = 0; i < rowData.length; i++)
        {
            String fieldValue = rowData[i];
            Field field = csvFileInputConfig.getFieldDefinition().getFields().get(
                i);
            if (!fieldValue.startsWith(enclosure)
                || !fieldValue.endsWith(enclosure))
            {
                throw new DataPipeException(" [ Field(name:" + field.getName()
                    + ";value:" + fieldValue + ") should be in two "
                    + enclosure);
            }
            fields.put(field.getName(), fieldValue.replaceAll(enclosure, ""));
        }
        rowIndex++;
        PerformanceMonitor.endRecord(this);

        return fields;
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        csvFileInputConfig = DataPipeConfigFactory.getCsvFileInputConfig(config.getConfigFile());
        footerLines = csvFileInputConfig.getFooterLines();
        this.pipeContext = pipeContext;
        footerBuffer = new ArrayList<String>();
        //for MultiFileInput, cannot use filename from config.
        if (fileName == null)
        {
            fileName = DataPipeUtils.replaceParameters(
                csvFileInputConfig.getFileName(), pipeContext.getParameters());
        }
        delimiter = csvFileInputConfig.getDelimiter();
        enclosure = csvFileInputConfig.getEnclosure();

        if (delimiter == null || "".equals(delimiter))
        {
            throw new DataPipeException(
                "Invalid delimiter, delimiter can not be none!");
        }
        if ("\\t".equals(delimiter))
        {
            delimiter = "\t";
        }
        if (enclosure == null)
        {
            enclosure = "";
        }
        try
        {
            fis = new FileInputStream(fileName);
            isr = new InputStreamReader(fis, csvFileInputConfig.getEncoding());
            br = new BufferedReader(isr);

            // handle header
            if (csvFileInputConfig.getHeaderLines() > 0)
            {
                if (!StringUtils.isEmpty(csvFileInputConfig.getHeaderHandleClass()))
                {
                    FileInputHeaderHandler headerHandler = (FileInputHeaderHandler)DataPipeUtils.newInstance(csvFileInputConfig.getHeaderHandleClass());
                    StringBuffer header = new StringBuffer();
                    for (int i = 0; i < csvFileInputConfig.getHeaderLines(); i++)
                    {
                        header.append(br.readLine() + csvFileInputConfig.getLineFeed());
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
        return csvFileInputConfig.getFieldDefinition();
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        return headerData;
    }

    /**
     * 按指定字符分隔字符串
     * 
     * @param value
     *            for split
     * @param mark
     *            split mark
     * @return split String array
     */
    private String[] splitString(String value, String mark)
    {
        //字符串不能为空
        if (value == null)
        {
            return null;
        }
        String temp = value;
        int pos = temp.indexOf(mark);
        int count = 1;
        //����ָ����Ŀ
        while (pos != -1)
        {
            count++;
            temp = temp.substring(pos + mark.length());
            pos = temp.indexOf(mark);
        }

        String result[] = new String[count];

        temp = value;
        pos = temp.indexOf(mark);
        count = 0;
        //�ָ��ַ�
        while (pos != -1)
        {
            result[count] = temp.substring(0, pos);
            count++;
            temp = temp.substring(pos + mark.length());
            pos = temp.indexOf(mark);
        }
        result[count] = temp.trim();

        return result;
    }

    public CsvFileInputConfig getCsvFileInputConfig()
    {
        return csvFileInputConfig;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getDelimiter()
    {
        return delimiter;
    }

    public String getEnclosure()
    {
        return enclosure;
    }

    public String getSeqPattern()
    {
        return csvFileInputConfig.getSeqPattern();
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
