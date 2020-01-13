package com.forms.datapipe.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * copy a javabean's field value to another javabean
 * 
 * @author cxl
 * 
 */
public final class BeanUtils
{
    /**
     * surpport form -> bean, bean -> form, bean -> bean, form -> form
     * 
     * @param from --
     *            the object which copy from
     * @param to --
     *            the object which copy to
     * @throws Exception 
     * @see transform(Object obj, Class clazz)
     */
    public static void populate(Object from, Object to) throws Exception
    {
        Field[] fieldsFrom = ClassUtils.getDeclaredFields(from.getClass());
        Field[] fieldsTo = ClassUtils.getDeclaredFields(to.getClass());
        outer: for (int i = 0; i < fieldsFrom.length; i++)
        {
            // skip static member
            if (Modifier.isStatic(fieldsFrom[i].getModifiers())) continue;

            String nameFrom = fieldsFrom[i].getName();
            for (int j = 0; j < fieldsTo.length; j++)
            {
                String nameTo = fieldsTo[j].getName();
                if (nameFrom.equals(nameTo))
                {
                    // get getter/setter method
                    Method getter = null, setter = null;
                    try
                    {
                        getter = ClassUtils.getFieldGetterMethod(
                            from.getClass(), nameFrom);
                        setter = ClassUtils.getFieldSetterMethod(to.getClass(),
                            nameFrom, fieldsTo[j].getType());
                    } catch (NoSuchMethodException nsme)
                    {
                        continue outer;
                    }

                    // get value
                    Object value = getter.invoke(from);

                    value = transform(value, fieldsTo[j].getType());
                    if (value == null) continue outer;

                    // set value
                    setter.invoke(to, new Object[] { value });
                    continue outer;
                }
            }
        }
    }

    /**
     * 
     * transform data type
     * 
     * 
     * @param obj --
     *            the object which need to transform data type
     * @param clazz --
     *            data type
     * @return if the clazz not surpported, return null
     * @throws Exception 
     * @see transform(String string, Class clazz)
     */
    public static Object transform(Object obj, Class clazz)
        throws Exception
    {
        if (obj == null) return null;

        clazz = ClassUtils.toObjectType(clazz);
        if (clazz.isInstance(obj)) return obj;

        if (clazz == String.class)
        {
            return obj.toString();
        } else
        {
            return transform(obj.toString(), clazz);
        }
    }

    /**
     * transform String to String, Number, Date, Boolean and sub class
     * 
     * @param string
     * @param clazz
     * @return
     * @throws Exception 
     * @see NumberUtils.transform(Number number, Class clazz)
     * @see DateUtils.transform(Date date, Class clazz)
     */
    private static Object transform(String string, Class clazz)
        throws Exception
    {
        if ("".equals(string)) return null;

        if (Number.class.isAssignableFrom(clazz))
        {
            Number number = NumberUtils.parse(string, null);
            return NumberUtils.transform(number, clazz);
        } else if (Date.class.isAssignableFrom(clazz))
        {
            Date date = DateUtils.parse(string, null);
            return DateUtils.transform(date, clazz);
        } else if (Boolean.class == clazz)
        {
            return new Boolean(string);
        } else
        {
            // unsupported type
            return null;
        }
    }
}
