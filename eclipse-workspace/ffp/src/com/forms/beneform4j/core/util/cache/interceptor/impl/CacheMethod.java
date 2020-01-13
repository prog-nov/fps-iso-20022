package com.forms.beneform4j.core.util.cache.interceptor.impl;

import java.lang.reflect.Method;
import java.util.Collection;

import org.springframework.cache.interceptor.CacheOperation;

import com.forms.beneform4j.core.util.cache.interceptor.ICacheMethod;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存方法实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class CacheMethod implements ICacheMethod {

    private Method method;

    private Class<?> targetClass;

    private Collection<CacheOperation> operations;

    public CacheMethod(Method method, Class<?> targetClass, Collection<CacheOperation> operations) {
        this.method = method;
        this.targetClass = targetClass;
        this.operations = operations;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public Collection<CacheOperation> getOperations() {
        return operations;
    }

    public void setOperations(Collection<CacheOperation> operations) {
        this.operations = operations;
    }
}
