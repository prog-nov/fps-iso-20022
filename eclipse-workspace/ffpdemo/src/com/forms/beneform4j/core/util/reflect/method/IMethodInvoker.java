package com.forms.beneform4j.core.util.reflect.method;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 方法调用器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-27<br>
 */
public interface IMethodInvoker {

    /**
     * 调用方法
     * 
     * @param param 方法参数
     * @return 方法返回值
     */
    public Object invoke(Object param);

    /**
     * 调用方法
     * 
     * @param param 方法参数
     * @param extractor 参数提取器
     * @return 方法返回值
     */
    public Object invoke(Object param, IParamExtractor extractor);
}
