package com.forms.beneform4j.core.util.page;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 分页对象工厂接口 <br>
 * Author : LinJisong <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-3-23<br>
 */
public interface IPageFactory {

    /**
     * 根据上下文环境创建分页对象
     * 
     * @return 分页对象
     */
    public IPage createPage();

    /**
     * 根据上下文环境创建分页对象（适用于多个分页对象）
     * 
     * @param pageKey 表示分页类型的键值
     * @return 分页对象
     */
    public IPage createPage(String pageKey);
}
