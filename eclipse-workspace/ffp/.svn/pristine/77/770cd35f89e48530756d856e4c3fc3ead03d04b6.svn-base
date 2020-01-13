package com.forms.ffp.webapp.common.bean;

import java.util.Locale;

import com.forms.beneform4j.util.Tool;

public class DataLayoutBean
{
	private String labelKey;

	private String valueKey;

	private String labelStr;

	private String valueStr;

	public DataLayoutBean()
	{
	}

	public DataLayoutBean(String labelKey, String valueKey)
	{
		this.labelKey = labelKey;
		this.valueKey = valueKey;
	}

	public String getLabelKey()
	{
		return labelKey;
	}

	public void setLabelKey(String labelKey)
	{
		this.labelKey = labelKey;
	}

	public String getValueKey()
	{
		return valueKey;
	}

	public void setValueKey(String valueKey)
	{
		this.valueKey = valueKey;
	}

	public String getLabelStr()
	{

		Locale locale = Tool.LOCALE.getCurrentLocale();
		labelStr = Tool.LOCALE.getMessage(this.labelKey, locale);
		return labelStr == null || "".equals(labelStr) ? this.labelKey : labelStr;
	}

	public void setLabelStr(String labelStr)
	{
		this.labelStr = labelStr;
	}

	public String getValueStr()
	{
		Locale locale = Tool.LOCALE.getCurrentLocale();
		valueStr = Tool.LOCALE.getMessage(this.valueKey,locale);
		return valueStr == null || "".equals(valueStr) ? this.valueKey : valueStr;
	}

	public void setValueStr(String valueStr)
	{
		this.valueStr = valueStr;
	}

}
