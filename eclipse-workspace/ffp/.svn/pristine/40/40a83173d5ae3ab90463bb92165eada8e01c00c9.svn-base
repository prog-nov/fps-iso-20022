package com.forms.beneform4j.core.util.page.impl;

import com.forms.beneform4j.core.util.page.IPage;
import com.forms.beneform4j.core.util.page.IPageFactory;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 简单分页对象工厂实现类 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class BasePageFactory implements IPageFactory {

    /*
     * (non-Javadoc)
     * 
     * @see com.forms.beneform4j.core.util.page.IPageFactory#createPage()
     */
    public IPage createPage() {
        return createPage(IPage.DEFAULT_PAGE_KEY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.forms.beneform4j.core.util.page.IPageFactory#createPage(java.lang.String)
     */
    public IPage createPage(String pageKey) {
        BasePage page = this.createBasePage(pageKey);
        this.setPageProperties(page, pageKey);
        return page;
    }

    /**
     * 根据Key创建分页对象
     * 
     * @param pageKey
     * @return
     */
    protected BasePage createBasePage(String pageKey) {
        return new BasePage();
    }

    /**
     * 设置分页属性
     * 
     * @param page
     * @param pageKey
     */
    protected void setPageProperties(BasePage page, String pageKey) {

    }

}
