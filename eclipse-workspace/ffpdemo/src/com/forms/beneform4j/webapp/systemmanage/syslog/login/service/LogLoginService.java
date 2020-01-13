package com.forms.beneform4j.webapp.systemmanage.syslog.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.bean.LogLoginBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.dao.ILogLoginDao;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.form.LogLoginForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录日志查询服务层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
@Service("logLoginService")
@Scope("prototype")
public class LogLoginService implements ILogLoginService {

    @Resource(name = "logLoginDao")
    private ILogLoginDao dao;

    /**
     * 查询登录日志列表
     * 
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<LogLoginBean> sList(LogLoginForm loginForm, IPage page) {
        return dao.dList(loginForm, page);
    }

}
