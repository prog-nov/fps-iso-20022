package com.forms.beneform4j.web.springmvc.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.web.annotation.User;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户参数解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-13<br>
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return IUser.class.isAssignableFrom(parameter.getParameterType()) || parameter.hasParameterAnnotation(User.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        IUser user = getUser(webRequest);
        if (null != user) {
            if (IUser.class.isAssignableFrom(parameter.getParameterType())) {
                return user;
            } else {
                User anno = parameter.getParameterAnnotation(User.class);
                String property = anno.value();
                if (CoreUtils.isBlank(property) || "id".equalsIgnoreCase(property) || "userId".equalsIgnoreCase(property)) {
                    if (String.class.isAssignableFrom(parameter.getParameterType())) {
                        return user.getUserId();
                    } else {
                        return CoreUtils.getProperty(user, "userId", parameter.getParameterType());
                    }
                } else {
                    return CoreUtils.getProperty(user, property, parameter.getParameterType());
                }
            }
        }
        return user;
    }

    protected IUser getUser(NativeWebRequest webRequest) {
        return null;
    }
}
