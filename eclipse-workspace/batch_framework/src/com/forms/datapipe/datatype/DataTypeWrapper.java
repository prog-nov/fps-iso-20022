package com.forms.datapipe.datatype;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import com.forms.datapipe.config.DataPipeConfigFactory;
import com.forms.datapipe.config.datatype.DataType;
import com.forms.datapipe.config.datatype.DataTypeConfig;
import com.forms.datapipe.exception.DataPipeException;

/**
 * 
 * 
 * @author cxl
 * 
 */
public class DataTypeWrapper
{
    private static DataTypeConfig config = null;

    public static void init(String configFile) throws DataPipeException
    {
        if (config != null)
            throw new DataPipeException(
                " [ DataTypeWrapper has been inited!  ] ");

        config = DataPipeConfigFactory.getDataTypeConfig(configFile);
    }

    private static DataType getDataType(String dataType)
        throws DataPipeException
    {
        if (config == null)
            throw new DataPipeException(" [ Call method 'init' first!  ] ");

        DataType d = config.getDataTypes().get(dataType);
        if (d == null)
            throw new DataPipeException(" [ Undefined datatype: " + dataType
                + "!  ] ");

        return d;
    }

    public static boolean validate(String dataType, String value, int length)
        throws DataPipeException
    {
        DataType dataTypeConfig = getDataType(dataType);
        String methodConfig = dataTypeConfig.getValidateMethod();
        return (Boolean) invokeTargetMethod(methodConfig, boolean.class, value,
            length);
    }

    public static String parse(String dataType, String value, int length)
        throws DataPipeException
    {
        DataType dataTypeConfig = getDataType(dataType);
        String methodConfig = dataTypeConfig.getParseMethod();
        return (String) invokeTargetMethod(methodConfig, String.class, value,
            length);
    }

    public static String print(String dataType, String value, int length)
        throws DataPipeException
    {
        DataType dataTypeConfig = getDataType(dataType);
        String methodConfig = dataTypeConfig.getPrintMethod();
        return (String) invokeTargetMethod(methodConfig, String.class, value,
            length);
    }

    private static Map<String, Method> methodCache = new HashMap<String, Method>();

    private static Object invokeTargetMethod(String methodConfig,
        Class<?> returnType, String value, int length) throws DataPipeException
    {
        Method method = methodCache.get(methodConfig);
        if (method == null)
        {
            int methodIndex = methodConfig.lastIndexOf(".");
            String className = methodConfig.substring(0, methodIndex);
            String methodName = methodConfig.substring(methodIndex + 1);
            try
            {
                Class<?> clazz = Class.forName(className);
                method = clazz.getMethod(methodName, String.class, int.class);
            } catch (Exception e)
            {
                throw new DataPipeException(e);
            }

            if (!Modifier.isStatic(method.getModifiers()))
                throw new DataPipeException(" [ Method '" + methodConfig
                    + "' must be static! ] ");

            if (!returnType.equals(method.getReturnType()))
                throw new DataPipeException(" [ The return type of method '"
                    + methodConfig + "' must be " + returnType.getName()
                    + "! ] ");
            
            synchronized (lock)
			{
            	methodCache.put(methodConfig, method);
			}
        }

        try
        {
            return method.invoke(null, value, length);
        } catch (Exception e)
        {
            throw new DataPipeException(e);
        }
    }
    
    private static Object lock = new Object();
}
