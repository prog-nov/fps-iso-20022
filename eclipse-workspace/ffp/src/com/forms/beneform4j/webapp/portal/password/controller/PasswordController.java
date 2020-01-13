package com.forms.beneform4j.webapp.portal.password.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.portal.password.service.IPasswordService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户个人密码控制层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
@Controller
@Scope("request")
public class PasswordController {

    @Resource(name = "passwordService")
    private IPasswordService passwordService;

    /**
     * 跳转到密码修改页面
     * 
     * @return
     */
    @RequestMapping("portal/password/gotoUpdatePersonalPassword")
    public String gotoUpdatePersonalPassword() {
        return "system/mainframe/portal/password/password";
    }

    /**
     * 修改个人密码
     * 
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @RequestMapping("portal/password/updatePersonalPassword")
    @JsonBody
    public String updatePersonalPassword(@RequestParam("newPassword") String newPassword, @RequestParam("oldPassword") String oldPassword, @User String userId) {
        return passwordService.sUpdatePersonalPassword(userId, oldPassword, newPassword);
    }
}
