package com.forms.ffp.core.logger;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Priority;

public class LimitedLevelFileAppender extends FileAppender {


	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return this.getThreshold().equals(priority);  
	}
	
}
