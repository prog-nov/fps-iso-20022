package com.forms.beneform4j.core.util.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : AOP帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-22<br>
 */
public class AopHelp {

    /**
     * 获取与切点对应的日志类
     * 
     * @param point 切点
     * @return 切点日志类
     */
    public static Logger getLogger(JoinPoint point) {
        Signature signature = point.getSignature();
        String className = signature.getDeclaringTypeName();
        Logger logger = LoggerFactory.getLogger(className);
        return logger;
    }

    /**
     * 获取与切点对应的堆栈
     * 
     * @param point 切点
     * @return 切点堆栈
     */
    public static StackTraceElement getStackTraceElement(JoinPoint point) {
        Signature signature = point.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        StackTraceElement stack = null;
        try {// 这里由于Spring根本没有实现SourceLocation接口，获取文件名称会抛出异常
            SourceLocation source = point.getSourceLocation();
            stack = new StackTraceElement(className, methodName, source.getFileName(), source.getLine());
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (null != stackTrace) {
                for (StackTraceElement st : stackTrace) {
                    if (st.getClassName().startsWith(className) && methodName.equals(st.getMethodName())) {
                        stack = st;
                        break;
                    }
                }
            }
        }
        return stack;
    }

    /**
     * 获取切点目标方法
     * 
     * @param point 切点
     * @return 切点的目标方法
     */
    public static Method getPointMethod(JoinPoint point) {
        Signature signature = point.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature m = (MethodSignature) signature;
            return m.getMethod();
        }
        return null;
    }
}
