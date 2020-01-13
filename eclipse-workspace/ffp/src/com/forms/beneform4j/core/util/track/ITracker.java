package com.forms.beneform4j.core.util.track;

import com.forms.beneform4j.core.util.annotation.Warning;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 跟踪器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface ITracker {

    /**
     * 开始跟踪
     */
    public void start();

    /**
     * 使用传入的跟踪号开始跟踪
     * 
     * @param trackId 跟踪号
     */
    @Warning("一般用于多线程环境中开始新的跟踪")
    public void start(String trackId);

    /**
     * 是否正在跟踪
     * 
     * @return 是否正在跟踪
     */
    public boolean isTracking();

    /**
     * 获取当前跟踪号
     * 
     * @return 当前跟踪号
     */
    public String getCurrentTrackId();

    /**
     * 停止跟踪
     */
    public void stop();
}
