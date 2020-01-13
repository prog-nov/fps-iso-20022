package com.forms.beneform4j.core.util.monitor.impl;

import com.forms.beneform4j.core.util.monitor.IMonitor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用内存存储状态的监控器接口实现类 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class MemoryMonitor implements IMonitor {

    private transient boolean monitor = false;

    /**
     * 是否处于监控状态
     * 
     * @return 是否处于监控状态
     */
    @Override
    public boolean isMonitoring() {
        return monitor;
    }

    /**
     * 开启监控
     */
    @Override
    public void start() {
        this.monitor = true;
    }

    /**
     * 停止监控
     */
    @Override
    public void stop() {
        this.monitor = false;
    }
}
