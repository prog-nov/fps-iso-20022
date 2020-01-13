package com.forms.beneform4j.security.core.logout.provider;

import java.util.Set;

import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 过期会话提供者<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
public interface IExpiredSessionProvider {

    public Set<ISession> getExpiredSessions();
}
