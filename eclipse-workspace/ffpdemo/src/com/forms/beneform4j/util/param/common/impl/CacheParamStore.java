package com.forms.beneform4j.util.param.common.impl;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;

import com.forms.beneform4j.core.util.cache.Caches;
import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.param.common.IParamStore;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用缓存的参数存储器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public class CacheParamStore<P extends IParam> implements IParamStore<P> {

    private final String storeName;

    private Cache cache;

    public CacheParamStore(String storeName) {
        super();
        this.storeName = storeName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public P get(String name) {
        if (null != cache) {
            ValueWrapper valueWrapper = cache.get(name);
            if (null != valueWrapper) {
                return (P) valueWrapper.get();
            }
        }
        return null;
    }

    @Override
    public void remove(String name) {
        if (null != cache) {
            cache.evict(name);
        }
    }

    @Override
    public void save(String name, P value) {
        if (null == cache) {
            synchronized (CacheParamStore.class) {
                if (null == cache) {
                    Cache c = Caches.getCache(CacheParamStore.class, storeName);
                    cache = c;
                }
            }
        }
        cache.put(name, value);
    }

    @Override
    public boolean contains(String name) {
        return null != cache && null != cache.get(name);
    }

    @Override
    public void clear() {
        if (null != cache) {
            cache.clear();
        }
    }
}
