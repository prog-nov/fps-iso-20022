package com.forms.beneform4j.security.core.access.request.impl;

import java.util.Map;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.access.request.IRequestInfoPermissionMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的根据参数查找的请求权限映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class AbstractParamPermissionMapping implements IRequestInfoPermissionMapping {

    private String paramName;

    @Override
    public IPermission lookup(IRequestInfo requestInfo) {
        String paramName = getParamName();
        Map<String, ?> parameters = requestInfo.getParameters();
        String param = this.getParameter(parameters, paramName);
        return getPermission(param);
    }

    /**
     * 根据请求参数获取权限
     * 
     * @param param 请求参数
     * @return 权限信息
     */
    protected abstract IPermission getPermission(String param);

    protected String getParameter(Map<String, ?> parameters, String name) {
        if (parameters.containsKey(name)) {
            Object p = parameters.get(name);
            if (null == p) {
                return null;
            } else if (p.getClass().isArray()) {
                Object[] arr = (Object[]) p;
                if (arr.length >= 1) {
                    return arr[0].toString();
                } else {
                    return null;
                }
            } else {
                return p.toString();
            }
        }
        return null;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
