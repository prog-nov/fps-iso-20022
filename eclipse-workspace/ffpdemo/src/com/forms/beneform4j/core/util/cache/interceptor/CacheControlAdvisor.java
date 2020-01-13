package com.forms.beneform4j.core.util.cache.interceptor;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.interceptor.BeanFactoryCacheOperationSourceAdvisor;
import org.springframework.cache.interceptor.CacheOperation;
import org.springframework.cache.interceptor.CacheOperationSource;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.cache.Caches;
import com.forms.beneform4j.core.util.cache.interceptor.impl.CacheMethod;
import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存拦截切面，有两个功能，设置默认缓存名称、缓存权限校验，开发人员可以通过设置不同的拦截器实现任意权限控制<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
// @Component
// @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class CacheControlAdvisor extends BeanFactoryCacheOperationSourceAdvisor {

    /**
     * 
     */
    private static final long serialVersionUID = -6233191503670217581L;

    private ICacheInterceptor interceptor;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        // 调用父类方法
        super.setBeanFactory(beanFactory);
        // 设置优先级为最高
        super.setOrder(HIGHEST_PRECEDENCE);
        // 获取配置的缓存操作提取源，用于定义切点
        final CacheOperationSource cacheOperationSource = beanFactory.getBean(CacheOperationSource.class);
        // 手工设置
        super.setCacheOperationSource(cacheOperationSource);
        // 手工设置切面逻辑
        super.setAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                Method method = invocation.getMethod();
                Class<?> targetClass = getTargetClass(invocation.getThis());
                Collection<CacheOperation> operations = cacheOperationSource.getCacheOperations(method, targetClass);
                checkCacheNamesAndSetDefaultCacheName(operations, targetClass.getName());
                ICacheInterceptor interceptor = getInterceptor();
                if (null == interceptor) {
                    return invocation.proceed();
                } else {
                    ICacheMethod cacheMethod = new CacheMethod(method, targetClass, operations);
                    try {
                        interceptor.onBeforeCacheOperations(cacheMethod);
                        Object rs = invocation.proceed();
                        return rs;
                    } finally {
                        interceptor.onAfterCacheOperations(cacheMethod);
                    }
                }
            }
        });
    }

    public ICacheInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(ICacheInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    private void checkCacheNamesAndSetDefaultCacheName(Collection<CacheOperation> operations, String targetClassName) {
        String defaultCacheName = BaseConfig.getDefaultCacheName();
        boolean hasDefault = !CoreUtils.isBlank(defaultCacheName);
        for (CacheOperation operation : operations) {
            Set<String> cacheNames = operation.getCacheNames();
            if (hasDefault && cacheNames.isEmpty()) {
                cacheNames.add(defaultCacheName);
            }
            for (String name : cacheNames) {
                Caches.checkCacheName(name, targetClassName);
            }
        }
    }

    private Class<?> getTargetClass(Object target) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);
        if (targetClass == null && target != null) {
            targetClass = target.getClass();
        }
        return targetClass;
    }
}
