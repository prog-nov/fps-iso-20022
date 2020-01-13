package com.forms.beneform4j.security.core.logout;

import java.util.List;
import java.util.Set;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.security.core.logout.handler.ILogoutHandler;
import com.forms.beneform4j.security.core.logout.info.ILogoutInfo;
import com.forms.beneform4j.security.core.logout.info.impl.BaseLogoutInfo;
import com.forms.beneform4j.security.core.logout.provider.IExpiredSessionProvider;
import com.forms.beneform4j.security.core.session.ISession;
import com.forms.beneform4j.security.core.session.ISessionManager;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 退出类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
public class BaseLogout {

    private List<IExpiredSessionProvider> providers;

    private List<ILogoutHandler> handlers;

    public void logout() {
        ISessionManager manager = Beneform4jConfig.getSessionManager();
        List<IExpiredSessionProvider> providers = getProviders();
        List<ILogoutHandler> handlers = getHandlers();
        if (null != providers && !providers.isEmpty() && null != handlers && !handlers.isEmpty()) {
            for (IExpiredSessionProvider provider : providers) {
                Set<ISession> sessions = provider.getExpiredSessions();
                if (null != sessions && !sessions.isEmpty()) {
                    for (ISession session : sessions) {
                        ILogoutInfo info = new BaseLogoutInfo(session);
                        for (ILogoutHandler handler : handlers) {
                            handler.handler(info);
                            // if(!info.isSuccess()){
                            // break;
                            // }
                        }
                        if (null != manager) {
                            manager.delete(session.getId());
                        }
                    }
                }
            }
        }
    }

    public List<IExpiredSessionProvider> getProviders() {
        return providers;
    }

    public void setProviders(List<IExpiredSessionProvider> providers) {
        this.providers = providers;
    }

    public List<ILogoutHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<ILogoutHandler> handlers) {
        this.handlers = handlers;
    }
}
