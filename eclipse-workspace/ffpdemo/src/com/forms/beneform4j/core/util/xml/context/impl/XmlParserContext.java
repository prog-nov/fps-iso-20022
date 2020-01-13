package com.forms.beneform4j.core.util.xml.context.impl;

import org.springframework.beans.factory.xml.DocumentLoader;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

import com.forms.beneform4j.core.util.parser.impl.ParserContext;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.parser.XmlParserUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML文件解析环境<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class XmlParserContext extends ParserContext implements IXmlParserContext {

    private DocumentLoader documentLoader;

    private EntityResolver entityResolver;

    private ErrorHandler errorHandler;

    private XmlValidationMode xmlValidationMode;

    /**
     * {@inheritDoc}
     */
    @Override
    public DocumentLoader getDocumentLoader() {
        if (null == this.documentLoader) {
            this.documentLoader = XmlParserUtils.getFactory().newDocumentLoader();
        }
        return this.documentLoader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityResolver getEntityResolver() {
        if (null == this.entityResolver) {
            this.entityResolver = XmlParserUtils.getFactory().newEntityResolver();
        }
        return this.entityResolver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ErrorHandler getErrorHandler() {
        if (null == this.errorHandler) {
            this.errorHandler = XmlParserUtils.getFactory().newErrorHandler();
        }
        return this.errorHandler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public XmlValidationMode getXmlValidationMode() {
        return xmlValidationMode;
    }

    public void setDocumentLoader(DocumentLoader documentLoader) {
        this.documentLoader = documentLoader;
    }

    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void setXmlValidationMode(XmlValidationMode xmlValidationMode) {
        this.xmlValidationMode = xmlValidationMode;
    }
}
