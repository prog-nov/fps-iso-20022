package com.forms.framework.persistence;

import javax.sql.DataSource;

import com.forms.framework.exception.BatchFrameworkException;

public class DataSourceForDataPipe
{

	public DataSourceForDataPipe()
	{

	}

	public static DataSource getDataSource() throws BatchFrameworkException
	{
		return ConnectionManager.getInstance().getDataSource();
	}
	
	public static DataSource getDataSource(String databaseName) throws BatchFrameworkException
	{
		if(null == databaseName || "".equals(databaseName))
		{
			return ConnectionManager.getInstance().getDataSource();
		}
		return ConnectionManager.getInstance(databaseName).getDataSource();
	}
	
	public static String getSchema(String databaseName) throws BatchFrameworkException
	{
		if(null == databaseName || "".equals(databaseName))
		{
			return ConnectionManager.getInstance().getDefaultSchema();
		}
		return ConnectionManager.getInstance(databaseName).getDefaultSchema();
	}
}
