package com.forms.ffp.msg.iclfps.bussiness;

import java.util.List;

import com.forms.ffp.msg.iclfps.validation.FFPValidateMessage;

public class FFPBaseBussinessDataBean
{
	@FFPValidateMessage(field = "Number of Copies")
	protected int numOfCopy = 1;
	protected List<String> appendSequenceFields;

	public int getNumOfCopy()
	{
		return this.numOfCopy;
	}

	public void setNumOfCopy(int numOfCopy)
	{
		this.numOfCopy = numOfCopy;
	}

	public List<String> getAppendSequenceFields()
	{
		return this.appendSequenceFields;
	}

	public void setAppendSequenceFields(List<String> appendSequenceFields)
	{
		this.appendSequenceFields = appendSequenceFields;
	}
}
