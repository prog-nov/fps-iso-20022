package com.forms.beneform4j.core.service.request.impl;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.service.request.IRequestInfoFactory;
import com.forms.beneform4j.core.service.request.RequestInfoContext;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 使用请求上下文创建请求信息的工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-21<br>
 */
public class BaseRequestInfoFactory implements IRequestInfoFactory {

    /**
     * 创建请求信息
     */
    @Override
    public IRequestInfo getRequestInfo() {
        return RequestInfoContext.getRequestInfo();
    }

}
