package com.forms.beneform4j.core.util.data.loader.impl;

import com.forms.beneform4j.core.util.data.loader.IDataLoader;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据不变的加载器<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class ImmutableDataDataLoader implements IDataLoader {

    private final Object data;

    public ImmutableDataDataLoader(Object data) {
        this.data = data;
    }

    @Override
    public Object load(Object param) {
        return data;
    }
}
