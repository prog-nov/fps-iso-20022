package com.forms.beneform4j.util.json.serial.wrapper;

import java.util.Map;

import org.springframework.validation.BindException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Ajax请求中的数据绑定错误处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-5<br>
 */
public interface IAjaxBindExceptionHandler {

    /**
     * 处理
     * 
     * @param context Json数据的上下文环境
     * @param exception 数据绑定异常
     */
    public void handler(Map<String, Object> context, BindException exception);
}
