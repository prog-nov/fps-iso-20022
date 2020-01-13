package com.forms.datapipe.headfoot;

import java.util.List;

import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.output.header.FileOutputFooterHandler;
import com.forms.datapipe.Output;

/**
 * headfootclass
 * 
 * @author ahnan createDate:2011-04-10 updateDate:2011-04-28
 */
public class FooterHandler implements FileOutputFooterHandler
{

	/* (non-Javadoc)
	 * @see com.forms.datapipe.output.header.FileOutputFooterHandler#handle(com.forms.datapipe.Output, com.forms.datapipe.context.PipeContext)
	 */
	public String handle(Output ip_output, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		String loc_fillter = "";
		String loc_value = "";
		int loc_recLen = 0;
		try
		{
			loc_recLen=this.getRecLen(ip_output);
			// no data,docCnt=0
			Object loc_docCnt = ip_pipeContext.getAttribute("docCnt");
			if (null == ip_pipeContext.getAttribute("docCnt")
					|| "0".equals(loc_docCnt))
			{
				loc_fillter = "";
				loc_docCnt = 0;
			}
			//name, bytes, data-type, value
			String[][] loc_dataArray = {
					{ "recId", "1", "StringType", "2" },
					{ "fileId" },
					{ "acDate", "10", "CharTypeLJ", "#{acDate[yyyy/MM/dd]}" },
					{ "sysDate", "16", "CharTypeLJ",
							"#{sysDate[yyyy/MM/ddHHmmss]}" },
					{ "docCnt", "9", "FixedNumberTypeRJ",
							String.valueOf(loc_docCnt) },
					{ "fillter", String.valueOf(loc_recLen), "CharTypeLJ", "" } };

			// get fixedFileOutputConfig object
			List<HeadFootBean> loc_list = HeadFootInit.getInstance().getList(
					ip_pipeContext.getParameter("jobPath"),
					this.getFileName(ip_output));
			if (loc_list == null)
			{
				throw new DataPipeException(this.getFileName(ip_output)
						+ " Foot config not found");
			}
			loc_value = HeadFootUtil.dealHeadFoot(loc_list, ip_pipeContext, loc_dataArray)
					+ loc_fillter;
		} catch (Exception e)
		{
			throw new DataPipeException(e);
		}

		return loc_value;
	}
	/**
	 * get record legnth
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	protected int getRecLen(Output ip_output) throws DataPipeException
	{
		return HeadFootUtil.getRecLen(ip_output);
	}
	/**
	 * get file name
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	protected String getFileName(Output ip_output) throws DataPipeException
	{
		return HeadFootUtil.getFileName(ip_output);
	}

}
