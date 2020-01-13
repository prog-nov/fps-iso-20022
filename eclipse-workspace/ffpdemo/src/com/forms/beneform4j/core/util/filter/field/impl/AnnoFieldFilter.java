package com.forms.beneform4j.core.util.filter.field.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.forms.beneform4j.core.util.filter.field.IFieldFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 含指定注解的属性过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2013-10-6<br>
 */
public class AnnoFieldFilter implements IFieldFilter {

    /**
     * 注解类
     */
    private Class<? extends Annotation> anno;

    /**
     * 默认构造函数
     */
    public AnnoFieldFilter() {}

    /**
     * 使用注解类的构造函数
     * 
     * @param anno
     */
    public AnnoFieldFilter(Class<? extends Annotation> anno) {
        this.anno = anno;
    }

    /**
     * 执行过滤
     */
    public boolean accept(Field field) {
        if (null != field && field.isAnnotationPresent(getAnno())) {
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
