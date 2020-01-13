package com.forms.beneform4j.core.util.data.accessor;

import java.util.Map;

import com.forms.beneform4j.core.util.config.BaseConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据访问器工厂静态帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.1 <br>
 * Since : 1.0.1 <br>
 * Date : 2017-1-17<br>
 */
public class DataAccessors {

    public static IDataAccessor newDataAccessor() {
        return BaseConfig.getDataAccessorFactory().newDataAccessor();
    }

    public static IDataAccessor newDataAccessor(Object root) {
        return BaseConfig.getDataAccessorFactory().newDataAccessor(root);
    }

    public static IDataAccessor newDataAccessor(Object root, Map<String, Object> vars) {
        return BaseConfig.getDataAccessorFactory().newDataAccessor(root, vars);
    }
}
