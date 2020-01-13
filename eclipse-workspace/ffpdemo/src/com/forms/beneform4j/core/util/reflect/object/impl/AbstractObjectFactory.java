package com.forms.beneform4j.core.util.reflect.object.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.reflect.object.IObjectFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的对象工厂实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public abstract class AbstractObjectFactory implements IObjectFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object create(String className, Object... constructorArgs) {
        return this.create(className, null, constructorArgs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object create(String className, List<Class<?>> constructorArgTypes, Object... constructorArgs) {
        Class<?> type = resolveInterface(className);
        return this.create(type, constructorArgTypes, constructorArgs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T create(Class<T> type, Object... constructorArgs) {
        return this.create(type, null, constructorArgs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, Object... constructorArgs) {
        try {
            Class<?> classToCreate = resolveInterface(type);
            Class<?>[] types = null;
            if (null != constructorArgTypes) {
                int size = constructorArgTypes.size();
                types = new Class<?>[size];
                for (int i = 0; i < size; i++) {
                    types[i] = constructorArgTypes.get(i);
                }
            }
            Object rs = this.doCreate(classToCreate, constructorArgs, types);
            if (null == rs) {
                return null;
            } else if (type.isInstance(rs)) {
                return type.cast(rs);
            } else {
                return CoreUtils.convert(rs, type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating " + type + ". Cause: " + e, e);
        }
    }

    /**
     * 创建对象实例
     * 
     * @param type 对象类型
     * @param args 构造器参数
     * @param argTypes 构造器参数类型
     * @return 对象实例
     * @throws Exception 调用构造函数抛出的异常
     */
    abstract protected Object doCreate(Class<?> type, Object[] args, Class<?>[] argTypes) throws Exception;

    /**
     * 解析接口，设置默认的实现类
     * 
     * @param className 接口类名称
     * @return 实现类类型
     */
    protected Class<?> resolveInterface(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException("Error loading class " + className + ". Cause: " + e, e);
        }
    }

    /**
     * 解析接口，设置默认的实现类
     * 
     * @param type 接口类型
     * @return 实现类类型
     */
    protected Class<?> resolveInterface(Class<?> type) {
        Class<?> classToCreate;
        if (type == List.class || type == Collection.class || type == Iterable.class) {
            classToCreate = ArrayList.class;
        } else if (type == Map.class) {
            classToCreate = HashMap.class;
        } else if (type == SortedSet.class) {
            classToCreate = TreeSet.class;
        } else if (type == Set.class) {
            classToCreate = HashSet.class;
        } else {
            classToCreate = type;
        }
        return classToCreate;
    }
}
