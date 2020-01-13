package com.forms.beneform4j.core.dao.util;

import java.util.HashMap;
import java.util.Map;

import com.forms.beneform4j.core.util.CoreUtils;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据库驱动工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class DriverUtilsImpl {

    private static final DriverUtilsImpl instance = new DriverUtilsImpl() {};

    private DriverUtilsImpl() {}

    static DriverUtilsImpl getInstance() {
        return instance;
    }

    private static final Map<String, Class<?>> driver = new HashMap<String, Class<?>>();

    /**
     * 加载数据库驱动
     * 
     * @param driverClassName
     * @return
     */
    public boolean load(String driverClassName) {
        if (!driver.containsKey(driverClassName)) {
            synchronized (driver) {
                if (!driver.containsKey(driverClassName)) {
                    try {
                        Class<?> cls = CoreUtils.forName(driverClassName);
                        driver.put(driverClassName, cls);
                        return true;
                    } catch (Exception e) {
                        driver.put(driverClassName, null);
                        return false;
                    }
                } else {
                    return null != driver.get(driverClassName);
                }
            }
        } else {
            return null != driver.get(driverClassName);
        }
    }
}
