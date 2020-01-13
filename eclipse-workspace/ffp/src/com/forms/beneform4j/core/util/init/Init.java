package com.forms.beneform4j.core.util.init;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description :
 * 初始化注解，该注解所标示的初始化类不支持集群，如果需要支持集群，需要在初始化类内部实现；整个初始化类先根据order排序，再根据依赖重新排序，依赖排序优先级更高<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {

    /**
     * 初始化方法，默认为init()方法
     * 
     * @return 初始化方法名称，默认init
     */
    String init() default "init";

    /**
     * 销毁方法，默认为destory()方法
     * 
     * @return 销毁方法名称，默认destory
     */
    String destory() default "destory";

    /**
     * 忽略执行过程中的异常
     * 
     * @return 是否忽略执行过程中的异常，默认不忽略
     */
    boolean ignoreRuntimeException() default false;

    /**
     * 执行顺序
     * 
     * @return 执行顺序，数值越小，优先级越高
     */
    int order() default 0;

    /**
     * 前置依赖
     * 
     * @return 前置依赖类数组，所有的依赖类必须本身也包括@Init注解，否则该依赖会被忽略
     */
    Class<?>[] depends() default {};
}
