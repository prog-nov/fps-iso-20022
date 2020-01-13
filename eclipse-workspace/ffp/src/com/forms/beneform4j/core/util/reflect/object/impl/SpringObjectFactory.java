package com.forms.beneform4j.core.util.reflect.object.impl;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 结合Spring容器和反射API的对象工厂实现类，若Spring容器不为空，则首先尝试从Spring容器中获取实例，获取不到再使用反射API创建实例<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public class SpringObjectFactory extends ReflectObjectFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object doCreate(Class<?> type, Object[] args, Class<?>[] argTypes) throws Exception {
        try {
            if (null != applicationContext) {
                if (null == args || 0 == args.length) {
                    return applicationContext.getBean(type);
                } else {
                    return applicationContext.getBean(type, args);
                }
            }
        } catch (Exception e) {
            // ignore
        }
        return super.doCreate(type, args, argTypes);
    }

    /**
     * <p>
     * 覆盖父类方法，如果类名不含有点号，则首先作为bean名称获取实例
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Object create(String className, List<Class<?>> constructorArgTypes, Object... constructorArgs) {
        if (-1 == className.indexOf(".") && null != applicationContext) {
            if (null == constructorArgs || 0 == constructorArgs.length) {
                return applicationContext.getBean(className);
            } else {
                return applicationContext.getBean(className, constructorArgs);
            }
        }
        return super.create(className, constructorArgTypes, constructorArgs);
    }

    /**
     * 实现ApplicationContextAware接口
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
