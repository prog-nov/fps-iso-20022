package com.forms.beneform4j.core.util.monitor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 监控器接口 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IMonitor {

    /**
     * 是否处于监控状态
     * 
     * @return 是否处于监控状态
     */
    public boolean isMonitoring();

    /**
     * 开启监控
     */
    public void start();

    /**
     * 停止监控
     */
    public void stop();
}
