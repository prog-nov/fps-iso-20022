package com.forms.beneform4j.core.util.xml.factory;

import org.springframework.beans.factory.xml.DocumentLoader;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;
import com.forms.beneform4j.core.util.xml.parser.IXmlParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML组件工厂接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IXmlComponentFactory {

    /**
     * 创建XML解析环境
     * 
     * @return XML解析环境
     */
    public IXmlParserContext newXmlParserContext();

    /**
     * 创建XML解析器
     * 
     * @return XML解析器
     */
    public <E extends IXmlParserContext> IXmlParser<E> newXmlParser();

    /**
     * 使用指定命名空间解析器创建XML解析器
     * 
     * @param parser 命名空间解析器
     * @return XML解析器
     */
    public <E extends IXmlParserContext> IXmlParser<E> newXmlParser(INamespaceParser<E> parser);

    /**
     * 创建文档加载器
     * 
     * @return 文档加载器
     */
    public DocumentLoader newDocumentLoader();

    /**
     * 创建实体解析器
     * 
     * @return 实体解析器
     */
    public EntityResolver newEntityResolver();

    /**
     * 创建错误处理器
     * 
     * @return 错误处理器
     */
    public ErrorHandler newErrorHandler();
}
