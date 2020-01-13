package com.forms.datapipe.config.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.forms.datapipe.config.input.FieldDefinition;

/**
 * 对应配置文件database-output-config.xml
 * 
 * @author cxl
 * 
 */
@XmlRootElement(name = "database-output-config")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatabaseOutputConfig
{
    @XmlElement(name = "databaseName")
    private String databaseName;
    
    @XmlElement(name = "data-source")
    private String dataSource;

    @XmlElement(name = "update-sql")
    private String updateSql;

    @XmlElement(name = "flush-rows")
    private int flushRows;

    @XmlElement(name = "field-definition")
    private FieldDefinition fieldDefinition;

    @XmlElement(name = "sql-parameters")
    private SqlParameters sqlParameters;

    public String getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(String dataSource)
    {
        this.dataSource = dataSource;
    }

    public String getUpdateSql()
    {
        return updateSql;
    }

    public void setUpdateSql(String updateSql)
    {
        this.updateSql = updateSql;
    }

    public int getFlushRows()
    {
        return flushRows;
    }

    public void setFlushRows(int flushRows)
    {
        this.flushRows = flushRows;
    }

    public FieldDefinition getFieldDefinition()
    {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition)
    {
        this.fieldDefinition = fieldDefinition;
    }

    public SqlParameters getSqlParameters()
    {
        return sqlParameters;
    }

    public void setSqlParameters(SqlParameters sqlParameters)
    {
        this.sqlParameters = sqlParameters;
    }
    
	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
}
