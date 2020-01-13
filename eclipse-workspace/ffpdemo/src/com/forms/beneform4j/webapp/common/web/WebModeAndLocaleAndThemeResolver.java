package com.forms.beneform4j.webapp.common.web;

import java.util.Arrays;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.LocaleUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.locale.ILocaleResolver;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.springmvc.resourceresolver.StaticResourceResolver;
import com.forms.beneform4j.webapp.common.AppConfig;
import com.forms.beneform4j.webapp.portal.setting.service.ISettingService;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 语言、主题、静态资源解析器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-13<br>
 */
public class WebModeAndLocaleAndThemeResolver extends StaticResourceResolver implements LocaleResolver, ThemeResolver, ILocaleResolver {

    /**
     * 个人设置服务
     */
    @Resource(name = "settingService")
    private ISettingService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return getLocale();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        this.setLocale(locale);
    }

    /**
     * 更新用户设置
     * 
     * @param code
     * @param value
     * @param user
     */
    private void updateProtalSetting(String code, String value, IUser user) {
        String[] codes = new String[] {code};
        String[] values = new String[] {value};
        service.sUpdateUserSettings(codes, values, user);
        user.getParamService().removeParams(Arrays.asList(code));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String resolveThemeName(HttpServletRequest request) {
        String theme = null;
        UserInfo user = WebTool.getLoginUser();
        if (null != user && null != user.getParamService()) {
            theme = user.getParamService().get(AppConfig.getThemeConfigName());
        }

        if (CoreUtils.isBlank(theme)) {
            theme = WebBeneform4jConfig.getDefaultTheme();
        }
        return theme;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setThemeName(HttpServletRequest request, HttpServletResponse response, String themeName) {
        UserInfo user = WebTool.getLoginUser();
        if (null != user && null != user.getParamService() && !Tool.CHECK.isBlank(themeName)) {
            updateProtalSetting(AppConfig.getThemeConfigName(), themeName, user);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        UserInfo user = WebTool.getLoginUser();
        if (null != user && null != user.getParamService()) {
            String locale = user.getParamService().get(AppConfig.getLocaleConfigName());
            if (!CoreUtils.isBlank(locale)) {
                return LocaleUtils.toLocale(locale);
            }
        }

        // HttpServletRequest request = ServletHelp.getRequest();
        // if(null != request){
        // return request.getLocale();
        // }else{
        // return Locale.getDefault();
        // }
        return Locale.getDefault();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLocale(Locale locale) {
        UserInfo user = WebTool.getLoginUser();
        if (null != user && null != user.getParamService() && null != locale) {
            updateProtalSetting(AppConfig.getLocaleConfigName(), locale.toString(), user);
        }
    }
}
