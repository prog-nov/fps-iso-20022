package com.forms.framework.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.forms.framework.BatchBaseJob;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.util.DateUtil;
import com.forms.framework.util.StringUtil;

public abstract class BaseWaitJob extends BatchBaseJob
{

	protected long sleepTime = 1000 * 60 * 10;

	protected Integer waittime;

	protected String utilTime;

	protected Map<String, String> loc_para = new HashMap<String, String>();
	
	private String timePattern="HH:mm:ss";

	@SuppressWarnings("unchecked")
	@Override
	public void init() throws BatchJobException
	{
		super.init();
		try
		{
			List<Element> loc_list = this.jobConfiger
					.getElementsByPath("job-config.private-settings");
			if (loc_list == null || loc_list.size() == 0)
			{
				throw new BatchJobException(
						"Null configer for wait action parameters in job configer:"
								+ this.actionName);
			}

			List<Element> loc_paraList = loc_list.get(0).elements();
			// get element
			for (Element loc_e : loc_paraList)
			{
				loc_para.put(StringUtil.xmlToJavaLow(loc_e.getName()), loc_e
						.getText());
			}

			String sleepTimeStr = loc_para.get("sleeptime");
			if (sleepTimeStr != null && !sleepTimeStr.equals(""))
			{
				sleepTime = Long.parseLong(sleepTimeStr) * 1000;
			}
			String waitTimeStr = loc_para.get("waittime");
			if (waitTimeStr != null && !waitTimeStr.equals(""))
			{
				waittime = Integer.valueOf(waitTimeStr);
			}
			utilTime = loc_para.get("utiltime");
			if (utilTime != null)
			{
				utilTime = DateUtil.dateToString(DateUtil.stringToDate(
						utilTime, timePattern), timePattern);
			}
		} catch (Exception ip_ex)
		{
			throw new BatchJobException(ip_ex);
		}
	}

	@Override
	public boolean execute() throws BatchJobException
	{
		boolean flag = false;
		try
		{
			while ((waittime == null && utilTime == null)
					|| (waittime == null || waittime > 0)
					&& (utilTime == null || utilTime.compareTo(DateUtil.getSysDate()) > 0))
			{
				if (waittime != null)
				{
					waittime--;
				}
				if (executeCondition())
				{
					flag = true;
					break;
				} else
				{
					try
					{
						this.batchLogger.info("condition false wait "
								+ sleepTime + " ms!");
						Thread.sleep(sleepTime);
					} catch (InterruptedException e)
					{
						throw new BatchJobException(e);
					}
				}
			}
		} catch (Exception e)
		{
			throw new BatchJobException(e);
		}
		if (!flag)
		{
			throw new BatchJobException("time is over,job failed");
		}
		return true;
	}

	public abstract boolean executeCondition() throws BatchJobException;

}
