package com.forms.datapipe.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.CsvFileOutputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.output.header.FileOutputFooterHandler;
import com.forms.datapipe.output.header.FileOutputHeaderHandler;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * 分隔符格式文件输出
 * 
 * @author cxl
 * 
 */
public class CsvFileOutput
    implements Output, FileSplitOutputSupport
{

    private static Log log = LogFactory.getLog(CsvFileOutput.class);

    private OutputConfig config;

    private CsvFileOutputConfig csvFileOutputConfig;

    private String fileName;

    private FileOutputStream fos;

    private byte[] buffer;

    private int index = 0;

    private String lineFeed;

    private String delimiter;

    private String enclosure;

    private PipeContext pipeContext;

    private File file;

    private int rowIndex;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        try
        {
            if (index > 0) writeData();
            writeFooter();
        } finally
        {
        	DataPipeUtils.close(fos);
        }

        if (log.isDebugEnabled()) log.debug(" [ write header. ] ");
        if (file != null && file.exists()) 
    	{
        	writeHeader();
        	File dest = new File(fileName);
        	if (dest.exists()) dest.delete();
        	file.renameTo(dest);
    	}

        buffer = null;
    }
    
    private void writeFooter() throws DataPipeException
    {
        // handle header
        if (StringUtils.isEmpty(csvFileOutputConfig.getFooterHandleClass()))
            return;
        FileOutputFooterHandler footerHandler = (FileOutputFooterHandler)DataPipeUtils.newInstance(csvFileOutputConfig.getFooterHandleClass());
        String footerData = footerHandler.handle(this, pipeContext);
        try
        {
            if (footerData == null || "".equals(footerData))
                return;
            // 因为最后一行没有换行符，则添加尾部信息时需要先增加换行符
            fos.write((lineFeed + footerData).getBytes(csvFileOutputConfig.getEncoding()));
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }
    }

    private void writeHeader() throws DataPipeException
    {
        // handle header
        if (StringUtils.isEmpty(csvFileOutputConfig.getHeaderHandleClass()))
            return;

        FileOutputHeaderHandler headerHandler = (FileOutputHeaderHandler)DataPipeUtils.newInstance(csvFileOutputConfig.getHeaderHandleClass());
        String headerData = headerHandler.handle(this, pipeContext);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        File tmp = null;
        try
        {
        	tmp = new File(fileName + ".tmp1");
            fos = new FileOutputStream(tmp);
            fis = new FileInputStream(file);
            fos.write(headerData.getBytes(csvFileOutputConfig.getEncoding()));
            int i = 0;
            while ((i = fis.read(buffer)) > -1)
            {
                fos.write(buffer, 0, i);
            }
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        } finally
        {
        	DataPipeUtils.close(fis);
        	DataPipeUtils.close(fos);
            file.delete();
            file = tmp;
        }
    }

    public FieldDefinition getFieldDefinition()
    {
        return csvFileOutputConfig.getFieldDefinition();
    }

    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        this.pipeContext = pipeContext;
        csvFileOutputConfig = DataPipeConfigFactory.getCsvFileOutputConfig(config.getConfigFile());

        fileName = DataPipeUtils.replaceParameters(
            csvFileOutputConfig.getFileName(), pipeContext.getParameters());
        lineFeed = csvFileOutputConfig.getLineFeed();
        delimiter = csvFileOutputConfig.getDelimiter();
        enclosure = csvFileOutputConfig.getEnclosure();
        if ("\\n".equals(lineFeed))
        {
            lineFeed = "\n";
        } else if ("\\r".equals(lineFeed))
        {
            lineFeed = "\r";
        } else if ("\\r\\n".equals(lineFeed))
        {
            lineFeed = "\r\n";
        } else
        {
            throw new DataPipeException(" [ Invalid lineFeed '" + lineFeed
                + "'! ] ");
        }

        if (delimiter == null || "".equals(delimiter))
        {
            throw new DataPipeException(
                "Invalid delimiter, delimiter can not be none!");
        }
        if (enclosure == null)
        {
            enclosure = "";
        }
        try
        {
            if (!StringUtils.isEmpty(csvFileOutputConfig.getHeaderHandleClass()))
            {
                file = new File(fileName + ".tmp0");
            } else
            {
                file = new File(fileName + ".tmp1");
            }
            fos = new FileOutputStream(file);
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }

        buffer = new byte[csvFileOutputConfig.getBufferSize()];
        rowIndex = 0;
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public synchronized void putRowData(Map<String, Object> rowData)
        throws DataPipeException
    {
        PerformanceMonitor.startRecord(this);
        if (log.isDebugEnabled())
            log.debug(" [ method 'putRowData' called. ] ");
        rowIndex++;

        StringBuffer rowValue = new StringBuffer();
        for (Field field : csvFileOutputConfig.getFieldDefinition().getFields())
        {
            if (!rowData.containsKey(field.getName()))
                throw new DataPipeException(" [ Missing field '"
                    + field.getName() + "' in rowData! ] ");

            String value = (String) rowData.get(field.getName());
            value = enclosure + value + enclosure + delimiter;
            rowValue.append(value);
        }
        rowValue.delete(rowValue.length() - delimiter.length(),
            rowValue.length());
        byte[] data = null;
        try
        {
            if (rowIndex != 1)
                data = (lineFeed + rowValue.toString()).getBytes(csvFileOutputConfig.getEncoding());
            else
                data = rowValue.toString().getBytes(csvFileOutputConfig.getEncoding());
        } catch (UnsupportedEncodingException e)
        {
            throw new DataPipeException(e);
        }
        if (buffer.length - index < data.length)
        {
            writeData();
        }
        System.arraycopy(data, 0, buffer, index, data.length);
        index += data.length;
        PerformanceMonitor.endRecord(this);

    }

    public OutputConfig getConfig()
    {
        return config;
    }

    private void writeData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'writeData' called. ] ");
        try
        {
            fos.write(buffer, 0, index);
        } catch (IOException e)
        {
            throw new DataPipeException(e);
        } finally
        {
            index = 0;
        }
    }

    public CsvFileOutputConfig getCsvFileOutputConfig()
    {
        return csvFileOutputConfig;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getLineFeed()
    {
        return lineFeed;
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
        return csvFileOutputConfig.getSeqPattern();
    }

    public int getSeqStartAt()
    {
        return csvFileOutputConfig.getSeqStartAt();
    }

    public int getSplitRows()
    {
        return csvFileOutputConfig.getSplitRows();
    }

}
