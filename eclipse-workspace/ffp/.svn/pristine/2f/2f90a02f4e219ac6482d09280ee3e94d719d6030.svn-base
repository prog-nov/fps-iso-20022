package com.forms.beneform4j.core.util.xml.parser.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.parser.impl.AbstractParser;
import com.forms.beneform4j.core.util.xml.XmlHelper;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext;
import com.forms.beneform4j.core.util.xml.context.IXmlParserContext.XmlValidationMode;
import com.forms.beneform4j.core.util.xml.parser.INamespaceParser;
import com.forms.beneform4j.core.util.xml.parser.IXmlParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的XML解析实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public abstract class AbstractXmlParser<E extends IXmlParserContext> extends AbstractParser<E> implements IXmlParser<E> {

    private static final String PROFILE_ATTRIBUTE = "profile";

    private static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";

    private final XmlValidationModeDetector validationModeDetector = new XmlValidationModeDetector();

    /**
     * 默认XML解析回调
     */
    private final ParseCallback<E, Document> defaultXmlParseCallback = new ParseCallback<E, Document>() {
        @Override
        public Document callback(E parserContext, EncodedResource resource) throws IOException {
            InputStream inputStream = null;
            try {
                inputStream = resource.getResource().getInputStream();
                InputSource inputSource = new InputSource(inputStream);
                if (resource.getEncoding() != null) {
                    inputSource.setEncoding(resource.getEncoding());
                }
                return doLoadDocument(parserContext, inputSource, resource.getResource());
            } finally {
                CoreUtils.closeQuietly(inputStream);
            }
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Document> buildDocuments(E parserContext, String locationPattern) {
        return buildDocuments(parserContext, new String[] {locationPattern});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Document> buildDocuments(E parserContext, String[] locationPatterns) {
        Environment environment = extractEnvironment(parserContext);
        Set<Resource> resources = CoreUtils.getResources(environment, locationPatterns);
        return doBuildDocuments(parserContext, resources);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document buildDocument(E parserContext, InputStream inputStream) {
        return buildDocument(parserContext, new InputStreamResource(inputStream));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Document buildDocument(E parserContext, Resource resource) {
        List<Document> documents = doBuildDocuments(parserContext, Arrays.asList(resource));
        if (null == documents || documents.isEmpty()) {
            return null;
        }
        return documents.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doParse(E parserContext, EncodedResource resource) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = resource.getResource().getInputStream();
            InputSource inputSource = new InputSource(inputStream);
            if (resource.getEncoding() != null) {
                inputSource.setEncoding(resource.getEncoding());
            }
            doParse(parserContext, inputSource, resource.getResource());
        } finally {
            CoreUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 构建文档后事件
     * 
     * @param parserContext 解析环境
     * @param document 文档对象
     * @param resource 资源对象
     */
    protected void afterBuildDocument(E parserContext, Document document, Resource resource) {

    }

    /**
     * 解析文档
     * 
     * @param parserContext 解析环境
     * @param document 文档对象
     * @param resource 资源对象
     */
    protected abstract void parseDocument(E parserContext, Document document, Resource resource);

    /**
     * 获取节点所在命名空间的解析器
     * 
     * @param node 节点
     * @return 节点所在命名空间的解析器
     */
    protected INamespaceParser<E> getNamespaceParser(Node node) {
        return XmlHelper.getNamespaceParser(node);
    }

    /**
     * 执行解析，根据根元素的profile属性判断环境是否匹配，不匹配则终止解析
     * 
     * @param parserContext
     * @param inputSource
     * @param resource
     */
    private void doParse(E parserContext, InputSource inputSource, Resource resource) {
        Document doc = doLoadDocument(parserContext, inputSource, resource);
        Element root = doc.getDocumentElement();
        Environment environment = extractEnvironment(parserContext);

        String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);
        if (null != environment && !CoreUtils.isBlank(profileSpec)) {
            String[] specifiedProfiles = StringUtils.tokenizeToStringArray(profileSpec, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
            if (!environment.acceptsProfiles(specifiedProfiles)) {
                CommonLogger.info("Skipped XML file due to specified profiles [" + profileSpec + "] not matching: " + resource);
                return;
            }
        }
        afterBuildDocument(parserContext, doc, resource);
        parseDocument(parserContext, doc, resource);
    }

    /**
     * 加载文档
     * 
     * @param parserContext
     * @param inputSource
     * @param resource
     * @return
     */
    private Document doLoadDocument(E parserContext, InputSource inputSource, Resource resource) {
        DocumentLoader loader = extractDocumentLoader(parserContext);
        if (null == loader) {
            Throw.throwRuntimeException("DocumentLoader is null when parsing XML document from " + resource.getDescription());
        }
        EntityResolver entityResolver = extractEntityResolver(parserContext);
        ErrorHandler errorHandler = extractErrorHandler(parserContext);
        XmlValidationMode mode = parserContext.getXmlValidationMode();
        int validationMode;
        if (null == mode || XmlValidationMode.AUTO.equals(mode)) {
            validationMode = detectValidationMode(resource);
        } else {
            validationMode = mode.getMode();
        }
        // 如果是XSD，加载器会强制设置namespaceAware为true
        Document document = null;
        try {
            document = loader.loadDocument(inputSource, entityResolver, errorHandler, validationMode, false);
        } catch (SAXParseException ex) {
            Throw.throwRuntimeException("Line " + ex.getLineNumber() + " in XML document from " + resource.getDescription() + " is invalid", ex);
        } catch (SAXException ex) {
            Throw.throwRuntimeException("XML document from " + resource.getDescription() + " is invalid", ex);
        } catch (ParserConfigurationException ex) {
            Throw.throwRuntimeException("Parser configuration exception parsing XML from " + resource.getDescription(), ex);
        } catch (IOException ex) {
            Throw.throwRuntimeException("IOException parsing XML document from " + resource.getDescription(), ex);
        } catch (Throwable ex) {
            Throw.throwRuntimeException("Unexpected exception parsing XML document from " + resource.getDescription(), ex);
        }
        return document;
    }

    protected ErrorHandler extractErrorHandler(E parserContext) {
        ErrorHandler errorHandler = parserContext.getErrorHandler();
        return errorHandler;
    }

    protected EntityResolver extractEntityResolver(E parserContext) {
        EntityResolver entityResolver = parserContext.getEntityResolver();
        return entityResolver;
    }

    protected DocumentLoader extractDocumentLoader(E parserContext) {
        return parserContext.getDocumentLoader();
    }

    /**
     * 侦测校验模式
     * 
     * @param resource
     * @return
     */
    private int detectValidationMode(Resource resource) {
        if (resource.isOpen()) {
            throw Throw.createRuntimeException("Passed-in Resource [" + resource + "] contains an open stream: " + "cannot determine validation mode automatically.");
        }

        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            return validationModeDetector.detectValidationMode(inputStream);
        } catch (IOException ex) {
            throw Throw.createRuntimeException("Unable to determine validation mode for [" + resource + "]: an error occurred whilst reading from the InputStream.", ex);
        } finally {
            CoreUtils.closeQuietly(inputStream);
        }
    }

    /**
     * 构建一组文档
     * 
     * @param parserContext
     * @param resources
     * @return
     */
    private List<Document> doBuildDocuments(E parserContext, Collection<Resource> resources) {
        return super.doParse(parserContext, resources, defaultXmlParseCallback);
    }
}
