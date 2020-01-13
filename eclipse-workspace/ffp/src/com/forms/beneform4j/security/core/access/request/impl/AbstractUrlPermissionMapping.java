package com.forms.beneform4j.security.core.access.request.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.access.request.IRequestInfoPermissionMapping;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 抽象的根据请求URL查找的请求权限映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public abstract class AbstractUrlPermissionMapping implements IRequestInfoPermissionMapping {

    private static final Map<String, List<InnerUrlPermission>> mapping = new HashMap<String, List<InnerUrlPermission>>();

    /**
     * 根据请求url查找可能的权限列表
     * 
     * @param url 请求URL
     * @return 权限列表
     */
    protected abstract List<IPermission> getPermissions(String url);

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

    /**
     * 查找和请求对应的权限
     */
    @Override
    public IPermission lookup(IRequestInfo requestInfo) {
        List<InnerUrlPermission> auth = getUrlPermissions(requestInfo);
        if (null == auth || auth.isEmpty()) {
            return null;
        } else if (1 == auth.size()) {
            InnerUrlPermission a = auth.get(0);
            if (null != a.args && 0 != a.args.length) {
                Map<String, ?> parameters = requestInfo.getParameters();
                if (null == parameters || parameters.isEmpty()) {
                    return null;
                }
                for (int i = 0, l = a.args.length; i < l; i++) {
                    if (null != a.vals[i] && !"".equals(a.vals[i]) && !a.vals[i].equals(getParameter(parameters, a.args[i]))) {
                        return null;// 只要有一个参数不相等，就返回空
                    }
                }
            }
            return a.permission;
        } else {// 同一个URL找到多个菜单，返回匹配次数最多的菜单
            Map<String, ?> parameters = requestInfo.getParameters();
            InnerUrlPermission ma = null;
            outer: for (InnerUrlPermission a : auth) {
                if (null != a.args && a.args.length >= 1) {// 有额外参数
                    for (int i = 0, l = a.args.length; i < l; i++) {
                        if (CoreUtils.isBlank(a.vals[i])) {// 额外参数没有值
                            if (parameters.containsKey(a.args[i])) {// 只要传入同名参数即可
                                a.match++;// 匹配次数加1
                            } else {
                                continue outer;// 否则就不匹配
                            }
                        } else {// 额外参数有值
                            if (a.vals[i].equals(getParameter(parameters, a.args[i]))) {
                                a.match++;// 匹配次数加1
                            } else {
                                continue outer;// 只要有一个参数不相等，就跳过
                            }
                        }
                    }
                }
                if (null == ma || a.match > ma.match) {
                    ma = a;
                }
            }
            for (InnerUrlPermission a : auth) {
                a.match = 0;
            }
            return ma == null ? null : ma.permission;
        }
    }

    /**
     * 获取和请求对应的内部URL权限列表
     * 
     * @param requestInfo
     * @return URL权限列表
     */
    private List<InnerUrlPermission> getUrlPermissions(IRequestInfo requestInfo) {
        synchronized (mapping) {
            String url = requestInfo.getRequestUrl().trim();
            int i1 = url.indexOf('.'), i2 = url.indexOf('?');
            if (-1 != i1) {
                url = url.substring(0, i1);
            } else if (-1 != i2) {
                url = url.substring(0, i2);
            }
            List<InnerUrlPermission> authList = mapping.get(url);
            if (null == authList) {
                List<IPermission> permissions = getPermissions(url);
                if (null == permissions || permissions.isEmpty()) {
                    authList = Collections.emptyList();
                } else {
                    authList = convertAuthList(permissions);
                }
                mapping.put(url, authList);
            }
            return authList;
        }
    }

    /**
     * 转换为内部URL权限
     * 
     * @param permissions 权限
     * @return 内部URL权限列表
     */
    private List<InnerUrlPermission> convertAuthList(List<IPermission> permissions) {
        if (null == permissions) {
            return null;
        } else {
            List<InnerUrlPermission> authList = new ArrayList<InnerUrlPermission>();
            for (IPermission permission : permissions) {
                authList.add(new InnerUrlPermission(permission));
            }
            return authList;
        }
    }

    /**
     * 内部URL权限类
     */
    private static class InnerUrlPermission {
        private IPermission permission;
        private String[] args;
        private String[] vals;
        private int match = 0;

        private InnerUrlPermission(IPermission permission) {
            this.permission = permission;
            String url = permission.getPermUrl();
            if (!CoreUtils.isBlank(url)) {
                int index = url.indexOf('?');
                if (-1 != index) {
                    try {
                        String[] params = url.substring(index + 1).split("[&]");
                        int length = params.length;
                        this.args = new String[length];
                        this.vals = new String[length];
                        for (int i = 0; i < length; i++) {
                            if (null != params[i] && !"".equals(params[i])) {
                                String[] p = params[i].split("=");
                                this.args[i] = p[0];
                                this.vals[i] = p.length >= 2 ? p[1] : null;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
