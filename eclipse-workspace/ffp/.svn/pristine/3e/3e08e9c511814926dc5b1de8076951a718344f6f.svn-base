package com.forms.beneform4j.core.util.cache.interceptor.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.cache.interceptor.CacheOperation;

import com.forms.beneform4j.core.util.cache.interceptor.ICacheMethod;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分组校验缓存的拦截器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class GroupCacheInterceptor extends CacheInterceptorSupport {

    private Map<Pattern, Pattern> cacheNamePatternMapping;

    @Override
    public void onBeforeCacheOperations(ICacheMethod cacheMethod) {
        Set<String> cacheNames = getCacheNames(cacheMethod);
        for (String cacheName : cacheNames) {
            checkVisitCacheRight(cacheMethod, cacheName);
        }
    }

    /**
     * 检查访问缓存的权限
     * 
     * @param cacheMethod
     * @param cacheName
     */
    private void checkVisitCacheRight(ICacheMethod cacheMethod, String cacheName) {
        String targetClassName = cacheMethod.getTargetClass().getName();
        Map<Pattern, Pattern> cacheNamePatternMapping = getCacheNamePatternMapping();
        if (null != cacheNamePatternMapping) {
            for (Pattern name : cacheNamePatternMapping.keySet()) {
                if (name.matcher(cacheName).find()) {
                    Pattern allow = cacheNamePatternMapping.get(name);
                    if (!allow.matcher(targetClassName).find()) {
                        Throw.throwRuntimeException(ExceptionCodes.BF010006, targetClassName, cacheName);
                    }
                }
            }
        }
    }

    private Set<String> getCacheNames(ICacheMethod cacheMethod) {
        Collection<CacheOperation> operations = cacheMethod.getOperations();
        Set<String> cacheNames = new HashSet<String>();
        for (CacheOperation operation : operations) {
            cacheNames.addAll(operation.getCacheNames());
        }
        return cacheNames;
    }

    public Map<Pattern, Pattern> getCacheNamePatternMapping() {
        return cacheNamePatternMapping;
    }

    public void setCacheNamePatternMapping(Map<Pattern, Pattern> cacheNamePatternMapping) {
        this.cacheNamePatternMapping = cacheNamePatternMapping;
    }
}
