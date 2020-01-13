package com.forms.framework.job;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.DateUtil;
import com.forms.framework.util.ExecCmdUtil;

public class UploadFileJob extends BatchBaseJob
{

	protected String cmd = null;

	protected String properties = null;

	protected String DATE_PATTERN = "YYYYMMDD";

	@Override
	public void init() throws BatchJobException
	{
		// TODO Auto-generated method stub
		super.init();
		try
		{
			List<Element> loc_list = this.jobConfiger
					.getElementsByPath("job-config.private-settings.sh-file");
			if (loc_list == null || loc_list.size() == 0)
			{
				throw new BatchJobException("sh-file not found in config");
			}
			cmd = this.batchRoot + File.separator + "bin" + File.separator
					+ loc_list.get(0).getText();
			List<Element> pro_list = this.jobConfiger
					.getElementsByPath("job-config.private-settings.properties-file");
			if (pro_list == null || pro_list.size() == 0)
			{
				throw new BatchJobException(
						"properties-file not found in config");
			}
			properties = this.batchConfInfo + File.separator
					+ pro_list.get(0).getText();
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		try
		{
			ExecCmdUtil.execCmd(getParameter());
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		return false;
	}

	protected String[] getParameter() throws ParseException
	{
		return new String[] {
				cmd,
				properties,
				DateUtil.dateToString(DateUtil.stringToDate(
						this.batchAcDate, DateUtil.BATCH_DATE_FORMAT),
						DateUtil.BATCH_DATE_FORMAT_NO_SPLIT) };
	}

}
