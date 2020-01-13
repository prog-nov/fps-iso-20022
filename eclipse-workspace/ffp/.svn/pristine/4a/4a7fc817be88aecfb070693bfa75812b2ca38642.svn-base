package com.forms.beneform4j.webapp.authority.login.service.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.access.permission.IPermissionManager;
import com.forms.beneform4j.security.core.access.permission.impl.PermissionManager;
import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 国际化拦截器 <br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
public class UserLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfo loginUser = (UserInfo) request.getSession().getAttribute(LoginConst.LOGIN_USER);
        if (null != loginUser && loginUser.getPermissionManager() != null) {
            String locale = loginUser.getParamService().get("USER_LOCALE");
            List<IMenuPermission> dGetAddOnMenu = dao.dGetAddOnMenu(locale);
            List<IMenuPermission> dGetMenuNodes = dao.dGetMenuNodes(loginUser, ParamHolder.getParameter("USER_ROLE_MODE"), locale);
            List<IMenuPermission> list = new ArrayList<IMenuPermission>();
            list.addAll(dGetMenuNodes);
            list.addAll(dGetAddOnMenu);
            IPermissionManager permissionManager = loginUser.getPermissionManager();
            if (permissionManager instanceof PermissionManager) {
                PermissionManager userPermissionManager = (PermissionManager) loginUser.getPermissionManager();
                userPermissionManager.rerfeshMenuTree(list);
            }
            if(WebUtils.getSession() != null)
            	WebUtils.getSession().setAttribute(LoginConst.SESSION_CHANGE_LOCAL_NO_LOGOUT, Boolean.TRUE);
        }
    }

}
