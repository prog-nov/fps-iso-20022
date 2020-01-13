package com.forms.beneform4j.core.dao.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;

import com.forms.beneform4j.core.dao.exception.DaoExceptionCodes;
import com.forms.beneform4j.core.util.aop.AopHelp;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 数据访问层AOP<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class DaoAspect {

    /**
     * 数据访问层AOP
     * <p>
     * <ul>
     * <li>1.打印数据访问日志
     * <li>2.捕获数据访问异常，包装成平台数据访问层异常并抛出
     * </ul>
     * </p>
     * 
     * @param point 节点
     * @return 原方法返回值
     */
    public Object doAspect(ProceedingJoinPoint point) {
        long start = System.currentTimeMillis();
        Logger logger = AopHelp.getLogger(point);
        try {
            CommonLogger.debug("enter into the class layer: DAO, call the method: " + point.getSignature(), null, logger);
            Object rs = point.proceed();
            CommonLogger.debug("the DAO method has executed success in " + (System.currentTimeMillis() - start) + " ms, exit form method: " + point.getSignature(), null, logger);
            return rs;
        } catch (Throwable e) {
            CommonLogger.error("the DAO method has occured exception, execute failure and exit after " + (System.currentTimeMillis() - start) + " ms, method: " + point.getSignature(), e, logger);
            throw Throw.createRuntimeException(DaoExceptionCodes.BF020000, e);
        }
    }
}
