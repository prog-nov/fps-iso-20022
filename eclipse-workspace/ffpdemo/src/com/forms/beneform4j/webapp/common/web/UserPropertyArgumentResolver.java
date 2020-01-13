package com.forms.beneform4j.webapp.common.web;

import org.springframework.web.context.request.NativeWebRequest;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.web.springmvc.argumentresolver.UserArgumentResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户相关参数解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-13<br>
 */
public class UserPropertyArgumentResolver extends UserArgumentResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    protected IUser getUser(NativeWebRequest webRequest) {
        return WebTool.getLoginUser();
    }

}
