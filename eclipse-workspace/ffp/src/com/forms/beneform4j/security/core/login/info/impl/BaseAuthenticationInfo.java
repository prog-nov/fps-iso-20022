package com.forms.beneform4j.security.core.login.info.impl;

import com.forms.beneform4j.security.core.common.info.impl.BaseSecurityInfo;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 认证返回信息的实现类，默认为认证成功状态，如认证失败，需要设置失败状态代码和描述<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class BaseAuthenticationInfo extends BaseSecurityInfo implements IAuthenticationInfo {

    /**
     * 会话
     */
    private ISession session;

    /**
     * 认证用户
     */
    private IUser user;

    public BaseAuthenticationInfo() {
        super(true);
    }

    @Override
    public ISession getSession() {
        return session;
    }

    @Override
    public void setSession(ISession session) {
        this.session = session;
        if (null != session) {
            if (null != user && null == session.getUser()) {
                session.setUser(user);
            } else if (null == user && null != session.getUser()) {
                this.user = session.getUser();
            } else if (null != user && null != session.getUser() && user.equals(session.getUser())) {
                throw new RuntimeException("the session user is not coherence");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.forms.beneform4j.security.core.login.info.IAuthenticationInfo#getUser()
     */
    @Override
    public IUser getUser() {
        if (null != user) {
            return user;
        } else if (null != session) {
            return session.getUser();
        }
        return null;
    }

    @Override
    public void setUser(IUser user) {
        this.user = user;
        if (null != session && null == session.getUser()) {
            session.setUser(user);
        }
    }
}
