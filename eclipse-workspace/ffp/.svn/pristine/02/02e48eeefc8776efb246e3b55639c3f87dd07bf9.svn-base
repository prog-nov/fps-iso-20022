package com.forms.beneform4j.core.service.spring.schema.exception;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.forms.beneform4j.core.service.spring.schema.exception.parser.ExceptionParser;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常命名空间处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class Beneform4jExceptionNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("exception", new ExceptionParser());
        // registerBeanDefinitionParser("exception-config", new ExceptionParser());
    }

}
