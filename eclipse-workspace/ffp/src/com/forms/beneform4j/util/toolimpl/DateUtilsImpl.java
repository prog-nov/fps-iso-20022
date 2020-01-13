package com.forms.beneform4j.util.toolimpl;

import java.text.ParseException;
import java.util.Date;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日期工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class DateUtilsImpl {

    private static final DateUtilsImpl instance = new DateUtilsImpl() {};

    private DateUtilsImpl() {}

    public static void main(String[] args) throws ParseException {
        System.out.println(getInstance().dateCalculate("20160620", 0, 0, 128));
    }

    /**
     * 获取单实例
     * 
     * @return
     */
    public static DateUtilsImpl getInstance() {
        return instance;
    }

    /**
     * 获取当前日期，格式为平台配置中的BaseConfig.getDateFormat()，默认格式yyyyMMdd
     * 
     * @return
     */
    public String getDate() {
        return CoreUtils.getDate();
    }

    /**
     * 获取当前时间，格式为平台配置中的BaseConfig.getTimeFormat()，默认格式HH:mm:ss
     * 
     * @return
     */
    public String getTime() {
        return CoreUtils.getTime();
    }

    /**
     * 获取当前日期时间，格式为平台配置中的BaseConfig.getDatetimeFormat()，默认格式yyyyMMdd-HH:mm:ss
     * 
     * @return
     */
    public String getDateAndTime() {
        return CoreUtils.getDateAndTime();
    }

    /**
     * 日期时间格式化
     * 
     * @param date 日期对象，格式为平台配置中的BaseConfig.getDatetimeFormat()，默认格式yyyyMMdd-HH:mm:ss
     * @return 格式化后的日期字符串
     */
    public String getDateAndTime(Date date) {
        return CoreUtils.getDateAndTime(date);
    }

    /**
     * 日期时间格式化
     * 
     * @param date 日期对象
     * @param format 日期格式
     * @return 格式化后的日期字符串
     */
    public String getFormatDate(Date date, String format) {
        return CoreUtils.getFormatDate(date, format);
    }

    /**
     * 日期计算
     * 
     * @param date 日期字符串，格式为平台配置中的BaseConfig.getDateFormat()，默认格式yyyyMMdd
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后并且格式化的日期字符串
     */
    public String dateCalculate(String date, int mYear, int mMonth, int mDay) {
        return CoreUtils.dateCalculate(date, mYear, mMonth, mDay);
    }

    /**
     * 日期计算
     * 
     * @param date 日期字符串
     * @param format 日期格式
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后并且格式化的日期字符串
     */
    public String dateCalculate(String date, String format, int mYear, int mMonth, int mDay) {
        return CoreUtils.dateCalculate(date, format, mYear, mMonth, mDay);
    }

    /**
     * 日期计算
     * 
     * @param date 日期对象
     * @param mYear 需要增加的年数，如果需要减少，传入负数
     * @param mMonth 需要增加的月数，如果需要减少，传入负数
     * @param mDay 需要增加的日数，如果需要减少，传入负数
     * @return 计算后的日期对象
     */
    public Date dateCalculate(Date date, int mYear, int mMonth, int mDay) {
        return CoreUtils.dateCalculate(date, mYear, mMonth, mDay);
    }

    /**
     * 计算是当年的第几个日期
     * 
     * @param year 年份
     * @param month 月份
     * @param day 日数
     * @return 当年的第几个日期
     */
    public int dayOfYear(int year, int month, int day) {
        return CoreUtils.dayOfYear(year, month, day);
    }

    /**
     * 判断是否闰年
     * 
     * @param year 年份
     * @return 是否闰年
     */
    public boolean isLeapYear(int year) {
        return CoreUtils.isLeapYear(year);
    }

    /**
     * 获取月份的最大日期
     * 
     * @param year 年份
     * @param month 月份
     * @return 月份的最大日期
     */
    public int getMaxDayOfMonth(int year, int month) {
        return CoreUtils.getMaxDayOfMonth(year, month);
    }

    /**
     * 根据日期格式获取指定时间的毫秒数
     * 
     * @param datetime 日期时间
     * @param datetimeFormat 日期时间格式
     * @return 毫秒数
     */
    public long getTime(String datetime, String datetimeFormat) {
        return CoreUtils.getTime(datetime, datetimeFormat);
    }

    /**
     * 是否有效日期
     * 
     * @param date 字符串日期
     * @param format 目前支持YYYYMMDD、YYYY、YYYYMM、YYYY-MM-DD、YYYY/MM/DD这些格式
     * @return 是否有效日期
     */
    public boolean isValidDate(String date, String format) {
        boolean result = false;

        try {
            int year, month, day;
            int minYear = 1800, maxYear = 2200;
            if (null == date)
                return false;
            if ("YYYY".equalsIgnoreCase(format)) {
                date = date.trim();
                if (date.length() == 4) {
                    year = Integer.parseInt(date);
                    return year >= minYear && year <= maxYear;
                } else {
                    return false;
                }
            } else if ("YYYYMM".equalsIgnoreCase(format)) {
                date = date.trim();
                if (date.length() == 6) {
                    year = Integer.parseInt(date.substring(0, 4));
                    month = Integer.parseInt(date.substring(4));
                    return year >= minYear && year <= maxYear && month <= 12 && month >= 1;
                } else {
                    return false;
                }
            } else if ("YYYYMMDD".equalsIgnoreCase(format)) {
                date = date.trim();
                if (date.length() == 8) {
                    year = Integer.parseInt(date.substring(0, 4));
                    month = Integer.parseInt(date.substring(4, 6));
                    day = Integer.parseInt(date.substring(6));
                } else {
                    return false;
                }
            } else if ("YYYY-MM-DD".equalsIgnoreCase(format)) {
                date = date.trim();
                if (date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-') {
                    year = Integer.parseInt(date.substring(0, 4));
                    month = Integer.parseInt(date.substring(5, 7));
                    day = Integer.parseInt(date.substring(8));
                } else {
                    return false;
                }
            } else if ("YYYY/MM/DD".equalsIgnoreCase(format)) {
                date = date.trim();
                if (date.length() == 10 && date.charAt(4) == '/' && date.charAt(7) == '/') {
                    year = Integer.parseInt(date.substring(0, 4));
                    month = Integer.parseInt(date.substring(5, 7));
                    day = Integer.parseInt(date.substring(8));
                } else {
                    return false;
                }
            } else {
                year = Integer.parseInt(date.substring(0, 4));
                month = Integer.parseInt(date.substring(4, 6));
                day = Integer.parseInt(date.substring(6));
            }
            result = isValidDate(year, month, day);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 是否有效日期
     * 
     * @param year 年份
     * @param month 月份
     * @param day 天数
     * @return 是否有效日期
     */
    public boolean isValidDate(int year, int month, int day) {
        if ((month < 1) || (month > 12)) {
            return false;
        }
        int ml = getMaxDayOfMonth(year, month);
        if ((day < 1) || (day > ml)) {
            return false;
        }
        return true;
    }

    /**
     * 是否有效时间
     * 
     * @param hour 小时数
     * @param minute 分钟数
     * @param second 秒数
     * @param millisecond 毫秒数
     * @return 是否有效时间
     */
    public boolean isValidTime(int hour, int minute, int second, int millisecond) {
        if ((hour < 0) || (hour >= 24)) {
            return false;
        }
        if ((minute < 0) || (minute >= 60)) {
            return false;
        }
        if ((second < 0) || (second >= 60)) {
            return false;
        }
        if ((millisecond < 0) || (millisecond >= 1000)) {
            return false;
        }
        return true;
    }

    /**
     * 是否有效日期和时间
     * 
     * @param year 年份
     * @param month 月份
     * @param day 天数
     * @param hour 小时数
     * @param minute 分钟数
     * @param second 秒数
     * @param millisecond 毫秒数
     * @return 是否有效日期和时间
     */
    public boolean isValidDateTime(int year, int month, int day, int hour, int minute, int second, int millisecond) {
        return (isValidDate(year, month, day) && isValidTime(hour, minute, second, millisecond));
    }
}
