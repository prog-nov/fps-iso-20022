package com.forms.ffp.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.forms.beneform4j.webapp.systemmanage.holiday.service.HolidayService;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FFPHolidayUtils
{
	@Resource(name = "holidayService")
	private HolidayService holidayService;

	public boolean isSaturday(Date ip_date) throws Exception
	{
		if (ip_date == null)
		{
			throw new Exception("input date=null");
		}
		Calendar loc_cal = Calendar.getInstance();
		loc_cal.setTime(ip_date);
		return Calendar.SATURDAY == loc_cal.get(Calendar.DAY_OF_WEEK);
	}

	public boolean isHoliday(Date ip_date) throws Exception
	{
		if (ip_date == null)
		{
			throw new Exception("input date=null");
		}

		Calendar loc_cal = Calendar.getInstance();
		loc_cal.setTime(ip_date);
		SimpleDateFormat loc_sdf = new SimpleDateFormat(FFPDateUtils.INT_DATE_FORMAT);
		String loc_date = loc_sdf.format(ip_date);
		return holidayService.isHolidayDay(loc_date);
	}
}
