package com.forms.beneform4j.core.service.spring.schema.common.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器抽象类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class AbstractParser extends AbstractSingleBeanDefinitionParser {

    /**
     * 没有ID时，是否主动生成ID
     */
    @Override
    protected boolean shouldGenerateIdAsFallback() {
        return true;
    }

    /**
     * 解析
     */
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        parseSimpleProperty(element, parserContext, builder);
        postProcess(element, parserContext, builder);
    }

    /**
     * 解析简单属性后的处理，供子类覆盖
     * 
     * @param element
     * @param parserContext
     * @param builder
     */
    protected void postProcess(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {}

    /**
     * 解析简单属性
     * 
     * @param element
     * @param parserContext
     * @param builder
     */
    protected void parseSimpleProperty(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        NamedNodeMap attributes = element.getAttributes();
        for (int x = 0, l = attributes.getLength(); x < l; x++) {
            Attr attribute = (Attr) attributes.item(x);
            String name = attribute.getLocalName();
            String value = attribute.getValue();
            parseOneSimpleProperty(element, parserContext, builder, attribute, name, value);
        }
    }

    /**
     * 解析一个简单属性
     * 
     * @param element
     * @param parserContext
     * @param builder
     * @param attribute
     * @param name
     * @param value
     */
    protected void parseOneSimpleProperty(Element element, ParserContext parserContext, BeanDefinitionBuilder builder, Attr attribute, String name, String value) {
        if ("parent".equalsIgnoreCase(name)) {
            builder.setParentName(value);
        } else if (name.endsWith("-ref")) {
            name = name.substring(0, name.length() - 4);
            builder.addPropertyReference(name, value);
        } else if ("id".equalsIgnoreCase(name)) {
            // builder.addPropertyValue("id", value);
        } else if ("class".equalsIgnoreCase(name)) {
            // builder.addPropertyValue("className", value);
        } else {
            builder.addPropertyValue(name, value);
        }
    }

    /**
     * 解析单个对象
     * 
     * @param ele
     * @param delegate
     * @param containingBean
     * @return
     */
    protected Object parseSingleObject(Element ele, BeanDefinitionParserDelegate delegate, BeanDefinition containingBean) {
        Object rs = null;
        if (nodeNameEquals(ele, "bean")) {
            BeanDefinitionHolder nestedBd = delegate.parseBeanDefinitionElement(ele, containingBean);
            if (nestedBd != null) {
                nestedBd = delegate.decorateBeanDefinitionIfRequired(ele, nestedBd, containingBean);
            }
            rs = nestedBd;
        } else if (nodeNameEquals(ele, "ref")) {
            String refName = ele.getAttribute(BeanDefinitionParserDelegate.BEAN_REF_ATTRIBUTE);
            boolean toParent = false;
            if (!StringUtils.hasLength(refName)) {
                refName = ele.getAttribute(BeanDefinitionParserDelegate.LOCAL_REF_ATTRIBUTE);
                if (!StringUtils.hasLength(refName)) {
                    refName = ele.getAttribute(BeanDefinitionParserDelegate.PARENT_REF_ATTRIBUTE);
                    toParent = true;
                }
            }
            rs = new RuntimeBeanReference(refName, toParent);
        } else {
            rs = delegate.parseCustomElement(ele, containingBean);
        }
        return rs;
    }

    /**
     * 解析对象包装元素
     * 
     * @param ele
     * @param delegate
     * @param containingBean
     * @return
     */
    protected Object parseObjectWarp(Element ele, BeanDefinitionParserDelegate delegate, BeanDefinition containingBean) {
        NodeList childNodes = ele.getChildNodes();
        for (int i = 0, l = childNodes.getLength(); i < l; i++) {
            Node node = childNodes.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            return parseSingleObject((Element) node, delegate, containingBean);
        }
        return null;
    }

    /**
     * 判断元素名称是否相同
     * 
     * @param node
     * @param desiredName
     * @return
     */
    protected boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName());
    }
}
