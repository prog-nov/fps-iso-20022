package com.forms.batch.job.commom;

public class SplitResult
{
	private String resultString;

	private String sign;

	private int start;

	private int end;

	public String getResultString()
	{
		return resultString;
	}

	public void setResultString(String resultString)
	{
		this.resultString = resultString;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getEnd()
	{
		return end;
	}

	public void setEnd(int end)
	{
		this.end = end;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}

	public String toString()
	{
		return "start=" + start + ",end=" + end + ",resultString="
				+ resultString;
	}
}
