package com.forms.framework.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.forms.datapipe.exception.DataPipeException;
import com.forms.framework.log.BatchLogger;

/**
 * String util
 * 
 * @author ahnan createDate:2011-04-28 updateDate:2011-04-28
 */
public class StringUtil
{
	private static BatchLogger logger = BatchLogger.getLogger(
			"dataProcess", "dataProcess", "dataProcess");

	/**
	 * parse a_bc_d to ABcD
	 * 
	 * @param ip_ret
	 * @return
	 */
	public static String xmlToJavaUp(String ip_ret)
	{
		ip_ret = ip_ret.toLowerCase();
		String[] loc_tmpArr = ip_ret.split("\\-");
		String loc_tmp = "";
		for (int loc_i = 0; loc_i < loc_tmpArr.length; loc_i++)
		{
			String loc_innerTmp = loc_tmpArr[loc_i];
			loc_innerTmp = loc_innerTmp.substring(0, 1).toUpperCase()
					+ loc_innerTmp.substring(1, loc_innerTmp.length());
			loc_tmp += loc_innerTmp;
		}
		return loc_tmp;
	}

	/**
	 * parse a_bc_d to aBcD
	 * 
	 * @param ip_ret
	 * @return
	 */
	public static String xmlToJavaLow(String ip_ret)
	{
		ip_ret = ip_ret.toLowerCase();
		String[] loc_tmpArr = ip_ret.split("\\-");
		String loc_tmp = "";
		for (int loc_i = 0; loc_i < loc_tmpArr.length; loc_i++)
		{
			String loc_innerTmp = loc_tmpArr[loc_i];
			loc_innerTmp = loc_innerTmp.substring(0, 1).toUpperCase()
					+ loc_innerTmp.substring(1, loc_innerTmp.length());
			loc_tmp += loc_innerTmp;
		}
		return loc_tmp.substring(0, 1).toLowerCase()
				+ loc_tmp.substring(1, loc_tmp.length());
	}

	public static String parseHtml(String ip_str)
	{
		if (ip_str != null)
		{
			ip_str = ip_str.replaceAll("&amp;", "&");
			ip_str = ip_str.replaceAll("&lt;", "<");
			ip_str = ip_str.replaceAll("&gt;", ">");
			ip_str = ip_str.replaceAll("&quot;", "\"");
			ip_str = ip_str.replaceAll("&nbsp;", " ");
			ip_str = ip_str.replaceAll("&amp;", "&");
		}
		return ip_str;
	}

	public static String fmtHtml2Text(String ip_value)
	{
		String loc_htmlStr = ip_value;
		String loc_textStr = "";
		Pattern loc_phtml;
		Matcher loc_mhtml;

		try
		{
			String loc_regEx_html = "<[^>]+>";
			loc_phtml = Pattern.compile(loc_regEx_html,
					Pattern.CASE_INSENSITIVE);
			loc_mhtml = loc_phtml.matcher(loc_htmlStr);
			loc_htmlStr = loc_mhtml.replaceAll("");
			loc_textStr = loc_htmlStr;
		} catch (Exception e)
		{
			logger.warn("fmtHtml2Text error:" + e.getMessage());
			return ip_value;
		}
		return loc_textStr;
	}

	public static boolean compareString(String ip_obj1, String ip_obj2)
	{
		if (null == ip_obj1 && null == ip_obj2)
		{
			return true;
		} else if (null == ip_obj1 && null != ip_obj2)
		{
			return false;
		} else
		{
			return ip_obj1.equals(ip_obj2);
		}
	}

	public static byte[] getBytes4IBM937(String ip_str)
			throws DataPipeException
	{
		byte[] loc_tempBytes = null;
		byte[] loc_Bytes = null;
		try
		{
			loc_tempBytes = CharsetConversionUtils
					.convertUnicodeToIBM937(ip_str + " ");
			loc_Bytes = new byte[loc_tempBytes.length - 1];
			System.arraycopy(loc_tempBytes, 0, loc_Bytes, 0,
					loc_tempBytes.length - 1);
		} catch (UnsupportedEncodingException e)
		{
			throw new DataPipeException(e);
		}
		return loc_Bytes;
	}
	
	public static String fieldNameToMethodName(String fieldName, String type)
	{
		if(CommonAPI.METHOD_FIX_SET.equals(type))
		{
			return CommonAPI.METHOD_FIX_SET + getFirstCharUpString(fieldName);
		}else if(CommonAPI.METHOD_FIX_GET.equals(type))
		{
			return CommonAPI.METHOD_FIX_GET + getFirstCharUpString(fieldName);
		}else
		{
			return getFirstCharUpString(fieldName);
		}
	}
	
	public static String getFirstCharUpString(String str)
	{
		if(!ValidateUtil.isEmpty(str))
		{
			str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
		}
		return str;
	}
}
