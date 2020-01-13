package com.forms.beneform4j.web.springmvc.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.web.servlet.ServletHelp;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求信息参数转换器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-6<br>
 */
public class RequestInfoArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否为请求对象
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return IRequestInfo.class.isAssignableFrom(paramType);
    }

    /**
     * 解析请求对象
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return ServletHelp.getRequestInfo();
    }
}
