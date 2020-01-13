package com.forms.beneform4j.core.util.exception.handler.impl;

import com.forms.beneform4j.core.util.exception.Beneform4jRuntimeException;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 返回明细信息的异常处理器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DetailExceptionHandler extends AbstractExceptionHandler {

    private boolean writeErrorLogger = true;

    /**
     * 处理异常，返回详细信息
     */
    @Override
    protected Object handlerBeneform4jRuntimeException(Beneform4jRuntimeException exception) {
        String msg = Throw.getMessage(exception);
        if (isWriteErrorLogger()) {
            CommonLogger.error(exception);
        }
        return msg;
    }

    public boolean isWriteErrorLogger() {
        return writeErrorLogger;
    }

    public void setWriteErrorLogger(boolean writeErrorLogger) {
        this.writeErrorLogger = writeErrorLogger;
    }

}
