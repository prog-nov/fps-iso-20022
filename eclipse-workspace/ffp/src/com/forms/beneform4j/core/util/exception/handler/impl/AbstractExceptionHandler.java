package com.forms.beneform4j.core.util.exception.handler.impl;

import com.forms.beneform4j.core.util.exception.Beneform4jRuntimeException;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的异常处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public abstract class AbstractExceptionHandler implements IExceptionHandler {

    /**
     * 是否忽略处理过程中的异常，默认为忽略
     */
    private boolean ignoreHandlerException = true;

    @Override
    public boolean ignoreHandlerException() {
        return ignoreHandlerException;
    }

    public void setIgnoreHandlerException(boolean ignoreHandlerException) {
        this.ignoreHandlerException = ignoreHandlerException;
    }

    /**
     * 先转换为平台运行时异常，再进行处理
     * 
     * @param throwable 异常
     * @return 处理后的结果
     */
    @Override
    public Object handler(Throwable throwable) {
        Beneform4jRuntimeException exception = Throw.createRuntimeException(throwable);
        return this.handlerBeneform4jRuntimeException(exception);
    }

    /**
     * 处理平台运行时异常
     * 
     * @param exception 平台异常
     * @return 处理后的结果
     */
    protected abstract Object handlerBeneform4jRuntimeException(Beneform4jRuntimeException exception);
}
