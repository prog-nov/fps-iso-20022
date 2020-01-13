package com.forms.ffp.core.utils;

public class FFPValidateUtils
{
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
}
