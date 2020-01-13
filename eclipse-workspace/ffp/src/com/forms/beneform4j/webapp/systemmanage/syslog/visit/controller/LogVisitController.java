package com.forms.beneform4j.webapp.systemmanage.syslog.visit.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.web.annotation.PageJsonBody;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.bean.LogVisitBean;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.form.LogVisitForm;
import com.forms.beneform4j.webapp.systemmanage.syslog.visit.service.ILogVisitService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 访问日志控制层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-7-5<br>
 */
@Controller
@Scope("request")
@RequestMapping("system/sysmanager/syslog/visit")
public class LogVisitController {

    @Resource(name = "logVisitService")
    private ILogVisitService service;

    /**
     * 访问日志列表
     * 
     * @param visitForm
     * @param page
     * @return
     */
    @RequestMapping("list")
    @PageJsonBody
    public List<LogVisitBean> list(LogVisitForm visitForm, IPage page) {
        return service.sList(visitForm, page);
    }

}
