package com.forms.framework.job.common.ftpservice;

import java.util.HashMap;
import java.util.Properties;

import com.forms.framework.conf.SysConfiger;
import com.forms.framework.exception.BatchFrameworkException;

public class FtpManager
{
	private static HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();	

	public static synchronized Properties getFtpProperties(SysConfiger ip_sysconfig,
			String ip_transferName) throws BatchFrameworkException
	{
		if (propertiesMap.get(ip_transferName) == null)
		{
			propertiesMap.put(ip_transferName, ip_sysconfig
					.getTransferPropertiesByName(ip_transferName));
		}
		return propertiesMap.get(ip_transferName);
	}

}
