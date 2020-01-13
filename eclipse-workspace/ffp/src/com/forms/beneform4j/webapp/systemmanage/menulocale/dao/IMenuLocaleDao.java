package com.forms.beneform4j.webapp.systemmanage.menulocale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.forms.beneform4j.core.dao.annotation.BatchParam;
import com.forms.beneform4j.core.dao.annotation.Execute;
import com.forms.beneform4j.core.dao.annotation.Executes;
import com.forms.beneform4j.core.dao.annotation.SqlRef;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfLocaleBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfMenuBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfMenuAllFrom;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : <br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
@Repository("menuLocaleDao")
public interface IMenuLocaleDao {

    /**
     * 子菜单
     * 
     * @param menuId
     * @return
     */
    public List<BfMenuBean> dLoadChildren(@Param("menuId") int menuId);

    /**
     * 菜单列表
     * 
     * @return
     */
    public List<BfLocaleBean> dListLocaleBean();

    /**
     * 保存
     * 
     * @param form
     * @return
     */
    @Executes({@Execute(sqlRef = @SqlRef("dDeleteMenuLocale"), param = @BatchParam(false)), @Execute(sqlRef = @SqlRef("dInsertMenuLocale"), param = @BatchParam(item = "menu", property = "localeMenus"))})
    public int[] dSaveLocaleMenu(BfMenuAllFrom form);

}
