package com.forms.beneform4j.core.service.mapping.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Set;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象注解映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-25<br>
 */
public abstract class AbstractAnnotationMapping<V extends AnnotatedElement> extends AbstractMapping<String, V> {

    /**
     * 扫描包
     */
    private String scanPackage;

    /**
     * 扫描注解
     */
    private Class<? extends Annotation> annoCls;

    /**
     * 注解中表示ID的属性
     */
    private String idProperty = "value";

    /**
     * 和idProperty对应的方法
     */
    private Method idMethod;

    /**
     * 注解中表示顺序的属性
     */
    private String orderProperty;

    /**
     * 和orderProperty对应的方法
     */
    private Method orderMethod;

    @Override
    protected Set<V> initializeMapping() {
        return initializeMapping(getScanPackage(), getAnnoCls());
    }

    @Override
    protected String getKey(V value) {
        try {
            Annotation anno = value.getAnnotation(annoCls);
            return (String) idMethod.invoke(anno, new Object[0]);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    protected int getOrder(V value) {
        if (null != orderMethod) {
            try {
                Annotation anno = value.getAnnotation(annoCls);
                return (int) orderMethod.invoke(anno, new Object[0]);
            } catch (Exception e) {
            }
        }
        return 0;
    }

    @Override
    protected void init() {
        Class<? extends Annotation> annoCls = getAnnoCls();
        String idProperty = getIdProperty();
        Method idMethod = getMethod(annoCls, idProperty);
        if (null == idMethod) {
            idMethod = getMethod(annoCls, "value");
        }
        if (null == idMethod) {
            idMethod = getMethod(annoCls, "id");
        }
        if (null == idMethod) {
            Throw.createRuntimeException("注解类" + annoCls + "中找不到属性" + idProperty);
        } else {
            this.idMethod = idMethod;
        }
        String orderProperty = getOrderProperty();
        if (!CoreUtils.isBlank(orderProperty)) {
            this.orderMethod = getMethod(annoCls, orderProperty);
        }
        super.init();
    }

    /**
     * 根据扫描包初始化
     * 
     * @param scanPackage
     * @param cls
     * @return
     */
    protected abstract Set<V> initializeMapping(String scanPackage, Class<? extends Annotation> cls);

    private Method getMethod(Class<?> cls, String field) {
        try {
            return cls.getDeclaredMethod(field, new Class<?>[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public Class<? extends Annotation> getAnnoCls() {
        return annoCls;
    }

    public void setAnnoCls(Class<? extends Annotation> annoCls) {
        this.annoCls = annoCls;
    }

    public String getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(String idProperty) {
        this.idProperty = idProperty;
    }

    public String getOrderProperty() {
        return orderProperty;
    }

    public void setOrderProperty(String orderProperty) {
        this.orderProperty = orderProperty;
    }
}
