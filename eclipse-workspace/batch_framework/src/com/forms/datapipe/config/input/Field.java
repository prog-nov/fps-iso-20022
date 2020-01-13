package com.forms.datapipe.config.input;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * field
 * 
 * @author cxl
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Field implements Cloneable
{
	@XmlAttribute
	private String name;

	@XmlAttribute(name = "data-type")
	private String dataType;

	@XmlAttribute(name = "loop-count")
	private String loopCount;

	@XmlAttribute(name = "case-depend")
	private String caseDepend;

	@XmlAttribute(name = "case-default")
	private String caseDefault;

	@XmlAttribute(name = "key-field")
	private String keyField;
	
	@XmlAttribute(name = "file-id")
	private String fileId;
	
	@XmlAttribute
	private int bytes;
	
	@XmlAttribute(name = "const-value")
    private String constValue;
	
	@XmlAttribute(name = "control-flag")
	private String controlFlag;
	
	@XmlAttribute(name = "validate-method")
	private String validateMethod;

	@XmlElement(name = "field")
	private List<Field> fields;

	@XmlElement(name = "case")
	private List<Case> cases;

	private Field parent;

	public Field getParent()
	{
		return parent;
	}

	public void setParent(Field parent)
	{
		this.parent = parent;
	}

	public List<Field> getFields()
	{
		return fields;
	}

	public void setFields(List<Field> fields)
	{
		this.fields = fields;
		for (Field f : fields)
			f.parent = this;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public int getBytes()
	{
		return bytes;
	}

	public void setBytes(int bytes)
	{
		this.bytes = bytes;
	}

	public String getLoopCount()
	{
		return loopCount;
	}

	public void setLoopCount(String loopCount)
	{
		this.loopCount = loopCount;
	}

	public String getCaseDefault()
	{
		return caseDefault;
	}

	public void setCaseDefault(String caseDefault)
	{
		this.caseDefault = caseDefault;
	}

	public String getCaseDepend()
	{
		return caseDepend;
	}

	public void setCaseDepend(String caseDepend)
	{
		this.caseDepend = caseDepend;
	}

	public List<Case> getCases()
	{
		return cases;
	}

	public void setCases(List<Case> cases)
	{
		this.cases = cases;
	}

	@Override
	protected Object clone()
	{
		Field clone = new Field();
		clone.name = getName();
		clone.dataType = getDataType();
		clone.bytes = getBytes();
		clone.fields = getFields();
		clone.loopCount = getLoopCount();
		clone.parent = getParent();
		clone.cases = getCases();
		clone.caseDepend = getCaseDepend();
		clone.caseDefault = getCaseDefault();
		clone.keyField = getKeyField();
		clone.fileId = getFileId();
		clone.controlFlag = getControlFlag();
		clone.constValue = getConstValue();
		clone.validateMethod = getValidateMethod();
		return clone;
	}

	public static Field clone(Field source)
	{
		if (source == null)
		{
			return null;
		}
		return (Field) source.clone();
	}

	public String getKeyField()
	{
		return keyField;
	}

	public void setKeyField(String keyField)
	{
		this.keyField = keyField;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setFileId(String fileId)
	{
		this.fileId = fileId;
	}

	public String getControlFlag()
	{
		return controlFlag;
	}

	public void setControlFlag(String controlFlag)
	{
		this.controlFlag = controlFlag;
	}

	public String getConstValue()
	{
		return constValue;
	}

	public void setConstValue(String constValue)
	{
		this.constValue = constValue;
	}

	public String getValidateMethod()
	{
		return validateMethod;
	}

	public void setValidateMethod(String validateMethod)
	{
		this.validateMethod = validateMethod;
	}

}
