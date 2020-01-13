package com.forms.beneform4j.security.core.session.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用Map实现简单会话<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public class MapSession implements ISession {

    private static final long serialVersionUID = -3034846963262198812L;

    /**
     * 非活动会话持续时间
     */
    private static final int DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS = 1800;// 默认30分钟

    /**
     * 会话Id
     */
    private String id;

    /**
     * 会话属性
     */
    private Map<String, Object> sessionAttrs = new LinkedHashMap<String, Object>();

    /**
     * 登录用户
     */
    private IUser user;

    /**
     * 会话创建时间
     */
    private long creationTime = System.currentTimeMillis();

    /**
     * 最后请求时间,初始为系统当前时间
     */
    private long lastAccessedTime = creationTime;

    /**
     * 最大非活动会话时间,默认为30秒
     */
    private int maxInactiveIntervalInSeconds = DEFAULT_MAX_INACTIVE_INTERVAL_SECONDS;

    public MapSession() {
        this(UUID.randomUUID().toString());
    }

    public MapSession(String id) {
        this.id = id;
    }

    public MapSession(ISession session) {
        if (session == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        this.id = session.getId();
        this.user = session.getUser();
        this.sessionAttrs = new HashMap<String, Object>(session.getAttributeNames().size());
        for (String attrName : session.getAttributeNames()) {
            Object attrValue = session.getAttribute(attrName);
            this.sessionAttrs.put(attrName, attrValue);
        }
        this.lastAccessedTime = session.getLastAccessedTime();
        this.creationTime = session.getCreationTime();
        this.maxInactiveIntervalInSeconds = session.getMaxInactiveIntervalInSeconds();
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String getId() {
        return id;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setMaxInactiveIntervalInSeconds(int maxInactiveIntervalInSeconds) {
        this.maxInactiveIntervalInSeconds = maxInactiveIntervalInSeconds;
    }

    public int getMaxInactiveIntervalInSeconds() {
        return maxInactiveIntervalInSeconds;
    }

    public boolean isExpired() {
        return isExpired(System.currentTimeMillis());
    }

    private boolean isExpired(long now) {
        if (maxInactiveIntervalInSeconds < 0) {
            return false;
        }
        return now - TimeUnit.SECONDS.toMillis(maxInactiveIntervalInSeconds) >= lastAccessedTime;
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String attributeName) {
        return (T) sessionAttrs.get(attributeName);
    }

    public Set<String> getAttributeNames() {
        return sessionAttrs.keySet();
    }

    public void setAttribute(String attributeName, Object attributeValue) {
        if (attributeValue == null) {
            removeAttribute(attributeName);
        } else {
            sessionAttrs.put(attributeName, attributeValue);
        }
    }

    public void removeAttribute(String attributeName) {
        sessionAttrs.remove(attributeName);
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public IUser getUser() {
        return user;
    }

    public void setUser(IUser user) {
        this.user = user;
    }

    public boolean equals(Object obj) {
        return obj instanceof ISession && id.equals(((ISession) obj).getId());
    }

    public int hashCode() {
        return id.hashCode();
    }
}
