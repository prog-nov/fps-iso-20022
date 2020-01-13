package com.forms.beneform4j.core.service.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import com.forms.beneform4j.core.Beneform4jConfig;
import com.forms.beneform4j.core.util.aop.AopHelp;
import com.forms.beneform4j.core.util.aop.IAopInterceptor;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.core.util.track.Tracker;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 服务层AOP<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-24<br>
 */
public class ServiceAspect {

    /**
     * 服务层AOP
     * <p>
     * <ul>
     * <li>1.打印服务层日志
     * <li>2.捕获服务层异常，包装成平台服务层异常并抛出
     * </ul>
     * </p>
     * 
     * @param point 节点
     * @return 原方法返回值
     */
    public Object doAspect(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        boolean hasTracking = Tracker.isTracking();
        Map<String, Object> context = new HashMap<String, Object>();
        Logger logger = AopHelp.getLogger(point);
        Object target = point.getTarget();
        Method method = AopHelp.getPointMethod(point);
        Object[] args = point.getArgs();
        List<IAopInterceptor> aopInterceptors = Beneform4jConfig.getServiceAopInterceptors();
        try {
            if (!hasTracking) {
                Tracker.start();
            }
            CommonLogger.debug("enter into the class layer: Service, call the method: " + point.getSignature(), null, logger);
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.before(context, target, method, args)) {
                        return null;
                    }
                }
            }
            Object rs = point.proceed();
            CommonLogger.debug("the Service method has executed success in " + (System.currentTimeMillis() - start) + " ms, exit form method: " + point.getSignature(), null, logger);
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.after(context, target, method, args, rs)) {
                        break;
                    }
                }
            }
            return rs;
        } catch (Throwable e) {
            CommonLogger.error("the Service method has occured exception, execute failure and exit after " + (System.currentTimeMillis() - start) + " ms, method: " + point.getSignature(), e, logger);
            if (null != aopInterceptors && !aopInterceptors.isEmpty()) {
                for (IAopInterceptor sai : aopInterceptors) {
                    if (!sai.afterException(context, target, method, args, e)) {
                        break;
                    }
                }
            }
            // IExceptionMeta meta = Throw.lookupExceptionMeta(null, e);
            throw Throw.createRuntimeException(e);
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
