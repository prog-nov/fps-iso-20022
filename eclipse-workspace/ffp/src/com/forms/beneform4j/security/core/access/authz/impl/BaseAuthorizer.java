package com.forms.beneform4j.security.core.access.authz.impl;

import java.util.List;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.handler.IAccessControlHandler;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.access.info.impl.BaseAuthorizationInfo;
import com.forms.beneform4j.security.core.access.listener.IAccessControlListener;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 基本授权器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class BaseAuthorizer extends AbstractPathMatcherAuthorizer {

    /**
     * 访问控制处理器
     */
    private List<IAccessControlHandler> handlers;

    /**
     * 访问控制侦听器
     */
    private List<IAccessControlListener> listeners;

    @Override
    protected IAuthorizationInfo doPermitted(IRequestInfo requestInfo) {
        BaseAuthorizationInfo info = new BaseAuthorizationInfo();
        List<IAccessControlListener> listeners = getListeners();
        try {
            if (null != listeners) {
                for (IAccessControlListener listener : listeners) {
                    listener.beforePermitted(requestInfo);
                }
            }
            this.handler(requestInfo, info);
            if (null != listeners) {
                boolean pass = info.isSuccess();
                for (IAccessControlListener listener : listeners) {
                    if (pass) {
                        listener.onPermittedPass(requestInfo, info);
                    } else {
                        listener.onPermittedDeny(requestInfo, info);
                    }
                }
            }
        } catch (Exception e) {
            if (null != listeners) {
                for (IAccessControlListener listener : listeners) {
                    listener.onPermittedException(requestInfo, info, e);
                }
            }
            e.printStackTrace();
        }
        return info;
    }

    private void handler(IRequestInfo requestInfo, IAuthorizationInfo info) {
        List<IAccessControlHandler> handlers = getHandlers();
        if (null != handlers) {
            for (IAccessControlHandler handler : handlers) {
                handler.handler(requestInfo, info);
                if (!info.isSuccess()) {
                    break;
                }
            }
        }
    }

    public List<IAccessControlHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<IAccessControlHandler> handlers) {
        this.handlers = handlers;
    }

    public List<IAccessControlListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<IAccessControlListener> listeners) {
        this.listeners = listeners;
    }
}
