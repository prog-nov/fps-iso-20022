package com.forms.beneform4j.core.util.aop;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : AOP拦截器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-12<br>
 */
public interface IAopInterceptor {

    /**
     * 执行前，返回false可阻止继续执行方法本身
     * 
     * @param context 上下文环境，对于多个拦截器，可以利用上下文环境传递参数
     * @param target AOP的目标对象
     * @param method AOP的目标方法
     * @param args AOP的原参数
     * @return 是否继续执行方法本身
     */
    public boolean before(Map<String, Object> context, Object target, Method method, Object[] args);

    /**
     * 执行后，和before不同的是，返回false可阻止继续执行后面的拦截器
     * 
     * @param context 上下文环境，对于多个拦截器，可以利用上下文环境传递参数
     * @param target AOP的目标对象
     * @param method AOP的目标方法
     * @param args AOP的原参数
     * @param result AOP目标方法的原始返回值
     * @return 是否继续执行后面的拦截器
     */
    public boolean after(Map<String, Object> context, Object target, Method method, Object[] args, Object result);

    /**
     * 异常抛出后，和before不同的是，返回false可阻止继续执行后面的拦截器
     * 
     * @param context 上下文环境，对于多个拦截器，可以利用上下文环境传递参数
     * @param target AOP的目标对象
     * @param method AOP的目标方法
     * @param args AOP的原参数
     * @param throwable 调用AOP目标方法抛出的异常
     * @return 是否继续执行后面的拦截器
     */
    public boolean afterException(Map<String, Object> context, Object target, Method method, Object[] args, Throwable throwable);

    /**
     * 方法返回后，和before不同的是，返回false可阻止继续执行后面的拦截器
     * 
     * @param context 上下文环境，对于多个拦截器，可以利用上下文环境传递参数
     * @param target AOP的目标对象
     * @param method AOP的目标方法
     * @param args AOP的原参数
     * @return 是否继续执行后面的拦截器
     */
    public boolean afterReturn(Map<String, Object> context, Object target, Method method, Object[] args);
}
