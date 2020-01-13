package com.forms.datapipe.headfoot;

import java.util.List;

import com.forms.datapipe.Output;
import com.forms.datapipe.context.PipeContext;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.output.header.FileOutputHeaderHandler;

/**
 * headfootclass
 * 
 * @author ahnan createDate:2011-04-10 updateDate:2011-04-28
 */
public class HeaderHandler implements FileOutputHeaderHandler
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.forms.datapipe.output.header.FileOutputHeaderHandler#handle(com.forms.datapipe.Output,
	 *      com.forms.datapipe.context.PipeContext)
	 */
	public String handle(Output ip_output, PipeContext ip_pipeContext)
			throws DataPipeException
	{
		String loc_fillter = "";
		String loc_value = "";
		int loc_recLen = 0;

		try
		{

			// name, bytes, data-type, value
			loc_recLen = getRecLen(ip_output);
			String[][] loc_dataArray = {
					{ "recId", "1", "StringType", "0" },
					{ "recLen", "5", "FixedNumberTypeRJ",
							String.valueOf(loc_recLen) }, { "fileId" },
					{ "fileName" },
					{ "fillter", String.valueOf(loc_recLen), "CharTypeLJ", "" } };

			List<HeadFootBean> loc_list = HeadFootInit.getInstance().getList(
					ip_pipeContext.getParameter("jobPath"),
					this.getFileName(ip_output));
			if (loc_list == null)
			{
				throw new DataPipeException(this.getFileName(ip_output)
						+ " Header config not found");
			}
			/*Object docCnt = pipeContext.getAttribute("docCnt");
			if (null != pipeContext.getAttribute("docCnt")
					&& !"0".equals(docCnt)){
				String lineFeed = this.getLineFeed(output);
				if ("none".equals(lineFeed) || lineFeed == null
						|| "".equals(lineFeed))
				{
					fillter = "";
				} else if ("\\n".equals(lineFeed))
				{
					fillter = "\n";
				} else if ("\\r".equals(lineFeed))
				{
					fillter = "\r";
				} else if ("\\r\\n".equals(lineFeed))
				{
					fillter = "\r\n";
				}
			}*/

			loc_value = HeadFootUtil.dealHeadFoot(loc_list, ip_pipeContext, loc_dataArray)
					+ loc_fillter;
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new DataPipeException(e);
		}

		return loc_value;
	}

	/**
	 * get record length
	 * 
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
	 * 
	 * @param ip_output
	 * @return
	 * @throws DataPipeException
	 */
	protected String getFileName(Output ip_output) throws DataPipeException
	{
		return HeadFootUtil.getFileName(ip_output);
	}

}
