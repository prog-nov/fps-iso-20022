package com.forms.beneform4j.core.util.cache.errorhandler;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 缓存异常处理器 <br>
 * Description : Beneform4j平台默认的缓存错误处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-24<br>
 */
public class Beneform4jCacheErrorHandler implements CacheErrorHandler {

    /**
     * 是否忽略缓存错误
     */
    private boolean ignoreCacheError;

    /**
     * 处理获取缓存时的异常
     */
    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        if (isIgnoreCacheError() && !commonHandler(exception)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010009, exception, "get", cache.getName());
        }
    }

    /**
     * 处理设置缓存时的异常
     */
    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        if (isIgnoreCacheError() && !commonHandler(exception)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010009, exception, "put", cache.getName());
        }
    }

    /**
     * 处理移除缓存时的异常
     */
    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        if (isIgnoreCacheError() && !commonHandler(exception)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010009, exception, "evict", cache.getName());
        }
    }

    /**
     * 处理清除缓存时的异常
     */
    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        if (isIgnoreCacheError() && !commonHandler(exception)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010009, exception, "clear", cache.getName());
        }
    }

    /**
     * 是否忽略缓存错误
     * 
     * @return 是否忽略缓存错误
     */
    public boolean isIgnoreCacheError() {
        return ignoreCacheError;
    }

    /**
     * 设置是否忽略缓存错误
     * 
     * @param ignoreCacheError 是否忽略缓存错误
     */
    public void setIgnoreCacheError(boolean ignoreCacheError) {
        this.ignoreCacheError = ignoreCacheError;
    }

    /**
     * 公共处理
     * 
     * @param exception 运行时异常
     * @return 是否已经处理完毕，如果处理完毕，就不再继续后续处理
     */
    protected boolean commonHandler(RuntimeException exception) {
        exception.printStackTrace();
        return false;
    }
}
