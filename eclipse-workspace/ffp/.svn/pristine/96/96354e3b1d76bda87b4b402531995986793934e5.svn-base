package com.forms.beneform4j.web.springmvc.download.dataexport;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.forms.beneform4j.web.springmvc.download.DownloadConsts;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 导出数据的返回值处理器，作用于Spring MVC的返回值处理，统一返回下载视图<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public class DataExportReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return DataExportHelp.isExportData() && DataExportExecutor.getDataStreamReader() != null;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setViewName(DownloadConsts.DOWNLOAD_VIEW_BEAN_NAME);
    }
}
