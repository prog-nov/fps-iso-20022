package com.forms.ffp.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;

public class FFPObjectUtils
{
	private static Logger _logger = Logger.getLogger(FFPObjectUtils.class);

	public static Field resolveFieldByPath(String path, Class<?> clazz)
	{
		path = path.replaceAll("\\[[0-9]+\\]", "");

		logInfo(String.format("Resolve [%s] from [%s]", new Object[] { path, clazz.getSimpleName() }));

		String[] parts = path.split("\\.");
		try
		{
			Field result = getField(clazz, parts[0]);
			for (int i = 1; i < parts.length; i++)
			{
				Class<?> searchInClass = result.getType();
				if (searchInClass.isAssignableFrom(List.class))
				{
					ParameterizedType type = (ParameterizedType) result.getGenericType();
					searchInClass = (Class<?>) type.getActualTypeArguments()[0];
				}
				result = getField(searchInClass, parts[i]);
			}
			return result;
		} catch (Exception e)
		{
			logError(String.format("Failed to resolve [%s] due to %s - %s", new Object[] { path, e.getClass().getSimpleName(), e.getMessage() }), e);
		}
		return null;
	}

	public static Object resolveValueByPath(String path, Object obj)
	{
		String[] parts = path.split("\\.");
		try
		{
			for (int i = 0; i < parts.length; i++)
			{
				int searchIdx = -1;
				if (List.class.isAssignableFrom(obj.getClass()))
				{
					List<?> list = (List<?>) obj;
					obj = list.get(0);
				} else if (obj.getClass().isArray())
				{
					obj = ((Object[]) obj)[0];
				}
				if (parts[i].matches(".*\\[[0-9]+\\]"))
				{
					searchIdx = Integer.parseInt(parts[i].replaceAll(".*\\[([0-9]+)\\]", "$1"));
					parts[i] = parts[i].replaceAll("\\[[0-9]+\\]", "");
				}
				obj = getFieldValue(obj, obj.getClass(), parts[i]);
				if (searchIdx != -1)
				{
					if (List.class.isAssignableFrom(obj.getClass()))
					{
						List<?> list = (List<?>) obj;
						obj = list.get(searchIdx);
					} else if (obj.getClass().isArray())
					{
						obj = ((Object[]) obj)[searchIdx];
					}
				}
				if (obj == null)
				{
					return null;
				}
			}
			return obj;
		} catch (Exception e)
		{
			logError(String.format("Failed to resolve [%s] due to %s - %s", new Object[] { path, e.getClass().getSimpleName(), e.getMessage() }), e);
		}
		return null;
	}

	private static Field getField(Class<?> clazz, String fieldName)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			if (field == null)
			{
				if (clazz.getSuperclass() != null)
				{
					return getField(clazz.getSuperclass(), fieldName);
				}
				throw new Exception("Field not found.");
			}
			return field;
		} catch (Exception e)
		{
			logError(String.format("Failed to retrieve [%s] from class [%s] due to %s - %s", new Object[] { fieldName, clazz.getName(), e.getClass().getSimpleName(), e.getMessage() }), e);
		}
		return null;
	}

	private static Object getFieldValue(Object object, Class<?> clazz, String fieldName)
	{
		try
		{
			Field field = clazz.getDeclaredField(fieldName);
			if (field == null)
			{
				if (clazz.getSuperclass() != null)
				{
					return getFieldValue(object, clazz.getSuperclass(), fieldName);
				}
				throw new Exception("Field not found.");
			}
			field.setAccessible(true);
			Object result = field.get(object);
			field.setAccessible(false);

			return result;
		} catch (Exception e)
		{
			logError(String.format("Failed to retrieve [%s] from class [%s] due to %s - %s", new Object[] { fieldName, clazz.getName(), e.getClass().getSimpleName(), e.getMessage() }), e);
		}
		return null;
	}

	private static void logError(String str, Throwable t)
	{
		_logger.error(str, t);
	}

	private static void logInfo(String str)
	{
		_logger.info(str);
	}
}
