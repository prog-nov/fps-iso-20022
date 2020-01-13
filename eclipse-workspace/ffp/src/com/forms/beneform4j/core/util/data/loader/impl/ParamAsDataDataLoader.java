package com.forms.beneform4j.core.util.data.loader.impl;

import com.forms.beneform4j.core.util.data.loader.IDataLoader;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 直接将参数作为数据的加载器<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class ParamAsDataDataLoader implements IDataLoader {

    @Override
    public Object load(Object param) {
        return param;
    }
}
