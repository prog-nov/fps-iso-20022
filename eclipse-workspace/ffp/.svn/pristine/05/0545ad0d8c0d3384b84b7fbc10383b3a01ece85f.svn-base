package com.forms.beneform4j.core.util.data.accessor.impl;

import com.forms.beneform4j.core.util.data.accessor.IDataAccessor;
import com.forms.beneform4j.core.util.data.accessor.IDataAccessorFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的数据访问器工厂对象实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public abstract class AbstractDataAccessorFactory implements IDataAccessorFactory {

    @Override
    public IDataAccessor newDataAccessor() {
        return this.newDataAccessor(null, null);
    }

    @Override
    public IDataAccessor newDataAccessor(Object root) {
        return this.newDataAccessor(root, null);
    }
}
