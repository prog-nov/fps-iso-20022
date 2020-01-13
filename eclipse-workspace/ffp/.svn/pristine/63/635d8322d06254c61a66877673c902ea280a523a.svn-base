package com.forms.beneform4j.core.util.logger;

import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.monitor.IMonitor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日志DEBUG模式监控帮助类，如果开启DEBUG模式，平台中所有日志级别自动修改为DEBUG，否则按原级别输出<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class LogMonitor {

    /**
     * 是否已经开启DEBUG模式监控
     * 
     * @return 是否已经开启DEBUG模式监控
     */
    public static boolean isMonitoring() {
        IMonitor monitor = getMonitor();
        return null == monitor ? false : monitor.isMonitoring();
    }

    /**
     * 开启DEBUG监控模式
     */
    public static void start() {
        IMonitor monitor = getMonitor();
        if (null != monitor) {
            monitor.start();
        }
    }

    /**
     * 停止DEBUG监控模式
     */
    public static void stop() {
        IMonitor monitor = getMonitor();
        if (null != monitor) {
            monitor.stop();
        }
    }

    /**
     * 获取DEBUG模式监控实现类
     * 
     * @return
     */
    private static IMonitor getMonitor() {
        return BaseConfig.getLogMonitor();
    }
}
