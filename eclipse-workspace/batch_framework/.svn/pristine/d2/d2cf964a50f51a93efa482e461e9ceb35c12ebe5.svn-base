package com.forms.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.forms.framework.env.BatchEnvBuilder;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.persistence.ConnectionManager;
import com.forms.framework.persistence.EntityManager;

public class HolidayUtil
{
	private static BatchLogger logger = BatchLogger.getLogger("HolidayUtil", "HolidayUtil", HolidayUtil.class);
	
	private static String HOLIDAY_TABLE = null;
	
	static
	{
		try
		{
			HOLIDAY_TABLE = BatchEnvBuilder.getInstance().getParamenter(CommonAPI.PARAMETER_HOLIDAY_TABLE);
		}
		catch(Exception ip_e)
		{
			logger.error(ip_e.getMessage());
		}
	}
	
	public static boolean isSaturday(Date ip_date) throws Exception
	{
		if(ip_date == null)
		{
			throw new Exception("input date=null");
		}
		Calendar loc_cal = Calendar.getInstance();
		loc_cal.setTime(ip_date);
		return Calendar.SATURDAY == loc_cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static boolean isHoliday(Date ip_date) throws Exception
	{
		if(ip_date == null)
		{
			throw new Exception("input date=null");
		}
		
		SimpleDateFormat loc_sdf = new SimpleDateFormat("yyyyMMdd");
		String key = loc_sdf.format(ip_date);
		
		String loc_schema = ConnectionManager.getInstance().getDefaultSchema();
		String loc_sql = "SELECT IS_HOLIDAY_DAY FROM " + loc_schema + "." + HOLIDAY_TABLE + " WHERE SOLAR_DATE = ?";
		Object[] loc_obj = EntityManager.queryArray(loc_sql, key);
		
		if(loc_obj == null)
		{
			return false;
		}
		else
		{
			return Integer.valueOf((String)loc_obj[0]) == 1;
		}
	}
}
