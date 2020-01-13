package com.forms.beneform4j.webapp.systemmanage.syslog.login.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.bean.LogLoginBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.login.form.LogLoginForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台<br>
 * Description : 登录日志DAO <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5 <br>
 */
@Repository("logLoginDao")
public interface ILogLoginDao {

    /**
     * 查询登录日志列表
     * 
     * @param sessionId
     * @return
     */
    public List<LogLoginBean> dList(LogLoginForm loginForm, IPage page);

}
