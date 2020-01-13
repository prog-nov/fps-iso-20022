package com.forms.beneform4j.webapp.portal.setting.service;

import java.util.List;
import java.util.Map;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.portal.setting.bean.SettingBean;
import com.forms.beneform4j.webapp.portal.setting.form.SettingForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 获取用户参数<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public interface ISettingService {

    /**
     * 查找用户设置参数列表
     * 
     * @param param
     * @param user
     * @return
     */
    public List<SettingBean> sQueryUserSettings(SettingForm param, IUser user);

    /**
     * 更新用户参数设置
     * 
     * @param params
     * @param user
     * @return
     */
    public int sUpdateUserSettings(String[] codes, String values[], IUser user);

    /**
     * 获取下拉数据
     * 
     * @return
     */
    public List<SettingBean> sGetComboData(IUser user, SettingForm form);

    /**
     * 获取用户数据MAP结果集
     * 
     * @param param
     * @param user
     * @return
     */
    public List<Map<String, Object>> sQueryUserSettingsMap(SettingForm param, IUser user);

}
