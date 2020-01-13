package com.forms.beneform4j.web.springmvc.handleradapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.springmvc.handlermapping.ViewMappingHandlerMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 支持请求默认视图映射的处理器适配器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Component
public class ViewMappingHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ViewMappingHandlerMapping.ViewMappingRequestHandler);
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return ((ViewMappingHandlerMapping.ViewMappingRequestHandler) handler).handler();
    }

    @Override
    public long getLastModified(HttpServletRequest request, Object handler) {
        return -1L;
    }

}
