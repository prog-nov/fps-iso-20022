package com.forms.beneform4j.webapp.portal.password.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人密码数据访问层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
@Repository("passwordDao")
public interface IPasswordDao {

    /**
     * 更新用户密码
     * 
     * @param userId
     * @param password
     * @return
     */
    public int dUpdateUserPassword(IUser user);

    /**
     * 根据用户ID查找用户
     * 
     * @param userId
     * @return
     */
    public UserInfo dFindUserById(@Param("userId") String userId);

}
