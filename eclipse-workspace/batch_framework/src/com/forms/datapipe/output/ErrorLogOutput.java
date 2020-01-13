package com.forms.datapipe.output;

import java.util.Map;

import com.forms.framework.log.BatchLogger;
import com.forms.datapipe.Output;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.OutputConfig;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FieldDefinition;
import com.forms.datapipe.config.output.FixedFileOutputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.util.DataPipeUtils;

public class ErrorLogOutput implements Output
{

	private OutputConfig config;

	private FixedFileOutputConfig fixedFileOutputConfig;

	private String fileName;

	private int lineBytes = 0;

	private FieldDefinition fieldDefinition;

	private int rowIndex;

	private String jobId;

	private String actionName;

	private BatchLogger batchLog = null;

	private String logLevel = null;

	private String LOGLEVEL_INFO = "INFO";

	private String LOGLEVEL_WARN = "WARN";

	private String LOGLEVEL_ERROR = "ERROR";

	private StringBuffer logStr = new StringBuffer();

	public int getRowIndex()
	{
		return rowIndex;
	}

	public void init(OutputConfig config, PipeContext pipeContext)
			throws DataPipeException
	{

		this.config = config;
		fixedFileOutputConfig = DataPipeConfigFactory
				.getFixedFileOutputConfig(config.getConfigFile());
		fieldDefinition = fixedFileOutputConfig.getFieldDefinition();

		fileName = DataPipeUtils.replaceParameters(fixedFileOutputConfig
				.getFileName(), pipeContext.getParameters());
		jobId = pipeContext.getParameters().get("jobId");
		actionName = pipeContext.getParameters().get("actionName");
		batchLog = BatchLogger.getLogger(jobId, actionName, this.getClass()
				.toString());
		logLevel = pipeContext.getParameter("log_level");
		
	}

	public synchronized void putRowData(Map<String, Object> rowData)
			throws DataPipeException
	{
		for (Field field : fieldDefinition.getFields())
		{
			logStr.append(rowData.get(field.getName()));
		}
		if (LOGLEVEL_INFO.equalsIgnoreCase(logLevel))
		{
			batchLog.info(logStr.toString());
		} else if (LOGLEVEL_WARN.equalsIgnoreCase(logLevel))
		{
			batchLog.warn(logStr.toString());
		} else if (LOGLEVEL_ERROR.equalsIgnoreCase(logLevel))
		{
			batchLog.error(logStr.toString());
		} else
		{
			batchLog.info(logStr.toString());
		}
		logStr = new StringBuffer();
	}

	public void close() throws DataPipeException
	{
	}

	public OutputConfig getConfig()
	{
		return config;
	}

	public FieldDefinition getFieldDefinition()
	{
		return this.fieldDefinition;
	}

	public FixedFileOutputConfig getFixedFileOutputConfig()
	{
		return fixedFileOutputConfig;
	}

	public String getFileName()
	{
		return fileName;
	}

	public int getLineBytes()
	{
		return lineBytes;
	}

	public String getSeqPattern()
	{
		return fixedFileOutputConfig.getSeqPattern();
	}

	public int getSeqStartAt()
	{
		return fixedFileOutputConfig.getSeqStartAt();
	}

	public int getSplitRows()
	{
		return fixedFileOutputConfig.getSplitRows();
	}
}
