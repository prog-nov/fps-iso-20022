package com.forms.beneform4j.util.json.serial.converter.impl;

import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 国际化值转换器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
public class I18nJsonConverter implements IJsonConverter {

    /**
     * 转换
     * 
     * @param container 容器，一般为JavaBean或Map对象
     * @param property 属性名称
     * @param value 属性值
     * @return 转换后的值
     */
    @Override
    public Object convert(Object container, Object property, Object value) {
        String key = getLocaleKey(container, property, value);
        if (null == key) {
            return null;
        } else {
            return Tool.LOCALE.getMessage(key);
        }
    }

    /**
     * 获取国际化Key值
     * 
     * @param container 容器，一般为JavaBean或Map对象
     * @param property 属性名称
     * @param value 属性值
     * @return 国际化文件中的Key值
     */
    protected String getLocaleKey(Object container, Object property, Object value) {
        if (value == null) {
            return null;
        } else {
            return value.toString();
        }
    }
}
