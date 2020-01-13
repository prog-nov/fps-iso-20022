package com.forms.beneform4j.webapp.systemmanage.syslog.visit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.bean.LogVisitBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.form.LogVisitForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台<br>
 * Description : 访问日志DAO <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5 <br>
 */
@Repository("logVisitDao")
public interface ILogVisitDao {

    /**
     * 查询登录日志列表
     * 
     * @param sessionId
     * @return
     */
    public List<LogVisitBean> dList(LogVisitForm visitForm, IPage page);

}
