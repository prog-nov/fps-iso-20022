package com.forms.beneform4j.security.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.authz.IAuthorizer;
import com.forms.beneform4j.security.core.access.info.IAuthorizationInfo;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.servlet.filter.AbstractSkipPathMatcherFilter;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 安全过滤器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-8<br>
 */
public class SecurityFilter extends AbstractSkipPathMatcherFilter {

    public static final String AUTHORIZATION_INFO_ATTRIBUTE_NAME = SecurityFilter.class.getName() + ".AUTHORIZATION_INFO_ATTRIBUTE_NAME";

    /**
     * 权限认证器
     */
    private IAuthorizer authorizer;

    /**
     * 执行过滤
     */
    @Override
    protected void executeFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        IAuthorizer authorizer = getAuthorizer();
        if (null != authorizer) {
            ServletHelp.setRequestAndResponse(request, response);
            IRequestInfo requestInfo = ServletHelp.getRequestInfo();
            IAuthorizationInfo info = authorizer.isPermitted(requestInfo);
            if (null != info && !info.isSuccess()) {// 未通过权限验证
                // 这里只做标记，而将实际处理推迟
                request.setAttribute(IAuthorizationInfo.class.getName(), info);
                // request.getRequestDispatcher().forward(request, response);
            }
            request.setAttribute(AUTHORIZATION_INFO_ATTRIBUTE_NAME, info);
        }
        filterChain.doFilter(request, response);
    }

    public IAuthorizer getAuthorizer() {
        return authorizer;
    }

    public void setAuthorizer(IAuthorizer authorizer) {
        this.authorizer = authorizer;
    }
}
