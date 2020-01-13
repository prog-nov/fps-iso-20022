package com.forms.beneform4j.core.util.logger.level;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日志级别，和Log4j中同名日志级别相对应，日志级别 ERROR > WARN > INFO > DEBUG <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public enum LogLevel {
    DEBUG, INFO, WARN, ERROR;

    /**
     * 获取日志级别描述
     * 
     * @return 日志级别描述
     */
    public String getDescription() {
        switch (this) {// 这里为了打印的时候格式一致，补上相应的空格
            case DEBUG:
                return "DEBUG";
            case INFO:
                return "INFO ";
            case WARN:
                return "WARN ";
            case ERROR:
                return "ERROR";
        }
        return null;
    }
}
