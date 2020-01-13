package com.forms.beneform4j.webapp.common.clean;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.clean.Clean;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.service.ILoginService;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录日志清理<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-19<br>
 */
@Clean(cron = "0 45 15 ? * *", description = "登录日志清理")
public class Beneform4jLoginLogClearJob implements Job {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String today = Tool.DATE.getDate();
        ILoginService service = SpringHelp.getBean("loginService", ILoginService.class);
        service.sDeleteLoginLog(today);
    }

}
