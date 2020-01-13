package com.forms.beneform4j.core.service.spring.schema.common.parser;

import org.w3c.dom.Element;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 自定义元素的解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class CustomElementParser extends AbstractParser {

    public static final CustomElementParser CUSTOM_ELEMENT_PARSER = new CustomElementParser();

    @Override
    protected String getBeanClassName(Element element) {
        String cls = element.getAttribute("class");
        if (!CoreUtils.isBlank(cls)) {
            return cls;
        }
        return null;
    }
}
