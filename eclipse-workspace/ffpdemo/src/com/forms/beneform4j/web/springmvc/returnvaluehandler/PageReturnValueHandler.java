package com.forms.beneform4j.web.springmvc.returnvaluehandler;

import java.lang.reflect.Method;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebBeneform4jConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分页查询返回结果处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Deprecated
public class PageReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method method = returnType.getMethod();
        if (Collection.class.isAssignableFrom(method.getReturnType())) {// 返回集合类型，并且含有分页参数
            for (Class<?> pt : method.getParameterTypes()) {
                if (IPage.class.isAssignableFrom(pt)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("application/json;charset=UTF-8");
        Object target = WebBeneform4jConfig.getPageJsonWrapper().wrap(returnValue);
        Tool.JSON.serialize(response.getOutputStream(), target);
        response.getOutputStream().flush();
    }

}
