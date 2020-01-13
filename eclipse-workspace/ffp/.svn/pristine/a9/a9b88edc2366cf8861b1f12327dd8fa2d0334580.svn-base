package com.forms.beneform4j.core.util.track.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用包含日期时间的随机数作为跟踪号的跟踪器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class RandomTracker extends AbstractThreadLocalTracker {

    private final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 是否包含日期时间
     */
    private boolean includeTime = true;

    /**
     * 随机部分的长度
     */
    private int randomCount = 8;

    /**
     * 生成跟踪号
     */
    @Override
    protected String generateTrackId() {
        StringBuffer sb = new StringBuffer();
        if (isIncludeTime()) {
            sb.append(df.format(new Date()));
        }
        int randomCount = Math.max(4, getRandomCount());
        sb.append(RandomStringUtils.randomNumeric(randomCount));
        return sb.toString();
    }

    public boolean isIncludeTime() {
        return includeTime;
    }

    public void setIncludeTime(boolean includeTime) {
        this.includeTime = includeTime;
    }

    public int getRandomCount() {
        return randomCount;
    }

    public void setRandomCount(int randomCount) {
        this.randomCount = randomCount;
    }
}
