package com.forms.beneform4j.webapp.systemmanage.guide.service;

import java.util.List;
import java.util.Map;

import com.forms.beneform4j.webapp.systemmanage.guide.form.GuideForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 业务操作指引服务接口 <br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-14<br>
 */
public interface IOperGuideService {

    /**
     * 列表
     * 
     * @param menuId
     * @param menuName
     * @return
     */
    public List<Map<String, Object>> sList(String menuId, String menuName);

    /**
     * 打开菜单与指引的相关内容
     * 
     * @param menuId
     * @return
     */
    public Map<String, Object> sFindForEdit(String menuId);

    /**
     * 编辑相应的指引到数据库表当中
     * 
     * @param form
     * @return
     */
    public int sEdit(GuideForm form);

    /**
     * 根据菜单Id查找
     * 
     * @param menuId
     * @return
     */
    public Map<String, String> sFind(String menuId);
}
