package com.forms.framework.util;

import java.text.ParseException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * sqlJob parameter util
 * 
 * @author ahnan createDate:2011-04-28 updateDate:2011-04-28
 */
public class PatternUtil
{
	public static final String PATTERN_STRING = "#\\{.*?}";

	public static final Pattern PATTERN = Pattern.compile(PATTERN_STRING,
			Pattern.DOTALL);

	public String batchAcDate = null;

	public String timePattern = "HHmmss";

	public PatternUtil(String batchAcDate)
	{
		this.batchAcDate = batchAcDate;
	}

	/**
	 * replace sql parameters
	 * 
	 * @param ip_sql
	 * @return
	 * @throws ParseException
	 */
	public String patternReplace(Map<String, String> ip_map, String ip_sql)
			throws Exception
	{
		StringBuffer loc_sb = new StringBuffer();
		Matcher loc_m = PATTERN.matcher(ip_sql);
		int loc_lastIndex = 0;
		while (loc_m.find())
		{
			String loc_tag = loc_m.group();
			String loc_name = loc_tag.substring(2, loc_tag.length() - 1);
			String loc_value = paraValue(ip_map, loc_name);

			loc_sb.append(ip_sql.substring(loc_lastIndex, loc_m.start()));
			loc_sb.append(loc_value);
			loc_lastIndex = loc_m.end();
		}
		loc_sb.append(ip_sql.substring(loc_lastIndex));

		return loc_sb.toString();
	}

	/**
	 * rreplace sql parameter
	 * 
	 * @param ip_para
	 * @return
	 * @throws ParseException
	 */
	private String paraValue(Map<String, String> ip_map, String ip_para)
			throws ParseException
	{
		String loc_format = DateUtil.BATCH_DATE_FORMAT;

		String loc_ret = "";
		int loc_index = 0;
		if (ip_para.startsWith("sysDate"))
		{
			loc_index = ip_para.indexOf("[");
			if (loc_index != -1)
				loc_format = ip_para.substring(loc_index + 1, ip_para.indexOf("]"));

			loc_ret = DateUtil
					.dateToString(DateUtil.getSysDatetime(), loc_format);
		} else if (ip_para.startsWith("acDate"))
		{
			loc_index = ip_para.indexOf("[");
			if (loc_index != -1)
				loc_format = ip_para.substring(loc_index + 1, ip_para.indexOf("]"));

			loc_ret = DateUtil.dateToString(DateUtil.stringToDate(batchAcDate
					+ DateUtil.dateToString(DateUtil.getSysDatetime(),
							timePattern), DateUtil.BATCH_DATE_FORMAT
					+ timePattern), loc_format);
		} else if (ip_map != null && ip_map.get(ip_para) != null)
		{
			loc_ret = ip_map.get(ip_para);
		} else
		{
			loc_ret = ip_para;
		}

		return loc_ret;
	}
}
