package com.forms.beneform4j.security.core.session.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.forms.beneform4j.security.core.session.ISession;
import com.forms.beneform4j.security.core.session.ISessionManager;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 针对MapSession的会话管理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public class MapSessionManager implements ISessionManager {

    /**
     * 如果不为空，则覆盖{@link ISession#setMaxInactiveIntervalInSeconds(int)}.
     */
    private Integer defaultMaxInactiveInterval;

    /**
     * 多个会话Id对应的会话
     */
    private final Map<String, ISession> sessions;

    public MapSessionManager() {
        this(new ConcurrentHashMap<String, ISession>());
    }

    public MapSessionManager(Map<String, ISession> sessions) {
        if (sessions == null) {
            throw new IllegalArgumentException("sessions cannot be null");
        }
        this.sessions = sessions;
    }

    public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
        this.defaultMaxInactiveInterval = Integer.valueOf(defaultMaxInactiveInterval);
    }

    public void save(ISession session) {
        sessions.put(session.getId(), new MapSession(session));
    }

    public ISession getSession(String id) {
        ISession saved = sessions.get(id);
        if (saved == null) {
            return null;
        }
        if (saved.isExpired()) {
            delete(saved.getId());
            return null;
        }
        return new MapSession(saved);
    }

    public void delete(String id) {
        sessions.remove(id);
    }

    public ISession createSession() {
        ISession result = new MapSession();
        if (defaultMaxInactiveInterval != null) {
            result.setMaxInactiveIntervalInSeconds(defaultMaxInactiveInterval);
        }
        return result;
    }
}
