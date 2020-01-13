package com.forms.beneform4j.webapp.common.session;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.init.Init;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 系统启动时清理当前服务器的会话记录<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-07-22<br>
 */
@Init
public class SessionTruncateService {

    /**
     * 系统启动清除当前服务器的会话
     */
    public void init() {
        try {
            String serverIp = InetAddress.getLocalHost().getHostAddress();
            if (!Tool.CHECK.isBlank(serverIp)) {
                ILoginDao loginDao = SpringHelp.getBean(ILoginDao.class);
                loginDao.dTruncateServerSessions(serverIp);
            }
        } catch (UnknownHostException e) {
            Throw.throwRuntimeException(e);
        }
    }

}
