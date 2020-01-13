package com.forms.beneform4j.webapp.portal.setting.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.portal.setting.bean.SettingBean;
import com.forms.beneform4j.webapp.portal.setting.dao.ISettingDao;
import com.forms.beneform4j.webapp.portal.setting.form.SettingForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人设置服务层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Service("settingService")
@Scope("prototype")
public class SettingService implements ISettingService {

    @Resource(name = "settingDao")
    private ISettingDao dao;

    @Override
    public List<SettingBean> sQueryUserSettings(SettingForm param, IUser user) {
        return dao.dQueryUserSettings(param, user);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public int sUpdateUserSettings(String[] codes, String[] values, IUser user) {
        if (null != codes && null != values && codes.length == values.length) {
            for (int n = 0; n < codes.length; n++) {
                SettingForm form = new SettingForm();
                form.setParamCode(codes[n]);
                form.setParamValue(values[n]);
                int i = dao.dUpdateUserSettings(form, user);
                if (i <= 0) {
                    dao.dInsertUserSettings(form, user);
                }
            }
            user.getParamService().refresh();
            return codes.length;
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SettingBean> sGetComboData(IUser user, SettingForm form) {
        return dao.dGetUserComboData(user, form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> sQueryUserSettingsMap(SettingForm param, IUser user) {
        return dao.dQueryUserSettingsMap(param, user);
    }
}
