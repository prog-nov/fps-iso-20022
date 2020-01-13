package com.forms.beneform4j.core.service.spring.schema.exception.parser;

import java.util.Set;

import com.forms.beneform4j.core.util.exception.meta.impl.ExceptionMeta;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 单个异常配置元素的容器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class SchemaExceptionMeta extends ExceptionMeta {

    private Set<String> schemaHandlers;

    private Set<SchemaExceptionMeta> metas;

    public Set<String> getSchemaHandlers() {
        return schemaHandlers;
    }

    public void setSchemaHandlers(Set<String> schemaHandlers) {
        this.schemaHandlers = schemaHandlers;
    }

    public Set<SchemaExceptionMeta> getMetas() {
        return metas;
    }

    public void setMetas(Set<SchemaExceptionMeta> metas) {
        this.metas = metas;
    }
}
