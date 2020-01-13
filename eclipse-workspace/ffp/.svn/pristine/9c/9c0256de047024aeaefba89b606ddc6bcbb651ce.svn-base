package com.forms.beneform4j.core.util.xml.factory.impl;

import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

import com.forms.beneform4j.core.util.xml.BaseErrorHandler;
import com.forms.beneform4j.core.util.xml.IniConfigEntityResolver;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.context.impl.XmlParserContext;
import com.forms.beneform4j.core.util.xml.factory.IXmlComponentFactory;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;
import com.forms.beneform4j.core.util.xml.parser.IXmlParser;
import com.forms.beneform4j.core.util.xml.parser.impl.CallbackXmlParser;
import com.forms.beneform4j.core.util.xml.parser.impl.PluggableXmlParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML组件工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class XmlComponentFactory implements IXmlComponentFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public IXmlParserContext newXmlParserContext() {
        return new XmlParserContext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IXmlParserContext> IXmlParser<E> newXmlParser() {
        return newXmlParser(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IXmlParserContext> IXmlParser<E> newXmlParser(INamespaceParser<E> parser) {
        if (null != parser) {
            return new CallbackXmlParser<E>(parser);
        } else {
            return new PluggableXmlParser<E>();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentLoader newDocumentLoader() {
        return new DefaultDocumentLoader();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityResolver newEntityResolver() {
        return new IniConfigEntityResolver();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ErrorHandler newErrorHandler() {
        return new BaseErrorHandler();
    }
}
