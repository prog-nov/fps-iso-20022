package com.forms.beneform4j.security.core.login.authc.impl;

import java.util.List;

import com.forms.beneform4j.security.core.login.authc.IAuthenticator;
import com.forms.beneform4j.security.core.login.handler.ILoginHandler;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.info.impl.BaseAuthenticationInfo;
import com.forms.beneform4j.security.core.login.listener.ILoginListener;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 基本认证器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class BaseAuthenticator implements IAuthenticator {

    /**
     * 处理器列表
     */
    private List<ILoginHandler> handlers;

    /**
     * 侦听器列表
     */
    private List<ILoginListener> listeners;

    @Override
    public IAuthenticationInfo login(IAuthenticationToken authenticationToken) {
        BaseAuthenticationInfo info = new BaseAuthenticationInfo();
        List<ILoginListener> listeners = getListeners();
        try {
            if (null != listeners) {
                for (ILoginListener listener : listeners) {
                    listener.beforeLogin(authenticationToken);
                }
            }
            this.handler(authenticationToken, info);
            if (null != listeners) {
                boolean success = info.isSuccess();
                for (ILoginListener listener : listeners) {
                    if (success) {
                        listener.onLoginSuccess(authenticationToken, info);
                    } else {
                        listener.onLoginFailure(authenticationToken, info);
                    }
                }
            }
        } catch (Exception e) {
            if (null != listeners) {
                for (ILoginListener listener : listeners) {
                    listener.onLoginException(authenticationToken, info, e);
                }
            }
            e.printStackTrace();
        }
        return info;
    }

    private void handler(IAuthenticationToken authenticationToken, IAuthenticationInfo info) {
        List<ILoginHandler> handlers = getHandlers();
        if (null != handlers) {
            for (ILoginHandler handler : handlers) {
                handler.handler(authenticationToken, info);
                if (!info.isSuccess()) {
                    break;
                }
            }
        }
    }

    public List<ILoginHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<ILoginHandler> handlers) {
        this.handlers = handlers;
    }

    public List<ILoginListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ILoginListener> listeners) {
        this.listeners = listeners;
    }
}
