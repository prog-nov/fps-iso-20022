package com.forms.beneform4j.core.service.mapping.impl;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 注解接口类映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-25<br>
 */
public class AnnotationInterfaceClassMapping<E> extends AnnotationClassMapping {

    private Class<?> parentCls;

    @Override
    protected Set<Class<?>> initializeMapping(String scanPackage, Class<? extends Annotation> cls) {
        Set<Class<?>> set = super.initializeMapping(scanPackage, cls);
        if (null != set && null != getParentCls()) {
            Class<?> parentCls = getParentCls();
            Set<Class<?>> rs = new HashSet<Class<?>>();
            for (Class<?> c : set) {
                if (parentCls.isAssignableFrom(c)) {
                    rs.add(c);
                }
            }
            return rs;
        }
        return set;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends E> get(String key) {
        Class<? extends E> cls = (Class<? extends E>) super.get(key);
        return cls;
    }

    public Class<?> getParentCls() {
        return parentCls;
    }

    public void setParentCls(Class<?> parentCls) {
        this.parentCls = parentCls;
    }
}
