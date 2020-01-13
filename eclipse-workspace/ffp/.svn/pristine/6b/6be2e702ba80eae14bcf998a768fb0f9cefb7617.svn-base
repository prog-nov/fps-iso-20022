package com.forms.beneform4j.core.util.exception;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.monitor.IMonitor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常详细堆栈监控帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class ExceptionMonitor {

    /**
     * 是否开启详细堆栈跟踪
     * 
     * @return 是否开启详细堆栈跟踪
     */
    public static boolean isMonitoring() {
        IMonitor monitor = getMonitor();
        return null == monitor ? false : monitor.isMonitoring();
    }

    /**
     * 开启详细堆栈跟踪
     */
    public static void start() {
        IMonitor monitor = getMonitor();
        if (null != monitor) {
            monitor.start();
        }
    }

    /**
     * 停止详细堆栈跟踪
     */
    public static void stop() {
        IMonitor monitor = getMonitor();
        if (null != monitor) {
            monitor.stop();
        }
    }

    /**
     * 获取平台配置的详细堆栈跟踪开关
     * 
     * @return 平台配置的详细堆栈跟踪开关类
     */
    private static IMonitor getMonitor() {
        return BaseConfig.getExceptionMonitor();
    }
}
