package com.forms.beneform4j.core.util.cache.stat.ehcache;

import java.util.LinkedHashSet;

import org.springframework.cache.Cache;

import com.forms.beneform4j.core.util.cache.stat.base.CacheInfoSupport;

import net.sf.ehcache.Ehcache;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ehcache缓存信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class EhcacheCacheInfo extends CacheInfoSupport {

    @SuppressWarnings("unchecked")
    public EhcacheCacheInfo(Cache cache) {
        Ehcache ec = (Ehcache) cache.getNativeCache();
        this.cacheName = cache.getName();
        this.cacheType = "ConcurrentMap";
        this.keys = new LinkedHashSet<Object>(ec.getKeys());
        this.size = this.keys.size();
    }
}
