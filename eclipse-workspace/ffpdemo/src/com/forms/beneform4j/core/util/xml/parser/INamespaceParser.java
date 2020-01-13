package com.forms.beneform4j.core.util.xml.parser;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML命名空间解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface INamespaceParser<E extends IXmlParserContext> {

    /**
     * 执行解析
     * 
     * @param parserContext 解析环境
     * @param document 文档对象
     * @param resource 和文档对象对应的资源对象
     */
    public void parse(E parserContext, Document document, Resource resource);
}
