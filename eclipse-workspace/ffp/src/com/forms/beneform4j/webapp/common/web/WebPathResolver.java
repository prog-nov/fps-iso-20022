package com.forms.beneform4j.webapp.common.web;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.web.path.impl.PathResolver;
import com.forms.beneform4j.webapp.common.AppConfig;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 路径解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-18<br>
 */
public class WebPathResolver extends PathResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isDebug(HttpServletRequest request) {
        return "debug".equalsIgnoreCase(ParamHolder.getParameter(AppConfig.getDevModeConfigName()));
    }

}
