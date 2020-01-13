package com.forms.beneform4j.core.util.cache.interceptor.impl;

import com.forms.beneform4j.core.util.cache.interceptor.ICacheInterceptor;
import com.forms.beneform4j.core.util.cache.interceptor.ICacheMethod;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存拦截支持类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class CacheInterceptorSupport implements ICacheInterceptor {

    /**
     * <p>
     * 空实现，不做任何处理
     * </p>
     * {@inheritDoc}
     */
    @Override
    public void onBeforeCacheOperations(ICacheMethod cacheMethod) {

    }

    /**
     * <p>
     * 空实现，不做任何处理
     * </p>
     * {@inheritDoc}
     */
    @Override
    public void onAfterCacheOperations(ICacheMethod cacheMethod) {

    }
}
