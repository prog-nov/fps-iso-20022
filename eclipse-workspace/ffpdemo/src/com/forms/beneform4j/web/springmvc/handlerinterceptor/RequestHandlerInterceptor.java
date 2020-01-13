package com.forms.beneform4j.web.springmvc.handlerinterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.servlet.filter.RequestFilter;
import com.forms.beneform4j.web.view.ViewMappingHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求拦截器，和{@link RequestFilter}配合使用<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class RequestHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * 执行请求前处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ServletHelp.setRequestAndResponse(request, response);
        return true;
    }

    /**
     * 正常完成处理
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        IRequestInfo info = ServletHelp.getRequestInfo();
        if (null != info && null != modelAndView) {
            String overrideView = ViewMappingHolder.getOverrideView(info);
            if (!CoreUtils.isBlank(overrideView)) {
                modelAndView.setViewName(overrideView);
            }
            if (!modelAndView.hasView()) {
                String defaultView = ViewMappingHolder.getDefaultView(info);
                if (!CoreUtils.isBlank(defaultView)) {
                    modelAndView.setViewName(defaultView);
                }
            }
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 完成请求后处理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ServletHelp.remove();
    }
}
