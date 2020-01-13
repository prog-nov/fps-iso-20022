package com.forms.beneform4j.webapp.portal.password.service;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 修改个人密码<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-13<br>
 */
public interface IPasswordService {

    /**
     * 修改个人密码
     * 
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public String sUpdatePersonalPassword(String userId, String oldPassword, String newPassword);

}
