package com.forms.beneform4j.security.core.session.impl;

import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;

import com.forms.beneform4j.security.core.session.ISession;
import com.forms.beneform4j.security.core.session.ISessionManager;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Spring会话管理代理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-9<br>
 */
public class SpringSessionManagerProxy implements ISessionManager {

    private final SessionRepository<ExpiringSession> proxy;

    public SpringSessionManagerProxy(SessionRepository<ExpiringSession> proxy) {
        super();
        this.proxy = proxy;
    }

    @Override
    public ISession createSession() {
        ExpiringSession session = proxy.createSession();
        if (null != session) {
            return new SpringSessionProxy(session);
        }
        return null;
    }

    @Override
    public void save(ISession session) {
        if (session instanceof SpringSessionProxy) {
            ExpiringSession es = ((SpringSessionProxy) session).getExpiringSession();
            proxy.save(es);
        } else {
            throw new UnsupportedOperationException("can not save the session.");
        }
    }

    @Override
    public ISession getSession(String id) {
        ExpiringSession session = proxy.getSession(id);
        if (null != session) {
            return new SpringSessionProxy(session);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        proxy.delete(id);
    }
}
