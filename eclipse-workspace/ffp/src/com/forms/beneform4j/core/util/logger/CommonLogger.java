package com.forms.beneform4j.core.util.logger;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.logger.level.LogLevel;
import com.forms.beneform4j.core.util.logger.termination.ILogTermination;
import com.forms.beneform4j.core.util.stack.IStack;
import com.forms.beneform4j.core.util.stack.IStackFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日志工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class CommonLogger {

    /**
     * 是否已开启DEBUG模式
     * 
     * @return 是否已开启
     */
    public static boolean isDebugEnabled() {
        StackTraceElement stack = getStack();
        return isEnabled(stack, LogLevel.DEBUG);
    }

    /**
     * 是否已开启INFO模式
     * 
     * @return 是否已开启
     */
    public static boolean isInfoEnabled() {
        StackTraceElement stack = getStack();
        return isEnabled(stack, LogLevel.INFO);
    }

    /**
     * 是否已开启WARN模式
     * 
     * @return 是否已开启
     */
    public static boolean isWarnEnabled() {
        StackTraceElement stack = getStack();
        return isEnabled(stack, LogLevel.WARN);
    }

    /**
     * 是否已开启ERROR模式
     * 
     * @return 是否已开启
     */
    public static boolean isErrorEnabled() {
        StackTraceElement stack = getStack();
        return isEnabled(stack, LogLevel.ERROR);
    }

    /**
     * 打印DEBUG级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param args 消息占位符参数
     */
    public static void debug(String msg, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.DEBUG), stack, format(msg, args), null);
    }

    /**
     * 打印DEBUG级别日志
     * 
     * @param e 异常
     */
    public static void debug(Throwable e) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.DEBUG), stack, null, e);
    }

    /**
     * 打印DEBUG级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param args 消息占位符参数
     */
    public static void debug(String msg, Throwable e, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.DEBUG), stack, format(msg, args), e);
    }

    /**
     * 向指定终端打印DEBUG级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param termination 日志终端
     * @param args 消息占位符参数
     */
    public static void debug(String msg, Throwable e, ILogTermination termination, String... args) {
        StackTraceElement stack = getStack();
        IStack ss = getStack(null, stack, format(msg, args), e);
        termination.write(getLogLevel(LogLevel.DEBUG), ss);
    }

    /**
     * 使用指定日志类打印DEBUG级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param logger 日志类
     * @param args 消息占位符参数
     */
    public static void debug(String msg, Throwable e, Logger logger, String... args) {
        StackTraceElement stack = getStack();
        write(logger, getLogLevel(LogLevel.DEBUG), stack, format(msg, args), e);
    }

    /**
     * 打印INFO级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param args 消息占位符参数
     */
    public static void info(String msg, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.INFO), stack, format(msg, args), null);
    }

    /**
     * 打印INFO级别日志
     * 
     * @param e 异常
     */
    public static void info(Throwable e) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.INFO), stack, null, e);
    }

    /**
     * 打印INFO级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param args 消息占位符参数
     */
    public static void info(String msg, Throwable e, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.INFO), stack, format(msg, args), e);
    }

    /**
     * 向指定终端打印INFO级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param termination 日志终端
     * @param args 消息占位符参数
     */
    public static void info(String msg, Throwable e, ILogTermination termination, String... args) {
        StackTraceElement stack = getStack();
        IStack ss = getStack(null, stack, format(msg, args), e);
        termination.write(getLogLevel(LogLevel.INFO), ss);
    }

    /**
     * 使用指定日志类打印INFO级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param logger 日志类
     * @param args 消息占位符参数
     */
    public static void info(String msg, Throwable e, Logger logger, String... args) {
        StackTraceElement stack = getStack();
        write(logger, getLogLevel(LogLevel.INFO), stack, format(msg, args), e);
    }

    /**
     * 打印WARN级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param args 消息占位符参数
     */
    public static void warn(String msg, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.WARN), stack, format(msg, args), null);
    }

    /**
     * 打印WARN级别日志
     * 
     * @param e 异常
     */
    public static void warn(Throwable e) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.WARN), stack, null, e);
    }

    /**
     * 打印WARN级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param args 消息占位符参数
     */
    public static void warn(String msg, Throwable e, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.WARN), stack, format(msg, args), e);
    }

    /**
     * 向指定终端打印WARN级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param termination 日志终端
     * @param args 消息占位符参数
     */
    public static void warn(String msg, Throwable e, ILogTermination termination, String... args) {
        StackTraceElement stack = getStack();
        IStack ss = getStack(null, stack, format(msg, args), e);
        termination.write(getLogLevel(LogLevel.WARN), ss);
    }

    /**
     * 使用指定日志类打印WARN级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param logger 日志类
     * @param args 消息占位符参数
     */
    public static void warn(String msg, Throwable e, Logger logger, String... args) {
        StackTraceElement stack = getStack();
        write(logger, getLogLevel(LogLevel.WARN), stack, format(msg, args), e);
    }

    /**
     * 打印ERROR级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param args 消息占位符参数
     */
    public static void error(String msg, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.ERROR), stack, format(msg, args), null);
    }

    /**
     * 打印ERROR级别日志
     * 
     * @param e 异常
     */
    public static void error(Throwable e) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.ERROR), stack, null, e);
    }

    /**
     * 打印ERROR级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param args 消息占位符参数
     */
    public static void error(String msg, Throwable e, String... args) {
        StackTraceElement stack = getStack();
        write(null, getLogLevel(LogLevel.ERROR), stack, format(msg, args), e);
    }

    /**
     * 向指定终端打印ERROR级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param termination 日志终端
     * @param args 消息占位符参数
     */
    public static void error(String msg, Throwable e, ILogTermination termination, String... args) {
        StackTraceElement stack = getStack();
        IStack ss = getStack(null, stack, format(msg, args), e);
        termination.write(getLogLevel(LogLevel.ERROR), ss);
    }

    /**
     * 使用指定日志类打印ERROR级别日志
     * 
     * @param msg 日志内容，可使用{0}、{1}等占位符
     * @param e 异常
     * @param logger 日志类
     * @param args 消息占位符参数
     */
    public static void error(String msg, Throwable e, Logger logger, String... args) {
        StackTraceElement stack = getStack();
        write(logger, getLogLevel(LogLevel.ERROR), stack, format(msg, args), e);
    }

    /**
     * 格式化消息
     * 
     * @param pattern
     * @param arguments
     * @return
     */
    private static String format(String pattern, String... arguments) {
        if (null == pattern) {
            return null;
        }
        if (null == arguments) {
            return pattern;
        }
        Object[] args = new Object[arguments.length];
        for (int i = arguments.length - 1; i >= 0; i--) {
            args[i] = arguments[i];
        }
        return CoreUtils.format(pattern, args);
    }

    /**
     * 是否已开启日志级别
     * 
     * @param stack 日志堆栈
     * @param logLevel 日志级别
     * @return 是否已开启
     */
    private static boolean isEnabled(StackTraceElement stack, LogLevel logLevel) {
        Logger logger = LoggerFactory.getLogger(stack.getClassName());
        LogLevel level = getLogLevel(logLevel);
        boolean enabled = false;
        switch (level) {
            case DEBUG:
                enabled = logger.isDebugEnabled();
                break;
            case INFO:
                enabled = logger.isInfoEnabled();
                break;
            case WARN:
                enabled = logger.isWarnEnabled();
                break;
            case ERROR:
                enabled = logger.isErrorEnabled();
                break;
        }
        return enabled;
    }

    /**
     * 写日志
     * 
     * @param logger 日志类
     * @param level 日志级别
     * @param stack 日志堆栈
     * @param message 日志内容
     * @param e 异常
     */
    private static void write(Logger logger, LogLevel level, StackTraceElement stack, String message, Throwable e) {
        IStackFactory stackFactory = BaseConfig.getStackFactory();
        List<ILogTermination> logTerminationList = BaseConfig.getLogTerminations();
        if (null != stackFactory && null != logTerminationList && !logTerminationList.isEmpty()) {
            IStack ss = getStack(logger, stack, message, e);
            for (ILogTermination logTermination : logTerminationList) {
                logTermination.write(level, ss);
            }
        }
    }

    /**
     * 获取日志级别
     * 
     * @param logLevel 原日志级别
     * @return 如果开启DEBUG模式监控，返回DEBUG级别，否则返回原日志级别
     */
    private static LogLevel getLogLevel(LogLevel logLevel) {
        return LogMonitor.isMonitoring() ? LogLevel.DEBUG : logLevel;
    }

    /**
     * 获取日志堆栈
     * 
     * @param logger 日志类
     * @param stack 堆栈
     * @param message 日志内容
     * @param e 异常
     * @return
     */
    private static IStack getStack(Logger logger, StackTraceElement stack, String message, Throwable e) {
        IStackFactory stackFactory = BaseConfig.getStackFactory();
        IStack ss = stackFactory.getStack(logger, stack, message, e);
        return ss;
    }

    /**
     * 获取堆栈
     * 
     * @return
     */
    private static StackTraceElement getStack() {
        return new Exception().getStackTrace()[2];
    }
}
