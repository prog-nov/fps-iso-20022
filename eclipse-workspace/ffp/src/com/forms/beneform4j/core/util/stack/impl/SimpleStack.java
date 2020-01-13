package com.forms.beneform4j.core.util.stack.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.env.EnvConsts;
import com.forms.beneform4j.core.util.stack.IStack;
import com.forms.beneform4j.core.util.track.Tracker;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 简单的堆栈信息类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class SimpleStack implements IStack {

    /**
     * 
     */
    private static final long serialVersionUID = -6403970075093676020L;

    private static final Pattern blank = Pattern.compile("^(\\s+)");

    private String trackId;

    private Logger logger;

    private StackTraceElement stack;

    private String message;

    private Throwable throwable;

    public SimpleStack() {
        this.trackId = Tracker.getCurrentTrackId();
    }

    public SimpleStack(Logger logger, StackTraceElement stack, String message, Throwable throwable) {
        this.logger = logger;
        this.message = message;
        this.throwable = throwable;
        this.stack = stack;
        this.trackId = Tracker.getCurrentTrackId();
    }

    @Override
    public String getTrackId() {
        return trackId;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public StackTraceElement getStack() {
        return this.stack;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder().append("Host:[").append(CoreUtils.rightPad(EnvConsts.LOCALE_HOST, 15, " ")).append("] ").append("App-Name:[").append(BaseConfig.getAppName()).append("] ");

        String trackId = getTrackId();
        if (!CoreUtils.isBlank(trackId)) {
            sb.append("trackId:[").append(trackId).append("] ");
        }

        StackTraceElement s = getStack();
        if (null != s && !isIgnoreStack(s.getClassName())) {
            sb.append(s);
        }

        String msg = getCustomMessage();
        if (!CoreUtils.isBlank(this.message)) {
            if (this.beginWithLine(this.message)) {
                msg += EnvConsts.LINE_SEPARATOR + this.message.trim();
            } else {
                msg += this.message.trim();
            }
        }

        if (!CoreUtils.isBlank(msg)) {
            sb.append(msg);
        }

        return sb.toString();
    }

    protected String getCustomMessage() {
        return "";
    }

    @Override
    public Throwable getThrowable() {
        return this.throwable;
    }

    private boolean isIgnoreStack(String stack) {
        List<String> ignores = BaseConfig.getIgnoreStacks();
        return null != ignores && ignores.contains(stack);
    }

    private boolean beginWithLine(String message) {
        Matcher matcher = blank.matcher(message);
        if (matcher.find()) {
            for (char ch : matcher.group(1).toCharArray()) {
                if (ch == '\n') {
                    return true;
                }
            }
        }
        return false;
    }
}
