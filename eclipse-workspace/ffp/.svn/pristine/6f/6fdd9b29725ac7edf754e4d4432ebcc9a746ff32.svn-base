package com.forms.beneform4j.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.ResponseBody;

import com.forms.beneform4j.util.json.serial.wrapper.IJsonWrapper;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : JsonBody注解<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-17<br>
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface JsonBody {

    /**
     * JsonWrapper对象的beanId
     * 
     * @return
     */
    String value() default "";

    /**
     * 属性组配置
     * 
     * <pre>
     *  <ul>
     *    属性筛选模式：根据有没有明确指定需要序列化的属性分成如下两种
     *   <li>1.否定模式：没有指定任意一个需要序列化的属性，就进入否定模式，也即只要没有忽略，就输出
     *   <li>2.肯定模式：只要有一个明确指定需要序列化的属性，就进入肯定模式，也即只有被明确指定需要序列化的属性才会输出
     *   <li>属性别名的配置不影响属性筛选模式，但会隐式表示需要输出该属性
     *  </ul>
     * 	<ul>
     *    配置方式：同一属性多次配置以前面的为准
     *   <li>1.property或property#1或property#true 配置属性名称
     *   <li>2.property#false或property#0 表示忽略属性
     *   <li>3.property#alias 表示将属性property使用别名alias输出，隐式表示是需要输出，但不影响属性筛选模式
     *  </ul>
     * </pre>
     * 
     * @return
     */
    String[] fields() default {};

    /**
     * 使用@JsonField注解配置属性组，优先级整体比fields要高，但同一属性多次配置以前面的为准
     * 
     * @return
     */
    JsonField[] jsonFields() default {};

    /**
     * JsonWrapper对象的Class，优先级比beanId要低
     * 
     * @return
     */
    Class<? extends IJsonWrapper> wrapperClass() default DefaultJsonWrapper.class;

    public class DefaultJsonWrapper implements IJsonWrapper {
        @Override
        public Object wrap(Object original) {
            throw new UnsupportedOperationException("DefaultJsonWrapper only used to marked the default value");
        }
    }
}
