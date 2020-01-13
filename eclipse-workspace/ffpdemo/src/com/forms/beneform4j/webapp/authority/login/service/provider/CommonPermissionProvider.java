package com.forms.beneform4j.webapp.authority.login.service.provider;

import java.util.Collection;
import java.util.Locale;

import javax.annotation.Resource;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.access.provider.impl.PermissionProviderSupport;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.common.AppConfig;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 权限提供<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
public class CommonPermissionProvider extends PermissionProviderSupport {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IMenuPermission> getMenuPermissions(IUser user, IAuthenticationInfo info) {
        String locale = user.getParamService().get(AppConfig.getLocaleConfigName());
        if (CoreUtils.isBlank(locale)) {
            locale = Locale.getDefault().toString();
        }
        return dao.dGetAddOnMenu(locale);
    }

}
