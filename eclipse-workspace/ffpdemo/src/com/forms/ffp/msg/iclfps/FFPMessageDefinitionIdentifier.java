package com.forms.ffp.msg.iclfps;

import java.util.HashMap;
import java.util.Map;

import com.forms.ffp.msg.iclfps.creater.FFPXmlDocCreater;
import com.forms.ffp.msg.iclfps.creater.FFPXmlDoc_Camt_060_Creater;

public class FFPMessageDefinitionIdentifier
{
	public static String ICL_MSG_MESSAGEDEFINITIONIDENTIFIER_CAMT_60 = "camt.060.001.03";
	
	public static Map<String, FFPXmlDocCreater> xmlDocCreater = new HashMap<String, FFPXmlDocCreater>();
	
	static
	{
		xmlDocCreater.put(ICL_MSG_MESSAGEDEFINITIONIDENTIFIER_CAMT_60, new FFPXmlDoc_Camt_060_Creater());
	}
}
