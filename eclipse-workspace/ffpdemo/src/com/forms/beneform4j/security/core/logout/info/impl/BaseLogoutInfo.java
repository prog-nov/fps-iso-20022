package com.forms.beneform4j.security.core.logout.info.impl;

import com.forms.beneform4j.security.core.common.info.impl.BaseSecurityInfo;
import com.forms.beneform4j.security.core.logout.info.ILogoutInfo;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录退出信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
public class BaseLogoutInfo extends BaseSecurityInfo implements ILogoutInfo {

    /**
     * 会话
     */
    private final ISession session;

    public BaseLogoutInfo(ISession session) {
        super(true);
        this.session = session;
    }

    @Override
    public ISession getSession() {
        return session;
    }
}
