package com.forms.datapipe.input;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于储存输入数据
 * 
 * @author YY
 *
 */
public class FieldData
{
	private String name;
	
	private String dataType;
	
	private String value;
	
	private int bytes;
	
	private FieldData parent;
	
	private List<FieldData> fieldDatas;
	
	private int loopCount;
	
	private String encoding;

	public String getEncoding()
	{
		return encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public int getLoopCount()
	{
		return loopCount;
	}

	public void setLoopCount(int loopCount)
	{
		this.loopCount = loopCount;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public List<FieldData> getFieldDatas()
	{
		return fieldDatas;
	}

	public void setFieldDatas(List<FieldData> fieldDatas)
	{
		this.fieldDatas = fieldDatas;
	}
	
	public void addFieldData(FieldData data)
	{
		if (this.fieldDatas == null)
			this.fieldDatas = new ArrayList<FieldData>();
		this.fieldDatas.add(data);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public FieldData getParent()
	{
		return parent;
	}

	public void setParent(FieldData parent)
	{
		this.parent = parent;
	}

	public int getBytes()
	{
		return bytes;
	}

	public void setBytes(int bytes)
	{
		this.bytes = bytes;
	}

}
