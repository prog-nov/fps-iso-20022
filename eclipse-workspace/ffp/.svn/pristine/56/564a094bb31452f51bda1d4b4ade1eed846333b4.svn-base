package com.forms.beneform4j.webapp.portal.setting.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.portal.setting.bean.SettingBean;
import com.forms.beneform4j.webapp.portal.setting.form.SettingForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人设置数据访问层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Repository("settingDao")
public interface ISettingDao {
    /**
     * 获取用户参数
     * 
     * @param param
     * @return
     */
    public List<SettingBean> dQueryUserSettings(@Param("param") SettingForm param, @Param("user") IUser user);

    /**
     * 更新用户参数
     * 
     * @param params
     * @param user
     * @return
     */
    public int dUpdateUserSettings(@Param("param") SettingForm params, @Param("user") IUser user);

    /**
     * 新增用户参数
     * 
     * @param params
     * @param user
     * @return
     */
    public int dInsertUserSettings(@Param("param") SettingForm params, @Param("user") IUser user);

    /**
     * 获取下拉数据
     * 
     * @return
     */
    public List<SettingBean> dGetUserComboData(@Param("user") IUser user, @Param("param") SettingForm param);

    /**
     * 获取用户数据MAP结果集
     * 
     * @param param
     * @param user
     * @return
     */
    public List<Map<String, Object>> dQueryUserSettingsMap(@Param("param") SettingForm param, @Param("user") IUser user);
}
