package com.forms.beneform4j.util.param.common.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.param.common.IParamStore;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用内存的参数存储器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-7<br>
 */
public class MemoryParamStore<P extends IParam> implements IParamStore<P> {

    private final Map<String, P> cache = new ConcurrentHashMap<String, P>();

    @Override
    public P get(String name) {
        return cache.get(name);
    }

    @Override
    public void remove(String name) {
        cache.remove(name);
    }

    @Override
    public void save(String name, P value) {
        cache.put(name, value);
    }

    @Override
    public boolean contains(String name) {
        return cache.containsKey(name);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
