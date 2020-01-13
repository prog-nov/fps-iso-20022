package com.forms.beneform4j.core.util.exception.meta.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMeta;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMetaLoader;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的异常元信息加载器实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class AbstractExceptionMetaLoader implements IExceptionMetaLoader {

    /**
     * 异常代码校验正则表达式
     */
    protected static final Pattern exceptionCodePatter = Pattern.compile("^[A-Z]{2}[0-9A-Za-z]{6}$");

    /**
     * 根据异常代码的缓存，所有子类共享
     */
    protected static final Map<String, IExceptionMeta> codeMetaMapping = new HashMap<String, IExceptionMeta>();

    /**
     * 根据异常类型的缓存，所有子类共享
     */
    protected static final Map<Class<?>, IExceptionMeta> classMetaMapping = new HashMap<Class<?>, IExceptionMeta>();

    /**
     * 查找异常元信息
     * <p>
     * <ul>
     * <li>1. 如果异常代码不为空，则首先根据异常代码查找元信息
     * <li>2. 如果根据异常代码获取不到，则根据异常原因查找元信息，异常原因会根据继承关系逐级网上查找
     * </ul>
     * <p>
     */
    @Override
    public IExceptionMeta lookup(String code, Throwable cause) {
        IExceptionMeta meta = null;
        if (null != code) {
            meta = codeMetaMapping.get(code);
        }
        if (null == meta && null != cause) {
            Class<?> oCls = cause.getClass();
            if (classMetaMapping.containsKey(oCls)) {
                return classMetaMapping.get(oCls);
            } else {
                Class<?> cls = oCls;
                while (!Object.class.equals(cls)) {
                    meta = classMetaMapping.get(cls);
                    if (null != meta) {
                        classMetaMapping.put(oCls, meta);
                        return meta;
                    }
                    cls = cls.getSuperclass();
                }
            }
        }
        return meta;
    }

    /**
     * 生成异常代码，只有不是底层异常配置才允许异常代码为空，如果异常代码为空，使用该方法生成
     * 
     * @return 生成的异常代码
     */
    protected String generateExceptionCode() {
        return "##" + RandomStringUtils.randomNumeric(6);
    }

    /**
     * 是否为生成的异常代码
     * 
     * @param code 异常代码
     * @return 是否为生成的异常代码
     */
    protected boolean isGenerateExceptionCode(String code) {
        return !CoreUtils.isBlank(code) && !code.startsWith("##");
    }

    /**
     * 校验异常代码
     * 
     * @param code 异常代码
     */
    protected void validateExceptionCode(String code) {
        if (codeMetaMapping.containsKey(code)) {
            throw new IllegalArgumentException("found duplicate exception code: " + code);
        } else if (!exceptionCodePatter.matcher(code).find()) {
            throw new IllegalArgumentException("the exception code is incorrect: " + code);
        }
    }

    /**
     * 记录异常加载日志
     * 
     * @param meta 异常元信息
     */
    protected void logExceptionMeta(IExceptionMeta meta) {
        StringBuffer sb = new StringBuffer();
        sb.append("{exception-meta}==>{code=").append(meta.getCode());

        if (meta instanceof ExceptionMeta) {
            String desc = ((ExceptionMeta) meta).getDescription();
            if (!CoreUtils.isBlank(desc)) {
                sb.append(",description=").append(desc);
            }
        }

        String pCode = meta.getParentCode();
        if (!this.isGenerateExceptionCode(pCode)) {
            sb.append(",parentCode=").append(pCode);
        }
        if (!CoreUtils.isBlank(meta.getMessageKey())) {
            sb.append(",messageKey=").append(meta.getMessageKey());
        }
        if (null != meta.getLevel()) {
            sb.append(",level=").append(meta.getLevel());
        }
        if (!CoreUtils.isBlank(meta.getView())) {
            sb.append(",view=").append(meta.getView());
        }
        List<Class<? extends Throwable>> causes = meta.getCauses();
        if (null != causes && !causes.isEmpty()) {
            sb.append(",causes=").append(causes);
        }
        sb.append("}");
        CommonLogger.info(sb.toString());
    }
}
