package com.forms.beneform4j.core.util.parser;

import com.forms.beneform4j.core.util.parser.impl.ParserComponentFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解析器组件类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public class ParserComponents {

    private static IParserComponentFactory factory = new ParserComponentFactory();

    /**
     * 获取组件工厂
     * 
     * @return 组件工厂
     */
    public static IParserComponentFactory getFactory() {
        return factory;
    }

    /**
     * 设置组件工厂
     * 
     * @param factory 组件工厂
     */
    public void setFactory(IParserComponentFactory factory) {
        if (null != factory) {
            ParserComponents.factory = factory;
        }
    }
}
