package com.forms.framework.ctrl;

import com.forms.framework.exception.BatchFrameworkException;
import com.forms.framework.exception.BatchJobException;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.CommonAPI;

public class BreakPoint
{

	private String batchAcDate = null;

	private String jobId = null;

	private int jnlSeq = 0;

	private String actionName = null;

	private String bussinessMessage = null;

	public BreakPoint(String ip_a_batchAcDate, String ip_a_jobId, int ip_a_jnlSeq)
	{
		this.batchAcDate = ip_a_batchAcDate;
		this.jobId = ip_a_jobId;
		this.jnlSeq = ip_a_jnlSeq;
	}

	public BreakPoint(String ip_a_batchAcDate, String ip_a_jobId, int ip_a_jnlSeq,
			String a_actionName)
	{
		this.batchAcDate = ip_a_batchAcDate;
		this.jobId = ip_a_jobId;
		this.jnlSeq = ip_a_jnlSeq;
		this.actionName = a_actionName;
	}

	public String getActionName()
	{
		return actionName;
	}

	public String getBussinessMessageKey()
	{
		if (bussinessMessage == null)
			return null;
		int idx = bussinessMessage.indexOf("=");
		if (idx < 0)
			return bussinessMessage;
		return bussinessMessage.substring(0, idx);
	}

	public String getBussinessMessageValue()
	{
		if (bussinessMessage == null)
			return null;
		int idx = bussinessMessage.indexOf("=");
		if (idx < 0)
			return bussinessMessage;
		return bussinessMessage.substring(idx + 1);
	}

	public void parseBreakPoint(String ip_actionName_bussinessMessage)
			throws BatchFrameworkException
	{
		if (ip_actionName_bussinessMessage == null
				|| ip_actionName_bussinessMessage.trim().length() < 1)
		{
			return;
		}
		int loc_actIdx = ip_actionName_bussinessMessage.indexOf(":");
		if (loc_actIdx < 1)
		{
			throw new BatchFrameworkException(
					"No action name in breakpoint message");
		}
		this.actionName = ip_actionName_bussinessMessage.substring(0, loc_actIdx);
		this.bussinessMessage = ip_actionName_bussinessMessage
				.substring(loc_actIdx + 1);
	}

	public boolean save(String ip_a_key, String ip_a_value) throws Exception
	{
		if (ip_a_key == null || ip_a_key.trim().length() < 1)
		{
			throw new BatchJobException("Null value for breakpoint key");
		}
		bussinessMessage = ip_a_key + "=" + ip_a_value;
		String loc_bpMsg = actionName + ":" + bussinessMessage;
		String loc_sql = "update "
				+ CommonAPI.schema
				+ "." + BatchJobJnlDao.BATCH_JNL_TABLE + " set BREAK_POINT=? where BATCH_AC_DATE=? and JOB_ID=? and JNL_SEQ=?";
		try
		{
			EntityManager.update(loc_sql, loc_bpMsg, batchAcDate, jobId, jnlSeq);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return true;
	}


}
