package com.forms.beneform4j.webapp.portal.setting.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.JsonField;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.portal.setting.bean.SettingBean;
import com.forms.beneform4j.webapp.portal.setting.form.SettingForm;
import com.forms.beneform4j.webapp.portal.setting.service.ISettingService;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人设置<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Controller
@RequestMapping("portal/setting")
public class SettingController {

    @Resource(name = "settingService")
    private ISettingService service;

    /**
     * 返回个人设置参数列表
     * 
     * @param param
     * @return
     */
    @RequestMapping("gotoUserSettings")
    public ModelAndView gotoUserSettings(SettingForm param, @User UserInfo user) {
        ModelAndView mv = new ModelAndView("system/mainframe/portal/setting/setting");
        mv.addObject("list", service.sQueryUserSettings(param, user));
        return mv;
    }

    /**
     * 返回个人设置参数列表
     * 
     * @param param
     * @return
     */
    @RequestMapping("queryUserSettings")
    @JsonBody
    public List<SettingBean> queryUserSettings(SettingForm param, @User UserInfo user) {
        return service.sQueryUserSettings(param, user);
    }

    /**
     * 返回个人设置参数列表(Map形式)
     * 
     * @param param
     * @return
     */
    @RequestMapping("queryUserSettingsMap")
    @JsonBody(jsonFields = {@JsonField(value = "selected", convertBean = "settingJsonConvert", alias = "selected")})
    public List<Map<String, Object>> queryUserSettingsMap(SettingForm param, @User UserInfo user) {
        return service.sQueryUserSettingsMap(param, user);
    }

    /**
     * 更新用户设置
     * 
     * @param paramCode
     * @param paramValue
     * @param user
     * @return
     */
    @RequestMapping("swapUserSettings")
    @JsonBody
    public int swapUserSettings(@RequestParam(name = "paramCode") String paramCode, @RequestParam("paramValue") String paramValue, @User UserInfo user) {
        return service.sUpdateUserSettings(new String[] {paramCode}, new String[] {paramValue}, user);

    }

    /**
     * 更新用户参数设置
     * 
     * @param param
     * @return
     */
    @RequestMapping("updateUserSettings")
    public ModelAndView updateUserSettings(@RequestParam(name = "paramCode[]") String[] paramCodes, @RequestParam(name = "paramValue[]") String[] paramValues, @User UserInfo user) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:main");
        service.sUpdateUserSettings(paramCodes, paramValues, user);
        return mv;

    }

    @RequestMapping("getUserComboData")
    @JsonBody
    public List<SettingBean> getComboData(SettingForm param, @User UserInfo user) {
        return service.sGetComboData(user, param);
    }

}
