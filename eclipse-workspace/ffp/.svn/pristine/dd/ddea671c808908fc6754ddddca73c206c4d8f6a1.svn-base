package com.forms.beneform4j.core.util.aop;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : AOP拦截器辅助类，由于拦截器接口有多个方法，该辅助类提供默认的空实现，开发人员只需继承该类，然后重写需要关注的方法即可<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-22<br>
 */
public class AopInterceptorSupport implements IAopInterceptor {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean before(Map<String, Object> context, Object target, Method method, Object[] args) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean after(Map<String, Object> context, Object target, Method method, Object[] args, Object result) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean afterException(Map<String, Object> context, Object target, Method method, Object[] args, Throwable throwable) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean afterReturn(Map<String, Object> context, Object target, Method method, Object[] args) {
        return true;
    }
}
