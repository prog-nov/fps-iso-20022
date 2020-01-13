package com.forms.datapipe.util;

import org.apache.commons.logging.Log;

/**
 * @author lindeshu
 *
 */
public class LogUtils {

	public static void debug(Log log, String message) {
		if(log.isDebugEnabled()) {
			log.debug(message);
		}
	}
	
	private LogUtils() {}
	
}
