package com.forms.beneform4j.webapp.common.kindeditor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 解决上传文件在parseRequest反回空的问题<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-28<br>
 */
public class KindEditorMultipartResolver extends CommonsMultipartResolver {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        String dir = request.getParameter("dir");
        if (dir != null) { // kindeditor 上传图片的时候 不进行request 的转换
            return false;
        }
        return super.isMultipart(request);
    }
}
