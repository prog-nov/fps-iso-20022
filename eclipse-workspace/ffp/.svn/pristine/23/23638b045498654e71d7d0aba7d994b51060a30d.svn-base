package com.forms.beneform4j.core.util.cache.interceptor;

import java.lang.reflect.Method;
import java.util.Collection;

import org.springframework.cache.interceptor.CacheOperation;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 添加了缓存注解的方法对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public interface ICacheMethod {

    /**
     * 获取添加缓存注解的方法
     * 
     * @return 添加了缓存注解的方法
     */
    Method getMethod();

    /**
     * 获取目标类
     * 
     * @return 运行时目标类
     */
    Class<?> getTargetClass();

    /**
     * 获取缓存注解表示的缓存操作集合
     * 
     * @return 缓存操作集合
     */
    Collection<CacheOperation> getOperations();
}
