package com.forms.beneform4j.core.service.mapping.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 注解方法映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-25<br>
 */
public class AnnotationMethodMapping extends AbstractAnnotationMapping<Method> {

    @Override
    protected Set<Method> initializeMapping(String scanPackage, Class<? extends Annotation> cls) {
        return CoreUtils.scanMethods(scanPackage, cls);
    }

}
