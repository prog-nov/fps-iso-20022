package com.forms.ffp.core.define;

public class FFPConstants
{
//	config name
	public final static String TX_MAPPING_PROPERTIES_FILE = "txmapping.properties";
	
	public final static String RUNNING_MODE_REALTIME = "R";
	public final static String RUNNING_MODE_BATCH = "B";
	public final static String RUNNING_MODE_CLOSE = "B";
	public final static String RUNNING_STATUS_NORMAL = "N";
	public final static String RUNNING_STATUS_CLOSE = "C";
	
	// Log level
	public final static String ERR_LEVEL_INFO = "INFO";
	
	public final static String ERR_LEVEL_WARN = "WARN";
	
	public final static String ERR_LEVEL_ERROR = "ERROR";
	
	// Connector TYPE
	public final static String CONNECTTOR_TYPE_MQ_APACHEMQ = "apachemq";
	public final static String CONNECTTOR_TYPE_MQ_WEBPHEREMQ = "ibmmq";
	public final static String CONNECTTOR_TYPE_TCP = "TCP";
	
	// SEND TYPE
	public final static String SEND_TYPE_REQ = "REQ";
	public final static String SEND_TYPE_ACK = "ACK";
	
	// MQ_LEVEL_PRIORITY
	public final static String MQ_LEVEL_PRIORITY_HIGH = "H";
	public final static String MQ_LEVEL_PRIORITY_MEDIUM = "M";
	
//	msg source
	public final static String TX_SOURCE_HKICL = "ICL";
	public final static String TX_SOURCE_FFPAGENT = "FFPAGENT";
	public final static String TX_SOURCE_FFP = "FFP";
	public final static String TX_SOURCE_AGENT = "AGENT";
	
	//msg type 
	public final static String MSG_TYPE_FPS_CLRSYSMMBID= "CSC";
	public final static String MSG_TYPE_FPS_BICFI = "BIC";
	public final static String MSG_TYPE_FFP = "FFP";
	public final static String MSG_CODE_FFP = "29";
	public final static String MSG_TYPE_AGENT = "AGENT";
	public final static String MSG_CODE_AGENT = "30";
	
	// cutoff type
	public final static String CUTOFF_TYPE_FFP = "FFP";
}
