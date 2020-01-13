package com.forms.ffp.core.config.runtime;

import com.forms.ffp.core.service.FFPRuntimeConfigSvc;

public class FFPRuntimeConstants
{
	public static String LOCAL_FPS_PARTICIPANT_ID = "";
	public static String LOCAL_BIC_CODE = "";
	public static String ICL_MQ_CONFIG_SELECT = "";
	
	//add by yrd
	public static String PARTICIPANT_TCP_CONFIG_SELECT = "";
	
	
	public static String ICL_FPS_ID = "ICL";
	
	static
	{
		try
		{
			PropertiesFile rootPro = FFPRuntimeConfigSvc.getInstance().getRootConfig();
			LOCAL_FPS_PARTICIPANT_ID = rootPro.get("participant.LOCAL_FPS_PARTICIPANT_ID");
			LOCAL_BIC_CODE = rootPro.get("participant.LOCAL_BIC_CODE");
			ICL_MQ_CONFIG_SELECT = rootPro.get("connector.key.iclfps");
			PARTICIPANT_TCP_CONFIG_SELECT = rootPro.get("connector.key.participant");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
