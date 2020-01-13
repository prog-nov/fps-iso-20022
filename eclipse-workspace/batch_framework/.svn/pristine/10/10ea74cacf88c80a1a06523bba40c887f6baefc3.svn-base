package com.forms.datapipe.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * util for class
 * 
 * @author cxl
 * 
 */
@SuppressWarnings("unchecked")
public final class ClassUtils
{
    // ------------------ invoke method ------------------------------

	/**
	 * 
	 */
    public static Object invokeFieldGetterMethod(Object obj, String fieldName)
        throws Exception
    {
        Method method = getFieldGetterMethod(obj.getClass(), fieldName);
        return method.invoke(obj);
    }

    /**
     * 
     * @param obj
     * @param fieldName
     * @param value
     * @throws Exception
     */
    public static void invokeFieldSetterMethod(Object obj, String fieldName,
        Object value) throws Exception
    {
        Method method = getFieldSetterMethod(obj.getClass(), fieldName,
            value.getClass());
        method.invoke(obj, new Object[] { value });
    }

    /**
     * 
     * @param obj
     * @param methodName
     * @param paras
     * @return
     * @throws Exception
     */
    public static Object invokeMethod(Object obj, String methodName,
        Object[] paras) throws Exception
    {
        return invokeMethod(obj, obj.getClass(), methodName, paras);
    }

    /**
     * 
     * @param clazz
     * @param methodName
     * @param paras
     * @return
     * @throws Exception
     */
    public static Object invokeStaticMethod(Class clazz, String methodName,
        Object[] paras) throws Exception
    {
        return invokeMethod(null, clazz, methodName, paras);
    }

    /**
     * 
     * @param obj
     * @param clazz
     * @param methodName
     * @param paras
     * @return
     * @throws Exception
     */
    public static Object invokeMethod(Object obj, Class clazz,
        String methodName, Object[] paras) throws Exception
    {
        Class[] paraTypes = null;
        if (paras != null)
        {
            paraTypes = new Class[paras.length];
            for (int i = 0; i < paras.length; i++)
            {
                paraTypes[i] = paras[i].getClass();
            }
        }
        Method method = getMethod(clazz, methodName, paraTypes);
        return method.invoke(obj, paras);
    }

    // -------------------- get field -----------------------------

    /**
     * 
     */
	public static Field[] getDeclaredFields(Class clazz)
    {
        Map map = getDeclaredFields0(clazz);
        return (Field[]) map.values().toArray(new Field[map.size()]);
    }

	/**
	 * 
	 * @param clazz
	 * @return
	 */
    private static Map getDeclaredFields0(Class clazz)
    {
        Map map = new HashMap();
        getDeclaredFields(map, clazz);
        return map;
    }

    /**
     * 
     * @param map
     * @param clazz
     */
    private static void getDeclaredFields(Map map, Class clazz)
    {
        // get super class
        Class superclass = clazz.getSuperclass();
        if (superclass != null)
        {
            getDeclaredFields(map, superclass);
        }

        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++)
        {
            getDeclaredFields(map, interfaces[i]);
        }

        // get self
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            map.put(fields[i].getName(), fields[i]);
        }
    }

    /**
     * 
     * @param clazz
     * @param fieldName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(Class clazz, String fieldName)
        throws NoSuchFieldException
    {
        Map map = getDeclaredFields0(clazz);
        Field field = (Field) map.get(fieldName);
        if (field == null) throw new NoSuchFieldException(fieldName);

        return field;
    }

    // ----------------- get method -------------------------------

    /**
     * 
     */
    public static Method getFieldGetterMethod(Class clazz, String fieldName)
        throws Exception
    {
        try
        {
            Field field = getField(clazz, fieldName);
            String prefix = "get";
            if (field.getType() == boolean.class)
            {
                prefix = "is";
            }
            return getFieldMethod(clazz, prefix, fieldName, null);
        } catch (Exception nsme)
        {
            return getFieldMethod(clazz, "get", fieldName, null);
        }
    }

    /**
     * 
     * @param clazz
     * @param fieldName
     * @param paraType
     * @return
     * @throws Exception
     */
    public static Method getFieldSetterMethod(Class clazz, String fieldName,
        Class paraType) throws Exception
    {
        return getFieldMethod(clazz, "set", fieldName, new Class[] { paraType });
    }

    /**
     * 
     * @param clazz
     * @param prefix
     * @param fieldName
     * @param paraTypes
     * @return
     * @throws Exception
     */
    public static Method getFieldMethod(Class clazz, String prefix,
        String fieldName, Class[] paraTypes) throws Exception
    {
        String methodName = prefix;
        if (fieldName.length() < 1)
        {
            throw new IllegalArgumentException(" [ fieldName is empty! ] ");
        }
        methodName += Character.toUpperCase(fieldName.charAt(0));
        if (fieldName.length() > 1)
        {
            methodName += fieldName.substring(1, fieldName.length());
        }
        return getMethod(clazz, methodName, paraTypes);
    }

    /**
     * 
     * @param clazz
     * @param methodName
     * @param paraTypes
     * @return
     * @throws Exception
     */
    public static Method getMethod(Class clazz, String methodName,
        Class[] paraTypes) throws Exception
    {
        return (Method) getOptimalMember(clazz, "method", methodName, paraTypes);
    }

    // ----------------- new instance ---------------------------------

    /**
     * 
     */
    public static Object newInstance(String className, Object[] paras)
        throws Exception
    {
        return newInstance(Class.forName(className), paras);
    }

    /**
     * 
     * @param clazz
     * @param paras
     * @return
     * @throws Exception
     */
    public static Object newInstance(Class clazz, Object[] paras)
        throws Exception
    {
        Class[] paraTypes = null;
        if (paras != null)
        {
            paraTypes = new Class[paras.length];
            for (int i = 0; i < paras.length; i++)
            {
                paraTypes[i] = paras[i].getClass();
            }
        }
        Constructor constructor = getConstructor(clazz, paraTypes);
        return constructor.newInstance(paras);
    }

    /**
     * 
     * @param clazz
     * @param paraTypes
     * @return
     * @throws Exception
     */
    public static Constructor getConstructor(Class clazz, Class[] paraTypes)
        throws Exception
    {
        return (Constructor) getOptimalMember(clazz, "constructor",
            clazz.getName(), paraTypes);
    }

    /**
     * 
     * @param clazz
     * @param memberType
     * @param memberName
     * @param paraTypes
     * @return
     * @throws Exception
     */
    private static Member getOptimalMember(Class clazz, String memberType,
        String memberName, Class[] paraTypes) throws Exception
    {
        if (paraTypes == null) paraTypes = new Class[0];

        Member[] members = getMembers(clazz, memberType);
        Method method = null;
        Member optimal = null;
        outer: for (int i = 0; i < members.length; i++)
        {
            if (method == null)
                method = members[i].getClass().getMethod("getParameterTypes");

            if (!members[i].getName().equals(memberName)) continue;

            Class[] clazzes = null;
            try
            {
                clazzes = (Class[]) method.invoke(members[i]);
            } catch (Exception e)
            {
            }
            if (clazzes.length != paraTypes.length) continue;

            for (int j = 0; j < clazzes.length; j++)
            {
                clazzes[j] = toObjectType(clazzes[j]);
                paraTypes[j] = toObjectType(paraTypes[j]);
                if (paraTypes[j] != null
                    && !clazzes[j].isAssignableFrom(paraTypes[j]))
                    continue outer;
            }

            if (optimal == null)
            {
                optimal = members[i];
            } else
            {
                for (int j = 0; j < clazzes.length; j++)
                {
                    Class[] clazzes0 = null;
                    try
                    {
                        clazzes0 = (Class[]) method.invoke(optimal);
                    } catch (Exception e)
                    {
                    }
                    clazzes0[j] = toObjectType(clazzes0[j]);
                    if (clazzes0[j].isAssignableFrom(clazzes[j]))
                    {
                        optimal = members[i];
                        break;
                    }
                }
            }
        }

        if (optimal != null) return optimal;

        StringBuffer sb = new StringBuffer();
        sb.append(clazz.getName());
        sb.append(".");
        sb.append(memberName);
        sb.append("(");
        for (int i = 0; i < paraTypes.length; i++)
        {
            sb.append(paraTypes[i].getName());
            sb.append(" arg");
            sb.append(i);
            if (i != paraTypes.length - 1)
            {
                sb.append(", ");
            }
        }
        sb.append(")");
        throw new NoSuchMethodException(sb.toString());
    }

    /**
     * @param clazz
     * @param memberType
     * @return
     * @throws Exception 
     */
    private static Member[] getMembers(Class clazz, String memberType) throws Exception
    {
        if ("constructor".equals(memberType))
        {
            return clazz.getConstructors();
        } else if ("method".equals(memberType))
        {
            return clazz.getMethods();
        } else
        {
            throw new Exception("Unsupported type : " + memberType);
        }
    }

    /**
     * 
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Class toObjectType(Class clazz) throws Exception
    {
        if (clazz == null || !clazz.isPrimitive()) return clazz;

        if (boolean.class == clazz)
        {
            return Boolean.class;
        } else if (char.class == clazz)
        {
            return Character.class;
        } else if (byte.class == clazz)
        {
            return Byte.class;
        } else if (short.class == clazz)
        {
            return Short.class;
        } else if (int.class == clazz)
        {
            return Integer.class;
        } else if (long.class == clazz)
        {
            return Long.class;
        } else if (float.class == clazz)
        {
            return Float.class;
        } else if (double.class == clazz)
        {
            return Double.class;
        } else if (void.class == clazz)
        {
            return Void.class;
        } else
        {
            throw new Exception("Unsupported type : " + clazz.getName());
        }
    }

}
