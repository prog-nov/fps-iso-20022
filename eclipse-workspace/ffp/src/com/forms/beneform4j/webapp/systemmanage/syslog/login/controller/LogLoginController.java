package com.forms.beneform4j.webapp.systemmanage.syslog.login.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.bean.LogLoginBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.form.LogLoginForm;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.service.ILogLoginService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录日志控制层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/syslog/login")
public class LogLoginController {

    @Resource(name = "logLoginService")
    private ILogLoginService service;

    /**
     * 日志列表
     * 
     * @param loginForm
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<LogLoginBean> list(LogLoginForm loginForm, IPage page) {
        return service.sList(loginForm, page);
    }

}
