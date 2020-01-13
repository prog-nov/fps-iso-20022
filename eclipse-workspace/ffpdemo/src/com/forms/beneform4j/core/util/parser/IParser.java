package com.forms.beneform4j.core.util.parser;

import java.io.InputStream;

import org.springframework.core.io.Resource;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 通用解析器接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IParser<E extends IParserContext> {

    /**
     * 解析一个模式字符串表示的资源
     * 
     * @param parserContext 解析上下文环境
     * @param locationPattern 资源模式字符串
     */
    public void parse(E parserContext, String locationPattern);

    /**
     * 解析一组模式字符串表示的资源
     * 
     * @param parserContext 解析上下文环境
     * @param locationPatterns 资源模式字符串数组
     */
    public void parse(E parserContext, String[] locationPatterns);

    /**
     * 解析一个输入流表示的资源
     * 
     * @param parserContext 解析上下文环境
     * @param inputStream 输入流
     */
    public void parse(E parserContext, InputStream inputStream);

    /**
     * 解析一个资源
     * 
     * @param parserContext 解析上下文环境
     * @param resource 资源
     */
    public void parse(E parserContext, Resource resource);
}
