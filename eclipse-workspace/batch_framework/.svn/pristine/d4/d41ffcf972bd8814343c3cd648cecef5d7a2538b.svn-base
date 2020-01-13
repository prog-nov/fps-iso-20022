package com.forms.datapipe.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.framework.util.StringUtil;
import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.FixedFileOutput4MultiConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.output.header.FileOutputFooterHandler;
import com.forms.datapipe.output.header.FileOutputHeaderHandler;
import com.forms.datapipe.util.ByteUtils;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * fixed file output
 * 
 * @author cxl
 * 
 */
public class FixedFileOutput4Multi
    implements Output
{

    private static Log log = LogFactory.getLog(FixedFileOutput4Multi.class);

    private OutputConfig config;

    private FixedFileOutput4MultiConfig fixedFileOutput4MultiConfig;

    private String fileName;

    private FileOutputStream fos;

    private byte[] buffer;
    
    private byte[] controlBuff;

    private int index = 0; // row index

    private int lineBytes = 0;
    
    private int detailBytes = 0;

    private String lineFeed;

    private PipeContext pipeContext;
    
    private FieldDefinition fieldDefinition;
    
    private File file;

    private int rowIndex;
    
    private int fieldIndex = 0;     // index of buffer
    
    private int controlIndex = 0;
    
    private static Charset ibm937 = Charset.forName("ibm937");
    
    private boolean isibm937;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void init(OutputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.config = config;
        this.pipeContext = pipeContext;
        fixedFileOutput4MultiConfig = DataPipeConfigFactory.getFixedFileOutput4MultiConfig(config.getConfigFile());
        fieldDefinition = fixedFileOutput4MultiConfig.getFieldDefinition();
        fileName = DataPipeUtils.replaceParameters(
        		fixedFileOutput4MultiConfig.getFileName(), pipeContext.getParameters());
        lineFeed = fixedFileOutput4MultiConfig.getLineFeed();
        if ("none".equals(lineFeed) || lineFeed == null || "".equals(lineFeed))
        {
            lineFeed = "";
        }
        else if ("\\n".equals(lineFeed))
        {
            lineFeed = "\n";
        }
        else if ("\\r".equals(lineFeed))
        {
            lineFeed = "\r";
        }
        else if ("\\r\\n".equals(lineFeed))
        {
            lineFeed = "\r\n";
        }
        else
        {
            throw new DataPipeException(" [ Invalid lineFeed '" + lineFeed
                + "'! ] ");
        }

        // line bytes
        for (Field field : fieldDefinition.getFields())
        {
            lineBytes += field.getBytes();
            
            if (!"Y".equalsIgnoreCase(field.getControlFlag()))
            	detailBytes += field.getBytes();
        }
        lineBytes += lineFeed.length();
        try
        {
            if (!StringUtils.isEmpty(fixedFileOutput4MultiConfig.getHeaderHandleClass()))
            {
                file = new File(fileName + ".tmp0");
            }
            else
            {
                file = new File(fileName + ".tmp1");
            }
            fos = new FileOutputStream(file);
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }

        if (fixedFileOutput4MultiConfig.getFlushRows() > 0)
        {
            buffer = new byte[fixedFileOutput4MultiConfig.getFlushRows() * lineBytes];
            controlBuff = new byte[detailBytes];
        }
        else
        {
            buffer = new byte[lineBytes];
            controlBuff = new byte[detailBytes];
        }
        rowIndex = 0;
        Charset c = Charset.forName(fixedFileOutput4MultiConfig.getEncoding());
        isibm937=(ibm937.compareTo(c) == 0);
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public synchronized void putRowData(Map<String, Object> rowData)
        throws DataPipeException
    {
        
        if (log.isDebugEnabled())
            log.debug(" [ method 'putRowData' called. ] ");
        rowIndex++;

        if (rowIndex != 1) 
        {
            System.arraycopy(lineFeed.getBytes(), 0, buffer, fieldIndex,
                lineFeed.length());
            fieldIndex += lineFeed.length();
        }
        
        String controlFlag = rowData.get("controlFlag").toString();
        	
        for (Field field : fieldDefinition.getFields())
        {
            if (!rowData.containsKey(field.getName()))
                throw new DataPipeException(" [ Missing field '"
                    + field.getName() + "' in rowData! ] ");
            if (field.getControlFlag().equals(controlFlag) || "Y".equalsIgnoreCase(controlFlag))
            {
            	String value = (String) rowData.get(field.getName());
            	if ("controlFlag".equals(field.getName()))
            		value = "";

                byte[] fieldBytes = null;
                try
                {
                	if (field.getDataType().indexOf("PackedDecimal") > -1)
					{
						fieldBytes = ByteUtils.fromHexString(value);
					} else
					{
		            	if(isibm937)
		        		{
		            		fieldBytes = StringUtil.getBytes4IBM937(value);
		        		} else
		        		{
		        			fieldBytes = value.getBytes(fixedFileOutput4MultiConfig.getEncoding());
		        		}
					}
                }
                catch (UnsupportedEncodingException e)
                {
                    throw new DataPipeException(e);
                }
                if (fieldBytes.length != field.getBytes()
						&& field.getDataType().indexOf("PackedDecimal") == -1)
					throw new DataPipeException(" [ Field(name:"
							+ field.getName() + ";value:" + value
							+ ") length '" + fieldBytes.length
							+ "' is wrong, should be '" + field.getBytes()
							+ "'. ");
                
                if (controlFlag.equalsIgnoreCase(field.getControlFlag()))
                {
                	System.arraycopy(fieldBytes, 0, buffer, fieldIndex,
                			fieldBytes.length);
                	fieldIndex += fieldBytes.length;
                }
            	
            	if ("Y".equalsIgnoreCase(controlFlag) && !"Y".equalsIgnoreCase(field.getControlFlag()) )
            	{
            		System.arraycopy(fieldBytes, 0, controlBuff, controlIndex,
        	                fieldBytes.length);
            		
            		controlIndex += fieldBytes.length;
            	}
            }
        }
        if ("Y".equalsIgnoreCase(controlFlag))
        {
        	System.arraycopy(lineFeed.getBytes(), 0, buffer, fieldIndex,
                    lineFeed.length());
        	System.arraycopy(controlBuff, 0, buffer, fieldIndex + lineFeed.length(), 
        			detailBytes);
        	fieldIndex += controlBuff.length + lineFeed.length();
        	rowIndex++;
        	index++;
        	controlBuff = new byte[detailBytes];
        	controlIndex = 0;
        }
        
        index++;
        if (index >= fixedFileOutput4MultiConfig.getFlushRows())
        {
            writeData();
        }
    }

    private void writeData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'writeData' called. ] ");
        try
        {
            fos.write(buffer, 0, fieldIndex);
        }
        catch (IOException e)
        {
            throw new DataPipeException(e);
        }
        finally
        {
            index = 0;
            fieldIndex = 0;
            controlIndex = 0;
        }
    }
    
    private void writeFooter() throws DataPipeException
    {
        // handle header
        if (StringUtils.isEmpty(fixedFileOutput4MultiConfig.getFooterHandleClass()))
            return;
        FileOutputFooterHandler footerHandler = DataPipeUtils.newInstance(fixedFileOutput4MultiConfig.getFooterHandleClass());
        String footerData = footerHandler.handle(this, pipeContext);
        try
        {
            if (footerData == null || "".equals(footerData))
                return;
            byte[] footByte=footerData.getBytes(fixedFileOutput4MultiConfig.getEncoding());
            byte[] footBuffer=new byte[footByte.length+lineFeed.length()];            
            System.arraycopy(lineFeed.getBytes(), 0, footBuffer, 0,
                lineFeed.length());           
            System.arraycopy(footByte, 0, footBuffer, lineFeed.length(),
            		footByte.length);
            fos.write(footBuffer);
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        try
        {
            if (index > 0) writeData();
            writeFooter();
        }
        finally
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

    private void writeHeader() throws DataPipeException
    {
        // handle header
        if (StringUtils.isEmpty(fixedFileOutput4MultiConfig.getHeaderHandleClass()))
            return;

        FileOutputHeaderHandler headerHandler = DataPipeUtils.newInstance(fixedFileOutput4MultiConfig.getHeaderHandleClass());
        String headerData = headerHandler.handle(this, pipeContext);

        FileInputStream fis = null;
        FileOutputStream fos = null;
        File tmp = null;
        try
        {
        	tmp = new File(fileName + ".tmp1");
            fos = new FileOutputStream(tmp);
            fis = new FileInputStream(file);
            byte[] headBuffer=null;
            byte[] headByte=headerData.getBytes(fixedFileOutput4MultiConfig.getEncoding());
            if(rowIndex!=0){
            	headBuffer=new byte[headByte.length+lineFeed.length()];
            }else{
            	headBuffer=new byte[headByte.length];
            }
            System.arraycopy(headByte, 0, headBuffer, 0,
            		headByte.length);
            if(rowIndex!=0){
            	System.arraycopy(lineFeed.getBytes(), 0, headBuffer, headByte.length,
                        lineFeed.length());	
            }
            fos.write(headBuffer);
            int i = 0;
            while ((i = fis.read(buffer)) > -1)
            {
                fos.write(buffer, 0, i);
            }
        }
        catch (Exception e)
        {
            throw new DataPipeException(e);
        }
        finally
        {
        	DataPipeUtils.close(fis);
        	DataPipeUtils.close(fos);
            file.delete();
            file = tmp;
        }
    }

    public OutputConfig getConfig()
    {
        return config;
    }

    public FieldDefinition getFieldDefinition()
    {
        return this.fieldDefinition;
    }

    public String getFileName()
    {
        return fileName;
    }

    public int getLineBytes()
    {
        return lineBytes;
    }

    public String getLineFeed()
    {
        return lineFeed;
    }

    public String getSeqPattern()
    {
        return fixedFileOutput4MultiConfig.getSeqPattern();
    }

    public int getSeqStartAt()
    {
        return fixedFileOutput4MultiConfig.getSeqStartAt();
    }

    public int getSplitRows()
    {
        return fixedFileOutput4MultiConfig.getSplitRows();
    }
}
