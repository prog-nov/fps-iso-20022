package com.forms.beneform4j.security.core.access.handler.impl;

import java.util.Map;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.handler.IAccessControlHandler;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.security.core.common.pathmatcher.PathMatcherSupport;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的路径匹配访问控制处理器，路径不匹配，不做任何处理，路径匹配，如果有路径变量，会存放于返回的授权信息中<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class AbstractPathMatcherAccessControlHandler extends PathMatcherSupport implements IAccessControlHandler {

    @Override
    public void handler(IRequestInfo requestInfo, IAuthorizationInfo info) {
        String url = requestInfo.getRequestUrl();
        String pattern = super.getMatcherPattern(url);
        if (null != pattern) {
            Map<String, String> vs = super.extractUriTemplateVariables(pattern, url);
            if (null != vs) {
                for (String name : vs.keySet()) {
                    info.setProperty(name, vs.get(name));
                }
            }
            this.doHandler(requestInfo, info);
        }
    }

    protected abstract void doHandler(IRequestInfo requestInfo, IAuthorizationInfo info);
}
