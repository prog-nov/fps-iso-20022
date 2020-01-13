package com.forms.beneform4j.core.util.xml.parser;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.forms.beneform4j.core.util.parser.IParser;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML资源解析器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-22<br>
 */
public interface IXmlParser<E extends IXmlParserContext> extends IParser<E> {

    /**
     * 解析一个模式字符串表示的XML资源
     * 
     * @param parserContext 解析上下文环境
     * @param locationPattern 资源模式字符串
     * @return Document对象集合
     */
    public List<Document> buildDocuments(E parserContext, String locationPattern);

    /**
     * 解析一组模式字符串表示的XML资源
     * 
     * @param parserContext 解析上下文环境
     * @param locationPatterns 资源模式字符串数组
     * @return Document对象集合
     */
    public List<Document> buildDocuments(E parserContext, String[] locationPatterns);

    /**
     * 解析一个输入流表示的XML资源
     * 
     * @param parserContext 解析上下文环境
     * @param inputStream 输入流
     * @return Document对象
     */
    public Document buildDocument(E parserContext, InputStream inputStream);

    /**
     * 解析一个XML资源
     * 
     * @param parserContext 解析上下文环境
     * @param resource 资源
     * @return Document对象
     */
    public Document buildDocument(E parserContext, Resource resource);
}
