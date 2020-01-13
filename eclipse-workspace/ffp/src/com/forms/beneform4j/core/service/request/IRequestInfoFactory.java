package com.forms.beneform4j.core.service.request;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求信息工厂接口<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public interface IRequestInfoFactory {

    /**
     * 从请求上下文环境中获取请求对象
     * 
     * @return 请求信息
     */
    public IRequestInfo getRequestInfo();
}
