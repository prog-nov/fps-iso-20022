package com.forms.datapipe.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * utils for Date
 * 
 * use SimpleDateFormat
 * 
 * @author cxl
 * 
 */
public class DateUtils
{

    // -----------------------calc---------------------------

	/**
	 * 
	 */
    public static int betweenDays(String dateString1, String dateString2,
        String format) throws Exception
    {
        return betweenDays(parse(dateString1, format), parse(dateString2,
            format));
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int betweenDays(Date date1, Date date2)
    {
        long balance = date1.getTime() - date2.getTime();
        balance = balance / (24 * 60 * 60 * 1000);
        return (int) balance;
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int betweenMonths(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);

        return (year1 - year2) * 12 + month1 - month2;
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int betweenYears(Date date1, Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        return (year1 - year2);
    }

    /**
     * 
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, seconds);
        return c.getTime();
    }

    /**
     * 
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }

    /**
     * 
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

    /**
     * 
     * @param dateString
     * @param format
     * @param days
     * @return
     * @throws Exception
     */
    public static String addDays(String dateString, String format, int days)
        throws Exception
    {
        Date date = addDays(parse(dateString, format), days);
        return format(date, format);
    }

    /**
     * 
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }

    /**
     * 
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, months);
        return c.getTime();
    }

    /**
     * 
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, years);
        return c.getTime();
    }

    /**
     * 
     * @param date
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Date transform(Date date, Class clazz) throws Exception
    {
        if (clazz == java.util.Date.class)
        {
            return new java.util.Date(date.getTime());
        } else if (clazz == java.sql.Date.class)
        {
            return new java.sql.Date(date.getTime());
        } else if (clazz == java.sql.Time.class)
        {
            return new java.sql.Time(date.getTime());
        }
        throw new Exception("Unsupported type : " +  clazz.getName());
    }

    // -------------------------parse/format--------------------------

    /**
     * 
     */
    public static String format(String dateString, String currentFormat,
        String newFormat) throws Exception
    {
        Date date = parse(dateString, currentFormat);
        return format(date, newFormat);
    }

    /**
     * 
     * @param date
     * @param format
     * @return
     * @throws Exception
     */
    public static String format(Date date, String format) throws Exception
    {
        if (format == null)
        {
            format = getDefaultFormat(date);
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 
     * @param dateString
     * @param format
     * @return
     * @throws Exception
     */
    public static Date parse(String dateString, String format)
        throws Exception
    {
        if (format == null)
        {
            format = getDefaultFormat(dateString);
        }
        return new SimpleDateFormat(format).parse(dateString);
    }

    /**
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static String getDefaultFormat(Date date) throws Exception
    {
        if (date.getClass() == java.util.Date.class)
        {
            return "yyyy-MM-dd";
        } else if (date.getClass() == java.sql.Date.class)
        {
            return "yyyy-MM-dd";
        } else if (date.getClass() == java.sql.Time.class)
        {
            return "HH:mm:ss";
        }

        throw new Exception(" [ Unsupported type: "
            + date.getClass() + "! ");
    }

    /**
     * 
     * @param dateString
     * @return
     * @throws Exception
     */
    public static String getDefaultFormat(String dateString) throws Exception
    {
        dateString = dateString.trim();
        if (dateString.indexOf(" ") > -1)
        {
            String[] array = dateString.split(" ");
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++)
            {
                sb.append(getDefaultFormat(array[i]));
                if (i != array.length - 1)
                {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }

        boolean pureDigit = dateString.matches("^\\d+$");
        if (pureDigit)
        {
            if (dateString.length() == 3)
            {
                return "SSS";
            } else if (dateString.length() == 6)
            {
                return "HHmmss";
            } else if (dateString.length() == 8)
            {
                return "yyyyMMdd";
            } else if (dateString.length() == 9)
            {
                return "HHmmssSSS";
            }
        } else
        {
            if (dateString.matches("^\\d{2}.\\d{2}.\\d{2}$"))
            {
                char separator = dateString.charAt(2);
                return ("HH" + separator + "mm" + separator + "ss");
            } else if (dateString.matches("^\\d{4}.\\d{2}.\\d{2}$"))
            {
                char separator = dateString.charAt(4);
                return ("yyyy" + separator + "MM" + separator + "dd");
            } else if (dateString.matches("^\\d{6}.\\d{3}$"))
            {
                char separator = dateString.charAt(6);
                return ("HHmmss" + separator + "SSS");
            } else if (dateString.matches("^\\d{2}.\\d{2}.\\d{2}.\\d{3}$"))
            {
                char separator = dateString.charAt(2);
                char separator1 = dateString.charAt(8);
                return ("HH" + separator + "mm" + separator + "ss" + separator1 + "SSS");
            }
        }

        throw new Exception(" [ No suitable default format for: "
            + dateString + "! ");
    }
}
