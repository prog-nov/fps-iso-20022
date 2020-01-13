package com.forms.beneform4j.core.util.cache;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.meta.ExceptionCodes;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 缓存工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class Caches {

    /**
     * 平台缓存名称
     */
    private static final String BENEFORM4J_CACHE_KEY = "beneform4j";

    /**
     * 用于判断是否平台包的正则表达式
     */
    private static final Pattern BENEFORM4J_PACKAGE_PATTERN = Pattern.compile("^com\\.forms\\.beneform4j\\..*");

    private static Field CONCURRENT_CACHE_MAP_FIELD;
    static {
        try {
            CONCURRENT_CACHE_MAP_FIELD = ConcurrentMapCacheManager.class.getDeclaredField("cacheMap");
            CONCURRENT_CACHE_MAP_FIELD.setAccessible(true);
        } catch (Exception e) {
        }
    }

    private static CacheManager getCacheManager() {
        return BaseConfig.getCacheManager();
    }

    /**
     * 声明不是平台缓存，如果是平台缓存，则抛出异常代码{@literal BF010006}
     * 
     * @param cacheName 缓存名称
     */
    public static void assertNotBeneform4jCache(String cacheName) {
        if (isBeneform4jCache(cacheName)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010006, "", cacheName);
        }
    }

    /**
     * 检查访问缓存名称的权限，无权限时抛出异常代码{@literal BF010006}
     * 
     * @param cacheName 缓存名称
     * @param visitClass 访问缓存的类
     */
    public static void checkCacheName(String cacheName, Class<?> visitClass) {
        checkCacheName(cacheName, visitClass.getName());
    }

    /**
     * 检查访问缓存名称的权限，无权限时抛出异常代码{@literal BF010006}
     * 
     * @param cacheName 缓存名称
     * @param visitClassName 访问缓存的类名称
     */
    public static void checkCacheName(String cacheName, String visitClassName) {
        if (isBeneform4jCache(cacheName) && !isBeneform4jClass(visitClassName)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010006, visitClassName, cacheName);
        }
    }

    /**
     * 清除缓存以及容器本身
     * 
     * @param cacheManager 缓存管理器，如果是ConcurrentMapCacheManager，在清除缓存的同时，会删除作为缓存容器的ConcurrentMap
     * @param cacheName 缓存名称
     */
    public static void clearCacheAndContainer(CacheManager cacheManager, String cacheName) {
        if (null == cacheManager || null == cacheName) {
            return;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (null == cache) {
            return;
        }
        cache.clear();
        if (cacheManager instanceof ConcurrentMapCacheManager) {
            try {
                @SuppressWarnings("unchecked")
                ConcurrentMap<String, Cache> cacheMap = (ConcurrentMap<String, Cache>) CONCURRENT_CACHE_MAP_FIELD.get(cacheManager);
                cacheMap.remove(cacheName);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 清除所有有权访问的缓存
     */
    public static void clearAllCache() {
        clearAllCache0(2);
    }

    /**
     * 获取默认缓存容器中键值为key的缓存值
     * 
     * @param key 缓存键值
     * @return 缓存值
     */
    public static Object get(Object key) {
        Cache cache = getDefaultCache();
        ValueWrapper wrapper = cache.get(key);
        return null == wrapper ? null : wrapper.get();
    }

    /**
     * 获取默认缓存容器中键值为key的缓存值
     * 
     * @param key 缓存键值
     * @param cls 期望的类型
     * @return 缓存值
     */
    public static <E> E get(Object key, Class<E> cls) {
        Cache cache = getDefaultCache();
        return cache.get(key, cls);
    }

    /**
     * 将缓存条目存入默认缓存容器中
     * 
     * @param key 缓存键值
     * @param value 缓存值
     */
    public static void put(Object key, Object value) {
        Cache cache = getDefaultCache();
        cache.put(key, value);
    }

    /**
     * 将缓存条目存入默认缓存容器中
     * 
     * @param key 缓存键值
     * @param value 缓存值
     * @return 如果已经存在，就直接返回已存在的值，如果不存在，将value存入缓存容器，并返回null
     */
    public static Object putIfAbsent(Object key, Object value) {
        Cache cache = getDefaultCache();
        ValueWrapper wrapper = cache.putIfAbsent(key, value);
        return null == wrapper ? null : wrapper.get();
    }

    /**
     * 从默认缓存容器中移除键值为key的缓存条目
     * 
     * @param key 缓存键值
     */
    public static void remove(Object key) {
        Cache cache = getDefaultCache();
        cache.evict(key);
    }

    /**
     * 清空默认缓存容器
     */
    public static void clear() {
        Cache cache = getDefaultCache();
        cache.clear();
    }

    /**
     * 返回所有有权访问的缓存名称
     * 
     * @return 调用包有权限访问的所有缓存名称集合
     */
    public static Collection<String> getCacheNames() {
        return getCacheNames0(2);
    }

    /**
     * 获取名称为cacheName的缓存，如无访问权限，抛出异常
     * 
     * @param cacheName
     * @return 缓存容器
     */
    public static Cache getCache(String cacheName) {
        return getCache0(cacheName, 2);
    }

    /**
     * 获取名称为cls.getName()的缓存，如无访问权限，抛出异常
     * 
     * @param cls 作为缓存名称的类
     * @return 缓存容器
     */
    public static Cache getCache(Class<?> cls) {
        return getCache0(cls.getName(), 2);
    }

    /**
     * 获取名称为cls.getName()+"###"+name的缓存，如无访问权限，抛出异常
     * 
     * @param cls 作为缓存名称的类
     * @param name 同一个缓存名称类的字符串名称部分
     * @return 缓存容器
     */
    public static Cache getCache(Class<?> cls, String name) {
        return getCache0(cls.getName() + "###" + name, 2);
    }

    /**
     * 是否平台缓存名称
     * 
     * @param cacheName 缓存名称
     * @return 是否为平台缓存容器
     */
    public static boolean isBeneform4jCache(String cacheName) {
        return null != cacheName && cacheName.toLowerCase().indexOf(BENEFORM4J_CACHE_KEY) != -1;
    }

    /**
     * 是否平台类
     * 
     * @param className 类名称
     * @return 是否平台类
     */
    public static boolean isBeneform4jClass(String className) {
        return BENEFORM4J_PACKAGE_PATTERN.matcher(className).find();
    }

    /**
     * 获取缓存
     * 
     * @param cacheName 缓存名称
     * @param visitClassName 访问的缓存类名
     * @return 缓存容器
     */
    private static Cache getCache0(String cacheName, int stackDeep) {
        CacheManager delegate = getCacheManager();
        if (null == delegate) {
            return null;
        }
        String visitClassName = new Exception().getStackTrace()[stackDeep].getClassName();
        checkCacheName(cacheName, visitClassName);
        return delegate.getCache(cacheName);
    }

    /**
     * 获取所有缓存名称
     * 
     * @param stackDeep 调用堆栈的深度
     * @return 缓存名称集合
     */
    private static Collection<String> getCacheNames0(int stackDeep) {
        CacheManager delegate = getCacheManager();
        if (null == delegate) {
            return null;
        }

        Collection<String> names = delegate.getCacheNames();
        if (null == names || names.isEmpty()) {
            return names;
        } else {
            Collection<String> nms = new ArrayList<String>();
            String visitClassName = new Exception().getStackTrace()[stackDeep].getClassName();
            boolean isBeneform4j = isBeneform4jClass(visitClassName);
            for (String name : names) {
                if (isBeneform4j || !isBeneform4jCache(name)) {// 是平台访问，或者不是平台缓存
                    nms.add(name);
                }
            }
            return nms;
        }
    }

    /**
     * 清除所有缓存
     * 
     * @stackDeep 堆栈深度
     */
    private static void clearAllCache0(int stackDeep) {
        CacheManager delegate = getCacheManager();
        if (null == delegate) {
            return;
        }

        Collection<String> names = delegate.getCacheNames();
        if (null == names || names.isEmpty()) {
            return;
        } else {
            String visitClassName = new Exception().getStackTrace()[stackDeep].getClassName();
            boolean isBeneform4j = isBeneform4jClass(visitClassName);
            for (String name : names) {
                if (isBeneform4j || !isBeneform4jCache(name)) {// 是平台访问，或者不是平台缓存
                    clearCacheAndContainer(delegate, name);
                }
            }
        }
    }

    /**
     * 获取默认缓存名称
     * 
     * @return 默认缓存容器
     */
    private static Cache getDefaultCache() {
        String cacheName = BaseConfig.getDefaultCacheName();
        if (CoreUtils.isBlank(cacheName)) {
            Throw.throwRuntimeException(ExceptionCodes.BF010007);
        }
        Cache cache = getCache0(cacheName, 3);
        if (null == cache) {
            Throw.throwRuntimeException(ExceptionCodes.BF010008, cacheName);
        }
        return cache;
    }
}
