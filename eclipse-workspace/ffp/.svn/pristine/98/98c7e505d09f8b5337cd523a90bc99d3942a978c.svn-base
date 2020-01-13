package com.forms.ffp.core.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.forms.ffp.persistents.bean.ss.FFPSsCutoff;
import com.forms.ffp.persistents.service.ss.FFPIDaoService_Cutoff;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FFPCutoffUtils
{
	@Resource(name = "FFPDaoService_Cutoff")
	private FFPIDaoService_Cutoff daoService;
	
	@Autowired
	private FFPHolidayUtils holidayUtils;

	public boolean isCutoff(String ip_cutType) throws Exception
	{
		FFPSsCutoff cutoff = daoService.inquiry(ip_cutType);
		if (cutoff == null)
		{
			return false;
		} else
		{
			Calendar loc_cal = Calendar.getInstance();
			Calendar loc_cal2 = Calendar.getInstance();
			Calendar loc_cal3 = Calendar.getInstance();
			if (holidayUtils.isHoliday(loc_cal.getTime()))
			{
				loc_cal2.setTime(cutoff.getHolidayStart());
				loc_cal3.setTime(cutoff.getHolidayEnd());
			} else if (holidayUtils.isSaturday(loc_cal.getTime()))
			{
				loc_cal2.setTime(cutoff.getSatStart());
				loc_cal3.setTime(cutoff.getSatEnd());
			} else
			{
				loc_cal2.setTime(cutoff.getWorkdayStart());
				loc_cal3.setTime(cutoff.getWorkdayEnd());
			}

			SimpleDateFormat loc_sdf = new SimpleDateFormat(FFPDateUtils.INT_TIME_FORMAT);
			String loc_str1 = loc_sdf.format(loc_cal.getTime());
			String loc_str2 = loc_sdf.format(loc_cal2.getTime());
			String loc_str3 = loc_sdf.format(loc_cal3.getTime());
			return loc_str1.compareTo(loc_str2) < 0 || loc_str1.compareTo(loc_str3) >= 0;
		}
	}

	public Date nextStartDate(String ip_cutType) throws Exception
	{
		Calendar loc_curCal = Calendar.getInstance();
		FFPSsCutoff cutoff = daoService.inquiry(ip_cutType);
		Calendar compareTime = Calendar.getInstance();
		if (holidayUtils.isHoliday(loc_curCal.getTime()))
		{
			compareTime.setTime(cutoff.getHolidayStart());
		}
		else if (holidayUtils.isSaturday(loc_curCal.getTime()))
		{
			compareTime.setTime(cutoff.getSatStart());
		}
		else
		{
			compareTime.setTime(cutoff.getWorkdayStart());
		}
		compareTime.set(Calendar.YEAR, loc_curCal.get(Calendar.YEAR));
		compareTime.set(Calendar.MONTH, loc_curCal.get(Calendar.MONTH));
		compareTime.set(Calendar.DAY_OF_MONTH, loc_curCal.get(Calendar.DAY_OF_MONTH));
		
		SimpleDateFormat loc_sdf = new SimpleDateFormat(FFPDateUtils.INT_TIME_FORMAT);
		String loc_str1 = loc_sdf.format(loc_curCal.getTime());
		String loc_str2 = loc_sdf.format(compareTime.getTime());

		if(loc_str1.compareTo(loc_str2) < 0)
		{
			return compareTime.getTime();
		}
		else
		{
			compareTime.set(Calendar.DAY_OF_MONTH, compareTime.get(Calendar.DAY_OF_MONTH) + 1);
			return compareTime.getTime();
		}
	}
	
	public Date nextEndDate(String ip_cutType) throws Exception
	{
		Calendar loc_curCal = Calendar.getInstance();
		FFPSsCutoff cutoff = daoService.inquiry(ip_cutType);
		Calendar compareTime = Calendar.getInstance();
		if (holidayUtils.isHoliday(loc_curCal.getTime()))
		{
			compareTime.setTime(cutoff.getHolidayEnd());
		}
		else if (holidayUtils.isSaturday(loc_curCal.getTime()))
		{
			compareTime.setTime(cutoff.getSatEnd());
		}
		else
		{
			compareTime.setTime(cutoff.getWorkdayEnd());
		}
		compareTime.set(Calendar.YEAR, loc_curCal.get(Calendar.YEAR));
		compareTime.set(Calendar.MONTH, loc_curCal.get(Calendar.MONTH));
		compareTime.set(Calendar.DAY_OF_MONTH, loc_curCal.get(Calendar.DAY_OF_MONTH));
		
		SimpleDateFormat loc_sdf = new SimpleDateFormat(FFPDateUtils.INT_TIME_FORMAT);
		String loc_str1 = loc_sdf.format(loc_curCal.getTime());
		String loc_str2 = loc_sdf.format(compareTime.getTime());

		if(loc_str1.compareTo(loc_str2) < 0)
		{
			return compareTime.getTime();
		}
		else
		{
			compareTime.set(Calendar.DAY_OF_MONTH, compareTime.get(Calendar.DAY_OF_MONTH) + 1);
			return compareTime.getTime();
		}
	}
}
