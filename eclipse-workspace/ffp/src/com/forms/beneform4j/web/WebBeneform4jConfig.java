package com.forms.beneform4j.web;

import java.util.List;

import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.util.annotation.Configable;
import com.forms.beneform4j.core.util.aop.IAopInterceptor;
import com.forms.beneform4j.util.json.serial.wrapper.IJsonWrapper;
import com.forms.beneform4j.web.path.IPathResolver;
import com.forms.beneform4j.web.request.log.IParamConvert;
import com.forms.beneform4j.web.request.log.IRequestLog;
import com.forms.beneform4j.web.view.IViewMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Web控制层平台配置类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class WebBeneform4jConfig extends Beneform4jConfig {

    /**
     * 表示分页查询的JSON包装器
     */
    @Configable
    private static IJsonWrapper pageJsonWrapper;

    /**
     * 表示默认操作的JSON包装器
     */
    @Configable
    private static IJsonWrapper defaultJsonWrapper;

    /**
     * 表示处理异常的JSON包装器
     */
    @Configable
    private static IJsonWrapper exceptionWrapper;

    /**
     * 默认视图映射
     */
    @Configable
    private static List<IViewMapping> viewMappings;

    /**
     * 请求日志
     */
    @Configable
    private static List<IRequestLog> requestLogs;

    /**
     * 请求日志参数转换器
     */
    @Configable
    private static IParamConvert paramConvert;

    /**
     * 控制层AOP拦截器
     */
    @Configable
    private static List<IAopInterceptor> controllerAopInterceptors;

    /**
     * 路径解析器
     */
    @Configable
    private static IPathResolver pathResolver;

    /**
     * 优先级占优的结果处理器
     */
    @Configable
    private static List<HandlerMethodReturnValueHandler> priorReturnValueHandlers;

    /**
     * 校验
     */
    @Override
    public void validate() {
        super.validate();

        // 执行当前配置类中的校验
    }

    public static IJsonWrapper getPageJsonWrapper() {
        return getComponent(pageJsonWrapper, IJsonWrapper.class, "page");
    }

    public void setPageJsonWrapper(IJsonWrapper pageJsonWrapper) {
        WebBeneform4jConfig.pageJsonWrapper = pageJsonWrapper;
    }

    public static IJsonWrapper getDefaultJsonWrapper() {
        return getComponent(defaultJsonWrapper, IJsonWrapper.class, "default");
    }

    public void setDefaultJsonWrapper(IJsonWrapper defaultJsonWrapper) {
        WebBeneform4jConfig.defaultJsonWrapper = defaultJsonWrapper;
    }

    public static IJsonWrapper getExceptionWrapper() {
        return getComponent(exceptionWrapper, IJsonWrapper.class, "exception");
    }

    public void setExceptionWrapper(IJsonWrapper exceptionWrapper) {
        WebBeneform4jConfig.exceptionWrapper = exceptionWrapper;
    }

    public static List<IViewMapping> getViewMappings() {
        return getComponents(viewMappings, IViewMapping.class);
    }

    public void setViewMappings(List<IViewMapping> viewMappings) {
        WebBeneform4jConfig.viewMappings = viewMappings;
    }

    public static List<IRequestLog> getRequestLogs() {
        return getComponents(requestLogs, IRequestLog.class);
    }

    public void setRequestLogs(List<IRequestLog> requestLogs) {
        WebBeneform4jConfig.requestLogs = requestLogs;
    }

    public static IParamConvert getParamConvert() {
        return paramConvert;
    }

    public void setParamConvert(IParamConvert paramConvert) {
        WebBeneform4jConfig.paramConvert = paramConvert;
    }

    public static List<IAopInterceptor> getControllerAopInterceptors() {
        return getComponents(controllerAopInterceptors, IAopInterceptor.class, "Controller");
    }

    public void setControllerAopInterceptors(List<IAopInterceptor> controllerAopInterceptors) {
        WebBeneform4jConfig.controllerAopInterceptors = controllerAopInterceptors;
    }

    public static IPathResolver getPathResolver() {
        return getComponent(pathResolver, IPathResolver.class);
    }

    public void setPathResolver(IPathResolver pathResolver) {
        WebBeneform4jConfig.pathResolver = pathResolver;
    }

    public static List<HandlerMethodReturnValueHandler> getPriorReturnValueHandlers() {
        return priorReturnValueHandlers;
    }

    public void setPriorReturnValueHandlers(List<HandlerMethodReturnValueHandler> priorReturnValueHandlers) {
        WebBeneform4jConfig.priorReturnValueHandlers = priorReturnValueHandlers;
    }
}
