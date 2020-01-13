package com.forms.beneform4j.core.util.cache.stat.concurrent;

import java.util.LinkedHashSet;
import java.util.concurrent.ConcurrentMap;

import org.springframework.cache.Cache;

import com.forms.beneform4j.core.util.cache.stat.base.CacheInfoSupport;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : ConcurrentMap缓存信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class ConcurrentCacheInfo extends CacheInfoSupport {

    @SuppressWarnings("unchecked")
    public ConcurrentCacheInfo(Cache cache) {
        ConcurrentMap<Object, Object> nc = (ConcurrentMap<Object, Object>) cache.getNativeCache();
        this.cacheName = cache.getName();
        this.cacheType = "ConcurrentMap";
        this.keys = new LinkedHashSet<Object>(nc.keySet());
        this.size = this.keys.size();
        this.capacity = -1;
        this.dynamic = true;
    }
}
