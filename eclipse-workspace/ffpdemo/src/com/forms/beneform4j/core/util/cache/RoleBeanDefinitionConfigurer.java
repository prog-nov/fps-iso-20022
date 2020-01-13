package com.forms.beneform4j.core.util.cache;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Spring内部交互角色的Bean配置帮助类<br>
 * <p>
 * 在使用Spring的XML配置时，没有和@Role(BeanDefinition.ROLE_INFRASTRUCTURE)对等的XML元素，因此这里添加一个辅助类，实现相同的功能<br>
 * 使用方法就是将需要设置为内部交互角色的bean名称配置为RoleBeanDefinitionConfigurer内的一个属性
 * </p>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-11-22<br>
 */
public class RoleBeanDefinitionConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String[] beanNames;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * 实现接口，将注册的beanNames设置为内部交互角色
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        if (beanNames == null)
            return;

        for (String name : beanNames) {
            if (registry.containsBeanDefinition(name))
                ((AbstractBeanDefinition) registry.getBeanDefinition(name)).setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        }
    }

    public String[] getBeanNames() {
        return beanNames;
    }

    public void setBeanNames(String[] beanNames) {
        this.beanNames = beanNames;
    }
}
