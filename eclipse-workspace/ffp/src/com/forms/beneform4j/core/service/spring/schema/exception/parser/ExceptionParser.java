package com.forms.beneform4j.core.service.spring.schema.exception.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.forms.beneform4j.core.service.spring.schema.common.parser.AbstractParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 单个异常配置解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class ExceptionParser extends AbstractParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return SchemaExceptionMeta.class;
    }

    @Override
    protected void parseOneSimpleProperty(Element element, ParserContext parserContext, BeanDefinitionBuilder builder, Attr attribute, String name, String value) {
        if ("handlers".equals(name)) {
            builder.addPropertyValue("schemaHandlers", value.trim().split("\\s+"));
        } else if ("causes".equals(name)) {
            builder.addPropertyValue(name, value.trim().split("\\s+"));
        } else {
            super.parseOneSimpleProperty(element, parserContext, builder, attribute, name, value);
        }
    }

    @Override
    protected void postProcess(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        BeanDefinitionParserDelegate delegate = parserContext.getDelegate();
        BeanDefinition containingBean = builder.getBeanDefinition();
        NodeList childNodes = element.getChildNodes();
        ManagedList<Object> exceptions = new ManagedList<Object>();
        for (int i = 0, l = childNodes.getLength(); i < l; i++) {
            Node node = childNodes.item(i);
            if ((node instanceof Element) && nodeNameEquals(node, "exception")) {
                exceptions.add(delegate.parseCustomElement((Element) node, containingBean));
            }
        }
        if (!exceptions.isEmpty()) {
            builder.addPropertyValue("metas", exceptions);
        }
        builder.setScope("prototype");
    }
}
