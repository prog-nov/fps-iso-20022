package com.forms.beneform4j.core.util.exception;

import java.lang.reflect.InvocationTargetException;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 异常工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class ThrowUtils extends Throw {

    /**
     * 抛出平台受检异常
     * 
     * @param code 异常代码
     * @param args 国际化信息中的占位符参数
     * @throws Beneform4jException 平台受检异常
     */
    public static void throwException(String code, Object... args) throws Beneform4jException {
        throw createException(code, null, args);
    }

    /**
     * 抛出平台受检异常
     * 
     * @param e 原始异常
     * @throws Beneform4jException 平台受检异常
     */
    public static void throwException(Throwable e) throws Beneform4jException {
        throw createException(null, e);
    }

    /**
     * 抛出平台受检异常
     * 
     * @param code 异常代码
     * @param e 原始异常
     * @param args 国际化信息中的占位符参数
     * @throws Beneform4jException 平台受检异常
     */
    public static void throwException(String code, Throwable e, Object... args) throws Beneform4jException {
        throw createException(code, e, args);
    }

    /**
     * 创建平台受检异常
     * 
     * @param code 异常代码
     * @param args 国际化信息中的占位符参数
     * @return 平台受检异常，只返回，不抛出
     */
    public static Beneform4jException createException(String code, Object... args) {
        return createException(code, null, args);
    }

    /**
     * 创建平台受检异常
     * 
     * @param e 原始异常
     * @return 平台受检异常，只返回，不抛出
     */
    public static Beneform4jException createException(Throwable e) {
        return createException(null, e);
    }

    /**
     * 创建平台受检异常
     * <p>
     * <ul>
     * <li>如果原始异常为{@link Beneform4jException}直接返回
     * <li>如果原始异常为{@link Beneform4jRuntimeException}，内部转换为Beneform4jException再返回
     * <li>如果原始异常为{@link InvocationTargetException}，将原始异常设置为getTargetException()，再包装为平台受检异常返回
     * <li>其它情况，将原始异常包装为平台受检异常返回
     * </ul>
     * </p>
     * 
     * @param code 异常代码
     * @param e 原始异常
     * @param args 国际化信息中的占位符参数
     * @return 平台受检异常，只返回，不抛出
     */
    public static Beneform4jException createException(String code, Throwable e, Object... args) {
        if (e instanceof Beneform4jException) {
            return (Beneform4jException) e;
        } else if (e instanceof Beneform4jRuntimeException) {
            return new Beneform4jException((Beneform4jRuntimeException) e);
        } else if (e instanceof InvocationTargetException) {
            return createException(code, ((InvocationTargetException) e).getTargetException(), args);
        } else {
            Beneform4jExceptionInnerProxy proxy = new Beneform4jExceptionInnerProxy(code, e, args);
            return new Beneform4jException(proxy);
        }
    }
}
