package com.forms.beneform4j.core.util.cache.stat;

import java.util.List;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存监控、管理、统计服务接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public interface ICacheStatService {

    /**
     * 查询并打印缓存信息
     * 
     * @return 缓存信息列表
     */
    public List<ICacheInfo> sListAndPrintCacheInfo();

    /**
     * 查询所有缓存容器列表
     * 
     * @return 缓存信息列表
     */
    public List<ICacheInfo> sListCacheInfo();

    /**
     * 查找单个缓存及其缓存条目信息
     * 
     * @param cacheName 缓存名称
     * @return 缓存信息
     */
    public ICacheInfo sFindCacheInfo(String cacheName);

    /**
     * 清除非平台缓存，如cacheName为平台缓存，抛出异常
     * 
     * @param cacheName 缓存名称
     */
    public void clear(String cacheName);

    /**
     * 清除所有非平台缓存
     */
    public void clearAll();

    /**
     * 移除缓存条目，如cacheName为平台缓存，抛出异常
     * 
     * @param cacheName 缓存名称
     * @param key 缓存键值
     */
    public void remove(String cacheName, Object key);

    /**
     * 移除一组缓存条目，如cacheName为平台缓存，抛出异常
     * 
     * @param cacheName 缓存名称
     * @param keys 缓存键值数组
     */
    public void remove(String cacheName, Object[] keys);
}
