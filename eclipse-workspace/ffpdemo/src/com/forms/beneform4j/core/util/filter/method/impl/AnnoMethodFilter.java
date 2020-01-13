package com.forms.beneform4j.core.util.filter.method.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.forms.beneform4j.core.util.filter.method.IMethodFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 包含指定注解的方法过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class AnnoMethodFilter implements IMethodFilter {

    /**
     * 注解类
     */
    private Class<? extends Annotation> anno;

    /**
     * 默认构造函数
     */
    public AnnoMethodFilter() {}

    /**
     * 使用注解类的构造函数
     * 
     * @param anno
     */
    public AnnoMethodFilter(Class<? extends Annotation> anno) {
        this.anno = anno;
    }

    /**
     * 执行过滤
     */
    public boolean accept(Method method) {
        if (null != method && method.isAnnotationPresent(getAnno())) {
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
