package com.forms.beneform4j.core.util.data.accessor.impl;

import java.util.Map;

import com.forms.beneform4j.core.util.data.accessor.IDataAccessor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用SpEL表达式实现的数据访问器工厂对象<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class SpelDataAccessorFactory extends AbstractDataAccessorFactory {

    @Override
    public IDataAccessor newDataAccessor(Object root, Map<String, Object> vars) {
        return new SpelDataAccessor(root, vars);
    }

}
