package com.forms.datapipe.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.DataPipe;
import com.forms.datapipe.Input;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.InputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.input.FixedFileInputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;
import com.forms.datapipe.input.header.FileInputHeaderHandler;
import com.forms.datapipe.util.ByteUtils;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.framework.util.CharsetConversionUtils;

/**
 * for fixed file input
 * 
 * @author cxl
 * 
 */
public class FixedFileInput
    implements Input
{

    private static Log log = LogFactory.getLog(FixedFileInput.class);

    private InputConfig config;

    private FixedFileInputConfig fixedFileInputConfig;

    private Map<String, String> headerData;

    private String fileName;

    private int lineBytes = 0;

    private FileInputStream fis;

    private byte[] buffer;

    private int readNum = 0;

    private int index = 0;

    private String lineFeed;

    private int rowIndex;
    
    private Map<String, String> footerData;   // file footer
    
    private long footerDataIndex;             // start index of file footer
    
    private PipeContext pipeContext;
    
    private FieldDefinition fieldDefinition;
    
    private static Charset ibm937 = Charset.forName("ibm937");
    
    private boolean isibm937;

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void init(InputConfig config, PipeContext pipeContext)
        throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'init' called. ] ");

        this.pipeContext = pipeContext;
        this.config = config;
		this.fixedFileInputConfig = 
				DataPipeConfigFactory.getFixedFileInputConfig(config.getConfigFile());
		this.fieldDefinition = this.fixedFileInputConfig.getFieldDefinition();

        lineFeed = fixedFileInputConfig.getLineFeed();
        if ("none".equals(lineFeed) || lineFeed == null || "".equals(lineFeed))
        {
            lineFeed = "";
        } else if ("\\n".equals(lineFeed))
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
        // for MultiFileInput, cannot use filename from config.
        if (fileName == null)
        {
            fileName = DataPipeUtils.replaceParameters(
            		fixedFileInputConfig.getFileName(), pipeContext.getParameters());
        }
        //line bytes
        for (Field field : fieldDefinition.getFields())
        {
            lineBytes += field.getBytes();
        }
        lineBytes += lineFeed.length();
        //read foot
        File f = new File(fileName);
        try
		{
			if (fixedFileInputConfig.getFooterBytes() > 0)
			{
				fis = new FileInputStream(f);
				byte[] footerBytes = new byte[fixedFileInputConfig
						.getFooterBytes()];
				if (fixedFileInputConfig.getFooterHandleClass() != null
						&& !"".equals(fixedFileInputConfig
								.getFooterHandleClass()))
				{
					fis.skip(f.length() - fixedFileInputConfig.getFooterBytes());
					fis.read(footerBytes);
					FileInputFooterHandler footerHandler = DataPipeUtils
							.newInstance(fixedFileInputConfig
									.getFooterHandleClass());
					footerData = footerHandler.handle(this, pipeContext,
							new String(footerBytes, fixedFileInputConfig
									.getEncoding()));
				}
			}
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		} finally
		{
			if (fis != null)
			{
				try
				{
					fis.close();
				} catch (IOException e)
				{
					throw new DataPipeException(e);
				}
			}
		}
		//read head
        try
        {
            footerDataIndex = f.length()
					- fixedFileInputConfig.getHeaderBytes()
					- fixedFileInputConfig.getFooterBytes();
            fis = new FileInputStream(f);

            //handle header 
            if (fixedFileInputConfig.getHeaderBytes() > 0)
            {
                byte[] headerBytes = new byte[fixedFileInputConfig.getHeaderBytes()];
                fis.read(headerBytes);

                if (fixedFileInputConfig.getHeaderHandleClass() != null
						&& !"".equals(fixedFileInputConfig.getHeaderHandleClass()))
                {
                    FileInputHeaderHandler headerHandler = 
                    		DataPipeUtils.newInstance(fixedFileInputConfig.getHeaderHandleClass());
                    headerData = headerHandler.handle(this, pipeContext,
                        new String(headerBytes,
                            fixedFileInputConfig.getEncoding()));
                }
            }
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }

        if (fixedFileInputConfig.getPrereadRows() > 0)
        {
            buffer = new byte[fixedFileInputConfig.getPrereadRows() * lineBytes];
        } else
        {
            buffer = new byte[lineBytes];
        }
        rowIndex = 0;
        Charset c = Charset.forName(fixedFileInputConfig.getEncoding());
        isibm937=(ibm937.compareTo(c) == 0);
        if (log.isDebugEnabled()) log.debug(" [ success. ] ");
    }

    public Map<String, Object> getRowData() throws DataPipeException
    {
        if (log.isDebugEnabled())
            log.debug(" [ method 'getRowData' called. ] ");

        if (readNum == -1
            || (index >= readNum && index != 0 && readNum != buffer.length))
        {
            return null; //finish
        }

        if (readNum == 0 || index == readNum)
        {
            readNum = 0;
            index = 0;
            readData();
            return getRowData();
        }

        if (rowIndex * lineBytes == footerDataIndex)
        {
            if (fixedFileInputConfig.getFooterHandleClass() != null
					&& !"".equals(fixedFileInputConfig.getFooterHandleClass()))
            {
                FileInputFooterHandler footerHandler = DataPipeUtils.newInstance(fixedFileInputConfig.getFooterHandleClass());
                // handle footer
                try
                {
                    if (readNum - index == fixedFileInputConfig.getFooterBytes())
                    {
                        // for all the footer is read in buffer
                        footerData = footerHandler.handle(this, pipeContext, new String(buffer, index, fixedFileInputConfig.getFooterBytes(), fixedFileInputConfig.getEncoding()));
                    }
                    else
                    {
                        // else
                        readLeftData();
                        footerData = footerHandler.handle(this, pipeContext, new String(buffer, 0, readNum, fixedFileInputConfig.getEncoding()));
                    }
                }
                catch (UnsupportedEncodingException e)
                {
                    throw new DataPipeException(e);
                }
            }
            // finish
            return null;
        }
        try
		{
        	pipeContext.setAttribute(DataPipe.INDEX_KEY, String.valueOf(rowIndex + 1));
        	pipeContext.setAttribute("rowValue" + String.valueOf(rowIndex + 1), new String(buffer,fixedFileInputConfig.getEncoding()));
		} catch (UnsupportedEncodingException e)
		{
			throw new DataPipeException(e);
		}
        Map<String, Object> fields = new HashMap<String, Object>();
        byte[] packedDecimalByte = null;
        byte[] temp=null;
        for (Field field : fieldDefinition.getFields())
        {
            String value = null;
            try
            {
            	if (field.getDataType().indexOf("PackedDecimal") <= -1)
				{
            		if(isibm937)
            		{
            			temp = new byte[field.getBytes()];
                		System.arraycopy(buffer, index, temp, 0, field.getBytes());
                		value=CharsetConversionUtils.convertIBM937ToUnicode(temp);	
            		} else
            		{
            			value = new String(buffer, index, field.getBytes(),
							fixedFileInputConfig.getEncoding());
            		}
            		
				} else
				{
					packedDecimalByte = new byte[field.getBytes()];
					System.arraycopy(buffer, index, packedDecimalByte, 0, field
							.getBytes());
					value = ByteUtils.toHexString(packedDecimalByte);
				}    
            } catch (UnsupportedEncodingException e)
            {
                throw new DataPipeException(e);
            }
            fields.put(field.getName(), value);

            index += field.getBytes();
        }
        index += lineFeed.length();
        rowIndex++;
        
        return fields;
    }

    private void readData() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'readData' called. ] ");

        try
        {
            readNum = fis.read(buffer, 0, buffer.length);
        } catch (IOException e)
        {
            throw new DataPipeException(e);
        }
    }
    
    private void readLeftData() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'readLeftData' called. ] ");

        try
        {
            System.arraycopy(buffer, buffer.length - lineBytes, buffer, 0, lineBytes);
            readNum = fis.read(buffer, lineBytes, buffer.length);
            if (readNum > -1)
            {
                readNum += lineBytes;
            }
            else
            {
                throw new DataPipeException("[ Invalid footer data! ]");
            }
        } catch (IOException e)
        {
            throw new DataPipeException(e);
        }
    }

    public void close() throws DataPipeException
    {
        if (log.isDebugEnabled()) log.debug(" [ method 'close' called. ] ");
        DataPipeUtils.close(fis);
        buffer = null;
        readNum = 0;
        index = 0;
    }

    public InputConfig getConfig()
    {
        return config;
    }

    public Map<String, String> getHeaderData() throws DataPipeException
    {
        return headerData;
    }
    
    public Map<String, String> getFooterData() throws DataPipeException
    {
        return footerData;
    }

    public FieldDefinition getFieldDefinition()
    {
        return this.fieldDefinition;
    }

    public FixedFileInputConfig getFixedFileInputConfig()
    {
        return fixedFileInputConfig;
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
        return fixedFileInputConfig.getSeqPattern();
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

}
