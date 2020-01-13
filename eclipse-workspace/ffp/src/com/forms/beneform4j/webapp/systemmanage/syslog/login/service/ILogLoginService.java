package com.forms.beneform4j.webapp.systemmanage.syslog.login.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.bean.LogLoginBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.form.LogLoginForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录日志查询服务层接口<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
public interface ILogLoginService {

    /**
     * 查询登录日志列表
     * 
     * @param loginForm
     * @param page
     * @return
     */
    List<LogLoginBean> sList(LogLoginForm loginForm, IPage page);

}
