package com.forms.beneform4j.core.service.spring.schema.logger;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.forms.beneform4j.core.service.spring.schema.logger.parser.LoggerParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日志命名空间处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class Beneform4jLoggerNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("logger", new LoggerParser());
    }

}
