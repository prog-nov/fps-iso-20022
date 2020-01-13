package com.forms.beneform4j.web.springmvc.exceptionresolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Beneform4jRuntimeException;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.json.serial.wrapper.IJsonWrapper;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.WebUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台异常处理<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class Beneform4jHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

    /**
     * 异常存放至模型中的属性名称
     */
    private String exceptionAttribute = "exception";

    /**
     * 异常处理结果存放至模型中的属性名称
     */
    private String exceptionHandlerResultsAttribute = "exceptionHandlerResults";

    /**
     * 异常代码与HTTP状态码之间的映射
     */
    private Map<String, String> exceptionCodeHttpStatusMapping;

    private MediaType jsonContentType = MediaType.APPLICATION_JSON_UTF8;

    public Beneform4jHandlerExceptionResolver() {
        setOrder(1);
    }

    /**
     * 处理异常
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            // ModelAndView mv = super.resolveException(request, response, handler, ex);
            // if(null != mv){
            // return mv;
            // }
            if (ex instanceof BindException) {
                return handleBindException((BindException) ex, request, response, handler);
            }

            Beneform4jRuntimeException be = Throw.createRuntimeException(ex);
            List<IExceptionHandler> handlers = be.getHandlers();
            String view = be.getView();
            List<Object> results = handlerException(handlers, be);
            if (isAjaxRequest(request, handler)) {
                this.handlerAjaxException(request, response, handler, be, view, results);
                return new ModelAndView();
            } else {
                String statusCode = this.getHttpStatus(be.getCode());
                if (!Tool.CHECK.isBlank(statusCode)) {
                    response.sendError(Integer.parseInt(statusCode));
                    return new ModelAndView();
                } else {
                    return handlerException(request, response, handler, be, view, results);
                }
            }
        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
            }
            return new ModelAndView();
        }
    }

    @Override
    protected ModelAndView handleBindException(BindException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (isAjaxRequest(request, handler)) {
            this.handlerAjaxException(request, response, handler, ex);
            return new ModelAndView();
        } else {
            return super.handleBindException(ex, request, response, handler);
        }
    }

    /**
     * 是否为Ajax请求
     * 
     * @param request
     * @return
     */
    protected boolean isAjaxRequest(HttpServletRequest request, Object handler) {
        if (WebUtils.isAjaxRequest(request)) {
            return true;
        } else if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            return hm.getMethodAnnotation(ResponseBody.class) != null;
        } else {
            return false;
        }
    }

    /**
     * 处理Ajax的异常
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 请求处理器
     * @param be 平台异常
     * @param view 异常配置的逻辑视图
     * @param results 后台处理异常的结果列表
     */
    protected void handlerAjaxException(HttpServletRequest request, HttpServletResponse response, Object handler, Beneform4jRuntimeException be, String view, List<Object> results) {
        this.handlerAjaxException(request, response, handler, be);
    }

    /**
     * 处理Ajax的异常
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 请求处理器
     * @param e 异常
     */
    private void handlerAjaxException(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable e) {
        try {
            IJsonWrapper exceptionWrapper = WebBeneform4jConfig.getExceptionWrapper();
            if (null != exceptionWrapper) {
                @SuppressWarnings("resource")
                ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
                HttpHeaders headers = outputMessage.getHeaders();
                headers.setContentType(this.getJsonContentType());

                Object target = exceptionWrapper.wrap(e);
                Tool.JSON.serialize(outputMessage.getBody(), target);

                outputMessage.getBody().flush();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 处理平台异常
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 请求处理器
     * @param be 平台异常
     * @param view 异常配置的逻辑视图
     * @param results 后台处理异常的结果列表
     * @return
     */
    protected ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Object handler, Beneform4jRuntimeException be, String view, List<Object> results) {
        ModelAndView mv = new ModelAndView(view);
        String exceptionAttribute = this.getExceptionAttribute();
        if (!CoreUtils.isBlank(exceptionAttribute)) {
            mv.addObject(exceptionAttribute, be);
        }
        String exceptionHandlerResultsAttribute = this.getExceptionHandlerResultsAttribute();
        if (!CoreUtils.isBlank(exceptionHandlerResultsAttribute)) {
            mv.addObject(exceptionHandlerResultsAttribute, results);
        }
        CommonLogger.info("handler exception, return view :" + view);
        return mv;
    }

    protected String getHttpStatus(String exceptionCode) {
        if (this.exceptionCodeHttpStatusMapping == null || this.exceptionCodeHttpStatusMapping.isEmpty() || exceptionCode == null) {
            return null;
        }
        return this.exceptionCodeHttpStatusMapping.get(exceptionCode);
    }

    public String getExceptionAttribute() {
        return exceptionAttribute;
    }

    public void setExceptionAttribute(String exceptionAttribute) {
        this.exceptionAttribute = exceptionAttribute;
    }

    public String getExceptionHandlerResultsAttribute() {
        return exceptionHandlerResultsAttribute;
    }

    public void setExceptionHandlerResultsAttribute(String exceptionHandlerResultsAttribute) {
        this.exceptionHandlerResultsAttribute = exceptionHandlerResultsAttribute;
    }

    public MediaType getJsonContentType() {
        return jsonContentType;
    }

    public void setJsonContentType(MediaType jsonContentType) {
        this.jsonContentType = jsonContentType;
    }

    public Map<String, String> getExceptionCodeHttpStatusMapping() {
        return exceptionCodeHttpStatusMapping;
    }

    public void setExceptionCodeHttpStatusMapping(Map<String, String> exceptionCodeHttpStatusMapping) {
        this.exceptionCodeHttpStatusMapping = exceptionCodeHttpStatusMapping;
    }

    /**
     * 处理平台异常
     * 
     * @param handlers
     * @param throwable
     * @return
     */
    private List<Object> handlerException(List<IExceptionHandler> handlers, Throwable throwable) {
        if (null == throwable || null == handlers || handlers.isEmpty()) {
            return null;
        }
        List<Object> rs = new ArrayList<Object>();
        for (IExceptionHandler handler : handlers) {
            try {
                Object obj = handler.handler(throwable);
                rs.add(obj);
            } catch (Throwable he) {
                if (handler.ignoreHandlerException()) {
                    rs.add(null);
                } else {
                    rs.add(he);
                }
            }
        }
        return rs;
    }
}
