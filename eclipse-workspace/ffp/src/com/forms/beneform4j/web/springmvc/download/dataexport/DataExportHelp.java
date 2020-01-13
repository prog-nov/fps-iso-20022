package com.forms.beneform4j.web.springmvc.download.dataexport;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.springmvc.download.DownloadConsts;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 导出数据的帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public class DataExportHelp {

    /**
     * 判断当前请求环境是否为导出数据
     * 
     * @return
     */
    public static boolean isExportData() {
        try {
            HttpServletRequest request = ServletHelp.getRequest();
            return null != request //
                    && DownloadConsts.DOWNLOAD_MIME.equalsIgnoreCase(request.getParameter(DownloadConsts.MIME_PARAM_NAME)) //
                    && DownloadConsts.DATA_EXPORT_BUILD_TYPE.equalsIgnoreCase(request.getParameter(DownloadConsts.BUILD_TYPE_PARAM_NAME));
        } catch (Exception e) {
            return false;
        }
    }
}
