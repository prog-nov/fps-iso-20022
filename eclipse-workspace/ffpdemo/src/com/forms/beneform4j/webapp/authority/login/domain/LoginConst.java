package com.forms.beneform4j.webapp.authority.login.domain;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 登录相关参数<br>
 * Author : Kingdom <br>
 * Version : 1_0_0 <br>
 * Since : 1_0_0 <br>
 * Date : 2016-4-6-<br>
 */
public class LoginConst {

    /**
     * 登录成功
     */
    public static String SUCCESS = "0000";

    /**
     * 用户或密码错误
     */
    public static String USER_PWD_ERROR = "0001";

    /**
     * 用户不存在
     */
    public static String USER_NOT_EXIST = "0002";

    /**
     * 用户状态异常
     */
    public static String USER_STATE_ERROR = "0003";

    /**
     * 当前IP不允许登录
     */
    public static String USER_IP_ERROR = "0004";

    /**
     * 密码错误尝试登录次数超过限制
     */
    public static String USER_TRY_TOO_MORE = "0005";

    /**
     * 同一会话重复登录
     */
    public static String USER_DUMP_LOGIN = "0006";

    /**
     * 会话数太多
     */
    public static String USER_SESSION_TOOMANY = "0007";

    /**
     * 登陆异常
     */
    public static String LOGIN_EXCEPTION = "9999";

    /**
     * 用户锁定时间
     */
    public static String USER_LOCKED_TIME = "USER_LOCKED_TIME";

    /**
     * 用户登录最多尝试次数
     */
    public static String USER_TRY_LOGIN_NUM = "USER_TRY_LOGIN_NUM";

    /**
     * 最大会话数
     */
    public static String USER_MAX_SESSION_NUM = "USER_MAX_SESSION_NUM";

    /**
     * 已锁定
     */
    public static String LOCK = "1";

    /**
     * 未锁定
     */
    public static String UN_LOCK = "0";

    /**
     * 在线
     */
    public static Integer ONLINE = 1;

    /**
     * 离线
     */
    public static Integer OFFLINE = 0;

    /**
     * 用户可用
     */
    public static String AVAILABLE = "1";

    /**
     * 用户不可用
     */
    public static String UN_AVAILABLE = "0";

    /**
     * 登录对象在SESSION中的KEY
     */
    public static String LOGIN_USER = "com_forms_beneform4j_webapp_base_LOGIN_USER";

    /**
     * 自定义SESSIONID的KEY
     */
    public static String SESSION_ID = "com_forms_beneform4j_webapp_base_SESSION_ID";

    /**
     * 登录
     */
    public static String TYPE_LOGIN = "1";

    /**
     * 登出
     */
    public static String TYPE_LOGOUT = "2";

    /**
     * 系统自动注销
     */
    public static String TYPE_AUTO_LOGOUT = "3";

    /**
     * 强制下线
     */
    public static String TYPE_FORCE_LOGOUT = "4";

}
