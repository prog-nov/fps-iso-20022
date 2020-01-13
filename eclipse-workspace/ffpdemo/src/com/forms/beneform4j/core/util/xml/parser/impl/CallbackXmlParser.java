package com.forms.beneform4j.core.util.xml.parser.impl;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用传入命名空间处理器进行回调处理的XML解析实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class CallbackXmlParser<E extends IXmlParserContext> extends AbstractXmlParser<E> {

    private final INamespaceParser<E> parser;

    public CallbackXmlParser(final INamespaceParser<E> parser) {
        super();
        this.parser = parser;
    }

    public INamespaceParser<E> getParser() {
        return parser;
    }

    @Override
    protected void parseDocument(E parserContext, Document document, Resource resource) {
        parser.parse(parserContext, document, resource);
    }
}
