package com.forms.beneform4j.web.springmvc.handlermapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 代理处理器映射<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
public class ProxyHandlerMapping extends AbstractHandlerMapping {

    private List<IProxyHandler> proxyHandlers;

    @Override
    protected Object getHandlerInternal(HttpServletRequest request) throws Exception {
        if (null != proxyHandlers) {
            for (IProxyHandler proxyHandler : proxyHandlers) {
                if (proxyHandler.isSupport(request)) {
                    return new ProxyRequestHandler(request, proxyHandler);
                }
            }
        }
        return null;
    }

    public List<IProxyHandler> getProxyHandlers() {
        return proxyHandlers;
    }

    public void setProxyHandlers(List<IProxyHandler> proxyHandlers) {
        this.proxyHandlers = proxyHandlers;
    }

    public class ProxyRequestHandler {

        private final HttpServletRequest request;
        private final IProxyHandler proxyHandler;

        private ProxyRequestHandler(HttpServletRequest request, IProxyHandler proxyHandler) {
            this.request = request;
            this.proxyHandler = proxyHandler;
        }

        public ModelAndView handler() {
            return proxyHandler.handler(request);
        }
    }
}
