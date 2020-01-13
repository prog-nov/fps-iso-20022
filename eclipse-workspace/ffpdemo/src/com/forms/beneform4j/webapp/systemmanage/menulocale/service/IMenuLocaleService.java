package com.forms.beneform4j.webapp.systemmanage.menulocale.service;

import java.util.List;

import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfLocaleBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.bean.BfMenuBean;
import com.forms.beneform4j.webapp.systemmanage.menulocale.form.BfMenuAllFrom;

/**
 * Copy Right Information : Forms Syntron <br>
 * Project : 四方精创 Java EE 开发平台 <br>
 * Description : 菜单国际化<br>
 * Author : XGP <br>
 * Version : 1.0.0 <br>
 * Since : 1.0.0 <br>
 * Date : 2016-12-29<br>
 */
public interface IMenuLocaleService {

    /**
     * 加载子菜单
     * 
     * @param menuId
     * @return
     */
    public List<BfMenuBean> sLoadChildren(int menuId);

    /**
     * 菜单列表
     * 
     * @return
     */
    public List<BfLocaleBean> sListLocaleBean();

    /**
     * 保存
     * 
     * @param form
     * @return
     */
    public int sSaveLocaleMenu(BfMenuAllFrom form);
}
