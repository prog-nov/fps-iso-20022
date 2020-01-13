package com.forms.beneform4j.web.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import com.forms.beneform4j.core.util.aop.AopHelp;
import com.forms.beneform4j.core.util.aop.IAopInterceptor;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.track.Tracker;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.exception.WebExceptionCodes;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Web控制层AOP<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class ControllerAspect {

    /**
     * Web控制层AOP
     * <p>
     * <ul>
     * <li>1.打印Web控制层日志
     * <li>2.捕获Web控制层异常，包装成平台Web控制层异常并抛出
     * <li>3.处理JsonBody注解族
     * </ul>
     * </p>
     * 
     * @param point 节点
     * @return 原方法返回值
     */
    public Object doAspect(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        boolean hasTracking = Tracker.isTracking();
        Logger logger = AopHelp.getLogger(point);
        Map<String, Object> context = new HashMap<String, Object>();
        Method method = AopHelp.getPointMethod(point);
        Object target = point.getTarget();
        Object[] args = point.getArgs();
        List<IAopInterceptor> aopInterceptors = WebBeneform4jConfig.getControllerAopInterceptors();
        try {
            if (!hasTracking) {
                Tracker.start();
            }
            CommonLogger.debug("enter into the class layer: Controller, call the method: " + point.getSignature(), null, logger);
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.before(context, target, method, args)) {
                        return null;
                    }
                }
            }
            Object rs = point.proceed();
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.after(context, target, method, args, rs)) {
                        break;
                    }
                }
            }
            CommonLogger.debug("the Controller method has executed success in " + (System.currentTimeMillis() - start) + " ms, exit form method: " + point.getSignature(), null, logger);
            return rs;
        } catch (Throwable e) {
            CommonLogger.error("the Controller method has occured exception, execute failure and exit after " + (System.currentTimeMillis() - start) + " ms, method: " + point.getSignature(), e, logger);
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.afterException(context, target, method, args, e)) {
                        break;
                    }
                }
            }
            throw Throw.createRuntimeException(WebExceptionCodes.BF060000, e);
        } finally {
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.afterReturn(context, target, method, args)) {
                        break;
                    }
                }
            }
            if (!hasTracking) {
                Tracker.stop();
            }
        }
    }
}
