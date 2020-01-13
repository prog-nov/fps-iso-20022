package com.forms.beneform4j.core.util.xml.parser.impl;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.xml.XmlHelper;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 可扩展的XML解析实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class PluggableXmlParser<E extends IXmlParserContext> extends AbstractXmlParser<E> {

    @Override
    protected void parseDocument(E parserContext, Document document, Resource resource) {
        Element root = document.getDocumentElement();
        INamespaceParser<E> parser = getNamespaceParser(document.getDocumentElement());
        if (null == parser) {
            Throw.throwRuntimeException("not found the namespace parser. [" + XmlHelper.getNamespaceURI(root) + "] ");
        }
        parser.parse(parserContext, document, resource);
    }
}
