package com.forms.beneform4j.webapp.common.form;

import java.io.Serializable;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 公共Form<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-15<br>
 */
public class BaseForm implements Serializable {

    private static final long serialVersionUID = -6569262238202362538L;

    /**
     * 用户登录后的会话对象
     */
    private ISession session;

    /**
     * 获取用户登录后的会话对象
     * 
     * @return 用户登录后的会话对象
     */
    public ISession getSession() {
        return session;
    }

    /**
     * 设置用户登录后的会话对象
     * 
     * @param session 用户登录后的会话对象
     */
    public void setSession(ISession session) {
        this.session = session;
    }

    /**
     * 获取用户对象
     * 
     * @return
     */
    public IUser getUser() {
        return session == null ? null : session.getUser();
    }

    /**
     * 属性设置前的处理
     */
    public void beforePropertiesSet() {

    }

    /**
     * 属性设置后的处理
     */
    public void afterPropertiesSet() {

    }
}
