package com.forms.datapipe.headfoot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.config.input.FixedFileInputConfig;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.header.FileInputHeaderHandler;
import com.forms.datapipe.Input;

/**
 * input head foot hander
 * 
 * @author lyz createDate:2011-04-10 updateDate:2011-04-28
 */
public class InputHeaderHandler implements FileInputHeaderHandler
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.datapipe.input.header.FileInputHeaderHandler#handle(com.forms.datapipe.Input,
	 *      com.forms.datapipe.context.PipeContext, java.lang.String)
	 */
	public Map<String, String> handle(Input ip_input, PipeContext ip_pipeContext,
			String ip_string) throws DataPipeException
	{
		Map<String, String> loc_returnValue = new HashMap<String, String>();
		byte[] loc_rowData = null;
		int loc_recLen = 0;
		try
		{
			FixedFileInputConfig loc_config = DataPipeConfigFactory
					.getFixedFileInputConfig(ip_input.getConfig().getConfigFile());
			for (Field loc_f : loc_config.getFieldDefinition().getFields())
			{
				loc_recLen += loc_f.getBytes();
			}
			String[][] loc_dataArray = { { "recId", "1" }, { "recLen", "5" },
					{ "fileId" }, { "fileName" },
					{ "fillter", String.valueOf(loc_recLen) } };

			String loc_fileName = HeadFootUtil.parseFileName(loc_config
					.getFileName());

			List<HeadFootBean> loc_list = HeadFootInit.getInstance().getList(
					ip_pipeContext.getParameter("jobPath"), loc_fileName);
			loc_rowData = ip_string.getBytes(loc_config.getEncoding());
			loc_returnValue = HeadFootUtil.dealInputHeadFoot(loc_list, loc_config
					.getEncoding(), loc_dataArray, loc_rowData);
			ip_pipeContext.setAttribute("inRecLen", loc_returnValue.get("recLen"));
			ip_pipeContext.removeAttribute("indocCnt");
			ip_pipeContext.setAttribute("inName", ip_input.getConfig().getName());
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}
		return loc_returnValue;
	}

}
