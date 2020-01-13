package com.forms.beneform4j.core.util.exception;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.level.ExceptionLevel;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMeta;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMetaLoader;
import com.forms.beneform4j.core.util.track.Tracker;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常抛出公共类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class Throw {

    /**
     * 加载异常元信息
     * 
     * @param code 异常代码
     * @param cause 异常原因
     * @return 异常元信息
     */
    public static IExceptionMeta lookupExceptionMeta(String code, Throwable cause) {
        IExceptionMetaLoader loader = BaseConfig.getExceptionMetaLoader();
        return null == loader ? null : loader.lookup(code, cause);
    }

    /**
     * 加载异常国际化描述信息
     * 
     * @param code 异常代码
     * @param cause 异常原因
     * @param args 国际化信息中的占位符参数
     * @return 异常国际化描述信息
     * @since 1.1.0
     */
    public static String getExceptionLocalMessage(String code, Object... args) {
        return getExceptionLocalMessage(code, null, args);
    }

    /**
     * 加载异常国际化描述信息
     * 
     * @param cause 异常原因
     * @param args 国际化信息中的占位符参数
     * @return 异常国际化描述信息
     * @since 1.1.0
     */
    public static String getExceptionLocalMessage(Throwable cause, Object... args) {
        return getExceptionLocalMessage(null, cause, args);
    }

    /**
     * 加载异常国际化描述信息
     * 
     * @param code 异常代码
     * @param cause 异常原因
     * @param args 国际化信息中的占位符参数
     * @return 异常国际化描述信息
     * @since 1.1.0
     */
    public static String getExceptionLocalMessage(String code, Throwable cause, Object... args) {
        Beneform4jExceptionInnerProxy proxy = createRuntimeException(code, cause, args).getProxy();
        return proxy.getMessage();
    }

    /**
     * 抛出平台运行时异常
     * 
     * @param code 异常代码
     * @param args 国际化信息中的占位符参数
     * @throws Beneform4jRuntimeException 平台受检异常
     */
    public static void throwRuntimeException(String code, Object... args) {
        throw createRuntimeException(code, null, args);
    }

    /**
     * 抛出平台运行时异常
     * 
     * @param e 原始异常
     * @throws Beneform4jRuntimeException 平台受检异常
     */
    public static void throwRuntimeException(Throwable e) {
        throw createRuntimeException(null, e);
    }

    /**
     * 抛出平台运行时异常
     * 
     * @param code 异常代码
     * @param e 原始异常
     * @param args 国际化信息中的占位符参数
     * @throws Beneform4jRuntimeException 平台受检异常
     */
    public static void throwRuntimeException(String code, Throwable e, Object... args) {
        throw createRuntimeException(code, e, args);
    }

    /**
     * 创建平台运行时异常
     * 
     * @param code 异常代码
     * @param args 国际化信息中的占位符参数
     * @return 平台运行时异常，只返回，不抛出
     */
    public static Beneform4jRuntimeException createRuntimeException(String code, Object... args) {
        return createRuntimeException(code, null, args);
    }

    /**
     * 创建平台运行时异常
     * 
     * @param e 原始异常
     * @return 平台运行时异常，只返回，不抛出
     */
    public static Beneform4jRuntimeException createRuntimeException(Throwable e) {
        return createRuntimeException(null, e);
    }

    /**
     * 创建平台运行时异常
     * <p>
     * <ul>
     * <li>如果原始异常为{@link Beneform4jRuntimeException}，直接返回
     * <li>如果原始异常为{@link Beneform4jException}，内部转换为Beneform4jRuntimeException再返回
     * <li>如果原始异常为{@link InvocationTargetException}，将原始异常设置为getTargetException()，再包装为平台运行时异常返回
     * <li>其它情况，将原始异常包装为平台运行时异常返回
     * </ul>
     * </p>
     * 
     * @param code 异常代码
     * @param e 原始异常
     * @param args 国际化信息中的占位符参数
     * @return 平台运行时异常，只返回，不抛出
     */
    public static Beneform4jRuntimeException createRuntimeException(String code, Throwable e, Object... args) {
        if (e instanceof Beneform4jRuntimeException) {
            return (Beneform4jRuntimeException) e;
        } else if (e instanceof Beneform4jException) {
            return new Beneform4jRuntimeException((Beneform4jException) e);
        } else if (e instanceof InvocationTargetException) {
            return createRuntimeException(code, ((InvocationTargetException) e).getTargetException(), args);
        } else {
            Beneform4jExceptionInnerProxy proxy = new Beneform4jExceptionInnerProxy(code, e, args);
            return new Beneform4jRuntimeException(proxy);
        }
    }

    /**
     * 获取没有日志跟踪号的信息描述
     * 
     * @param e 异常
     * @return 异常信息
     */
    public static String getMessageWithoutTrackId(Throwable e) {
        Beneform4jExceptionInnerProxy proxy = createRuntimeException(e).getProxy();
        return proxy.getMessage();
    }

    /**
     * 获取简单的异常信息描述，使用系统默认的换行符
     * 
     * @param e 异常
     * @return 异常描述
     */
    public static String getShortMessage(Throwable e) {
        return getShortMessage0(e, EnvConsts.LINE_SEPARATOR);
    }

    /**
     * 获取简单的异常信息描述
     * 
     * @param e 异常
     * @param lineSeparator 换行符
     * @return 异常描述
     */
    public static String getShortMessage(Throwable e, String lineSeparator) {
        return getShortMessage0(e, lineSeparator);
    }

    /**
     * 获取异常信息描述，使用系统默认的换行符
     * <p>
     * 如果当前环境开启了异常堆栈监控，则返回详细堆栈，否则不返回堆栈
     * </p>
     * 
     * @param e 异常
     * @return 异常描述
     */
    public static String getMessage(Throwable e) {
        return getExceptionMessage(e, EnvConsts.LINE_SEPARATOR, false);
    }

    /**
     * 获取异常信息描述
     * <p>
     * 如果当前环境开启了异常堆栈监控，则返回详细堆栈，否则不返回堆栈
     * </p>
     * 
     * @param e 异常
     * @param lineSeparator 换行符
     * @return 异常描述
     */
    public static String getMessage(Throwable e, String lineSeparator) {
        return getExceptionMessage(e, lineSeparator, false);
    }

    /**
     * 获取异常信息详细描述，使用系统默认的换行符
     * 
     * @param e 异常
     * @return 异常详细描述
     */
    public static String getStackMessage(Throwable e) {
        return getExceptionMessage(e, EnvConsts.LINE_SEPARATOR, true);
    }

    /**
     * 获取异常信息详细描述，使用系统默认的换行符
     * 
     * @param e 异常
     * @param lineSeparator 换行符
     * @return 异常详细描述
     */
    public static String getStackMessage(Throwable e, String lineSeparator) {
        return getExceptionMessage(e, lineSeparator, true);
    }

    /**
     * 获取异常信息描述
     * 
     * @param e 异常
     * @param lineSeparator 换行符
     * @param forcePrintStack 是否强制返回详细堆栈信息
     * @return 异常描述
     */
    private static String getExceptionMessage(Throwable e, String lineSeparator, boolean forcePrintStack) {
        StringBuffer sb = new StringBuffer(getShortMessage0(e, lineSeparator));
        if (forcePrintStack || ExceptionMonitor.isMonitoring()) {
            StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; i < trace.length; i++) {
                sb.append("\tat ").append(trace[i]).append(lineSeparator);
            }
            Throwable t = e.getCause();
            if (null != t) {
                setTrace(t, sb, trace, lineSeparator);
            }
        }
        if (!CoreUtils.isBlank(lineSeparator)) {
            sb.delete(sb.lastIndexOf(lineSeparator), sb.length());
        }
        return sb.toString();
    }

    /**
     * 获取简短异常信息描述
     * 
     * @param e 异常信息
     * @param lineSeparator 换行符
     * @return 简短异常信息描述
     */
    private static String getShortMessage0(Throwable e, String lineSeparator) {
        StringBuffer sb = new StringBuffer();
        Beneform4jExceptionInnerProxy proxy = createRuntimeException(e).getProxy();
        if (null != proxy.getTrackId()) {
            sb.append("TrackId:" + proxy.getTrackId()).append(lineSeparator);
        }
        String message = proxy.getMessage();
        String code = proxy.getCode();
        if (null != code && !code.equals(message)) {
            sb.append("Code:" + code).append(lineSeparator);
        }
        // String pCode = proxy.getParentCode();
        // if(null != pCode && !pCode.startsWith("##")){
        // sb.append("ParentCode:"+pCode).append(lineSeparator);
        // }
        // if(null != proxy.getLevel()){
        // sb.append(proxy.getLevel().getDescription()).append(lineSeparator);
        // }
        if (null != message) {
            sb.append("Message:" + message).append(lineSeparator);
        }
        Throwable cause = getRootCause(e);
        if (null != cause && !cause.equals(e)) {
            String c = cause.getMessage();
            if (!CoreUtils.isBlank(c)) {
                sb.append("Cause:" + c).append(lineSeparator);
            }
        }
        return sb.toString();
    }

    /**
     * 获取导致异常的最起初的原因
     * 
     * @param e 异常
     * @return 根异常
     */
    private static Throwable getRootCause(Throwable e) {
        if (null == e) {
            return null;
        }
        Throwable rootCause = null;
        Throwable cause = e.getCause();
        while (cause != null && cause != rootCause) {
            rootCause = cause;
            if (e instanceof InvocationTargetException) {
                cause = ((InvocationTargetException) cause).getTargetException();
            } else {
                cause = cause.getCause();
            }
        }
        return rootCause;
    }

    /**
     * 嵌套打印异常堆栈信息
     * 
     * @param ourCause
     * @param info
     * @param causedTrace
     * @param lineSeparator
     */
    private static void setTrace(Throwable ourCause, StringBuffer info, StackTraceElement[] causedTrace, String lineSeparator) {
        StackTraceElement[] trace = ourCause.getStackTrace();
        int m = trace.length - 1, n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;

        info.append("Caused by: ").append(ourCause).append(lineSeparator);
        for (int i = 0; i <= m; i++) {
            info.append("\tat ").append(trace[i]).append(lineSeparator);
        }

        if (framesInCommon != 0) {
            info.append("\t... ").append(framesInCommon).append(" more").append(lineSeparator);
        }

        // Recurse if we have a cause
        Throwable cause = ourCause.getCause();
        if (cause != null) {
            setTrace(cause, info, trace, lineSeparator);
        }
    }

    /* package */static class Beneform4jExceptionInnerProxy {
        private String trackId;
        private String parentCode;
        private String code;
        private String message;
        private String view;
        private ExceptionLevel level;
        private List<IExceptionHandler> handlers;
        private Throwable cause;
        private AtomicBoolean load = new AtomicBoolean(false);
        private Object[] args;

        Beneform4jExceptionInnerProxy(String code, Throwable cause, Object... args) {
            this.trackId = Tracker.getCurrentTrackId();
            this.code = code;
            this.cause = cause;
            this.args = args;
        }

        private boolean doMessageResolver(IExceptionMeta meta, Object... args) {
            String localeMessageKey = meta.getMessageKey();
            if (!CoreUtils.isBlank(localeMessageKey)) {// 国际化key值存在
                this.message = CoreUtils.getMessage(localeMessageKey, args);
                if (CoreUtils.isBlank(this.message)) {// 但是国际化配置文件中不存在对应的key配置
                    throw new IllegalArgumentException("not found the message key[" + localeMessageKey + "] in exception property files, exception code is " + this.code + ".");
                }
                return true;
            }
            return false;
        }

        private boolean doViewResolver(IExceptionMeta meta) {
            if (!CoreUtils.isBlank(meta.getView())) {
                this.view = meta.getView();
                return true;
            }
            return false;
        }

        private boolean doLevelResolver(IExceptionMeta meta) {
            if (null != meta.getLevel()) {
                this.level = meta.getLevel();
                return true;
            }
            return false;
        }

        private boolean doHandlersResolver(IExceptionMeta meta) {
            if (null != meta.getHandlers() && !meta.getHandlers().isEmpty()) {
                this.handlers = meta.getHandlers();
                return true;
            }
            return false;
        }

        private void doResolver() {
            if (!load.get()) {
                synchronized (load) {
                    if (!load.get()) {
                        try {
                            boolean messageStatus = false, viewStatus = false, levelStatus = false, handlerStatus = false;
                            IExceptionMeta meta = lookupExceptionMeta(code, cause);
                            if (null == meta) {// 未找到元信息
                                this.parentCode = null;
                                this.code = CoreUtils.isBlank(code) ? BaseConfig.getExceptionCode() : code;
                            } else {
                                this.parentCode = meta.getParentCode();
                                this.code = meta.getCode();
                                boolean status = false;
                                while (!status) {
                                    messageStatus = messageStatus || this.doMessageResolver(meta, args);
                                    viewStatus = viewStatus || this.doViewResolver(meta);
                                    levelStatus = levelStatus || this.doLevelResolver(meta);
                                    handlerStatus = handlerStatus || this.doHandlersResolver(meta);
                                    String type = meta.getParentCode();
                                    if (!CoreUtils.isBlank(type)) {
                                        meta = lookupExceptionMeta(type, null);
                                    } else {
                                        meta = null;
                                    }
                                    status = (meta == null) || (messageStatus && viewStatus && levelStatus && handlerStatus);
                                }
                            }
                            if (!messageStatus) {
                                this.message = CoreUtils.getMessage(this.code, args);
                                if (CoreUtils.isBlank(this.message)) {
                                    this.message = this.code;
                                }
                            }
                            if (!viewStatus) {
                                this.view = BaseConfig.getExceptionView();
                            }
                            if (!levelStatus) {
                                this.level = BaseConfig.getExceptionLevel();
                            }
                            if (!handlerStatus) {
                                this.handlers = BaseConfig.getExceptionHandlers();
                            }
                        } finally {
                            load.set(true);
                        }
                    }
                }
            }
        }

        String getTrackId() {
            return trackId;
        }

        String getParentCode() {
            doResolver();
            return parentCode;
        }

        String getCode() {
            doResolver();
            return code;
        }

        String getMessage() {
            doResolver();
            return message;
        }

        String getView() {
            doResolver();
            return view;
        }

        ExceptionLevel getLevel() {
            doResolver();
            return level;
        }

        List<IExceptionHandler> getHandlers() {
            doResolver();
            return handlers;
        }

        Throwable getCause() {
            return cause;
        }
    }
}
