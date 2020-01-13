package com.forms.beneform4j.webapp.systemmanage.holiday.bean;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日历信息Bean <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20 <br>
 */
public class HolidayBean implements java.io.Serializable {

    /**
     * 版本号
     */
    private static final long serialVersionUID = -430953396432845692L;

    /**
     * 阳历日期
     */
    private String solarDate;

    /**
     * 农历年
     */
    private String lunarYear;

    /**
     * 农历月（闰月为12+闰月数）
     */
    private String lunarMonth;

    /**
     * 农历日
     */
    private String lunarDay;

    /**
     * 农历日期描述
     */
    private String lunarDesc;

    /**
     * 星期
     */
    private String week;

    /**
     * 星座
     */
    private String constellation;

    /**
     * 月初日期
     */
    private String monthBegin;

    /**
     * 月末日期
     */
    private String monthEnd;

    /**
     * 当年第几天
     */
    private Integer yearDays;

    /**
     * 当月第几天
     */
    private Integer monthDays;

    /**
     * 是否节假日
     */
    private Integer isHolidayDay;

    /**
     * 节假日描述
     */
    private String holidayDesc;

    /**
     * 是否月末
     */
    private Integer isMonthEnd;

    /**
     * 获取阳历日期
     * 
     * @return 阳历日期
     */
    public String getSolarDate() {
        return this.solarDate;
    }

    /**
     * 设置阳历日期
     * 
     * @param solarDate 阳历日期
     */
    public void setSolarDate(String solarDate) {
        this.solarDate = solarDate;
    }

    /**
     * 获取农历年
     * 
     * @return 农历年
     */
    public String getLunarYear() {
        return this.lunarYear;
    }

    /**
     * 设置农历年
     * 
     * @param lunarYear 农历年
     */
    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    /**
     * 获取农历月（闰月为12+闰月数）
     * 
     * @return 农历月（闰月为12+闰月数）
     */
    public String getLunarMonth() {
        return this.lunarMonth;
    }

    /**
     * 设置农历月（闰月为12+闰月数）
     * 
     * @param lunarMonth 农历月（闰月为12+闰月数）
     */
    public void setLunarMonth(String lunarMonth) {
        this.lunarMonth = lunarMonth;
    }

    /**
     * 获取农历日
     * 
     * @return 农历日
     */
    public String getLunarDay() {
        return this.lunarDay;
    }

    /**
     * 设置农历日
     * 
     * @param lunarDay 农历日
     */
    public void setLunarDay(String lunarDay) {
        this.lunarDay = lunarDay;
    }

    /**
     * 获取农历日期描述
     * 
     * @return 农历日期描述
     */
    public String getLunarDesc() {
        return this.lunarDesc;
    }

    /**
     * 设置农历日期描述
     * 
     * @param lunarDesc 农历日期描述
     */
    public void setLunarDesc(String lunarDesc) {
        this.lunarDesc = lunarDesc;
    }

    /**
     * 获取星期
     * 
     * @return 星期
     */
    public String getWeek() {
        return this.week;
    }

    /**
     * 设置星期
     * 
     * @param week 星期
     */
    public void setWeek(String week) {
        this.week = week;
    }

    /**
     * 获取星座
     * 
     * @return 星座
     */
    public String getConstellation() {
        return this.constellation;
    }

    /**
     * 设置星座
     * 
     * @param constellation 星座
     */
    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    /**
     * 获取月初日期
     * 
     * @return 月初日期
     */
    public String getMonthBegin() {
        return this.monthBegin;
    }

    /**
     * 设置月初日期
     * 
     * @param monthBegin 月初日期
     */
    public void setMonthBegin(String monthBegin) {
        this.monthBegin = monthBegin;
    }

    /**
     * 获取月末日期
     * 
     * @return 月末日期
     */
    public String getMonthEnd() {
        return this.monthEnd;
    }

    /**
     * 设置月末日期
     * 
     * @param monthEnd 月末日期
     */
    public void setMonthEnd(String monthEnd) {
        this.monthEnd = monthEnd;
    }

    /**
     * 获取当年第几天
     * 
     * @return 当年第几天
     */
    public Integer getYearDays() {
        return this.yearDays;
    }

    /**
     * 设置当年第几天
     * 
     * @param yearDays 当年第几天
     */
    public void setYearDays(Integer yearDays) {
        this.yearDays = yearDays;
    }

    /**
     * 获取当月第几天
     * 
     * @return 当月第几天
     */
    public Integer getMonthDays() {
        return this.monthDays;
    }

    /**
     * 设置当月第几天
     * 
     * @param monthDays 当月第几天
     */
    public void setMonthDays(Integer monthDays) {
        this.monthDays = monthDays;
    }

    /**
     * 获取是否节假日
     * 
     * @return 是否节假日
     */
    public Integer getIsHolidayDay() {
        return this.isHolidayDay;
    }

    /**
     * 设置是否节假日
     * 
     * @param isHolidayDay 是否节假日
     */
    public void setIsHolidayDay(Integer isHolidayDay) {
        this.isHolidayDay = isHolidayDay;
    }

    /**
     * 获取节假日描述
     * 
     * @return 节假日描述
     */
    public String getHolidayDesc() {
        return this.holidayDesc;
    }

    /**
     * 设置节假日描述
     * 
     * @param holidayDesc 节假日描述
     */
    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    /**
     * 获取是否月末
     * 
     * @return 是否月末
     */
    public Integer getIsMonthEnd() {
        return this.isMonthEnd;
    }

    /**
     * 设置是否月末
     * 
     * @param isMonthEnd 是否月末
     */
    public void setIsMonthEnd(Integer isMonthEnd) {
        this.isMonthEnd = isMonthEnd;
    }
}
