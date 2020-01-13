package com.forms.beneform4j.web.page;

import javax.servlet.http.HttpServletRequest;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.core.util.page.IPageFactory;
import com.forms.beneform4j.core.util.page.impl.BasePage;
import com.forms.beneform4j.web.WebBeneform4jConfig;
import com.forms.beneform4j.web.servlet.ServletHelp;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 根据Web请求参数创建分页对象的分页工厂<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-25<br>
 */
public class WebPageFactory implements IPageFactory {

    /**
     * 创建分页对象
     */
    @Override
    public IPage createPage() {
        return createPage(IPage.DEFAULT_PAGE_KEY);
    }

    protected BasePage createBasePage(HttpServletRequest request, String pageKey) {
        return new BasePage();
    }

    @Override
    public IPage createPage(String pageKey) {
        HttpServletRequest request = ServletHelp.getRequest();
        BasePage page = createBasePage(request, pageKey);
        if (null == page) {
            page = new BasePage();
        }
        // 获取当前页数
        try {
            page.setCurrentPage(getIntParamValue(request, WebBeneform4jConfig.getCurrentPageParamName()));
        } catch (Exception e) {
            page.setCurrentPage(1);
        }

        // 获取分页大小
        int pageSize = WebBeneform4jConfig.getDefaultPageSize();
        try {
            String pageSizeParamName = WebBeneform4jConfig.getPageSizeParamName();
            if (!CoreUtils.isBlank(pageSizeParamName)) {
                pageSize = getIntParamValue(request, pageSizeParamName);
            }
        } catch (Exception e) {
        }
        page.setPageSize(pageSize);

        // 获取总记录数
        try {
            page.setPageProperty(getIntParamValue(request, WebBeneform4jConfig.getTotalRecordsParamName()));
        } catch (Exception e) {
            page.setNeedCalTotal(true);
        }
        return page;
    }

    /**
     * 从请求中获取整型参数值
     * 
     * @param request 请求对象
     * @param paramName 参数名称
     * @return 整型参数值
     */
    private int getIntParamValue(HttpServletRequest request, String paramName) {
        return Integer.parseInt(request.getParameter(paramName));
    }
}
