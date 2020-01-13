package com.forms.beneform4j.core.util.logger.log4j.appender;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;

import com.forms.beneform4j.core.util.logger.log4j.CustomLoggerAppenderHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 自定义文件名的Log4j文件日志输出终端<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class CustomFileAppender extends FileAppender {

    private String filenameResolver;

    public CustomFileAppender() {
        super();
    }

    public CustomFileAppender(Layout layout, String filename, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
        super(layout, filename, append, bufferedIO, bufferSize);
    }

    public CustomFileAppender(Layout layout, String filename, boolean append) throws IOException {
        super(layout, filename, append);
    }

    public CustomFileAppender(Layout layout, String filename) throws IOException {
        super(layout, filename);
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
