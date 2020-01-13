package com.forms.beneform4j.webapp.authority.login.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.access.permission.impl.MenuPermission;
import com.forms.beneform4j.security.core.login.authc.IAuthenticator;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.tree.ITree;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.webapp.authority.login.bean.BaseLoginLogBean;
import com.forms.beneform4j.webapp.authority.login.bean.TopMessageBean;
import com.forms.beneform4j.webapp.authority.login.dao.ILoginDao;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.authority.login.form.TopMessageForm;
import com.forms.beneform4j.webapp.authority.session.LocalSessionStore;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户登录服务层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-30<br>
 */
@Service("loginService")
@Scope("prototype")
public class LoginService implements ILoginService {

    @Resource(name = "loginDao")
    private ILoginDao dao;

    @Autowired
    private IAuthenticator authenticator;

    @Override
    public IAuthenticationInfo sLogin(IAuthenticationToken token) {
        return authenticator.login(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sLogout(UserInfo user, String type, IRequestInfo info) {
        if (!Tool.CHECK.isEmpty(user) && !Tool.CHECK.isEmpty(info)) {
            dao.dRecordLoginLog(getLoginLogger(user, type, info));
            dao.dAfterLoginOut(user);
            if (null != user.getSessionId()) {
                dao.dSetUserOffline(user.getSessionId());
                LocalSessionStore.remove(user.getSessionId());
            }
            user.getParamService().clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sLogout(String userId, String sessionId) {
        if (!Tool.CHECK.isEmpty(userId) && !Tool.CHECK.isEmpty(sessionId)) {
            BaseLoginLogBean rs = dao.dGetSessionInfo(sessionId);
            if (null != rs) {
                UserInfo user = new UserInfo();
                user.setUserId(userId);
                rs.setLioFlag(LoginConst.TYPE_FORCE_LOGOUT);
                rs.setOptDate(Tool.DATE.getDate());
                rs.setOptTime(Tool.DATE.getTime());
                rs.setKeyId(Tool.STRING.getRandomNumeric(32));
                dao.dRecordLoginLog(rs);
                dao.dAfterLoginOut(user);
                if (null != sessionId) {
                    dao.dSetUserOffline(sessionId);
                    HttpSession httpSession = LocalSessionStore.get(sessionId);
                    if (null != httpSession) {
                        httpSession.invalidate();
                    }
                    LocalSessionStore.remove(sessionId);
                }
            }
            return 1;
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ITreeNode> sGetTopMenu(UserInfo user) {
        List<MenuPermission> list = new ArrayList<MenuPermission>();
        ITree<IMenuPermission> menuTree = user.getPermissionManager().getMenuTree();
        List<ITreeNode> children = menuTree.getRoot().getChildren();
        for (ITreeNode child : children) {
            MenuPermission mp = (MenuPermission) child;
            if (!"0".equals(mp.getDisplayArea())) {
                MenuPermission m = (MenuPermission) mp.copyNodeData();
                list.add(m);
            } else {
                list.add(mp);
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ITreeNode> sGetShortMenuNodes(UserInfo user) {
        ITree<IMenuPermission> menuTree = user.getPermissionManager().getMenuTree();
        List<IMenuPermission> list = dao.dGetShortMenuNodes(user, Tool.LOCALE.getCurrentLocale().toString());
        IMenuPermission m = null;
        // 为路径信息赋值
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                m = list.get(i);
                try {
                    m.setPath(menuTree.getNode(m.getCode()).getPath());
                } catch (Exception e) {
                    m.setPath(m.getText());
                }
                list.set(i, m);
            }
        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends ITreeNode> sGetLeftMenu(UserInfo user, Integer pid) {
        if (null == pid) {
            return Collections.emptyList();
        }
        ITree<IMenuPermission> menuTree = user.getPermissionManager().getMenuTree();
        List<ITreeNode> children = menuTree.getNode("" + pid).getChildren();
        if (null == children) {
            return Collections.emptyList();
        }
        return children;
    }

    /**
     * 组装日志信息
     * 
     * @param loginUser
     * @param type
     * @param info
     * @return
     */
    private BaseLoginLogBean getLoginLogger(UserInfo loginUser, String type, IRequestInfo info) {
        BaseLoginLogBean log = new BaseLoginLogBean();
        log.setKeyId(Tool.STRING.getRandomNumeric(32));
        log.setSessionId(loginUser.getSessionId());
        log.setUserId(loginUser.getUserId());
        log.setClientIp(loginUser.getLastLoginIp());
        log.setLioFlag(type);
        log.setOptDate(Tool.DATE.getDate());
        log.setOptTime(Tool.DATE.getTime());
        if (null != info) {
            log.setBrowser(info.getRemoteBrowser());
            log.setOs(info.getRemoteOperateSystem());
            log.setServerIp(info.getRemoteIp());
        }

        return log;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TopMessageBean> sGetTopMessage(UserInfo user) {
        TopMessageForm form = new TopMessageForm();
        form.setCurrentDateTime(Tool.DATE.getDate() + Tool.DATE.getTime());
        return dao.dGetTopMessage(user, form);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sDeleteLoginLog(String date) {
        dao.dDeleteLoginLog(date);
    }
}
