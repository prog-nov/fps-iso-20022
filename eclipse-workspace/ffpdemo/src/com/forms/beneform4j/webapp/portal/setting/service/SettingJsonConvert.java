package com.forms.beneform4j.webapp.portal.setting.service;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 转换返回前台用户参数的属性值<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-16<br>
 */
@Service
public class SettingJsonConvert implements IJsonConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object convert(Object container, Object property, Object value) {
        if (property.equals("selected")) {
            return value.equals("true") ? true : false;
        }
        return value;
    }

}
