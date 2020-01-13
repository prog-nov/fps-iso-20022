package com.forms.beneform4j.util.param;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.util.param.IParam;
import com.forms.beneform4j.util.param.IParamServiceApi;
import com.forms.beneform4j.util.param.common.IParamLoader;
import com.forms.beneform4j.util.param.common.IParamStore;
import com.forms.beneform4j.util.param.common.impl.CacheParamStore;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 参数服务实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public abstract class AbstractParamServiceApi<P extends IParam> implements IParamServiceApi<P> {

    private IParamLoader<P> loader;

    private IParamStore<P> store;

    public AbstractParamServiceApi() {
        this.store = new CacheParamStore<P>(getStoreElementName());
    }

    /**
     * 获取参数
     * 
     * @param name 参数名称
     * @return 参数
     */
    @Override
    public P getParam(String name) {
        Map<String, P> params = getParams(Arrays.asList(name));
        if (null != params && !params.isEmpty()) {
            return params.values().iterator().next();
        }
        return null;
    }

    /**
     * 获取一组参数
     * 
     * @param names 参数名称组
     * @return 参数组
     */
    @Override
    public Map<String, P> getParams(List<String> names) {
        IParamLoader<P> loader = getLoader();
        if (null == loader || null == names || names.isEmpty()) {
            return null;
        }
        IParamStore<P> store = getStore();
        if (null == store) {
            return doLoad(store, names);
        } else {
            List<String> loadnames = new ArrayList<String>();
            Map<String, P> caches = new HashMap<String, P>();
            for (String name : names) {
                P treeParam = store.get(name);
                if (null != treeParam) {
                    caches.put(name, treeParam);
                } else {
                    loadnames.add(name);
                }
            }
            if (!loadnames.isEmpty()) {
                Map<String, P> loads = this.doLoad(store, loadnames);
                if (null != loads) {
                    caches.putAll(loads);
                }
            }
            return caches;
        }
    }

    @Override
    public void removeParams(List<String> names) {
        IParamStore<P> store = getStore();
        if (null == store || null == names || names.isEmpty()) {
            return;
        } else {
            for (String name : names) {
                store.remove(name);
            }
        }
    }

    /**
     * 是否已经加载
     * 
     * @param name 参数名称
     * @return 是否已经加载
     */
    public boolean isLoaded(String name) {
        IParamStore<P> store = getStore();
        if (null == store) {
            return false;
        } else {
            return store.contains(name);
        }
    }

    @Override
    public void clear() {
        IParamStore<P> store = getStore();
        if (null != store) {
            store.clear();
        }
    }

    private Map<String, P> doLoad(IParamStore<P> store, List<String> names) {
        IParamLoader<P> loader = getLoader();
        Map<String, P> o = loader.loadParams(names);
        if (null != o && !o.isEmpty()) {
            this.afterLoad(o);
        }
        return o;
    }

    /**
     * 获取存储元素类型名称
     * 
     * @return
     */
    protected abstract String getStoreElementName();

    /**
     * 加载后处理
     * 
     * @param params
     */
    protected abstract void afterLoad(Map<String, P> params);

    public IParamLoader<P> getLoader() {
        return loader;
    }

    public void setLoader(IParamLoader<P> loader) {
        this.loader = loader;
    }

    public IParamStore<P> getStore() {
        return store;
    }

    public void setStore(IParamStore<P> store) {
        this.store = store;
    }
}
