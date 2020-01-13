package com.forms.beneform4j.core.util.logger.log4j.appender;

import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;

import com.forms.beneform4j.core.util.logger.log4j.CustomLoggerAppenderHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 自定义文件名的Log4j文件日志输出终端（每天一个独立文件）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class CustomDailyRollingFileAppender extends DailyRollingFileAppender {

    private String filenameResolver;

    public CustomDailyRollingFileAppender() {
        super();
    }

    public CustomDailyRollingFileAppender(Layout layout, String filename, String datePattern) throws IOException {
        super(layout, filename, datePattern);
    }

    @Override
    public synchronized void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        fileName = CustomLoggerAppenderHolder.resolverCustomFileName(this, getFilenameResolver(), fileName);
        super.setFile(fileName, append, bufferedIO, bufferSize);
    }

    public String getFilenameResolver() {
        return filenameResolver;
    }

    public void setFilenameResolver(String filenameResolver) {
        this.filenameResolver = filenameResolver;
    }
}
