package com.forms.datapipe.valve;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.forms.datapipe.config.input.Field;
import com.forms.datapipe.datatype.DataTypeWrapper;
import com.forms.datapipe.exception.DataPipeException;
import com.forms.datapipe.input.FieldData;

/**
 * validate and parse input field value
 * 
 * @author cxl
 * 
 */
public class FieldConverter
{
	private static Log log = LogFactory.getLog(FieldConverter.class);

	public static String parse(Field definition, String value)
			throws DataPipeException
	{
		String dataType = definition.getDataType();
		if (log.isDebugEnabled())
			log.debug(" [ dataType '" + dataType + "', value '" + value
					+ "'. ] ");

		// 1.validate
		if (log.isDebugEnabled())
			log.debug(" [ execute validate... ] ");
		try
		{
			boolean valid = DataTypeWrapper.validate(dataType, value,
					definition.getBytes());
			if (!valid)
				throw new DataPipeException(" [ Invalid field:"
						+ definition.getName() + " value: '" + value
						+ "' for datatype '" + dataType + "'.  ] ");
		} catch (Exception e)
		{
			throw new DataPipeException(" [ Invalid field:"
					+ definition.getName() + " value: '" + value
					+ "' for datatype '" + dataType + "'.  ] " + e.getMessage());
		}
		// 2.parse
		if (log.isDebugEnabled())
			log.debug(" [ execute parse... ] ");
		try
		{
			return DataTypeWrapper.parse(dataType, value, definition
					.getBytes());
		} catch (Exception e)
		{
			
			throw new DataPipeException(" [ parse field: '"
					+ definition.getName() + "' value: '" + value
					+ "' length:'" + definition.getBytes() + " 'for datatype '"
					+ dataType + "'.  ] " + e.getMessage());
		}
	}

	public static String parse(FieldData data) throws DataPipeException
	{
		String dataType = data.getDataType();
		String value = data.getValue();
		if (log.isDebugEnabled())
			log.debug(" [ dataType '" + dataType + "', value '" + value
					+ "'. ] ");

		// 1.validate
		if (log.isDebugEnabled())
			log.debug(" [ execute validate... ] ");
		boolean valid = DataTypeWrapper.validate(dataType, value, data
				.getBytes());
		if (!valid)
			throw new DataPipeException(" [ Invalid value '" + value
					+ "' for datatype '" + dataType + "'.  ] ");

		// 2.parse
		if (log.isDebugEnabled())
			log.debug(" [ execute parse... ] ");
		return DataTypeWrapper.parse(dataType, value, data.getBytes());
	}

	public static String print(Field definition, String value)
			throws DataPipeException
	{
		String dataType = definition.getDataType();
		if (log.isDebugEnabled())
			log.debug(" [ dataType '" + dataType + "', value '" + value
					+ "'. ] ");

		// 1.validate
		if (log.isDebugEnabled())
			log.debug(" [ execute validate... ] ");
		try
		{
			boolean valid = DataTypeWrapper.validate(dataType, value,
					definition.getBytes());
			if (!valid)
				throw new DataPipeException(" [ Invalid field:"
						+ definition.getName() + " value: '" + value
						+ "' for datatype '" + dataType + "'.  ] ");
		} catch (Exception e)
		{
			throw new DataPipeException(" [ Invalid field:"
					+ definition.getName() + " value: '" + value
					+ "' for datatype '" + dataType + "'.  ] " + e.getMessage());
		}
		// 2.print
		if (log.isDebugEnabled())
			log.debug(" [ execute print... ] ");
		try
		{
			return DataTypeWrapper
					.print(dataType, value, definition.getBytes());
		} catch (Exception e)
		{
			throw new DataPipeException(" [ print field: '"
					+ definition.getName() + "' value: '" + value
					+ "' length:'" + definition.getBytes() + " 'for datatype '"
					+ dataType + "'.  ] " + e.getMessage());
		}
	}
}
