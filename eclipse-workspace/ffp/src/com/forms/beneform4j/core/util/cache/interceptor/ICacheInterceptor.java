package com.forms.beneform4j.core.util.cache.interceptor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存切面拦截器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public interface ICacheInterceptor {

    /**
     * 缓存操作执行前
     * 
     * @param cacheMethod 添加了缓存注解的方法
     */
    public void onBeforeCacheOperations(ICacheMethod cacheMethod);

    /**
     * 缓存操作执行后
     * 
     * @param cacheMethod 添加了缓存注解的方法
     */
    public void onAfterCacheOperations(ICacheMethod cacheMethod);
}
