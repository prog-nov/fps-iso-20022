package com.forms.beneform4j.core.service.spring.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.forms.beneform4j.core.service.exception.ServiceExceptionCodes;
import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.service.spring.schema.exception.parser.SchemaExceptionMeta;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.meta.impl.XmlExceptionMetaLoader;
import com.forms.beneform4j.core.util.init.Init;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用自定义Spring命名空间的异常元信息加载器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
@Init(order = Integer.MIN_VALUE / 2)
public class SpringExceptionMetaLoader extends XmlExceptionMetaLoader {

    /**
     * 初始化
     */
    public void init() {
        super.load();
        Map<String, SchemaExceptionMeta> metas = SpringHelp.getBeansOfType(SchemaExceptionMeta.class);
        if (null != metas && !metas.isEmpty()) {
            for (SchemaExceptionMeta meta : metas.values()) {
                resolverExceptionMeta(meta);
            }
        }
    }

    /**
     * 解析异常元信息
     * 
     * @param meta 异常配置元信息
     */
    private void resolverExceptionMeta(SchemaExceptionMeta meta) {
        if (null != meta) {
            String code = meta.getCode();
            Set<SchemaExceptionMeta> metas = meta.getMetas();
            if (CoreUtils.isBlank(code)) {
                if (null == metas || metas.isEmpty()) {// 没有code，同时没有子配置
                    Throw.throwRuntimeException(ServiceExceptionCodes.BF030003);
                } else {
                    code = generateExceptionCode();
                }
            } else if (codeMetaMapping.containsKey(code)) {
                Throw.throwRuntimeException(ServiceExceptionCodes.BF030004, code);
            } else if (!exceptionCodePatter.matcher(code).find()) {
                Throw.throwRuntimeException(ServiceExceptionCodes.BF030005, code);
            }
            meta.setHandlers(resolverExceptionHandlers(meta.getSchemaHandlers()));
            codeMetaMapping.put(code, meta);
            if (null != meta.getCauses() && !meta.getCauses().isEmpty()) {
                for (Class<?> cls : meta.getCauses()) {
                    classMetaMapping.put(cls, meta);
                }
            }
            logExceptionMeta(meta);
            if (null != metas) {
                for (SchemaExceptionMeta m : metas) {
                    m.setParentCode(code);
                    resolverExceptionMeta(m);
                }
            }
        }
    }

    /**
     * 解析异常处理器
     * 
     * @param handlers 处理器配置列表
     * @return 处理器实例列表
     */
    private List<IExceptionHandler> resolverExceptionHandlers(Set<String> handlers) {
        List<IExceptionHandler> exceptionHandlers = null;
        if (null != handlers && handlers.size() > 0) {
            exceptionHandlers = new ArrayList<IExceptionHandler>();
            for (String handler : handlers) {
                IExceptionHandler h = SpringHelp.getBean(handler, IExceptionHandler.class);
                exceptionHandlers.add(h);
            }
            return exceptionHandlers;
        }
        return null;
    }
}
