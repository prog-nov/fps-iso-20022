package com.forms.beneform4j.webapp.portal.password.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.rsa.AbstractKey;
import com.forms.beneform4j.core.util.rsa.IKeyService;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.portal.password.dao.IPasswordDao;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人密码服务层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Service("passwordService")
@Scope("prototype")
public class PasswordService implements IPasswordService {

    @Resource(name = "rsaKeyService")
    private IKeyService<AbstractKey> keyService;

    @Resource(name = "passwordDao")
    private IPasswordDao dao;

    /**
     * 修改个人密码
     * 
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public String sUpdatePersonalPassword(String userId, String oldPassword, String newPassword) {

        String newPasswordMd5 = Tool.STRING.getMd5(userId + keyService.decrypt(newPassword));
        UserInfo loginUser = dao.dFindUserById(userId);
        if (!Tool.STRING.safeEquals(loginUser.getUserPwd(), Tool.STRING.getMd5(userId + keyService.decrypt(oldPassword)))) {
            return Tool.LOCALE.getMessage("user.oldpwd.error", userId);
        }
        loginUser.setModiPwdDate(Tool.DATE.getDate());
        loginUser.setModiPwdTime(Tool.DATE.getTime());
        loginUser.setUserPwd(newPasswordMd5);
        dao.dUpdateUserPassword(loginUser);
        return "ok";
    }
}
