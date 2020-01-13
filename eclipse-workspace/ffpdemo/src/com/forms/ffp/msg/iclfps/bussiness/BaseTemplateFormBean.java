package com.forms.ffp.msg.iclfps.bussiness;

public abstract class BaseTemplateFormBean extends FFPBaseBussinessDataBean
{
	private String fileId;

	public String getFileId()
	{
		return this.fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public abstract int getCount();
}
