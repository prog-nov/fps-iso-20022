package com.forms.beneform4j.web.springmvc.download;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.forms.beneform4j.core.util.exception.Throw;
import com.forms.beneform4j.core.util.logger.CommonLogger;
import com.forms.beneform4j.security.core.login.user.IUser;
import com.forms.beneform4j.util.Tool;
import com.forms.beneform4j.web.ajax.AjaxInvoker;
import com.forms.beneform4j.web.springmvc.download.builder.DownloadObjectBuilderManager;
import com.forms.beneform4j.web.springmvc.download.builder.IDownloadObjectBuilder;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 下载视图<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public class DownloadView extends AbstractView {

    /**
     * 下载文件时，是在浏览器在线显示，还是下载对话框，默认为下载对话框 inline内嵌在浏览器中 attachment弹出下载对话框
     */
    private final static String SHOW_TYPE_PARAM_NAME = "showType";
    /**
     * 为了给下载增加进度条，整个下载处于Ajax请求中，这里的AJAX_ID则表示这个Ajax请求的ID
     */
    private final static String AJAX_ID_PARAM_NAME = "ajaxStatusId";

    protected boolean generatesDownloadContent() {
        return true;
    }

    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File local = null;
        ByteArrayOutputStream os = null;
        ServletOutputStream out = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String ajaxStatusId = getParameter(request, AJAX_ID_PARAM_NAME);
        String showType = getParameter(request, SHOW_TYPE_PARAM_NAME, "attachment");
        boolean flag = false;
        try {
            IUser user = getUser(request);
            if (null != user) {
                CommonLogger.info("下载:用户代码【" + user.getUserId() + "】用户名称【" + user.getUserName() + "】日期【" + Tool.DATE.getDateAndTime() + "】");
            }
            response.setContentType("application/octet-stream;charset=UTF-8");
            out = response.getOutputStream();

            IDownloadObjectBuilder builder = getDownloadObjectBuilder(request);
            if (null == builder) {
                Throw.throwRuntimeException("未找到下载构建器");
            }

            Object result = builder.build(model, request, response);
            if (result instanceof File)// 如果返回的是文件
            {
                local = (File) result;
                String filename = local.getName();
                response.addHeader("Content-Disposition", showType + ";filename=" + new String(filename.getBytes("gb2312"), "iso-8859-1"));
                fis = new FileInputStream(local);
                bis = new BufferedInputStream(fis);
                Tool.IO.copy(bis, out);
            } else if (result instanceof ByteArrayOutputStream) {
                String filename = getParameter(request, DownloadConsts.DOWNLOAD_TITLE_PARAM_NAME);
                response.addHeader("Content-Disposition", showType + ";filename=" + new String(filename.getBytes("gb2312"), "iso-8859-1"));
                os = (ByteArrayOutputStream) result;
                response.setContentLength(os.size());
                os.writeTo(out);
                out.flush();
            } else if (result instanceof CharSequence) {
                this.deal(response, showType, result.toString(), os, out);
            } else {
                // 其它，暂不实现
            }
            flag = true;
        } catch (OutOfMemoryError me) {
            flag = false;
            out.print("下载数据量过大，内存溢出！");
            me.printStackTrace();
            throw me;
        } catch (Exception se) {
            String c = "org.apache.catalina.connector.ClientAbortException";
            if (c.equals(se.getClass().getName()) || (null != se.getCause() && c.equals(se.getCause().getClass().getName()))) {// 客户取消下载
                flag = true;
            } else {
                try {
                    this.deal(response, showType, "下载出现异常：" + Throw.getMessage(se), os, out);
                    se.printStackTrace();
                    flag = true;
                } catch (Exception ii) {
                }
            }
        } finally {
            Tool.IO.closeQuietly(out, bis, fis, os);
            Tool.FILE.forceDelete(local);
            if (flag) {
                AjaxInvoker.updateSuccess(ajaxStatusId);
            } else {
                AjaxInvoker.updateFailure(ajaxStatusId);
            }
        }
    }

    /**
     * 获取下载对象构建器
     * 
     * @param request
     * @return
     */
    protected IDownloadObjectBuilder getDownloadObjectBuilder(HttpServletRequest request) {
        String buildType = getParameter(request, DownloadConsts.BUILD_TYPE_PARAM_NAME);
        IDownloadObjectBuilder builder = DownloadObjectBuilderManager.getDownloadObjectBuilder(buildType);
        return builder;
    }

    protected IUser getUser(HttpServletRequest request) {
        return null;
    }

    /**
     * 处理字符串形式的下载
     * 
     * @param response
     * @param showType
     * @param result
     * @param os
     * @param out
     * @throws IOException
     */
    private void deal(HttpServletResponse response, String showType, String result, ByteArrayOutputStream os, ServletOutputStream out) throws IOException {
        String filename = "download.txt";
        response.addHeader("Content-Disposition", showType + ";filename=" + new String(filename.getBytes("gb2312"), "iso-8859-1"));
        os = new ByteArrayOutputStream();
        os.write(result.getBytes());
        response.setContentLength(os.size());
        os.writeTo(out);
        out.flush();
    }

    /**
     * 从请求中获取参数
     * 
     * @param request
     * @param name
     * @return
     */
    private String getParameter(HttpServletRequest request, String name) {
        return getParameter(request, name, null);
    }

    /**
     * 从请求中获取参数
     * 
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (!Tool.CHECK.isBlank(value)) {
            return value;
        }
        return defaultValue;
    }
}
