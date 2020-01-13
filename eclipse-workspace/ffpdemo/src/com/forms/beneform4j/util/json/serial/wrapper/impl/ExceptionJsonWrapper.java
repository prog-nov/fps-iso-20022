package com.forms.beneform4j.util.json.serial.wrapper.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.validation.BindException;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.exception.Beneform4jRuntimeException;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.util.json.serial.wrapper.IAjaxBindExceptionHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台异常JSON包装器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-18<br>
 */
public class ExceptionJsonWrapper extends MapJsonWrapper {

    private final IAjaxBindExceptionHandler defaultIAjaxBindExceptionHandler = new DefaultAjaxBindExceptionHandler();

    @Override
    protected boolean addOriginalData() {
        return false;
    }

    @Override
    protected boolean getStatus(Object original) {
        return false;
    }

    @Override
    protected void doWrap(Map<String, Object> wrapper, Object original) {
        if (original instanceof BindException) {
            Map<String, Object> ex = getExceptionInfoMap(wrapper);
            IAjaxBindExceptionHandler b = getAjaxBindExceptionHandler();
            b.handler(ex, (BindException) original);
        } else if (original instanceof Throwable) {
            Beneform4jRuntimeException be = Throw.createRuntimeException((Throwable) original);
            Map<String, Object> ex = getExceptionInfoMap(wrapper);
            ex.put("code", be.getCode());
            ex.put("trackId", be.getTrackId());
            ex.put("level", be.getLevel());
            ex.put("message", be.getShortMessage());
            ex.put("detail", be.getMessage());
        }
    }

    private IAjaxBindExceptionHandler getAjaxBindExceptionHandler() {
        try {
            IAjaxBindExceptionHandler d = SpringHelp.getBean(IAjaxBindExceptionHandler.class);
            return null == d ? defaultIAjaxBindExceptionHandler : d;
        } catch (Exception e) {
            return defaultIAjaxBindExceptionHandler;
        }
    }

    private Map<String, Object> getExceptionInfoMap(Map<String, Object> wrapper) {
        Map<String, Object> ex = new LinkedHashMap<String, Object>();
        wrapper.put(getDataJsonPropertyName(), ex);
        return ex;
    }
}
