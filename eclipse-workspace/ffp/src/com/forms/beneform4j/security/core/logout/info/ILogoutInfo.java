package com.forms.beneform4j.security.core.logout.info;

import com.forms.beneform4j.security.core.common.info.ISecurityInfo;
import com.forms.beneform4j.security.core.session.ISession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录退出信息接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
public interface ILogoutInfo extends ISecurityInfo {

    /**
     * 获取会话对象
     * 
     * @return 会话对象
     */
    public ISession getSession();
}
