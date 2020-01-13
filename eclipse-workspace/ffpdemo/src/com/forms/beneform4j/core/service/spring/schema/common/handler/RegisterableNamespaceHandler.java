package com.forms.beneform4j.core.service.spring.schema.common.handler;

import org.springframework.beans.factory.xml.BeanDefinitionDecorator;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 可调用注册方法的命名空间支持类（扩大原支持类注册方法的可见范围）<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public abstract class RegisterableNamespaceHandler extends NamespaceHandlerSupport {

    public void doRegisterBeanDefinitionParser(String elementName, BeanDefinitionParser parser) {
        super.registerBeanDefinitionParser(elementName, parser);
    }

    public void doRegisterBeanDefinitionDecorator(String elementName, BeanDefinitionDecorator dec) {
        super.registerBeanDefinitionDecorator(elementName, dec);
    }

    public void doRegisterBeanDefinitionDecoratorForAttribute(String attrName, BeanDefinitionDecorator dec) {
        super.registerBeanDefinitionDecoratorForAttribute(attrName, dec);
    }
}
