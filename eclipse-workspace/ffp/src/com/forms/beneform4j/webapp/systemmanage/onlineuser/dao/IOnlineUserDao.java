package com.forms.beneform4j.webapp.systemmanage.onlineuser.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.bean.OnlineUserBean;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.form.OnlineUserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台<br>
 * Description : 在线用户DAO <br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20 <br>
 */
@Repository("onlineUserDao")
public interface IOnlineUserDao {

    /**
     * 查询在线用户列表
     * 
     * @param sessionId
     * @return
     */
    public List<OnlineUserBean> dList(OnlineUserForm user, IPage page);

}
