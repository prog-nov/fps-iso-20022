package com.forms.beneform4j.webapp.authority.access.service.request;

import java.util.List;

import javax.annotation.Resource;

import com.forms.beneform4j.security.core.access.permission.IPermission;
import com.forms.beneform4j.security.core.access.request.impl.AbstractUrlPermissionMapping;
import com.forms.beneform4j.webapp.authority.access.dao.IAccessControlDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 请求权限映射实现类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public class UrlRequestPermissionMapping extends AbstractUrlPermissionMapping {

    @Resource(name = "accessControlDao")
    private IAccessControlDao dao;

    /**
     * 根据请求URL获取所有可能的权限
     */
    @Override
    protected List<IPermission> getPermissions(String url) {
        return dao.getPermissions(url);
    }

}
