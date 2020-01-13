package com.forms.beneform4j.core.dao.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.logger.LogMonitor;
import com.forms.beneform4j.core.util.logger.level.LogLevel;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Mybatis日志类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class MybatisLog extends Slf4jImpl {

    private Logger logger;

    private static final Map<String, Logger> map = new HashMap<String, Logger>();

    /**
     * 构造函数
     * 
     * @param clazz 执行的SQL-ID
     */
    public MybatisLog(String clazz) {
        super(clazz);
        try {
            Log log = (Log) FieldUtils.readField(this, "log", true);
            try {
                logger = (Logger) FieldUtils.readField(log, "logger", true);// Slf4jLocationAwareLoggerImpl
            } catch (Exception e) {
                logger = (Logger) FieldUtils.readField(log, "log", true);// Slf4jLoggerImpl
            }
        } catch (Exception i) {
            logger = LoggerFactory.getLogger(clazz);
        }
    }

    /**
     * 是否开启debug模式
     */
    @Override
    public boolean isDebugEnabled() {
        return super.isDebugEnabled();
    }

    /**
     * 是否开启跟踪模式
     */
    @Override
    public boolean isTraceEnabled() {
        return super.isTraceEnabled();
        // return true;
    }

    /**
     * 打印错误日志
     */
    @Override
    public void error(String s, Throwable e) {
        if (isEnabled(s, LogLevel.ERROR)) {
            CommonLogger.error(s, e, logger);
        }
    }

    /**
     * 打印错误日志
     */
    @Override
    public void error(String s) {
        if (isEnabled(s, LogLevel.ERROR)) {
            CommonLogger.error(s, null, logger);
        }
    }

    /**
     * 打印调试日志
     */
    @Override
    public void debug(String s) {
        if (isEnabled(s, LogLevel.DEBUG)) {
            CommonLogger.debug(s, null, logger);
        }
    }

    /**
     * 打印跟踪日志
     */
    @Override
    public void trace(String s) {
        if (isEnabled(s, LogLevel.DEBUG)) {
            CommonLogger.debug(s, null, logger);
        }
    }

    /**
     * 打印警告日志
     */
    @Override
    public void warn(String s) {
        if (isEnabled(s, LogLevel.WARN)) {
            CommonLogger.warn(s, null, logger);
        }
    }

    /**
     * 判断当前级别日志是否生效
     * 
     * @param msg 日志内容
     * @param level 日志级别
     * @return 是否需要打印
     */
    private boolean isEnabled(String msg, LogLevel level) {
        if (LogMonitor.isMonitoring()) {
            return true;
        } else {
            String type = getType(msg);
            if (!CoreUtils.isBlank(type)) {
                Logger l = getLogger(type);
                if (null == l) {
                    return true;
                } else {
                    switch (level) {
                        case DEBUG:
                            return l.isDebugEnabled();
                        case INFO:
                            return l.isInfoEnabled();
                        case WARN:
                            return l.isWarnEnabled();
                        case ERROR:
                            return l.isErrorEnabled();
                        default:
                            return true;
                    }
                }
            }
            return true;
        }

    }

    /**
     * 根据日志内容返回日志类型
     * 
     * @param msg 日志内容
     * @return 日志类型
     */
    private String getType(String msg) {
        Map<Pattern, String> mybatisLogTypeMapping = Beneform4jConfig.getMybatisLogTypeMapping();
        if (null != mybatisLogTypeMapping && !CoreUtils.isBlank(msg)) {
            for (Pattern p : mybatisLogTypeMapping.keySet()) {
                if (p.matcher(msg).find()) {
                    return mybatisLogTypeMapping.get(p);
                }
            }
        }
        return null;
    }

    /**
     * 获取日志
     * 
     * @param type 日志类型
     * @return 日志
     */
    private Logger getLogger(String type) {
        Logger logger = map.get(type);
        if (null == logger) {
            synchronized (map) {
                logger = map.get(type);
                if (null == logger) {
                    logger = LoggerFactory.getLogger(type);
                    map.put(type, logger);
                }
            }
        }
        return logger;
    }
}
