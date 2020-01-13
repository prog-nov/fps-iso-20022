package com.forms.beneform4j.core.service.spring.schema.common.parser;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

import com.forms.beneform4j.core.service.spring.schema.common.handler.RegisterableNamespaceHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 只处理属性的简单解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class AttributesParser extends AbstractParser {

    private Map<String, Class<?>> beanClassMap;

    public AttributesParser() {
        this.beanClassMap = new HashMap<String, Class<?>>();
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        if (beanClassMap.containsKey(element.getLocalName())) {
            return beanClassMap.get(element.getLocalName());
        }
        return super.getBeanClass(element);
    }

    public AttributesParser registerBeanDefinitionParser(RegisterableNamespaceHandler namespace, String elementName, Class<?> beanClass) {
        this.beanClassMap.put(elementName, beanClass);
        namespace.doRegisterBeanDefinitionParser(elementName, this);
        return this;
    }
}
