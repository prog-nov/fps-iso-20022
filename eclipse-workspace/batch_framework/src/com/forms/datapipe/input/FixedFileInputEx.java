package com.forms.datapipe.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
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
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.input.FixedFileInputExConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;
import com.forms.datapipe.input.header.FileInputHeaderHandler;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;
import com.forms.datapipe.util.ByteUtils;

/**
 * 对应循环数据处理
 * 
 * @author YY
 * 
 */
public class FixedFileInputEx implements MultiFileInputSupport, Input
{

	private static Log log = LogFactory.getLog(FixedFileInputEx.class);

	private InputConfig config;

	private FixedFileInputExConfig fixedFileInputExConfig;

	private Map<String, String> headerData;

    private String fileName;

    private int lineBytes = 0;

    private FileInputStream fis;

    private byte[] buffer;

    private int readNum = 0;

    private int index = 0;

    private String lineFeed;

    private int rowIndex;
    
    private Map<String, String> footerData;   // 文件尾信息
    
    private long footerDataIndex;             // 尾信息起始位置
    
    private PipeContext pipeContext;
    
    private FieldDefinition fieldDefinition;
    
    private FieldDefinitionHandler configHandler;
    
    private String encoding;
    
    private Map<String, Integer> loopIndexes = new HashMap<String, Integer>();

	public int getRowIndex()
	{
		return rowIndex;
	}

	public Map<String, Object> getRowData() throws DataPipeException
	{
		PerformanceMonitor.startRecord(this);
		if (log.isDebugEnabled())
			log.debug(" [ method 'getRowData' called. ] ");
		if (readNum == -1
				|| (index >= readNum && index != 0 && readNum != buffer.length))
		{
			PerformanceMonitor.endRecord(this);
			return null; // 到达文件结尾
		}

		if (readNum == 0 || index == readNum)
		{
			readNum = 0;
			index = 0;
			readData();
			PerformanceMonitor.endRecord(this);
			return getRowData();
		}

		if (rowIndex * lineBytes == footerDataIndex)
		{
			if (!StringUtils.isEmpty(fixedFileInputExConfig
					.getFooterHandleClass()))
			{
				FileInputFooterHandler footerHandler = (FileInputFooterHandler)DataPipeUtils
						.newInstance(fixedFileInputExConfig
								.getFooterHandleClass());
				// 文件明细内容已经读取完毕，读取文件尾部信息
				try
				{
					if (readNum - index == fixedFileInputExConfig
							.getFooterBytes())
					{
						// 文件尾部信息已经在buffer中完全读取到了
						footerData = footerHandler.handle(this, pipeContext,
								new String(buffer, index, fixedFileInputExConfig
										.getFooterBytes(), fixedFileInputExConfig
										.getEncoding()));
					} else
					{
						// 还有一部分尾部信息在没读完的文件中
						readLeftData();
						footerData = footerHandler.handle(this, pipeContext,
								new String(buffer, 0, readNum,
										fixedFileInputExConfig.getEncoding()));
					}
				} catch (UnsupportedEncodingException e)
				{
					throw new DataPipeException(e);
				}
			}
			// 到达文件尾
			return null;
		}
		Map<String, Object> fields = new HashMap<String, Object>();
		// 不再判断当前所读的行数据长度与预设长度是否相等
		byte[] rowData = new byte[lineBytes - lineFeed.length()];
		System.arraycopy(buffer, index, rowData, 0, lineBytes - lineFeed.length());
		try 
		{
			pipeContext.setAttribute("rowValue" + String.valueOf(rowIndex + 1),
					new String(rowData, fixedFileInputExConfig.getEncoding()));
		} catch (Exception e) 
		{
			throw new DataPipeException(e);
		}
		List<FieldData> datas = getFieldValue(fieldDefinition.getFields(),
				rowData, 0, 1, null, null);
		fields.put("loopFileData", datas);
		
		index += lineBytes;
        rowIndex++;
		PerformanceMonitor.endRecord(this);

		return fields;
	}

	public void init(InputConfig config, PipeContext pipeContext)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'init' called. ] ");

		this.config = config;
		this.pipeContext = pipeContext;
		fixedFileInputExConfig = DataPipeConfigFactory
				.getFixedFileInputExConfig(config.getConfigFile());
		this.fieldDefinition = FieldDefinition.clone(this.fixedFileInputExConfig
				.getFieldDefinition());
		this.configHandler = new FieldDefinitionHandler(fixedFileInputExConfig
				.getHandlers());
		this.configHandler.handle(this.fieldDefinition, pipeContext);
		
		// for MultiFileInput, cannot use filename from config.
		if (fileName == null)
		{
			fileName = DataPipeUtils.replaceParameters(fixedFileInputExConfig
					.getFileName(), pipeContext.getParameters());
		}
		lineFeed = fixedFileInputExConfig.getLineFeed();
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
		// line bytes
        for (Field field : fieldDefinition.getFields())
        {
            lineBytes += field.getBytes();
        }
        lineBytes += lineFeed.length();
        encoding = fixedFileInputExConfig.getEncoding();
		try
		{
			File f = new File(fileName);
            footerDataIndex = f.length()
					- fixedFileInputExConfig.getHeaderBytes()
					- fixedFileInputExConfig.getFooterBytes();
            fis = new FileInputStream(f);

            //handle header 
            if (fixedFileInputExConfig.getHeaderBytes() > 0)
            {
                byte[] headerBytes = new byte[fixedFileInputExConfig.getHeaderBytes()];
                fis.read(headerBytes);

                if (!StringUtils.isEmpty(fixedFileInputExConfig.getHeaderHandleClass()))
                {
                    FileInputHeaderHandler headerHandler = 
                    		(FileInputHeaderHandler)DataPipeUtils.newInstance(fixedFileInputExConfig.getHeaderHandleClass());
                    headerData = headerHandler.handle(this, pipeContext,
                        new String(headerBytes,
                        		fixedFileInputExConfig.getEncoding()));
                }
            }
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		if (fixedFileInputExConfig.getPrereadRows() > 0)
        {
            buffer = new byte[fixedFileInputExConfig.getPrereadRows() * lineBytes];
        } else
        {
            buffer = new byte[lineBytes];
        }
        rowIndex = 0;
		if (log.isDebugEnabled())
			log.debug(" [ success. ] ");
	}

	public void close() throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'close' called. ] ");
		DataPipeUtils.close(fis);
		buffer = null;
        readNum = 0;
        index = 0;
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

	public FixedFileInputExConfig getfixedFileInputExConfig()
	{
		return fixedFileInputExConfig;
	}

	public String getFileName()
	{
		return fileName;
	}

	public String getSeqPattern()
	{
		return fixedFileInputExConfig.getSeqPattern();
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public Map<String, String> getFooterData() throws DataPipeException
	{
		return footerData;
	}

	private List<FieldData> getFieldValue(List<Field> fields, byte[] rowData,
			int index, int count, FieldData parent, String loopName)
			throws DataPipeException
	{
		List<FieldData> datas = new ArrayList<FieldData>();
		try
		{
			for (int i = 0; i < count; i++)
			{
				FieldData current = new FieldData();
				current.setParent(parent);
				if (loopName != null)
				{
					FieldData loopIndex = new FieldData();
					loopIndex.setParent(parent);
					loopIndex.setName(loopName + "_loopIndex");
					loopIndex.setDataType("NumberType");
					Integer lastIndex = loopIndexes.get(loopName + "_loopIndex");
					if (lastIndex == null) lastIndex = new Integer(0);
					Integer newIndex = new Integer(lastIndex.intValue() + 1);
					loopIndex.setValue(String.valueOf(newIndex.intValue()));
					loopIndexes.put(loopName + "_loopIndex", newIndex);
					datas.add(loopIndex);
				}
				for (Field field : fields)
				{
					FieldData data = new FieldData();
					data.setName(field.getName());
					data.setParent(parent);
					data.setEncoding(encoding);
					if (field.getFields() == null
							|| field.getFields().size() == 0)
					{
						String value = null;
						// 非循环字段，单个字段
						if (field.getCases() != null
								|| field.getDataType().indexOf("PackedDecimal") > -1
								|| field.getDataType().indexOf("ASM") > -1
								|| field.getDataType().indexOf("Binary") > -1)
						{
							// value應存為字節16進制字符串，不能直接new String()
							byte[] tmp = new byte[field.getBytes()];
							System.arraycopy(rowData, index, tmp, 0, field.getBytes());
							value = ByteUtils.toHexString(tmp);
						} else
						{
							value = new String(rowData, index,
									field.getBytes(), fixedFileInputExConfig.getEncoding());
						}
						data.setDataType(field.getDataType());
						data.setValue(value);
						data.setBytes(field.getBytes());
						current.addFieldData(data);
						datas.add(data);
						index += field.getBytes();
					} else
					{
						// 循环起始字段
						String loopCountString = field.getLoopCount();
						int loopCount = getLoopCountValue(loopCountString,
								field, current);
						data.setLoopCount(loopCount);
						current.addFieldData(data);
						data.setFieldDatas(getFieldValue(field.getFields(),
								rowData, index, loopCount, current, field.getName()));
						datas.add(data);
						// 如果循環體有限制總長度，則取循環體總長度，否則取字段長度纍計
						if (field.getBytes() != 0)
							index += field.getBytes();
						else
						{
							int tmp = 0;
							for (Field f : field.getFields())
							{
								tmp += f.getBytes();
							}
							index += tmp * loopCount;
						}
					}
				}
			}
			return datas;
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}

	private int getLoopCountValue(String loopCountString, Field field,
			FieldData parent) throws Exception
	{
		int loopCount = 0;
		if (loopCountString == null || "".equals(loopCountString))
			throw new DataPipeException("Field " + field.getName()
					+ "'s loop count can not be null!");
		if (loopCountString.startsWith("constant:"))
			loopCount = Integer.parseInt(loopCountString.substring(9));
		else if (loopCountString.startsWith("field:"))
		{
			String fieldName = loopCountString.substring(6);
			boolean flag = false;
			// 需要遞歸向上尋找對應名稱的字段
			if (parent.getFieldDatas() == null) parent = parent.getParent();
			while (!flag && parent != null)
			{
				for (FieldData data : parent.getFieldDatas())
				{
					if (data.getName().equals(fieldName))
					{
						loopCount = Integer.parseInt(data.getValue());
						flag = true;
						break;
					}
				}
				parent = parent.getParent();
			}
			if (!flag)
				throw new DataPipeException("No satisfiy loop count field!");
		} else if (loopCountString.startsWith("class:"))
		{
			String className = loopCountString.substring(6);
			Class<?> clazz = Class.forName(className);
			Method m = clazz.getMethod("execute", Map.class);
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("loopFileData", parent.getFieldDatas());
			loopCount = Integer.parseInt((String) m.invoke(clazz.newInstance(), parameter));
		} else
			throw new DataPipeException("Invalid loop count!");
		return loopCount;
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
}
