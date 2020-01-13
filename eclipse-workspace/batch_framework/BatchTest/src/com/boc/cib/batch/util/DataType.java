/**
 * updateDate:2012-01-18
 *	ModifiedBy:zt
 *	ModifiedReason:add method printDate4Empty
 *	updateVersion:4 month
 */
package com.boc.cib.batch.util;

import com.forms.datapipe.exception.DataPipeException;
/**
 * data formatter
 * 
 * @author cxl
 * 
 */
public class DataType
{
	public static String parseBKString(String value, int length) throws DataPipeException
	{
		String loc_tmp = SysUtil.getOrgBranch(value, "1");
		if(null == loc_tmp || "".equals(loc_tmp))
		{
			if(value != null && !"".equals(value))
				loc_tmp = value.substring(0, 3);
		}
		return loc_tmp;
	}
	
	public static String parseBHString(String value, int length) throws DataPipeException
	{
		String loc_tmp = SysUtil.getOrgBranch(value, "2");
		if(null == loc_tmp || "".equals(loc_tmp))
		{
			if(value != null && !"".equals(value))
				loc_tmp = value.substring(3, 6);
		}
		return loc_tmp;
	}
}
