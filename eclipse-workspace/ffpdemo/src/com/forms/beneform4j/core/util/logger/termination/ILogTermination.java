package com.forms.beneform4j.core.util.logger.termination;

import com.forms.beneform4j.core.util.logger.level.LogLevel;
import com.forms.beneform4j.core.util.stack.IStack;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 日志终端<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface ILogTermination {

    /**
     * 根据日志级别写日志
     * 
     * @param level 日志级别
     * @param stack 日志堆栈信息
     */
    public void write(LogLevel level, IStack stack);
}
