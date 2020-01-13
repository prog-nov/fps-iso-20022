package com.forms.beneform4j.core.util.parser;

import java.util.Collection;

import org.springframework.core.io.Resource;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器侦听接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IParseEventListener {

    /**
     * 整个解析开始前事件
     * 
     * @param context 上下文环境
     * @param resources 解析的资源对象集合
     */
    public void onBeforeParse(IParserContext context, Collection<Resource> resources);

    /**
     * 整个解析完成后事件，可能解析成功，也可能解析失败
     * 
     * @param context 上下文环境
     * @param resources 解析的资源对象集合
     */
    public void onFinishParse(IParserContext context, Collection<Resource> resources);

    /**
     * 解析单个资源前事件
     * 
     * @param context 上下文环境
     * @param resource 解析的单个资源
     */
    public void onBeforeParseOneResource(IParserContext context, Resource resource);

    /**
     * 解析单个资源出现异常后事件
     * 
     * @param context 上下文环境
     * @param resource 解析的单个资源
     * @param e 异常
     */
    public void onFailureParseOneResource(IParserContext context, Resource resource, Exception e);

    /**
     * 解析单个资源成功后事件
     * 
     * @param context 上下文环境
     * @param resource 解析的单个资源
     */
    public void onSuccessParseOneResource(IParserContext context, Resource resource);
}
