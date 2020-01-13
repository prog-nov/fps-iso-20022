package com.forms.beneform4j.web.springmvc.argumentresolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartRequest;

import com.forms.beneform4j.web.WebUtils;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.upload.IUploadFile;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 上传文件参数转换器<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class UploadFileArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 判断是否为上传参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return IUploadFile.class.isAssignableFrom(paramType) || IUploadFile[].class.isAssignableFrom(paramType);
    }

    /**
     * 转换上传参数
     */
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = null;
        if (webRequest instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes) webRequest).getRequest();
        } else {
            request = WebUtils.getRequest();
        }
        if (request instanceof MultipartRequest) {
            ServletHelp.setRequestAndResponse(request, null);
            IUploadFile[] uploadFiles = ServletHelp.getUploadFile();
            if (null != uploadFiles && uploadFiles.length != 0)
                if (IUploadFile.class.isAssignableFrom(parameter.getParameterType())) {
                    return uploadFiles[0];
                } else {
                    return uploadFiles;
                }
        }
        return null;
    }
}
