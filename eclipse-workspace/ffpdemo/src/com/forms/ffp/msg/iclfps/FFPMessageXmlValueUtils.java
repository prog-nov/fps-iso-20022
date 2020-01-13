package com.forms.ffp.msg.iclfps;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.ffp.msg.iclfps.bussiness.FFPBaseBussinessDataBean;
import com.forms.ffp.msg.iclfps.validation.FFPValidateLength;
import com.forms.ffp.utils.FFPObjectUtils;
import com.forms.ffp.utils.FFPStringUtils;

public class FFPMessageXmlValueUtils
{
	private static Logger _logger = LoggerFactory.getLogger(FFPMessageXmlValueUtils.class);

	public static XMLGregorianCalendar toGregorianDtType1(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static XMLGregorianCalendar toGregorianDtType2(Date date)
	{
		if (date == null)
		{
			return null;
		}
		XMLGregorianCalendar cal = null;
		try
		{
			SimpleDateFormat _dtType2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String fmtDate = _dtType2.format(date);
			cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(fmtDate);
			cal.setTimezone(Integer.MIN_VALUE);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return cal;
	}

	public static String getStringValue(String value)
	{
		if ((value == null) || (value.length() == 0))
		{
			return null;
		}
		return value;
	}

	public static Boolean isNullObject(Object... input)
	{
		Boolean isNullObject = Boolean.valueOf(true);
		Object[] arrayOfObject;
		int j = (arrayOfObject = input).length;
		for (int i = 0; i < j; i++)
		{
			Object o = arrayOfObject[i];

			isNullObject = Boolean.valueOf(isNullObject.booleanValue() & o == null);
		}
		return isNullObject;
	}

	public static String appendSequence(String prefix, int index, int length)
	{
		StringBuffer sb = new StringBuffer(prefix);
		sb.append(FFPStringUtils.padZero(index + 1, length - prefix.length()));
		return sb.toString();
	}

	public static String getGeneratedSequenceValue(FFPBaseBussinessDataBean tx, String fieldName, int idx)
	{
		try
		{
			String str = String.valueOf(FFPObjectUtils.resolveValueByPath(fieldName, tx));
			if ((isNullObject(new Object[] { str }).booleanValue()) || (tx.getAppendSequenceFields() == null) || (!tx.getAppendSequenceFields().contains(fieldName)))
			{
				return str;
			}
			Field field = FFPObjectUtils.resolveFieldByPath(fieldName, tx.getClass());
			FFPValidateLength lengths = (FFPValidateLength) field.getAnnotation(FFPValidateLength.class);
			if (lengths == null)
			{
				return String.format("%s-%s", new Object[] { str, Integer.valueOf(idx + 1) });
			}
			return appendSequence(str, idx, lengths.maxLength());
		} catch (Exception e)
		{
			_logger.error(String.format("Failed to getGeneratedSequence from class '%s' for field '%s' and index %s", new Object[] { tx.getClass().getSimpleName(), fieldName, Integer.valueOf(idx) }),
					e);
		}
		return null;
	}
}
