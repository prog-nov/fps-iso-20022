package com.forms.beneform4j.webapp.systemmanage.onlineuser.service;

import java.util.List;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.bean.OnlineUserBean;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.form.OnlineUserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 在线用户管理服务层接口<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
public interface IOnlineUserService {

    /**
     * 查询在线用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    List<OnlineUserBean> sList(OnlineUserForm user, IPage page);

    /*	*//**
           * 强制离线
           * 
           * @param userId 用户代码
           * @return
           *//*
             * int sOffline(String userId,String sessionId);
             */
}
