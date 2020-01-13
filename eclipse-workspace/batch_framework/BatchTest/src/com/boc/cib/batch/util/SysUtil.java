package com.boc.cib.batch.util;

import java.util.HashMap;
import java.util.Map;

public class SysUtil
{

	public static String TYPE_BK = "1";

	public static String TYPT_BH = "2";
	
	public static Map<String,String> bhMap = new HashMap<String,String>();

	public static String getOrgBranch(String ip_cbsAc, String ip_type)
	{
		String loc_ret = "";
		try
		{
			if (ip_cbsAc == null || "".equals(ip_cbsAc.trim()))
			{
				return loc_ret;
			}
			if (ip_cbsAc != null && ip_cbsAc.trim().length() < 6)
				throw new Exception(
						"cbs account length is not longer then expect");

			if (ip_type.equals(TYPE_BK))
				loc_ret = ip_cbsAc.substring(0, 3);
			else if (ip_type.equals(TYPT_BH))
			{
				loc_ret = ip_cbsAc.substring(0, 6);
			} else
				throw new Exception("we do not support type " + ip_type);
		} catch (Exception ip_e)
		{
			ip_e.printStackTrace();
		}

		return loc_ret;
	}
}
