package com.forms.beneform4j.core.util.exception.handler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常处理接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IExceptionHandler {

    /**
     * 是否忽略处理器处理过程中的异常
     * 
     * @return 是否忽略处理器处理过程中的异常
     */
    public boolean ignoreHandlerException();

    /**
     * 处理异常
     * 
     * @param throwable
     * @return 处理后的结果
     */
    public Object handler(Throwable throwable);
}
