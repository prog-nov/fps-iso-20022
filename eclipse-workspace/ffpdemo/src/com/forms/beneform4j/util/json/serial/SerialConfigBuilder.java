package com.forms.beneform4j.util.json.serial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.util.json.serial.converter.IJsonConverter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 序列化配置构建工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
public class SerialConfigBuilder {

    private final List<SerialConfigBuilder> builders;
    private Class<?> serialType;
    private final Map<String, String> aliases = new HashMap<String, String>();
    private final Set<String> excludeProperties = new HashSet<String>();
    private final Set<String> includeProperties = new HashSet<String>();
    private final Map<String, IJsonConverter> converters = new HashMap<String, IJsonConverter>();

    /**
     * 开始构建
     * 
     * @return
     */
    public static SerialConfigBuilder start() {
        return new SerialConfigBuilder(null);
    }

    /**
     * 添加一个新实例
     * 
     * @return
     */
    public SerialConfigBuilder newInstance() {
        return new SerialConfigBuilder(this.builders);
    }

    /**
     * 私有构造函数
     * 
     * @param builders
     */
    private SerialConfigBuilder(List<SerialConfigBuilder> builders) {
        this.builders = builders == null ? new ArrayList<SerialConfigBuilder>() : builders;
        this.builders.add(this);
    }

    /**
     * 构建单个类型的配置，如果有多个只返回最后一个
     * 
     * @return
     */
    public ISerialConfig build() {
        return new ISerialConfig() {
            @Override
            public Class<?> getSerialType() {
                return serialType;
            }

            @Override
            public Map<String, String> getAliases() {
                return aliases;
            }

            @Override
            public Set<String> getExcludeProperties() {
                return excludeProperties;
            }

            @Override
            public Set<String> getIncludeProperties() {
                return includeProperties;
            }

            @Override
            public Map<String, IJsonConverter> getConverters() {
                return converters;
            }

            @Override
            public boolean isValid() {
                return (null != aliases && !aliases.isEmpty()) || (null != excludeProperties && !excludeProperties.isEmpty()) || (null != includeProperties && !includeProperties.isEmpty()) || (null != converters && !converters.isEmpty());
            }
        };
    }

    /**
     * 构建类型的多个配置
     * 
     * @return
     */
    public ISerialConfig[] builds() {
        int size = null == builders ? 0 : builders.size();
        if (size > 0) {
            ISerialConfig[] configs = new ISerialConfig[builders.size()];
            for (int i = 0; i < size; i++) {
                configs[i] = builders.get(i).build();
            }
            return configs;
        }
        return null;
    }

    /**
     * 设置类型
     * 
     * @param serialType
     * @return
     */
    public SerialConfigBuilder serialType(Class<?> serialType) {
        this.serialType = serialType;
        return this;
    }

    /**
     * 添加属性别名
     * 
     * @param property
     * @param alias
     * @return
     */
    public SerialConfigBuilder addAlias(String property, String alias) {
        this.aliases.put(property, alias);
        return this;
    }

    /**
     * 添加一组属性别名
     * 
     * @param aliases
     * @return
     */
    public SerialConfigBuilder addAliases(Map<String, String> aliases) {
        if (null != aliases) {
            this.aliases.putAll(aliases);
        }
        return this;
    }

    /**
     * 添加需要包含的属性
     * 
     * @param property
     * @return
     */
    public SerialConfigBuilder addIncludeProperty(String... property) {
        if (null != property) {
            for (String p : property) {
                this.includeProperties.add(p);
            }
        }
        return this;
    }

    /**
     * 添加一组需要包含的属性
     * 
     * @param includeProperties
     * @return
     */
    public SerialConfigBuilder addIncludeProperties(Collection<String> includeProperties) {
        if (null != includeProperties) {
            this.includeProperties.addAll(includeProperties);
        }
        return this;
    }

    /**
     * 添加需要排除的属性
     * 
     * @param property
     * @return
     */
    public SerialConfigBuilder addExcludeProperty(String... property) {
        if (null != property) {
            for (String p : property) {
                this.excludeProperties.add(p);
            }
        }
        return this;
    }

    /**
     * 添加需要排除的一组属性
     * 
     * @param excludeProperties
     * @return
     */
    public SerialConfigBuilder addExcludeProperties(Collection<String> excludeProperties) {
        if (null != excludeProperties) {
            this.excludeProperties.addAll(excludeProperties);
        }
        return this;
    }

    /**
     * 添加属性转换器
     * 
     * @param property
     * @param converter
     * @return
     */
    public SerialConfigBuilder addConverter(String property, IJsonConverter converter) {
        this.converters.put(property, converter);
        return this;
    }

    /**
     * 添加属性转换器
     * 
     * @param converters
     * @return
     */
    public SerialConfigBuilder addConverters(Map<String, IJsonConverter> converters) {
        if (null != converters) {
            this.converters.putAll(converters);
        }
        return this;
    }
}
