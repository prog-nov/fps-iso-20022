package com.forms.beneform4j.webapp.systemmanage.syslog.visit.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.bean.LogVisitBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.form.LogVisitForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问日志查询服务层接口<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
public interface ILogVisitService {

    /**
     * 查询访问日志列表
     * 
     * @param user
     * @param page
     * @return
     */
    List<LogVisitBean> sList(LogVisitForm visitForm, IPage page);

}
