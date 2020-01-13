package com.forms.beneform4j.core.util.logger.termination.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.logger.level.LogLevel;
import com.forms.beneform4j.core.util.logger.termination.ILogTermination;
import com.forms.beneform4j.core.util.stack.IStack;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Log4j的日志终端代理类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class LogTerminationProxy implements ILogTermination {

    /**
     * 根据日志级别写日志
     * 
     * @param level 日志级别
     * @param stack 日志信息堆栈
     */
    @Override
    public void write(LogLevel level, IStack stack) {
        Logger logger = getLogger(stack);
        String message = stack.getMessage();
        Throwable e = stack.getThrowable();
        switch (level) {
            case DEBUG:
                if (logger.isDebugEnabled()) {
                    logger.debug(message, e);
                }
                break;
            case INFO:
                if (logger.isInfoEnabled()) {
                    logger.info(message, e);
                }
                break;
            case WARN:
                if (logger.isWarnEnabled()) {
                    logger.warn(message, e);
                }
                break;
            case ERROR:
                if (logger.isErrorEnabled()) {
                    logger.error(message, e);
                }
                break;
        }
    }

    /**
     * 获取日志
     * 
     * @param stack 日志信息堆栈
     * @return 日志类
     */
    protected Logger getLogger(IStack stack) {
        Logger logger = stack.getLogger();
        if (null == logger) {
            String key = BaseConfig.getScanPackage();
            if (null != stack && null != stack.getStack()) {
                key = stack.getStack().getClassName();
            }
            logger = map.get(key);
            if (null == logger) {
                synchronized (map) {
                    logger = map.get(key);
                    if (null == logger) {
                        logger = LoggerFactory.getLogger(key);
                        map.put(key, logger);
                    }
                }
            }
        }
        return logger;
    }

    private static final Map<String, Logger> map = new HashMap<String, Logger>();
}
