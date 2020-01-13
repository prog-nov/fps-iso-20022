package com.forms.beneform4j.core.util.xml.parser;

import java.util.List;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import com.forms.beneform4j.core.util.parser.IParser;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.factory.IXmlComponentFactory;
import com.forms.beneform4j.core.util.xml.factory.impl.XmlComponentFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML解析帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class XmlParserUtils {

    private static IXmlComponentFactory factory = new XmlComponentFactory();

    /**
     * 获取XML解析组件工厂类
     * 
     * @return XML解析组件工厂类
     */
    public static IXmlComponentFactory getFactory() {
        return factory;
    }

    /**
     * 设置XML解析组件工厂类
     * 
     * @param factory XML解析组件工厂类
     */
    public void setFactory(IXmlComponentFactory factory) {
        if (null != factory) {
            XmlParserUtils.factory = factory;
        }
    }

    /**
     * 解析XML文件
     * 
     * @param locationPattern 路径模式
     */
    public static void parseXml(String locationPattern) {
        parseXml(new String[] {locationPattern});
    }

    /**
     * 使用指定命名空间解析器来解析XML文件
     * 
     * @param parser 解析器
     * @param locationPattern 路径模式
     */
    public static void parseXml(INamespaceParser<IXmlParserContext> parser, String locationPattern) {
        parseXml(parser, new String[] {locationPattern});
    }

    /**
     * 在预设的解析环境中解析XML文件
     * 
     * @param context 解析环境
     * @param locationPattern 路径模式
     */
    public static void parseXml(IXmlParserContext context, String locationPattern) {
        parseXml(context, new String[] {locationPattern});
    }

    /**
     * 解析XML文件
     * 
     * @param locationPatterns 路径模式数组
     */
    public static void parseXml(String[] locationPatterns) {
        parseXml((INamespaceParser<IXmlParserContext>) null, locationPatterns);
    }

    /**
     * 使用指定命名空间解析器来解析XML文件
     * 
     * @param parser 解析器
     * @param locationPatterns 路径模式数组
     */
    public static void parseXml(INamespaceParser<IXmlParserContext> parser, String[] locationPatterns) {
        IXmlParserContext parserContext = factory.newXmlParserContext();
        IParser<IXmlParserContext> xp = factory.newXmlParser(parser);
        xp.parse(parserContext, locationPatterns);
    }

    /**
     * 在预设的解析环境中解析XML文件
     * 
     * @param context 解析环境
     * @param locationPatterns 路径模式数组
     */
    public static void parseXml(IXmlParserContext context, String[] locationPatterns) {
        IParser<IXmlParserContext> xp = factory.newXmlParser();
        xp.parse(context, locationPatterns);
    }

    /**
     * 解析XML文件
     * 
     * @param resource 资源对象
     */
    public static void parseXml(Resource resource) {
        parseXml((INamespaceParser<IXmlParserContext>) null, resource);
    }

    /**
     * 使用指定命名空间解析器来解析XML文件
     * 
     * @param parser 解析器
     * @param resource 资源对象
     */
    public static void parseXml(INamespaceParser<IXmlParserContext> parser, Resource resource) {
        IXmlParserContext parserContext = factory.newXmlParserContext();
        IParser<IXmlParserContext> xp = factory.newXmlParser(parser);
        xp.parse(parserContext, resource);
    }

    /**
     * 在预设的解析环境中解析XML文件
     * 
     * @param context 解析环境
     * @param resource 资源对象
     */
    public static void parseXml(IXmlParserContext context, Resource resource) {
        IParser<IXmlParserContext> xp = factory.newXmlParser();
        xp.parse(context, resource);
    }

    /**
     * 解析XML文件为Document对象
     * 
     * @param locationPattern 路径模式
     * @return Document对象集合
     */
    public static List<Document> buildDocuments(String locationPattern) {
        return buildDocuments(new String[] {locationPattern});
    }

    /**
     * 在预设的解析环境中解析XML文件为Document对象
     * 
     * @param context 解析环境
     * @param locationPattern 路径模式
     * @return Document对象集合
     */
    public static List<Document> buildDocuments(IXmlParserContext context, String locationPattern) {
        return buildDocuments(context, new String[] {locationPattern});
    }

    /**
     * 解析XML文件为Document对象
     * 
     * @param locationPatterns 路径模式数组
     * @return Document对象集合
     */
    public static List<Document> buildDocuments(String[] locationPatterns) {
        return buildDocuments(null, locationPatterns);
    }

    /**
     * 在预设的解析环境中解析XML文件为Document对象集合
     * 
     * @param context 解析环境
     * @param locationPatterns 路径模式数组
     * @return Document对象集合
     */
    public static List<Document> buildDocuments(IXmlParserContext context, String[] locationPatterns) {
        if (null == context) {
            context = factory.newXmlParserContext();
        }
        IXmlParser<IXmlParserContext> xp = factory.newXmlParser();
        return xp.buildDocuments(context, locationPatterns);
    }

    /**
     * 解析XML文件为Document对象
     * 
     * @param resource 资源对象
     * @return Document对象
     */
    public static Document buildDocument(Resource resource) {
        return buildDocument(null, resource);
    }

    /**
     * 在预设的解析环境中解析XML文件为Document对象
     * 
     * @param context 解析环境
     * @param resource 资源对象
     * @return Document对象
     */
    public static Document buildDocument(IXmlParserContext context, Resource resource) {
        if (null == context) {
            context = factory.newXmlParserContext();
        }
        IXmlParser<IXmlParserContext> xp = factory.newXmlParser();
        return xp.buildDocument(context, resource);
    }
}
