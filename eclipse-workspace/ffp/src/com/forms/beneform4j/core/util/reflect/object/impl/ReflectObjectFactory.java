package com.forms.beneform4j.core.util.reflect.object.impl;

import org.apache.commons.lang3.reflect.ConstructorUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 直接使用反射API实现的对象工厂实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public class ReflectObjectFactory extends AbstractObjectFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object doCreate(Class<?> type, Object[] args, Class<?>[] argTypes) throws Exception {
        return ConstructorUtils.invokeConstructor(type, args, argTypes);
    }
}
