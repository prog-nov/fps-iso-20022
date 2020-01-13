package com.forms.beneform4j.core.dao.mybatis.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 自动映射注册类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class MapperRegistry extends org.apache.ibatis.binding.MapperRegistry {

    private Configuration config;
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<Class<?>, MapperProxyFactory<?>>();

    public MapperRegistry(Configuration config) {
        super(config);
        this.config = config;
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null)
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new BindingException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                knownMappers.put(type, new MapperProxyFactory<T>(type));
                // It's important that the type is added before the parser is run
                // otherwise the binding may automatically be attempted by the
                // mapper parser. If the type is already known, it won't try.
                MapperAnnotationBuilder parser = new MapperAnnotationBuilder(config, type);
                parser.parse();
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    knownMappers.remove(type);
                }
            }
        }
    }

    /**
     * @since 3.2.2
     */
    public Collection<Class<?>> getMappers() {
        return Collections.unmodifiableCollection(knownMappers.keySet());
    }

}
