package com.forms.datapipe.util;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * 字符串处理类 提供一些常用的字符串处理工具
 * 
 * @author cxl
 *
 */

public final class StringUtils
{

    // ------------------------- encode ----------------------------

    /**
     * 把字符串从当前系统的默认编码转换成GBK编码
     * 
     * @param string
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFromLocalToGBK(String string)
        throws UnsupportedEncodingException
    {
        return encodeFromLocal(string, "GBK");
    }

    /**
     * 把字符串从当前系统的默认编码转换成指定的编码
     * 
     * @param string
     * @param targetCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFromLocal(String string, String targetCharset)
        throws UnsupportedEncodingException
    {
        return encode(string, System.getProperty("file.encoding"),
            targetCharset);
    }

    /**
     * 把字符串转码
     * 
     * @param string
     * @param srcCharset
     * @param targetCharset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String string, String srcCharset,
        String targetCharset) throws UnsupportedEncodingException
    {
        return new String(string.getBytes(srcCharset), targetCharset);
    }

    // --------------------------- join string ---------------------

    /**
     * 用指定的分隔符，连接字符串
     * 
     * @param strings -- 待连接的字符串数组
     * @param spacer -- 分隔符
     * @return
     */
    public static String join(String[] strings, String spacer)
    {
        return join(strings, "", "", spacer, "");
    }

    /**
     * 用指定的分隔符，连接字符串。字符串为：prefix + string + suffix。
     * 如果string包含filter，则忽列该字符串。
     * 
     * @param strings
     * @param prefix
     * @param suffix
     * @param spacer
     * @param filter
     * @return
     */
    public static String join(String[] strings, String prefix, String suffix,
        String spacer, String filter)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++)
        {
            if ("".equals(filter) || strings[i].indexOf(filter) == -1)
            {
                sb.append(prefix);
                sb.append(strings[i]);
                sb.append(suffix);
            } else
            {
                sb.append(strings[i]);
            }
            sb.append(spacer);
        }
        if (strings.length > 0)
        {
            sb.delete(sb.length() - spacer.length(), sb.length());
        }
        return sb.toString();
    }

    /**
     * 用指定的分隔符，连接指定次数的字符串。
     * 
     * @param number
     * @param string
     * @param spacer
     * @return
     */
    public static String join(int number, String string, String spacer)
    {
        return join(number, string, "", "", spacer);
    }

    /**
     * 连接字符串，string = prefix + string + suffix
     * 
     * @param number
     * @param string
     * @param prefix
     * @param suffix
     * @param spacer
     * @return
     */
    public static String join(int number, String string, String prefix,
        String suffix, String spacer)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < number; i++)
        {
            sb.append(prefix);
            sb.append(string);
            sb.append(suffix);
            sb.append(spacer);
        }
        if (number > 0)
        {
            sb.delete(sb.length() - spacer.length(), sb.length());
        }
        return sb.toString();
    }

    // --------------------- fill/unfill string -------------------------------

    /**
     * 填充字符串
     * 
     * 根据填充类型(fillType)把指定的字符串(src)用填充符(fillString)填充至指定的长度(length)
     * 
     * 示例1: String s = "a"; String result = StringDeal.fill("Right", s, "b", 5);
     * result的结果为abbbb
     * 
     * 注意: 1.若length 大于src.length() 则不填充 2.若填充符不是单个字符,则会出现如下情况: 示例2: String s =
     * "a"; String result = StringDeal.fill("Right", s, "bo", 6);
     * result的结果为abobob
     * 
     * 
     * @param fillType
     *            填充类型(Left or Right)
     * @param src
     *            要填充的字符串
     * @param fillString
     *            用来填充的字符串
     * @param length
     *            填充后的长度
     * @return
     */
    public static String fill(String fillType, String src, String fillString,
        int length) throws Exception
    {
        if (fillType == null || fillString == null || src == null)
        {
            throw new Exception(" [ At least one of parameters is null! ] ");
        }

        if ("Left".equalsIgnoreCase(fillType)
            && "Right".equalsIgnoreCase(fillType))
        {
            throw new Exception(" [ The fillType must be Right or Left! ] ");
        }

        int fillLength = length - src.length();
        if (fillLength == 0)
        {
            // not need to fill
            return src;
        } else if (fillLength < 0)
        {
            throw new Exception(
                " [ The src' length is longer than expected ! ] ");
        }

        StringBuffer sb = new StringBuffer();
        while (sb.length() < fillLength)
        {
            sb.append(fillString);
        }
        sb.delete(fillLength, sb.length());

        if ("Left".equalsIgnoreCase(fillType))
        {
            return sb + src;
        } else if ("Right".equalsIgnoreCase(fillType))
        {
            return src + sb;
        }

        return src;
    }

    /**
     * 去除填充符 与fill()正好相反,详见fill()的说明
     * 
     * @param fillType
     *            填充类型(Left or Right)
     * @param src
     *            被填充后的字符串
     * @param fillString
     *            用来填充的字符串
     * @return
     */
    public static String unFill(String fillType, String src, String fillString)
        throws Exception
    {
        if (fillType == null || fillString == null || src == null)
        {
            throw new Exception(" [ At least one of parameters is null! ] ");
        }

        // 判断填充类型
        if ("Left".equalsIgnoreCase(fillType)
            && "Right".equalsIgnoreCase(fillType))
        {
            throw new Exception(" [ The fillType must be Right or Left! ] ");
        }

        // 生成填充符
        StringBuffer sb = new StringBuffer();
        while (sb.length() < src.length())
        {
            sb.append(fillString);
        }

        // 去掉填充符
        if (fillType.equalsIgnoreCase("Left"))
        {
            for (int i = 0; i < src.length(); i++)
            {
                if (sb.charAt(i) != src.charAt(i))
                {
                    return src.substring(i, src.length());
                }
            }
        } else if (fillType.equalsIgnoreCase("Right"))
        {
            for (int i = src.length() - 1; i >= 0; i--)
            {
                if (sb.charAt(i) != src.charAt(i))
                {
                    return src.substring(0, i + 1);
                }
            }
        }
        return src;
    }

    // ---------------------- other ------------------------
    /**
     * Compare to current time, return true if it is before current time.
     * 
     * @param calendar
     * @param yearOffset
     * @param monthOffset
     * @param dayOffset
     * @param hourOffset
     * @param minuteOffset
     * @param secondOffset
     * @return
     */
    public static boolean beforeCurrentTime(Calendar calendar, int yearOffset,
        int monthOffset, int dayOffset, int hourOffset, int minuteOffset,
        int secondOffset)
    {
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.add(Calendar.YEAR, -yearOffset);
        currentCalendar.add(Calendar.MONTH, -monthOffset);
        currentCalendar.add(Calendar.DATE, -dayOffset);
        currentCalendar.add(Calendar.HOUR, -hourOffset);
        currentCalendar.add(Calendar.MINUTE, -minuteOffset);
        currentCalendar.add(Calendar.SECOND, -secondOffset);
        return currentCalendar.after(calendar);
    }

}
