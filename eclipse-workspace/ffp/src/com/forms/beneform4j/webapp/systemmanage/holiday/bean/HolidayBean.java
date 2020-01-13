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

    private static final long serialVersionUID = -430953396432845692L;

    private String solarDate;

    private String isHolidayDay;

    private String holidayDesc;
    
	private int year;

	private int month;

	private int day;

    public String getSolarDate() {
        return this.solarDate;
    }

    public void setSolarDate(String solarDate) {
        this.solarDate = solarDate;
    }

    public String getIsHolidayDay() {
        return this.isHolidayDay;
    }

    public void setIsHolidayDay(String isHolidayDay) {
        this.isHolidayDay = isHolidayDay;
    }

    public String getHolidayDesc() {
        return this.holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }
    
    public int getYear()
	{
		return year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public int getMonth()
	{
		return month;
	}

	public void setMonth(int month)
	{
		this.month = month;
	}

	public int getDay()
	{
		return day;
	}

	public void setDay(int day)
	{
		this.day = day;
	}

}
