package com.forms.beneform4j.core.util.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import com.forms.beneform4j.core.util.exception.Throw.Beneform4jExceptionInnerProxy;
import com.forms.beneform4j.core.util.exception.handler.IExceptionHandler;
import com.forms.beneform4j.core.util.exception.level.ExceptionLevel;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 平台受检异常类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
final public class Beneform4jException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -2159369710297472601L;

    private final Beneform4jExceptionInnerProxy proxy;

    /* package */ Beneform4jException(Beneform4jRuntimeException exception) {
        this(exception.getProxy());
    }

    /* package */ Beneform4jException(Beneform4jExceptionInnerProxy proxy) {
        super(proxy.getCause());
        this.proxy = proxy;
    }

    /**
     * 获取跟踪ID
     * 
     * @return 跟踪ID
     */
    public String getTrackId() {
        return this.proxy.getTrackId();
    }

    /**
     * 获取异常父代码
     * 
     * @return 异常父代码
     */
    public String getParentCode() {
        return this.proxy.getParentCode();
    }

    /**
     * 获取异常代码
     * 
     * @return 异常代码
     */
    public String getCode() {
        return this.proxy.getCode();
    }

    /**
     * 获取异常逻辑视图
     * 
     * @return 异常逻辑视图
     */
    public String getView() {
        return this.proxy.getView();
    }

    /**
     * 获取异常级别
     * 
     * @return 异常级别
     */
    public ExceptionLevel getLevel() {
        return this.proxy.getLevel();
    }

    /**
     * 获取异常简短描述
     * 
     * @return 异常简短描述
     */
    public String getShortMessage() {
        return this.proxy.getMessage();
    }

    /**
     * 获取异常描述
     * 
     * @return 异常描述
     */
    @Override
    public String getMessage() {
        return Throw.getMessage(this);
    }

    /**
     * 获取异常详细信息描述
     * 
     * @return 异常详细信息描述
     */
    public String getStackMessage() {
        return Throw.getStackMessage(this);
    }

    /**
     * 获取异常处理器列表
     * 
     * @return 异常处理器列表
     */
    public List<IExceptionHandler> getHandlers() {
        return this.proxy.getHandlers();
    }

    /**
     * 打印异常
     */
    @Override
    public void printStackTrace(PrintStream s) {
        s.print(Throw.getStackMessage(this));
        // super.printStackTrace(s);
    }

    /**
     * 打印异常
     */
    @Override
    public void printStackTrace(PrintWriter s) {
        s.print(Throw.getStackMessage(this));
        // super.printStackTrace(s);
    }

    /**
     * 获取代理
     */
    /* package */ Beneform4jExceptionInnerProxy getProxy() {
        return proxy;
    }
}
