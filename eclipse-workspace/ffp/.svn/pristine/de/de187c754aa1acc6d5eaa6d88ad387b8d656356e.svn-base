package com.forms.beneform4j.webapp.authority.login.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.util.rsa.impl.KeyProperty;
import com.forms.beneform4j.security.core.access.permission.IMenuPermission;
import com.forms.beneform4j.security.core.login.token.IAuthenticationToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.webapp.authority.login.bean.BaseLoginLogBean;
import com.forms.beneform4j.webapp.authority.login.bean.TopMessageBean;
import com.forms.beneform4j.webapp.authority.login.form.TopMessageForm;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

@Repository("loginDao")
public interface ILoginDao {

    /**
     * 获取登录用户信息
     * 
     * @param token
     * @return
     */
    public UserInfo dGetLoginUser(IAuthenticationToken token);

    /**
     * 成功后 处理
     * 
     * @param user
     * @return
     */
    public int dAfterLoginSuccess(UserInfo user);

    /**
     * 登出后处理
     * 
     * @param user
     * @return
     */
    public int dAfterLoginOut(UserInfo user);

    /**
     * 登录失败后处理
     * 
     * @param user
     * @return
     */
    public int dAfterLoginFailure(UserInfo user);

    /**
     * 记录登录日志
     * 
     * @param log
     * @return
     */
    public int dRecordLoginLog(BaseLoginLogBean log);

    /**
     * 删除登录日志
     * 
     * @param dateBefore
     * @return
     */
    public int dDeleteLoginLog(@Param("dateBefore") String dateBefore);

    /**
     * 重置公钥表
     */
    public void dTruncateKey();

    /**
     * 获取公钥
     * 
     * @return
     */
    public KeyProperty dGetKey();

    /**
     * 初始化公钥
     * 
     * @param kp
     */
    public void dInitKey(KeyProperty kp);

    /**
     * 获取权限Id
     * 
     * @param user
     * @param userRoleMode
     * @return
     */
    public Set<Integer> dGetPermissionIds(@Param("user") IUser user, @Param("userRoleMode") String userRoleMode);

    /**
     * 获取附加菜单
     * 
     * @param locale
     * @return
     */
    public List<IMenuPermission> dGetAddOnMenu(@Param("locale") String locale);

    /**
     * 获取角色Id
     * 
     * @param user
     * @param userRoleMode
     * @return
     */
    public Set<Integer> dGetRoleIds(@Param("user") IUser user, @Param("userRoleMode") String userRoleMode);

    /**
     * 获取菜单节点
     * 
     * @param user
     * @param userRoleMode
     * @param locale
     * @return
     */
    public List<IMenuPermission> dGetMenuNodes(@Param("user") IUser user, @Param("userRoleMode") String userRoleMode, @Param("locale") String locale);

    /**
     * 获取快捷菜单节点
     * 
     * @param user
     * @param locale
     * @return
     */
    public List<IMenuPermission> dGetShortMenuNodes(@Param("user") IUser user, @Param("locale") String locale);

    /**
     * 获取顶部消息
     * 
     * @param user
     * @param form
     * @return
     */
    public List<TopMessageBean> dGetTopMessage(@Param("user") IUser user, @Param("form") TopMessageForm form);

    /**
     * 设置用户在线
     * 
     * @param log
     * @return
     */
    public int dSetUserOnline(BaseLoginLogBean log);

    /**
     * 设置用户离线
     * 
     * @param sessionId
     * @return
     */
    public int dSetUserOffline(String sessionId);

    /**
     * 获取会话信息
     * 
     * @param sessionId
     * @return
     */
    public BaseLoginLogBean dGetSessionInfo(String sessionId);

    /**
     * 清空会话信息
     * 
     * @param serverIp
     * @return
     */
    public int dTruncateServerSessions(@Param("serverIp") String serverIp);

    /**
     * 获取当前会话数
     * 
     * @param userId
     * @return
     */
    public int dGetSessionNum(@Param("userId") String userId);

}
