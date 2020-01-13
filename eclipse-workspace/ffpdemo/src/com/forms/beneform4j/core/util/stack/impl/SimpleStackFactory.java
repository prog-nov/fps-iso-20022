package com.forms.beneform4j.core.util.stack.impl;

import org.slf4j.Logger;

import com.forms.beneform4j.core.util.stack.IStack;
import com.forms.beneform4j.core.util.stack.IStackFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 产生堆栈信息的工厂类接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class SimpleStackFactory implements IStackFactory {

    /**
     * 根据简单提示信息、参数和异常产生堆栈信息
     * 
     * @param logger 日志类
     * @param stack 调用堆栈
     * @param message 日志信息
     * @param throwable 异常
     * @return 日志堆栈对象
     */
    public IStack getStack(Logger logger, StackTraceElement stack, String message, Throwable throwable) {
        SimpleStack ss = new SimpleStack(logger, stack, message, throwable);
        return ss;
    }
}
