package com.forms.beneform4j.core.util.filter.type.impl;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.StandardClassMetadata;

import com.forms.beneform4j.core.util.filter.type.ITypeFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 排除抽象类、接口、注解、内部类的类过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class InstanceableClassFilter implements ITypeFilter {

    /**
     * 执行过滤
     */
    public boolean accept(Class<?> cls) {
        if (null == cls) {
            return false;
        }
        ClassMetadata meta = new StandardClassMetadata(cls);
        if (meta.isAbstract() || meta.isAnnotation() || meta.isInterface() && meta.hasEnclosingClass()) {
            return false;
        } else {
            return true;
        }
    }
}
