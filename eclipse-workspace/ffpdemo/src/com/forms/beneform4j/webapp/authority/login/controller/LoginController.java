package com.forms.beneform4j.webapp.authority.login.controller;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.core.util.rsa.IKeyService;
import com.forms.beneform4j.core.util.rsa.impl.KeyProperty;
import com.forms.beneform4j.security.core.login.info.IAuthenticationInfo;
import com.forms.beneform4j.security.core.login.token.impl.UsernamePasswordToken;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.util.tree.ITreeNode;
import com.forms.beneform4j.web.annotation.JsonBody;
import com.forms.beneform4j.web.annotation.JsonField;
import com.forms.beneform4j.web.annotation.NoWrapJsonBody;
import com.forms.beneform4j.web.annotation.TreeJsonBody;
import com.forms.beneform4j.web.annotation.User;
import com.forms.beneform4j.webapp.authority.login.bean.TopMessageBean;
import com.forms.beneform4j.webapp.authority.login.domain.LoginConst;
import com.forms.beneform4j.webapp.authority.login.service.ILoginService;
import com.forms.beneform4j.webapp.common.web.WebTool;
import com.forms.beneform4j.webapp.systemmanage.user.bean.UserInfo;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 用户登录WEB控制层<br>
 * Author : Kingdom <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-30<br>
 */
@Controller
public class LoginController {

    @Resource(name = "loginService")
    private ILoginService service;

    @Resource(name = "rsaKeyService")
    private IKeyService<KeyProperty> keyService;

    /**
     * 登录页
     * 
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index() {
        return "system/index";
    }

    /**
     * 登录提交
     * 
     * @param token
     * @param result
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView login(@Validated UsernamePasswordToken token, BindingResult result, HttpServletRequest request) {

        reBuildSession(request);
        ModelAndView mv = new ModelAndView("system/index");
        IAuthenticationInfo login = service.sLogin(token);
        if (null != login && login.isSuccess()) {
            mv.setViewName("redirect:main");
        } else {
            mv.addObject("login", login);
        }
        return mv;
    }

    /**
     * 欢迎页
     * 
     * @param user
     * @return
     */
    @RequestMapping({"/main"})
    public ModelAndView main(IUser user) {
        ModelAndView mv = new ModelAndView("system/mainframe/welcome");
        UserInfo loginUser = WebTool.getLoginUser();
        mv.addObject("user", loginUser);
        return mv;
    }

    /**
     * 改变样式
     * 
     * @return
     */
    @RequestMapping({"/uiholder/changeLocale", "/uiholder/changeTheme"})
    public ModelAndView changeLocaleOrTheme() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:main");
        return mv;
    }

    /**
     * 登出/注销
     * 
     * @param sessionId
     * @return
     */
    @RequestMapping({"/logout"})
    public ModelAndView logout(@RequestParam(value = "sessionId", required = false) String sessionId, IRequestInfo info) {
        UserInfo loginUser = (UserInfo) WebTool.getLoginUser();
        if (Tool.CHECK.isBlank(sessionId)) {
            sessionId = WebTool.getAutoGenerateSessionId();
        }
        if (null != loginUser) {
            loginUser.setSessionId(sessionId);
        }
        service.sLogout(loginUser, LoginConst.TYPE_LOGOUT, info);
        WebTool.removeLoginUser();
        return new ModelAndView("system/index");
    }

    /**
     * 登出/注销Ajax
     * 
     * @param sessionId
     * @return
     */
    @RequestMapping({"/logoutAjax"})
    @JsonBody
    public boolean logoutAjax(@RequestParam(value = "sessionId", required = false) String sessionId, IRequestInfo info) {
        UserInfo loginUser = (UserInfo) WebTool.getLoginUser();
        if (Tool.CHECK.isBlank(sessionId)) {
            sessionId = WebTool.getAutoGenerateSessionId();
        }
        if (null != loginUser) {
            loginUser.setSessionId(sessionId);
        }
        service.sLogout(loginUser, LoginConst.TYPE_LOGOUT, info);
        WebTool.removeLoginUser();
        return true;
    }

    /**
     * 获取左侧MINI菜单-一次性加载
     * 
     * @param id
     * @return
     */
    @RequestMapping("/miniMenu")
    @NoWrapJsonBody(value = {"id#false", "code#id", "icon#iconCls", "checkStatus#false", "parentCode#false", "permId#false", "displayArea#false", "dependMenuId#false", "authorLevel#false", "menuFlag#false", "seqno#false", "depth#false", "permAttr#false"}, jsonFields = {@JsonField(value = "leaf", convertBean = "menuJsonConvert", alias = "state"), @JsonField(value = "path", convertBean = "menuJsonConvert", alias = "attributes")})
    public List<? extends ITreeNode> getMiniMenu(@RequestParam(value = "id", required = false) Integer id, @User UserInfo user) {
        return service.sGetLeftMenu(user, id);
    }

    /**
     * 获取左侧菜单-异步
     * 
     * @param id
     * @return
     */
    @RequestMapping("/leftMenu")
    @NoWrapJsonBody(value = {"children#false", "id#false", "code#id", "icon#iconCls", "checkStatus#false", "parentCode#false", "permId#false", "displayArea#false", "dependMenuId#false", "authorLevel#false", "menuFlag#false", "seqno#false", "depth#false", "permAttr#false"}, jsonFields = {@JsonField(value = "leaf", convertBean = "menuJsonConvert", alias = "state"), @JsonField(value = "path", convertBean = "menuJsonConvert", alias = "attributes")})
    public List<? extends ITreeNode> getLeftMenu(@RequestParam(value = "id", required = false) Integer id, @User UserInfo user) {
        return service.sGetLeftMenu(user, id);
    }

    /**
     * 获取横向菜单
     * 
     * @param session
     * @return
     */
    @RequestMapping("/topMenu")
    @TreeJsonBody(value = {"id#false", "code#id", "icon#iconCls", "checkStatus#false", "parentCode#false", "permId#false", "displayArea#false", "dependMenuId#false", "authorLevel#false", "menuFlag#false", "seqno#false", "depth#false", "permAttr#false"}, jsonFields = {@JsonField(value = "leaf", convertBean = "menuJsonConvert", alias = "state"), @JsonField(value = "path", convertBean = "menuJsonConvert", alias = "attributes")})
    public List<? extends ITreeNode> getTopMenu(@User UserInfo user) {
        return service.sGetTopMenu(user);
    }

    /**
     * 获取悬浮菜单
     * 
     * @param session
     * @return
     */
    @RequestMapping("/shortCutMenu")
    @JsonBody
    public List<? extends ITreeNode> getShortCutMenu(@User UserInfo user) {
        List<? extends ITreeNode> sGetShortMenuNodes = service.sGetShortMenuNodes(user);
        if (null != sGetShortMenuNodes && !sGetShortMenuNodes.isEmpty()) {
            return sGetShortMenuNodes;
        }
        return Collections.emptyList();
    }

    /**
     * 获取公钥
     * 
     * @return
     */
    @RequestMapping("/getKey")
    @JsonBody
    public KeyProperty getKey() throws Exception {
        return keyService.getKey().buildHexKey();
    }

    /**
     * 获取顶部消息
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/getTopMessage")
    @JsonBody
    public List<TopMessageBean> getTopMessageList(@User UserInfo user) throws Exception {
        return service.sGetTopMessage(user);
    }

    /**
     * 加载普通页面
     * 
     * @param pageUrl
     * @return
     */
    @RequestMapping("commons/loadpage")
    public ModelAndView loadPage(@RequestParam(value = "pageUrl", required = true) String pageUrl) {
        return new ModelAndView(pageUrl);
    }

    /**
     * 重建 Session
     * 
     * @param request
     * @return
     */
    private String reBuildSession(HttpServletRequest request) {
        // 销毁原始session
        request.getSession(true).invalidate();
        // 主动让容器重新生成session
        return request.getSession().getId();
    }
}
