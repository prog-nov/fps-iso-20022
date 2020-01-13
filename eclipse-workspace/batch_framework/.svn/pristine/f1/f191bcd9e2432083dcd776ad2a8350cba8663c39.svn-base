package com.forms.datapipe.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.FileInfo;
import com.forms.datapipe.config.output.FixedFileOutputExConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;
import com.forms.datapipe.output.header.FileOutputFooterHandler;
import com.forms.datapipe.output.header.FileOutputHeaderHandler;
import com.forms.datapipe.performance.PerformanceMonitor;
import com.forms.datapipe.util.DataPipeUtils;

/**
 * 循環數據（分隔符）格式文件输出
 * 
 * @author cxl
 * 
 */
public class FixedFileOutputEx implements Output, FileSplitOutputSupport
{

	private static Log log = LogFactory.getLog(FixedFileOutputEx.class);

	private OutputConfig config;

	private FixedFileOutputExConfig fixedFileOutputExConfig;

	private String fileName;

	private FileOutputStream fos;

	private byte[] buffer;

	private String lineFeed;

	private String delimiter;

	private String enclosure;

	private PipeContext pipeContext;

	private File file;

	private int rowIndex;

	private Map<String, OutputStream> outputs = new HashMap<String, OutputStream>();

	private Map<String, FileInfo> fileInfos = new HashMap<String, FileInfo>();
	
	private Map<String, File> files = new HashMap<String, File>();

	private Map<String, List<String>> buffers = new HashMap<String, List<String>>();
	
	private static final String MAIN_FILE = FixedFileOutputEx.class.getName() + "_mainFile";

	public int getRowIndex()
	{
		return rowIndex;
	}

	public void close() throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'close' called. ] ");
		try
		{
			Iterator<String> it = buffers.keySet().iterator();
			while (it.hasNext())
			{
				String fileId = it.next();
				List<String> buffer = buffers.get(fileId);
				if (buffer != null && buffer.size() > 0)
					writeData(fileId, buffer);
			}
			writeFooter();
		} finally
		{
			for (OutputStream os : outputs.values())
				DataPipeUtils.close(os);
		}

		if (log.isDebugEnabled())
			log.debug(" [ write header. ] ");
		if (file != null && file.exists())
			writeHeader();
		Iterator<String> tmpFiles = files.keySet().iterator();
		while (tmpFiles.hasNext())
		{
			String fileId = tmpFiles.next();
			File tmpFile = files.get(fileId);
			File destFile = new File(fileInfos.get(fileId).getFileName());
			if (destFile.exists()) destFile.delete();
			if (tmpFile != null && tmpFile.exists())
				tmpFile.renameTo(destFile);
		}

		buffer = null;
		buffers = null;
		fileInfos = null;
		files = null;
		outputs = null;
	}

	private void writeFooter() throws DataPipeException
	{
		// handle header
		if (StringUtils.isEmpty(fixedFileOutputExConfig.getFooterHandleClass()))
			return;
		FileOutputFooterHandler footerHandler = (FileOutputFooterHandler)DataPipeUtils
				.newInstance(fixedFileOutputExConfig.getFooterHandleClass());
		String footerData = footerHandler.handle(this, pipeContext);
		try
		{
			if (footerData == null || "".equals(footerData))
				return;
			fos.write(footerData.getBytes(fixedFileOutputExConfig.getEncoding()));
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
	}

	private void writeHeader() throws DataPipeException
	{
		// handle header
		if (StringUtils.isEmpty(fixedFileOutputExConfig.getHeaderHandleClass()))
			return;

		FileOutputHeaderHandler headerHandler = (FileOutputHeaderHandler)DataPipeUtils
				.newInstance(fixedFileOutputExConfig.getHeaderHandleClass());
		String headerData = headerHandler.handle(this, pipeContext);

		FileInputStream fis = null;
		FileOutputStream fos = null;
		File tmp = null;
		try
		{
			tmp = new File(fileName + ".tmp1");
			fos = new FileOutputStream(tmp);
			fis = new FileInputStream(file);
			fos.write(headerData.getBytes(fixedFileOutputExConfig.getEncoding()));
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
			files.put(MAIN_FILE, file);
		}
	}

	public FieldDefinition getFieldDefinition()
	{
		return fixedFileOutputExConfig.getFieldDefinition();
	}

	public void init(OutputConfig config, PipeContext pipeContext)
			throws DataPipeException
	{
		if (log.isDebugEnabled())
			log.debug(" [ method 'init' called. ] ");

		this.config = config;
		this.pipeContext = pipeContext;
		fixedFileOutputExConfig = DataPipeConfigFactory
				.getFixedFileOutputExConfig(config.getConfigFile());

		fileName = DataPipeUtils.replaceParameters(fixedFileOutputExConfig
				.getFileName(), pipeContext.getParameters());
		lineFeed = fixedFileOutputExConfig.getLineFeed();
		delimiter = fixedFileOutputExConfig.getDelimiter();
		enclosure = fixedFileOutputExConfig.getEnclosure();

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
			// 循環的主文件輸出流初始化
			if (!StringUtils.isEmpty(fixedFileOutputExConfig
					.getHeaderHandleClass()))
			{
				// 如果有頭信息，需要先輸出到一個臨時文件，然後將數據複製到目標文件
				file = new File(fileName + ".tmp0");
			} else
			{
				file = new File(fileName + ".tmp1");
			}
			fos = new FileOutputStream(file);
			// 如果存在子文件的定義，則初始化所有循環子文件的輸出流
			if (fixedFileOutputExConfig.getFileInfos() != null
					&& fixedFileOutputExConfig.getFileInfos().getFileInfos() != null)
			{
				for (FileInfo info : fixedFileOutputExConfig.getFileInfos()
						.getFileInfos())
				{
					String subFileName = DataPipeUtils.replaceParameters(
							info.getFileName(), pipeContext.getParameters());
					File subFile = new File(subFileName + ".tmp1");
					FileOutputStream os = new FileOutputStream(subFile);
					info.setFileName(subFileName);
					outputs.put(info.getFileId(), os);
					fileInfos.put(info.getFileId(), info);
					files.put(info.getFileId(), subFile);
				}
			}
			FileInfo main = new FileInfo();
			main.setDelimiter(delimiter);
			main.setEnclosure(enclosure);
			main.setEncoding(fixedFileOutputExConfig.getEncoding());
			main.setFileId(this.getClass().getName() + "_mainFile");
			main.setFlushRows(fixedFileOutputExConfig.getFlushRows());
			main.setLineFeed(lineFeed);
			main.setFileName(fileName);
			outputs.put(MAIN_FILE, fos);
			fileInfos.put(MAIN_FILE, main);
			files.put(MAIN_FILE, file);
			dealLineFeed(fileInfos);
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}

		buffer = new byte[4096];
		rowIndex = 0;
		if (log.isDebugEnabled())
			log.debug(" [ success. ] ");
	}

	@SuppressWarnings("unchecked")
	public synchronized void putRowData(Map<String, Object> rowData)
			throws DataPipeException
	{
		PerformanceMonitor.startRecord(this);
		if (log.isDebugEnabled())
			log.debug(" [ method 'putRowData' called. ] ");
		rowIndex++;
		// rowData應為和輸入數據相同結構的樹形數據
		outputRowData((List<FieldData>) rowData.get("loopFileData"),
				fixedFileOutputExConfig.getFieldDefinition().getFields(), MAIN_FILE);
		PerformanceMonitor.endRecord(this);
	}

	public OutputConfig getConfig()
	{
		return config;
	}

	public FixedFileOutputExConfig getfixedFileOutputExConfig()
	{
		return fixedFileOutputExConfig;
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
		return fixedFileOutputExConfig.getSeqPattern();
	}

	public int getSeqStartAt()
	{
		return fixedFileOutputExConfig.getSeqStartAt();
	}

	public int getSplitRows()
	{
		return fixedFileOutputExConfig.getSplitRows();
	}

	/**
	 * 處理行數據
	 * 
	 * @param fieldDatas --
	 *            只處理當前行的數據，循環數據為單次循環的數據
	 * @param fields
	 * @throws DataPipeException
	 */
	private void outputRowData(List<FieldData> fieldDatas, List<Field> fields,
			String fileId) throws DataPipeException
	{
		FileInfo f = fileInfos.get(fileId);
		StringBuffer rowValue = new StringBuffer(); // 當前層需要輸出的行數據
		for (Field field : fields)
		{
			String value = null;
			// 輸出的數據配置應和輸出的數據層次結構相同
			if (field.getFields() == null || field.getFields().size() == 0)
			{
				// 非循環數據
				if (field.getKeyField() == null
						|| "".equals(field.getKeyField()))
				{
					// 非鍵值字段，直接在當前數據層尋找對應數據
					for (FieldData fieldData : fieldDatas)
					{
						if (fieldData.getName().equals(field.getName()))
						{
							value = fieldData.getValue();
							break;
						}
					}
				} else
				{
					// 是鍵值字段，需要遞歸向上層數據尋找對應字段
					FieldData parent = fieldDatas.get(0).getParent();
					boolean flag = false;
					while (parent != null && !flag)
					{
						for (FieldData data : parent.getFieldDatas())
						{
							if (field.getKeyField().equals(data.getName()))
							{
								value = data.getValue();
								flag = true;
								break;
							}
						}
						parent = parent.getParent();
					}
				}
				if (value == null)
					value = ""; 
				// 因爲存在reDefine的問題，所以當前字段在數据集中找不到對應的值是正常情況，置成空字符串以保證輸出檔的正常執行
				rowValue.append(f.getEnclosure());
				rowValue.append(value);
				rowValue.append(f.getEnclosure());
				rowValue.append(f.getDelimiter());
			} else
			{
				// 從當前數據層找到對應的循環數據起始對象
				FieldData tmp = null;
				for (FieldData fieldData : fieldDatas)
				{
					if (fieldData.getName().equals(field.getName()))
					{
						tmp = fieldData;
						break;
					}
				}
				// 和非循環數據相同，當前循環起始字段在數据集中找不到對應值也是正常情況
				if (tmp != null && tmp.getLoopCount() > 0)
				{
					// 循環數據，需要遞歸處理
					int fieldCnt = tmp.getFieldDatas().size()
						/ tmp.getLoopCount();
					for (int i = 0; i < tmp.getLoopCount(); i++)
					{
						List<FieldData> datas = new ArrayList<FieldData>();
						for (int j = 0; j < fieldCnt; j++)
						{
							datas.add(tmp.getFieldDatas().remove(0));
						}
						outputRowData(datas, field.getFields(), field.getFileId());
					}
				}
			}
		}
		if (rowValue.length() > 0)
			putRowValue2Buffer(rowValue, fileId);
	}

	private void putRowValue2Buffer(StringBuffer rowValue, String fileId)
			throws DataPipeException
	{
		rowValue.delete(rowValue.length() - delimiter.length(), rowValue
				.length()); // 去掉最後一個分隔符
		List<String> buffer = buffers.get(fileId);
		if (buffer == null)
			buffer = new ArrayList<String>();
		if (buffer.size() >= fileInfos.get(fileId).getFlushRows())
			writeData(fileId, buffer);
		buffer.add(rowValue.toString());
		buffers.put(fileId, buffer);
	}

	private void writeData(String fileId, List<String> buffer)
			throws DataPipeException
	{
		FileInfo f = fileInfos.get(fileId);
		FileOutputStream fos = (FileOutputStream) outputs.get(fileId);
		for (String rowValue : buffer)
		{
			try
			{
				fos.write((rowValue + f.getLineFeed())
						.getBytes(f.getEncoding()));
			} catch (Exception e)
			{
				throw new DataPipeException(e);
			}
		}
		buffer.clear();
	}
	
	private void dealLineFeed(Map<String, FileInfo> fileInfos) throws DataPipeException
	{
		Iterator it = fileInfos.keySet().iterator();
		while (it.hasNext())
		{
			FileInfo f = fileInfos.get(it.next());
			if ("\\n".equals(f.getLineFeed()))
			{
				f.setLineFeed("\n");
			} else if ("\\r".equals(f.getLineFeed()))
			{
				f.setLineFeed("\r");
			} else if ("\\r\\n".equals(f.getLineFeed()))
			{
				f.setLineFeed("\r\n");
			}  else if ("\n".equals(f.getLineFeed()) || "\r".equals(f.getLineFeed()) || "\r\n".equals(f.getLineFeed()))
			{
				return;
			}else
			{
				throw new DataPipeException(" [ Invalid lineFeed '" + f.getLineFeed()
						+ "'! ] ");
			}
		}
	}
}
