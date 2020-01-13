package com.forms.beneform4j.core.service.mapping.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.InitializingBean;

import com.forms.beneform4j.core.service.mapping.IMapping;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-25<br>
 */
public abstract class AbstractMapping<K, V> implements IMapping<K, V>, InitializingBean {

    /**
     * 是否懒初始化
     */
    private boolean lazyInit = true;

    /**
     * 是否允许重复
     */
    private boolean allowDuplicate = true;

    /**
     * 是否加载的标志
     */
    private AtomicBoolean monitor = new AtomicBoolean(false);

    /**
     * 内部映射
     */
    private Map<K, V> mapping = new HashMap<K, V>();

    @Override
    public V get(K key) {
        sureInited();
        Map<K, V> mapping = getMapping();
        return mapping.get(key);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (isLazyInit()) {
            return;
        }
        init();
    }

    protected void init() {
        Set<V> set = initializeMapping();
        if (null == set || set.isEmpty()) {
            return;
        }
        Map<K, V> mapping = getMapping();
        for (V s : set) {
            K key = getKey(s);
            if (key == null) {
                continue;
            }
            if (mapping.containsKey(key)) {
                V o2 = mapping.get(key);
                if (isAllowDuplicate()) {
                    if (getOrder(o2) > getOrder(s)) {
                        mapping.put(key, s);
                        CommonLogger.debug("mapping key [" + key + "] is duplicate, according to the order, use the map [" + s + "]");
                    } else {
                        CommonLogger.debug("mapping key [" + key + "] is duplicate, according to the order, ignore the map [" + s + "]");
                        continue;
                    }
                } else {
                    Throw.createRuntimeException("mapping key [" + key + "] is duplicate,mapping:[" + o2 + "," + s + "]");
                }
            } else {
                mapping.put(key, s);
                CommonLogger.debug("mapping key [" + key + "] is [" + s + "]");
            }
        }
    }

    /**
     * 初始化映射
     * 
     * @return
     */
    protected abstract Set<V> initializeMapping();

    /**
     * 获取键值
     * 
     * @param value
     * @return
     */
    protected abstract K getKey(V value);

    /**
     * 获取顺序
     * 
     * @param value
     * @return
     */
    protected abstract int getOrder(V value);

    private Map<K, V> getMapping() {
        return mapping;
    }

    private void sureInited() {
        if (!isLazyInit()) {
            return;
        }
        if (!monitor.get()) {
            synchronized (monitor) {
                if (!monitor.get()) {
                    try {
                        init();
                    } finally {
                        monitor.set(true);
                    }
                }
            }
        }
    }

    public boolean isAllowDuplicate() {
        return allowDuplicate;
    }

    public void setAllowDuplicate(boolean allowDuplicate) {
        this.allowDuplicate = allowDuplicate;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }
}
