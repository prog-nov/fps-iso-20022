package com.forms.beneform4j.core.dao.mybatis.mapper.impl;

import com.forms.beneform4j.core.dao.mybatis.mapper.IMapperMethodExecutor;
import com.forms.beneform4j.core.dao.mybatis.mapper.MapperMethod;
import com.forms.beneform4j.core.dao.mybatis.mapper.MapperMethod.SqlCommand;
import com.forms.beneform4j.core.dao.stream.IListStreamReader;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据列表流相关的MapperMethod执行器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-6<br>
 */
public abstract class DataStreamMapperMethodExecutor implements IMapperMethodExecutor {

    private static final ThreadLocal<IListStreamReader<?>> streams = new ThreadLocal<IListStreamReader<?>>();

    /**
     * 必须返回列表数据
     */
    @Override
    public boolean isSupport(MapperMethod mapperMethod) {
        return mapperMethod.getMethodSignature().returnsMany() && isSupport();
    }

    abstract protected boolean isSupport();

    protected int getFetchSize() {
        return 1000;
    }

    @Override
    public Object execute(MapperMethod mapperMethod, Object[] args) {
        SqlCommand command = mapperMethod.getCommand();
        String sqlId = command.getName();
        Object parameter = mapperMethod.getMethodSignature().convertArgsToSqlCommandParam(args);
        int fetchSize = getFetchSize();
        IListStreamReader<?> reader = command.getDao().selectListStream(sqlId, parameter, fetchSize);
        streams.set(reader);
        return null;
    }

    public static IListStreamReader<?> getDataStreamReader() {
        return streams.get();
    }

    public static void clearDataStreamReader() {
        streams.remove();
    }
}
