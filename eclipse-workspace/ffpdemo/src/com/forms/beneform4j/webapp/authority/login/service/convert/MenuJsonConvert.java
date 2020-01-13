package com.forms.beneform4j.webapp.authority.login.service.convert;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.forms.beneform4j.security.core.access.permission.impl.MenuPermission;
import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 转换返回前台菜单的属性值<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
@Service
public class MenuJsonConvert implements IJsonConverter {

    /**
     * {@inheritDoc}
     */
    @Override
    public Object convert(Object container, Object property, Object value) {
        if (property.equals("leaf")) {
            Boolean isLeaf = (Boolean) value;
            return isLeaf ? "open" : "closed";
        } else if ("path".equals(property)) {
            MenuPermission node = (MenuPermission) container;
            Map<String, Object> attributes = new HashMap<String, Object>();
            String permAttr = node.getPermAttr();
            attributes.put("path", value);
            attributes.put("url", node.getUrl());
            attributes.put("disable", "2".equals(permAttr) ? "true" : "false");
            return attributes;
        }
        return value;
    }

}
