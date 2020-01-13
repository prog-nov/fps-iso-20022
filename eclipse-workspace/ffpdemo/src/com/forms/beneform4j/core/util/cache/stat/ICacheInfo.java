package com.forms.beneform4j.core.util.cache.stat;

import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.core.util.annotation.Beta;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public interface ICacheInfo {

    /**
     * 缓存名称
     * 
     * @return 缓存名称
     */
    String getCacheName();

    /**
     * 缓存类型，如ConcurrentMap、Ehcache、Redis等
     * 
     * @return 缓存类型
     */
    String getCacheType();

    /**
     * 缓存容器是否动态
     * 
     * @return 缓存容器是否动态
     */
    @Beta
    boolean isDynamic();

    /**
     * 缓存容器大小
     * 
     * @return 缓存容器大小
     */
    @Beta
    int getCapacity();

    /**
     * 已缓存数目
     * 
     * @return 已缓存数目
     */
    @Beta
    int getSize();

    /**
     * 被访问次数
     * 
     * @return 被访问次数
     */
    @Beta
    int getVisitNum();

    /**
     * 命中缓存次数
     * 
     * @return 命中缓存次数
     */
    @Beta
    int getHitNum();

    /**
     * 所有缓存条目的键值
     * 
     * @return 所有缓存条目的键值
     */
    Set<Object> getKeys();

    /**
     * 缓存实现相关的属性
     * 
     * @return 缓存实现相关的属性
     */
    @Beta
    Map<String, Object> getNativeProperties();

    /**
     * 描述
     * 
     * @return 描述
     */
    String getDes();
}
