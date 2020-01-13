package com.forms.ffp.framework.logger;

import org.apache.log4j.Priority;

import com.forms.beneform4j.core.util.logger.log4j.appender.CustomFileAppender;

public class LimitedLevelFileAppender extends CustomFileAppender {


	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);  
	}
	
}
