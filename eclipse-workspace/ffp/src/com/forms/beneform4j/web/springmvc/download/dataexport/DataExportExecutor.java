package com.forms.beneform4j.web.springmvc.download.dataexport;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.core.dao.mybatis.mapper.impl.DataStreamMapperMethodExecutor;
import com.forms.beneform4j.web.servlet.ServletHelp;
import com.forms.beneform4j.web.springmvc.download.DownloadConsts;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 导出数据的执行器，作用于Mybatis中的查询，替换为流式查询<br>
 * Author : LinJisong <br>
 * Version : 1.1.0 <br>
 * Since : 1.1.0 <br>
 * Date : 2017-2-23<br>
 */
public class DataExportExecutor extends DataStreamMapperMethodExecutor {

    protected boolean isSupport() {
        return DataExportHelp.isExportData();
    }

    protected int getFetchSize() {
        try {
            HttpServletRequest request = ServletHelp.getRequest();
            return Integer.parseInt(request.getParameter(DownloadConsts.FETCH_SIZE_PARAM_NAME));
        } catch (Exception e) {
            return 1000;
        }
    }
}
