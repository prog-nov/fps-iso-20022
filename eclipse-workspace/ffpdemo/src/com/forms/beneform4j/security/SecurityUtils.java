package com.forms.beneform4j.security;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMeta;
import com.forms.beneform4j.security.core.common.info.ISecurityInfo;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.security.core.session.ISession;
import com.forms.beneform4j.security.core.session.ISessionManager;
import com.forms.beneform4j.util.Tool;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 安全层工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-10<br>
 */
public class SecurityUtils {

    public static void setSecurityInfo(ISecurityInfo info, String code, Object... params) {
        if (null != info && null != code) {
            info.setCode(code);
            info.setSuccess(false);
            IExceptionMeta meta = Throw.lookupExceptionMeta(code, null);
            if (null != meta && null != meta.getMessageKey()) {
                String message = Tool.LOCALE.getMessage(meta.getMessageKey(), params);
                info.setMessage(message);
            }
        }
    }

    public static void setSecurityCodeAndMessage(ISecurityInfo info, String code, String message) {
        if (null != info && null != code) {
            info.setCode(code);
            info.setMessage(message);
            info.setSuccess(false);
        }
    }

    public static ISessionManager getSessionManager() {
        return Beneform4jConfig.getSessionManager();
    }

    public static ISession getSession(String id) {
        ISessionManager manager = getSessionManager();
        return null == manager ? null : manager.getSession(id);
    }

    public static IUser getUser(String sessionId) {
        ISession session = getSession(sessionId);
        return null == session ? null : session.getUser();
    }
}
