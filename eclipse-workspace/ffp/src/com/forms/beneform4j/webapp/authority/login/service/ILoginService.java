package com.forms.beneform4j.webapp.authority.login.service;

import java.util.List;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.webapp.authority.login.bean.TopMessageBean;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录服务<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-11<br>
 */
public interface ILoginService {

    /**
     * 登录
     * 
     * @param token
     * @return
     */
    public IAuthenticationInfo sLogin(IAuthenticationToken token);

    /**
     * 登出
     * 
     * @param user
     * @param type
     * @param info
     */
    public void sLogout(UserInfo user, String type, IRequestInfo info);

    /**
     * 登出
     * 
     * @param userId
     * @param sessionId
     * @return
     */
    public int sLogout(String userId, String sessionId);

    /**
     * 获取左侧菜单
     * 
     * @param user
     * @param pid
     * @return
     */
    public List<? extends ITreeNode> sGetLeftMenu(UserInfo user, Integer pid);

    /**
     * 获取顶部菜单
     * 
     * @param user
     * @return
     */
    public List<? extends ITreeNode> sGetTopMenu(UserInfo user);

    /**
     * 获取快捷菜单
     * 
     * @param user
     * @return
     */
    public List<? extends ITreeNode> sGetShortMenuNodes(UserInfo user);

    /**
     * 获取顶部消息
     * 
     * @param user
     * @return
     */
    public List<TopMessageBean> sGetTopMessage(UserInfo user);

    /**
     * 删除登录日志
     * 
     * @param date
     */
    public void sDeleteLoginLog(String date);

}
