package com.forms.batch.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.forms.ffp.persistents.bean.ss.FFPSsCutoff;
import com.forms.framework.log.BatchLogger;
import com.forms.framework.persistence.EntityManager;
import com.forms.framework.util.HolidayUtil;

public class CuttoffUtils
{
	private static BatchLogger _logger = BatchLogger.getLogger("ffp_batch", "CuttoffUtils", CuttoffUtils.class);
	
	private static Map<String, FFPSsCutoff> cutMap = new HashMap<String, FFPSsCutoff>();
	
	public static boolean isCutoff(String ip_cutType) throws Exception
	{
		if(cutMap.get(ip_cutType) == null)
		{
			getCutOffBean(ip_cutType);
		}
		
		FFPSsCutoff cutoff = cutMap.get(ip_cutType);
		if(cutoff == null)
		{
			return false;
		}
		else
		{
			Calendar loc_cal = Calendar.getInstance();
			Calendar loc_cal2 = Calendar.getInstance();
			Calendar loc_cal3 = Calendar.getInstance();
			if(HolidayUtil.isHoliday(loc_cal.getTime()))
			{
				loc_cal2.setTime(cutoff.getHolidayStart());
				loc_cal3.setTime(cutoff.getHolidayEnd());
			}
			else if(HolidayUtil.isSaturday(loc_cal.getTime()))
			{
				loc_cal2.setTime(cutoff.getSatStart());
				loc_cal3.setTime(cutoff.getSatEnd());
			}
			else
			{
				loc_cal2.setTime(cutoff.getWorkdayStart());
				loc_cal3.setTime(cutoff.getWorkdayEnd());
			}
			
			SimpleDateFormat loc_sdf = new SimpleDateFormat("HHmmss");
			String loc_str1 = loc_sdf.format(loc_cal.getTime());
			String loc_str2 = loc_sdf.format(loc_cal2.getTime());
			String loc_str3 = loc_sdf.format(loc_cal3.getTime());
			return loc_str1.compareTo(loc_str2) < 0 || loc_str1.compareTo(loc_str3) >= 0;
		}
	}
	
	private static void getCutOffBean(String ip_cutType)
	{
		try
		{
			String loc_sql = "SELECT WORKDAY_START, WORKDAY_END, SAT_START, SAT_END, HOLIDAY_START, HOLIDAY_END FROM TB_SS_CUTOFF WHERE CUTOFF_TYPE = ?";
			Map<String, Object> loc_map = EntityManager.queryMap(loc_sql, ip_cutType);
			if(loc_map != null)
			{	FFPSsCutoff cutoff = new FFPSsCutoff();
				cutoff.setCutoffType(ip_cutType);
				cutoff.setWorkdayStart((Date) loc_map.get("WORKDAY_START"));
				cutoff.setWorkdayEnd((Date) loc_map.get("WORKDAY_END"));
				cutoff.setSatStart((Date) loc_map.get("SAT_START"));
				cutoff.setSatEnd((Date) loc_map.get("SAT_END"));
				cutoff.setHolidayStart((Date) loc_map.get("HOLIDAY_START"));
				cutoff.setHolidayEnd((Date) loc_map.get("HOLIDAY_END"));
				cutMap.put(ip_cutType, cutoff);
			}
		} catch (Exception e)
		{
			_logger.error(e.getMessage());
		}
	}
}
