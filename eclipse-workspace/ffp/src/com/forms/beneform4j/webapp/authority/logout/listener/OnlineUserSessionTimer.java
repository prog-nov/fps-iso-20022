package com.forms.beneform4j.webapp.authority.logout.listener;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import com.forms.beneform4j.core.service.spring.SpringHelp;
import com.forms.beneform4j.core.util.init.Init;
import com.forms.beneform4j.webapp.authority.session.LocalSessionStore;
import com.forms.beneform4j.webapp.common.param.service.ParamHolder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 超时会话清理定时器<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
@Init(depends = ParamHolder.class)
public class OnlineUserSessionTimer extends TimerTask {

    private static final Timer timer = new Timer();

    public void init() {

        long delay = ParamHolder.getParameter("AUTO_LOGOUT_DELAY", Long.class) * 60 * 1000;
        long cycle = ParamHolder.getParameter("AUTO_LOGOUT_CYCLE", Long.class) * 60 * 1000;
        if (delay >= 0) {
            timer.schedule(this, delay, cycle);
        }

    }

    public void destory() {}

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            OnlineUserSessionService service = SpringHelp.getBean(OnlineUserSessionService.class);
            if (null != service) {
                Map<String, HttpSession> list = LocalSessionStore.list();
                for (Entry<String, HttpSession> entry : list.entrySet()) {
                    if (service.isTimeout(entry.getValue())) {
                        service.destroySession(entry.getValue());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * public void execute() { OnlineUserSessionService service =
     * SpringHelp.getBean(OnlineUserSessionService.class); Set<HttpSession> sessions =
     * OnlineUserSessionListener.getSessions(); for (HttpSession session : sessions) {
     * if(service.isTimeout(session)){ //移除会话 service.destroySession(session); } } }
     */
}
