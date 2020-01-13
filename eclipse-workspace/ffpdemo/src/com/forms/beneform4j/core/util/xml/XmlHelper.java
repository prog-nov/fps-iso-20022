package com.forms.beneform4j.core.util.xml;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.springframework.core.io.Resource;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML解析帮助类，和命名空间相关的配置存储于类路径下的beneform4j-*-namespaces.ini文件中<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class XmlHelper {

    private static final String[] defaultNamespaceMappingsLocation = {"classpath*:**/beneform4j-*-namespaces.ini"};

    @SuppressWarnings("rawtypes")
    private static final Map<String, INamespaceParser> parsers = new HashMap<String, INamespaceParser>();

    private static INIConfiguration config;

    /**
     * 获取命名空间对应的XSD文件路径配置
     * 
     * @param namespaceUri 命名空间
     * @return 命名空间对应的XSD文件路径
     */
    public static String getNamespaceSchema(String namespaceUri) {
        return getConfig(namespaceUri, "schema");
    }

    /**
     * 获取命名空间对应的解析器实现类配置
     * 
     * @param namespaceUri 命名空间
     * @return 命名空间对应的解析器实现类
     */
    public static String getNamespaceParser(String namespaceUri) {
        return getConfig(namespaceUri, "parser");
    }

    /**
     * 获取命名空间对应的所有配置
     * 
     * @param namespaceUri 命名空间
     * @return 命名空间对应的所有配置
     */
    public static Properties getNamespaceConfigs(String namespaceUri) {
        if (namespaceUri.endsWith(".xsd")) {
            namespaceUri = namespaceUri.substring(0, namespaceUri.length() - 4);
        }
        initConfig();
        SubnodeConfiguration section = null == config ? null : config.getSection(namespaceUri);
        if (null == section) {
            return null;
        }
        Properties properties = new Properties();
        for (Iterator<String> iterator = section.getKeys(); iterator.hasNext();) {
            String key = iterator.next();
            properties.setProperty(key, section.getString(key));
        }
        return properties;
    }

    /**
     * 获取XML节点所在命名空间的解析器
     * 
     * @param node 节点
     * @return 解析器
     */
    @SuppressWarnings({"unchecked"})
    public static <E extends IXmlParserContext> INamespaceParser<E> getNamespaceParser(Node node) {
        String uri = XmlHelper.getNamespaceURI(node);
        if (null == uri) {
            return null;
        } else {
            INamespaceParser<E> parser = parsers.get(uri);
            if (null == parser) {
                String parserCls = getNamespaceParser(uri);
                if (null == parserCls) {
                    CommonLogger.warn("not found the namespace parser [" + uri + "]");
                    return null;
                }
                parser = (INamespaceParser<E>) CoreUtils.newInstance(parserCls);
                CommonLogger.info("parse the namespace [" + uri + "] using parser [" + parserCls + "]");
                parsers.put(uri, parser);
            }
            return parser;
        }
    }

    /**
     * 根据名称获取相应子元素列表
     * 
     * @param ele 原始
     * @param childEleNames 子元素名称
     * @return 子元素名称与相应子元素列表构成的Map，若不存在子元素名称对应的元素，则该子元素名称对应空列表，但不会为null
     */
    public static Map<String, List<Element>> getChildElementsMapByTagName(Element ele, String... childEleNames) {
        Set<String> names = new HashSet<String>(Arrays.asList(childEleNames));
        Map<String, List<Element>> map = new HashMap<String, List<Element>>(names.size());
        for (String name : names) {
            map.put(name, new ArrayList<Element>());
        }
        NodeList nl = ele.getChildNodes();
        for (int i = 0, l = nl.getLength(); i < l; i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                String name = getLocalName(node);
                if (names.contains(name)) {
                    map.get(name).add((Element) node);
                }
            }
        }
        return map;
    }

    /**
     * 根据名称获取子元素列表
     * 
     * @param ele 元素
     * @param childEleNames 子元素名称
     * @return 子元素列表
     */
    public static List<Element> getChildElementsByTagName(Element ele, String... childEleNames) {
        List<String> childEleNameList = Arrays.asList(childEleNames);
        NodeList nl = ele.getChildNodes();
        List<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameMatch(node, childEleNameList)) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    /**
     * 根据元素名获取子元素列表
     * 
     * @param ele 元素
     * @param childEleName 子元素名称
     * @return 子元素列表
     */
    public static List<Element> getChildElementsByTagName(Element ele, String childEleName) {
        return getChildElementsByTagName(ele, new String[] {childEleName});
    }

    /**
     * 根据元素名获取第一个子元素
     * 
     * @param ele 元素
     * @param childEleName 子元素名称
     * @return 子元素
     */
    public static Element getChildElementByTagName(Element ele, String childEleName) {
        NodeList nl = ele.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && nodeNameEquals(node, childEleName)) {
                return (Element) node;
            }
        }
        return null;
    }

    /**
     * 根据元素名获取第一个子元素的文本值
     * 
     * @param ele 元素
     * @param childEleName 子元素名称
     * @return 子元素文本值
     */
    public static String getChildElementValueByTagName(Element ele, String childEleName) {
        Element child = getChildElementByTagName(ele, childEleName);
        return (child != null ? getTextValue(child) : null);
    }

    /**
     * 获取所有子元素
     * 
     * @param ele 元素
     * @return 所有的直接子元素
     */
    public static List<Element> getChildElements(Element ele) {
        NodeList nl = ele.getChildNodes();
        List<Element> childEles = new ArrayList<Element>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                childEles.add((Element) node);
            }
        }
        return childEles;
    }

    /**
     * 获取元素的文本值
     * 
     * @param valueEle 元素
     * @return 文本值
     */
    public static String getTextValue(Element valueEle) {
        StringBuilder sb = new StringBuilder();
        NodeList nl = valueEle.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node item = nl.item(i);
            if ((item instanceof CharacterData && !(item instanceof Comment)) || item instanceof EntityReference) {
                sb.append(item.getNodeValue());
            }
        }
        return sb.toString();
    }

    /**
     * 获取节点的命名空间
     * 
     * @param node 节点
     * @return 命名空间
     */
    public static String getNamespaceURI(Node node) {
        return node.getNamespaceURI();
    }

    /**
     * 获取节点的本地节点名（去除命名空间前缀后的名称）
     * 
     * @param node 节点
     * @return 节点名称
     */
    public static String getLocalName(Node node) {
        String name = node.getLocalName();
        if (null == name) {
            name = node.getNodeName();
        }
        return name;
    }

    /**
     * 忽略命名空间的情况下，节点和名称是否匹配
     * 
     * @param node 节点
     * @param desiredName 期望的名称
     * @return 是否匹配
     */
    public static boolean nodeNameEquals(Node node, String desiredName) {
        return desiredName.equals(node.getNodeName()) || desiredName.equals(getLocalName(node));
    }

    /**
     * 忽略命名空间的情况下，节点名是否为预期名称的其中一个
     * 
     * @param node 节点
     * @param desiredNames 期望的名称
     * @return 是否匹配
     */
    public static boolean nodeNameMatch(Node node, Collection<String> desiredNames) {
        return (desiredNames.contains(node.getNodeName()) || desiredNames.contains(node.getLocalName()));
    }

    /**
     * 获取第一个子元素
     * 
     * @param ele 元素
     * @return 第一个直接子元素
     * @since 1.1.0
     */
    public static Element getChildElement(Element ele) {
        NodeList nl = ele.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                return (Element) node;
            }
        }
        return null;
    }

    /**
     * 获取元素属性值
     * 
     * @param ele 元素
     * @param attr 属性名
     * @param defaultValue 默认值
     * @return 元素属性值
     * @since 1.1.0
     */
    public static String getAttribute(Element ele, String attr, String defaultValue) {
        String value = ele.getAttribute(attr);
        if (CoreUtils.isBlank(value)) {
            value = defaultValue;
        } else {
            value = value.trim();
        }
        return value;
    }

    private static String getConfig(String namespaceUri, String key) {
        if (namespaceUri.endsWith(".xsd")) {
            namespaceUri = namespaceUri.substring(0, namespaceUri.length() - 4);
        }
        initConfig();
        SubnodeConfiguration section = null == config ? null : config.getSection(namespaceUri);
        String value = section == null ? null : section.getString(key);
        return value;
    }

    private static void initConfig() {
        if (config == null) {
            synchronized (XmlHelper.class) {
                if (config == null) {
                    String[] namespaceMappingsLocation = defaultNamespaceMappingsLocation;
                    try {
                        Set<Resource> resources = CoreUtils.getResources(null, namespaceMappingsLocation);
                        INIConfiguration config = new INIConfiguration();
                        for (Resource resource : resources) {
                            config.read(new InputStreamReader(resource.getInputStream()));
                        }
                        XmlHelper.config = config;
                    } catch (Exception ex) {
                        throw new IllegalStateException("Unable to load ini config from location [" + CoreUtils.join(namespaceMappingsLocation, ",") + "]", ex);
                    }
                }
            }
        }
    }
}
