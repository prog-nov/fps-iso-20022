package com.forms.beneform4j.util.json.serial;

import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : JSON序列化配置接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
public interface ISerialConfig {

    /**
     * 获取目标类型
     * 
     * @return
     */
    public Class<?> getSerialType();

    /**
     * 获取属性别名
     * 
     * @return
     */
    public Map<String, String> getAliases();

    /**
     * 获取需要排除的属性
     * 
     * @return
     */
    public Set<String> getExcludeProperties();

    /**
     * 获取需要包含的属性
     * 
     * @return
     */
    public Set<String> getIncludeProperties();

    /**
     * 获取属转换器
     * 
     * @return
     */
    public Map<String, IJsonConverter> getConverters();

    /**
     * 是否有效
     * 
     * @return
     */
    public boolean isValid();
}
