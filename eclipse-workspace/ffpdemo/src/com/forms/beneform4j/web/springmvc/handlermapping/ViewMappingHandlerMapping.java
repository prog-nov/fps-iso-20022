package com.forms.beneform4j.web.springmvc.handlermapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.view.ViewMappingHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 支持请求默认视图映射的处理器映射<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Component
public class ViewMappingHandlerMapping extends AbstractHandlerMapping {

    public ViewMappingHandlerMapping() {
        super.setOrder(Integer.MAX_VALUE - 1);
    }

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        IRequestInfo info = ServletHelp.getRequestInfo();
        String defaultView = ViewMappingHolder.getDefaultView(info);
        if (!CoreUtils.isBlank(defaultView)) {
            return new ViewMappingRequestHandler(defaultView);
        }
        return null;
    }

    public class ViewMappingRequestHandler {

        private String defaultView;

        private ViewMappingRequestHandler(String defaultView) {
            this.defaultView = defaultView;
        }

        public ModelAndView handler() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName(defaultView);
            return mv;
        }
    };
}
