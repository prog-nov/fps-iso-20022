package com.forms.beneform4j.util.toolimpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.BasicSerializerFactory;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.json.serial.ISerialConfig;
import com.forms.beneform4j.util.json.serial.SerialConfigContext;
import com.forms.beneform4j.util.json.serial.stdexp.JsonBeanSerializerFactory;
import com.forms.beneform4j.util.json.serial.stdexp.JsonClassIntrospector;
import com.forms.beneform4j.util.json.serial.wrapper.IJsonWrapper;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : JSON工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-2-23<br>
 */
public abstract class JsonUtilsImpl {

    private static final JsonUtilsImpl instance = new JsonUtilsImpl() {};

    private JsonUtilsImpl() {}

    /**
     * 获取单实例
     * 
     * @return
     */
    public static JsonUtilsImpl getInstance() {
        return instance;
    }

    /**
     * JSON对象
     */
    private static ObjectMapper mapper = null;

    /**
     * 获取单例ObjectMapper实例
     * 
     * @return ObjectMapper单实例
     */
    public ObjectMapper getSingleonObjectMapper() {
        if (null == mapper) {
            synchronized (JsonUtilsImpl.class) {
                if (null == mapper) {
                    ObjectMapper mapper = getObjectMapper();
                    JsonUtilsImpl.mapper = mapper;
                }
            }
        }
        return mapper;
    }

    /**
     * 获取新的ObjectMapper实例
     * 
     * @return ObjectMapper 新实例
     */
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true) // 允许使用单引号
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true) // 允许字段名不用引号
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) // 允许字段名不用引号
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)// 忽略字符串有java中没有的属性
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)//
                .configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false)// 自引用时是否失败
                .configure(SerializationFeature.INDENT_OUTPUT, true)// 格式化输出
                .configure(MapperFeature.AUTO_DETECT_FIELDS, false).configure(MapperFeature.AUTO_DETECT_GETTERS, true).configure(MapperFeature.AUTO_DETECT_IS_GETTERS, true).setSerializationInclusion(Include.NON_NULL);// 是否包括null值属性

        // 使用自定义的类侦测器
        JsonClassIntrospector jci = new JsonClassIntrospector();
        mapper.setConfig(mapper.getSerializationConfig().with(jci));
        // 使用自定义的序列化工厂类（目前只替换了Map的序列化）
        BasicSerializerFactory sf = (BasicSerializerFactory) mapper.getSerializerFactory();
        mapper.setSerializerFactory(new JsonBeanSerializerFactory(sf.getFactoryConfig()));
        return mapper;
    }

    /**
     * 将Json字符串转变为Map<String, Object>
     * 
     * @param jsonString json字符串
     * @return Map<String, Object>对象
     */
    public Map<String, Object> deserial2Map(String jsonString) {
        try {
            return getSingleonObjectMapper().readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 将Json字符串转变为List<Map<String, Object>>
     * 
     * @param jsonString json字符串
     * @return List<Map<String, Object>>对象
     */
    public List<Map<String, Object>> deserial2ListMap(String jsonString) {
        try {
            return getSingleonObjectMapper().readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        }
    }

    /**
     * 根据配置序列化对象
     * 
     * @param original 原始对象
     * @param configs 序列化配置
     * @return json字符串
     */
    public String serialize(Object original, ISerialConfig... configs) {
        return serialize(original, null, configs);
    }

    /**
     * 根据配置和包装对象序列化对象
     * 
     * @param original 原始对象
     * @param wrapper 原始对象的包装器
     * @param configs 序列化配置
     * @return json字符串
     */
    public String serialize(Object original, IJsonWrapper wrapper, ISerialConfig... configs) {
        try {
            SerialConfigContext.addSerialConfigs(configs);
            Object target = original;
            if (null != wrapper) {
                target = wrapper.wrap(original);
            }
            return getContextObjectMapper().writeValueAsString(target);
        } catch (IOException e) {
            throw Throw.createRuntimeException(e);
        } finally {
            SerialConfigContext.remove();
        }
    }

    /**
     * 根据配置序列化对象至输出流
     * 
     * @param out 输出流
     * @param original 原始对象
     * @param configs 序列化配置
     */
    public void serialize(OutputStream out, Object original, ISerialConfig... configs) {
        serialize(out, original, null, configs);
    }

    /**
     * 根据配置和包装对象序列化对象至输出流
     * 
     * @param out 输出流
     * @param original 原始对象
     * @param wrapper 包装器
     * @param configs 序列化配置
     */
    public void serialize(OutputStream out, Object original, IJsonWrapper wrapper, ISerialConfig... configs) {
        try {
            SerialConfigContext.addSerialConfigs(configs);
            Object target = original;
            if (null != wrapper) {
                target = wrapper.wrap(original);
            }
            getContextObjectMapper().writeValue(out, target);
        } catch (IOException e) {
            Throw.throwRuntimeException(e);
        } finally {
            SerialConfigContext.remove();
        }
    }

    /**
     * 反序列化对象
     * 
     * @param content Json字符串
     * @param list LIST泛型类型
     * @return List<T>
     */
    public <T> List<T> deserialize2ListBean(String content, Class<T> cls) {

        try {
            return getSingleonObjectMapper().readValue(content, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            Throw.throwRuntimeException(e);
        }
        return null;
    }

    /**
     * 反序列化对象
     * 
     * @param content Json字符串
     * @param cls 返回的类对象
     * @return cls类实例
     */
    public <T> T deserialize2Bean(String content, Class<T> cls) {

        try {
            return getSingleonObjectMapper().readValue(content, cls);
        } catch (IOException e) {
            Throw.throwRuntimeException(e);
        }
        return null;
    }

    /**
     * 反序列化对象
     * 
     * @param stream 输入流
     * @param list LIST泛型类型
     * @return List<T>
     */
    public <T> List<T> deserialize2ListBean(InputStream stream, Class<T> cls) {

        try {
            return getSingleonObjectMapper().readValue(stream, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            Throw.throwRuntimeException(e);
        }
        return null;
    }

    /**
     * 反序列化对象
     * 
     * @param stream 输入流
     * @param cls 返回的类对象
     * @return
     */
    public <T> T deserialize2Bean(InputStream stream, Class<T> cls) {

        try {
            return getSingleonObjectMapper().readValue(stream, cls);
        } catch (IOException e) {
            Throw.throwRuntimeException(e);
        }
        return null;
    }

    private ObjectMapper getContextObjectMapper() {
        if (SerialConfigContext.isValid()) {
            return getObjectMapper();
        } else {
            return getSingleonObjectMapper();
        }
    }
}
