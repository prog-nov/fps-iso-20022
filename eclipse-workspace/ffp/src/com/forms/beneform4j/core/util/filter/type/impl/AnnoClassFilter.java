package com.forms.beneform4j.core.util.filter.type.impl;

import java.lang.annotation.Annotation;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 含指定注解的可实例化类过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class AnnoClassFilter extends InstanceableClassFilter {

    /**
     * 注解类
     */
    private Class<? extends Annotation> anno;

    /**
     * 默认构造函数
     */
    public AnnoClassFilter() {}

    /**
     * 使用注解类的构造函数
     * 
     * @param anno
     */
    public AnnoClassFilter(Class<? extends Annotation> anno) {
        this.anno = anno;
    }

    /**
     * 执行过滤
     */
    public boolean accept(Class<?> cls) {
        if (cls.isAnnotationPresent(getAnno()) && super.accept(cls)) {
            return true;
        }
        return false;
    }

    /**
     * 获取注解类
     * 
     * @return
     */
    public Class<? extends Annotation> getAnno() {
        return anno;
    }

    /**
     * 设置注解类
     * 
     * @param anno
     */
    public void setAnno(Class<? extends Annotation> anno) {
        this.anno = anno;
    }
}
