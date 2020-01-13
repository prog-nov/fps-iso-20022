package com.forms.datapipe.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * utils for number
 * 
 * use DecimalFormat
 * 
 * @author cxl
 * 
 */
public class NumberUtils
{
    /**
     * 
     * @param number
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Number transform(Number number, Class clazz) throws Exception
    {
        if (clazz == Byte.class)
        {
            return new Byte(number.byteValue());
        } else if (clazz == Short.class)
        {
            return new Short(number.shortValue());
        } else if (clazz == Integer.class)
        {
            return new Integer(number.intValue());
        } else if (clazz == Long.class)
        {
            return new Long(number.longValue());
        } else if (clazz == Float.class)
        {
            return new Float(number.floatValue());
        } else if (clazz == Double.class)
        {
            return new Double(number.doubleValue());
        } else if (clazz == BigInteger.class)
        {
            return new BigInteger(number.toString());
        } else if (clazz == BigDecimal.class)
        {
            return new BigDecimal(number.toString());
        }

        throw new Exception("Unsupported type : " + clazz.getName());
    }

    // -------------------------parse/format--------------------------

    /**
     * 
     */
    public static String format(String numberString, String currentFormat,
        String newFormat) throws Exception
    {
        Number number = parse(numberString, currentFormat);
        return format(number, newFormat);
    }

    /**
     * 
     * @param numberString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Number parse(String numberString, String format)
        throws ParseException
    {
        if (format == null)
        {
            format = getDefaultFormat(numberString);
        }
        return new DecimalFormat(format).parse(numberString);
    }

    /**
     * 
     * @param number
     * @param format
     * @return
     * @throws Exception
     */
    public static String format(Number number, String format) throws Exception
    {
        if (format == null)
        {
            format = getDefaultFormat(number);
        }
        return new DecimalFormat(format).format(number);
    }

    /**
     * 
     * @param number
     * @return
     * @throws Exception
     */
    public static String getDefaultFormat(Number number) throws Exception
    {
        if ((number instanceof Byte) || (number instanceof Short)
            || (number instanceof Integer) || (number instanceof Long)
            || (number instanceof BigInteger))
        {
            return "#,##0";
        } else if ((number instanceof Float) || (number instanceof Double)
            || (number instanceof BigDecimal))
        {
            return "#,##0.00";
        }

        throw new Exception(" [ Unsupported type: "
            + number.getClass() + "! ");
    }

    /**
     * 
     * @param numberString
     * @return
     */
    public static String getDefaultFormat(String numberString)
    {
        numberString = numberString.trim();
        numberString = numberString.replaceAll("\\d", "#");
        return numberString;
    }
}
