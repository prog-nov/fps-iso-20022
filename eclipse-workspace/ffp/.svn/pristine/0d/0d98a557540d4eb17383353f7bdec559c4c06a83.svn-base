package com.forms.beneform4j.core.util.xml.context;

import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.util.xml.XmlValidationModeDetector;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

import com.forms.beneform4j.core.util.parser.IParserContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : XML文件解析环境<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public interface IXmlParserContext extends IParserContext {

    /**
     * 获取文档加载器
     * 
     * @return 文档加载器
     */
    public DocumentLoader getDocumentLoader();

    /**
     * 获取实体解析器
     * 
     * @return 实体解析器
     */
    public EntityResolver getEntityResolver();

    /**
     * 获取错误处理器
     * 
     * @return 错误处理器
     */
    public ErrorHandler getErrorHandler();

    /**
     * 获取XML校验模式
     * 
     * @return XML校验模式
     * @since 1.1.0
     */
    public XmlValidationMode getXmlValidationMode();

    /**
     * XML校验模式
     * 
     * @since 1.1.0
     */
    public enum XmlValidationMode {
        /**
         * 不校验
         */
        NONE(XmlValidationModeDetector.VALIDATION_NONE),
        /**
         * 自动检测
         */
        AUTO(XmlValidationModeDetector.VALIDATION_AUTO),
        /**
         * DTD校验
         */
        DTD(XmlValidationModeDetector.VALIDATION_DTD),
        /**
         * XSD校验
         */
        XSD(XmlValidationModeDetector.VALIDATION_XSD);

        private final int mode;

        private XmlValidationMode(int mode) {
            this.mode = mode;
        }

        public int getMode() {
            return mode;
        }
    }
}
