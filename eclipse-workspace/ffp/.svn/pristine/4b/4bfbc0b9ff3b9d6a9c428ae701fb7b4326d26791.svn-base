package com.forms.beneform4j.util.json.serial.stdexp;

import java.util.HashSet;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.PropertyBuilder;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Bean序列器工厂，覆盖查找Map类型的序列器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-20<br>
 */
public class JsonBeanSerializerFactory extends BeanSerializerFactory {

    /**
     * 
     */
    private static final long serialVersionUID = 4376466461858249985L;

    public JsonBeanSerializerFactory(SerializerFactoryConfig config) {
        super(config);
    }

    @Override
    protected PropertyBuilder constructPropertyBuilder(SerializationConfig config, BeanDescription beanDesc) {
        return new JsonPropertyBuilder(config, beanDesc);
    }

    @Override
    protected JsonSerializer<?> buildMapSerializer(SerializerProvider prov, MapType type, BeanDescription beanDesc, boolean staticTyping, JsonSerializer<Object> keySerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> elementValueSerializer) throws JsonMappingException {
        final SerializationConfig config = prov.getConfig();
        JsonSerializer<?> ser = null;

        ser = findSerializerByAnnotations(prov, type, beanDesc); // (2) Annotations
        if (ser == null) {
            Object filterId = findFilterId(config, beanDesc);
            AnnotationIntrospector ai = config.getAnnotationIntrospector();
            MapSerializer mapSer = construct(ai.findPropertiesToIgnore(beanDesc.getClassInfo(), true), type, staticTyping, elementTypeSerializer, keySerializer, elementValueSerializer, filterId);
            Object suppressableValue = findSuppressableContentValue(config, type.getContentType(), beanDesc);
            if (suppressableValue != null) {
                mapSer = mapSer.withContentInclusion(suppressableValue);
            }
            ser = mapSer;
            if (null != ser) {
                return ser;
            }
        }

        return super.buildMapSerializer(prov, type, beanDesc, staticTyping, keySerializer, elementTypeSerializer, elementValueSerializer);
    }

    /**
     * @since 2.3
     */
    public MapSerializer construct(String[] ignoredList, JavaType mapType, boolean staticValueType, TypeSerializer vts, JsonSerializer<Object> keySerializer, JsonSerializer<Object> valueSerializer, Object filterId) {
        HashSet<String> ignoredEntries = (ignoredList == null || ignoredList.length == 0) ? null : ArrayBuilders.arrayToSet(ignoredList);

        JavaType keyType, valueType;

        if (mapType == null) {
            keyType = valueType = TypeFactory.unknownType();
        } else {
            keyType = mapType.getKeyType();
            valueType = mapType.getContentType();
        }
        // If value type is final, it's same as forcing static value typing:
        if (!staticValueType) {
            staticValueType = (valueType != null && valueType.isFinal());
        } else {
            // also: Object.class can not be handled as static, ever
            if (valueType.getRawClass() == Object.class) {
                staticValueType = false;
            }
        }
        MapSerializer ser = new JsonMapSerializer(ignoredEntries, keyType, valueType, staticValueType, vts, keySerializer, valueSerializer);
        if (filterId != null) {
            ser = ser.withFilterId(filterId);
        }
        return ser;
    }

}
