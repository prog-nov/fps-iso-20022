package com.forms.beneform4j.web;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.forms.beneform4j.core.service.encrypt.EncryptHelp;
import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.upload.IUploadFile;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Web层级的工具类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class WebUtils {

    /**
     * 获取ServletContext对象，即JSP内建对象中的application
     * 
     * @return Web应用对象
     */
    public static ServletContext getApplication() {
        return ServletHelp.getApplication();
    }

    /**
     * 获取应用属性
     * 
     * @param attr 属性名称
     * @return 属性值
     */
    public static Object getApplicationAttr(String attr) {
        return getApplication().getAttribute(attr);
    }

    /**
     * 设置应用属性
     * 
     * @param attr 属性名称
     * @param value 属性值
     */
    public static void setApplicationAttr(String attr, Object value) {
        getApplication().setAttribute(attr, value);
    }

    /**
     * 移除应用属性
     * 
     * @param attr 属性名称
     */
    public static void removeApplicationAttr(String attr) {
        getApplication().removeAttribute(attr);
    }

    /**
     * 获取应用初始化参数，配置在web.xml文件中<context-param>的元素
     * 
     * @param name 参数名
     * @return 参数值
     */
    public static String getApplicationInitParameter(String name) {
        return getApplication().getInitParameter(name);
    }

    /**
     * 获取项目路径
     * 
     * @return 项目路径
     */
    public static String getProjectPath() {
        if (null == projectPath) {
            synchronized (WebUtils.class) {
                if (null == projectPath) {
                    String realPath = getApplication().getRealPath("/");
                    if (realPath.charAt(realPath.length() - 1) != '/' && realPath.charAt(realPath.length() - 1) != '\\') {
                        realPath = realPath + "/";
                    }
                    projectPath = realPath;
                }
            }
        }
        return projectPath;
    }

    /**
     * 获取项目根
     * 
     * @return 项目根
     */
    public static String getProjectRoot() {
        if (null == projectRoot) {
            synchronized (WebUtils.class) {
                if (null == projectRoot) {
                    projectRoot = getApplication().getContextPath() + "/";
                }
            }
        }
        return projectRoot;
    }

    /**
     * 获取HttpServletRequest对象，即JSP内建对象中的request
     * 
     * @return 请求对象
     */
    public static HttpServletRequest getRequest() {
        return ServletHelp.getRequest();
    }

    /**
     * 是否为Ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String h = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(h)) {
            return true;
        }
        return false;
    }

    /**
     * 获取请求属性
     * 
     * @param attr 属性名称
     * @return 属性值
     */
    public static Object getRequestAttr(String attr) {
        HttpServletRequest request = getRequest();
        return null == request ? null : request.getAttribute(attr);
    }

    /**
     * 设置请求属性
     * 
     * @param attr 属性名称
     * @param value 属性值
     */
    public static void setRequestAttr(String attr, Object value) {
        HttpServletRequest request = getRequest();
        if (null != request) {
            request.setAttribute(attr, value);
        }
    }

    /**
     * 移除请求属性
     * 
     * @param attr 属性名称
     */
    public static void removeRequestAttr(String attr) {
        HttpServletRequest request = getRequest();
        if (null != request) {
            request.removeAttribute(attr);
        }
    }

    /**
     * 获取参数
     * 
     * @param name 参数名称
     * @return 参数值
     */
    public static String getRequestParameter(String name) {
        HttpServletRequest request = getRequest();
        return null == request ? null : request.getParameter(name);
    }

    /**
     * 获取参数
     * 
     * @param name 参数名称
     * @param defaultValue 默认值
     * @return 参数值
     */
    public static String getRequestParameter(String name, String defaultValue) {
        HttpServletRequest request = getRequest();
        if (null != request) {
            String value = request.getParameter(name);
            if (!CoreUtils.isBlank(value)) {
                return value;
            }
        }
        return defaultValue;
    }

    /**
     * 获取参数值（一组值）
     * 
     * @param name 参数名称
     * @return 参数值
     */
    public static String[] getRequestParameterValues(String name) {
        HttpServletRequest request = getRequest();
        return null == request ? null : request.getParameterValues(name);
    }

    /**
     * 获取客户端IP
     * 
     * @param request 请求对象
     * @return 客户端实际IP地址
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (null != ip && -1 != ip.indexOf(',')) {
            ip = ip.substring(0, ip.indexOf(','));
        }
        return CoreUtils.convertIp(ip);
    }

    /**
     * 获取没有项目根路径的URI
     * 
     * @param uri 请求URI
     * @return 去掉根路径后的URI
     */
    public static String getUriWithoutRoot(String uri) {
        String root = getProjectRoot();
        int i = uri.indexOf(root);
        if (-1 != i) {
            uri = uri.substring(i + root.length());
        }
        return uri;
    }

    /**
     * 获取没有项目根路径的URI，并将最后一个点后去掉
     * 
     * @param uri 请求URI
     * @return 去掉根路径后的URI
     */
    public static String getRestUriWithoutRoot(String uri) {
        String root = getProjectRoot();
        int i = uri.indexOf(root);
        if (-1 != i) {
            uri = uri.substring(i + root.length());
        }
        i = uri.lastIndexOf('.');
        if (-1 != i) {
            uri = uri.substring(0, i);
        }
        return uri;
    }

    /**
     * 获取上传文件
     * 
     * @return 上传文件数组
     */
    public static IUploadFile[] getUploadFile() {
        return ServletHelp.getUploadFile();
    }

    /**
     * 获取HttpSession对象，即JSP内建对象中的session
     * 
     * @return 会话对象
     */
    public static HttpSession getSession() {
        return ServletHelp.getSession();
    }

    /**
     * 获取会话属性
     * 
     * @param attr 属性名称
     * @return 属性值
     */
    public static Object getSessionAttr(String attr) {
        return getSessionAttr(getSession(), attr);
    }

    /**
     * 设置会话属性
     * 
     * @param attr 属性名称
     * @param value 属性值
     */
    public static void setSessionAttr(String attr, Object value) {
        setSessionAttr(getSession(), attr, value);
    }

    /**
     * 移除会话属性
     * 
     * @param attr 属性名称
     */
    public static void removeSessionAttr(String attr) {
        removeSessionAttr(getSession(), attr);
    }

    /**
     * 判断session是否已经失效
     * 
     * @param session 会话对象
     * @return 是否失效
     */
    public static boolean isInvalidated(HttpSession session) {
        try {
            session.getAttribute("");
            return false;
        } catch (java.lang.IllegalStateException e) {
            return true;
        }
    }

    /**
     * 移除session中属性
     * 
     * @param session 会话对象
     * @param attr 属性名称
     */
    public static void removeSessionAttr(HttpSession session, String attr) {
        if (null != session && !isInvalidated(session)) {
            session.removeAttribute(attr);
        }
    }

    /**
     * 获取session中属性
     * 
     * @param session 会话对象
     * @param attr 属性名称
     * @return 属性值
     */
    public static Object getSessionAttr(HttpSession session, String attr) {
        if (null != session && !isInvalidated(session)) {
            return session.getAttribute(attr);
        }
        return null;
    }

    /**
     * 设置session中属性
     * 
     * @param session 会话对象
     * @param attr 属性名称
     * @param value 属性值
     */
    public static void setSessionAttr(HttpSession session, String attr, Object value) {
        if (null != session && !isInvalidated(session)) {
            session.setAttribute(attr, value);
        }
    }

    /**
     * 获取HttpServletResponse对象，即JSP内建对象中的response
     * 
     * @return 响应对象
     */
    public static HttpServletResponse getResponse() {
        return ServletHelp.getResponse();
    }

    /**
     * 添加头部
     * 
     * @param name 响应头名称
     * @param context 响应头
     */
    public static void addHeader(String name, String context) {
        HttpServletResponse response = getResponse();
        if (null != response) {
            response.addHeader(name, context);
        }
    }

    /**
     * 添加Cookie
     * 
     * @param cookie Cookie对象
     */
    public static void addCookie(Cookie cookie) {
        HttpServletResponse response = getResponse();
        if (null != response) {
            response.addCookie(cookie);
        }
    }

    /**
     * 添加Cookie
     * 
     * @param name cookie名
     * @param value cookie值
     */
    public static void addCookie(String name, String value) {
        HttpServletResponse response = getResponse();
        if (null != response) {
            response.addCookie(new Cookie(name, value));
        }
    }

    /**
     * 添加Cookie
     * 
     * @param name cookie名
     * @param value cookie值
     * @param encrypt 是否加密
     */
    public static void addCookie(String name, String value, boolean encrypt) {
        HttpServletResponse response = getResponse();
        if (null != response) {
            response.addCookie(new Cookie(name, encrypt ? EncryptHelp.encode(value) : value));
        }
    }

    /**
     * 项目根
     */
    private static String projectRoot = null;

    /**
     * 项目路径
     */
    private static String projectPath = null;
}
