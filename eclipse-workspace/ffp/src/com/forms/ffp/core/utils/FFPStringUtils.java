package com.forms.ffp.core.utils;

public class FFPStringUtils
{
    public static boolean isEmptyOrNull(String data)
    {
	return (data == null) || (data.trim().length() == 0);
    }

    public static boolean isEmptyOrNullForAll(String... data)
    {
	if (data == null)
	    return true;
	for (int i = 0; i < data.length; i++)
	{
	    if (!isEmptyOrNull(data[i]))
	    {
		return false;
	    }
	}
	return true;
    }

    public static String lpad(String str, char padChar, int length)
    {
	if (str == null)
	{
	    return null;
	}
	StringBuffer sb = new StringBuffer();
	for (int i = str.length(); i < length; i++)
	{
	    sb.append(padChar);
	}

	sb.append(str);

	return sb.toString();
    }

    public static String rpad(String str, char padChar, int length)
    {
	if (str == null)
	{
	    return null;
	}
	StringBuffer sb = new StringBuffer(str);
	for (int i = str.length(); i < length; i++)
	{
	    sb.append(padChar);
	}

	return sb.toString();
    }

    public static String padZero(int value, int length)
    {
	return lpad(String.valueOf(value), '0', length);
    }
    
    public static String padZeroLong(long value, int length)
    {
    	return lpad(String.valueOf(value), '0', length);
    }
    
	public static String getStringValue(String value)
	{
		if ((value == null) || (value.length() == 0))
		{
			return null;
		}
		return value;
	}
}
