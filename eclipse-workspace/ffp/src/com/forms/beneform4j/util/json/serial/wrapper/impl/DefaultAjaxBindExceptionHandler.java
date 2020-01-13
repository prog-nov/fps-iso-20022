package com.forms.beneform4j.util.json.serial.wrapper.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.forms.beneform4j.util.json.serial.wrapper.IAjaxBindExceptionHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ajax请求中的数据绑定错误处理器默认实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
public class DefaultAjaxBindExceptionHandler implements IAjaxBindExceptionHandler {

    @Override
    public void handler(Map<String, Object> context, BindException exception) {
        BindingResult result = exception.getBindingResult();
        List<Map<String, String>> msgs = new ArrayList<Map<String, String>>();
        if (result.hasFieldErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", error.getField());
                map.put("type", "field");
                map.put("message", error.getDefaultMessage());
                msgs.add(map);
            }
        }
        if (result.hasGlobalErrors()) {
            for (ObjectError error : result.getGlobalErrors()) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", error.getObjectName());
                map.put("type", "global");
                map.put("message", error.getDefaultMessage());
                msgs.add(map);
            }
        }
        context.put("errors", msgs);
    }
}
