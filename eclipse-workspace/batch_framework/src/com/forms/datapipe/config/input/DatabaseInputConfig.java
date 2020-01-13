package com.forms.datapipe.config.input;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * database-input-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "database-input-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseInputConfig
{
    @XmlElement(name = "databaseName")
    private String databaseName;

    @XmlElement(name = "query-sql")
    private String querySql;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition)
    {
        this.fieldDefinition = fieldDefinition;
    }

    public String getQuerySql()
    {
        return querySql;
    }

    public void setQuerySql(String querySql)
    {
        this.querySql = querySql;
    }

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
