package com.forms.beneform4j.core.util.exception.meta.impl;

import java.util.List;

import com.forms.beneform4j.core.util.exception.meta.IExceptionMeta;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMetaLoader;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 组合的异常元信息加载器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class CompositeExceptionMetaLoader implements IExceptionMetaLoader {

    private List<IExceptionMetaLoader> loaders;

    public CompositeExceptionMetaLoader() {}

    public CompositeExceptionMetaLoader(List<IExceptionMetaLoader> loaders) {
        this.loaders = loaders;
    }

    public List<IExceptionMetaLoader> getLoaders() {
        return loaders;
    }

    public void setLoaders(List<IExceptionMetaLoader> loaders) {
        this.loaders = loaders;
    }

    /**
     * 查找异常元信息 逐个使用内部的加载器查找，一旦找到就返回
     */
    @Override
    public IExceptionMeta lookup(String code, Throwable cause) {
        List<IExceptionMetaLoader> loaders = getLoaders();
        if (null != loaders) {
            for (IExceptionMetaLoader loader : loaders) {
                IExceptionMeta meta = loader.lookup(code, cause);
                if (null != meta) {
                    return meta;
                }
            }
        }
        return null;
    }
}
