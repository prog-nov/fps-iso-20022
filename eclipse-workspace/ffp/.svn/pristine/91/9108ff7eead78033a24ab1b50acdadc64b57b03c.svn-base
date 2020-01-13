package com.forms.beneform4j.webapp.systemmanage.onlineuser.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.bean.OnlineUserBean;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.dao.IOnlineUserDao;
import com.forms.beneform4j.webapp.systemmanage.onlineuser.form.OnlineUserForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 在线用户查询服务层<br>
 * Author : OuLinhai <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-5-20<br>
 */
@Service("onlineUserService")
@Scope("prototype")
public class OnlineUserService implements IOnlineUserService {

    @Resource(name = "onlineUserDao")
    private IOnlineUserDao dao;

    /**
     * 查询在线用户列表
     * 
     * @param user
     * @param page
     * @return
     */
    @Override
    public List<OnlineUserBean> sList(OnlineUserForm user, IPage page) {
        return dao.dList(user, page);
    }

}
