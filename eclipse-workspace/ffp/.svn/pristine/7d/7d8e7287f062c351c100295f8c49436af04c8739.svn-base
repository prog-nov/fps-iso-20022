package com.forms.beneform4j.core.util.page;

import com.forms.beneform4j.core.util.CoreUtils;
import com.forms.beneform4j.core.util.config.BaseConfig;
import com.forms.beneform4j.core.util.page.impl.BasePage;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分页帮助类<br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public class PageUtils {

    /**
     * 分页模型属性名称
     */
    private final static String PAGE_ATTR_NAME = PageUtils.class.getName() + ".PAGE_ATTR_NAME";

    /**
     * 创建默认分页对象，先从当前线程获取分页对象，如果获取不到，则新创建一个分页对象
     * 
     * @return 分页对象
     */
    public static IPage createPage() {
        return createPage(IPage.DEFAULT_PAGE_KEY);
    }

    /**
     * 根据分页键值创建分页对象，先从当前线程获取分页对象，如果获取不到，则新创建一个分页对象
     * 
     * @param pageKey
     * @return 分页对象
     */
    public static IPage createPage(String pageKey) {
        IPage page = getPage(pageKey);
        if (null == page) {
            synchronized (PAGE_ATTR_NAME) {
                page = getPage(pageKey);
                if (null == page) {
                    page = BaseConfig.getPageFactory().createPage(pageKey);
                    CoreUtils.putThreadCache(PAGE_ATTR_NAME + "." + pageKey, page);
                }
            }
        }
        return page;
    }

    /**
     * 从当前线程获取默认分页对象
     * 
     * @return 分页对象
     */
    public static IPage getPage() {
        return getPage(IPage.DEFAULT_PAGE_KEY);
    }

    /**
     * 根据分页键值从当前线程获取分页对象
     * 
     * @param pageKey 分页键值
     * @return 分页对象
     */
    public static IPage getPage(String pageKey) {
        return CoreUtils.getThreadCache(PAGE_ATTR_NAME + "." + pageKey, IPage.class);
    }

    /**
     * 设置默认分页的分页大小
     * 
     * @param size -- 每页显示条数
     */
    public static void setPageSize(int size) {
        setPageSize(IPage.DEFAULT_PAGE_KEY, size);
    }

    /**
     * 设置分页对象ID为pageKey的分页大小
     * 
     * @param pageKey -- 分页模型ID
     * @param size -- 每页显示条数
     */
    public static void setPageSize(String pageKey, int size) {
        IPage page = createPage(pageKey);
        if (page instanceof BasePage) {
            ((BasePage) page).setPageSize(size);
        }
    }
}
