package com.forms.beneform4j.core.util.exception.meta.impl;

import java.util.List;

import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.level.ExceptionLevel;
import com.forms.beneform4j.core.util.exception.meta.IExceptionMeta;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常配置元信息<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class ExceptionMeta implements IExceptionMeta {

    /**
     * 父代码
     */
    private String parentCode;

    /**
     * 异常代码
     */
    private String code;

    /**
     * 国际化描述信息的Key值
     */
    private String messageKey;

    /**
     * 获取异常级别
     */
    private ExceptionLevel level;

    /**
     * 异常返回逻辑视图
     */
    private String view;

    /**
     * 异常描述，只给开发人员使用
     */
    private String description;

    /**
     * 异常处理器列表
     */
    private List<IExceptionHandler> handlers;

    /**
     * 获取导致该异常的原始异常列表
     */
    private List<Class<? extends Throwable>> causes;

    @Override
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public ExceptionLevel getLevel() {
        return level;
    }

    public void setLevel(ExceptionLevel level) {
        this.level = level;
    }

    @Override
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<IExceptionHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<IExceptionHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public List<Class<? extends Throwable>> getCauses() {
        return causes;
    }

    public void setCauses(List<Class<? extends Throwable>> causes) {
        this.causes = causes;
    }
}
