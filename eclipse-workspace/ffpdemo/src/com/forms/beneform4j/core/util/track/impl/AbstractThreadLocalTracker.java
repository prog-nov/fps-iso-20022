package com.forms.beneform4j.core.util.track.impl;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.track.ITracker;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用线程本地变量实现的跟踪器抽象实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public abstract class AbstractThreadLocalTracker implements ITracker {

    private static final ThreadLocal<String> trackIdLocal = new ThreadLocal<String>();

    /**
     * 生成跟踪号
     * 
     * @return 跟踪号
     */
    abstract protected String generateTrackId();

    /**
     * 开始跟踪
     */
    @Override
    public void start() {
        String id = trackIdLocal.get();
        if (!CoreUtils.isBlank(id)) {
            CommonLogger.debug("the tracker has started, the trackId is " + id + ".");
        } else {
            this.startNewTracker();
        }
    }

    /**
     * 开始新的跟踪
     */
    private void startNewTracker() {
        trackIdLocal.set(generateTrackId());
    }

    /**
     * 使用指定跟踪号开始新的跟踪
     */
    @Override
    public void start(String trackId) {
        String id = trackIdLocal.get();
        if (!CoreUtils.isBlank(id)) {
            CommonLogger.debug("the old trackId is " + id + ", and override using new trackId " + trackId + ".");
        }
        trackIdLocal.set(trackId);
    }

    /**
     * 判断是否正在跟踪
     */
    @Override
    public boolean isTracking() {
        return !CoreUtils.isBlank(trackIdLocal.get());
    }

    /**
     * 获取目前的跟踪号
     */
    @Override
    public String getCurrentTrackId() {
        return trackIdLocal.get();
    }

    /**
     * 停止跟踪
     */
    @Override
    public void stop() {
        trackIdLocal.remove();
    }
}
