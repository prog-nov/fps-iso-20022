package com.forms.beneform4j.web.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.forms.beneform4j.core.service.request.IRequestInfo;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.springmvc.upload.SpringMVCUploadFile;
import com.forms.beneform4j.web.upload.IUploadFile;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : Servlet帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
final public class ServletHelp {

    /**
     * 当前线程中的帮助对象容器
     */
    private static final ThreadLocal<ServletHelp> local = new ThreadLocal<ServletHelp>();
    /**
     * Web应用
     */
    private static ServletContext application;// 应用级别
    /**
     * Web请求
     */
    private HttpServletRequest request;// 请求级别
    /**
     * Web响应
     */
    private HttpServletResponse response;// 请求级别
    /**
     * 请求信息
     */
    private IRequestInfo requestInfo;// 请求级别
    /**
     * 上传文件
     */
    private IUploadFile[] uploadFiles;// 请求级别

    private ServletHelp() {}

    /**
     * 设置应用对象
     * 
     * @param application Web应用对象，整个Web应用唯一
     */
    public static void setApplication(final ServletContext application) {
        if (ServletHelp.application != application) {
            ServletHelp.application = application;
        }
    }

    /**
     * 设置请求对象、响应对象
     * 
     * @param request 请求对象
     * @param response 响应对象
     */
    public static void setRequestAndResponse(final HttpServletRequest request, final HttpServletResponse response) {
        setHttpServletRequest(request);
        setHttpServletResponse(response);
    }

    /**
     * 从当前线程中移除请求对象、响应对象等
     */
    public static void remove() {
        local.remove();
    }

    /**
     * 获取应用对象
     * 
     * @return Web应用对象
     */
    public static ServletContext getApplication() {
        return application;
    }

    /**
     * 获取当前线程请求对象
     * 
     * @return Web请求对象
     */
    public static HttpServletRequest getRequest() {
        return get().request;
    }

    /**
     * 获取当前线程响应对象
     * 
     * @return Web响应对象
     */
    public static HttpServletResponse getResponse() {
        return get().response;
    }

    /**
     * 获取当前线程请求对象对应的会话对象
     * 
     * @return Web会话
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        if (null != request) {
            return request.getSession(false);
        }
        return null;
    }

    /**
     * 获取当前线程请求信息
     * 
     * @return 请求信息
     */
    public static IRequestInfo getRequestInfo() {
        return get().requestInfo;
    }

    /**
     * 获取当前线程上传文件
     * 
     * @return 上传文件
     */
    public static IUploadFile[] getUploadFile() {
        return get().uploadFiles;
    }

    /**
     * 设置请求对象
     * 
     * @param request 请求对象
     */
    private static void setHttpServletRequest(final HttpServletRequest request) {
        if (null != request && get().request != request) {
            ServletHelp help = get();
            help.request = request;
            if (null == help.requestInfo) {
                help.requestInfo = WebBeneform4jConfig.getRequestInfoFactory().getRequestInfo();
            }
            if (request instanceof MultipartRequest) {// 如果是上传
                MultipartRequest mr = (MultipartRequest) request;
                List<IUploadFile> list = new ArrayList<IUploadFile>();
                for (List<MultipartFile> files : mr.getMultiFileMap().values()) {
                    for (MultipartFile file : files) {
                        list.add(new SpringMVCUploadFile(file));
                    }
                }
                IUploadFile[] uploadFiles = new IUploadFile[list.size()];
                list.toArray(uploadFiles);
                help.uploadFiles = uploadFiles;
            }
        }
    }

    /**
     * 设置响应对象
     * 
     * @param response 响应对象
     */
    private static void setHttpServletResponse(final HttpServletResponse response) {
        if (null != response && get().response != response) {
            get().response = response;
        }
    }

    /**
     * 获取当前线程的帮助对象
     * 
     * @return
     */
    private static ServletHelp get() {
        ServletHelp help = local.get();
        if (null == help) {
            help = new ServletHelp();
            local.set(help);
        }
        return help;
    }
}
