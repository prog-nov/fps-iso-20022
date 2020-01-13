package com.forms.beneform4j.webapp.systemmanage.onlineuser.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.authority.login.service.ILoginService;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.bean.OnlineUserBean;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.form.OnlineUserForm;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.service.IOnlineUserService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 在线用户控制层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-19<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/onlineuser")
public class OnlineUserController {

    @Resource(name = "onlineUserService")
    private IOnlineUserService service;

    @Resource(name = "loginService")
    private ILoginService loginService;

    /**
     * 在线列表查询
     * 
     * @param user
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<OnlineUserBean> list(OnlineUserForm user, IPage page) {
        return service.sList(user, page);
    }

    /**
     * 下线
     * 
     * @param userId
     * @param sessionId
     * @return
     */
    @RequestMapping("offline")
    @JsonBody
    public int offline(@RequestParam("userId") String userId, @RequestParam("sessionId") String sessionId) {
        return loginService.sLogout(userId, sessionId);
    }
}
