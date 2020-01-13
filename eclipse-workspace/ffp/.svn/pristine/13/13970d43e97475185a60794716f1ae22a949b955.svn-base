package com.forms.beneform4j.webapp.systemmanage.guide.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.webapp.systemmanage.guide.form.GuideForm;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 业务操作指引数据库操作接口<br>
 * Author : luow <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-4-21<br>
 */
@Repository("operGuideDao")
@Scope("prototype")
public interface IOperGuideDao {

    /**
     * 获取操作菜单树
     * 
     * @param menuId
     * @param menuName
     * @return
     */
    public List<Map<String, Object>> dList(@Param("menuId") String menuId, @Param("menuName") String menuName);

    /**
     * 取对应的菜单，由前端为其它增加操作指引
     * 
     * @param menuId 传入参数说明 菜单id
     * @return 返回前面的数据信息（如果有指引由返回对应的指引，如果没有指引，则为空的指引） 主要是点击菜单进动态处理值
     */
    public Map<String, Object> dFindForEdit(@Param("menuId") String menuId);

    /**
     * 编辑操作指引
     * 
     * @param form 传入参数说明 界面传入的操作指引，如标示，指引内容
     * @return
     */
    public int dEdit(@Param("menu") GuideForm form);

    /**
     * 为公共界面上的业务操作指引，只要是登录进入系统，在点击指灯泡时，取对应的操作指引
     * 
     * @param menuId
     * @return 返回结果说明 操作指引内容
     */
    public Map<String, String> dFind(@Param("menuId") String menuId);

    /**
     * 新增操作指引
     * 
     * @param form
     * @return
     */
    public int dInsert(@Param("form") GuideForm form);
}
