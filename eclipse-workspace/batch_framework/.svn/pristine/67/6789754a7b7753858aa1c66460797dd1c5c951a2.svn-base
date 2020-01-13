package com.forms.datapipe.headfoot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.framework.util.DateUtil;
import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FixedFileInputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputFooterHandler;
import com.forms.datapipe.Input;

/**
 * input head foot parse
 * 
 * @author lyz createDate:2011-04-10 updateDate:2011-04-28
 */
public class InputFooterHandler implements FileInputFooterHandler
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.datapipe.input.header.FileInputFooterHandler#handle(com.forms.datapipe.Input,
	 *      com.forms.datapipe.context.PipeContext, java.lang.String)
	 */
	public Map<String, String> handle(Input ip_input,
			PipeContext ip_pipeContext, String ip_string)
			throws DataPipeException
	{
		Map<String, String> loc_returnValue = new HashMap<String, String>();
		byte[] loc_rowData = null;
		int loc_recLen = 0;
		try
		{
			FixedFileInputConfig loc_config = DataPipeConfigFactory
					.getFixedFileInputConfig(ip_input.getConfig()
							.getConfigFile());
			for (Field f : loc_config.getFieldDefinition().getFields())
			{
				loc_recLen += f.getBytes();
			}
			Object loc_indocCnt = ip_pipeContext.getAttribute("indocCnt");
			// name, bytes, data-type, value
			String[][] loc_dataArray = { { "recId", "1" }, { "fileId" },
					{ "acDate", "10", }, { "sysDate", "16", },
					{ "docCnt", "9" },
					{ "fillter", String.valueOf(loc_recLen) } };

			String loc_fileName = HeadFootUtil.parseFileName(loc_config
					.getFileName());

			List<HeadFootBean> loc_list = HeadFootInit.getInstance()
					.getList(ip_pipeContext.getParameter("jobPath"),
							loc_fileName);
			loc_rowData = ip_string.getBytes(loc_config.getEncoding());
			loc_returnValue = HeadFootUtil.dealInputHeadFoot(loc_list,
					loc_config.getEncoding(), loc_dataArray, loc_rowData);
			String loc_fileAcDate = DateUtil.dateToString(DateUtil
					.stringToDate(loc_returnValue.get("acDate"), "yyyy/MM/dd"),
					DateUtil.BATCH_DATE_FORMAT);
			if (ip_pipeContext.getParameter("validateDate") != null
					&& !"".equals(ip_pipeContext.getParameter("validateDate")))
			{
				if (!loc_fileAcDate.equals(ip_pipeContext
						.getParameter("validateDate")))
				{
					throw new DataPipeException(
							"File acDate error.File acDate=" + loc_fileAcDate
									+ ",Batch acDate="
									+ ip_pipeContext.getParameter("validateDate"));

				}
			}			
			if (loc_indocCnt != null && loc_returnValue.get("docCnt") != null)
			{
				// if file contains empty recode throw datapipeExcpetion
				if ("0".equals(loc_returnValue.get("docCnt")))
					throw new DataPipeException("File: " + loc_fileName
							+ " has blank record");

				if (Integer.parseInt(loc_indocCnt.toString()) != Integer
						.parseInt(loc_returnValue.get("docCnt").toString()))
				{
					throw new DataPipeException("File: "
							+ loc_fileName
							+ "    footdocCnt="
							+ Integer.parseInt(loc_returnValue.get("docCnt")
									.toString()) + ",recordCnt=" + loc_indocCnt);
				}
			}

		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		return loc_returnValue;
	}

}
