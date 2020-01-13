package com.forms.beneform4j.core.util.track;

import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 跟踪器帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class Tracker {

    /**
     * 开启跟踪
     */
    public static void start() {
        getTracker().start();
    }

    /**
     * 使用指定跟踪号开始跟踪
     * 
     * @param trackId 跟踪号
     */
    public static void start(String trackId) {
        getTracker().start(trackId);
    }

    /**
     * 判断是否正在跟踪
     * 
     * @return 是否正在跟踪
     */
    public static boolean isTracking() {
        return getTracker().isTracking();
    }

    /**
     * 获取目前的跟踪号
     * 
     * @return 当前跟踪号
     */
    public static String getCurrentTrackId() {
        return getTracker().getCurrentTrackId();
    }

    /**
     * 停止跟踪
     */
    public static void stop() {
        getTracker().stop();
    }

    /**
     * 获取跟踪器
     * 
     * @return 跟踪器
     */
    private static ITracker getTracker() {
        return BaseConfig.getTracker();
    }
}
